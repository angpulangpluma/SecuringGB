/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sec_algo;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author Marienne Lopez
 */
public class img_proc {
    
    private BufferedImage image;
//    private File f;
    
    public img_proc(){
        this.image = null;
//        this.f = null;
    }
    
    public void setImage(File fileloc){
        try{
//            f = new File(fileloc);
            BufferedImage temp = ImageIO.read(fileloc);
            image = new BufferedImage(temp.getWidth(), temp.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        } catch(IOException e){
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
