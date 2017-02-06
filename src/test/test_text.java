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

import java.util.Scanner;
import sec_algo.*;
public class test_text {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter text to encrypt: ");
        String toEncrypt = reader.next();
        System.out.println("Enter encryption key: ");
        String key = reader.next();
        System.out.println("Enter first salt: ");
        String s1 = reader.next();
        System.out.println("Enter second salt: ");
        String s2 = reader.next();
        
        aes endemodule = new aes();
        endemodule.setKey(key, s1, s2);
        
        long startTime = System.currentTimeMillis();
        String enOutput = endemodule.getEncryptedString(toEncrypt);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("String to encrypt: " + toEncrypt);
        System.out.println("Encrypted string: " + enOutput);
        System.out.println("Encryption time: " + duration);
        
        startTime = System.currentTimeMillis();
        String deOutput = endemodule.getDecryptedString(enOutput);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("String to decrypt: " + enOutput);
        System.out.println("Decrypted: " + deOutput);
        System.out.println("Decryption time: " + duration);
    }

}
