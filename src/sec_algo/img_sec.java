/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sec_algo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

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
    
    public void encryptFile(){
        File encrypted = new File(returnFileName()+"_encrypted."+returnFileExt());
        int totalBytesRead = 0;
        byte[] result = new byte[(int)file.length()];
        byte[] temp = new byte[(int)file.length()];
        try{
            FileInputStream inputStream = new FileInputStream(file);
            BufferedOutputStream outputStream = 
                new BufferedOutputStream(new FileOutputStream(returnFileName()+"_encrypted."+returnFileExt()));
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretkey);
            while(totalBytesRead < result.length){
                int bytesRemaining = result.length - totalBytesRead;
                int bytesRead = inputStream.read(temp, totalBytesRead, bytesRemaining);
                if (bytesRead > 0)
                    totalBytesRead = totalBytesRead + bytesRead;
            }
            byte[] encoded = Base64.encodeBase64(temp);
            long startTime = System.currentTimeMillis();
            //encrypt
            result = cipher.doFinal(encoded);
            long endTime = System.currentTimeMillis();
            encryptTime = endTime - startTime;
            //write
            outputStream.write(result);
            inputStream.close();
            outputStream.close();
//            System.out.println("Read " + total + " bytes");
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void decryptFile(){
        File decrypted = new File(returnFileName()+"_decrypted."+returnFileExt());
        int totalBytesRead = 0;
        byte[] result = new byte[(int)file.length()];
        byte[] temp = new byte[(int)file.length()];
        try{
            FileInputStream inputStream = new FileInputStream(file);
            BufferedOutputStream outputStream = 
                new BufferedOutputStream(new FileOutputStream(returnFileName()+"_encrypted."+returnFileExt()));
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretkey);
            while(totalBytesRead < result.length){
                int bytesRemaining = result.length - totalBytesRead;
                int bytesRead = inputStream.read(temp, totalBytesRead, bytesRemaining);
                if (bytesRead > 0)
                    totalBytesRead = totalBytesRead + bytesRead;
            }
            byte[] encoded = Base64.encodeBase64(temp);
            long startTime = System.currentTimeMillis();
            //encrypt
//            result = new Base64().decode(cipher.doFinal(temp));
            result = cipher.doFinal(encoded);
            long endTime = System.currentTimeMillis();
            decryptTime = endTime - startTime;
            //write
            outputStream.write(result);
            inputStream.close();
            outputStream.close();
//            System.out.println("Read " + total + " bytes");
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
            String temp = s1 + myKey + s2;
            key = temp.getBytes("UTF-8");
            System.out.println("old key length: " + key.length);
            sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
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

}
