/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sec_algo.img_proc;

/**
 *
 * @author Marienne Lopez
 */
public class test_firstimgenc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        long startTime, endTime, encryptTime;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        JFrame imgenc = new JFrame("Image encryption module");
        img_proc img = new img_proc();
        File f = null;
        BufferedImage result;
        
        int returnValue = fileChooser.showOpenDialog(null);
        JOptionPane.showMessageDialog(imgenc, "Please choose an image file to open.");
        fileChooser.show();
        if (returnValue == JFileChooser.APPROVE_OPTION){
            f = fileChooser.getSelectedFile();
//            cryptmodule.setFile(selectedfile);
            img.setImage(f);
            System.out.println("Selected file "+ f.getAbsoluteFile());
        }
        
        try{
        FileWriter fw = new FileWriter("test_imgenc.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("---------");
        bw.newLine();
//        bw.write("encrypting file through first algorithm");  
//        bw.newLine();
//        bw.write("file to encrypt: " + f.getAbsoluteFile());
//        bw.newLine();
//        startTime = System.currentTimeMillis();
//        result = img.firstPixScram();
//        endTime = System.currentTimeMillis();
//        encryptTime = endTime - startTime;
//        bw.write("encryption time: " + encryptTime);
//        bw.newLine();
//        System.out.println("Pixels transfer complete!");
//        createNewImage(returnFileName(f)+"_enc."+returnFileExt(f), result);
//        bw.write("file to decrypt: " + returnFileName(f)+"_enc."+returnFileExt(f));
//        bw.newLine();
//        startTime = System.currentTimeMillis();
//        result = img.firstPixUnscram();
//        endTime = System.currentTimeMillis();
//        encryptTime = endTime - startTime;
//        bw.write("decryption time: " + encryptTime);
//        bw.newLine();
//        System.out.println("Pixels transfer complete!");
//        createNewImage(returnFileName(f)+"_dec."+returnFileExt(f), result);
        
        bw.write("encrypting file through second algorithm");  
        bw.newLine();
        bw.write("file to encrypt: " + f.getAbsoluteFile());
        bw.newLine();
        startTime = System.currentTimeMillis();
        result = img.secondPixScram();
        endTime = System.currentTimeMillis();
        encryptTime = endTime - startTime;
        bw.write("encryption time: " + encryptTime);
        bw.newLine();
        System.out.println("Pixels transfer complete!");
        createNewImage(returnFileName(f)+"_enc."+returnFileExt(f), result);
        if (bw!=null)
            bw.close();
        if(fw!=null)
            fw.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        
//        System.out.println("Img height: " + img.getImageHeight());
//        System.out.println("Img width: " + img.getImageWidth());
        
//        BufferedImage img1, img2 = null;
//        if(img.getImageHeight()>img.getImageWidth()){
//            System.out.println("Height is larger than width");
//            img1 = img.getImage().getSubimage(0, 0, img.getImageWidth(), img.getImageHeight()/2);
//            img2 = img.getImage().getSubimage(0, (img.getImageHeight()/2), img.getImageWidth(), img.getImageHeight()/2);
////            System.out.println("Seg1: " + returnFileName(f) + "_seg1." + returnFileExt(f));
////            System.out.println("Seg2: " + returnFileName(f) + "_seg2." + returnFileExt(f));
//            try{
//            File output = new File("segmentstest\\" + returnFileName(f) + "_seg1." + returnFileExt(f));
//            ImageIO.write(img1, "jpg", output);
//            output = new File("segmentstest\\"+returnFileName(f) + "_seg2." + returnFileExt(f));
//            ImageIO.write(img2, "jpg", output);
//            } catch(Exception e){
//                e.printStackTrace();
//            }
//        } else if (img.getImageHeight()<img.getImageWidth()){
//            System.out.println("Width is larger than height");
//            img1 = img.getImage().getSubimage(0, 0, img.getImageWidth()/2, img.getImageHeight());
//            img2 = img.getImage().getSubimage((img.getImageWidth()/2), 0, img.getImageWidth()/2, img.getImageHeight());
////            System.out.println("Seg1: " + returnFileName(f) + "_seg1." + returnFileExt(f));
////            System.out.println("Seg2: " + returnFileName(f) + "_seg2." + returnFileExt(f));
//            try{
//            File output = new File("segmentstest\\"+returnFileName(f) + "_seg1." + returnFileExt(f));
//            ImageIO.write(img1, "jpg", output);
//            output = new File("segmentstest\\"+returnFileName(f) + "_seg2." + returnFileExt(f));
//            ImageIO.write(img2, "jpg", output);
//            } catch(Exception e){
//                e.printStackTrace();
//            }
//        } else {
            //encrypt as is
//            System.out.println("Square img detected!");
//        }
        
        
//        
//        JOptionPane.showMessageDialog(imgenc, "Please enter filename to save file as");
//        returnValue = fileChooser.showOpenDialog(null);
//        fileChooser.show();
//        if (returnValue == JFileChooser.APPROVE_OPTION){
//            File f = fileChooser.getSelectedFile();
////            cryptmodule.setFile(selectedfile);
//            img.createNewImage(f.getName());
////            System.out.println("Saved file: "+ f.getAbsoluteFile());
//        }
        
    }
    
    private static String returnFileExt(File file){
        String ext = "";
        int i = file.getName().lastIndexOf('.');
        if (i >= 0)
            ext = file.getName().substring(i+1);
        return ext;
    }
    
    private static String returnFileName(File file){
        String filename = "";
        int i = file.getName().lastIndexOf('.');
        if (i >= 0)
            filename = file.getName().substring(0, i);
        return filename;
    }
    
    private static void createNewImage(String fileloc, BufferedImage image){
        try{
            File output = new File(fileloc);
            ImageIO.write(image, "jpg", output);
            System.out.println("Writing image successful!");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
