
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MY_CONNECTION {
    //in this class we will make connection to mySQL database
    //first download connector 
    //copy connector to this project in libraries add JAR
    //open xampp, start apache and mysql
    // goto myPHPAdmin
    //create a database
    
    //create a function to connect to database
    public Connection createConnection(){
        Connection connection=null;
        MysqlDataSource msd= new MysqlDataSource();
        
        msd.setServerName("localhost");
        msd.setPortNumber(3306);
        msd.setUser("root");
        msd.setPassword("");
        msd.setDatabaseName("java_hotel_db");
        
        try {
            connection = (Connection) msd.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MY_CONNECTION.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
