/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sec_algo.img_proc;

/**
 *
 * @author Marienne Lopez
 */
public class test_imgsplit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        JFrame imgenc = new JFrame("Image encryption module");
        img_proc img = new img_proc();
        File f = null;
        int returnValue = fileChooser.showOpenDialog(null);
        JOptionPane.showMessageDialog(imgenc, "Please choose an image file to open.");
        fileChooser.show();
        if (returnValue == JFileChooser.APPROVE_OPTION){
            f = fileChooser.getSelectedFile();
//            cryptmodule.setFile(selectedfile);
            img.setImage(f);
            System.out.println("Selected file "+ f.getAbsoluteFile());
        }
        
        System.out.println("Img height: " + img.getImageHeight());
        System.out.println("Img width: " + img.getImageWidth());
        
        BufferedImage img1, img2 = null;
        if(img.getImageHeight()>img.getImageWidth()){
            System.out.println("Height is larger than width");
            img1 = img.getImage().getSubimage(0, 0, img.getImageWidth(), img.getImageHeight()/2);
            img2 = img.getImage().getSubimage(0, (img.getImageHeight()/2), img.getImageWidth(), img.getImageHeight()/2);
//            System.out.println("Seg1: " + returnFileName(f) + "_seg1." + returnFileExt(f));
//            System.out.println("Seg2: " + returnFileName(f) + "_seg2." + returnFileExt(f));
            try{
            File output = new File("segmentstest\\" + returnFileName(f) + "_seg1." + returnFileExt(f));
            ImageIO.write(img1, "jpg", output);
            output = new File("segmentstest\\"+returnFileName(f) + "_seg2." + returnFileExt(f));
            ImageIO.write(img2, "jpg", output);
            } catch(Exception e){
                e.printStackTrace();
            }
        } else if (img.getImageHeight()<img.getImageWidth()){
            System.out.println("Width is larger than height");
            img1 = img.getImage().getSubimage(0, 0, img.getImageWidth()/2, img.getImageHeight());
            img2 = img.getImage().getSubimage((img.getImageWidth()/2), 0, img.getImageWidth()/2, img.getImageHeight());
//            System.out.println("Seg1: " + returnFileName(f) + "_seg1." + returnFileExt(f));
//            System.out.println("Seg2: " + returnFileName(f) + "_seg2." + returnFileExt(f));
            try{
            File output = new File("segmentstest\\"+returnFileName(f) + "_seg1." + returnFileExt(f));
            ImageIO.write(img1, "jpg", output);
            output = new File("segmentstest\\"+returnFileName(f) + "_seg2." + returnFileExt(f));
            ImageIO.write(img2, "jpg", output);
            } catch(Exception e){
                e.printStackTrace();
            }
        } else {
            //encrypt as is
            System.out.println("Square img detected!");
        }
        
        
//        
//        System.out.println("Pixels transfer complete!");
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

}
