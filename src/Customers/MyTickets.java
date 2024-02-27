/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Customers;

import CustomJTable.table.TableCustom;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author anasm
 */
public class MyTickets extends javax.swing.JInternalFrame {

    /**
     * Creates new form BookingForm
     */
    // Store movies ID
    private static ArrayList<Integer> moviesID;
    
    // 
    private static Formatter output;
    
    public MyTickets() {
        initComponents();
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
    }
    
    public static void deleteExpireShows(){
        // Delete the expire shows
        final String deleteOldShows = "DELETE shows FROM shows INNER JOIN show_times ON shows.Show_ID = show_times.Show_ID WHERE show_times.End_Time < NOW() "; 
        try(Statement deleteExpireShows = CustomerSystem.connection.createStatement();)
        {
            deleteExpireShows.executeUpdate(deleteOldShows);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    public static void refreshTable(){
        String allTicketsQuery = "SELECT Ticket_ID, Customer, Show_ID, Movie_Name, Theater, Start_Time, End_Time FROM TicketsInfo WHERE Customer = \'" + CustomerSystem.getCurrentCustomer().getUsername() + "\' ";
        
        // Check for the order combobox 
        if(orderComboBox.getSelectedItem() == "The nearest"){
            allTicketsQuery = allTicketsQuery + " ORDER BY Start_Time ASC";
        }else if(orderComboBox.getSelectedItem() == "ID: Ascending"){
            allTicketsQuery = allTicketsQuery + " ORDER BY Ticket_ID ASC";
        }else if(orderComboBox.getSelectedItem() == "ID: Descending"){
            allTicketsQuery = allTicketsQuery + " ORDER BY Ticket_ID DESC";
        }

        try(
                    Statement selectAllTickets = CustomerSystem.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet ticketsResultSet = selectAllTickets.executeQuery(allTicketsQuery);
                ){

            ticketsTable.setModel(DbUtils.resultSetToTableModel(ticketsResultSet));
            
            // Change the format of the returned timestamp to split the date and time in different lines
            int numOfRows = ticketsTable.getRowCount();
            for(int i = 0; i < numOfRows; i++){
                String startTime = "" + ticketsTable.getModel().getValueAt(i, 5);
                String endTime = "" + ticketsTable.getModel().getValueAt(i, 6);

                // Replace T with new line
                ticketsTable.getModel().setValueAt(startTime.replace('T', '\n'), i, 5);
                ticketsTable.getModel().setValueAt(endTime.replace('T', '\n'), i, 6);
            }
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void openFile(){

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
        jScrollPane1 = new javax.swing.JScrollPane();
        ticketsTable = new javax.swing.JTable();
        orderComboBox = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        exportButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        searchComboBox = new javax.swing.JComboBox<>();

        dateChooser.setForeground(new java.awt.Color(21, 122, 255));

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        ticketsTable.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        ticketsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        ticketsTable.setFocusable(false);
        ticketsTable.setRowHeight(40);
        ticketsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ticketsTable.setShowGrid(true);
        ticketsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ticketsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ticketsTable);

        orderComboBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        orderComboBox.setForeground(new java.awt.Color(21, 22, 29));
        orderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "The nearest", "ID: Ascending", "ID: Descending" }));
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

        exportButton.setBackground(new java.awt.Color(21, 122, 255));
        exportButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        exportButton.setForeground(new java.awt.Color(255, 255, 255));
        exportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Customers/images/icons8_send_file_24px.png"))); // NOI18N
        exportButton.setText("Export Ticket");
        exportButton.setBorder(null);
        exportButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exportButton.setFocusable(false);
        exportButton.setIconTextGap(10);
        exportButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exportButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exportButtonMouseExited(evt);
            }
        });
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        cancelButton.setBackground(new java.awt.Color(249, 93, 94));
        cancelButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Employees/images/icons8_remove_24px_1.png"))); // NOI18N
        cancelButton.setText("Cancel Ticket");
        cancelButton.setBorder(null);
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelButton.setFocusable(false);
        cancelButton.setIconTextGap(10);
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelButtonMouseExited(evt);
            }
        });
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(21, 122, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Search by:");

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        searchTextField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        searchTextField.setText("Search here");
        searchTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchTextFieldFocusGained(evt);
            }
        });

        searchButton.setBackground(new java.awt.Color(21, 22, 29));
        searchButton.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        searchButton.setForeground(new java.awt.Color(255, 255, 255));
        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Employees/images/icons8_search_20px.png"))); // NOI18N
        searchButton.setText("Search");
        searchButton.setBorder(null);
        searchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchButton.setFocusable(false);
        searchButton.setIconTextGap(8);
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchButtonMouseExited(evt);
            }
        });
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        clearButton.setBackground(new java.awt.Color(21, 22, 29));
        clearButton.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        clearButton.setForeground(new java.awt.Color(255, 255, 255));
        clearButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Employees/images/icons8_broom_20px.png"))); // NOI18N
        clearButton.setText("Clear");
        clearButton.setBorder(null);
        clearButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clearButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clearButtonMouseExited(evt);
            }
        });
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        searchComboBox.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        searchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ticket ID", "Movie name" }));
        searchComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchComboBox.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchTextField)
                .addGap(18, 18, 18)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(exportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(orderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(exportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(orderComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ticketsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ticketsTableMouseClicked

    }//GEN-LAST:event_ticketsTableMouseClicked

    private void orderComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderComboBoxActionPerformed
        refreshTable();
    }//GEN-LAST:event_orderComboBoxActionPerformed

    private void searchTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchTextFieldFocusGained
        searchTextField.setText("");
    }//GEN-LAST:event_searchTextFieldFocusGained

    private void searchButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseEntered
        searchButton.setBackground(new Color(44, 45, 52));
    }//GEN-LAST:event_searchButtonMouseEntered

    private void searchButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseExited
       clearButton.setBackground(new Color(21, 22, 29));
    }//GEN-LAST:event_searchButtonMouseExited

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String searchQuery = "SELECT Ticket_ID, Customer, Show_ID, Movie_Name, Theater, Start_Time, End_Time FROM TicketsInfo WHERE ";
        
        // Search by Ticket ID
        if(searchComboBox.getSelectedItem() == "Ticket ID"){
            try{
                int ticketID = Integer.parseInt(searchTextField.getText());
                searchQuery = searchQuery + " Ticket_ID = " + searchTextField.getText();
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Ticket ID must be a number, Please enter a valid number", "Invalid Ticket ID", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Search by customer 
        }else if(searchComboBox.getSelectedItem() == "Movie name"){
            searchQuery = searchQuery + " Movie_Name LIKE \'" + searchTextField.getText() +"%\' ";
            // Search by movie name
        }
        
        // Show only the tickets of the customer
        searchQuery = searchQuery + " AND Customer = \'" + CustomerSystem.getCurrentCustomer().getUsername() + "\' ";

        // Check for the order combobox 
        if(orderComboBox.getSelectedItem() == "The nearest"){
            searchQuery = searchQuery + " ORDER BY Start_Time ASC";
        }else if(orderComboBox.getSelectedItem() == "ID: Ascending"){
            searchQuery = searchQuery + " ORDER BY Ticket_ID ASC";
        }else if(orderComboBox.getSelectedItem() == "ID: Descending"){
            searchQuery = searchQuery + " ORDER BY Ticket_ID DESC";
        }   
        
        // Get the result and refresh the table
        try(
            Statement getSearchResult = CustomerSystem.connection.createStatement();
            ResultSet searchResultSet = getSearchResult.executeQuery(searchQuery);)
        {
                    
            // Refresh the table
            ticketsTable.setModel(DbUtils.resultSetToTableModel(searchResultSet));
        
            // Change the format of the returned timestamp to split the date and time in different lines
            int numOfRows = ticketsTable.getRowCount();
            for(int i = 0; i < numOfRows; i++){
                String startTime = "" + ticketsTable.getModel().getValueAt(i, 5);
                String endTime = "" + ticketsTable.getModel().getValueAt(i, 6);

                // Replace T with new line
                ticketsTable.getModel().setValueAt(startTime.replace('T', '\n'), i, 5);
                ticketsTable.getModel().setValueAt(endTime.replace('T', '\n'), i, 6);
            }
            

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void clearButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearButtonMouseEntered
        clearButton.setBackground(new Color(44, 45, 52));
    }//GEN-LAST:event_clearButtonMouseEntered

    private void clearButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearButtonMouseExited
        clearButton.setBackground(new Color(21, 22, 29));
    }//GEN-LAST:event_clearButtonMouseExited

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        refreshTable();
        searchTextField.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void exportButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportButtonMouseEntered
        exportButton.setBackground(new Color(68, 149, 255));
    }//GEN-LAST:event_exportButtonMouseEntered

    private void exportButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportButtonMouseExited
        exportButton.setBackground(new Color(21, 122, 255));
    }//GEN-LAST:event_exportButtonMouseExited

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        
        // Get the selected row
        int rowNum = ticketsTable.getSelectedRow();
        
        if(rowNum < 0){
            JOptionPane.showMessageDialog(null, "Please choose a ticket to export it", "Exporting Tickets", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Get the selected ticket ID
        int selectedTicketID = (Integer) ticketsTable.getModel().getValueAt(rowNum, 0);

        // Create a folder
        String folderName = "Customers" + System.getProperty("file.separator") + CustomerSystem.getCurrentCustomer().getUsername() + System.getProperty("file.separator");
        
        File folder = new File(folderName);
        folder.mkdirs(); 
     
        // File name
        String file_name = folderName + System.getProperty("file.separator") + "Ticket_" + selectedTicketID + ".txt";

        // Open the file
        try{            
            output = new Formatter(file_name);
        }catch(SecurityException e){
            JOptionPane.showMessageDialog(null, "The program was unable to write a file.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "The program was unable to open a file.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Write ticket information into the file
        for(int i = 0; i < ticketsTable.getColumnCount(); i++){
            try{
                
                output.format("%s%n", "|--------------------------------------------------------------------------------|");
                output.format("|%-80s|%n", "");
                String value = ticketsTable.getModel().getValueAt(rowNum, i) + "";
               
                    output.format("|%-80s|\n", ticketsTable.getColumnName(i) + ": " + value.replace("\n", " "));
                
                output.format("|%-80s|%n", "");
            }catch(FormatterClosedException e){
                JOptionPane.showMessageDialog(null, "Error writing to the file.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }catch(NoSuchElementException e){
                JOptionPane.showMessageDialog(null, "Invalid input cannot write to the file.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        // Show success message
        Path path = Paths.get(file_name);
        JOptionPane.showMessageDialog(null, "File created successfully at " + path.toAbsolutePath(), "File Created Successfully", JOptionPane.INFORMATION_MESSAGE);
        
        // Close the file
        if(output != null){
            output.close();
        }
    }//GEN-LAST:event_exportButtonActionPerformed

    private void cancelButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseEntered
        cancelButton.setBackground(new Color(250,125,126));
    }//GEN-LAST:event_cancelButtonMouseEntered

    private void cancelButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseExited
        cancelButton.setBackground(new Color(249,93,94));
    }//GEN-LAST:event_cancelButtonMouseExited

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // Get the selected row
        int selectedRow = ticketsTable.getSelectedRow();
        if(selectedRow >=0){
            
            // Show confirmation dialog
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Deleting record", JOptionPane.YES_NO_OPTION);
            
            // If the user chose yes
            if(confirm == JOptionPane.YES_OPTION){
                
                // Get the selected ticket id from the table
                int selectedTicketID = (Integer) ticketsTable.getModel().getValueAt(selectedRow, 0);
                
                // Get the show ID of the selected ticket
                int selectedShowID = (Integer) ticketsTable.getModel().getValueAt(selectedRow, 2);
                
                // Delete SQL statement
                final String DELETE_QUERY = "DELETE FROM Tickets WHERE Ticket_ID = ?";
                
                // Update the number of available seats
                final String UPDATE_AFTER_DELETE = "UPDATE Shows SET Available_Seats = Available_Seats + 1 WHERE Show_ID = ? ";

                try(PreparedStatement deleteStatement = CustomerSystem.connection.prepareStatement(DELETE_QUERY);
                        PreparedStatement updateStatement = CustomerSystem.connection.prepareStatement(UPDATE_AFTER_DELETE);)
                {
                    // Set the values
                    deleteStatement.setInt(1, selectedTicketID);
                    updateStatement.setInt(1, selectedShowID);
                    
                    // If the record is deleted show success message and refresh the table
                    if(deleteStatement.executeUpdate() == 1 && updateStatement.executeUpdate() == 1){
                        JOptionPane.showMessageDialog(null, "The record has been deleting successfully", "Deleting record", JOptionPane.INFORMATION_MESSAGE);
                        refreshTable();
                    }
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Please choose a record to delete it", "Deleting record", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_cancelButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton clearButton;
    private static com.raven.datechooser.DateChooser dateChooser;
    private javax.swing.JButton exportButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private static javax.swing.JComboBox<String> orderComboBox;
    private javax.swing.JButton searchButton;
    private javax.swing.JComboBox<String> searchComboBox;
    private javax.swing.JTextField searchTextField;
    protected static javax.swing.JTable ticketsTable;
    // End of variables declaration//GEN-END:variables
}
