/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Employees;
import CustomJTable.table.TableCustom;
import Employees.EmployeeSystem;
import java.awt.Color;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.proteanit.sql.DbUtils;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
/**
 *
 * @author anasm
 */
public class NewAndUpdateTicket extends javax.swing.JDialog {

    /**
     * Creates new form NewAndUpdateShow
     */
    private void loadCustomersIntoComboBox(){
        final String getCustomersQuery = "SELECT Username FROM Customers";
        try(        
            Statement selectCustomers = EmployeeSystem.connection.createStatement();
            ResultSet customersResultSet = selectCustomers.executeQuery(getCustomersQuery);
            ){
            
            // Add users to the combobox
            while(customersResultSet.next()){
                customerComboBox.addItem(customersResultSet.getString(1));
            }    
             
            customerComboBox.setSelectedIndex(-1);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void deleteExpireShows(){
        // Delete the expire shows
        final String deleteOldShows = "DELETE shows FROM shows INNER JOIN show_times ON shows.Show_ID = show_times.Show_ID WHERE show_times.End_Time < NOW() "; 
        try(Statement deleteExpireShows = EmployeeSystem.connection.createStatement();)
        {
            deleteExpireShows.executeUpdate(deleteOldShows);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    public static void refreshTable(){
        String allshowsQuery = "SELECT Show_ID, Movie_Name, CONCAT(Theater_Number, \' | \', Theater_Type) AS Theater, Start_Time, End_Time, Price, Available_Seats FROM Show_Times ";
        
        // Delete the expire shows
        deleteExpireShows();
       
        // Check for the order combobox 
        if(orderComboBox.getSelectedItem() == "ID: Ascending"){
            allshowsQuery = allshowsQuery + " ORDER BY Show_ID ASC";
        }
        else if(orderComboBox.getSelectedItem() == "ID: Descending"){
            allshowsQuery = allshowsQuery + " ORDER BY Show_ID DESC";
        }
        else if(orderComboBox.getSelectedItem() == "The nearest"){
            allshowsQuery = allshowsQuery + " ORDER BY Start_Time ASC";
        }else if(orderComboBox.getSelectedItem() == "Price: High to low"){
            allshowsQuery = allshowsQuery + " ORDER BY Price DESC";
        }else if(orderComboBox.getSelectedItem() == "Price: Low to high"){
            allshowsQuery = allshowsQuery + " ORDER BY Price ASC";
        }   
        
        // Get the shows and refresh the table
        try(
                    Statement selectAllShows = EmployeeSystem.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet showsResultSet = selectAllShows.executeQuery(allshowsQuery);
                ){
            
            showsTable.setModel(DbUtils.resultSetToTableModel(showsResultSet));
            
            // Change the format of the returned timestamp to split the date and time in different lines
            int numOfRows = showsTable.getRowCount();
            for(int i = 0; i < numOfRows; i++){
                String startTime = "" + showsTable.getModel().getValueAt(i, 3);
                String endTime = "" + showsTable.getModel().getValueAt(i, 4);

                showsTable.getModel().setValueAt(startTime.replace('T', '\n'), i, 3);
                showsTable.getModel().setValueAt(endTime.replace('T', '\n'), i, 4);

            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateTotalPrice(){
        boolean validNumOfTickets = true;
        int numOfTickets = 0;

        // Check if the number of tickets is a number and positive
        try{
            // Check if it is an integer
            numOfTickets = Integer.parseInt(numOfTicketsField.getText());

            // Check if it is a positive
            if(numOfTickets < 0 || numOfTickets > 10){
                validNumOfTickets = false;
            }
        }catch(NumberFormatException e){
            validNumOfTickets = false;
        }

        // Change the label text if the number of tickets is not valid
        if(validNumOfTickets){
            int selectedShow = showsTable.getSelectedRow();
            if(selectedShow >= 0){
                String showPriceStr = showsTable.getModel().getValueAt(selectedShow, 5) + "";
                double showPriceDou = Double.parseDouble(showPriceStr);
                double calculatedTotalPrice = numOfTickets * showPriceDou;
                totalPrice.setText(calculatedTotalPrice + "");
            } 
        }
    }
    
    
    public NewAndUpdateTicket(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
       
        initComponents();
        
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        AutoCompleteDecorator.decorate(customerComboBox);
        
        // Update the total price when the user change the number of tickets
        numOfTicketsField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                updateLabel(e);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateLabel(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateLabel(e);
            }

            private void updateLabel(DocumentEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        updateTotalPrice();
                    }
                });
            }
        });
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
        jPanel2 = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        customerComboBox = new javax.swing.JComboBox<>();
        actionButton = new javax.swing.JButton();
        customerLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        numOfTicketsLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        showsTable = new javax.swing.JTable();
        orderComboBox = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        totalPrice = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        decrementButton = new javax.swing.JButton();
        numOfTicketsField = new javax.swing.JTextField();
        incrementButton = new javax.swing.JButton();

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
        titleLabel.setText("New Ticket");
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
                .addGap(210, 210, 210)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(208, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(130, 142, 156));
        jLabel3.setText("Customer Username");

        customerComboBox.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        customerComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        customerComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerComboBoxActionPerformed(evt);
            }
        });

