/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sec_algo;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Marienne Lopez
 */
public class img_sec {
    
    private File file;
    private byte[] key;
    private SecretKeySpec secretkey;
    private long encryptTime;
    private long decryptTime;
    
    public img_sec(){
        file = null;
        encryptTime = 0;
        decryptTime = 0;
    }
    
    public void setFile(File file){
        this.file = file;
    }
    
    /*
    Implementation for encryptFile() from www.macs.hw.ac.uk/~ml355/lore/FileEncryption.java
    */
    
    public void encryptFile(){
        File encrypted = new File("demo2\\" + returnFileName()+"_encrypted."+returnFileExt());
        byte[] temp, result;
        try{
            FileInputStream in = new FileInputStream(file);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretkey, ivspec);
            CipherOutputStream os = new CipherOutputStream(new FileOutputStream(encrypted),
                cipher);

            long startTime = System.currentTimeMillis();
            copy(in, os);
            long endTime = System.currentTimeMillis();
            System.out.println("startTime - " + startTime);
            System.out.println("endTime - " + endTime);
            encryptTime = endTime - startTime;

            in.close();
            os.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    /*
    Implementation for decryptFile() from www.macs.hw.ac.uk/~ml355/lore/FileEncryption.java
    */
    
    public void decryptFile(){
        File decrypted = new File("demo2de\\" + returnFileName()+"_decrypted."+returnFileExt());
        try{
            FileOutputStream os = new FileOutputStream(decrypted);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            AlgorithmParameters.getInstance("AES");
//            if (iv!=null)
             byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);
                cipher.init(Cipher.DECRYPT_MODE, secretkey, ivspec);
//            else cipher.init(Cipher.DECRYPT_MODE, secretkey);
            CipherInputStream is = new CipherInputStream(new FileInputStream(file),
                cipher);

            long startTime = System.currentTimeMillis();
            copy(is, os);
            long endTime = System.currentTimeMillis();
            System.out.println("startTime - " + startTime);
            System.out.println("endTime - " + endTime);
            decryptTime = endTime - startTime;
            
            is.close();
            os.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    private String returnFileExt(){
        String ext = "";
        int i = file.getName().lastIndexOf('.');
        if (i >= 0)
            ext = file.getName().substring(i+1);
        return ext;
    }
    
    private String returnFileName(){
        String filename = "";
        int i = file.getName().lastIndexOf('.');
        if (i >= 0)
            filename = file.getName().substring(0, i);
        return filename;
    }
    
    public long getEncryptedTime(){
        return this.encryptTime;
    }
    
    public long getDecryptedTime(){
        return this.decryptTime;
    }
    
    public void setKey(String myKey, String s1, String s2){
        MessageDigest sha = null;
        try{
//            key = myKey.getBytes("UTF-8");
           key = myKey.getBytes("UTF-8");
            System.out.println("old key length: " + key.length);
            sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
            String temp = s1 + new String(key, "UTF-8") + s2;
            key = sha.digest(temp.getBytes("UTF-8"));
            key = Arrays.copyOf(key, 16);
            System.out.println("new key length: " + key.length);
            System.out.println("key: " + new String(key, "UTF-8"));
            secretkey = new SecretKeySpec(key, "AES");
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }
    
    /*
    Implementation for copy() from www.macs.hw.ac.uk/~ml355/lore/FileEncryption.java
    */
    
    private void copy(InputStream is, OutputStream os){
        int i;
        byte[] b = new byte[1024];
        try{
            while((i=is.read(b))!=-1) {
                os.write(b, 0, i);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
