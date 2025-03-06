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
public class Publisher extends javax.swing.JFrame {

    /**
     * Creates new form Category
     */
    public Publisher() {
        initComponents();
        connect();
        load_publisher();
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
     
     
     public void load_publisher(){
         
         int c;
         
        try {
            pst=con.prepareStatement("select * from publisher");
            
            rs=pst.executeQuery();
            
            ResultSetMetaData rsd=(ResultSetMetaData) rs.getMetaData();
            c=rsd.getColumnCount();
            
            DefaultTableModel d=(DefaultTableModel) authortable.getModel();
            d.setRowCount(0);
            
            while(rs.next()){
            Vector v=new Vector();
            
            for(int i=1 ;i<=c;i++){
            v.add(rs.getString("id"));
            v.add(rs.getString("first_name"));
            v.add(rs.getString("last_name"));
            v.add(rs.getString("contact"));
            
            
            }
            d.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     
     }
     
     
      public void load_search(String str){
         
         int c;
         
        try {
            pst=con.prepareStatement("select * from publisher where first_name like ? ");
             pst.setString(1, "%" + str + "%");
            rs=pst.executeQuery();
           
             if(rs.isBeforeFirst()){
            ResultSetMetaData rsd=(ResultSetMetaData) rs.getMetaData();
            c=rsd.getColumnCount();
            
            DefaultTableModel d=(DefaultTableModel) authortable.getModel();
            d.setRowCount(0);
            
            while(rs.next()){
            Vector v=new Vector();
            
            for(int i=1 ;i<=c;i++){
            v.add(rs.getString("id"));
            v.add(rs.getString("first_name"));
            v.add(rs.getString("last_name"));
            v.add(rs.getString("contact"));
            
            
            }
            d.addRow(v);
            }
             }else{
             
             JOptionPane.showMessageDialog(rootPane, "No record found");
             }
        } catch (SQLException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
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
        authortable = new javax.swing.JTable();
        addbtn = new javax.swing.JButton();
        phonetf = new javax.swing.JTextField();
        deletebtn = new javax.swing.JButton();
        updatebtn = new javax.swing.JButton();
        backbtn = new javax.swing.JButton();
        checkbtn = new javax.swing.JButton();
        catnamelbl1 = new javax.swing.JLabel();
        lnametf = new javax.swing.JTextField();
        fnametf = new javax.swing.JTextField();
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
        catlbl.setText("Publisher");
        jPanel1.add(catlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 230, 56));

        statuslbl.setBackground(new java.awt.Color(0, 0, 0));
        statuslbl.setFont(new java.awt.Font("MS PGothic", 1, 16)); // NOI18N
        statuslbl.setForeground(new java.awt.Color(255, 255, 255));
        statuslbl.setText("Phone Number");
        jPanel1.add(statuslbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 120, 30));

        catnamelbl.setBackground(new java.awt.Color(0, 0, 0));
        catnamelbl.setFont(new java.awt.Font("MS PGothic", 1, 16)); // NOI18N
        catnamelbl.setForeground(new java.awt.Color(255, 255, 255));
        catnamelbl.setText("Last Name");
        jPanel1.add(catnamelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 90, 36));

        authortable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "phone number"
            }
        ));
        authortable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                authortableFocusLost(evt);
            }
        });
        authortable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                authortableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(authortable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, 450, 370));

        addbtn.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        addbtn.setText("ADD");
        addbtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtnActionPerformed(evt);
            }
        });
        jPanel1.add(addbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 120, 30));

        phonetf.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        phonetf.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.add(phonetf, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 160, 30));

        deletebtn.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        deletebtn.setText("Delete");
        deletebtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });
        jPanel1.add(deletebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 400, 130, 30));

        updatebtn.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        updatebtn.setText("Update");
        updatebtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });
        jPanel1.add(updatebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 120, 30));

        backbtn.setBackground(new java.awt.Color(0, 0, 0));
        backbtn.setFont(new java.awt.Font("MS PGothic", 1, 16)); // NOI18N
        backbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib_managment_sys/turn-back2.png"))); // NOI18N
        backbtn.setText("Back");
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
        jPanel1.add(checkbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 80, 30));

        catnamelbl1.setBackground(new java.awt.Color(0, 0, 0));
        catnamelbl1.setFont(new java.awt.Font("MS PGothic", 1, 16)); // NOI18N
        catnamelbl1.setForeground(new java.awt.Color(255, 255, 255));
        catnamelbl1.setText("First Name");
        jPanel1.add(catnamelbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 90, 30));

        lnametf.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        lnametf.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.add(lnametf, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 160, 30));

        fnametf.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        fnametf.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.add(fnametf, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 160, 30));

        searchtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchtfKeyPressed(evt);
            }
        });
        jPanel1.add(searchtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 140, 30));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Search below");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        new liberarian().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backbtnActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        DefaultTableModel d1=(DefaultTableModel) authortable.getModel();
             int index=authortable.getSelectedRow();
             
       int id=Integer.parseInt( d1.getValueAt(index, 0).toString());
             
       
 
        
         try {
            pst=con.prepareStatement("delete from publisher where id = ? ");
       
            
            pst.setInt(1, id);
            
            int k =pst.executeUpdate();
             
              if(k==1){
       JOptionPane.showMessageDialog(rootPane, "publisher deleted succesfuly");
       
       phonetf.setText("");
       fnametf.setText("");
       lnametf.setText("");
       
       
       fnametf.requestFocus();
       
       load_publisher();
       
       }
       else{
       JOptionPane.showMessageDialog(rootPane, "unexpected error!");
       }
        } catch (SQLException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_deletebtnActionPerformed

    private void addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtnActionPerformed
       
        
        String fname= fnametf.getText();
        String lname= lnametf.getText();
        String phone= phonetf.getText();
       
        
         try {
            pst=con.prepareStatement("insert into publisher(first_name,last_name,contact)values(?,?,?) ");
       
            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setString(3, phone);
            
            int k =pst.executeUpdate();
             
              if(k==1){
       JOptionPane.showMessageDialog(rootPane, "publisher added succesfuly");
       
       phonetf.setText("");
       fnametf.setText("");
       lnametf.setText("");
       
       
       fnametf.requestFocus();
       
       load_publisher();
       }
       else{
       JOptionPane.showMessageDialog(rootPane, "unexpected error!");
       }
        } catch (SQLException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      
       
      
        
    }//GEN-LAST:event_addbtnActionPerformed

    private void checkbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbtnActionPerformed
     
       String searchstr = searchtf.getText();

       
       
       
       if(searchstr.equalsIgnoreCase("")) {
       load_publisher();
       }
       else{
       load_search(searchstr);
       
       }
    }//GEN-LAST:event_checkbtnActionPerformed

    private void authortableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_authortableMouseClicked
             DefaultTableModel d1=(DefaultTableModel) authortable.getModel();
             int index=authortable.getSelectedRow();
             
             int id=Integer.parseInt( d1.getValueAt(index, 0).toString());
             fnametf.setText(d1.getValueAt(index, 1).toString());
             lnametf.setText(d1.getValueAt(index, 2).toString());
             phonetf.setText(d1.getValueAt(index, 3).toString());
             
             
             addbtn.setEnabled(false);
             
             
        
    }//GEN-LAST:event_authortableMouseClicked

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
           DefaultTableModel d1=(DefaultTableModel) authortable.getModel();
             int index=authortable.getSelectedRow();
             
       int id=Integer.parseInt( d1.getValueAt(index, 0).toString());
             
        String fname= fnametf.getText();
        String lname= lnametf.getText();
        String phone= phonetf.getText();
       
        
         try {
            pst=con.prepareStatement("update publisher set first_name= ?, last_name = ?,contact= ? where id= ? ");
       
            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setString(3, phone);
            pst.setInt(4, id);
            
            int k =pst.executeUpdate();
             
              if(k==1){
       JOptionPane.showMessageDialog(rootPane, "publisher updated succesfuly");
       
       phonetf.setText("");
       fnametf.setText("");
       lnametf.setText("");
       
       
       fnametf.requestFocus();
       
       load_publisher();
       }
       else{
       JOptionPane.showMessageDialog(rootPane, "unexpected error!");
       }
        } catch (SQLException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      
       
      
             
    }//GEN-LAST:event_updatebtnActionPerformed

    private void authortableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_authortableFocusLost
        addbtn.setEnabled(true);
    }//GEN-LAST:event_authortableFocusLost

    private void searchtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtfKeyPressed
        
           if(evt.getKeyCode()== KeyEvent.VK_ENTER){
        
             String searchstr=searchtf.getText();
       
       
       if(searchstr.equalsIgnoreCase("")) {
       load_publisher();
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
            java.util.logging.Logger.getLogger(Publisher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Publisher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Publisher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Publisher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Publisher().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbtn;
    private javax.swing.JTable authortable;
    private javax.swing.JButton backbtn;
    private javax.swing.JLabel catlbl;
    private javax.swing.JLabel catnamelbl;
    private javax.swing.JLabel catnamelbl1;
    private javax.swing.JButton checkbtn;
    private javax.swing.JButton deletebtn;
    private javax.swing.JTextField fnametf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lnametf;
    private javax.swing.JTextField phonetf;
    private javax.swing.JTextField searchtf;
    private javax.swing.JLabel statuslbl;
    private javax.swing.JButton updatebtn;
    // End of variables declaration//GEN-END:variables
}
