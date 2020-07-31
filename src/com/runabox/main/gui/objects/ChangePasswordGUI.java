package com.runabox.main.gui.objects;

import com.runabox.io.CreateFolderStructure;
import com.runabox.main.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ChangePasswordGUI extends JPanel{
    private JPasswordField jcomp1;
    private JLabel jcomp2;
    private JButton jcomp3;

    private static JFrame frame;

    //standard vars
    private static IconHandler ico;

    public ChangePasswordGUI() {
        //init standard vars
        ico = new IconHandler();

        //construct components
        jcomp1 = new JPasswordField (5);
        jcomp2 = new JLabel ("New Password:");
        jcomp3 = new JButton ("Change Password");

        //adjust size and set layout
        setPreferredSize (new Dimension(349, 149));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);

        //action listeners
        jcomp3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File(CreateFolderStructure.root + "pass/password.p4j");

                try{
                    FileWriter writer = new FileWriter(file);

                    writer.write(new String(jcomp1.getPassword()));
                    writer.close();

                    System.out.println("Successfully changed password");

                    frame.dispose();
                } catch (IOException ee){
                    ee.printStackTrace();
                }
            }
        });

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (25, 40, 300, 25);
        jcomp2.setBounds (25, 15, 100, 25);
        jcomp3.setBounds (100, 80, 145, 25);

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
}
