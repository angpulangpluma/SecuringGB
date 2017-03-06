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
import javax.imageio.ImageIO;

/**
 *
 * @author Marienne Lopez
 */
public class img_proc {
    
    private int[] RGB_MASKS;
    private final ColorModel RGB_OPAQUE;
    private BufferedImage image;
//    private File f;
    
    public img_proc(){
        this.image = null;
        RGB_MASKS = new int[]{0xFF0000, 0xFF00, 0xFF};
        RGB_OPAQUE = new DirectColorModel(32, RGB_MASKS[0],
            RGB_MASKS[1], RGB_MASKS[2]);
//        this.f = null;
    }
    
    public void setImage(File fileloc){
        try{
//            f = new File(fileloc);
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
    
}
