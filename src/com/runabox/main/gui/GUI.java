package com.runabox.main.gui;

import com.runabox.io.CreateFolderStructure;
import com.runabox.io.LogHandler;
import com.runabox.io.SettingsHandler;
import com.runabox.main.gui.objects.IconHandler;
import com.runabox.main.gui.objects.KeyInitGUI;
import com.runabox.main.gui.objects.PGPasswordDialog;
import com.runabox.pgp.PGPHandler;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class GUI extends JPanel {

    public static String version = "0.3.4";

    private JButton jcomp1;
    public static JTextArea jcomp2;
    private JLabel jcomp3;
    private JButton jcomp4;
    private JLabel jcomp5;
    private JButton jcomp6;
    private JScrollPane spane;

    //standard vars
    private static IconHandler ico;
    public static CreateFolderStructure cfs;

    public static final File pubFile = new File(cfs.root + "key/pub.asc");
    public static final File secFile = new File(cfs.root + "key/sec.asc");

    private static SettingsHandler sh;
    public static LogHandler lh;

    public void initGUI() {
        //check if folder structure is created
        cfs = new CreateFolderStructure();
        cfs.checkFolderStructure();

        //init log and add init log
        lh = new LogHandler();
        lh.logCheck();
        lh.addLog("Log init");

        lh.addLog("Folder structure check complete");

        //init settings and print them to console
        sh = new SettingsHandler();
        System.out.println(sh.readSettings());

        lh.addLog("Settings init");

        //init standard vars
        ico = new IconHandler();

        //construct components
        jcomp1 = new JButton ("Setup Key");
        jcomp2 = new JTextArea (5, 5);
        spane = new JScrollPane(jcomp2);
        jcomp3 = new JLabel ("Message to decrypt / encrypt");
        jcomp4 = new JButton ("Encrypt");
        jcomp5 = new JLabel ("Runabox, 2020");
        jcomp6 = new JButton ("Decrypt");

        //adjust size and set layout
        setPreferredSize (new Dimension (954, 587));
        setLayout (null);

        //action listeners
        jcomp1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //creates keyinitgui
                KeyInitGUI kig = new KeyInitGUI();
            }
        });

        jcomp4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!GUI.pubFile.exists()){
                    jcomp2.setText("Public key file does not exist. Set your public key by clicking the Setup Key button!");
                    return;
                }

                PGPHandler pgpH = new PGPHandler();

                String encrypted = pgpH.encrypt(new File(cfs.root + "key/pub.asc"), jcomp2.getText());
                jcomp2.setText(encrypted);
            }
        });

        jcomp6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!GUI.secFile.exists()){
                    jcomp2.setText("Secret key file does not exist. Set your secret key by clicking the Setup Key button!");
                    return;
                }

                //open password dialog and have "Done" button run final methods
                PGPasswordDialog temp = new PGPasswordDialog();
            }
        });

        //add components
        add (jcomp1);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (jcomp6);
        add (spane);


        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (0, 545, 260, 45);
        spane.setBounds (35, 40, 875, 210);
        jcomp3.setBounds (35, 15, 370, 25);
        jcomp4.setBounds (70, 265, 225, 45);
        jcomp5.setBounds (870, 565, 170, 25);
        jcomp6.setBounds (635, 270, 225, 45);

        lh.addLog("Panel initialized");

        initFrame();
    }

    public void finishDecryptionProcess(String password){
        PGPHandler pgpH = new PGPHandler();

        //try catch for error details
        String decrypted = null;

        try{
            decrypted = pgpH.decrypt(new File(cfs.root + "key/sec.asc"), jcomp2.getText(), password);
        } catch (Exception e){
            e.printStackTrace();
            jcomp2.setText("Fatal error! Stack Trace: " + e.getMessage());
        }

        jcomp2.setText(decrypted);
    }

    public void finishDecryptionProcess(){
        PGPHandler pgpH = new PGPHandler();
        String decrypted = pgpH.decryptFromPassFile(jcomp2.getText(), new File(cfs.root + "key/sec.asc"), new File(cfs.root + "pass/password.p4j"));

        jcomp2.setText(decrypted);
    }

    public void initFrame(){
        JFrame frame = new JFrame ("pgp4Java v" + version);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().add (this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        ico.setDefaultIcon(frame);
        frame.setVisible (true);

        lh.addLog("Frame initialized");
    }
}
