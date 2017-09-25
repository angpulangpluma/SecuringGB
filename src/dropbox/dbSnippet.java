/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dropbox;
import com.dropbox.core.*;
import com.dropbox.core.v2.*;
import com.dropbox.core.v2.files.*;
import com.dropbox.core.v2.users.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.util.Date;
import javax.swing.JFileChooser;
/**
 *
 * @author Marienne Lopez
 * Source: www.dropbox.com/developers/documentation/java#tutorial
 */
public class dbSnippet {
    private static final String ACCESS_TOKEN = "I9h5pIu_C2AAAAAAAAAAX-7E5iZmUqhT3n7Jo8MQ7M3c2fyzV6tyMQsdmecbwAoO";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        DbxRequestConfig config = new DbxRequestConfig("DrpBxWithEncryption", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        
        try{
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());
        
        ListFolderResult result = client.files().listFolder("");
        while(true){
            for(Metadata metadata : result.getEntries()){
                System.out.println(metadata.getPathLower());
            }
            
            if (!result.getHasMore())
                break;
        }
        
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
            
        }
        } catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }

}
