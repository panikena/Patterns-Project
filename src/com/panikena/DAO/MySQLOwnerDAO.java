package com.panikena.DAO;

import com.panikena.Models.Owner;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artem on 28.04.2016.
 */
public class MySQLOwnerDAO implements OwnerDAO {

    @Override
    public Owner create(Owner owner) throws SQLException {
        return null;
    }

    @Override
    public Owner read(String name) throws SQLException {
        return null;
    }

    @Override
    public void update(Owner owner) throws SQLException {

    }

    @Override
    public boolean delete(Owner owner) throws SQLException {
        return false;
    }

    @Override
    public List<Owner> getAll() throws SQLException {
        return null;
    }
}
