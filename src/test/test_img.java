/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sec_algo.img_proc;

/**
 *
 * @author Marienne Lopez
 */
public class test_img {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        JFrame imgenc = new JFrame("Image encryption module");
        img_proc img = new img_proc();
        int returnValue = fileChooser.showOpenDialog(null);
        JOptionPane.showMessageDialog(imgenc, "Please choose an image file to open.");
        fileChooser.show();
        if (returnValue == JFileChooser.APPROVE_OPTION){
            File f = fileChooser.getSelectedFile();
//            cryptmodule.setFile(selectedfile);
            img.setImage(f);
            System.out.println("Selected file for decryption: "+ f.getAbsoluteFile());
        }
        
        JOptionPane.showMessageDialog(imgenc, "Please enter filename to save file as");
        returnValue = fileChooser.showOpenDialog(null);
        fileChooser.show();
        if (returnValue == JFileChooser.APPROVE_OPTION){
            File f = fileChooser.getSelectedFile();
//            cryptmodule.setFile(selectedfile);
            img.createNewImage(f.getName());
            System.out.println("Saved file: "+ f.getAbsoluteFile());
        }
    }
}


