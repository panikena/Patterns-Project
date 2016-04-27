package com.panikena.DAO;

import com.panikena.Models.User;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artem on 27.04.2016.
 */
public class HibernateUserDAO implements UserDAO {
    @Override
    public User create(User user) throws SQLException {
        return null;
    }

    @Override
    public User read(String name) throws SQLException {
        return null;
    }

    @Override
    public void update(User user) throws SQLException {

    }

    @Override
    public boolean delete(User user) throws SQLException {
        return false;
    }

    @Override
    public List<User> getAll() throws SQLException {
        return null;
    }
}
