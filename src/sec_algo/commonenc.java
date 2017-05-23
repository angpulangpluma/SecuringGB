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
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Marienne Lopez
 * @source aesencryption.net - Java AES encryption example and 
 *      www.macs.hw.ac.uk/~ml355/lore/FileEncryption.java
 */
public class commonenc {
    
    //for setting keys
    private SecretKeySpec secretkey;
    private static final int AES_Key_Size = 256;
    private byte[] key;
    private Cipher pkCipher, aesCipher;
    
    //for file encryption
    private File file;
    private long encryptTime;
    private long decryptTime;
    
    /**
    * Constructor: creates ciphers
    */
    public commonenc () {
        try{
        // create RSA public key cipher
        pkCipher = Cipher.getInstance("RSA");
        // create AES shared key cipher
        aesCipher = Cipher.getInstance("AES");
        file = null;
        encryptTime = 0;
        decryptTime = 0;
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
    * Creates a new AES key
    */
    public void makeKey(){
        try{
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(AES_Key_Size);
        SecretKey aeskey = kgen.generateKey();
        key = aeskey.getEncoded();
        secretkey = new SecretKeySpec(key, "AES");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
    * Decrypts an AES key from a file using an RSA private key
    */
    public void loadKey(File in, File privateKeyFile){
        try {    
        // read private key to be used to decrypt the AES key
        byte[] encodedKey = new byte[(int)privateKeyFile.length()];
        new FileInputStream(privateKeyFile).read(encodedKey);

        // create private key
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey pk = kf.generatePrivate(privateKeySpec);

        // read AES key
        pkCipher.init(Cipher.DECRYPT_MODE, pk);
        key = new byte[AES_Key_Size/8];
        CipherInputStream is = new CipherInputStream(new FileInputStream(in), pkCipher);
        is.read(key);
        secretkey = new SecretKeySpec(key, "AES");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Encrypts the AES key to a file using an RSA public key
     */
    public void saveKey(File out, File publicKeyFile){
        try {
        // read public key to be used to encrypt the AES key
        byte[] encodedKey = new byte[(int)publicKeyFile.length()];
        new FileInputStream(publicKeyFile).read(encodedKey);

        // create public key
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey pk = kf.generatePublic(publicKeySpec);

        // write AES key
        pkCipher.init(Cipher.ENCRYPT_MODE, pk);
        CipherOutputStream os = new CipherOutputStream(new FileOutputStream(out), pkCipher);
        os.write(key);
        os.close();
        } catch(Exception e){
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
