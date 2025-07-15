package connection;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class ConnectionPostgreSQL {

    private Connection connection;

    private String user;
    private String password;
    private String server;
    private String port;
    private String DBName;
    private String connectionUrl;

    public ConnectionPostgreSQL() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("db.properties")); 

            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
            server = props.getProperty("db.server");
            port = props.getProperty("db.port");
            DBName = props.getProperty("db.name");
            connectionUrl = "jdbc:postgresql://" + server + ":" + port + "/" + DBName;
        } catch (IOException e) {
            System.out.println("Error leyendo db.properties: " + e.getMessage());
        }
    }

    public Connection connect() {
        System.out.println("Hello");
        try {
            connection = DriverManager.getConnection(connectionUrl, user, password);
            if(connection != null){
                System.out.println("Successful connection!");
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return connection;
    }
}
