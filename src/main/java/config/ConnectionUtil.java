package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil{

    private static ConnectionUtil instance;

    private ConnectionUtil(){}

    public static ConnectionUtil getInstance(){
        if(instance == null){
            instance = new ConnectionUtil();
        }
        return instance;
    }

        public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(
                    "jdbc:postgresql://samplepsql.cx2v78knsmfh.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=car_dealership",
                    "caradmin",
                    "password");
        }
}
