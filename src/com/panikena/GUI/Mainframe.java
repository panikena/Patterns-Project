package com.panikena.GUI;

import com.panikena.DAO.CarDAO;
import com.panikena.Models.CarTableModel;
import com.panikena.DAO.DAOFactory;
import com.panikena.Models.OwnerTableModel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;

/**
 * Created by Artem on 18.04.2016.
 */
public class Mainframe extends JFrame{

    JMenuBar menuBar;
    JMenu fileMenu;
    JTable carsTable, ownersTable;
    JScrollPane carsScrollPane, ownersScrollPane;
    JTabbedPane tabbedPane;
    JPanel buttonPanel;
    JButton editButton, addButton, deleteButton, checkButton;
    CarTableModel carTableModel;
    OwnerTableModel ownerTableModel;
    CarDAO carDAO;
    int DBType;

    public Mainframe(int DBtype) {
        super("Car list");

        this.DBType = DBtype;

        carDAO = DAOFactory.getDAOFactory(DBType).getCarDAO();

        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(650, 400));
        this.setPreferredSize(new Dimension(650, 400));
        this.setResizable(true);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - this.getWidth()) / 2;
        int centerY = (screenSize.height - this.getHeight()) / 2;

        this.setLocation(centerX, centerY);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        fileMenu = new JMenu("File");
        menuBar = new JMenuBar();

        menuBar.add(fileMenu);
        this.add(menuBar, BorderLayout.NORTH);


        tabbedPane = new JTabbedPane();
        this.add(tabbedPane, BorderLayout.CENTER);


        carTableModel = new CarTableModel(DBtype);
        carsTable = new JTable(carTableModel);
        carsScrollPane = new JScrollPane(carsTable);
        tabbedPane.add("Cars", carsScrollPane);

        TableRowSorter<TableModel> carsSorter = new TableRowSorter<>(carsTable.getModel());
        carsTable.setRowSorter(carsSorter);
        TableColumn column;
        for (int i = 0; i < 4; i++) {
            column = carsTable.getColumnModel().getColumn(i);
            if (i == 0 || i == 2) {
                column.setPreferredWidth(150);
            } else {
                column.setPreferredWidth(100);
            }
        }


        ownerTableModel = new OwnerTableModel(DBtype);
        ownersTable = new JTable(ownerTableModel);
        ownersScrollPane = new JScrollPane(ownersTable);
        tabbedPane.add("Owners", ownersScrollPane);
        TableRowSorter<TableModel> ownersSorter = new TableRowSorter<>(ownersTable.getModel());
        ownersTable.setRowSorter(ownersSorter);


        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(105, 380));
        this.add(buttonPanel, BorderLayout.EAST);

        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        checkButton = new JButton("Check");
        addButton.setMaximumSize(new Dimension(80, 25));
        editButton.setMaximumSize(new Dimension(80, 25));
        deleteButton.setMaximumSize(new Dimension(80, 25));
        checkButton.setMaximumSize(new Dimension(80, 25));

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(editButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(addButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(checkButton);
        editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddFrame.getInstance(Mainframe.this);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditFrame.getInstance(Mainframe.this);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String [] options = {"Yes", "No"};
                int option = JOptionPane.showOptionDialog(Mainframe.this,
                        "Do you want to delete an entry?",
                        "Delete " + carTableModel.getValueAt(carsTable.getSelectedRow(), 0),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        JOptionPane.NO_OPTION);

                if(option == JOptionPane.YES_OPTION){
                    try{
                        carDAO.delete(carTableModel.getRowAt(carsTable.getSelectedRow()));
                    }catch (SQLException ex){
                        ex.printStackTrace();
                    }
                    carTableModel.updateData(DBtype);
                    carsTable.updateUI();
                }
            }
        });

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Desktop.getDesktop().browse(URI.create("http://www.vindecoderz.com/EN/check-lookup/" + carTableModel.getRowAt(carsTable.getSelectedRow()).getVIN()));
                }catch (IOException ex){
                    ex.printStackTrace();
                }

            }
        });

        this.setVisible(true);
        this.pack();

    }

    public CarTableModel getCarTableModel(){
        return carTableModel;
    }

//    public void tableChanged(TableModelEvent e) {
//        int row = e.getFirstRow();
//        int column = e.getColumn();
//        System.out.println("Row:"+ row);
//        System.out.println("Column" + column);
//        TableModel model = (TableModel)e.getSource();
//        String columnName = model.getColumnName(column);
//        Object data = model.getValueAt(row, column);
//    }

    public int getDBType(){
        return DBType;
    }
}
