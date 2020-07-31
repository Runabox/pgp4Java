package com.runabox.main.gui.objects;

import com.runabox.main.gui.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class SavePasswordGUI extends JPanel implements ActionListener{
    private JLabel jcomp1;
    private JButton jcomp2;
    private JButton jcomp3;

    public static JFrame frame;
    private static IconHandler ico;

    String password;

    public SavePasswordGUI(String password1) {
        //init standard
        password = password1;
        ico = new IconHandler();

        //construct components
        jcomp1 = new JLabel ("Would you like to save your password?");
        jcomp2 = new JButton ("Yes");
        jcomp3 = new JButton ("No");

        //adjust size and set layout
        setPreferredSize (new Dimension (358, 90));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);

        //action listeners
        jcomp2.addActionListener(this);

        jcomp3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //close frame if no
                SavePasswordGUI.frame.dispose();
            }
        });

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (70, 5, 225, 25);
        jcomp2.setBounds (15, 45, 100, 25);
        jcomp3.setBounds (240, 45, 100, 25);

        initFrame();
    }


    public void initFrame() {
        frame = new JFrame ("pgp4Java v" + GUI.version);
        frame.setResizable(false);
        frame.getContentPane().add (this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        ico.setDefaultIcon(frame);
        frame.setVisible (true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //save password then close frame

        try {
            PGPasswordDialog.savePassword(password);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        PGPasswordDialog.frame.dispose();
        frame.dispose();
    }
}
