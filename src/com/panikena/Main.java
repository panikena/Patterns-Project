package com.panikena;

import com.panikena.DAO.DAOFactory;
import com.panikena.GUI.LoginFrame;
import com.panikena.GUI.Mainframe;
import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;
import javax.swing.*;

/**
 * Created by Artem on 25.04.2016.
 */
public class Main {

    public static void  main(String args[]){
        try{
            UIManager.setLookAndFeel(SyntheticaPlainLookAndFeel.class.getName());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //LoginFrame lf = new LoginFrame(DAOFactory.HIBERNATE);

        Mainframe mf = new Mainframe(DAOFactory.MYSQL);
        //mf.setVisible(true);
    }
}
