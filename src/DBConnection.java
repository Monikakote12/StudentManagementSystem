import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/studentdb";
        String username = "root";
        String password = System.getenv("DB_PASSWORD");

        try{
            Connection connection = DriverManager.getConnection(url, username, password);
//            System.out.println("Database connected Successfully");
            return connection;
        }catch (SQLException e){
//            System.out.println("Databse connection Failed");
            e.printStackTrace();
            return null;
        }
    }
}
