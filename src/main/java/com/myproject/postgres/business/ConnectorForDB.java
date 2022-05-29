package com.myproject.postgres.business;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectorForDB {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/productdb";
    private static final String USER = "postgres";
    private static final String PASS = "2303";

    private static final ConnectorForDB instance = new ConnectorForDB();

    public static ConnectorForDB getInstance() {
        return instance;
    }

    public Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public Connection createPropertyConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("database.properties"))){
            props.load(in);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        String url = props.getProperty("url");
        String user = props.getProperty("user");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, user, password);
    }



}
