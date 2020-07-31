package com.runabox.main;

import com.runabox.main.gui.GUI;

public class Main {

    private static GUI gui;

    public static void main(String[] args){
        gui = new GUI();

        //Have init function to prevent GUI from being opened more than once when initialized in other classes.
        gui.initGUI();
    }

}
