/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surat.View.SuratKeluar;

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
public class SuratKeluarEdit extends javax.swing.JFrame {
    
    private static Connection getConnection = Koneksi.getConnection();
    
    private static SuratKeluarEdit suratKeluarEdit = null;
    
    public Integer id;
    private boolean isLoad = false;
    
    DefaultComboBoxModel cmbKlasifikasiSuratModel = new DefaultComboBoxModel();

    /**
     * Creates new form SuratKeluarEdit
     */
    public SuratKeluarEdit() {
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
    
     public static SuratKeluarEdit showForm(Integer id) {
        if (suratKeluarEdit == null) {
            suratKeluarEdit = new SuratKeluarEdit().setId(id).initializeForm();
        }
        
        return suratKeluarEdit;
    }
    
    public SuratKeluarEdit setId(Integer id) {
        this.id = id;
        
        return this;
    }
    
     public SuratKeluarEdit initializeForm() {
        
        if (this.id != null) {
            try {
                String query = "SELECT " +
                            "surat_keluar.id AS surat_keluar_id, " +
                            "surat_keluar.nomor_surat AS surat_keluar_no_surat, " +
                            "surat_keluar.perihal AS surat_keluar_perihal, " +
                            "surat_keluar.tanggal AS surat_keluar_tanggal, " +
                            "surat_keluar.isi AS surat_keluar_isi, " +
                            "surat_keluar.keterangan AS surat_keluar_keterangan, " +
                            "surat_keluar.pengirim AS surat_keluar_pengirim, " +
                            "surat_keluar.tertuju AS surat_keluar_tertuju, " +
                            "surat_keluar.alamat AS surat_keluar_alamat, " +
                            "klasifikasi_surat.id AS klasifikasi_surat_id, " +
                            "klasifikasi_surat.nama AS klasifikasi_surat_nama " +
                            "FROM surat_keluar " + 
                            "LEFT JOIN klasifikasi_surat " + 
                            "ON klasifikasi_surat.id = " + 
                            "( SELECT id FROM klasifikasi_surat WHERE klasifikasi_surat.id = surat_keluar.klasifikasi_surat_id" +
                            " LIMIT 1 ) " +
                            "WHERE surat_keluar.id =?";
                PreparedStatement pst = getConnection.prepareStatement(query);
                pst.setInt(1, this.id);
                ResultSet rs = pst.executeQuery();
              
                if (rs.first()) {
                    isLoad = true;

                    String noSurat = rs.getString("surat_keluar_no_surat");
                    String perihal = rs.getString("surat_keluar_perihal");
                    String isi = rs.getString("surat_keluar_isi");
                    String keterangan = rs.getString("surat_keluar_keterangan");
                    String pengirim = rs.getString("surat_keluar_pengirim");
                    String tertuju = rs.getString("surat_keluar_tertuju");
                    String alamat = rs.getString("surat_keluar_alamat");

                    try {
                        Date tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("surat_keluar_tanggal"));

                        txtDate.setDate(tanggal);
                    } catch (ParseException ex) {
                    }
                    
                    txtNoSurat.setText(noSurat);
                    txtPerihal.setText(perihal);
                    txtIsi.setText(isi);
                    txtKeterangan.setText(keterangan);
                    txtPengirim.setText(pengirim);
                    txtTertuju.setText(tertuju);
                    txtAlamat.setText(alamat);
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
        txtNoSurat = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPerihal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbKlasifikasi = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtDate = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtIsi = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtKeterangan = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPengirim = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTertuju = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        btnSimpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Edit Surat Keluar");

        jLabel2.setText("No. Surat");

        jLabel3.setText("Perihal");

        jLabel4.setText("Klasifikasi");

        jLabel5.setText("Tanggal");

        jLabel6.setText("Isi");

        txtIsi.setColumns(20);
        txtIsi.setRows(5);
        jScrollPane1.setViewportView(txtIsi);

        txtKeterangan.setColumns(20);
        txtKeterangan.setRows(5);
        jScrollPane2.setViewportView(txtKeterangan);

        jLabel7.setText("Keterangan");

        jLabel8.setText("Pengirim");

        jLabel9.setText("Tertuju");

        jLabel10.setText("Alamat");

        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        jScrollPane3.setViewportView(txtAlamat);

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
                        .addComponent(jLabel1)
                        .addGap(0, 259, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNoSurat)
                            .addComponent(txtPerihal)
                            .addComponent(cmbKlasifikasi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2)
                            .addComponent(txtPengirim)
                            .addComponent(txtTertuju)
                            .addComponent(jScrollPane3))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNoSurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPerihal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbKlasifikasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPengirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTertuju, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(btnSimpan)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        String noSurat = txtNoSurat.getText();
        String perihal = txtPerihal.getText();
        KlasifikasiSurat klasifikasi = (KlasifikasiSurat) cmbKlasifikasi.getSelectedItem();
        String isi = txtIsi.getText();
        String keterangan = txtKeterangan.getText();
        String pengirim = txtPengirim.getText();
        String tertuju = txtTertuju.getText();
        String alamat = txtAlamat.getText();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date_string = formatter.format(txtDate.getDate());

        if (noSurat.isBlank() ||
            perihal.isBlank() ||
            cmbKlasifikasi.getSelectedIndex() < 0 ||
            isi.isBlank() ||
            keterangan.isBlank() ||
            pengirim.isBlank() ||
            date_string.isBlank() ||
            tertuju.isBlank() ||
            alamat.isBlank()) {
            JOptionPane.showMessageDialog(rootPane, "Tolong isi input dengan benar");

            return;
        }

        try {
           String query = "UPDATE surat_keluar SET  " + 
                            "nomor_surat=?, " +
                            "perihal=?, " + 
                            "klasifikasi_surat_id=?, " + 
                            "tanggal=?, " + 
                            "isi=?, " + 
                            "keterangan=?, " + 
                            "pengirim=?, "+ 
                            "tertuju=?, "+ 
                            "alamat=? "+ 
                            "WHERE id=?";
            PreparedStatement pst = getConnection.prepareStatement(query);
            pst.setString(1, noSurat);
            pst.setString(2, perihal);
            pst.setString(3, klasifikasi.getId());
            pst.setString(4, date_string);
            pst.setString(5, isi);
            pst.setString(6, keterangan);
            pst.setString(7, pengirim);
            pst.setString(8, tertuju);
            pst.setString(9, alamat);
            pst.setInt(10, this.id);

            int result = pst.executeUpdate();

            if (result == 1) {
                JOptionPane.showMessageDialog(rootPane, "Data Berhasil Diedit");

                SuratKeluarIndex.showForm().read();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Terjadi Kesalahan Proses Data");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

            JOptionPane.showMessageDialog(rootPane, "Terjadi Kesalahan Proses Data");
        }

    }//GEN-LAST:event_btnSimpanActionPerformed

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
            java.util.logging.Logger.getLogger(SuratKeluarEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuratKeluarEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuratKeluarEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuratKeluarEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SuratKeluarEdit().setVisible(true);
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea txtAlamat;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextArea txtIsi;
    private javax.swing.JTextArea txtKeterangan;
    private javax.swing.JTextField txtNoSurat;
    private javax.swing.JTextField txtPengirim;
    private javax.swing.JTextField txtPerihal;
    private javax.swing.JTextField txtTertuju;
    // End of variables declaration//GEN-END:variables
}
