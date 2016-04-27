package com.panikena.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Artem on 27.04.2016.
 */
public class LoginFrame extends JFrame {

    String name, password;
    JTextField nameField;
    JPasswordField passwordField;
    JLabel nameLabel, passwordLabel;
    JButton signInButton, cancelButton;
    JPanel panel;


    LoginFrame(){
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(600, 400));
        this.setPreferredSize(new Dimension(600, 400));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - this.getWidth()) / 2;
        int centerY = (screenSize.height - this.getHeight()) / 2;

        this.setLocation(centerX, centerY);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel();
        this.add(panel);

        nameField = new JTextField();
        nameLabel = new JLabel("Name: ");
        passwordField = new JPasswordField();
        passwordLabel = new JLabel("Password: ");

        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();


        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(nameLabel, constraints);

        constraints.gridy = 1;
        panel.add(nameField, constraints);


        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(passwordLabel, constraints);




    }
}
