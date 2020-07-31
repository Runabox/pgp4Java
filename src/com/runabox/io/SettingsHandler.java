package com.runabox.io;

import com.runabox.main.gui.GUI;

import java.io.*;

public class SettingsHandler {

    public static final File settings = new File(GUI.cfs.root + "settings.ini");

    public SettingsHandler(){
        if(!settings.exists()){
            try {
                createSettingsFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createSettingsFile() throws IOException {
        if(settings.createNewFile()){
            System.out.println("Settings file created (" + settings.getPath() + ")");
        }
    }

    public String readSettings(){
        String settingsString = null;

        try{
            FileInputStream fis = new FileInputStream(settings);
            byte[] buffer = new byte[1];
            StringBuilder sb = new StringBuilder();

            while(fis.read(buffer) != -1){
                sb.append(new String(buffer));
                buffer = new byte[1];
            }
            fis.close();

            settingsString = sb.toString();

            return settingsString;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Error";
    }

    public void writeLineToSettings(String line) throws IOException {
        FileOutputStream fos = new FileOutputStream(settings);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        //write line
        bw.write(line);
        bw.newLine();

        bw.close();
    }

}
