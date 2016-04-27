package com.panikena.DAO;

import com.panikena.Models.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artem on 27.04.2016.
 */
public interface UserDAO {

    public User create(User user) throws SQLException;
    public User read(String name) throws SQLException;
    public void update(User user)throws SQLException;
    public boolean delete(User user) throws SQLException;
    public List<User> getAll() throws SQLException;
}
