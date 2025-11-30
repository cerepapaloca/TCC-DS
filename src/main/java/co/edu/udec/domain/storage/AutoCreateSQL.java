package co.edu.udec.domain.storage;

import java.util.UUID;

public class AutoCreateSQL {

    public static String createInsertSQL(Object obj) {
        Class<?> clazz = obj.getClass();
        var fields = clazz.getDeclaredFields();

        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        // Hacer un for por cada valor de la clase
        for (var field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(obj);
                columns.append(field.getName()).append(",");

                if (value instanceof String || value instanceof UUID) {
                    values.append("'").append(value).append("',");
                } else {
                    values.append(value).append(",");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        // eliminar la coma
        columns.setLength(columns.length() - 1);
        values.setLength(values.length() - 1);

        // Construye el SQL
        return String.format(
                "INSERT INTO %s (%s) VALUES (%s);",
                obj.getClass().getSimpleName(),
                columns,
                values
        );
    }

    private static String createSQLFromID(Object obj) {
        Class<?> clazz = obj.getClass();
        var fields = clazz.getDeclaredFields();

        String column = "";
        String value = "";
        for (var field : fields) {
            field.setAccessible(true);
            try {
                column = field.getName();
                value = field.get(obj).toString();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            break;
        }

        // Construye el SQL
        return String.format(
                " FROM `%s` WHERE %s = %s;",
                obj.getClass().getSimpleName(),
                column,
                value
        );
    }

    public static String createDeleteSQL(UUID uuid, String name) {
        return "DELETE FROM " + name + " WHERE uuid = '" + uuid + "';";
    }

    public static String createSelectSQL(UUID uuid, String name) {
        return "SELECT * FROM " + name + " WHERE uuid = '" + uuid + "';";
    }

    public static String createAllSelectSQL(String table) {
        return "SELECT * FROM " + table;
    }

}
