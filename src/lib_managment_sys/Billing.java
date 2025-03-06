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
public class Billing extends javax.swing.JFrame {

    /**
     * Creates new form Category
     */
    public Billing() {
        initComponents();
        connect();
        
        billinglbl.requestFocus();
         Image icon=new ImageIcon(this.getClass().getResource("/library.png")).getImage();
        this.setIconImage(icon);
    }

        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        
        
     public void connect() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // JDBC URL, username, and password
            String url = "jdbc:mysql://localhost:3306/lab_managment";
            String user = "root";
            String password = "MySQL";

            // Create the connection
            con = DriverManager.getConnection(url, user, password);
            
            
        
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
     
     
     public void load_data(int id){
         
         int c;
         
         
        try {
            pst=con.prepareStatement(" select distinct  f.id ,f.amount,f.status,r.returned_date from fine f left outer join returnedBooks r on f.id=r.fine_id where f.member_id = ? and status = \"unpaid\" and returned_date is not null");
            pst.setInt(1, id);
            rs=pst.executeQuery();
            
            ResultSetMetaData rsd=(ResultSetMetaData) rs.getMetaData();
            c=rsd.getColumnCount();
            
            DefaultTableModel d=(DefaultTableModel) finetbl.getModel();
            d.setRowCount(0);
            
            while(rs.next()){
            Vector v=new Vector();
            
            for(int i=1 ;i<=c;i++){
            v.add(rs.getString("f.id"));
            v.add(rs.getString("f.amount"));
            v.add(rs.getString("f.status"));
            v.add(rs.getString("r.returned_date"));
            
            
            }
            d.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     
     }
     
     public int totalamount(int mid){
         
         int tamount=0;
     
        try {
            pst=con.prepareStatement(" select sum(amount)as total from fine where member_id=? and status=\"unpaid\"");
             pst.setInt(1,mid);
             
             rs=pst.executeQuery();
             while(rs.next()){
             
                 tamount=rs.getInt("total");
             }
             
        } catch (SQLException ex) {
            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tamount; 
     }
     
       public String name(int mid){
         
         String name="";
     
        try {
            pst=con.prepareStatement(" select distinct member_name from returnedBooks where member_id=?");
             pst.setInt(1,mid);
             
             rs=pst.executeQuery();
             while(rs.next()){
             
                 name=rs.getString("member_name");
             }
             
        } catch (SQLException ex) {
            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return name; 
     }
     
       
      public boolean searchid(int id){
      
      
        try {
            pst=con.prepareStatement(" select * from lendbook where member_id=?");
             pst.setInt(1,id);
             
             rs=pst.executeQuery();
             if(rs.next()){
             return true;
             }else{
             return false;
             }
             
        } catch (SQLException ex) {
            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
      
      }
      
      
       public boolean searchmember(int id){
      
      
        try {
            pst=con.prepareStatement(" select * from member where mid=?");
             pst.setInt(1,id);
             
             rs=pst.executeQuery();
             if(rs.next()){
             return true;
             }else{
             return false;
             }
             
        } catch (SQLException ex) {
            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
      
      }
      
      
     
     
     //last work not done trying to enable search;
     
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        billinglbl = new javax.swing.JLabel();
        catnamelbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        finetbl = new javax.swing.JTable();
        paybtn = new javax.swing.JButton();
        backbtn = new javax.swing.JButton();
        catnamelbl1 = new javax.swing.JLabel();
        mnametxt = new javax.swing.JTextField();
        midtxt = new javax.swing.JTextField();
        catnamelbl2 = new javax.swing.JLabel();
        tamount = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("LIBERARY MANAGMENT SYSTEM");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        billinglbl.setBackground(new java.awt.Color(0, 0, 0));
        billinglbl.setFont(new java.awt.Font("Lucida Fax", 1, 48)); // NOI18N
        billinglbl.setForeground(new java.awt.Color(255, 255, 255));
        billinglbl.setText("Billing");
        jPanel1.add(billinglbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 200, 56));

        catnamelbl.setBackground(new java.awt.Color(0, 0, 0));
        catnamelbl.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        catnamelbl.setForeground(new java.awt.Color(255, 255, 255));
        catnamelbl.setText("Total Amount");
        jPanel1.add(catnamelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 130, 30));

        finetbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Amount", "Status", "Returned Date"
            }
        ));
        finetbl.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                finetblFocusLost(evt);
            }
        });
        finetbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                finetblMouseClicked(evt);
            }
        });
        finetbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                finetblKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(finetbl);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 470, 380));

        paybtn.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        paybtn.setText("PAY");
        paybtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        paybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paybtnActionPerformed(evt);
            }
        });
        jPanel1.add(paybtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 130, 40));

        backbtn.setBackground(new java.awt.Color(0, 0, 0));
        backbtn.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        backbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib_managment_sys/turn-back2.png"))); // NOI18N
        backbtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });
        jPanel1.add(backbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 40));

        catnamelbl1.setBackground(new java.awt.Color(0, 0, 0));
        catnamelbl1.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        catnamelbl1.setForeground(new java.awt.Color(255, 255, 255));
        catnamelbl1.setText("Member name");
        jPanel1.add(catnamelbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 130, 30));

        mnametxt.setEditable(false);
        mnametxt.setBackground(new java.awt.Color(255, 255, 255));
        mnametxt.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        mnametxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.add(mnametxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 140, 29));

        midtxt.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        midtxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        midtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                midtxtActionPerformed(evt);
            }
        });
        midtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                midtxtKeyPressed(evt);
            }
        });
        jPanel1.add(midtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 140, 29));

        catnamelbl2.setBackground(new java.awt.Color(0, 0, 0));
        catnamelbl2.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        catnamelbl2.setForeground(new java.awt.Color(255, 255, 255));
        catnamelbl2.setText("Member ID");
        jPanel1.add(catnamelbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 130, 30));

        tamount.setEditable(false);
        tamount.setBackground(new java.awt.Color(255, 255, 255));
        tamount.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        tamount.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.add(tamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 140, 29));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        new liberarian().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backbtnActionPerformed

    private void paybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paybtnActionPerformed
       
        
           String mid=midtxt.getText();
           int id=Integer.parseInt(mid);
       
        
         try {
            pst=con.prepareStatement("  update fine set status=\"clear\" where member_id=? ");
       
            pst.setInt(1, id);
           
            
            int a =pst.executeUpdate();
             
              if(a>0){
       JOptionPane.showMessageDialog(rootPane, "Paid Successfuly");
       
       midtxt.setText("");
      
       
       midtxt.requestFocus();
       
       
       }
       else{
       JOptionPane.showMessageDialog(rootPane, "unexpected error!");
       }
        } catch (SQLException ex) {
            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      
       
      
        
    }//GEN-LAST:event_paybtnActionPerformed

    private void finetblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_finetblMouseClicked
     
        
    }//GEN-LAST:event_finetblMouseClicked

    private void finetblFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_finetblFocusLost
        paybtn.setEnabled(true);
    }//GEN-LAST:event_finetblFocusLost

    private void midtxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_midtxtKeyPressed
        
          if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            
              String mid=midtxt.getText();
              int id=Integer.parseInt(mid);
        
          if(searchmember(id)) {  
           load_data(id);
           int k=totalamount(id);
           
           
          mnametxt.setText(name(id));
           
          if(name(id).equalsIgnoreCase("")){
          JOptionPane.showMessageDialog(rootPane, "Member never returned any book");
          
          }
         
           if (searchid(id)){
          JOptionPane.showMessageDialog(rootPane, "Member has to return book");
           tamount.setText(String.valueOf(k));
          }
           else{
           tamount.setText(String.valueOf(k));
           }
        
          }else{
          
             JOptionPane.showMessageDialog(rootPane, "member with this id does not exists");
      }
        
        
        }
    
    }//GEN-LAST:event_midtxtKeyPressed

    private void finetblKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_finetblKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_finetblKeyPressed

    private void midtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_midtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_midtxtActionPerformed

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
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Billing().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backbtn;
    private javax.swing.JLabel billinglbl;
    private javax.swing.JLabel catnamelbl;
    private javax.swing.JLabel catnamelbl1;
    private javax.swing.JLabel catnamelbl2;
    private javax.swing.JTable finetbl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField midtxt;
    private javax.swing.JTextField mnametxt;
    private javax.swing.JButton paybtn;
    private javax.swing.JTextField tamount;
    // End of variables declaration//GEN-END:variables

 
}
