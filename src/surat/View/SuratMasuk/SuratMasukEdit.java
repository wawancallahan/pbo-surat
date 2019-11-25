/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surat.View.SuratMasuk;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import surat.Config.Koneksi;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import surat.Config.KlasifikasiSurat;

/**
 *
 * @author riefist
 */
public class SuratMasukEdit extends javax.swing.JFrame {

    private static Connection getConnection = Koneksi.getConnection();
    private static SuratMasukEdit suratMasukEdit = null;
    public Integer id;
    private boolean isLoad = false;
    
    DefaultComboBoxModel cmbKlasifikasiSuratModel = new DefaultComboBoxModel();
    
    /**
     * Creates new form SuratMasukEdit
     */
    public SuratMasukEdit() {
        initComponents();
        
        cmbKlasifikasiSuratModel.removeAllElements();
        try {
            String query = "SELECT * FROM klasifikasi_surat";
            PreparedStatement pst = getConnection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                cmbKlasifikasiSuratModel.addElement(new KlasifikasiSurat(rs.getString("nama"), rs.getString("id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        cmbKlasifikasi.setModel(cmbKlasifikasiSuratModel);
        cmbKlasifikasi.setSelectedIndex(-1);
    }
    
      public static SuratMasukEdit showForm(Integer id) {
        if (suratMasukEdit == null) {
            suratMasukEdit = new SuratMasukEdit().setId(id).initializeForm();
        }
        
        return suratMasukEdit;
    }
    
    public SuratMasukEdit setId(Integer id) {
        this.id = id;
        
        return this;
    }
    
    public SuratMasukEdit initializeForm() {
        
        if (this.id != null) {
            try {
                String query = "SELECT " +
                            "surat_masuk.id AS surat_masuk_id, " +
                            "surat_masuk.nomor_agenda AS surat_masuk_no_agenda, " +
                            "surat_masuk.nomor_surat AS surat_masuk_no_surat, " +
                            "surat_masuk.perihal AS surat_masuk_perihal, " +
                            "surat_masuk.tanggal AS surat_masuk_tanggal, " +
                            "surat_masuk.isi AS surat_masuk_isi, " +
                            "surat_masuk.keterangan AS surat_masuk_keterangan, " +
                            "surat_masuk.pengirim AS surat_masuk_pengirim, " +
                            "klasifikasi_surat.id AS klasifikasi_surat_id, " +
                            "klasifikasi_surat.nama AS klasifikasi_surat_nama " +
                            "FROM surat_masuk " + 
                            "LEFT JOIN klasifikasi_surat " + 
                            "ON klasifikasi_surat.id = " + 
                            "( SELECT id FROM klasifikasi_surat WHERE klasifikasi_surat.id = surat_masuk.klasifikasi_surat_id" +
                            " LIMIT 1 ) " +
                            "WHERE surat_masuk.id =?";
                PreparedStatement pst = getConnection.prepareStatement(query);
                pst.setInt(1, this.id);
                ResultSet rs = pst.executeQuery();
              
                if (rs.first()) {
                    isLoad = true;

                    String noAgenda = rs.getString("surat_masuk_no_agenda");
                    String noSurat = rs.getString("surat_masuk_no_surat");
                    String perihal = rs.getString("surat_masuk_perihal");
                    String isi = rs.getString("surat_masuk_isi");
                    String keterangan = rs.getString("surat_masuk_keterangan");
                    String pengirim = rs.getString("surat_masuk_pengirim");

                    try {
                        Date tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("surat_masuk_tanggal"));

                        txtDate.setDate(tanggal);
                    } catch (ParseException ex) {
                    }
                    
                    txtNoAgenda.setText(noAgenda);
                    txtNoSurat.setText(noSurat);
                    txtPerihal.setText(perihal);
                    txtIsi.setText(isi);
                    txtKeterangan.setText(keterangan);
                    txtPengirim.setText(pengirim);
                    cmbKlasifikasi.getModel().setSelectedItem(new KlasifikasiSurat(rs.getString("klasifikasi_surat_nama"), rs.getString("klasifikasi_surat_id")));
               
                    int index = -1;
                    KlasifikasiSurat item;
                    for (int i = 0; i < cmbKlasifikasiSuratModel.getSize(); i ++) {
                        item = (KlasifikasiSurat) cmbKlasifikasiSuratModel.getElementAt(i);

                        if (item.getId().equals(rs.getString("klasifikasi_surat_id"))) {
                            index = i;

                            break;
                        }
                    }
                    
                    cmbKlasifikasi.setSelectedIndex(index);
                } else {
                    System.out.println("Data Tidak Ditemukan");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        return this;
    }
    
     private void setDateText() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String today = formatter.format(new Date());
            Date dateNow = new SimpleDateFormat("yyyy-MM-dd").parse(today);
            
            txtDate.setDate(dateNow);
        } catch (ParseException ex) {
        }
    }
    
    public void clearInput() {
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
        jLabel2 = new javax.swing.JLabel();
        txtNoAgenda = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNoSurat = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPerihal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbKlasifikasi = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtIsi = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtKeterangan = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        txtPengirim = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        txtDate = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Edit Surat Masuk");

        jLabel2.setText("No. Agenda");

        jLabel3.setText("No. Surat");

        jLabel4.setText("Perihal");

        jLabel6.setText("Klasifikasi");

        jLabel7.setText("Tanggal");

        jLabel8.setText("Isi");

        txtIsi.setColumns(20);
        txtIsi.setRows(5);
        jScrollPane1.setViewportView(txtIsi);

        jLabel9.setText("Keterangan");

        txtKeterangan.setColumns(20);
        txtKeterangan.setRows(5);
        jScrollPane2.setViewportView(txtKeterangan);

        jLabel10.setText("Pengirim");

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addComponent(txtNoAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNoSurat)
                                    .addComponent(txtPerihal)
                                    .addComponent(cmbKlasifikasi, 0, 278, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPengirim)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1)
                                    .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(35, 35, 35))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNoAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNoSurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtPerihal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbKlasifikasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPengirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(btnSimpan)
                        .addGap(24, 24, 24))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        String noAgenda = txtNoAgenda.getText();
        String noSurat = txtNoSurat.getText();
        String perihal = txtPerihal.getText();
        KlasifikasiSurat klasifikasi = (KlasifikasiSurat) cmbKlasifikasi.getSelectedItem();
        String isi = txtIsi.getText();
        String keterangan = txtKeterangan.getText();
        String pengirim = txtPengirim.getText();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date_string = formatter.format(txtDate.getDate());

        if (noAgenda.isBlank() ||
            noSurat.isBlank() ||
            perihal.isBlank() ||
            cmbKlasifikasi.getSelectedIndex() < 0 ||
            isi.isBlank() ||
            keterangan.isBlank() ||
            pengirim.isBlank() ||
            date_string.isBlank()) {
            JOptionPane.showMessageDialog(rootPane, "Tolong isi input dengan benar");

            return;
        }

        try {
            String query = "UPDATE surat_masuk SET nomor_agenda=?, " + 
                            "nomor_surat=?, " +
                            "perihal=?, " + 
                            "klasifikasi_surat_id=?, " + 
                            "tanggal=?, " + 
                            "isi=?, " + 
                            "keterangan=?, " + 
                            "pengirim=? "+ 
                            "WHERE id=?";
            PreparedStatement pst = getConnection.prepareStatement(query);
            pst.setString(1, noAgenda);
            pst.setString(2, noSurat);
            pst.setString(3, perihal);
            pst.setString(4, klasifikasi.getId());
            pst.setString(5, date_string);
            pst.setString(6, isi);
            pst.setString(7, keterangan);
            pst.setString(8, pengirim);
            pst.setInt(9, this.id);

            int result = pst.executeUpdate();

            if (result == 1) {
                JOptionPane.showMessageDialog(rootPane, "Data Berhasil Diedit");

                clearInput();
                SuratMasukIndex.showForm().read();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Terjadi Kesalahan Proses Data");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

            JOptionPane.showMessageDialog(rootPane, "Terjadi Kesalahan Proses Data");
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        SuratMasukEdit.suratMasukEdit = null;
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
            java.util.logging.Logger.getLogger(SuratMasukEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuratMasukEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuratMasukEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuratMasukEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SuratMasukEdit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cmbKlasifikasi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextArea txtIsi;
    private javax.swing.JTextArea txtKeterangan;
    private javax.swing.JTextField txtNoAgenda;
    private javax.swing.JTextField txtNoSurat;
    private javax.swing.JTextField txtPengirim;
    private javax.swing.JTextField txtPerihal;
    // End of variables declaration//GEN-END:variables
}
