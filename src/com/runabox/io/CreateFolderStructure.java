package com.runabox.io;

import java.io.File;

public class CreateFolderStructure {

    public static final String appdata = System.getProperty("user.home") + "\\Appdata\\Roaming";
    public static final String root = appdata + "/.pgp4Java/";

    public void checkFolderStructure(){
        File rootFolder = new File(appdata + "/.pgp4Java");
        File keyFolder = new File(appdata + "/.pgp4Java/key");
        File iconFolder = new File(appdata + "/.pgp4Java/icon");
        File passFolder = new File(appdata + "/.pgp4Java/pass");
        File logFolder = new File(appdata + "/.pgp4Java/log");

        if(!rootFolder.exists()){
            createFolderStructure();
        }

        if(!keyFolder.exists()){
            createFolderStructure();
        }

        if(!iconFolder.exists()){
            createFolderStructure();
        }

        if(!passFolder.exists()){
            createFolderStructure();
        }

        if(!logFolder.exists()){
            createFolderStructure();
        }
    }

    public void createFolderStructure(){
        File rootFolder = new File(appdata + "/.pgp4Java");

        if(rootFolder.mkdir()){
            System.out.println("Root folder successfully created");
        } else {
            System.out.println("Root folder failed to create! Path: (" + rootFolder.getPath() + ")");
        }

        File keyFolder = new File(appdata + "/.pgp4Java/key");
        File iconFolder = new File(appdata + "/.pgp4Java/icon");
        File passFolder = new File(appdata + "/.pgp4Java/pass");
        File logFolder = new File(appdata + "/.pgp4Java/log");

        if(keyFolder.mkdir()){
            System.out.println("Key folder successfully created");
        } else {
            System.out.println("Key folder failed to create! Path: (" + keyFolder.getPath() + ")");
        }

        if(iconFolder.mkdir()){
            System.out.println("Icon folder successfully created");
        } else {
            System.out.println("Icon folder failed to create! Path: (" + iconFolder.getPath() + ")");
        }

        if(passFolder.mkdir()){
            System.out.println("Pass folder successfully created");
        } else {
            System.out.println("Pass folder failed to create! Path: (" + passFolder.getPath() + ")");
        }

        if(logFolder.mkdir()){
            System.out.println("Log folder successfully created!");
        } else {
            System.out.println("Log folder failed to create! Path: (" + logFolder.getPath() + ")");
        }

        try{
            File iconFile = new File(root + "icon/icon.png");

            if(!iconFile.exists()){
                FileDownloader fd = new FileDownloader();
                fd.download("https://runa.live/s/PcnH5lvZ61.png", iconFile.getPath(), false, false);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Successfully created folder structure!");
    }

}
