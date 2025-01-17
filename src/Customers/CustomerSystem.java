/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Customers;

import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author anasm
 */
public class CustomerSystem extends javax.swing.JFrame {

    /**
     * Creates new form CustomerSystem
     */
        // Declare database connection objects
    protected static final String DBURL = "jdbc:mysql://localhost:3308/mycinemadb";
    protected static final String DBUSER = "root";
    protected static final String DBPASS = "";
    public static Connection connection;
    
    // Store user information
    private static Customer currentCustomer;
    
    // Define the colors
    private Color blackColor = new Color(21, 22, 29);
    private Color blueColor = new Color(21, 122, 255);
    
    // Define the menus forms
    BookingForm bookingForm = new BookingForm();
    
    
    public static void setCurrentCustomer(Customer customer) {
        currentCustomer = customer;
    }
    
    public static Customer getCurrentCustomer(){
        return currentCustomer;
    }
    
    public void dbClose(){
        if(connection != null){
            try{
                connection.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static void updateWelcomeMessage(){
        // Write the customer name in the welcome message
        welcomeLabel.setText("<html> Welcome <br/>" + currentCustomer.getFirstName() + " " + currentCustomer.getLastName() + "</html>");
        
    }
    
    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/MyCinemaLogo.png")));
    }
    
    public CustomerSystem() {
        initComponents();
        
        setIconImage();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        welcomeLabel = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();
        book = new javax.swing.JButton();
        myTicketsMenu = new javax.swing.JButton();
        profile = new javax.swing.JButton();
        logOut = new javax.swing.JButton();
        body = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MyCinema | Customer System");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        Header.setBackground(new java.awt.Color(21, 22, 29));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MyCinema | Customer System");

        welcomeLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        welcomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        welcomeLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Employees/images/icons8_person_36px.png"))); // NOI18N
        welcomeLabel.setText("<html> Welcome <br/> ");
        welcomeLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145)
                .addComponent(jLabel1)
                .addContainerGap(323, Short.MAX_VALUE))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(HeaderLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(welcomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
        );

        Menu.setBackground(new java.awt.Color(21, 22, 29));
        Menu.setLayout(new java.awt.GridLayout(1, 0));

        book.setBackground(new java.awt.Color(21, 122, 255));
        book.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        book.setForeground(new java.awt.Color(255, 255, 255));
        book.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Employees/images/icons8_cinema_ticket_36px.png"))); // NOI18N
        book.setText("Book");
        book.setBorder(null);
        book.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        book.setFocusable(false);
        book.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        book.setIconTextGap(6);
        book.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookActionPerformed(evt);
            }
        });
        Menu.add(book);

        myTicketsMenu.setBackground(new java.awt.Color(21, 22, 29));
        myTicketsMenu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        myTicketsMenu.setForeground(new java.awt.Color(255, 255, 255));
        myTicketsMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Employees/images/icons8_two_tickets_36px_1.png"))); // NOI18N
        myTicketsMenu.setText("My Tickets");
        myTicketsMenu.setBorder(null);
        myTicketsMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        myTicketsMenu.setFocusable(false);
        myTicketsMenu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        myTicketsMenu.setIconTextGap(6);
        myTicketsMenu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        myTicketsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myTicketsMenuActionPerformed(evt);
            }
        });
        Menu.add(myTicketsMenu);

        profile.setBackground(new java.awt.Color(21, 22, 29));
        profile.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        profile.setForeground(new java.awt.Color(255, 255, 255));
        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Employees/images/icons8_person_36px.png"))); // NOI18N
        profile.setText("Profile");
        profile.setBorder(null);
        profile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profile.setFocusable(false);
        profile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        profile.setIconTextGap(6);
        profile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileActionPerformed(evt);
            }
        });
        Menu.add(profile);

        logOut.setBackground(new java.awt.Color(21, 22, 29));
        logOut.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        logOut.setForeground(new java.awt.Color(255, 255, 255));
        logOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Employees/images/icons8_Logout_Rounded_32px.png"))); // NOI18N
        logOut.setText("Log Out");
        logOut.setBorder(null);
        logOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logOut.setFocusable(false);
        logOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        logOut.setIconTextGap(6);
        logOut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutActionPerformed(evt);
            }
        });
        Menu.add(logOut);

        body.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 539, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(body, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(body)
                .addGap(0, 0, 0)
                .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookActionPerformed
        bookingForm.loadMoviesIntoComboBox();
        BookingForm.refreshTable();

        body.removeAll();
        body.add(bookingForm);
        bookingForm.show();

        // Change the colors
        book.setBackground(blueColor);
        myTicketsMenu.setBackground(blackColor);
        profile.setBackground(blackColor);
        logOut.setBackground(blackColor);
        
    }//GEN-LAST:event_bookActionPerformed

    private void myTicketsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myTicketsMenuActionPerformed
        // Refresh the tickets table
        MyTickets myTickets = new MyTickets();
        
        // Show the menu
        MyTickets.refreshTable();
        body.removeAll();
        body.add(myTickets);
        myTickets.show();

        // Change the colors
        book.setBackground(blackColor);
        myTicketsMenu.setBackground(blueColor);
        profile.setBackground(blackColor);
        logOut.setBackground(blackColor);
        

    }//GEN-LAST:event_myTicketsMenuActionPerformed

    private void profileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileActionPerformed
//        TicketsForm.refreshTable();
//
        Profile profileForm = new Profile();
        body.removeAll();
        body.add(profileForm);
        profileForm.show();

        // Change the colors
        book.setBackground(blackColor);
        myTicketsMenu.setBackground(blackColor);
        profile.setBackground(blueColor);
        logOut.setBackground(blackColor);
        
    }//GEN-LAST:event_profileActionPerformed

    private void logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutActionPerformed
        // Show confirmation dialog
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Log out", JOptionPane.YES_NO_OPTION);

        // If the user chose yes
        if(confirm == JOptionPane.YES_OPTION){
            dbClose();
            this.dispose();
        }
    }//GEN-LAST:event_logOutActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try{
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        updateWelcomeMessage();
        
        bookingForm.loadMoviesIntoComboBox();
        BookingForm.refreshTable();
        body.removeAll();
        body.add(bookingForm);
        bookingForm.show();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        dbClose();
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CustomerSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerSystem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Menu;
    private javax.swing.JDesktopPane body;
    private javax.swing.JButton book;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton logOut;
    private javax.swing.JButton myTicketsMenu;
    private javax.swing.JButton profile;
    public static javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
