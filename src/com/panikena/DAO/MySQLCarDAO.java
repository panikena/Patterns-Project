package com.panikena.DAO;

import com.panikena.Models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 18.04.2016.
 */
public class MySQLCarDAO implements CarDAO {

    private static Connection connection;

    public MySQLCarDAO() {
        if (connection == null){
            connection = MySQLDAOFactory.createConnection();
        }
        System.out.println("MySQLDAO constructor, conn: " + connection);
    }

    @Override
    public Car create(Car car) throws SQLException{
        String sql = "INSERT INTO cars.cars (VIN, license_plate, owner, color) VALUES (?, ?, ?, ?);";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, car.getVIN());
        stm.setString(2, car.getLicense_plate());
        stm.setString(3, car.getOwner());
        stm.setString(4, car.getColor());
        stm.executeUpdate();
        return car;
    }
    @Override
    public Car read(String VIN) throws SQLException{
        String sql = "SELECT * FROM cars.cars WHERE VIN = ?;";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, VIN);
        ResultSet rs = stm.executeQuery();
        rs.next();
        Car car = new Car.CarBuilder(rs.getString("VIN"))
                .addOwner(rs.getString("owner"))
                .addColor(rs.getString("color"))
                .addPlate(rs.getString("license_plate"))
                .build();
        return car;
    }
    @Override
    public void update(Car car) throws SQLException{
        String sql = "UPDATE cars.cars SET owner=? , license_plate=? , color=? WHERE VIN=?;";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, car.getOwner());
        stm.setString(2, car.getLicense_plate());
        stm.setString(3, car.getColor());
        stm.setString(4, car.getVIN());
        stm.executeUpdate();
    }
    @Override
    public boolean delete(Car car) throws SQLException{
        String sql = "DELETE FROM cars.cars WHERE VIN=?;";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, car.getVIN());
        stm.executeUpdate();
        return true;
    }
    @Override
    public List<Car> getAll() throws SQLException{

        String sql = "SELECT * FROM cars.cars;";
        System.out.println("Getting all with connection: " + connection);
        PreparedStatement stm = connection.prepareStatement(sql);

        ResultSet rs = stm.executeQuery();
        List<Car> list = new ArrayList<>();
        while (rs.next()) {
            Car car = new Car.CarBuilder(rs.getString("VIN"))
                    .addOwner(rs.getString("owner"))
                    .addColor(rs.getString("color"))
                    .addPlate(rs.getString("license_plate"))
                    .build();

            list.add(car);
        }
        return list;
    }
}
