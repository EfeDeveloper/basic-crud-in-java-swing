package connection;


public class TestConnection {
    public static void main(String[] args) {
        
               ConnectionPostgreSQL connection = new ConnectionPostgreSQL();
               
               connection.connect();
    }
}
