/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package LoginPage;

import Employees.Employee;
import Employees.EmployeeSystem;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.sql.*;

/**
 *
 * @author anasm
 */
public class EmployeeSignIn extends javax.swing.JInternalFrame {

    /**
     * Creates new form EmployeeSignIn
     */
    public EmployeeSignIn() {
        initComponents();
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        employeeID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        employeePassword = new javax.swing.JPasswordField();
        signInButton = new javax.swing.JButton();
        customerLoginButton = new javax.swing.JButton();
        IDLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(22, 21, 26));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Employee Login");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Please sign in to your account");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(130, 142, 156));
        jLabel1.setText("Employee ID");

        employeeID.setBackground(new java.awt.Color(31, 30, 37));
        employeeID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        employeeID.setForeground(new java.awt.Color(255, 255, 255));
        employeeID.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        employeeID.setCaretColor(new java.awt.Color(255, 255, 255));
        employeeID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                employeeIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                employeeIDFocusLost(evt);
            }
        });
        employeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeIDActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(130, 142, 156));
        jLabel6.setText("Password");

        employeePassword.setBackground(new java.awt.Color(31, 30, 37));
        employeePassword.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        employeePassword.setForeground(new java.awt.Color(255, 255, 255));
        employeePassword.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        employeePassword.setCaretColor(new java.awt.Color(255, 255, 255));
        employeePassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                employeePasswordFocusGained(evt);
            }
        });

        signInButton.setBackground(new java.awt.Color(21, 122, 255));
        signInButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        signInButton.setForeground(new java.awt.Color(255, 255, 255));
        signInButton.setText("Sign In");
        signInButton.setBorder(null);
        signInButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signInButton.setFocusable(false);
        signInButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signInButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signInButtonMouseExited(evt);
            }
        });
        signInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInButtonActionPerformed(evt);
            }
        });

        customerLoginButton.setBackground(new java.awt.Color(52, 199, 128));
        customerLoginButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        customerLoginButton.setForeground(new java.awt.Color(255, 255, 255));
        customerLoginButton.setText("Go Back to Customer Login");
        customerLoginButton.setBorder(null);
        customerLoginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        customerLoginButton.setFocusable(false);
        customerLoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                customerLoginButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                customerLoginButtonMouseExited(evt);
            }
        });
        customerLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerLoginButtonActionPerformed(evt);
            }
        });

        IDLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        IDLabel.setForeground(new java.awt.Color(255, 35, 71));

        passwordLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(255, 35, 71));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(signInButton, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(employeeID, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(employeePassword, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(customerLoginButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(employeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(IDLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(2, 2, 2)
                .addComponent(employeePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(signInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(customerLoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void employeeIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_employeeIDFocusGained
        IDLabel.setText("");
    }//GEN-LAST:event_employeeIDFocusGained

    private void employeeIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_employeeIDFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeIDFocusLost

    private void employeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeIDActionPerformed

    private void employeePasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_employeePasswordFocusGained
        passwordLabel.setText("");
    }//GEN-LAST:event_employeePasswordFocusGained

    private void customerLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerLoginButtonActionPerformed
        CustomerSignIn customerSignIn = new CustomerSignIn();
        CustomerLogin.body.removeAll();
        CustomerLogin.body.add(customerSignIn);
        customerSignIn.show();
    }//GEN-LAST:event_customerLoginButtonActionPerformed

    private void signInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInButtonActionPerformed
        boolean emptyID = employeeID.getText().trim().isEmpty();
        boolean emptyPassword = employeePassword.getText().trim().isEmpty();
        
        if(emptyID && emptyPassword){
            IDLabel.setText("Please fill this field");
            passwordLabel.setText("Please fill this field");
        }
        
        else if(emptyID){
            IDLabel.setText("Please fill this field");
        }
        
        else if(emptyPassword){
            passwordLabel.setText("Please fill this field");
        }
        
        else{
            final String DBURL = "jdbc:mysql://localhost:3308/mycinemadb";
            final String FIND_USER = "SELECT Employee_ID, FirstName, LastName, Email, Password, Role_ID FROM Employees WHERE Employee_ID = ? AND Password = ?";
            
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet result = null;
            try{
                connection = DriverManager.getConnection(DBURL, "root", "");
                statement = connection.prepareStatement(FIND_USER);
                statement.setString(1, employeeID.getText());
                statement.setString(2, String.valueOf(employeePassword.getPassword()));
                result = statement.executeQuery();
                
                if(result.next()){
                    // Store employee's information into Employee object
                    Employee employee = new Employee();
                    employee.setEmployee_ID(result.getString(1));
                    employee.setFirstName(result.getString(2));
                    employee.setLastName(result.getString(3));
                    employee.setEmail(result.getString(4));
                    employee.setPassword(result.getString(5));
                    employee.setRole_ID(result.getInt(6));
                    
                    // Open the employee system and pass the employee to the system
                    EmployeeSystem employeeSystem = new EmployeeSystem();
                    employeeSystem.setCurrentEmployee(employee);
                    employeeSystem.setVisible(true);
                    
                    // Clear the form
                    employeeID.setText("");
                    employeePassword.setText("");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Failed, incorrect information", "Login", JOptionPane.ERROR_MESSAGE);

                }
                
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }finally{
                try{
                    result.close();
                    statement.close();
                    connection.close();
                    
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_signInButtonActionPerformed

    private void signInButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signInButtonMouseEntered
        signInButton.setBackground(new Color(68, 149, 255));
    }//GEN-LAST:event_signInButtonMouseEntered

    private void signInButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signInButtonMouseExited
        signInButton.setBackground(new Color(21, 122, 255));
    }//GEN-LAST:event_signInButtonMouseExited

    private void customerLoginButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerLoginButtonMouseEntered
        customerLoginButton.setBackground(new Color(93, 210, 153));
    }//GEN-LAST:event_customerLoginButtonMouseEntered

    private void customerLoginButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerLoginButtonMouseExited
        customerLoginButton.setBackground(new Color(52, 199, 128));
    }//GEN-LAST:event_customerLoginButtonMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IDLabel;
    private javax.swing.JButton customerLoginButton;
    private javax.swing.JTextField employeeID;
    private javax.swing.JPasswordField employeePassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton signInButton;
    // End of variables declaration//GEN-END:variables
}
