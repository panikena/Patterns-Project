package com.panikena.Models;

import com.panikena.DAO.CarDAO;
import com.panikena.DAO.DAOFactory;
import com.panikena.DAO.OwnerDAO;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by Artem on 27.04.2016.
 */
public class OwnerTableModel extends AbstractTableModel {

    List<Owner> data;

    String [] columnNames = {"Name","Country", "Date of birth",  "Sex"};

    public OwnerTableModel(int DBType){
        updateData(DBType);
    }

    public void updateData(int DBtype){
        DAOFactory factory = DAOFactory.getDAOFactory(DBtype);
        OwnerDAO ownerDAO = factory.getOwnerDAO();
        try {
            data  = ownerDAO.getAll();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }


    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Owner owner = data.get(rowIndex);
        switch (columnIndex){
            case 0: return owner.getName();
            case 1: return owner.getCountry();
            case 2: return owner.getDob();
            case 3: return owner.getSex();
            default:
                throw new IllegalArgumentException();
        }
    }
}
