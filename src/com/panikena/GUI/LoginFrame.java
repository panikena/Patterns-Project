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


        nameField = new JTextField();
        passwordField = new JPasswordField();


    }
}
