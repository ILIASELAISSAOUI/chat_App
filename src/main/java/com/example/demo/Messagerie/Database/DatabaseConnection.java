package com.example.demo.Messagerie.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/messagerie";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection connection;

    public  DatabaseConnection() {
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion à la base de données
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            System.out.println("Connexion à la base de données réussie.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connexion à la base de données fermée.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
