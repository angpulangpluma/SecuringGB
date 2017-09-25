/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dropbox;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import enc_mods.file_aes;
import enc_mods.text_aes;

/**
 *
 * @author Marienne Lopez
 */
    
public class dbController {
    
    private DbxRequestConfig config = null;
    private DbxClientV2 client = null;
    private DefaultListModel model = null;
    private String curFolder = null;
    private file_aes filecrypt = null;
    private text_aes textcrypt = null;
//    private ArrayList<FolderMetadata> folders = null;
    
    public dbController(String dropbox, String folderpath){
        config = new DbxRequestConfig("DrpBxWithEncryption", "en_US");
        client = new DbxClientV2(config, dropbox);
        
        curFolder = folderpath;
//        folders = new ArrayList<FolderMetadata>();
        
        filecrypt = new file_aes();
        textcrypt = new text_aes();
        try{
        refreshFiles(curFolder);
//        knowFolders();
        } catch (Exception e){
            e.printStackTrace();
           System.exit(1);
        }
    }
    
    public file_aes getFileCrypt(){
        return filecrypt;
    }
    
    public text_aes getTextCrypt(){
        return textcrypt;
    }
    
    public DefaultListModel returnModel(){
        return this.model;
    }
    
//    private void knowFolders() throws Exception{
//        boolean isFolder = false;
//        ArrayList<Integer> folderindices = new ArrayList<Integer>();
//        int fi = 0;
//        int cnt = 0;
//        ListFolderResult result = client.files().listFolder("");
//            while(true){
//                for(Metadata metadata : result.getEntries()){
////                    System.out.println(metadata.getPathLower());
//                    if(metadata instanceof FolderMetadata){
//                        model.addElement(metadata.getName());
//                        folders.add((FolderMetadata)metadata);
//                        cnt++;
//                        isFolder = true;
//                    }
//                }
//                
//                if (!result.getHasMore() && isFolder){
//                    
//                    result = client.files().listFolder(folders.get(folderindices.get(fi)).getName());
//                }
//
//                else if (!result.getHasMore() && !isFolder)
//                    break;
//            }
//    }
    
    private void refreshFiles(String folderPath) throws Exception{
        model.clear();
                
        //list files in that folder        
        ListFolderResult result = client.files().listFolder(folderPath);
            while(true){
                for(Metadata metadata : result.getEntries()){
//                    System.out.println(metadata.getPathLower());
                    model.addElement(metadata.getName());
                }

                if (!result.getHasMore())
                    break;
            }
        
    }
    
}
