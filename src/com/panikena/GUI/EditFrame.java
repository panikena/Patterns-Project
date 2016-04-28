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
 * Created by Artem on 26.04.2016.
 */
public class EditFrame extends JDialog {

    JPanel panel;
    private static JTextField plateTextField, ownerTextField, VinTextField, colorTextField;
    JLabel VinLabel, plateLabel, ownerLabel, colorLabel;
    JButton addButton, cancelButton;

    CarDAO carDAO;

    private static EditFrame instance = null;

    private static String VIN, plate, owner, color;


    private EditFrame(JFrame parent, Car car){

        super((Mainframe)parent);


        carDAO = DAOFactory.getDAOFactory(((Mainframe) parent).getDBType()).getCarDAO();

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        this.add(panel);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 330));
        this.setLocationRelativeTo(null);

        GridBagConstraints constraints = new GridBagConstraints();

        VinTextField = new JTextField();
        VinTextField.setPreferredSize(new Dimension(150, 25));
        VinTextField.setEditable(false);

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

                plate = plateTextField.getText();
                owner = ownerTextField.getText();
                color = colorTextField.getText();

                if (!color.equals("") && !owner.equals("") && !plate.equals("")) {
                    Car car = new Car.CarBuilder(VIN).addOwner(owner).addPlate(plate).addColor(color).build();
                    try {
                        carDAO.update(car);
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
                else if(plate.equals("")){
                    JOptionPane.showMessageDialog(instance, "Invalid license plate!", "Error", JOptionPane.ERROR_MESSAGE);
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

    public static EditFrame getInstance(JFrame parent) {
        Car car;
        if (((Mainframe) parent).carsTable.getSelectedRow() > -1) {
            car = ((Mainframe) parent).carTableModel.getRowAt(((Mainframe) parent).carsTable.getSelectedRow());
            VIN = car.getVIN();
            plate = car.getLicense_plate();
            owner = car.getOwner();
            color = car.getColor();
        }
        else{
            VIN = plate = owner = color = "";
            JOptionPane.showMessageDialog(instance, "Select row!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(instance == null ){
            instance = new EditFrame(parent, car);
        }

        VinTextField.setText(VIN);
        plateTextField.setText(plate);
        ownerTextField.setText(owner);
        colorTextField.setText(color);

        if(!instance.isVisible()){
            instance.setVisible(true);
        }


        return instance;
    }

}
