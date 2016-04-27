package com.panikena.DAO;

import com.panikena.Models.Car;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artem on 18.04.2016.
 */
public interface CarDAO {
    public Car create(Car car) throws SQLException;
    public Car read(String VIN) throws SQLException;
    public void update(Car car)throws SQLException;
    public boolean delete(Car car) throws SQLException;
    public List<Car> getAll() throws SQLException;
}
