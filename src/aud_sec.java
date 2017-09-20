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
import java.util.ArrayList;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
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
    
    public File getFile(){
        return this.file;
    }
    
    public BufferedWriter getAudioStream(){
       FileInputStream fin = null;
       BufferedWriter audstream = null;
       
       try{
           fin = new FileInputStream(this.file);
//           audstream = new BufferedWriter(new FileWriter(returnFileName()+"_ex."+returnFileExt()));
//           byte contents[] = new byte[100];
//           while(fin.read(contents)!= -1){
//               System.out.println("reading & writing from file");
//               for(byte b : contents)
//                   for(int x = 0; x < 8; x++)
//                       audstream.write(b>>x & 1);
//           }
//           System.out.println("Finished!");
//           System.out.println("audstream contents: " + audstream.toString());
           byte[] header = new byte[8];
           fin.read(header);
           fin.close();
//           System.out.println("header bytes: " + Arrays.toString(header));
           ArrayList<String> bitstring = new ArrayList<String>();
            for(int i=0; i<header.length; i++)
                bitstring.add(String.format("%8s", Integer.toBinaryString(header[i] & 0xFF)).replace(' ', '0'));
            System.out.print("bit input: [/");
            for (int i=0; i<bitstring.size(); i++){
                System.out.print(bitstring.get(i) + " ");
            }
            System.out.println("]/");
            
            System.out.println(bitstring.get(0) + " " + bitstring.get(1) + " " + bitstring.get(2));
            System.out.println("Bitrate index: " + bitstring.get(2).substring(0, 4));
            
            AudioInputStream in = AudioSystem.getAudioInputStream(this.file);
            AudioInputStream din = null;
            AudioFormat baseFormat = in.getFormat();
            AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                                                        baseFormat.getSampleRate(),
                                                        getBitrate(bitstring.get(2).substring(0, 4)),
                                                        baseFormat.getChannels(),
                                                        baseFormat.getChannels() * 2,
                                                        baseFormat.getSampleRate(),
                                                        false);
            din = AudioSystem.getAudioInputStream(decodedFormat, in);
            int size = din.available();
            byte[] bytaud = new byte[size];
            din.read(bytaud);
            bitstring = new ArrayList<String>();
            for(int i=0; i<header.length; i++)
                bitstring.add(String.format("%8s", Integer.toBinaryString(header[i] & 0xFF)).replace(' ', '0'));
            System.out.print("bit input: [/");
            for (int i=0; i<bitstring.size(); i++){
                System.out.print(bitstring.get(i) + " ");
            }
            System.out.println("]/");
            in.close();
            din.close();
       } catch(Exception e){
           e.printStackTrace();
       }
       return audstream;
    }
    
    private int getBitrate(String input){
        int bitrate = 0;
        switch(input){
            case "0001": bitrate = 32; break;
            case "0010": bitrate = 48; break;
            case "0011": bitrate = 56; break;
            case "0100": bitrate = 64; break;
            case "0101": bitrate = 80; break;
            case "0110": bitrate = 96; break;    
            case "0111": bitrate = 112; break;
            case "1000": bitrate = 128; break;
            case "1001": bitrate = 144; break;
            case "1010": bitrate = 160; break;
            case "1011": bitrate = 176; break;
            case "1100": bitrate = 192; break;
            case "1101": bitrate = 224; break;
            case "1110": bitrate = 256; break;
        }
        return bitrate;
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
