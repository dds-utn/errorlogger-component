package ar.utn.frba.dds.logger.adapters.connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLConnector {
    private Connection connection;

    // Método para conectarse a la base de datos MySQL
    public void connect(String url, String username, String password) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        System.out.println("Conexión establecida con la base de datos MySQL");
    }

    // Método para desconectarse de la base de datos MySQL
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Desconexión exitosa de la base de datos MySQL");
        }
    }

    // Método para insertar un registro en una tabla MySQL
    public void insert(String tableName, String[] columnNames, Object[] values) throws SQLException {
        StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
        for (int i = 0; i < columnNames.length; i++) {
            sql.append(columnNames[i]);
            if (i < columnNames.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(") VALUES (");
        for (int i = 0; i < values.length; i++) {
            sql.append("?");
            if (i < values.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(")");

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            statement.executeUpdate();
            System.out.println("Registro insertado en la tabla " + tableName);
        }
    }
}
