package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDb {

    private Connection connection = null;
    private static ConnectDb instance = null;
    private final String DB_URL = "jdbc:postgresql://localhost:5431/deneme";
    private final String DB_USERNAME = "postgres";
    private final String DB_PASSWORD = "changeme";

    private ConnectDb(){
        try {
            this.connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection(){
        return this.connection;
    }

    public static Connection getInstance(){
        try {
            if (instance == null || instance.getConnection().isClosed()){
                instance = new ConnectDb();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return instance.getConnection();
    }
}
