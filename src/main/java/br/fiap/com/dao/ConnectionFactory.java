package br.fiap.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {

                Class.forName("oracle.jdbc.driver.OracleDriver");

                String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
                String user = "rm75999";
                String password = "150896";


                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Conexão aberta com sucesso.");

            } catch (ClassNotFoundException e) {
                System.err.println("Driver JDBC não encontrado: " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
                System.out.println("Conexão fechada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}
