package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDAO {

    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String DB_URL;
    private static String USER;
    private static String PASS;

    static {
        Properties property = new Properties();
        try {
            property.load(new FileInputStream("src/main/resources/config.properties"));

            DB_URL = property.getProperty("db.host");
            USER = property.getProperty("db.login");
            PASS = property.getProperty("db.password");

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }

    private Connection conn;

    public ConnectionDAO() {
        try {
            Class.forName(JDBC_DRIVER).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnectionDAO() {
        return conn;
    }

    public void closeConnectionDAO() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
