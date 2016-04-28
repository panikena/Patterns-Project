package com.panikena.DAO;

import com.panikena.Models.Owner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 28.04.2016.
 */
public class MySQLOwnerDAO implements OwnerDAO {

    Connection connection;

    public MySQLOwnerDAO() {
        if (connection == null){
            connection = MySQLDAOFactory.createConnection();
        }
        System.out.println("MySQLDAO constructor, conn: " + connection);
    }

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
        String sql = "SELECT * FROM cars.owners;";
        System.out.println("Getting all with connection: " + connection);
        PreparedStatement stm = connection.prepareStatement(sql);

        ResultSet rs = stm.executeQuery();
        List<Owner> list = new ArrayList<>();
        while (rs.next()) {
            Owner owner = new Owner(
                    rs.getString("name"),
                    rs.getString("country"),
                    Date.valueOf(rs.getString("dob")),
                    rs.getString("sex")
            );

            list.add(owner);
        }
        return list;
    }
}
