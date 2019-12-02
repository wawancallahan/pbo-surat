/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surat.View;

import surat.Config.Session;
import surat.Login;
import surat.View.KlasifikasiSurat.KlasifikasiSuratIndex;
import surat.View.SuratKeluar.SuratKeluarIndex;
import surat.View.SuratMasuk.SuratMasukIndex;
import surat.View.User.UserIndex;

/**
 *
 * @author riefist
 */
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        
        System.out.print(Session.role);
        
        switch (Session.role) {
            case "admin":
                menuItemSuratMasuk.setVisible(false);
                menuItemSuratKeluar.setVisible(false);
                break;
            case "petugas":
                menuMaster.setVisible(false);
                menuItemKlasifikasi.setVisible(false);
                break;
        }
        
        lblNama.setText(Session.nama);
        lblRole.setText(Session.role);
    }
    
    public void clearSession() {
        Session.nama = "";
        Session.role = "";
        Session.username = "";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        lblNama = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        lblRole = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuItemKeluar = new javax.swing.JMenuItem();
        menuSurat = new javax.swing.JMenu();
        menuItemSuratMasuk = new javax.swing.JMenuItem();
        menuItemSuratKeluar = new javax.swing.JMenuItem();
        menuItemKlasifikasi = new javax.swing.JMenuItem();
        menuMaster = new javax.swing.JMenu();
        menuItemUser = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jToolBar1.setRollover(true);

        lblNama.setText("Nama");
        jToolBar1.add(lblNama);
        jToolBar1.add(jSeparator1);

        lblRole.setText("Role");
        jToolBar1.add(lblRole);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );

        menuFile.setText("File");

        menuItemKeluar.setText("Keluar");
        menuItemKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemKeluarActionPerformed(evt);
            }
        });
        menuFile.add(menuItemKeluar);

        jMenuBar1.add(menuFile);

        menuSurat.setText("Surat");

        menuItemSuratMasuk.setText("Surat Masuk");
        menuItemSuratMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSuratMasukActionPerformed(evt);
            }
        });
        menuSurat.add(menuItemSuratMasuk);

        menuItemSuratKeluar.setText("Surat Keluar");
        menuItemSuratKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSuratKeluarActionPerformed(evt);
            }
        });
        menuSurat.add(menuItemSuratKeluar);

        menuItemKlasifikasi.setText("Klasifikasi");
        menuItemKlasifikasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemKlasifikasiActionPerformed(evt);
            }
        });
        menuSurat.add(menuItemKlasifikasi);

        jMenuBar1.add(menuSurat);

        menuMaster.setText("Master");

        menuItemUser.setText("User");
        menuItemUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemUserActionPerformed(evt);
            }
        });
        menuMaster.add(menuItemUser);

        jMenuBar1.add(menuMaster);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemKeluarActionPerformed
        // TODO add your handling code here:
        clearSession();
        
        Login login = new Login();
        login.setVisible(true);
        
        dispose();
    }//GEN-LAST:event_menuItemKeluarActionPerformed

    private void menuItemSuratMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSuratMasukActionPerformed
        // TODO add your handling code here:
        
        SuratMasukIndex.showForm().setVisible(true);
    }//GEN-LAST:event_menuItemSuratMasukActionPerformed

    private void menuItemUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemUserActionPerformed
        // TODO add your handling code here:
        UserIndex.showForm().setVisible(true);
    }//GEN-LAST:event_menuItemUserActionPerformed

    private void menuItemKlasifikasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemKlasifikasiActionPerformed
        // TODO add your handling code here:
        KlasifikasiSuratIndex.showForm().setVisible(true);
    }//GEN-LAST:event_menuItemKlasifikasiActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void menuItemSuratKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSuratKeluarActionPerformed
        // TODO add your handling code here:
        SuratKeluarIndex.showForm().setVisible(true);
    }//GEN-LAST:event_menuItemSuratKeluarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblRole;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuItemKeluar;
    private javax.swing.JMenuItem menuItemKlasifikasi;
    private javax.swing.JMenuItem menuItemSuratKeluar;
    private javax.swing.JMenuItem menuItemSuratMasuk;
    private javax.swing.JMenuItem menuItemUser;
    private javax.swing.JMenu menuMaster;
    private javax.swing.JMenu menuSurat;
    // End of variables declaration//GEN-END:variables
}
