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
                BufferedImage.TYPE_INT_ARGB);
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
    
    
}
