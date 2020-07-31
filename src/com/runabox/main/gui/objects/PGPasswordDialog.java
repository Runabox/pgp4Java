package com.runabox.main.gui.objects;

import com.runabox.main.gui.GUI;
import com.runabox.pgp.PGPHandler;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class PGPasswordDialog extends JPanel {
    private JLabel jcomp1;
    private JPasswordField jcomp2;
    private JButton jcomp3;

    public static JFrame frame;
    private static IconHandler ico;

    public PGPasswordDialog() {
        File temp1 = new File(GUI.cfs.root + "pass/password.p4j");

        if(temp1.exists()){
            //get password from file in GUI class
            GUI temp = new GUI();
            temp.finishDecryptionProcess();

            return;
        }

        //init standard
        ico = new IconHandler();

        //construct components
        jcomp1 = new JLabel ("Enter Password:");
        jcomp2 = new JPasswordField(20);
        jcomp3 = new JButton ("Done");

        //adjust size and set layout
        setPreferredSize (new Dimension (374, 111));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);

        jcomp3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI guiTemp = new GUI();

                String password = new String(jcomp2.getPassword());
                System.out.println("Decrypting text (" + GUI.jcomp2.getText() + ") with password (" + password + ")");
                guiTemp.finishDecryptionProcess(password);

                File temp = new File( GUI.cfs.root + "pass/password.p4j");

                if(!temp.exists()){
                    SavePasswordGUI spG = new SavePasswordGUI(password);
                } else {
                    frame.dispose();
                }
            }
        });

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (10, 5, 100, 25);
        jcomp2.setBounds (10, 30, 350, 25);
        jcomp3.setBounds (90, 70, 195, 25);

        initGui();
    }

    public static void savePassword(String password) throws IOException {
        File file = new File(GUI.cfs.root + "pass/password.p4j");

        if(!file.exists()){
            //create file if doesn't exist
            try{
                if(file.createNewFile()){
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("Password file already exists! Rewriting file...");
                }
            } catch (IOException ee){
                System.out.println("An error occured. Stack Trace: " + ee.getMessage());
                return;
            }
        }

        FileWriter writer = new FileWriter(file);
        writer.write(password);
        writer.close();

        System.out.println("Wrote password to file (" + password + ")");
    }

    public void initGui() {
        frame = new JFrame ("pgp4Java v" + GUI.version);
        frame.setResizable(false);
        frame.getContentPane().add (this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        ico.setDefaultIcon(frame);
        frame.setVisible (true);
    }
}
