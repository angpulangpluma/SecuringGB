package test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marienne Lopez
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import sec_algo.commonenc;

public class test_commonenc_text2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FileWriter fw = null;
        BufferedWriter bw = null;
        
        try{
            fw = new FileWriter("test_log.txt",true);
            bw = new BufferedWriter(fw);
            bw.write("---------");
            bw.newLine();
            bw.write("encrypting text");  
            bw.newLine();
        } catch(Exception e){
            e.printStackTrace();
        }
        
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter text to encrypt: ");
        String toEncrypt = reader.nextLine();
//        System.out.println("Enter encryption key: ");
//        String key = reader.nextLine();
//        System.out.println("Enter first salt: ");
//        String s1 = reader.nextLine();
//        System.out.println("Enter second salt: ");
//        String s2 = reader.nextLine();
        
//        aes endemodule = new aes();
//        endemodule.setKey(key, s1, s2);
        commonenc enc = new commonenc();
        enc.makeKey();
        
        long startTime = System.currentTimeMillis();
        String enOutput = enc.getEncryptedString(toEncrypt);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("String to encrypt: " + toEncrypt);
        System.out.println("Encrypted string: " + enOutput);
        System.out.println("Encryption time: " + duration);
        
        try{
            if(bw!=null){
                bw.newLine();
                bw.newLine();
                bw.write("Result for encrypting text:");
                bw.newLine();
                bw.write("String to encrypt: " + toEncrypt );
                bw.newLine();
                bw.write("Encrypted string: " + enOutput );
                bw.newLine();
                bw.write("Encryption time: " + duration );
                bw.newLine();

//                bw.newLine();
//                bw.write("String to decrypt: " + enOutput );
//                bw.newLine();
//                bw.write("Decrypted: " + deOutput );
//                bw.newLine();
//                bw.write("Decryption time: " + duration );
//                bw.newLine();
//                
//                bw.newLine();
//                bw.write("hashing text");
//                bw.newLine();
//                bw.write("String to encrypt: " + toEncrypt );
//                bw.newLine();
//                bw.write("Encrypted string: " + enOutput );
//                bw.newLine();
//                bw.write("Encryption time: " + duration );
//                bw.newLine();
//                bw.write("---------");
//                bw.newLine();
//                bw.newLine();
//                bw.close();
            }
            
//            if(fw!=null)
//                fw.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        
        startTime = System.currentTimeMillis();
        String deOutput = enc.getDecryptedString(enOutput);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("String to decrypt: " + enOutput);
        System.out.println("Decrypted: " + deOutput);
        System.out.println("Decryption time: " + duration);
        
        try{
            if(bw!=null){
//                bw.newLine();
//                bw.newLine();
//                bw.write("Result for encrypting text:");
//                bw.newLine();
//                bw.write("String to encrypt: " + toEncrypt );
//                bw.newLine();
//                bw.write("Encrypted string: " + enOutput );
//                bw.newLine();
//                bw.write("Encryption time: " + duration );
//                bw.newLine();

                bw.newLine();
                bw.write("String to decrypt: " + enOutput );
                bw.newLine();
                bw.write("Decrypted: " + deOutput );
                bw.newLine();
                bw.write("Decryption time: " + duration );
                bw.newLine();
                
//                bw.newLine();
//                bw.write("hashing text");
//                bw.newLine();
//                bw.write("String to encrypt: " + toEncrypt );
//                bw.newLine();
//                bw.write("Encrypted string: " + enOutput );
//                bw.newLine();
//                bw.write("Encryption time: " + duration );
//                bw.newLine();
//                bw.write("---------");
//                bw.newLine();
//                bw.newLine();
//                bw.close();
            }
            
//            if(fw!=null)
//                fw.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        
        startTime = System.currentTimeMillis();
        enOutput = enc.getHashedString(toEncrypt);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("String to encrypt: " + toEncrypt);
        System.out.println("Encrypted string: " + enOutput);
        System.out.println("Encryption time: " + duration);
        
        try{
            if(bw!=null){
//                bw.newLine();
//                bw.newLine();
//                bw.write("Result for encrypting text:");
//                bw.newLine();
//                bw.write("String to encrypt: " + toEncrypt );
//                bw.newLine();
//                bw.write("Encrypted string: " + enOutput );
//                bw.newLine();
//                bw.write("Encryption time: " + duration );
//                bw.newLine();
//
//                bw.newLine();
//                bw.write("String to decrypt: " + enOutput );
//                bw.newLine();
//                bw.write("Decrypted: " + deOutput );
//                bw.newLine();
//                bw.write("Decryption time: " + duration );
//                bw.newLine();
                
                bw.newLine();
                bw.write("hashing text");
                bw.newLine();
                bw.write("String to encrypt: " + toEncrypt );
                bw.newLine();
                bw.write("Encrypted string: " + enOutput );
                bw.newLine();
                bw.write("Encryption time: " + duration );
                bw.newLine();
                bw.write("---------");
                bw.newLine();
                bw.newLine();
                bw.close();
            }
            
            if(fw!=null)
                fw.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
