/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lib_managment_sys;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
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
import java.text.ParseException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;



import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.DatabaseMetaData;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;





/**
 *
 * @author kashi
 */
public class ReturnBook extends javax.swing.JFrame {

    /**
     * Creates new form Category
     */
    public ReturnBook() {
        initComponents();
        
     
        connect();
       
      load_returnedBooks();
        
       
        pagelbl.requestFocus();
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
     
     
      


     
     
     public void load_returnedBooks(){
         
         int c;
         
        try {
            pst=con.prepareStatement("select r.id,r.member_id,r.member_name,r.book_name,r.daysElap,f.amount from returnedBooks r JOIN fine f ON r.fine_id=f.id");
            
            rs=pst.executeQuery();
            
            ResultSetMetaData rsd=(ResultSetMetaData) rs.getMetaData();
            c=rsd.getColumnCount();
            
            DefaultTableModel d=(DefaultTableModel) bdetailstbl.getModel();
            d.setRowCount(0);
            
            while(rs.next()){
            Vector v=new Vector();
            
            for(int i=1 ;i<=c;i++){
            v.add(rs.getString("r.id"));
            v.add(rs.getString("r.member_id"));
            v.add(rs.getString("r.member_name"));
            v.add(rs.getString("r.book_name"));
            v.add(rs.getString("r.daysElap"));
             v.add(rs.getString("f.amount"));
            
            
            
            }
            d.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReturnBook.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     
     }
     
      public static java.sql.Date convertUtilToSql(java.util.Date utilDate) {
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(utilDate);
        // Get the time in milliseconds from the Calendar instance
        long timeInMillis = calendar.getTimeInMillis();
        // Create a java.sql.Date object using the time in milliseconds
        return new java.sql.Date(timeInMillis);
    }
      
         public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
     
     
         
          public void load_search(String str){
         
         int c;
         
        try {
            pst=con.prepareStatement("select r.id,r.member_id,r.member_name,r.book_name,r.daysElap,f.amount from returnedBooks r JOIN fine f ON r.fine_id=f.id where book_name like ?");
             pst.setString(1, "%" + str + "%");
            rs=pst.executeQuery();
            
            
             if(rs.isBeforeFirst()){
            ResultSetMetaData rsd=(ResultSetMetaData) rs.getMetaData();
            c=rsd.getColumnCount();
            
            DefaultTableModel d=(DefaultTableModel) bdetailstbl.getModel();
            d.setRowCount(0);
            
            while(rs.next()){
            Vector v=new Vector();
            
            for(int i=1 ;i<=c;i++){
            v.add(rs.getString("r.id"));
            v.add(rs.getString("r.member_id"));
            v.add(rs.getString("r.member_name"));
            v.add(rs.getString("r.book_name"));
            v.add(rs.getString("r.daysElap"));
             v.add(rs.getString("f.amount"));
            
            
            
            }
            d.addRow(v);
            }
             }
             else{
             JOptionPane.showMessageDialog(rootPane, "No record found");
             }
        } catch (SQLException ex) {
            Logger.getLogger(ReturnBook.class.getName()).log(Level.SEVERE, null, ex);
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
        pagelbl = new javax.swing.JLabel();
        statuslbl = new javax.swing.JLabel();
        bklbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bdetailstbl = new javax.swing.JTable();
        addbtn = new javax.swing.JButton();
        backbtn = new javax.swing.JButton();
        checkbtn = new javax.swing.JButton();
        catnamelbl1 = new javax.swing.JLabel();
        catnamelbl2 = new javax.swing.JLabel();
        finetxt = new javax.swing.JTextField();
        rdatelbl = new javax.swing.JLabel();
        mnamelbl = new javax.swing.JLabel();
        statuslbl2 = new javax.swing.JLabel();
        statuslbl3 = new javax.swing.JLabel();
        bkidtxt = new javax.swing.JTextField();
        delap = new javax.swing.JTextField();
        bnamelbl = new javax.swing.JLabel();
        midtxt = new javax.swing.JTextField();
        catnamelbl3 = new javax.swing.JLabel();
        searchtf = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("LIBERARY MANAGMENT SYSTEM");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pagelbl.setBackground(new java.awt.Color(0, 0, 0));
        pagelbl.setFont(new java.awt.Font("Lucida Fax", 1, 44)); // NOI18N
        pagelbl.setForeground(new java.awt.Color(255, 255, 255));
        pagelbl.setText("Return Book");
        jPanel1.add(pagelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 290, 80));

        statuslbl.setBackground(new java.awt.Color(0, 0, 0));
        statuslbl.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        statuslbl.setForeground(new java.awt.Color(255, 255, 255));
        statuslbl.setText("Fine");
        jPanel1.add(statuslbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 396, 70, 30));

        bklbl.setBackground(new java.awt.Color(0, 0, 0));
        bklbl.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        bklbl.setForeground(new java.awt.Color(255, 255, 255));
        bklbl.setText("Book ID");
        jPanel1.add(bklbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 80, 30));

        bdetailstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "MEMBER ID", "Member Name", "Book Name", "Days Elap", "Fine"
            }
        ));
        bdetailstbl.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                bdetailstblFocusLost(evt);
            }
        });
        bdetailstbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bdetailstblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bdetailstbl);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 530, 380));

        addbtn.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        addbtn.setText("ADD");
        addbtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtnActionPerformed(evt);
            }
        });
        jPanel1.add(addbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 460, 140, 30));

        backbtn.setBackground(new java.awt.Color(0, 0, 0));
        backbtn.setFont(new java.awt.Font("MS PGothic", 1, 16)); // NOI18N
        backbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib_managment_sys/turn-back2.png"))); // NOI18N
        backbtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });
        jPanel1.add(backbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 40));

        checkbtn.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        checkbtn.setText("check");
        checkbtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        checkbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbtnActionPerformed(evt);
            }
        });
        jPanel1.add(checkbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 110, 30));

        catnamelbl1.setBackground(new java.awt.Color(0, 0, 0));
        catnamelbl1.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        catnamelbl1.setForeground(new java.awt.Color(255, 255, 255));
        catnamelbl1.setText("Book ");
        jPanel1.add(catnamelbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 256, 70, 30));

        catnamelbl2.setBackground(new java.awt.Color(0, 0, 0));
        catnamelbl2.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        catnamelbl2.setForeground(new java.awt.Color(255, 255, 255));
        catnamelbl2.setText("Member Name");
        jPanel1.add(catnamelbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 216, 130, 30));

        finetxt.setEditable(false);
        finetxt.setBackground(new java.awt.Color(0, 0, 0));
        finetxt.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        finetxt.setForeground(new java.awt.Color(255, 255, 255));
        finetxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        finetxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finetxtActionPerformed(evt);
            }
        });
        finetxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                finetxtKeyPressed(evt);
            }
        });
        jPanel1.add(finetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 160, 29));

        rdatelbl.setBackground(new java.awt.Color(0, 0, 0));
        rdatelbl.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        rdatelbl.setForeground(new java.awt.Color(255, 255, 255));
        rdatelbl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.add(rdatelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 160, 30));

        mnamelbl.setBackground(new java.awt.Color(0, 0, 0));
        mnamelbl.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        mnamelbl.setForeground(new java.awt.Color(255, 255, 255));
        mnamelbl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.add(mnamelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 160, 30));

        statuslbl2.setBackground(new java.awt.Color(0, 0, 0));
        statuslbl2.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        statuslbl2.setForeground(new java.awt.Color(255, 255, 255));
        statuslbl2.setText("Return Date");
        jPanel1.add(statuslbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 296, 120, 30));

        statuslbl3.setBackground(new java.awt.Color(0, 0, 0));
        statuslbl3.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        statuslbl3.setForeground(new java.awt.Color(255, 255, 255));
        statuslbl3.setText("DaysElap");
        jPanel1.add(statuslbl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 346, 100, 30));

        bkidtxt.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        bkidtxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        bkidtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkidtxtActionPerformed(evt);
            }
        });
        bkidtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bkidtxtKeyPressed(evt);
            }
        });
        jPanel1.add(bkidtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 160, 29));

        delap.setEditable(false);
        delap.setBackground(new java.awt.Color(0, 0, 0));
        delap.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        delap.setForeground(new java.awt.Color(255, 255, 255));
        delap.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        delap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delapActionPerformed(evt);
            }
        });
        delap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                delapKeyPressed(evt);
            }
        });
        jPanel1.add(delap, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 160, 29));

        bnamelbl.setBackground(new java.awt.Color(0, 0, 0));
        bnamelbl.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        bnamelbl.setForeground(new java.awt.Color(255, 255, 255));
        bnamelbl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.add(bnamelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 160, 30));

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
        jPanel1.add(midtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 160, 29));

        catnamelbl3.setBackground(new java.awt.Color(0, 0, 0));
        catnamelbl3.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        catnamelbl3.setForeground(new java.awt.Color(255, 255, 255));
        catnamelbl3.setText("Member ID");
        jPanel1.add(catnamelbl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 100, 30));

        searchtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchtfKeyPressed(evt);
            }
        });
        jPanel1.add(searchtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 160, 30));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Search below");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 46, 100, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        new liberarian().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backbtnActionPerformed

    private void addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtnActionPerformed
       
      String bid=bkidtxt.getText();
      String memid=midtxt.getText();
   String memname=mnamelbl.getText();
    
     String bname=bnamelbl.getText();
     String rdate=rdatelbl.getText();
      String delp=delap.getText();
       String fine=finetxt.getText();
       
       
       
       
       
       int fineid=0;
       
     
     
       if(Integer.parseInt(fine)>0 | Integer.parseInt(fine)==0){
       
          try {
              pst=con.prepareStatement("insert into fine(member_id, amount , reason, status) values(?,?,?,?)");
              
              pst.setInt(1, Integer.parseInt(memid));
            pst.setInt(2,Integer.parseInt(fine) );
            
            if(Integer.parseInt(fine)>0){
              pst.setString(3,"late return");
             pst.setString(4, "unpaid");
            }
            else{
              pst.setString(3,"returned on time");
             pst.setString(4, "clear");
            }
          
             
        int c=     pst.executeUpdate();
        
        
       pst=con.prepareStatement("select id from fine order by id desc limit 1");
        
        rs=pst.executeQuery();
        
          if (rs.next()) {
            fineid = rs.getInt("id");
        }
          
          
          
          } catch (SQLException ex) {
              Logger.getLogger(ReturnBook.class.getName()).log(Level.SEVERE, null, ex);
          }
        
       
         
        
       
       }
       
      
    
    LocalDate currentDate = LocalDate.now();
       
        Date d=new Date();
         
       java.sql.Date sqlDate=new java.sql.Date(d.getTime());
   
      
       
         try {
            pst=con.prepareStatement("insert into returnedBooks(member_id, fine_id, member_name, book_name,daysElap,returned_date) values(?,?,?,?,?,?)");

       
            pst.setString(1, memid);
            pst.setInt(2,fineid );
            pst.setString(3, memname );
             pst.setString(4, bname);
             pst.setInt(5, Integer.parseInt(delp));
             pst.setDate(6,sqlDate) ;
             
            
            int k =pst.executeUpdate();
             
              if(k==1){
       JOptionPane.showMessageDialog(rootPane, "Book returned!");
       
      finetxt.setText("");
      mnamelbl.setText("");
     
     rdatelbl.setText("");
     delap.setText("");
     bkidtxt.setText("");
     
       
       
       pagelbl.requestFocus();
       
        
       
          
          
       pst=con.prepareStatement("delete from lendbook where member_id=? and book_id=?");
         pst.setInt(1, Integer.parseInt(memid));
        pst.setInt(2,Integer.parseInt(bid));
        
         int rowsaffected =pst.executeUpdate();
         
         load_returnedBooks();
       
       }
       else{
       JOptionPane.showMessageDialog(rootPane, "unexpected error!");
       }
        } catch (SQLException ex) {
            Logger.getLogger(ReturnBook.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        
    }//GEN-LAST:event_addbtnActionPerformed

    private void checkbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbtnActionPerformed
            
         String searchstr = searchtf.getText();

       
       
       
       if(searchstr.equalsIgnoreCase("")) {
       load_returnedBooks();
       }
       else{
       load_search(searchstr);
       
       }

    }//GEN-LAST:event_checkbtnActionPerformed

    private void bdetailstblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bdetailstblMouseClicked
             
                                             
    DefaultTableModel d1 = (DefaultTableModel) bdetailstbl.getModel();
    int index = bdetailstbl.getSelectedRow();

    // Fill member id
    bkidtxt.setText(d1.getValueAt(index, 0).toString());
    
   // int mid=Integer.parseInt(d1.getValueAt(index, 1).toString());
    
    mnamelbl.setText((d1.getValueAt(index, 2).toString()));


    try {
        String issueDateString = d1.getValueAt(index, 3).toString();
        Date issueDate = new SimpleDateFormat("yyyy-MM-dd").parse(issueDateString);
      //  idate.setDate(issueDate);
    } catch (ParseException ex) {
        // Handle parsing exception
        System.err.println("Error parsing issue date: " + ex.getMessage());
    }

    // Parse and set return date
    try {
        String returnDateString = d1.getValueAt(index, 4).toString();
        Date returnDate = new SimpleDateFormat("yyyy-MM-dd").parse(returnDateString);
      //  rdate.setDate(returnDate);
    } catch (ParseException ex) {
        // Handle parsing exception
        System.err.println("Error parsing return date: " + ex.getMessage());
    }

    // Disable add button
    addbtn.setEnabled(false);


             
             
        
    }//GEN-LAST:event_bdetailstblMouseClicked

    private void bdetailstblFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_bdetailstblFocusLost
        addbtn.setEnabled(true);
    }//GEN-LAST:event_bdetailstblFocusLost

    private void finetxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finetxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_finetxtActionPerformed

    private void finetxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_finetxtKeyPressed
        
        
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
        String mid=finetxt.getText();
        
            try {
                pst=con.prepareStatement("select * from member where mid = ?");
                pst.setString(1,mid);
                
                rs=pst.executeQuery();
                if(rs.next()==false){
                JOptionPane.showMessageDialog(this,"Member id not found");
                }
                else{
                
                String memname=rs.getString("first_name");
                mnamelbl.setText(memname.trim());
                }
            } catch (SQLException ex) {
                Logger.getLogger(ReturnBook.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
        }
    }//GEN-LAST:event_finetxtKeyPressed

    private void bkidtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkidtxtActionPerformed
     
          
