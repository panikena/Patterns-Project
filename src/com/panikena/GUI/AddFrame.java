package com.panikena.GUI;


import com.panikena.Models.Car;
import com.panikena.DAO.CarDAO;
import com.panikena.DAO.DAOFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Artem on 22.04.2016.
 */
public class AddFrame extends JDialog {

    private static AddFrame instance = null;

    JPanel panel;
    JTextField plateTextField, ownerTextField, VinTextField, colorTextField;
    JLabel VinLabel, plateLabel, ownerLabel, colorLabel;
    JButton addButton, cancelButton;

    CarDAO carDAO;

    private String VIN, plate, owner, color;

    private AddFrame(JFrame parent) {
        super((Mainframe)parent);



        System.out.println("Creating connection in AddFrame");
        carDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getCarDAO();

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        this.add(panel);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 330));
        this.setLocationRelativeTo(null);

        GridBagConstraints constraints = new GridBagConstraints();

        VinTextField = new JTextField();
        VinTextField.setPreferredSize(new Dimension(150, 25));

        plateTextField = new JTextField();
        plateTextField.setPreferredSize(new Dimension(150, 25));

        ownerTextField = new JTextField();
        ownerTextField.setPreferredSize(new Dimension(150, 25));

        colorTextField = new JTextField();
        colorTextField.setPreferredSize(new Dimension(150, 25));

        VinLabel = new JLabel("VIN: ");
        plateLabel = new JLabel("License plate: ");
        ownerLabel = new JLabel("Owner(s): ");
        colorLabel = new JLabel("Color: ");

        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 25, 10, 25);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(VinLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(VinTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(plateLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(plateTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(ownerLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(ownerTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(colorLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(colorTextField,constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(addButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        panel.add(cancelButton, constraints);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VIN = VinTextField.getText();
                plate = plateTextField.getText();
                owner = ownerTextField.getText();
                color = colorTextField.getText();
                if (VIN.matches("[0-9A-Z&&[^IOQ]]{17}") && !color.equals("") && !owner.equals("")) {
                    Car car = new Car.CarBuilder(VIN).addOwner(owner).addPlate(plate).addColor(color).build();
                    try {
                        carDAO.create(car);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    ((Mainframe) parent).getCarTableModel().updateData(((Mainframe) parent).getDBType());
                    ((Mainframe) parent).carsTable.updateUI();

                    VinTextField.setText("");
                    plateTextField.setText("");
                    ownerTextField.setText("");
                    colorTextField.setText("");

                    instance.setVisible(false);
                }
                else if(!VIN.matches("[0-9A-Z&&[^IOQ]]{17}")){
                    JOptionPane.showMessageDialog(instance, "Invalid VIN!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(color.equals("")){
                    JOptionPane.showMessageDialog(instance, "Invalid color!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(owner.equals("")){
                    JOptionPane.showMessageDialog(instance, "Invalid owner!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VinTextField.setText("");
                plateTextField.setText("");
                ownerTextField.setText("");
                colorTextField.setText("");
                instance.setVisible(false);
            }
        });

        this.pack();
    }

    public static AddFrame getInstance(JFrame parent){
        if(instance == null ){
            instance = new AddFrame(parent);
        }
        if(!instance.isVisible()){
            instance.setVisible(true);
        }
        return instance;
    }

}
