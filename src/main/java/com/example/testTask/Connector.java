package com.example.testTask;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Connector implements AutoCloseable {

    public static Connection connection;
    static Properties properties = new Properties();

    public static Connection getConnection() {

        try {
            FileInputStream configFile = new FileInputStream("src/main/resources/com/example/testTask/config/properties.ini");
            properties.load(configFile);

            String url = properties.getProperty("url");
            String user = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
            configFile.close();
        } catch (SQLException e) {
            System.out.println("Ошибка при установлении соединения: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
        return connection;
    }

    @Override
    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

