/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sec_algo;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.imageio.ImageIO;
import matrixpack.*;

/**
 *
 * @author Marienne Lopez
 */
public class img_proc {
    
    private int[] RGB_MASKS;
    private final ColorModel RGB_OPAQUE;
    private BufferedImage image;
    private File f;
    
    public img_proc(){
        this.image = null;
        RGB_MASKS = new int[]{0xFF0000, 0xFF00, 0xFF};
        RGB_OPAQUE = new DirectColorModel(32, RGB_MASKS[0],
            RGB_MASKS[1], RGB_MASKS[2]);
//        this.f = null;
    }
    
    public void setImage(File fileloc){
        try{
            this.f = fileloc;
            BufferedImage temp = ImageIO.read(fileloc);
            Image img = Toolkit.getDefaultToolkit().createImage(fileloc.getAbsoluteFile().toString());
            PixelGrabber pg = new PixelGrabber(img, 0, 0, -1, -1, true);
            pg.grabPixels();
            int width = pg.getWidth(), height = pg.getHeight();
            DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(), 
                pg.getWidth() * pg.getHeight());
            WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
//            image = new BufferedImage(temp.getWidth(), temp.getHeight(),
//                BufferedImage.TYPE_INT_ARGB);
            image = new BufferedImage(RGB_OPAQUE, raster, false, null);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void createNewImage(String fileloc){
        try{
            File output = new File(fileloc);
            ImageIO.write(image, "jpg", output);
            System.out.println("Writing image successful!");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public BufferedImage getImage(){
        return this.image;
    }
    
    public int getImageWidth(){
        return this.image.getWidth();
    }
    
    public int getImageHeight(){
        return this.image.getHeight();
    }
    
    public int getImagePixelVal(int x, int y){
        return this.image.getRGB(x,y);
    }
    
    public int[][] getImageInPixels(){
        int w = getImageWidth();
        int h = getImageHeight();
        int[][] result = new int[h][w];
        
        for(int row=0; row < h; row++){
            for (int col=0; col < w; col++){
                result[row][col] = image.getRGB(col,row);
            }
        }
        return result;
    }
    
    public int getAOfImagePixel(int x, int y){
        return (getImagePixelVal(x,y)>>24) & 0xff;
    }
    
    public int getROfImagePixel(int x, int y){
        return (getImagePixelVal(x,y)>>16) & 0xff;
    }
    
    public int getGOfImagePixel(int x, int y){
        return (getImagePixelVal(x,y)>>8) & 0xff;
    }
    
    public int getBOfImagePixel(int x, int y){
        return getImagePixelVal(x,y) & 0xff;
    }
    
    /*
    based on first encryption algorithm from "Research on Colour Image
        Efficiency" by Jing-Yu Peng
    */
    public BufferedImage firstPixScram(FileWriter fw){
//        BufferedWriter bw = new BufferedWriter(fw);
        BufferedImage imgscram = null;
        int[][] pix = getImageInPixels();
        try{
            Image temp = Toolkit.getDefaultToolkit().createImage(f.getAbsoluteFile().toString());
            PixelGrabber pg = new PixelGrabber(temp, 0, 0, -1, -1, true);
            pg.grabPixels();
            int width = pg.getWidth(), height = pg.getHeight();
            DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(), 
                pg.getWidth() * pg.getHeight());
            WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
            imgscram = new BufferedImage(RGB_OPAQUE, raster, false, null);
        } catch (Exception e){
            e.printStackTrace();
        }
            //setting basis matrix
            matrix a1 = new matrix();
            a1.setRow(2);
            a1.setCol(2);
            a1.defineMatrix();
            
            a1.setValue(0, 0, 1);
            a1.setValue(0, 1, 1);
            a1.setValue(1, 0, 1);
            a1.setValue(1, 1, 2);
            
            System.out.println("\n Basis matrix for first encryption algorithm:");
            a1.displayMatrix();
                
            //encrypt img here
            int w = getImageWidth();
            int h = getImageHeight();
            int[][] imgpix = getImageInPixels();
            matrix selpix = new matrix();
            selpix.setRow(2);
            selpix.setCol(1);
            selpix.defineMatrix();
            matrixop mop = new matrixop();
            int a, r, g, b;
            
//            try{
//                bw.write("First image encryption algorithm");
//                bw.newLine();
//                bw.write("a1:");
//                bw.newLine();
//                writeMatrix(bw, a1);
//                bw.newLine();
//            } catch (Exception e){
//                e.printStackTrace();
//            }
            
            for(int row=0; row<w; row++){
                for(int col=0; col<h; col++){
                    selpix.setValue(0, 0, col);
                    selpix.setValue(1, 0, row);
                    mop.setFirstMatrix(a1);
                    mop.setSecondMatrix(selpix);
//                    try{
//                        bw.write("step " + w);
//                        bw.write("before encryption:");
//                        bw.newLine();
//                        bw.write("a1:");
//                        bw.newLine();
//                        writeMatrix(bw, a1);
//                        bw.write("selected pixel");
//                        bw.newLine();
//                        writeMatrix(bw, selpix);
//                        bw.newLine();
//                    } catch (Exception e){
//                        e.printStackTrace();
//                    }
                    matrix swap = mop.multiplyMatrix(w);
                    swap.displayMatrix();
                    
//                    try{
////                        bw.write("step " + w);
//                    bw.write("after encryption:");
//                    bw.newLine();
//                    bw.write("a1:");
//                    bw.newLine();
//                    writeMatrix(bw, a1);
//                    bw.write("swap");
//                    bw.newLine();
//                    writeMatrix(bw, swap);
//                    bw.newLine();
//                    } catch (Exception e){
//                        e.printStackTrace();
//                }
                }
            }
            
//            try{
//            if (bw!=null)
//             bw.close();
//            if(fw!=null)
//                fw.close();
//            } catch (Exception e){
//                e.printStackTrace();
//            }
            
            return imgscram;
    }
    
    private void writeMatrix(BufferedWriter bw, matrix a){
        try{
            int r = a.getRow();
            int c = a.getCol();
            
            for(int row = 0; row<r; r++){
                bw.write("[");
                for(int col=0; col<c; c++){
                    bw.write(a.getValue(row, col) + " ");
                }
                bw.write("]");    
                bw.newLine();
            }
                
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /*
    based on first encryption algorithm from "Research on Colour Image
        Efficiency" by Jing-Yu Peng
    */
    public BufferedImage secondPixScram(){
        BufferedImage imgscram = null;
        int[][] pix = getImageInPixels();
        try{
            Image temp = Toolkit.getDefaultToolkit().createImage(f.getAbsoluteFile().toString());
            PixelGrabber pg = new PixelGrabber(temp, 0, 0, -1, -1, true);
            pg.grabPixels();
            int width = pg.getWidth(), height = pg.getHeight();
            DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(), 
                pg.getWidth() * pg.getHeight());
            WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
            imgscram = new BufferedImage(RGB_OPAQUE, raster, false, null);
            
            //setting basis matrix
            matrix a2 = new matrix();
            a2.setRow(3);
            a2.setCol(3);
            a2.defineMatrix();
            
            a2.setValue(0, 0, 1);
            a2.setValue(0, 1, 1);
            a2.setValue(0, 2, 1);
            a2.setValue(1, 0, 1);
            a2.setValue(1, 1, 2);
            a2.setValue(1, 2, 2);
            a2.setValue(2, 0, 1);
            a2.setValue(2, 1, 2);
            a2.setValue(2, 2, 3);
            
            System.out.println("\n Basis matrix for second encryption algorithm:");
            a2.displayMatrix();
            
            //encrypt img here
            //don't forget to do mod 256 after each pixel processed; limited values 0 to 255
                
        } catch (Exception e){
            e.printStackTrace();
        }
            
            return imgscram;
    }
    
    /*
    based on first encryption algorithm from "Research on Colour Image
        Efficiency" by Jing-Yu Peng
    */
    public BufferedImage firstPixUnscram(){
        BufferedImage imgscram = null;
        int[][] pix = getImageInPixels();
        try{
            Image temp = Toolkit.getDefaultToolkit().createImage(f.getAbsoluteFile().toString());
            PixelGrabber pg = new PixelGrabber(temp, 0, 0, -1, -1, true);
            pg.grabPixels();
            int width = pg.getWidth(), height = pg.getHeight();
            DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(), 
                pg.getWidth() * pg.getHeight());
            WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
            imgscram = new BufferedImage(RGB_OPAQUE, raster, false, null);
            
            //setting basis matrix
            matrix a1 = new matrix();
            a1.setRow(2);
            a1.setCol(2);
            a1.defineMatrix();
            
            a1.setValue(0, 0, 2);
            a1.setValue(0, 1, -1);
            a1.setValue(1, 0, -1);
            a1.setValue(1, 1, 1);
            
            System.out.println("\n Basis matrix for first decryption algorithm:");
            a1.displayMatrix();
                
            //encrypt img here
            
        } catch (Exception e){
            e.printStackTrace();
        }
            
            return imgscram;
    }
    
    /*
    based on first encryption algorithm from "Research on Colour Image
        Efficiency" by Jing-Yu Peng
    */
    public BufferedImage secondPixUnscram(){
        BufferedImage imgscram = null;
        int[][] pix = getImageInPixels();
        try{
            Image temp = Toolkit.getDefaultToolkit().createImage(f.getAbsoluteFile().toString());
            PixelGrabber pg = new PixelGrabber(temp, 0, 0, -1, -1, true);
            pg.grabPixels();
            int width = pg.getWidth(), height = pg.getHeight();
            DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(), 
                pg.getWidth() * pg.getHeight());
            WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
            imgscram = new BufferedImage(RGB_OPAQUE, raster, false, null);
            
            //setting basis matrix
            matrix a2 = new matrix();
            a2.setRow(3);
            a2.setCol(3);
            a2.defineMatrix();
            
            a2.setValue(0, 0, 2);
            a2.setValue(0, 1, -1);
            a2.setValue(0, 2, 0);
            a2.setValue(1, 0, -1);
            a2.setValue(1, 1, 2);
            a2.setValue(1, 2, -1);
            a2.setValue(2, 0, 0);
            a2.setValue(2, 1, -1);
            a2.setValue(2, 2, 1);
            
            System.out.println("\n Basis matrix for second encryption algorithm:");
            a2.displayMatrix();
            
            //encrypt img here
            //don't forget to do mod 256 after each pixel processed; limited values 0 to 255
            // r' = |2r-g| mod 256
            // g' = |-r+2g-b| mod 256
            // b' = |b-g| mod 256
                
        } catch (Exception e){
            e.printStackTrace();
        }
            
            return imgscram;
    }
    
    /*
    based on first encryption algorithm from "Research on Colour Image
        Efficiency" by Jing-Yu Peng
    */
    public BufferedImage finalImgEnc(){
        BufferedImage imgscram = null;
        int[][] pix = getImageInPixels();
        try{
            Image temp = Toolkit.getDefaultToolkit().createImage(f.getAbsoluteFile().toString());
            PixelGrabber pg = new PixelGrabber(temp, 0, 0, -1, -1, true);
            pg.grabPixels();
            int width = pg.getWidth(), height = pg.getHeight();
            DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(), 
                pg.getWidth() * pg.getHeight());
            WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
            imgscram = new BufferedImage(RGB_OPAQUE, raster, false, null);
            
            //call firstPixScram() T times
            //call secondPixScram() T times 
            System.out.println("Image encrypted!");
                
        } catch (Exception e){
            e.printStackTrace();
        }
            
            return imgscram;
    }
    
    /*
    based on first encryption algorithm from "Research on Colour Image
        Efficiency" by Jing-Yu Peng
    */
    public BufferedImage finalImgDec(){
        BufferedImage imgscram = null;
        int[][] pix = getImageInPixels();
        try{
            Image temp = Toolkit.getDefaultToolkit().createImage(f.getAbsoluteFile().toString());
            PixelGrabber pg = new PixelGrabber(temp, 0, 0, -1, -1, true);
            pg.grabPixels();
            int width = pg.getWidth(), height = pg.getHeight();
            DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(), 
                pg.getWidth() * pg.getHeight());
            WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
            imgscram = new BufferedImage(RGB_OPAQUE, raster, false, null);
            
            //call firstPixUnscram() T times
            //call secondPixUnscram() T times 
            System.out.println("Image decrypted!");
                
        } catch (Exception e){
            e.printStackTrace();
        }
            
            return imgscram;
    }
    
    
    
}