//        String memberId = midtxt.getText();
//        loadBooks(Integer.parseInt(memberId));
    
    }//GEN-LAST:event_bkidtxtActionPerformed

    private void bkidtxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bkidtxtKeyPressed

        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        String id=midtxt.getText();
        String bid=bkidtxt.getText();
        try {
            pst=con.prepareStatement("SELECT m.first_name, b.TITLE, l.return_date, DATEDIFF(NOW(), l.return_date) AS elap FROM lendbook l JOIN member m ON l.member_id = m.mid JOIN books b ON l.book_id = b.ISBN WHERE l.book_id = ? and l.member_id =?");
            pst.setString(1, bid);
            pst.setString(2,id);
            
            rs=pst.executeQuery();
            
            if(rs.next()==false){
            JOptionPane.showMessageDialog(rootPane, "Member id not found or book id not found");
            
            }
            else{
            String mname=rs.getString("m.first_name");
          
             String bname=rs.getString("b.TITLE");
             String rdate=rs.getString("l.return_date");
             String elp=rs.getString("elap");
             
            
             mnamelbl.setText(mname);
            bnamelbl.setText(bname);
            rdatelbl.setText(rdate);
            delap.setText(elp);
            
            
          
            
            int elaped=Integer.parseInt(elp);
             int fine;
             if(elaped>0){
             delap.setText(elp);
                 fine=elaped*50;
                
                finetxt.setText(String.valueOf(fine));
             }else{
             
             delap.setText("0");
             finetxt.setText("0");
             }
             
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReturnBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        } 
    }//GEN-LAST:event_bkidtxtKeyPressed

    private void delapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delapActionPerformed

    private void delapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_delapKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_delapKeyPressed

    private void midtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_midtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_midtxtActionPerformed

    private void midtxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_midtxtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_midtxtKeyPressed

    private void searchtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtfKeyPressed
       
           if(evt.getKeyCode()== KeyEvent.VK_ENTER){
        
             String searchstr=searchtf.getText();
       
       
       if(searchstr.equalsIgnoreCase("")) {
       load_returnedBooks();
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
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReturnBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbtn;
    private javax.swing.JButton backbtn;
    private javax.swing.JTable bdetailstbl;
    private javax.swing.JTextField bkidtxt;
    private javax.swing.JLabel bklbl;
    private javax.swing.JLabel bnamelbl;
    private javax.swing.JLabel catnamelbl1;
    private javax.swing.JLabel catnamelbl2;
    private javax.swing.JLabel catnamelbl3;
    private javax.swing.JButton checkbtn;
    private javax.swing.JTextField delap;
    private javax.swing.JTextField finetxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField midtxt;
    private javax.swing.JLabel mnamelbl;
    private javax.swing.JLabel pagelbl;
    private javax.swing.JLabel rdatelbl;
    private javax.swing.JTextField searchtf;
    private javax.swing.JLabel statuslbl;
    private javax.swing.JLabel statuslbl2;
    private javax.swing.JLabel statuslbl3;
    // End of variables declaration//GEN-END:variables
}
