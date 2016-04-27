package com.panikena.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Artem on 25.04.2016.
 */
public class MySQLDAOFactory extends DAOFactory {

    public static final String user = "root";
    public static final String password = "root";
    public static final String url = "jdbc:mysql://localhost:3306/cars";

    public static Connection createConnection(){
        try{
            Connection connection =  DriverManager.getConnection(url, user, password);
            return connection;
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public CarDAO getCarDAO() {
        return new MySQLCarDAO();
    }




    @Override
    public UserDAO getUserDAO(){
      return new MySQLUserDAO();
    }
    /*
    * @Overrride
    * public UserDAO getSomethingDAO(){
    *   return new MySQLSomethingDAO();
    * }
    *
    *
    * */

}
