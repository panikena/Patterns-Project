package com.panikena.GUI;

import com.panikena.DAO.DAOFactory;
import com.panikena.DAO.UserDAO;
import com.panikena.Models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LoginFrame extends JFrame {

    String name, password;
    JTextField nameField;
    JPasswordField passwordField;
    JLabel nameLabel, passwordLabel;
    JButton signInButton;
    JPanel panel;


    public LoginFrame(int DBType){
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


        UserDAO userDAO = DAOFactory.getDAOFactory(DBType).getUserDAO();
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

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = nameField.getText();
                try {
                    password = SHA1(passwordField.getPassword().toString());
                } catch (NoSuchAlgorithmException ex) {
                    ex.printStackTrace();
                }
                try {
                    User user = userDAO.read(name);
                    if (user != null && user.getPassword().equals(password)) {
                        LoginFrame.this.setVisible(false);
                        nameField.setText("");
                        passwordField.setText("");
                        new Mainframe(DBType);
                    } else {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Invalid name or password!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        this.setVisible(true);

    }

    public static String SHA1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
