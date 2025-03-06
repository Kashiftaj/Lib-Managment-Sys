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
public class Books extends javax.swing.JFrame {

    /**
     * Creates new form Category
     */
    public Books() {
        initComponents();
        connect();
        category();
        author();
        publisher();
        load_book();
        catlbl.requestFocus();
         Image icon=new ImageIcon(this.getClass().getResource("/library.png")).getImage();
        this.setIconImage(icon);
    }
    
    
    
    public class LoadCategoryItem{
    int id;
    String name;
    
   public  LoadCategoryItem(int id,String name){
   this.id=id;
   this.name=name;
   }
    
   
   public String toString(){
   return name;
   }
      
    }
    
     public class LoadAuthorItem{
    int id;
    String name;
    
   public  LoadAuthorItem(int id,String name){
   this.id=id;
   this.name=name;
   }
    
   
   public String toString(){
   return name;
   }
      
    }
     
     public class LoadPublisherItem{
    int id;
    String name;
    
   public  LoadPublisherItem(int id,String name){
   this.id=id;
   this.name=name;
   }
    
   
   public String toString(){
   return name;
   }
      
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
     
     
     public void category(){
     
        try {
            pst=con.prepareStatement("select * from category where status like \"Ac%\"");
            rs=pst.executeQuery();
            catcombo.removeAllItems();
            
            
            while(rs.next()){
            
            catcombo.addItem(new LoadCategoryItem(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     }
     
      public void author(){
     
        try {
            pst=con.prepareStatement("select * from author");
            rs=pst.executeQuery();
            acombo.removeAllItems();
            
            
            while(rs.next()){
            
            acombo.addItem(new LoadAuthorItem(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     }
      
      public void publisher(){
     
        try {
            pst=con.prepareStatement("select * from publisher");
            rs=pst.executeQuery();
            pcombo.removeAllItems();
            
            
            while(rs.next()){
            
            pcombo.addItem(new LoadPublisherItem(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     }
     
     public void load_book(){
         
         int c;
         
        try {
            pst=con.prepareStatement("select b.ISBN,b.TITLE,b.EDDITION,c.name,a.first_name,p.first_name,b.PUBLISH_YEAR,b.status from books b JOIN category c ON b.category_id=c.id JOIN author a ON b.AUTHOR_ID=a.id JOIN publisher p ON b.pid=p.id");
            
            rs=pst.executeQuery();
            
            ResultSetMetaData rsd=(ResultSetMetaData) rs.getMetaData();
            c=rsd.getColumnCount();
            
            DefaultTableModel d=(DefaultTableModel) bdetailstbl.getModel();
            d.setRowCount(0);
            
            while(rs.next()){
            Vector v=new Vector();
            
            for(int i=1 ;i<=c;i++){
            v.add(rs.getString("b.ISBN"));
            v.add(rs.getString("b.TITLE"));
            v.add(rs.getString("b.EDDITION"));
            v.add(rs.getString("c.name"));
            v.add(rs.getString("a.first_name"));
            v.add(rs.getString("p.first_name"));
            v.add(rs.getString("b.PUBLISH_YEAR"));
            v.add(rs.getString("b.status"));
            
            
            }
            d.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     
     }
     
     
     
     
     //last work not done trying to enable search;
     
    public void loadSearch(String str){
    
       try {
    pst = con.prepareStatement("select b.ISBN,b.TITLE,b.EDDITION,c.name,a.first_name,p.first_name,b.PUBLISH_YEAR,b.status from books b JOIN category c ON b.category_id=c.id JOIN author a ON b.AUTHOR_ID=a.id JOIN publisher p ON b.pid=p.id where b.TITLE like ?");
    pst.setString(1, "%" + str + "%"); 
    
     rs = pst.executeQuery();
    int c;
    
    if (rs.isBeforeFirst()) {
     
        
        ResultSetMetaData rsd=(ResultSetMetaData) rs.getMetaData();
            c=rsd.getColumnCount();
            
            DefaultTableModel d=(DefaultTableModel) bdetailstbl.getModel();
            d.setRowCount(0);
            
            while(rs.next()){
            Vector v=new Vector();
            
            for(int i=1 ;i<=c;i++){
            v.add(rs.getString("b.ISBN"));
            v.add(rs.getString("b.TITLE"));
            v.add(rs.getString("b.EDDITION"));
            v.add(rs.getString("c.name"));
            v.add(rs.getString("a.first_name"));
            v.add(rs.getString("p.first_name"));
            v.add(rs.getString("b.PUBLISH_YEAR"));
            v.add(rs.getString("b.status"));
            
            
            }
            d.addRow(v);
            }
        
          statuscombo.setSelectedIndex(-1);
       bnametf.setText("");
       editiontf.setText("");
       pyeartf.setText("");
       
       catcombo.setSelectedIndex(-1);
       acombo.setSelectedIndex(-1);
       pcombo.setSelectedIndex(-1);
       
       
       bnametf.requestFocus();
    } else {
        JOptionPane.showMessageDialog(rootPane, "No record found");
    }
} catch (SQLException ex) {
    Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
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
        catlbl = new javax.swing.JLabel();
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
        editiontf = new javax.swing.JTextField();
        bnametf = new javax.swing.JTextField();
        statuslbl1 = new javax.swing.JLabel();
        pyeartf = new javax.swing.JTextField();
        statuscombo = new javax.swing.JComboBox<>();
        catnamelbl2 = new javax.swing.JLabel();
        catnamelbl3 = new javax.swing.JLabel();
        catnamelbl4 = new javax.swing.JLabel();
        pcombo = new javax.swing.JComboBox();
        catcombo = new javax.swing.JComboBox();
        acombo = new javax.swing.JComboBox();
        searchtxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("LIBERARY MANAGMENT SYSTEM");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        catlbl.setBackground(new java.awt.Color(0, 0, 0));
        catlbl.setFont(new java.awt.Font("Lucida Fax", 1, 44)); // NOI18N
        catlbl.setForeground(new java.awt.Color(255, 255, 255));
        catlbl.setText("Books");
        jPanel1.add(catlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 160, 50));

        statuslbl.setBackground(new java.awt.Color(0, 0, 0));
        statuslbl.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        statuslbl.setForeground(new java.awt.Color(255, 255, 255));
        statuslbl.setText("Status");
        jPanel1.add(statuslbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 60, 30));

        catnamelbl.setBackground(new java.awt.Color(0, 0, 0));
        catnamelbl.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        catnamelbl.setForeground(new java.awt.Color(255, 255, 255));
        catnamelbl.setText("Edition");
        jPanel1.add(catnamelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 80, 30));

        bdetailstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Book Name", "Edition", "Category", "Author", "Publisher", "Publish year", "Status"
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
        bdetailstbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bdetailstblKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(bdetailstbl);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 570, 450));

        addbtn.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        addbtn.setText("ADD");
        addbtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtnActionPerformed(evt);
            }
        });
        jPanel1.add(addbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 100, 30));

        deletebtn.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        deletebtn.setText("Delete");
        deletebtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });
        jPanel1.add(deletebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, 100, 30));

        updatebtn.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        updatebtn.setText("Update");
        updatebtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });
        jPanel1.add(updatebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 480, 100, 30));

        backbtn.setBackground(new java.awt.Color(0, 0, 0));
        backbtn.setFont(new java.awt.Font("MS PGothic", 1, 14)); // NOI18N
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
        jPanel1.add(checkbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 80, 30));

