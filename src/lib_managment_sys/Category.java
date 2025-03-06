/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lib_managment_sys;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.DatabaseMetaData;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;




/**
 *
 * @author kashi
 */
public class Category extends javax.swing.JFrame {

    /**
     * Creates new form Category
     */
    public Category() {
        initComponents();
        connect();
        load_category();
        catlbl.requestFocus();
         Image icon=new ImageIcon(this.getClass().getResource("/library.png")).getImage();
        this.setIconImage(icon);
    }

        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        
        
     public void connect() {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
           
            String url = "jdbc:mysql://localhost:3306/lab_managment";
            String user = "root";
            String password = "MySQL";

            con = DriverManager.getConnection(url, user, password);
       
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
     
     
     public void load_category(){
         
         int c;
         
        try {
            pst=con.prepareStatement("select * from category");
            
            rs=pst.executeQuery();
            
            ResultSetMetaData rsd=(ResultSetMetaData) rs.getMetaData();
            c=rsd.getColumnCount();
            
            DefaultTableModel d=(DefaultTableModel) cattable.getModel();
            d.setRowCount(0);
            
            while(rs.next()){
            Vector v=new Vector();
            
            for(int i=1 ;i<=c;i++){
            v.add(rs.getString("id"));
            v.add(rs.getString("name"));
            v.add(rs.getString("status"));
            
            
            }
            d.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     
     }
     
     
     public void load_search(String str){
         
         int c;
         
        try {
            pst=con.prepareStatement("select * from category where name like ?");
             pst.setString(1, "%" + str + "%");
            rs=pst.executeQuery();
            
            
             if(rs.isBeforeFirst()){
            ResultSetMetaData rsd=(ResultSetMetaData) rs.getMetaData();
            c=rsd.getColumnCount();
            
            DefaultTableModel d=(DefaultTableModel) cattable.getModel();
            d.setRowCount(0);
            
            while(rs.next()){
            Vector v=new Vector();
            
            for(int i=1 ;i<=c;i++){
            v.add(rs.getString("id"));
            v.add(rs.getString("name"));
            v.add(rs.getString("status"));
            
            
            }
            d.addRow(v);
            }
        }
             
     else
        {
            JOptionPane.showMessageDialog(rootPane, "No record found");
                }
             
        }
       
        catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     
     }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        catlbl = new javax.swing.JLabel();
        statuslbl = new javax.swing.JLabel();
        catnamelbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cattable = new javax.swing.JTable();
        addbtn = new javax.swing.JButton();
        catnametf = new javax.swing.JTextField();
        combotxt = new javax.swing.JComboBox<>();
        deletebtn = new javax.swing.JButton();
        updatebtn = new javax.swing.JButton();
        backbtn = new javax.swing.JButton();
        checkbtn = new javax.swing.JButton();
        searchtf = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("LIBERARY MANAGMENT SYSTEM");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        catlbl.setBackground(new java.awt.Color(0, 0, 0));
        catlbl.setFont(new java.awt.Font("Lucida Fax", 1, 44)); // NOI18N
        catlbl.setForeground(new java.awt.Color(255, 255, 255));
        catlbl.setText("Categories");
        jPanel1.add(catlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 260, 56));

        statuslbl.setBackground(new java.awt.Color(0, 0, 0));
        statuslbl.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        statuslbl.setForeground(new java.awt.Color(255, 255, 255));
        statuslbl.setText("Status");
        jPanel1.add(statuslbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 100, 30));

        catnamelbl.setBackground(new java.awt.Color(0, 0, 0));
        catnamelbl.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        catnamelbl.setForeground(new java.awt.Color(255, 255, 255));
        catnamelbl.setText("Category Name");
        jPanel1.add(catnamelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 150, 30));

