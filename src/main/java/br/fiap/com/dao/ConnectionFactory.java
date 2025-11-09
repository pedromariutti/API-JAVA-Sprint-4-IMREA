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


                String url = System.getenv("DB_URL");
                String user = System.getenv("DB_USER");
                String password = System.getenv("DB_PASSWORD");


                if (url == null || user == null || password == null) {
                    System.err.println("Variáveis de ambiente DB_URL, DB_USER, ou DB_PASSWORD não configuradas.");
                    throw new RuntimeException("Variáveis de ambiente do banco não configuradas!");
                }


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
