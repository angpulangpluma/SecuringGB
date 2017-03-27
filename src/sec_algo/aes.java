/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sec_algo;

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
 * @source aesencryption.net - Java AES encryption example
 */
public class aes {
    private SecretKeySpec secretkey;
    private byte[] key;
    
    public void setKey(String myKey, String s1, String s2){
        MessageDigest sha = null;
        try{
//            key = myKey.getBytes("UTF-8");
//            String temp = s1 + myKey + s2;
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
    
    public String getDecryptedString(String str){
        String decrypted = "";
        try{
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
//            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//            IvParameterSpec ivspec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretkey);
            decrypted = new String(cipher.doFinal(Base64.decodeBase64(str)));
        }catch(Exception e){
            e.printStackTrace();
        }
        return decrypted;
    }
    
    public String getEncryptedString(String str){
        String encrypted = "";
        try{
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
//            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//            IvParameterSpec ivspec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretkey);
            encrypted = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        }catch(Exception e){
            e.printStackTrace();
        }
        return encrypted;
    }
    
    public String getHashedString(String word){
        String result = "";
        byte[] hashed;
        MessageDigest digest;
        try{
            digest = MessageDigest.getInstance("SHA-256");
            hashed = digest.digest(word.getBytes("UTF-8"));    
            result = new String(hashed, "UTF-8");
        } catch(Exception e){
            e.printStackTrace();
            result = "";
        }
        return result;
    }
        
    /* 
    //use SHA-256 with salt start and end, check whether adding salt per
    //letter works for both web and mobile based on how fast it goes
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
    */
}
