package com.panikena.DAO;

import com.panikena.Models.Owner;
import com.panikena.Models.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artem on 28.04.2016.
 */
public interface OwnerDAO {
    public Owner create(Owner owner) throws SQLException;
    public Owner read(String name) throws SQLException;
    public void update(Owner owner)throws SQLException;
    public boolean delete(Owner owner) throws SQLException;
    public List<Owner> getAll() throws SQLException;
}