        actionButton.setBackground(new java.awt.Color(21, 122, 255));
        actionButton.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        actionButton.setForeground(new java.awt.Color(255, 255, 255));
        actionButton.setText("Add Ticket");
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

        customerLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        customerLabel.setForeground(new java.awt.Color(255, 35, 71));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(130, 142, 156));
        jLabel8.setText("Number of tickets");

        numOfTicketsLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        numOfTicketsLabel.setForeground(new java.awt.Color(255, 35, 71));

        showsTable.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        showsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Show_ID", "Movie", "Theater", "Start_Time", "End_Time", "Available Seats", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        showsTable.setFocusable(false);
        showsTable.setRowHeight(40);
        showsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        showsTable.setShowGrid(true);
        showsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(showsTable);

        orderComboBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        orderComboBox.setForeground(new java.awt.Color(21, 22, 29));
        orderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID: Ascending", "ID: Descending", "The nearest", "Price: High to low", "Price: Low to high" }));
        orderComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        orderComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderComboBoxActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(21, 22, 29));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Employees/images/icons8_filter_and_sort_24px_1.png"))); // NOI18N
        jLabel10.setText("Order by:");

        jPanel3.setBackground(new java.awt.Color(249, 93, 94));

        jLabel9.setBackground(new java.awt.Color(249, 93, 94));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Employees/images/icons8_billing_machine_28px.png"))); // NOI18N
        jLabel9.setText("Total Price:");
        jLabel9.setToolTipText("");
        jLabel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        jLabel9.setOpaque(true);

        totalPrice.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totalPrice.setForeground(new java.awt.Color(255, 255, 255));
        totalPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(totalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
            .addComponent(totalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setLayout(new java.awt.GridLayout());

        decrementButton.setBackground(new java.awt.Color(21, 122, 255));
        decrementButton.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        decrementButton.setForeground(new java.awt.Color(255, 255, 255));
        decrementButton.setText("-");
        decrementButton.setBorder(null);
        decrementButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        decrementButton.setFocusable(false);
        decrementButton.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        decrementButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                decrementButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                decrementButtonMouseExited(evt);
            }
        });
        decrementButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decrementButtonActionPerformed(evt);
            }
        });
        jPanel4.add(decrementButton);

        numOfTicketsField.setBackground(new java.awt.Color(246, 247, 249));
        numOfTicketsField.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        numOfTicketsField.setForeground(new java.awt.Color(21, 22, 29));
        numOfTicketsField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        numOfTicketsField.setText("1");
        numOfTicketsField.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        jPanel4.add(numOfTicketsField);

        incrementButton.setBackground(new java.awt.Color(21, 122, 255));
        incrementButton.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        incrementButton.setForeground(new java.awt.Color(255, 255, 255));
        incrementButton.setText("+");
        incrementButton.setBorder(null);
        incrementButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        incrementButton.setFocusable(false);
        incrementButton.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        incrementButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                incrementButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                incrementButtonMouseExited(evt);
            }
        });
        incrementButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incrementButtonActionPerformed(evt);
            }
        });
        jPanel4.add(incrementButton);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(actionButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(customerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(customerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(orderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(numOfTicketsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(customerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(orderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(customerLabel)))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numOfTicketsLabel)
                .addGap(18, 18, 18)
                .addComponent(actionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 38, Short.MAX_VALUE))
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
        
        // Check if there is a selected customer
        if(customerComboBox.getSelectedIndex() < 0){
            customerLabel.setText("Please choose a customer");
            noEmptyFields = false;
        }
        
        int selectedRow = showsTable.getSelectedRow();
        // Check if the there is a selected show
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(null, "Please choose a show", "Choose show", JOptionPane.WARNING_MESSAGE);
            noEmptyFields = false;
        }


        // Check if the number of tickets is empty
        if(numOfTicketsField.getText().isEmpty()){
            numOfTicketsLabel.setText("Please fill this field");
            noEmptyFields = false;
        }
        
        if(noEmptyFields){
            boolean validNumOfTickets = true;
            int numOfTickets = 0;
            
            // Check if the theater number is a number and positive
            try{
                // Check if it is an integer
                numOfTickets = Integer.parseInt(numOfTicketsField.getText());
                
                // Check if it is a positive
                if(numOfTickets < 0 || numOfTickets > 10){
                    validNumOfTickets = false;
                }
            }catch(NumberFormatException e){
                validNumOfTickets = false;
            }
            
            // Change the label text if the number of tickets is not valid
            if(!validNumOfTickets){
                numOfTicketsLabel.setText("Enter a valid number between 1 and 10");  
                return;
            }
            
            // Get the username of the selected customer
            String selectedCustomerUser = (String) customerComboBox.getSelectedItem();

            // SQL Statement: Insert 
            final String INSERT_TICKET = "INSERT INTO Tickets(Customer_Username, Show_ID) VALUES(?, ?)";

            // To store the success message
            String successMessage = "Ticket added successfully";

            // To store the success message title 
            String successTitle = "Add Ticket";

            // Get the ID of the selected show
            int selectedShowID= (Integer) showsTable.getModel().getValueAt(selectedRow, 0);
                        
            // Get the available seats of the selected show
            final String availableSeatsBefore = "SELECT Available_Seats FROM Shows WHERE Show_ID = " + selectedShowID;   
            
            // Decrement the number of available seats
            final String avilableSeatsAfter = "UPDATE Shows SET Available_Seats = Available_Seats - ? WHERE Show_ID = " + selectedShowID;
        
            
            // Insert the new ticket
            try(
                PreparedStatement newOrUpdateTicket = EmployeeSystem.connection.prepareStatement(INSERT_TICKET);
                Statement getAvailableSeats = EmployeeSystem.connection.createStatement();
                ResultSet availableSeatsResultSet = getAvailableSeats.executeQuery(availableSeatsBefore);
                PreparedStatement updateAvailableSeats = EmployeeSystem.connection.prepareStatement(avilableSeatsAfter);
                    ){

                // Check if there are enough seats
                availableSeatsResultSet.next();
                int showAvailableSeats = availableSeatsResultSet.getInt(1);

                if(showAvailableSeats >= numOfTickets){
                    for(int i = 0; i < numOfTickets; i++){
                        // Set the values
                        newOrUpdateTicket.setString(1, selectedCustomerUser);
                        newOrUpdateTicket.setInt(2, selectedShowID);
                        
                        // Execute the update
                        newOrUpdateTicket.executeUpdate();
                    }


                    // Show success message
                    JOptionPane.showMessageDialog(null, successMessage, successTitle, JOptionPane.INFORMATION_MESSAGE);
                               
                    // Update the number of available seats
                    updateAvailableSeats.setInt(1, numOfTickets);
                    updateAvailableSeats.executeUpdate();
                    
                    // Clear the form
                    customerComboBox.setSelectedIndex(-1);
                    numOfTicketsField.setText("1");
                    
                    // Update the price
                    totalPrice.setText("");
                
                    // Refresh the table of tickets
                    TicketsForm.refreshTable();

                    // Refresh the table of shows
                    refreshTable();
                }else{
                    JOptionPane.showMessageDialog(null, "Sorry, There are not enough seats", "Not enough seats", JOptionPane.WARNING_MESSAGE);
                }

            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
           
        }
    }//GEN-LAST:event_actionButtonActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
        // Load the types of theaters from the database
        loadCustomersIntoComboBox();
        
        // Refresh the shows table
        refreshTable();
    }//GEN-LAST:event_formWindowOpened

    private void titleLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleLabelMouseClicked
       
    }//GEN-LAST:event_titleLabelMouseClicked

    private void customerComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerComboBoxActionPerformed
        customerLabel.setText("");
    }//GEN-LAST:event_customerComboBoxActionPerformed

    private void orderComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderComboBoxActionPerformed
        refreshTable();
    }//GEN-LAST:event_orderComboBoxActionPerformed

    private void showsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showsTableMouseClicked
        updateTotalPrice();
    }//GEN-LAST:event_showsTableMouseClicked

    private void decrementButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decrementButtonMouseEntered
        decrementButton.setBackground(new Color(68, 149, 255));
    }//GEN-LAST:event_decrementButtonMouseEntered

    private void decrementButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decrementButtonMouseExited
        decrementButton.setBackground(new Color(21, 122, 255));
    }//GEN-LAST:event_decrementButtonMouseExited

    private void decrementButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decrementButtonActionPerformed

        int numOfTickets;
        try{
            numOfTickets = Integer.parseInt(numOfTicketsField.getText());
            if(numOfTickets > 1){
                numOfTickets--;
                numOfTicketsField.setText(numOfTickets + "");
            }
        }catch(NumberFormatException e){
            numOfTicketsField.setText("1");
        }

        updateTotalPrice();
    }//GEN-LAST:event_decrementButtonActionPerformed

    private void incrementButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_incrementButtonMouseEntered
        incrementButton.setBackground(new Color(68, 149, 255));
    }//GEN-LAST:event_incrementButtonMouseEntered

    private void incrementButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_incrementButtonMouseExited
        incrementButton.setBackground(new Color(21, 122, 255));
    }//GEN-LAST:event_incrementButtonMouseExited

    private void incrementButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incrementButtonActionPerformed
        int numOfTickets;
        try{
            numOfTickets = Integer.parseInt(numOfTicketsField.getText());
            if(numOfTickets < 10){
                numOfTickets++;
                numOfTicketsField.setText(numOfTickets + "");
            }
        }catch(NumberFormatException e){
            numOfTicketsField.setText("1");
        }

        updateTotalPrice();
    }//GEN-LAST:event_incrementButtonActionPerformed

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
            java.util.logging.Logger.getLogger(NewAndUpdateTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewAndUpdateTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewAndUpdateTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewAndUpdateTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                NewAndUpdateTicket dialog = new NewAndUpdateTicket(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> customerComboBox;
    private javax.swing.JLabel customerLabel;
    private javax.swing.JButton decrementButton;
    private javax.swing.JButton incrementButton;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField numOfTicketsField;
    private javax.swing.JLabel numOfTicketsLabel;
    private static javax.swing.JComboBox<String> orderComboBox;
    protected static javax.swing.JTable showsTable;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel totalPrice;
    // End of variables declaration//GEN-END:variables
}
