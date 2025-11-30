package co.edu.udec.domain.storage;

import co.edu.udec.domain.Service;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static co.edu.udec.domain.storage.DataBaseMySql.getConnection;

@UtilityClass
@SuppressWarnings("SqlSourceToSinkFlow")
public class ExecuteSQL {


    public void addRow(Object object) {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(AutoCreateSQL.createInsertSQL(object));
            //ResultSet resultSet = statement.executeQuery();
            statement.executeUpdate();
        } catch (SQLException e) {
            Service.sendMessage("Error al crear el registro", e);
        }
    }

    public void removeRow(UUID uuid, Class<?> clazz) {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(AutoCreateSQL.createDeleteSQL(uuid, clazz.getSimpleName()));
            //ResultSet resultSet = statement.executeQuery();
            statement.executeUpdate();
        } catch (SQLException e) {
            Service.sendMessage("Error al crear el registro", e);
        }
    }

    public String selectRow(UUID uuid, Class<?> clazz) {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(AutoCreateSQL.createSelectSQL(uuid, clazz.getName()));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return createObject(clazz, resultSet);
        } catch (SQLException e) {
            Service.sendMessage("Error al consultar la columnas", e);
            return null;
        }
    }

    public List<String> selectAllRow(Class<?> clazz) {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(AutoCreateSQL.createAllSelectSQL(clazz.getSimpleName()));
            ResultSet resultSet = statement.executeQuery();
            ArrayList<String> objects = new ArrayList<>();
            while (resultSet.next()) {
                objects.add(createObject(clazz, resultSet));
            }
            return objects;
        } catch (SQLException e) {
            Service.sendMessage("Error al consultar multiples columnas", e);
            return null;
        }
    }

    private @NotNull String createObject(Class<?> clazz, ResultSet rs) throws SQLException {
        StringBuilder builder = new StringBuilder();
        builder.append(clazz.getSimpleName()).append("={");
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            builder.append(field.getName()).append("=").append(rs.getObject(field.getName())).append(",");
        }
        builder.setLength(builder.length() - 1);
        return builder.append("}").toString();
    }

}
