/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surat.View.SuratMasuk;

import surat.Config.Koneksi;
import surat.Config.Crud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author riefist
 */
public class SuratMasukIndex extends javax.swing.JFrame implements Crud {
    
    private static SuratMasukIndex suratMasukIndex = null;
    
    private static Connection getConnection = Koneksi.getConnection();

    /**
     * Creates new form SuratMasukIndex
     */
    public SuratMasukIndex() {
        initComponents();
        read();
    }
    
     public static SuratMasukIndex showForm() {
        if (suratMasukIndex == null) {
            suratMasukIndex = new SuratMasukIndex();
        }
        
        return suratMasukIndex;
    }
     
    @Override
    public void create() {
        SuratMasukCreate.showForm().setVisible(true);
    }

    @Override
    public void read() {
       DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        model.setRowCount(0);
        
        model.addColumn("No");
        model.addColumn("ID");
        model.addColumn("No. Agenda");
        model.addColumn("No. Surat");
        model.addColumn("Perihal");
        model.addColumn("Klasifikasi");
        model.addColumn("Tanggal");
        model.addColumn("Pengirim");
        
        try {
            String query = "SELECT " +
                           "surat_masuk.id AS surat_masuk_id, " +
                           "surat_masuk.nomor_agenda AS surat_masuk_no_agenda, " +
                           "surat_masuk.nomor_surat AS surat_masuk_no_surat, " +
                           "surat_masuk.perihal AS surat_masuk_perihal, " +
                           "surat_masuk.tanggal AS surat_masuk_tanggal, " +
                           "surat_masuk.pengirim AS surat_masuk_pengirim, " +
                           "klasifikasi_surat.nama AS klasifikasi_surat_nama " +
                           "FROM surat_masuk " + 
                           "LEFT JOIN klasifikasi_surat " + 
                           "ON klasifikasi_surat.id = " + 
                           "( SELECT id FROM klasifikasi_surat WHERE klasifikasi_surat.id = surat_masuk.klasifikasi_surat_id" +
                           " LIMIT 1 )";
            PreparedStatement pst = getConnection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            int increment = 1;
            
            while (rs.next()) {
                model.addRow(new Object[] {
                    increment,
                    rs.getString("surat_masuk_id"),
                    rs.getString("surat_masuk_no_agenda"),
                    rs.getString("surat_masuk_no_surat"),
                    rs.getString("surat_masuk_perihal"),
                    rs.getString("klasifikasi_surat_nama"),
                    rs.getString("surat_masuk_tanggal"),
                    rs.getString("surat_masuk_pengirim")
                });
                
                increment += 1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        table.setModel(model);
        table.getColumnModel().getColumn(1).setMinWidth(0);
        table.getColumnModel().getColumn(1).setMaxWidth(0);
        table.getColumnModel().getColumn(2).setMinWidth(100);
        table.getColumnModel().getColumn(3).setMinWidth(100);
        table.getColumnModel().getColumn(4).setMinWidth(100);
        table.getColumnModel().getColumn(5).setMinWidth(100);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void update(Integer id) {
        SuratMasukEdit.showForm(id).setVisible(true);
    }

    @Override
    public void delete(Integer id) {
        try {
            String query = "DELETE FROM surat_masuk WHERE id=?";
            PreparedStatement pst = getConnection.prepareStatement(query);
            pst.setInt(1, id);
            int result = pst.executeUpdate();
            
            if (result == 1) {
                JOptionPane.showMessageDialog(rootPane, "Data Berhasil Dihapus");
                read();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Terjadi Kesalahan Proses Data");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(rootPane, "Terjadi Kesalahan Proses Data");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnTambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnCari = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Surat Masuk");

        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(table);

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jLabel2.setText("Cari");

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHapus))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCari)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnHapus)
                    .addComponent(btnEdit))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnCari)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        create();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here: 
        int rowIndex = table.getSelectedRow();
         
        if (rowIndex > -1) {
            delete(Integer.valueOf(table.getValueAt(rowIndex, 1).toString()));
            table.clearSelection();
            return;
        }
        
        JOptionPane.showMessageDialog(rootPane, "Data Belum Terpilih");
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        int rowIndex = table.getSelectedRow();
        
        if (rowIndex > -1) {
            update(Integer.valueOf(table.getValueAt(rowIndex, 1).toString()));
            table.clearSelection();
            return;
        }
        
        JOptionPane.showMessageDialog(rootPane, "Data Belum Terpilih");
    }//GEN-LAST:event_btnEditActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        SuratMasukIndex.suratMasukIndex = null;
    }//GEN-LAST:event_formWindowClosing

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        model.setRowCount(0);
        
        model.addColumn("No");
        model.addColumn("ID");
        model.addColumn("No. Agenda");
        model.addColumn("No. Surat");
        model.addColumn("Perihal");
        model.addColumn("Klasifikasi");
        model.addColumn("Tanggal");
        model.addColumn("Pengirim");
        
        try {
            
            String cari = txtCari.getText();
            
            String query = "SELECT " +
                           "surat_masuk.id AS surat_masuk_id, " +
                           "surat_masuk.nomor_agenda AS surat_masuk_no_agenda, " +
                           "surat_masuk.nomor_surat AS surat_masuk_no_surat, " +
                           "surat_masuk.perihal AS surat_masuk_perihal, " +
                           "surat_masuk.tanggal AS surat_masuk_tanggal, " +
                           "surat_masuk.pengirim AS surat_masuk_pengirim, " +
                           "klasifikasi_surat.nama AS klasifikasi_surat_nama " +
                           "FROM surat_masuk " + 
                           "LEFT JOIN klasifikasi_surat " + 
                           "ON klasifikasi_surat.id = " + 
                           "( SELECT id FROM klasifikasi_surat WHERE klasifikasi_surat.id = surat_masuk.klasifikasi_surat_id" +
                           " LIMIT 1 )" + 
                           " WHERE surat_masuk.nomor_agenda LIKE '%" + cari + "%' OR surat_masuk.nomor_surat LIKE '%" + cari + "%' OR surat_masuk.perihal LIKE '%" + cari + "%'";
            PreparedStatement pst = getConnection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            int increment = 1;
            
            while (rs.next()) {
                model.addRow(new Object[] {
                    increment,
                    rs.getString("surat_masuk_id"),
                    rs.getString("surat_masuk_no_agenda"),
                    rs.getString("surat_masuk_no_surat"),
                    rs.getString("surat_masuk_perihal"),
                    rs.getString("klasifikasi_surat_nama"),
                    rs.getString("surat_masuk_tanggal"),
                    rs.getString("surat_masuk_pengirim")
                });
                
                increment += 1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        table.setModel(model);
        table.getColumnModel().getColumn(1).setMinWidth(0);
        table.getColumnModel().getColumn(1).setMaxWidth(0);
        table.getColumnModel().getColumn(2).setMinWidth(100);
        table.getColumnModel().getColumn(3).setMinWidth(100);
        table.getColumnModel().getColumn(4).setMinWidth(100);
        table.getColumnModel().getColumn(5).setMinWidth(100);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }//GEN-LAST:event_btnCariActionPerformed

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
            java.util.logging.Logger.getLogger(SuratMasukIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuratMasukIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuratMasukIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuratMasukIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SuratMasukIndex().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables

}
