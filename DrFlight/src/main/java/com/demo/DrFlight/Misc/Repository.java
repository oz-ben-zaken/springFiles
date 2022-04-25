package com.demo.DrFlight.Misc;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Repository {
    private static Connection con = null;
    private Statement stm = null;

    /**
     * Try to open connection to the database specified in the config file.
     * Returns (Connection) connection if opened successfully or null if not.
     */
    public Connection getCon() {
        try {

            Class.forName("org.postgresql.Driver");

            String configFilePath = "src/main/java/com/demo/DrFlight/config.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            String url = prop.getProperty("DB_URL");
            String user = prop.getProperty("DB_USER");
            String password = prop.getProperty("DB_PASSWORD");
            if(con == null) {
                con = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to Postgress server.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.con;
    }

    /**
     * Try to create statement to the connection we have.
     * Returns (Statement) statement if created successfully or null if not.
     */
    public Statement getStm() {
        try {
            stm = this.con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stm;
    }
}