        cattable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Category name", "Status"
            }
        ));
        cattable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cattableFocusLost(evt);
            }
        });
        cattable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cattableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(cattable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 500, 360));

        addbtn.setFont(new java.awt.Font("MS PGothic", 1, 16)); // NOI18N
        addbtn.setText("ADD");
        addbtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        addbtn.setMaximumSize(new java.awt.Dimension(50, 21));
        addbtn.setMinimumSize(new java.awt.Dimension(50, 21));
        addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtnActionPerformed(evt);
            }
        });
        jPanel1.add(addbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 110, 30));

        catnametf.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        catnametf.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.add(catnametf, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 90, 29));

        combotxt.setFont(new java.awt.Font("MS PGothic", 1, 16)); // NOI18N
        combotxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Deactive" }));
        combotxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        combotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combotxtActionPerformed(evt);
            }
        });
        jPanel1.add(combotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 90, 29));

        deletebtn.setFont(new java.awt.Font("MS PGothic", 1, 16)); // NOI18N
        deletebtn.setText("Delete");
        deletebtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });
        jPanel1.add(deletebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, 110, 30));

        updatebtn.setFont(new java.awt.Font("MS PGothic", 1, 16)); // NOI18N
        updatebtn.setText("Update");
        updatebtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });
        jPanel1.add(updatebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 110, 30));

        backbtn.setBackground(new java.awt.Color(0, 0, 0));
        backbtn.setFont(new java.awt.Font("MS PGothic", 1, 16)); // NOI18N
        backbtn.setForeground(new java.awt.Color(255, 255, 255));
        backbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib_managment_sys/turn-back2.png"))); // NOI18N
        backbtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });
        jPanel1.add(backbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 40));

        checkbtn.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        checkbtn.setText("check");
        checkbtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        checkbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbtnActionPerformed(evt);
            }
        });
        jPanel1.add(checkbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 90, 30));

        searchtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchtfKeyPressed(evt);
            }
        });
        jPanel1.add(searchtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 150, 30));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Search below");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void combotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combotxtActionPerformed

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        new liberarian().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backbtnActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        DefaultTableModel d1=(DefaultTableModel) cattable.getModel();
             int index=cattable.getSelectedRow();
             
       int id=Integer.parseInt( d1.getValueAt(index, 0).toString());
             
       
         try {
            pst=con.prepareStatement(" delete from category where id = ?");
        
           
            pst.setInt(1, id);
            
            int k =pst.executeUpdate();
             
              if(k==1){
       JOptionPane.showMessageDialog(rootPane, "Record deleted successfuly");
       
       catnametf.setText("");
       combotxt.setSelectedIndex(-1);
       
       catnametf.requestFocus();
       load_category();
       addbtn.setEnabled(true);
       }
       else{
       JOptionPane.showMessageDialog(rootPane, "unexpected error!");
       }
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_deletebtnActionPerformed

    private void addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtnActionPerformed
        String categoryname= catnametf.getText();
        String status=combotxt.getSelectedItem().toString();
        
         try {
            pst=con.prepareStatement("insert into category(name,status)values(?,?) ");
       
            pst.setString(1, categoryname);
            pst.setString(2, status);
            int k =pst.executeUpdate();
             
              if(k==1){
       JOptionPane.showMessageDialog(rootPane, "Category created");
       
       catnametf.setText("");
       combotxt.setSelectedIndex(-1);
       
       catnametf.requestFocus();
       load_category();
       }
       else{
       JOptionPane.showMessageDialog(rootPane, "unexpected error!");
       }
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      
       
      
        
    }//GEN-LAST:event_addbtnActionPerformed

    private void checkbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbtnActionPerformed
     
        String searchstr = searchtf.getText();

       
       
       
       if(searchstr.equalsIgnoreCase("")) {
       load_category();
       }
       else{
       load_search(searchstr);
       
       }
    }//GEN-LAST:event_checkbtnActionPerformed

    private void cattableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cattableMouseClicked
             DefaultTableModel d1=(DefaultTableModel) cattable.getModel();
             int index=cattable.getSelectedRow();
             
             int id=Integer.parseInt( d1.getValueAt(index, 0).toString());
             catnametf.setText(d1.getValueAt(index, 1).toString());
             combotxt.setSelectedItem(d1.getValueAt(index, 2).toString());
             
             addbtn.setEnabled(false);
             
             
        
    }//GEN-LAST:event_cattableMouseClicked

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
           DefaultTableModel d1=(DefaultTableModel) cattable.getModel();
             int index=cattable.getSelectedRow();
             
       int id=Integer.parseInt( d1.getValueAt(index, 0).toString());
             
        String categoryname= catnametf.getText();
        String status=combotxt.getSelectedItem().toString();
        
         try {
            pst=con.prepareStatement("update category set name = ? , status = ? where id = ? ");
        
            pst.setString(1, categoryname);
            pst.setString(2, status);
            pst.setInt(3, id);
            
            int k =pst.executeUpdate();
             
              if(k==1){
       JOptionPane.showMessageDialog(rootPane, "Category updated");
       
       catnametf.setText("");
       combotxt.setSelectedIndex(-1);
       
       catnametf.requestFocus();
       load_category();
       addbtn.setEnabled(true);
       }
       else{
       JOptionPane.showMessageDialog(rootPane, "unexpected error!");
       }
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      
             
             
    }//GEN-LAST:event_updatebtnActionPerformed

    private void cattableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cattableFocusLost
        addbtn.setEnabled(true);
    }//GEN-LAST:event_cattableFocusLost

    private void searchtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtfKeyPressed
           if(evt.getKeyCode()== KeyEvent.VK_ENTER){
        
             String searchstr=searchtf.getText();
       
       
       if(searchstr.equalsIgnoreCase("")) {
       load_category();
       }
       else{
       load_search(searchstr);
       
       }
        }
    }//GEN-LAST:event_searchtfKeyPressed

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
            java.util.logging.Logger.getLogger(Category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Category().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbtn;
    private javax.swing.JButton backbtn;
    private javax.swing.JLabel catlbl;
    private javax.swing.JLabel catnamelbl;
    private javax.swing.JTextField catnametf;
    private javax.swing.JTable cattable;
    private javax.swing.JButton checkbtn;
    private javax.swing.JComboBox<String> combotxt;
    private javax.swing.JButton deletebtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchtf;
    private javax.swing.JLabel statuslbl;
    private javax.swing.JButton updatebtn;
    // End of variables declaration//GEN-END:variables
}
