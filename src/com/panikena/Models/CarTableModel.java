package com.panikena.Models;

import com.panikena.DAO.CarDAO;
import com.panikena.DAO.DAOFactory;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Artem on 18.04.2016.
 */
public class CarTableModel extends AbstractTableModel {
    String [] columnNames = { "VIN", "License plate", "Owner(s)", "Color"};

    List<Car> data = new ArrayList<>();

    public CarTableModel(int DBType){
        updateData(DBType);
    }

    public void updateData(int DBtype){
        DAOFactory factory = DAOFactory.getDAOFactory(DBtype);
        CarDAO carDAO = factory.getCarDAO();
        try {
            data  = carDAO.getAll();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        System.out.println("getColumnName: " + col);
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Car car = data.get(row);
        switch (col){
            case 0: return car.getVIN();
            case 1: return car.getLicense_plate();
            case 2: return car.getOwner();
            case 3: return car.getColor();
            default:
                throw new IllegalArgumentException();
        }
    }

    public Car getRowAt(int index){
        return data.get(index);
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }


}
