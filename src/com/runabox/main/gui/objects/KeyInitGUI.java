package com.runabox.main.gui.objects;

import com.runabox.io.CreateFolderStructure;
import com.runabox.main.gui.GUI;
import com.runabox.main.gui.objects.IconHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class KeyInitGUI extends JPanel {
    private JTextArea jcomp1;
    private JLabel jcomp2;
    private JButton jcomp3;
    private JButton jcomp4;
    private JLabel jcomp5;
    private JButton jcomp6;

    //standard vars
    IconHandler ico;

    public KeyInitGUI() {
        //init standard vars
        ico = new IconHandler();

        //construct components
        jcomp1 = new JTextArea (5, 5);
        jcomp2 = new JLabel ("Enter Public / Private Key Here");
        jcomp3 = new JButton ("Set Public Key");
        jcomp4 = new JButton ("Set Private Key");
        jcomp5 = new JLabel ("Runabox, 2020");
        jcomp6 = new JButton("Change Stored Password");

        //adjust size and set layout
        setPreferredSize (new Dimension (944, 574));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);

        //check if password file exists, if not don't show change password button (jcomp6)
        File tempFile = new File(CreateFolderStructure.root + "pass/password.p4j");
        if(tempFile.exists()){
            add(jcomp6);
        }

        //public key listener
        jcomp3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File(GUI.cfs.root + "key/pub.asc");

                if(!file.exists()){
                    //create file if doesn't exist
                    try{
                        if(file.createNewFile()){
                            System.out.println("File created: " + file.getName());
                        } else {
                            System.out.println("Public key already exists... rewriting file");
                        }
                    } catch (IOException ee){
                        System.out.println("An error occured. Stack Trace: " + ee.getMessage());
                        return;
                    }
                }

                //check if file can be written to
                if(!file.canWrite()){
                    System.out.println("Error: Cannot write to file");
                    return;
                }

                //write to file
                FileWriter writer = null;
                try{
                    writer = new FileWriter(file);
                    writer.write(jcomp1.getText());
                    writer.close();
                    System.out.println("Succesfully wrote public key to file!");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println("An error occured writing public key to file.");
                    jcomp1.setText("An error occured while saving the public key.");
                }

                jcomp1.setText("Successfully saved public key!");

            }
        });

        //secret key listener
        jcomp4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File(GUI.cfs.root + "key/sec.asc");

                if(!file.exists()){
                    //create file if doesn't exist
                    try{
                        if(file.createNewFile()){
                            System.out.println("File created: " + file.getName());
                        } else {
                            System.out.println("Secret key already exists... rewriting file");
                        }
                    } catch (IOException ee){
                        System.out.println("An error occured. Stack Trace: " + ee.getMessage());
                        return;
                    }
                }

                //check if file can be written to
                if(!file.canWrite()){
                    System.out.println("Error: Cannot write to file");
                    return;
                }

                //write to file
                FileWriter writer = null;
                try{
                    writer = new FileWriter(file);
                    writer.write(jcomp1.getText());
                    writer.close();
                    System.out.println("Succesfully wrote secret key to file!");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println("An error occured writing secret key to file.");
                    jcomp1.setText("An error occured while saving the secret key.");
                }

                jcomp1.setText("Successfully saved secret key!");
            }
        });

        jcomp6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //create changepasswordgui window
                ChangePasswordGUI cpg = new ChangePasswordGUI();
            }
        });

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (20, 20, 900, 200);
        jcomp2.setBounds (20, 0, 180, 25);
        jcomp3.setBounds (20, 270, 245, 50);
        jcomp4.setBounds (675, 270, 245, 50);
        jcomp5.setBounds (860, 555, 100, 25);
        jcomp6.setBounds(345, 270, 245, 50);

        initGUI();
    }


    public void initGUI () {
        JFrame frame = new JFrame ("pgp4Java v" + GUI.version);
        frame.setResizable(false);
        frame.getContentPane().add (this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        ico.setDefaultIcon(frame);
        frame.setVisible (true);
    }
}
