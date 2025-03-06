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
import java.text.ParseException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;




/**
 *
 * @author kashi
 */
public class IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form Category
     */
    public IssueBook() {
        initComponents();
        connect();
        book();
        load_issuebook();
        
       
        pagelbl.requestFocus();
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

    private int getBookId(String bookName) {
        int id=0;
        try {
            pst=con.prepareStatement("select ISBN from books where TITLE= ? " );
             pst.setString(1, bookName);
             rs=pst.executeQuery();
             rs.next();
             id= rs.getInt(1);
             
        } catch (SQLException ex) {
            Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return id;
        
    }
     
     
     
     public class LoadBookItem{
     
     int id;
     String name;
     
     LoadBookItem(int i,String n){
     id=i;
     name=n;
     }
          
     public String toString(){
     
     return name;
     }
     }
     
     public void book(){
          try {
            pst=con.prepareStatement("select * from books where status like \"av%\"");
            rs=pst.executeQuery();
            bcombo.removeAllItems();
            
            
            while(rs.next()){
            
            bcombo.addItem(new LoadBookItem(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        }
     
         
     }     
     
   
     
     public void load_issuebook(){
         
         int c;
         
        try {
            pst=con.prepareStatement("select l.id,m.mid,b.TITLE,l.issue_date,l.return_date from lendbook l JOIN member m ON l.member_id=m.mid Join books b ON l.book_id=b.ISBN");
            
            rs=pst.executeQuery();
            
            ResultSetMetaData rsd=(ResultSetMetaData) rs.getMetaData();
            c=rsd.getColumnCount();
            
            DefaultTableModel d=(DefaultTableModel) bdetailstbl.getModel();
            d.setRowCount(0);
            
            while(rs.next()){
            Vector v=new Vector();
            
            for(int i=1 ;i<=c;i++){
            v.add(rs.getString("l.id"));
            v.add(rs.getString("m.mid"));
            v.add(rs.getString("b.TITLE"));
            v.add(rs.getString("l.issue_date"));
            v.add(rs.getString("l.return_date"));
            
            
            
            }
            d.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     
     }
     
     
      
     public void load_search(String str){
         
         int c;
         
        try {
            pst=con.prepareStatement("select l.id,m.mid,b.TITLE,l.issue_date,l.return_date from lendbook l JOIN member m ON l.member_id=m.mid Join books b ON l.book_id=b.ISBN where b.TITLE like ?");
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
            v.add(rs.getString("l.id"));
            v.add(rs.getString("m.mid"));
            v.add(rs.getString("b.TITLE"));
            v.add(rs.getString("l.issue_date"));
            v.add(rs.getString("l.return_date"));
            
            
            
            }
            d.addRow(v);
            }
             }else{
             JOptionPane.showMessageDialog(rootPane, "No record found");
             }
        } catch (SQLException ex) {
            Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     
     }
      
     
     
     
     private String fetchMemberName(String memberId) {
    String memberName = "";

    try {
      
        String query = "SELECT first_name FROM member WHERE mid = ?";
     
        PreparedStatement pst = con.prepareStatement(query);
        
       
        pst.setString(1, memberId);
        
      
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
           
            memberName = rs.getString("first_name");
        }
        
        rs.close();
        pst.close();
    } catch (SQLException ex) {
       
        ex.printStackTrace();
    }
    
    return memberName; 
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
        catnamelbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bdetailstbl = new javax.swing.JTable();
        addbtn = new javax.swing.JButton();
        deletebtn = new javax.swing.JButton();
        updatebtn = new javax.swing.JButton();
        backbtn = new javax.swing.JButton();
        checkbtn = new javax.swing.JButton();
        catnamelbl1 = new javax.swing.JLabel();
        mnametxt = new javax.swing.JTextField();
        statuslbl1 = new javax.swing.JLabel();
        catnamelbl2 = new javax.swing.JLabel();
        bcombo = new javax.swing.JComboBox();
        rdate = new com.toedter.calendar.JDateChooser();
        midtxt = new javax.swing.JTextField();
        idate = new com.toedter.calendar.JDateChooser();
        searchtf = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("LIBERARY MANAGMENT SYSTEM");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pagelbl.setFont(new java.awt.Font("Lucida Fax", 1, 44)); // NOI18N
        pagelbl.setForeground(new java.awt.Color(255, 255, 255));
        pagelbl.setText("Issue Book");
        jPanel1.add(pagelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 260, 56));

        statuslbl.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        statuslbl.setForeground(new java.awt.Color(255, 255, 255));
        statuslbl.setText("Return Date");
        jPanel1.add(statuslbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 120, 36));

        catnamelbl.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        catnamelbl.setForeground(new java.awt.Color(255, 255, 255));
        catnamelbl.setText("Member ID");
        jPanel1.add(catnamelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 110, 36));

        bdetailstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "MEMBER ID", "Book", "Issue Date", "Return Date"
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 520, 330));

        addbtn.setFont(new java.awt.Font("MS PGothic", 1, 16)); // NOI18N
        addbtn.setText("ADD");
        addbtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtnActionPerformed(evt);
            }
        });
        jPanel1.add(addbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 120, 30));

        deletebtn.setFont(new java.awt.Font("MS PGothic", 1, 16)); // NOI18N
        deletebtn.setText("Delete");
        deletebtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });
        jPanel1.add(deletebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 450, 110, 30));

        updatebtn.setFont(new java.awt.Font("MS PGothic", 1, 16)); // NOI18N
        updatebtn.setText("Update");
        updatebtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });
        jPanel1.add(updatebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 110, 30));

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

        checkbtn.setFont(new java.awt.Font("MS PGothic", 1, 16)); // NOI18N
        checkbtn.setText("check");
        checkbtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        checkbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbtnActionPerformed(evt);
            }
        });
        jPanel1.add(checkbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 90, 30));

        catnamelbl1.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        catnamelbl1.setForeground(new java.awt.Color(255, 255, 255));
        catnamelbl1.setText("Book ");
        jPanel1.add(catnamelbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 70, 36));

        mnametxt.setFont(new java.awt.Font("MS PGothic", 1, 16)); // NOI18N
        mnametxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        mnametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnametxtActionPerformed(evt);
            }
        });
        jPanel1.add(mnametxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 130, 29));

        statuslbl1.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        statuslbl1.setForeground(new java.awt.Color(255, 255, 255));
        statuslbl1.setText("Date");
        jPanel1.add(statuslbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 80, 36));

        catnamelbl2.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        catnamelbl2.setForeground(new java.awt.Color(255, 255, 255));
        catnamelbl2.setText("Member Name");
        jPanel1.add(catnamelbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 150, 36));

        bcombo.setFont(new java.awt.Font("MS PGothic", 1, 16)); // NOI18N
        bcombo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        bcombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcomboActionPerformed(evt);
            }
        });
        jPanel1.add(bcombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 130, 30));

        rdate.setBackground(java.awt.Color.white);
        rdate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.add(rdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 130, 30));

        midtxt.setFont(new java.awt.Font("MS PGothic", 1, 16)); // NOI18N
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
        jPanel1.add(midtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 130, 29));

        idate.setBackground(java.awt.Color.white);
        idate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.add(idate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 130, 30));

        searchtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchtfKeyPressed(evt);
            }
        });
        jPanel1.add(searchtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 170, 30));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Search below");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 882, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        new liberarian().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backbtnActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        DefaultTableModel d1=(DefaultTableModel) bdetailstbl.getModel();
             int index=bdetailstbl.getSelectedRow();
             
       int id=Integer.parseInt( d1.getValueAt(index, 0).toString());
             
       
 
        
         try {
            pst=con.prepareStatement("delete from lendbook where id = ? ");
       
            
            pst.setInt(1, id);
            
            int k =pst.executeUpdate();
             
              if(k==1){
       JOptionPane.showMessageDialog(rootPane, "Book deleted succesfuly");
       
       midtxt.setText("");
       mnametxt.setText("");
       bcombo.setSelectedItem(-1);
       rdate.setDateFormatString("");
        idate.setDateFormatString("");
       
       
       
       midtxt.requestFocus();
       
      load_issuebook();
       
       }
       else{
       JOptionPane.showMessageDialog(rootPane, "unexpected error!");
       }
        } catch (SQLException ex) {
            Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_deletebtnActionPerformed

    private void addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtnActionPerformed
       
        
       String memid=midtxt.getText();
    //   String memname=mnametxt.getText();
     
       LoadBookItem book=(LoadBookItem) bcombo.getSelectedItem();
       
       SimpleDateFormat date_formate=new SimpleDateFormat("yyyy-MM-dd");
       String issuedate=date_formate.format(idate.getDate());
       
       SimpleDateFormat date_format=new SimpleDateFormat("yyyy-MM-dd");
       String returndate=date_format.format(rdate.getDate());
       
       
        
         try {
            pst=con.prepareStatement("insert into lendbook(member_id, book_id, issue_date, return_date) values(?,?,?,?)");

       
            pst.setString(1, memid);
            pst.setInt(2,book.id );
            pst.setString(3, issuedate );
             pst.setString(4, returndate);
             
            
            int k =pst.executeUpdate();
             
              if(k==1){
       JOptionPane.showMessageDialog(rootPane, "Book issued!");
       
      midtxt.setText("");
      mnametxt.setText("");
      bcombo.setSelectedItem(-1);
     
       
       
       pagelbl.requestFocus();
       
       //load_book();
       }
       else{
       JOptionPane.showMessageDialog(rootPane, "unexpected error!");
       }
        } catch (SQLException ex) {
            Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       load_issuebook();
       
      
        
    }//GEN-LAST:event_addbtnActionPerformed

    private void bdetailstblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bdetailstblMouseClicked
             
                                             
    DefaultTableModel d1 = (DefaultTableModel) bdetailstbl.getModel();
    int index = bdetailstbl.getSelectedRow();

   
    midtxt.setText(d1.getValueAt(index, 1).toString());
    
   
    
    mnametxt.setText(fetchMemberName(d1.getValueAt(index, 1).toString()));

    
    String bookTitle = d1.getValueAt(index, 2).toString();
    
    for (int i = 0; i < bcombo.getItemCount(); i++) {
        LoadBookItem item = (LoadBookItem) bcombo.getItemAt(i);
        if (item.name.equals(bookTitle)) {
            bcombo.setSelectedItem(item);
            break;
        }
    }

    
    try {
        String issueDateString = d1.getValueAt(index, 3).toString();
        Date issueDate = new SimpleDateFormat("yyyy-MM-dd").parse(issueDateString);
        idate.setDate(issueDate);
    } catch (ParseException ex) {
        // Handle parsing exception
        System.err.println("Error parsing issue date: " + ex.getMessage());
    }

   
    try {
        String returnDateString = d1.getValueAt(index, 4).toString();
        Date returnDate = new SimpleDateFormat("yyyy-MM-dd").parse(returnDateString);
        rdate.setDate(returnDate);
    } catch (ParseException ex) {
        
        System.err.println("Error parsing return date: " + ex.getMessage());
    }

  
    addbtn.setEnabled(false);


             
             
        
    }//GEN-LAST:event_bdetailstblMouseClicked

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
           DefaultTableModel d1=(DefaultTableModel) bdetailstbl.getModel();
             int index=bdetailstbl.getSelectedRow();
             
       int id=Integer.parseInt( d1.getValueAt(index, 0).toString());
       
       
      int memberid=Integer.parseInt(d1.getValueAt(index, 1).toString());
      String bookName =  bcombo.getSelectedItem().toString();
      int bookId = getBookId(bookName);
     
    
      
       String issueDateString = d1.getValueAt(index, 3).toString();
       String returnDateString = d1.getValueAt(index, 4).toString();
       
        Date issueDate = null;
        Date returnDate = null;
        try {
           issueDate = new SimpleDateFormat("yyyy-MM-dd").parse(issueDateString);
            returnDate = new SimpleDateFormat("yyyy-MM-dd").parse(issueDateString);
        } catch (ParseException ex) {
            Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
       java.sql.Date sqlDate=new java.sql.Date(issueDate.getTime());
       java.sql.Date redate=new java.sql.Date(returnDate.getTime());
       
       
        
         try {
            pst=con.prepareStatement("update lendbook set member_id= ?, book_id = ?, issue_date= ?, return_date = ? where id= ? ");
       
            pst.setInt(1, memberid);
            pst.setInt(2, bookId);
            pst.setDate(3, sqlDate);
            pst.setDate(4, redate);
            pst.setInt(5, id);
            
            int k =pst.executeUpdate();
             
              if(k==1){
       JOptionPane.showMessageDialog(rootPane, " updated succesfuly");
       
       midtxt.setText("");
       mnametxt.setText("");
       bcombo.setSelectedItem(-1);
       idate.setDateFormatString("");
       rdate.setDateFormatString("");
       
       
       midtxt.requestFocus();
       
       load_issuebook();
       }
       else{
       JOptionPane.showMessageDialog(rootPane, "unexpected error!");
       }
        } catch (SQLException ex) {
            Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      
       
      
             
    }//GEN-LAST:event_updatebtnActionPerformed

    private void bdetailstblFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_bdetailstblFocusLost
        addbtn.setEnabled(true);
    }//GEN-LAST:event_bdetailstblFocusLost

    private void mnametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnametxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnametxtActionPerformed

    private void midtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_midtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_midtxtActionPerformed

    private void midtxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_midtxtKeyPressed
        
        
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
        String mid=midtxt.getText();
        
            try {
                pst=con.prepareStatement("select * from member where mid = ?");
                pst.setString(1,mid);
                
                rs=pst.executeQuery();
                if(rs.next()==false){
                JOptionPane.showMessageDialog(this,"Member id not found");
                }
                else{
                
                String memname=rs.getString("first_name");
                mnametxt.setText(memname.trim());
                }
            } catch (SQLException ex) {
                Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
        }
    }//GEN-LAST:event_midtxtKeyPressed

    private void bcomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcomboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bcomboActionPerformed

    private void checkbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbtnActionPerformed
             String searchstr = searchtf.getText();

       
       
       
       if(searchstr.equalsIgnoreCase("")) {
       load_issuebook();
       }
       else{
       load_search(searchstr);
       
       }
    }//GEN-LAST:event_checkbtnActionPerformed

    private void searchtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtfKeyPressed
          if(evt.getKeyCode()== KeyEvent.VK_ENTER){
        
             String searchstr=searchtf.getText();
       
       
       if(searchstr.equalsIgnoreCase("")) {
       load_issuebook();
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
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbtn;
    private javax.swing.JButton backbtn;
    private javax.swing.JComboBox bcombo;
    private javax.swing.JTable bdetailstbl;
    private javax.swing.JLabel catnamelbl;
    private javax.swing.JLabel catnamelbl1;
    private javax.swing.JLabel catnamelbl2;
    private javax.swing.JButton checkbtn;
    private javax.swing.JButton deletebtn;
    private com.toedter.calendar.JDateChooser idate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField midtxt;
    private javax.swing.JTextField mnametxt;
    private javax.swing.JLabel pagelbl;
    private com.toedter.calendar.JDateChooser rdate;
    private javax.swing.JTextField searchtf;
    private javax.swing.JLabel statuslbl;
    private javax.swing.JLabel statuslbl1;
    private javax.swing.JButton updatebtn;
    // End of variables declaration//GEN-END:variables
}
