package lk.ijse.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnetion {

    private static DBConnetion dbConnetion;
    private Connection connection;

    private DBConnetion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost/possytem";
            String userName="root";
            String password="root";

            connection = DriverManager.getConnection(url, userName, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace( );
        } catch (SQLException e) {
            e.printStackTrace( );
        }
    }
    public static DBConnetion getInstance(){
        return (null == dbConnetion) ? (dbConnetion=new DBConnetion()):(dbConnetion);
    }
    public Connection getConnection(){
        return connection;
    }
}
