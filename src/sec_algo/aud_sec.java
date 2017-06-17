/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sec_algo;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Marienne Lopez
 */
public class aud_sec {
    
    private File file;
//    private byte[] key;
//    private byte[] iv;
//    private SecretKeySpec secretkey;
    private long encryptTime;
    private long decryptTime;
    
    public aud_sec(){
        file = null;
        encryptTime = 0;
        decryptTime = 0;
    }
    
    public void setFile(File file){
        this.file = file;
    }
    
    public void headerChecker(){
       FileInputStream fin = null;
       BufferedWriter audstream = null;
       try{
           fin = new FileInputStream(this.file);
           audstream = new BufferedWriter(new FileWriter(returnFileName()+"_ex."+returnFileExt()));
           byte contents[] = new byte[4];
           fin.read(contents);
//           contents[0]
//           contents[1]
//           contents[2]
//           contents[3]           
       } catch(Exception e){
           e.printStackTrace();
       }
    }
    
    private BufferedWriter getAudioStream(){
       FileInputStream fin = null;
       BufferedWriter audstream = null;
       try{
           fin = new FileInputStream(this.file);
           audstream = new BufferedWriter(new FileWriter(returnFileName()+"_ex."+returnFileExt()));
           byte contents[] = new byte[100];
           while(fin.read(contents)!= -1){
               for(byte b : contents)
                   for(int x = 0; x < 8; x++)
                       audstream.write(b>>x & 1);
           }
       } catch(Exception e){
           e.printStackTrace();
       }
       return audstream;
    }
    
    
    
//    public void encryptFile(){
//        File encrypted = new File(returnFileName()+"_encrypted."+returnFileExt());
//        byte[] temp, result;
//        try{
//            FileInputStream in = new FileInputStream(file);
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//            cipher.init(Cipher.ENCRYPT_MODE, secretkey);
//            iv = cipher.getIV();
//            CipherOutputStream os = new CipherOutputStream(new FileOutputStream(encrypted),
//                cipher);
//
//            long startTime = System.currentTimeMillis();
//            copy(in, os);
//            long endTime = System.currentTimeMillis();
//            encryptTime = endTime - startTime;
//
//            in.close();
//            os.close();
//        } catch(Exception ex){
//            ex.printStackTrace();
//        }
//    }
//    
//    public void decryptFile(){
//        File decrypted = new File(returnFileName()+"_decrypted."+returnFileExt());
//        try{
//            FileOutputStream os = new FileOutputStream(decrypted);
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//            AlgorithmParameters.getInstance("AES");
//            if (iv!=null)
//                cipher.init(Cipher.DECRYPT_MODE, secretkey, new IvParameterSpec(iv));
//            else cipher.init(Cipher.DECRYPT_MODE, secretkey);
//            CipherInputStream is = new CipherInputStream(new FileInputStream(file),
//                cipher);
//
//            long startTime = System.currentTimeMillis();
//            copy(is, os);
//            long endTime = System.currentTimeMillis();
//            decryptTime = endTime - startTime;
//            
//            is.close();
//            os.close();
//        } catch(Exception ex){
//            ex.printStackTrace();
//        }
//        
//    }
    
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
    
//    public void setKey(String myKey, String s1, String s2){
//        MessageDigest sha = null;
//        try{
////            key = myKey.getBytes("UTF-8");
//            String temp = s1 + myKey + s2;
//            key = temp.getBytes("UTF-8");
//            System.out.println("old key length: " + key.length);
//            sha = MessageDigest.getInstance("SHA-256");
//            key = sha.digest(key);
//            key = Arrays.copyOf(key, 16);
//            System.out.println("new key length: " + key.length);
//            System.out.println("key: " + new String(key, "UTF-8"));
//            secretkey = new SecretKeySpec(key, "AES");
//        }catch(NoSuchAlgorithmException e){
//            e.printStackTrace();
//        }catch(UnsupportedEncodingException e){
//            e.printStackTrace();
//        }
//    }
    
//    private void copy(InputStream is, OutputStream os){
//        int i;
//        byte[] b = new byte[1024];
//        try{
//            while((i=is.read(b))!=-1) {
//                os.write(b, 0, i);
//            }
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }

}
