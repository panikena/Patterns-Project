package com.panikena.GUI;

import com.panikena.DAO.CarDAO;
import com.panikena.Models.CarTableModel;
import com.panikena.DAO.DAOFactory;

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
public class Mainframe extends JFrame implements TableModelListener{

    JTable table;
    JScrollPane scrollPane;
    JPanel tablePanel, buttonPanel;
    JButton editButton, addButton, deleteButton, checkButton;
    CarTableModel tableModel;
    CarDAO carDAO;
    int DBType;

    public Mainframe(int DBtype) {
        super("Car list");

        this.DBType = DBtype;

        carDAO = DAOFactory.getDAOFactory(DBType).getCarDAO();

        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(600, 400));
        this.setPreferredSize(new Dimension(600, 400));
        this.setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - this.getWidth()) / 2;
        int centerY = (screenSize.height - this.getHeight()) / 2;

        this.setLocation(centerX, centerY);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        this.add(tablePanel, BorderLayout.CENTER);


        tableModel = new CarTableModel(DBtype);
        table = new JTable(tableModel);
        table.getModel().addTableModelListener(this);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        TableColumn column;
        for (int i = 0; i < 4; i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 0 || i == 2) {
                column.setPreferredWidth(150);
            } else {
                column.setPreferredWidth(80);
            }
        }

        scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);


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
                        "Delete " + tableModel.getValueAt( table.getSelectedRow(), 0),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        JOptionPane.NO_OPTION);

                if(option == JOptionPane.YES_OPTION){
                    try{
                        carDAO.delete(tableModel.getRowAt(table.getSelectedRow()));
                    }catch (SQLException ex){
                        ex.printStackTrace();
                    }
                    tableModel.updateData(DBtype);
                    table.updateUI();
                }
            }
        });

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Desktop.getDesktop().browse(URI.create("http://www.vindecoderz.com/EN/check-lookup/" + tableModel.getRowAt(table.getSelectedRow()).getVIN()));
                }catch (IOException ex){
                    ex.printStackTrace();
                }

            }
        });

        this.setVisible(true);
        this.pack();

    }

    public CarTableModel getTableModel(){
        return tableModel;
    }

    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        System.out.println("Row:"+ row);
        System.out.println("Column" + column);
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
    }

    public int getDBType(){
        return DBType;
    }
}
