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
        String sql = "INSERT INTO cars.owners (name, country, dob, sex) VALUES (?, ?, ?, ?);";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, owner.getName());
        stm.setString(2, owner.getCountry());
        stm.setDate(3, owner.getDob());
        stm.setString(4, owner.getSex());
        stm.executeUpdate();
        return owner;
    }

    @Override
    public Owner read(String name) throws SQLException {
        String sql = "SELECT * FROM cars.owners WHERE name = ?;";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, name);
        ResultSet rs = stm.executeQuery();
        rs.next();
        return new Owner(rs.getString("name"),
                        rs.getString("country"),
                        rs.getDate("color"),
                        rs.getString("sex"));
    }

    @Override
    public void update(Owner owner) throws SQLException {
        String sql = "UPDATE cars.owners SET country=? , dob=? , sex=? WHERE name=?;";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, owner.getCountry());
        stm.setDate(2, owner.getDob());
        stm.setString(3, owner.getSex());
        stm.setString(4, owner.getName());
        stm.executeUpdate();
    }

    @Override
    public boolean delete(Owner owner) throws SQLException {
        String sql = "DELETE FROM cars.owners WHERE name=?;";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, owner.getName());
        stm.executeUpdate();
        return true;
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
