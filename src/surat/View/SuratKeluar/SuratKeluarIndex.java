/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surat.View.SuratKeluar;

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
public class SuratKeluarIndex extends javax.swing.JFrame implements Crud {
    
    private static SuratKeluarIndex suratKeluarIndex = null;
    
    private static Connection getConnection = Koneksi.getConnection();

    /**
     * Creates new form SuratKeluarIndex
     */
    public SuratKeluarIndex() {
        initComponents();
        read();
    }
    
    public static SuratKeluarIndex showForm() {
        if (suratKeluarIndex == null) {
            suratKeluarIndex = new SuratKeluarIndex();
        }
        
        return suratKeluarIndex;
    }
    
    @Override
    public void create() {
        SuratKeluarCreate.showForm().setVisible(true);
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
        model.addColumn("No. Surat");
        model.addColumn("Perihal");
        model.addColumn("Klasifikasi");
        model.addColumn("Tanggal");
        model.addColumn("Pengirim");
        model.addColumn("Tertuju");
        
        try {
            String query = "SELECT " +
                           "surat_keluar.id AS surat_keluar_id, " +
                           "surat_keluar.nomor_surat AS surat_keluar_no_surat, " +
                           "surat_keluar.perihal AS surat_keluar_perihal, " +
                           "surat_keluar.tanggal AS surat_keluar_tanggal, " +
                           "surat_keluar.pengirim AS surat_keluar_pengirim, " +
                           "surat_keluar.tertuju AS surat_keluar_tertuju, " +
                           "klasifikasi_surat.nama AS klasifikasi_surat_nama " +
                           "FROM surat_keluar " + 
                           "LEFT JOIN klasifikasi_surat " + 
                           "ON klasifikasi_surat.id = " + 
                           "( SELECT id FROM klasifikasi_surat WHERE klasifikasi_surat.id = surat_keluar.klasifikasi_surat_id" +
                           " LIMIT 1 )";
            PreparedStatement pst = getConnection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            int increment = 1;
            
            while (rs.next()) {
                model.addRow(new Object[] {
                    increment,
                    rs.getString("surat_keluar_id"),
                    rs.getString("surat_keluar_no_surat"),
                    rs.getString("surat_keluar_perihal"),
                    rs.getString("klasifikasi_surat_nama"),
                    rs.getString("surat_keluar_tanggal"),
                    rs.getString("surat_keluar_pengirim"),
                    rs.getString("surat_keluar_tertuju")
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
        SuratKeluarEdit.showForm(id).setVisible(true);
    }

    @Override
    public void delete(Integer id) {
        try {
            String query = "DELETE FROM surat_keluar WHERE id=?";
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Surat Keluar");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHapus)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnHapus)
                    .addComponent(btnEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        suratKeluarIndex.suratKeluarIndex = null;
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(SuratKeluarIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuratKeluarIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuratKeluarIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuratKeluarIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SuratKeluarIndex().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
