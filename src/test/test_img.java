/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.File;
import javax.swing.JFileChooser;

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
        int returnValue = fileChooser.showOpenDialog(null);
        
        fileChooser.show();
        if (returnValue == JFileChooser.APPROVE_OPTION){
            File f = fileChooser.getSelectedFile();
//            cryptmodule.setFile(selectedfile);
            
            System.out.println("Selected file for decryption: "+ f.getName());
        }
    }
}


