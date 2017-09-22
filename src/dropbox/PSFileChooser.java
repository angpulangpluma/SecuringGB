/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dropbox;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Marienne Lopez
 * source: https://stackoverflow.com/questions
 *   /24290210/getting-multiple-files-from-jfilechooser
 * 
 */
public class PSFileChooser extends JComponent implements PropertyChangeListener{

    private DefaultListModel model = null;
    private JList list = null;
    private JButton removeItem = null;
    private JFileChooser filewindow = null;
    
    public PSFileChooser(JFileChooser chooser){
        chooser.addPropertyChangeListener(this);
        chooser.setMultiSelectionEnabled(true);
        this.filewindow = chooser;
        
        model = new DefaultListModel();
        list = new JList(model);
        JScrollPane pane = new JScrollPane(list);
        pane.setPreferredSize(new Dimension(200, 250));
        
        removeItem = new JButton("Remove");
        removeItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                removeFileFromList();
            }
        });
        
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());
        add(pane);
        add(removeItem, BorderLayout.SOUTH);
    }
    
    public DefaultListModel getModel(){
        return this.model;
    }
    
    public void addFileToList(File file){
        model.addElement(file);
    }
    
    public void removeFileFromList(){
        if (list.getSelectedIndex()!= -1)
            model.remove(list.getSelectedIndex());
    }
    
    public File[] getAllSelectedFiles(){
        Object[] filelist = model.toArray();
        System.out.println("file count:" + filelist.length);
        File[] output = new File[filelist.length];
        for(int i=0; i<filelist.length; i++){
            output[i] = (File)filelist[i];
        }
        return output;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        boolean update = false;
        String prop = evt.getPropertyName();
        File file = null;
        
        if(JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(prop)){
            file = null;
            update = true;
        } else if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)){
            file = (File) evt.getNewValue();
            update = true;
        }
        
        if (update && file != null){
            addFileToList(file);
        }
    }
    
    }
