/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dropbox;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v1.DbxEntry;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.WriteMode;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import enc_mods.file_aes;
import enc_mods.text_aes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import javax.swing.JFileChooser;

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
    
    public Metadata isFile(String item) throws Exception{
        Metadata result = null;
        result = client.files().getMetadata(item);
        if (result instanceof FolderMetadata)
            result = null;
        return result;
        //ask whether user wants to download the file
        //if yes, download
        //else view the file, check extension
    }
    
    public boolean getFile(String filename) throws Exception{
        boolean result = false;
        FileOutputStream outputStream = new FileOutputStream("fromdb//" +
                curFolder + "_" + filename);
        try{
            FileMetadata downloadedFile = client.files().downloadBuilder(curFolder + "/" 
                    + filename).download(outputStream);
        } finally{
            outputStream.close();
            result = true;
        }
        
        return result;
    }
    
    public boolean uploadFiles() throws Exception{
        boolean result = false;
        JFileChooser fileChooser = new JFileChooser();
        PSFileChooser accessory = new PSFileChooser(fileChooser);
        fileChooser.setAccessory(accessory);
        int rVal = fileChooser.showOpenDialog(fileChooser);
        if (rVal == JFileChooser.APPROVE_OPTION){
            InputStream in = null;
            File[] selfiles = accessory.getAllSelectedFiles();
            System.out.println("The following files have been uploaded:");
            for(int i=0; i<selfiles.length; i++){
                in = new FileInputStream(selfiles[i]);
            //tip: if one wants to store in folder, put "/<folder name>/"
                FileMetadata metadata = client.files().uploadBuilder("/" + selfiles[i].getName())
                        .withMode(WriteMode.ADD)
                        .withClientModified(new Date(selfiles[i].lastModified()))
                        .uploadAndFinish(in);
                System.out.println(selfiles[i].getName());
            }
            result = true;
        }
        return result;
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
