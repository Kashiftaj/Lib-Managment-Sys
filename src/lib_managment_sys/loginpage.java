/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lib_managment_sys;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author kashi
 */
public class loginpage extends javax.swing.JFrame {


   

   
    
    public loginpage() {
        initComponents();
        displaylbl.requestFocusInWindow();
        connect();
        
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
    
     public boolean searchmember(String user,String pass){
      
      
        try {
            pst=con.prepareStatement(" select * from logindetails where username=? and password= ?");
             pst.setString(1,user);
             pst.setString(2,pass);
             
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
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginpnl = new javax.swing.JPanel();
        usernamelbl = new javax.swing.JLabel();
        passwordlbl = new javax.swing.JLabel();
        usernametf = new javax.swing.JTextField();
        passwordf = new javax.swing.JPasswordField();
        loginbtn = new javax.swing.JButton();
        displaylbl = new javax.swing.JLabel();
        exitbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("LIBERARY MANAGMENT SYSTEM");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImages(null);
        setResizable(false);
        setSize(new java.awt.Dimension(1080, 1080));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginpnl.setBackground(new java.awt.Color(255, 255, 255));
        loginpnl.setForeground(new java.awt.Color(255, 255, 255));

        usernamelbl.setFont(new java.awt.Font("Lucida Fax", 1, 44)); // NOI18N
        usernamelbl.setText("Welcome Back!");

        passwordlbl.setFont(new java.awt.Font("Lucida Fax", 0, 18)); // NOI18N
        passwordlbl.setText("Please Enter Log in Details Below");

        usernametf.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        usernametf.setText("Enter your username");
        usernametf.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 255, 255), 2, true));
        usernametf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernametfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernametfFocusLost(evt);
            }
        });
        usernametf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usernametfMouseClicked(evt);
            }
        });
        usernametf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernametfActionPerformed(evt);
            }
        });
        usernametf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernametfKeyPressed(evt);
            }
        });

        passwordf.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        passwordf.setText("password");
        passwordf.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 255, 255), 2, true));
        passwordf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordfFocusGained(evt);
            }
        });
        passwordf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordfMouseClicked(evt);
            }
        });
        passwordf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordfKeyPressed(evt);
            }
        });

        loginbtn.setBackground(new java.awt.Color(0, 0, 0));
        loginbtn.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        loginbtn.setForeground(new java.awt.Color(255, 255, 255));
        loginbtn.setText("Login");
        loginbtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        loginbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        loginbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbtnActionPerformed(evt);
            }
        });

        displaylbl.setBackground(new java.awt.Color(51, 255, 255));
        displaylbl.setFont(new java.awt.Font("MS PGothic", 1, 36)); // NOI18N
        displaylbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib_managment_sys/icon.jpg"))); // NOI18N
        displaylbl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        exitbtn.setBackground(new java.awt.Color(0, 0, 0));
        exitbtn.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        exitbtn.setForeground(new java.awt.Color(255, 255, 255));
        exitbtn.setText("Exit");
        exitbtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        exitbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitbtnActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib_managment_sys/login.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib_managment_sys/lockf.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib_managment_sys/user.png"))); // NOI18N
        jLabel3.setText("jLabel2");
        jLabel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        javax.swing.GroupLayout loginpnlLayout = new javax.swing.GroupLayout(loginpnl);
        loginpnl.setLayout(loginpnlLayout);
        loginpnlLayout.setHorizontalGroup(
            loginpnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginpnlLayout.createSequentialGroup()
                .addGroup(loginpnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginpnlLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(exitbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(loginpnlLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(loginpnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginpnlLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(loginpnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(usernametf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                                    .addComponent(passwordf, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(passwordlbl)
                            .addComponent(usernamelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(displaylbl, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loginbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 29, Short.MAX_VALUE))
        );
        loginpnlLayout.setVerticalGroup(
            loginpnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginpnlLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(loginpnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginpnlLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14))
                    .addGroup(loginpnlLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(displaylbl, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(usernamelbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addGroup(loginpnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usernametf, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(41, 41, 41)
                        .addGroup(loginpnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(36, 36, 36)
                        .addComponent(loginbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exitbtn)
                        .addGap(32, 32, 32))))
        );

        getContentPane().add(loginpnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 570));

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 710, 490));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void usernametfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernametfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernametfActionPerformed
        
    

    @Override
    public void setIconImage(Image image) {
        super.setIconImage(image); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
   

    private void loginbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbtnActionPerformed
        
        String user=usernametf.getText();
       String pass=new String(passwordf.getPassword());
       
       
       
       if(searchmember(user,pass)){
        
    
            new liberarian().setVisible(true);
             this.dispose();
           
             
        }
        else{
       
        JOptionPane.showMessageDialog(rootPane, "please check your username or password");
        usernametf.setText("");
        passwordf.setText("");
        usernametf.requestFocus();
        
        }
            
        
        
    }//GEN-LAST:event_loginbtnActionPerformed

    private void usernametfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernametfMouseClicked
        usernametf.setText("");
    }//GEN-LAST:event_usernametfMouseClicked

    private void usernametfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernametfKeyPressed
       
    }//GEN-LAST:event_usernametfKeyPressed

    private void usernametfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernametfFocusGained
        usernametf.setText("");
    }//GEN-LAST:event_usernametfFocusGained

    private void passwordfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordfMouseClicked
        
    }//GEN-LAST:event_passwordfMouseClicked

    private void passwordfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordfFocusGained
       passwordf.setText("");
    }//GEN-LAST:event_passwordfFocusGained

    private void exitbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitbtnActionPerformed
       int option= JOptionPane.showConfirmDialog(rootPane, "are you sure!", "Exit confirmation", JOptionPane.YES_NO_OPTION);
       
       if(option==JOptionPane.YES_OPTION){
       this.dispose();
       }
       
       
    }//GEN-LAST:event_exitbtnActionPerformed

    private void usernametfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernametfFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_usernametfFocusLost

    private void passwordfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordfKeyPressed
        
        
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
        
          String user=usernametf.getText();
       String pass=new String(passwordf.getPassword());
       
       
       
       if(searchmember(user,pass)){
        
    
           // JOptionPane.showMessageDialog(rootPane, "Login Successfully");
           
            new liberarian().setVisible(true);
             this.dispose();
           
             
        }
        else{
       
        JOptionPane.showMessageDialog(rootPane, "please  correct username or password");
        usernametf.setText("");
        passwordf.setText("");
        usernametf.requestFocus();
        
        }
        
        }
    }//GEN-LAST:event_passwordfKeyPressed

  
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginpage().setVisible(true);
            }
        });
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JLabel displaylbl;
    private javax.swing.JButton exitbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginbtn;
    private javax.swing.JPanel loginpnl;
    private javax.swing.JPasswordField passwordf;
    private javax.swing.JLabel passwordlbl;
    private javax.swing.JLabel usernamelbl;
    private javax.swing.JTextField usernametf;
    // End of variables declaration//GEN-END:variables
}
