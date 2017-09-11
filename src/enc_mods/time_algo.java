/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enc_mods;

import java.io.File;

/**
 *
 * @author Marienne Lopez
 */
public class time_algo {
    
//    private aes basealgo;
    private text_aes textalgo;
    private file_aes filealgo;
    
//    public time_algo(int n){
//       
////       basealgo = null;
//       textalgo = null;
//       filealgo = null;
//       switch(n){
////           case 1:
////            basealgo = new aes(); break;
//           case 2:
//            textalgo = new text_aes(); break;
//           case 3:
//            filealgo = new file_aes(); break;
//       }
//       
//    }
    
    public time_algo(text_aes ta){
        this.textalgo = ta;
    }
    
    public time_algo(file_aes fa){
        this.filealgo = fa;
    }
    
    public long getEncTime(int n, File file, String string){
        long time = 0;
        long starttime = 0;
        long endtime = 0;
        
        switch(n){
            case 21: //aes encryption - text
             {
                starttime = System.currentTimeMillis();
                textalgo.getEncString(string);
                endtime = System.currentTimeMillis();
             }; break;
            case 22: //hash encryption - text
             {
                starttime = System.currentTimeMillis();
                textalgo.getHashedString(string);
                endtime = System.currentTimeMillis();
             }; break;
            case 3: //aes encryption - file
             {
                starttime = System.currentTimeMillis();
                filealgo.encryptFile(file);
                endtime = System.currentTimeMillis();
             }; break;
        }
        
        return endtime - starttime;
    }
    
    public long getDecTime(int n, File file, String string){
        long time = 0;
        long starttime = 0;
        long endtime = 0;
        
        switch(n){
            case 2: 
             {
                starttime = System.currentTimeMillis();
                textalgo.getDecString(string);
                endtime = System.currentTimeMillis();
             }; break;
            case 3:
             {
                starttime = System.currentTimeMillis();
                filealgo.decryptFile(file);
                endtime = System.currentTimeMillis();
             }; break;
        }
        
        return endtime - starttime;
    }

}
