package com.runabox.pgp;

import com.didisoft.pgp.PGPException;
import com.didisoft.pgp.PGPLib;
import com.runabox.io.LogHandler;

import java.io.*;

public class PGPHandler {

    private static PGPLib pgp;

    private static LogHandler lh;

    public PGPHandler(){
        lh = new LogHandler();

        pgp = new PGPLib();
        lh.addLog("PGPLib init");
    }

    public PGPLib getPGP(){
        return pgp;
    }

    public String encrypt(File key, String message){
        InputStream pks = null;

        String encrypted;
        try{
            pks = new FileInputStream(key.getPath());

            encrypted = pgp.encryptString(message, pks);
        } catch (PGPException | IOException e) {
            e.printStackTrace();
            return "Error while encrypting message! Stack Trace: " + e.getMessage();
        }

        lh.addLog("Encrypted String: " + message);
        return encrypted;
    }

    public String decrypt(File key, String message, String password){
        InputStream sks = null;

        String decrypted = null;
        try{
            sks = new FileInputStream(key.getPath());

            decrypted = pgp.decryptString(message, sks, password);
        } catch (IOException | PGPException e){
            e.printStackTrace();
        }

        lh.addLog("Decrypted PGP message: \n" + message + "\n with key at path: " + key.getPath() + "! \n" + "Decrypted message: \n" + decrypted);
        return decrypted;
    }

    public String decryptFromPassFile(String message, File key, File pass){
        //get password to string (password11 to make it unique)
        String password11 = null;
        InputStream sks = null;
        String decrypted = null;

        try{
            FileInputStream fis = new FileInputStream(pass);
            byte[] buffer = new byte[1];
            StringBuilder sb = new StringBuilder();

            while(fis.read(buffer) != -1){
                sb.append(new String(buffer));
                buffer = new byte[1];
            }
            fis.close();

            password11 = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //decrypt string
        try{
            sks = new FileInputStream(key);

            decrypted = pgp.decryptString(message, sks, password11);
        } catch (PGPException | IOException e) {
            e.printStackTrace();
            return "Error decrypting string! Maybe try changing your password in the Setup Key menu?";
        }

        lh.addLog("Decrypted PGP message: \n" + message + "\n with key at path: " + key.getPath() + "! \n" + "Decrypted message: \n" + decrypted);
        return decrypted;
    }
}