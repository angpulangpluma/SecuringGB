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
import sec_algo.aud_sec;

/**
 *
 * @author Marienne Lopez
 */
public class test_aud {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        JFrame imgenc = new JFrame("Audio encryption module");
        aud_sec aud = new aud_sec();
        int returnValue = fileChooser.showOpenDialog(null);
        JOptionPane.showMessageDialog(imgenc, "Please choose an audio file to open.");
        fileChooser.show();
        if (returnValue == JFileChooser.APPROVE_OPTION){
            File f = fileChooser.getSelectedFile();
            aud.setFile(f);
            System.out.println("Selected file "+ f.getAbsoluteFile());
        }
        
        //other stuff
        

    }
}


