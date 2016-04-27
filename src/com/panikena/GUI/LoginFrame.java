package com.panikena.GUI;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    String name, password;
    JTextField nameField;
    JPasswordField passwordField;
    JLabel nameLabel, passwordLabel;
    JButton signInButton;
    JPanel panel;


    public LoginFrame(){
        super("Login");
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(300, 250));
        this.setPreferredSize(new Dimension(300, 250));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - this.getWidth()) / 2;
        int centerY = (screenSize.height - this.getHeight()) / 2;

        this.setLocation(centerX, centerY);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

        panel = new JPanel();
        this.add(panel);

        nameField = new JTextField();
        nameLabel = new JLabel("Name: ");
        passwordField = new JPasswordField();
        passwordLabel = new JLabel("Password: ");
        signInButton = new JButton("Sign in");

        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();


        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(25, 0, 10, 0);
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(nameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        nameField.setPreferredSize(new Dimension(100, 30));
        panel.add(nameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 10, 0);
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 10, 10);
        passwordField.setPreferredSize(new Dimension(100, 30));
        panel.add(passwordField, constraints);


        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(signInButton, constraints);

        this.setVisible(true);

    }
}
