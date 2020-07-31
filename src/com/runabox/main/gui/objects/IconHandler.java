package com.runabox.main.gui.objects;

import com.runabox.main.gui.GUI;

import javax.swing.*;
import java.io.File;

public class IconHandler {

    public static final String dIconPath = GUI.cfs.root + "icon/icon.png";

    public void setDefaultIcon(JFrame frame){
        File file = new File(dIconPath);

        if(!file.exists()){
            System.out.println("Error setting Icon: File does not exist!");
            return;
        }

        ImageIcon img = new ImageIcon(file.getPath());
        try{
            frame.setIconImage(img.getImage());
        } catch (Exception e){
            System.out.println("Error setting Icon: " + e.getMessage());
        }

        System.out.println("Icon successfully set!");
    }

    public void setCustomIcon(JFrame frame, String path){
        File file = new File(path);

        if(!file.exists()){
            System.out.println("Error setting Icon: File does not exist.");
            return;
        }

        ImageIcon img = new ImageIcon(file.getPath());
        try{
            frame.setIconImage(img.getImage());
        } catch (Exception e){
            System.out.println("Error setting Icon: " + e.getMessage());
        }

        System.out.println("Successfully set custom Icon! Path: " + file.getPath());
    }

}
