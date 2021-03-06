package com.panikena.DAO;

/**
 * Created by Artem on 25.04.2016.
 */

public abstract class DAOFactory {


    public static final int MYSQL = 1;
    public static final int HIBERNATE = 2;

    public abstract CarDAO getCarDAO();


    public static DAOFactory getDAOFactory(
            int whichFactory) {

        switch (whichFactory) {
            case MYSQL:
                return new MySQLDAOFactory();
            case HIBERNATE:
                return new HibernateDAOFactory();
            default           :
                return null;
        }
    }
}