        catnamelbl1.setBackground(new java.awt.Color(0, 0, 0));
        catnamelbl1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        catnamelbl1.setForeground(new java.awt.Color(255, 255, 255));
        catnamelbl1.setText("Publisher");
        jPanel1.add(catnamelbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 80, 30));

        editiontf.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        editiontf.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        editiontf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editiontfActionPerformed(evt);
            }
        });
        jPanel1.add(editiontf, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 130, 29));

        bnametf.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        bnametf.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.add(bnametf, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 130, 30));

        statuslbl1.setBackground(new java.awt.Color(0, 0, 0));
        statuslbl1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        statuslbl1.setForeground(new java.awt.Color(255, 255, 255));
        statuslbl1.setText("Publish Year");
        jPanel1.add(statuslbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 100, 30));

        pyeartf.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        pyeartf.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.add(pyeartf, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 130, 29));

        statuscombo.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        statuscombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not available" }));
        statuscombo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        statuscombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statuscomboActionPerformed(evt);
            }
        });
        jPanel1.add(statuscombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 130, 30));

        catnamelbl2.setBackground(new java.awt.Color(0, 0, 0));
        catnamelbl2.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        catnamelbl2.setForeground(new java.awt.Color(255, 255, 255));
        catnamelbl2.setText("Book Name");
        jPanel1.add(catnamelbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 115, 90, 36));

        catnamelbl3.setBackground(new java.awt.Color(0, 0, 0));
        catnamelbl3.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        catnamelbl3.setForeground(new java.awt.Color(255, 255, 255));
        catnamelbl3.setText(" Category");
        jPanel1.add(catnamelbl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 80, 30));

        catnamelbl4.setBackground(new java.awt.Color(0, 0, 0));
        catnamelbl4.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        catnamelbl4.setForeground(new java.awt.Color(255, 255, 255));
        catnamelbl4.setText("Author");
        jPanel1.add(catnamelbl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 60, 30));

        pcombo.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        pcombo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.add(pcombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, 130, 30));

        catcombo.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        catcombo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.add(catcombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 130, 30));

        acombo.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        acombo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.add(acombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, 130, 30));

        searchtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchtxtKeyPressed(evt);
            }
        });
        jPanel1.add(searchtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 140, 30));

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Search Below");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 36, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
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
            pst=con.prepareStatement("delete from books where ISBN= ? ");
       
            
            pst.setInt(1, id);
            
            int k =pst.executeUpdate();
             
              if(k==1){
       JOptionPane.showMessageDialog(rootPane, "Book deleted succesfuly");
       
       statuscombo.setSelectedItem(-1);
       bnametf.setText("");
       editiontf.setText("");
         pyeartf.setText("");
       
       
       
       bnametf.requestFocus();
       
       load_book();
       
       }
       else{
       JOptionPane.showMessageDialog(rootPane, "unexpected error!");
       }
        } catch (SQLException ex) {
            Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_deletebtnActionPerformed

    private void addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtnActionPerformed
       
        
        String bname= bnametf.getText();
        String edition= editiontf.getText();
        String pyear= pyeartf.getText();
        String status= statuscombo.getSelectedItem().toString();
        
        LoadCategoryItem cat=(LoadCategoryItem) catcombo.getSelectedItem();
        LoadAuthorItem auth=(LoadAuthorItem) acombo.getSelectedItem();
        LoadPublisherItem publisher=(LoadPublisherItem) pcombo.getSelectedItem();
       
       
        if(!bname.equalsIgnoreCase("") && !edition.equalsIgnoreCase("") && !pyear.equalsIgnoreCase("") ){
         try {
            pst=con.prepareStatement("insert into books(TITLE,PUBLISH_YEAR,status,EDDITION,AUTHOR_ID,category_id,pid)values(?,?,?,?,?,?,?) ");
       
            pst.setString(1, bname);
            pst.setString(2, pyear);
            pst.setString(3, status);
             pst.setString(4, edition);
             pst.setInt(5, auth.id);
             pst.setInt(6, cat.id);
             pst.setInt(7, publisher.id);
            
            int k =pst.executeUpdate();
             
              if(k==1){
       JOptionPane.showMessageDialog(rootPane, "Book added succesfuly");
       
      statuscombo.setSelectedIndex(-1);
       bnametf.setText("");
       editiontf.setText("");
       pyeartf.setText("");
       
       catcombo.setSelectedIndex(-1);
       acombo.setSelectedIndex(-1);
       pcombo.setSelectedIndex(-1);
       
       
       bnametf.requestFocus();
       
       load_book();
       }
       else{
       JOptionPane.showMessageDialog(rootPane, "unexpected error!");
       }
        } catch (SQLException ex) {
            Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      
        }else{
        
        JOptionPane.showMessageDialog(rootPane, "Please Enter Missing Fields ");
        }
      
        
    }//GEN-LAST:event_addbtnActionPerformed

    private void checkbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbtnActionPerformed
     
        String searchstr=searchtxt.getText();
       
       
       if(searchstr.equalsIgnoreCase("")) {
       load_book();
       }
       else{
       loadSearch(searchstr);
       
       }
    
       
    }//GEN-LAST:event_checkbtnActionPerformed

    private void bdetailstblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bdetailstblMouseClicked
             DefaultTableModel d1=(DefaultTableModel) bdetailstbl.getModel();
             int index=bdetailstbl.getSelectedRow();
             
             int id=Integer.parseInt( d1.getValueAt(index, 0).toString());
             String str="";
             
             bnametf.setText(d1.getValueAt(index, 1).toString());
             
             editiontf.setText(d1.getValueAt(index, 2).toString());
             
              String cat = d1.getValueAt(index, 3).toString();
              String auth = d1.getValueAt(index, 4).toString();
               String pub = d1.getValueAt(index, 5).toString();
               pyeartf.setText(d1.getValueAt(index,6).toString());
                statuscombo.setSelectedItem(d1.getValueAt(index, 7).toString());
    
    for (int i = 0; i < catcombo.getItemCount(); i++) {
        LoadCategoryItem item = (LoadCategoryItem) catcombo.getItemAt(i);
        if (item.name.equals(cat)) {
            catcombo.setSelectedItem(item);
            break;
        }
    }
    
        
     for (int i = 0; i < acombo.getItemCount(); i++) {
        LoadAuthorItem item = (LoadAuthorItem) acombo.getItemAt(i);
        if (item.name.equals(auth)) {
            acombo.setSelectedItem(item);
            break;
        }
    }
           
     
     for (int i = 0; i < pcombo.getItemCount(); i++) {
        LoadPublisherItem item = (LoadPublisherItem) pcombo.getItemAt(i);
        if (item.name.equals(pub)) {
            acombo.setSelectedItem(item);
            break;
        }
    }
           
             
            
             
            
             
             
        
          
             
             
             addbtn.setEnabled(false);
             
             
        
    }//GEN-LAST:event_bdetailstblMouseClicked

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
           DefaultTableModel d1=(DefaultTableModel) bdetailstbl.getModel();
             int index=bdetailstbl.getSelectedRow();
             
       int id=Integer.parseInt( d1.getValueAt(index, 0).toString());
             
        String name= bnametf.getText();
        String edition= editiontf.getText();
        String pyear= pyeartf.getText();
        String status= statuscombo.getSelectedItem().toString();
       
        
         try {
            pst=con.prepareStatement("update books set TITLE= ?, EDDITION = ?, PUBLISH_YEAR= ?, status = ? where ISBN= ? ");
       
            pst.setString(1, name);
            pst.setString(2, edition);
            pst.setString(3, pyear);
            pst.setString(4, status);
            pst.setInt(5, id);
            
            int k =pst.executeUpdate();
             
              if(k==1){
       JOptionPane.showMessageDialog(rootPane, "Book updated succesfuly");
       
       statuscombo.setSelectedItem(-1);
       bnametf.setText("");
       editiontf.setText("");
       pyeartf.setText("");
       
       
       bnametf.requestFocus();
       
       load_book();
       }
       else{
       JOptionPane.showMessageDialog(rootPane, "unexpected error!");
       }
        } catch (SQLException ex) {
            Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      
       
      
             
    }//GEN-LAST:event_updatebtnActionPerformed

    private void bdetailstblFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_bdetailstblFocusLost
        addbtn.setEnabled(true);
    }//GEN-LAST:event_bdetailstblFocusLost

    private void editiontfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editiontfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editiontfActionPerformed

    private void statuscomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statuscomboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statuscomboActionPerformed

    private void bdetailstblKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bdetailstblKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_bdetailstblKeyPressed

    private void searchtxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtxtKeyPressed
        
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
        
         String searchstr=searchtxt.getText();
       
       
       if(searchstr.equalsIgnoreCase("")) {
       load_book();
       }
       else{
       loadSearch(searchstr);
       
       }
        }
    }//GEN-LAST:event_searchtxtKeyPressed

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
            java.util.logging.Logger.getLogger(Books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new Books().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox acombo;
    private javax.swing.JButton addbtn;
    private javax.swing.JButton backbtn;
    private javax.swing.JTable bdetailstbl;
    private javax.swing.JTextField bnametf;
    private javax.swing.JComboBox catcombo;
    private javax.swing.JLabel catlbl;
    private javax.swing.JLabel catnamelbl;
    private javax.swing.JLabel catnamelbl1;
    private javax.swing.JLabel catnamelbl2;
    private javax.swing.JLabel catnamelbl3;
    private javax.swing.JLabel catnamelbl4;
    private javax.swing.JButton checkbtn;
    private javax.swing.JButton deletebtn;
    private javax.swing.JTextField editiontf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox pcombo;
    private javax.swing.JTextField pyeartf;
    private javax.swing.JTextField searchtxt;
    private javax.swing.JComboBox<String> statuscombo;
    private javax.swing.JLabel statuslbl;
    private javax.swing.JLabel statuslbl1;
    private javax.swing.JButton updatebtn;
    // End of variables declaration//GEN-END:variables
}
