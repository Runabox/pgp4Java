package com.runabox.io;

import com.runabox.main.gui.GUI;

import java.io.*;
import java.util.Random;

public class LogHandler {

    public static File logFile;
    private Random random;

    public LogHandler(){
        random = new Random();
    }

    public void logCheck(){
        logFile = new File(GUI.cfs.root + "log/LOG " + random.nextInt(999999999) + ".log");

        if(!logFile.exists()){
            try{
                createLogFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void createLogFile() throws IOException {
        if(logFile.createNewFile()){
            System.out.println("Log file created (" + logFile.getPath() + ")");
        }
    }

    public void addLog(String log) {
        try{
            FileWriter writer = new FileWriter(logFile, true);

            writer.write(log + "\n");

            writer.close();
        } catch (IOException e){
            System.out.println("Failed to write to log to file! Stack Trace: " + e.getMessage());
        }

        System.out.println(log);
    }

}
