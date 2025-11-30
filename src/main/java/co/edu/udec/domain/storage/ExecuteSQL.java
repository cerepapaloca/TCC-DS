package co.edu.udec.domain.storage;

import co.edu.udec.domain.Service;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

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

    public <T> T selectRow(UUID uuid, Class<T> clazz) {
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

    public <T> List<T> selectAllRow(Class<T> clazz) {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(AutoCreateSQL.createAllSelectSQL(clazz.getName()));
            ResultSet resultSet = statement.executeQuery();
            ArrayList<T> objects = new ArrayList<>();
            while (resultSet.next()) {
                objects.add(createObject(clazz, resultSet));
            }
            return objects;
        } catch (SQLException e) {
            Service.sendMessage("Error al consultar multiples columnas", e);
            return null;
        }
    }

    private <T> @NotNull T createObject(Class<T> clazz, ResultSet resultSet) {
        try {
            // Obtiene la declaraciones de los constructores
            var constructors = clazz.getDeclaredConstructors();
            var constructor = constructors[0];

            // Obtiene los parámetros del constructor
            var params = constructor.getParameters();
            Object[] values = new Object[params.length];

            // Va por cada parámetros y le asigna el valor
            for (int i = 0; i < params.length; i++) {
                String columnName = params[i].getName();
                values[i] = resultSet.getObject(columnName);
            }

            return (T) constructor.newInstance(values);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
