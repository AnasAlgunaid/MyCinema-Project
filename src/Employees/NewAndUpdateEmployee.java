/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Employees;
import Employees.EmployeeSystem;
import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import assets.TextFieldLimit;
/**
 *
 * @author anasm
 */
public class NewAndUpdateEmployee extends javax.swing.JDialog {

    /**
     * Creates new form NewAndUpdateShow
     */

    private String selectedEmployeeID = null;

    public void setSelectedCustomerUser(String selectedEmployeeID) {
        this.selectedEmployeeID = selectedEmployeeID;
    }
    
    ArrayList<Integer> roleID;
    private void loadRolesIntoComboBox(){
        final String getRolesQuery = "SELECT Role_ID, Role_Title FROM Roles";
        try(        
            Statement selectRoles = EmployeeSystem.connection.createStatement();
            ResultSet typesResultSet = selectRoles.executeQuery(getRolesQuery);
            ){
            
            // Store movies' id
            roleID = new ArrayList<>();
            
            while(typesResultSet.next()){
                roleID.add(typesResultSet.getInt(1));
                roleComboBox.addItem(typesResultSet.getString(2));
            }    
             
            roleComboBox.setSelectedIndex(-1);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public NewAndUpdateEmployee(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
       
        initComponents();
            
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        actionButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lastName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        firstNameLabel = new javax.swing.JLabel();
        firstName = new javax.swing.JTextField();
        lastNameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        email = new javax.swing.JTextField();
        roleLabel = new javax.swing.JLabel();
        roleComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        employeeID = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();

        dateChooser.setForeground(new java.awt.Color(21, 122, 255));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(21, 122, 255));

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("New Employee");
        titleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                titleLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        actionButton.setBackground(new java.awt.Color(21, 122, 255));
        actionButton.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        actionButton.setForeground(new java.awt.Color(255, 255, 255));
        actionButton.setText("Add Employee");
        actionButton.setBorder(null);
        actionButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        actionButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                actionButtonFocusGained(evt);
            }
        });
        actionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                actionButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                actionButtonMouseExited(evt);
            }
        });
        actionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionButtonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(130, 142, 156));
        jLabel6.setText("Password");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(130, 142, 156));
        jLabel2.setText("Last name");

        lastName.setBackground(new java.awt.Color(246, 247, 249));
        lastName.setDocument(new TextFieldLimit(25));
        lastName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lastName.setForeground(new java.awt.Color(21, 22, 29));
        lastName.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lastName.setCaretColor(new java.awt.Color(21, 22, 29));
        lastName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lastNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                lastNameFocusLost(evt);
            }
        });
        lastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastNameActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(130, 142, 156));
        jLabel4.setText("First name");

        emailLabel.setForeground(new java.awt.Color(255, 35, 71));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(130, 142, 156));
        jLabel1.setText("Email");

        firstNameLabel.setForeground(new java.awt.Color(255, 35, 71));
        firstNameLabel.setToolTipText("");

        firstName.setBackground(new java.awt.Color(246, 247, 249));
        firstName.setDocument(new TextFieldLimit(25)
        );
        firstName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        firstName.setForeground(new java.awt.Color(21, 22, 29));
        firstName.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        firstName.setCaretColor(new java.awt.Color(21, 22, 29));
        firstName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                firstNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                firstNameFocusLost(evt);
            }
        });
        firstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameActionPerformed(evt);
            }
        });

        lastNameLabel.setForeground(new java.awt.Color(255, 35, 71));

        passwordLabel.setForeground(new java.awt.Color(255, 35, 71));

        password.setBackground(new java.awt.Color(246, 247, 249));
        password.setDocument(new TextFieldLimit(25));
        password.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        password.setForeground(new java.awt.Color(21, 22, 29));
        password.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        password.setCaretColor(new java.awt.Color(21, 22, 29));
        password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFocusGained(evt);
            }
        });

        email.setBackground(new java.awt.Color(246, 247, 249));
        email.setDocument(new TextFieldLimit(100));
        email.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        email.setForeground(new java.awt.Color(21, 22, 29));
        email.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        email.setCaretColor(new java.awt.Color(21, 22, 29));
        email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFocusLost(evt);
            }
        });
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        roleLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        roleLabel.setForeground(new java.awt.Color(255, 35, 71));

        roleComboBox.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        roleComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        roleComboBox.setFocusable(false);
        roleComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleComboBoxActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(130, 142, 156));
        jLabel3.setText("Role");

        employeeID.setBackground(new java.awt.Color(246, 247, 249));
        employeeID.setDocument(new TextFieldLimit(10)
        );
        employeeID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        employeeID.setForeground(new java.awt.Color(21, 22, 29));
        employeeID.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        employeeID.setCaretColor(new java.awt.Color(21, 22, 29));
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

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(130, 142, 156));
        jLabel5.setText("Employee ID");

        idLabel.setForeground(new java.awt.Color(255, 35, 71));
        idLabel.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(roleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(roleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(employeeID, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(firstName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lastName)
                            .addComponent(email)
                            .addComponent(password)
                            .addComponent(actionButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 78, Short.MAX_VALUE)))
                        .addGap(46, 46, 46))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(firstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(idLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(employeeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(firstNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(firstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lastNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(emailLabel))
                .addGap(4, 4, 4)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(passwordLabel))
                .addGap(5, 5, 5)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(roleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(roleLabel)))
                .addGap(21, 21, 21)
                .addComponent(actionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void actionButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_actionButtonFocusGained

    }//GEN-LAST:event_actionButtonFocusGained

    private void actionButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actionButtonMouseEntered
        actionButton.setBackground(new Color(68, 149, 255));
    }//GEN-LAST:event_actionButtonMouseEntered

    private void actionButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actionButtonMouseExited
        actionButton.setBackground(new Color(21, 122, 255));
    }//GEN-LAST:event_actionButtonMouseExited

    private void actionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionButtonActionPerformed
        boolean noEmptyFields = true;

        // Check if the employee ID is empty
        if(employeeID.getText().trim().isEmpty()){
            idLabel.setText("Please fill this field");
            noEmptyFields = false;
        }
        
        // Check if the first name is empty
        if(firstName.getText().trim().isEmpty()){
            firstNameLabel.setText("Please fill this field");
            noEmptyFields = false;
        }

        // Check if the last name is empty
        if(lastName.getText().trim().isEmpty()){
            lastNameLabel.setText("Please fill this field");
            noEmptyFields = false;
        }
        
        // Check if the email is empty
        if(email.getText().trim().isEmpty()){
            emailLabel.setText("Please fill this field");
            noEmptyFields = false;
        }
        
        // Check if the password is empty
        if(password.getText().trim().isEmpty()){
            passwordLabel.setText("Please fill this field");
            noEmptyFields = false;
        }
        
        // Check if there is a selected role
        if(roleComboBox.getSelectedIndex() < 0){
            roleLabel.setText("Please choose a type");
            noEmptyFields = false;
        }
        
        if(noEmptyFields){
            
            // Check if the password's length is less than 8 characters
            if(String.valueOf(password.getPassword()).trim().length() < 8){
                passwordLabel.setText("Password must be at least 8 characters.");
                return;
            }
            
            // Check if the entered id is number
            int idLength = employeeID.getText().length();
            for(int i = 0; i < idLength; i++){
                if(!Character.isDigit(employeeID.getText().charAt(i))){
                    idLabel.setText("Employee ID must be a number");
                    return;
                }
            }   
            
            // Check if the length is 10
            if(idLength != 10){
                idLabel.setText("Employee ID must be 10 digits");
                return;
            }
            
            // Check if the email is correct
            if(!email.getText().trim().contains("@") || !email.getText().trim().contains(".")){
                emailLabel.setText("Enter a valid email");
                return;
            }
            
            // If the password and the ID are correct open the connection
                
            // SQL Statements
            final String CHECK_ID = "SELECT Employee_ID FROM Employees WHERE Employee_ID = ?";
            final String INSERT_EMPLOYEE = "INSERT INTO Employees VALUES(?, ?, ?, ?, ?, ?)";
            final String UPDATE_EMPLOYEE = "UPDATE Employees SET Employee_ID = ?, FirstName = ?, LastName = ?, Email = ? ,Password = ?, Role_ID = ? WHERE Employee_ID = \'" + selectedEmployeeID + "\' ";

            // Declare database objects
            ResultSet resultSet = null;
            // To store the success message depends on the operation (Add or update)
            String successMessage;

            // To store the success message title depends on the operation (Add or update)
            String successTitle;

            // To store the sql statement that will be executed
            String InsertUpdateQuery;

            // If the operation is update
            if(selectedEmployeeID != null){
                InsertUpdateQuery = UPDATE_EMPLOYEE;
                successTitle = "Update Employee";
                successMessage = "Employee: [" + selectedEmployeeID + "] Updated Successfully";
            }else{
                InsertUpdateQuery = INSERT_EMPLOYEE;
                successTitle = "Add Employee";
                successMessage = "Employee added successfully";
            }

            try(
                PreparedStatement checkIDStatement = EmployeeSystem.connection.prepareStatement(CHECK_ID);
                PreparedStatement newOrUpdateEmployeeStatement = EmployeeSystem.connection.prepareStatement(InsertUpdateQuery);)
            {
                checkIDStatement.setString(1, employeeID.getText().trim());
                resultSet = checkIDStatement.executeQuery();


                // Check if the employee ID is already exists
                boolean isExist = false;
                if(resultSet.next()){
                    if(selectedEmployeeID != null){
                        if(!selectedEmployeeID.equals(resultSet.getString(1))){
                            isExist = true;
                        }
                    }else{
                        isExist = true;
                    }
                }
                if(isExist){
                    JOptionPane.showMessageDialog(null, "Sorry, employee ID is already exists. Please try another one.", "Adding Employee", JOptionPane.WARNING_MESSAGE);
                    idLabel.setText("Employee ID is already exists");
                }
                else{

                    // Set the values to the insert statement
                    newOrUpdateEmployeeStatement.setString(1, employeeID.getText().trim());
                    newOrUpdateEmployeeStatement.setString(2, firstName.getText().trim());
                    newOrUpdateEmployeeStatement.setString(3, lastName.getText().trim());
                    newOrUpdateEmployeeStatement.setString(4, email.getText().trim());
                    
                    // Get the password
                    String pass = new String(password.getPassword());
                    newOrUpdateEmployeeStatement.setString(5, pass);
                    
                    newOrUpdateEmployeeStatement.setInt(6, roleID.get(roleComboBox.getSelectedIndex()));
                    
                    // Execute the insert statement
                    if(newOrUpdateEmployeeStatement.executeUpdate() == 1){
                        // Show success dialog
                        JOptionPane.showMessageDialog(null, successMessage, successTitle, JOptionPane.INFORMATION_MESSAGE);
                    }

                    // Clear the form
                    employeeID.setText("");
                    firstName.setText("");
                    lastName.setText("");
                    email.setText("");
                    password.setText("");
                    roleComboBox.setSelectedIndex(-1);

                    // Refresh the table of movies
                    EmployeesForm.refreshTable();

                    // Dispose the dialog after the update
                    if(selectedEmployeeID != null){
                        this.dispose();
                    }
                }

            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }finally{
                if(resultSet != null){
                    try{
                        resultSet.close();
                    }catch(SQLException e){
                        JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }


        }
    }//GEN-LAST:event_actionButtonActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // Load the roles from the database
        loadRolesIntoComboBox();   
        
        if(selectedEmployeeID != null){
            titleLabel.setText("Update Employee");
            actionButton.setText("Update Employee");

            final String SELECTED_EMPLOYEE_QUERY = "SELECT Employee_ID, FirstName, LastName, Email ,Password, Role_ID FROM Employees WHERE Employee_ID = \'" + selectedEmployeeID + "\' ";
            try(Statement getSelectedEmployee = EmployeeSystem.connection.createStatement();
                ResultSet selectedEmployeeResultSet = getSelectedEmployee.executeQuery(SELECTED_EMPLOYEE_QUERY))
            {
                if(selectedEmployeeResultSet.next()){
                    // Get the old information 
                    employeeID.setText(selectedEmployeeResultSet.getString(1));
                    firstName.setText(selectedEmployeeResultSet.getString(2));
                    lastName.setText(selectedEmployeeResultSet.getString(3));
                    email.setText(selectedEmployeeResultSet.getString(4));
                    password.setText(selectedEmployeeResultSet.getString(5));
                    roleComboBox.setSelectedIndex(roleID.indexOf(selectedEmployeeResultSet.getInt(6)));
                }
 
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_formWindowOpened

    private void titleLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleLabelMouseClicked
       
    }//GEN-LAST:event_titleLabelMouseClicked

    private void lastNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameFocusGained
        lastNameLabel.setText("");
    }//GEN-LAST:event_lastNameFocusGained

    private void lastNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameFocusLost

    private void lastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameActionPerformed

    private void firstNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstNameFocusGained
        firstNameLabel.setText("");
    }//GEN-LAST:event_firstNameFocusGained

    private void firstNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameFocusLost

    private void firstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameActionPerformed

    private void passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusGained
        passwordLabel.setText("");
    }//GEN-LAST:event_passwordFocusGained

    private void emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusGained
        emailLabel.setText("");
    }//GEN-LAST:event_emailFocusGained

    private void emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFocusLost

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void roleComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleComboBoxActionPerformed
        roleLabel.setText("");
    }//GEN-LAST:event_roleComboBoxActionPerformed

    private void employeeIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_employeeIDFocusGained
        idLabel.setText("");
    }//GEN-LAST:event_employeeIDFocusGained

    private void employeeIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_employeeIDFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeIDFocusLost

    private void employeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeIDActionPerformed

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
            java.util.logging.Logger.getLogger(NewAndUpdateEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewAndUpdateEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewAndUpdateEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewAndUpdateEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NewAndUpdateEmployee dialog = new NewAndUpdateEmployee(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actionButton;
    private com.raven.datechooser.DateChooser dateChooser;
    private javax.swing.JTextField email;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField employeeID;
    private javax.swing.JTextField firstName;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField lastName;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JComboBox<String> roleComboBox;
    private javax.swing.JLabel roleLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}