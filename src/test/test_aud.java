/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.BufferedWriter;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
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
            System.out.println("Selected file by aud: " + aud.getFile().getAbsoluteFile());
            BufferedWriter result = null;
            result = aud.getAudioStream();
//            try{
//            AudioInputStream in = AudioSystem.getAudioInputStream(f);
//            AudioInputStream din = null;
//            AudioFormat baseFormat = in.getFormat();
//            AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
//                                                        baseFormat.getSampleRate(),
//                                                        16,
//                                                        baseFormat.getChannels(),
//                                                        baseFormat.getChannels() * 2,
//                                                        baseFormat.getSampleRate(),
//                                                        false);
//            din = AudioSystem.getAudioInputStream(decodedFormat, in);
//            int size = din.available();
//            
//            } catch (Exception e){
//                e.printStackTrace();
//            }
        }
        
        //other stuff
        

    }
}


