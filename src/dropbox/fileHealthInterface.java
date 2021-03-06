/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dropbox;

/**
 *
 * @author YING LOPEZ
 */
public class fileHealthInterface extends javax.swing.JFrame {

    private dbInterface dbione = null;
    private dbInterface dbitwo = null;
    private dbInterface dbitri = null;
    /**
     * Creates new form fileHealthInterface
     */    
    public fileHealthInterface() {
        dbione = new dbInterface("/binan/h1/", this);
        dbitwo = new dbInterface("/binan/h2/", this);
        dbitri = new dbInterface("/binan/h3/", this);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        h1_btn = new javax.swing.JButton();
        h2_btn = new javax.swing.JButton();
        h3_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        h1_btn.setText("Health Center A, Binan, Laguna");
        h1_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h1_btnActionPerformed(evt);
            }
        });

        h2_btn.setText("Health Center B, Binan, Laguna");
        h2_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h2_btnActionPerformed(evt);
            }
        });

        h3_btn.setText("National Health Center, Laguna");
        h3_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h3_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(h3_btn)
                    .addComponent(h2_btn)
                    .addComponent(h1_btn))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(h1_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(h2_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(h3_btn)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void h1_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h1_btnActionPerformed
        // TODO add your handling code here:
        this.hide();
        dbione.show();
    }//GEN-LAST:event_h1_btnActionPerformed

    private void h2_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h2_btnActionPerformed
        // TODO add your handling code here:
        this.hide();
        dbitwo.show();
    }//GEN-LAST:event_h2_btnActionPerformed

    private void h3_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h3_btnActionPerformed
        // TODO add your handling code here:
        this.hide();
        dbitri.show();
    }//GEN-LAST:event_h3_btnActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        
        dbione.getController().getFileCrypt().getCrypt().saveKey("demo2//1_file.txt");
        dbione.getController().getTextCrypt().getCrypt().saveKey("demo2//1_text.txt");
        
        dbitwo.getController().getFileCrypt().getCrypt().saveKey("demo2//2_file.txt");
        dbitwo.getController().getTextCrypt().getCrypt().saveKey("demo2//2_text.txt");
        
        dbitri.getController().getFileCrypt().getCrypt().saveKey("demo2//3_file.txt");
        dbitri.getController().getTextCrypt().getCrypt().saveKey("demo2//3_text.txt");
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(fileHealthInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(fileHealthInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(fileHealthInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(fileHealthInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new fileHealthInterface().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton h1_btn;
    private javax.swing.JButton h2_btn;
    private javax.swing.JButton h3_btn;
    // End of variables declaration//GEN-END:variables
}
