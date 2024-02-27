/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Customers;

import CustomJTable.table.TableCustom;
import com.raven.datechooser.SelectedDate;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author anasm
 */
public class BookingForm extends javax.swing.JInternalFrame {

    /**
     * Creates new form BookingForm
     */
    // Store movies ID
    private static ArrayList<Integer> moviesID;
    
    public BookingForm() {
        initComponents();
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        dateField.setEnabled(false);
        
        numOfTicketsField.setEditable(false);
    }
    
    protected void loadMoviesIntoComboBox(){
        final String getMoviesQuery = "SELECT Movie_ID, Movie_Name FROM Movies";
        try(        
            Statement selectMovies = CustomerSystem.connection.createStatement();
            ResultSet movies = selectMovies.executeQuery(getMoviesQuery);
            ){
            
            // Store movies' id
            moviesID = new ArrayList<>();
            
            // Clear the old movies
            moviesComboBox.removeAllItems();;
            
            // Update the combobox
            moviesComboBox.addItem("Any Movie");
            while(movies.next()){
                moviesID.add(movies.getInt(1));
                moviesComboBox.addItem(movies.getString(2));
            }    
             
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
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
        String allshowsQuery = "SELECT Show_ID, Movie_Name, CONCAT(Theater_Number, \' | \', Theater_Type) AS Theater, Start_Time, End_Time, Price, Available_Seats FROM Show_Times ";
        
        // Delete the expire shows
        deleteExpireShows();
        
        // Get the selected date and change its format
        SelectedDate selectedDate = dateChooser.getSelectedDate();
        String selectedDateFormatting = selectedDate.getYear() + "-" + selectedDate.getMonth() + "-" + selectedDate.getDay();
        
        // Movie filter
        boolean movieFilter = false;
        if(moviesComboBox.getSelectedIndex() > 0){
            allshowsQuery = allshowsQuery + " WHERE Movie_ID = " +moviesID.get(moviesComboBox.getSelectedIndex() - 1);
            movieFilter = true;
        }
        
        // Date filter
        if(dateFilter.getSelectedIndex() == 1 && movieFilter){
            allshowsQuery = allshowsQuery + " AND DATEDIFF(Start_Time, \'" + selectedDateFormatting + "\' ) = 0 ";
        }else if(dateFilter.getSelectedIndex() == 1){
            allshowsQuery = allshowsQuery + " WHERE DATEDIFF(Start_Time, \'" + selectedDateFormatting + "\' ) = 0 ";
        }
        
        // Check for the order combobox 
        if(orderComboBox.getSelectedItem() == "The nearest"){
            allshowsQuery = allshowsQuery + " ORDER BY Start_Time ASC";
        }else if(orderComboBox.getSelectedItem() == "Price: High to low"){
            allshowsQuery = allshowsQuery + " ORDER BY Price DESC";
        }else if(orderComboBox.getSelectedItem() == "Price: Low to high"){
            allshowsQuery = allshowsQuery + " ORDER BY Price ASC";
        }   

        // Get the shows and refresh the table
        try(
                    Statement selectAllShows = CustomerSystem.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
        int numOfTickets;

        // Check if the number of tickets is a number
        try{
            // Check if it is an integer
            numOfTickets = Integer.parseInt(numOfTicketsField.getText());

            // Get the selected show
            int selectedShow = showsTable.getSelectedRow();
            
            // Calculate the total price
            if(selectedShow >= 0){
                String showPriceStr = showsTable.getModel().getValueAt(selectedShow, 5) + "";
                double showPriceDou = Double.parseDouble(showPriceStr);
                double calculatedTotalPrice = numOfTickets * showPriceDou;
                totalPrice.setText(calculatedTotalPrice + "");
            } 
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Number of tickets must be a valid number between 1 and 10", "Number of tickets", JOptionPane.WARNING_MESSAGE);

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

        dateChooser = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        showsTable = new javax.swing.JTable();
        orderComboBox = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        decrementButton = new javax.swing.JButton();
        numOfTicketsField = new javax.swing.JTextField();
        incrementButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        totalPrice = new javax.swing.JLabel();
        actionButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        moviesComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dateFilter = new javax.swing.JComboBox<>();
        dateField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        dateChooser.setForeground(new java.awt.Color(21, 122, 255));
        dateChooser.setTextRefernce(dateField);

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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
        orderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "The nearest", "Price: High to low", "Price: Low to high" }));
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

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

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
        jPanel3.add(decrementButton);

        numOfTicketsField.setBackground(new java.awt.Color(246, 247, 249));
        numOfTicketsField.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        numOfTicketsField.setForeground(new java.awt.Color(21, 22, 29));
        numOfTicketsField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        numOfTicketsField.setText("1");
        numOfTicketsField.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        jPanel3.add(numOfTicketsField);

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
        jPanel3.add(incrementButton);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(130, 142, 156));
        jLabel2.setText("Tickets");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(21, 22, 29));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Customers/images/icons8_billing_machine_28px.png"))); // NOI18N
        jLabel5.setText("Total Price:");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jLabel5.setOpaque(true);

        totalPrice.setBackground(new java.awt.Color(21, 122, 255));
        totalPrice.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totalPrice.setForeground(new java.awt.Color(255, 255, 255));
        totalPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalPrice.setToolTipText("");
        totalPrice.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(totalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        actionButton.setBackground(new java.awt.Color(21, 122, 255));
        actionButton.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        actionButton.setForeground(new java.awt.Color(255, 255, 255));
        actionButton.setText("Book");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(orderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(75, 75, 75)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(actionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 939, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(orderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(actionButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        jPanel2.setBackground(new java.awt.Color(21, 122, 255));

        moviesComboBox.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        moviesComboBox.setForeground(new java.awt.Color(21, 22, 29));
        moviesComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Movie" }));
        moviesComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        moviesComboBox.setFocusable(false);
        moviesComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moviesComboBoxMouseClicked(evt);
            }
        });
        moviesComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moviesComboBoxActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Movie");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Date");

        dateFilter.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        dateFilter.setForeground(new java.awt.Color(21, 22, 29));
        dateFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Time", "Choose Date" }));
        dateFilter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dateFilter.setFocusable(false);
        dateFilter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                dateFilterItemStateChanged(evt);
            }
        });
        dateFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateFilterActionPerformed(evt);
            }
        });

        dateField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        dateField.setForeground(new java.awt.Color(21, 22, 29));
        dateField.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dateField.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        dateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateFieldActionPerformed(evt);
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
        searchButton.setIconTextGap(10);
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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MyCinema");

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(moviesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateFilter, 0, 208, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(moviesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void moviesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moviesComboBoxActionPerformed
        
    }//GEN-LAST:event_moviesComboBoxActionPerformed

    private void dateFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateFilterActionPerformed
        if(dateFilter.getSelectedIndex() == 1){
            dateField.setEnabled(true);
        }else{
            dateField.setEnabled(false);
        }
    }//GEN-LAST:event_dateFilterActionPerformed

    private void showsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showsTableMouseClicked
        updateTotalPrice();
    }//GEN-LAST:event_showsTableMouseClicked

    private void orderComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderComboBoxActionPerformed
        refreshTable();
    }//GEN-LAST:event_orderComboBoxActionPerformed

    private void moviesComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moviesComboBoxMouseClicked
        
    }//GEN-LAST:event_moviesComboBoxMouseClicked

    private void dateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateFieldActionPerformed
        
    }//GEN-LAST:event_dateFieldActionPerformed

    private void searchButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseEntered
        searchButton.setBackground(new Color(44, 45, 52));

    }//GEN-LAST:event_searchButtonMouseEntered

    private void searchButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseExited
        searchButton.setBackground(new Color(21, 22, 29));
    }//GEN-LAST:event_searchButtonMouseExited

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        refreshTable();
    }//GEN-LAST:event_searchButtonActionPerformed

    private void dateFilterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dateFilterItemStateChanged

    }//GEN-LAST:event_dateFilterItemStateChanged

    private void incrementButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_incrementButtonMouseEntered
        incrementButton.setBackground(new Color(68, 149, 255));

    }//GEN-LAST:event_incrementButtonMouseEntered

    private void decrementButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decrementButtonMouseExited
        decrementButton.setBackground(new Color(21, 122, 255));
    }//GEN-LAST:event_decrementButtonMouseExited

    private void incrementButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_incrementButtonMouseExited
        incrementButton.setBackground(new Color(21, 122, 255));

    }//GEN-LAST:event_incrementButtonMouseExited

    private void decrementButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decrementButtonMouseEntered
        decrementButton.setBackground(new Color(68, 149, 255));
    }//GEN-LAST:event_decrementButtonMouseEntered

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


        int selectedRow = showsTable.getSelectedRow();
        // Check if the there is a selected show
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(null, "Please choose a show", "Choose show", JOptionPane.WARNING_MESSAGE);
            return;
        }

        
        int numOfTickets = 0;

        // Check if the theater number is a number and positive
        try{
            // Get the number of tickets
            numOfTickets = Integer.parseInt(numOfTicketsField.getText());

        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Number of tickets must be a valid number between 1 and 10", "Number of tickets", JOptionPane.WARNING_MESSAGE);
            return;
        }


        // Get the username of the selected customer
        String selectedCustomerUser = CustomerSystem.getCurrentCustomer().getUsername();

        // SQL Statement: Insert
        final String INSERT_TICKET = "INSERT INTO Tickets(Customer_Username, Show_ID) VALUES(?, ?)";

        // To store the success message
        String successMessage = "You successfully created your booking!";

        // To store the success message title
        String successTitle = "Ticket Booking";

        // Get the ID of the selected show
        int selectedShowID= (Integer) showsTable.getModel().getValueAt(selectedRow, 0);

        // Get the available seats of the selected show
        final String availableSeatsBefore = "SELECT Available_Seats FROM Shows WHERE Show_ID = " + selectedShowID;

        // Decrement the number of available seats
        final String avilableSeatsAfter = "UPDATE Shows SET Available_Seats = Available_Seats - ? WHERE Show_ID = " + selectedShowID;

        // Insert the new ticket
        try(
            PreparedStatement newTicket = CustomerSystem.connection.prepareStatement(INSERT_TICKET);
            Statement getAvailableSeats = CustomerSystem.connection.createStatement();
            ResultSet availableSeatsResultSet = getAvailableSeats.executeQuery(availableSeatsBefore);
            PreparedStatement updateAvailableSeats = CustomerSystem.connection.prepareStatement(avilableSeatsAfter);
        ){

            // Check if there are enough seats
            availableSeatsResultSet.next();
            int showAvailableSeats = availableSeatsResultSet.getInt(1);

            if(showAvailableSeats >= numOfTickets){
                for(int i = 0; i < numOfTickets; i++){
                    // Set the values
                    newTicket.setString(1, selectedCustomerUser);
                    newTicket.setInt(2, selectedShowID);

                    // Execute the update
                    newTicket.executeUpdate();
                }

                // Show success message
                JOptionPane.showMessageDialog(null, successMessage, successTitle, JOptionPane.INFORMATION_MESSAGE);

                // Update the number of available seats
                updateAvailableSeats.setInt(1, numOfTickets);
                updateAvailableSeats.executeUpdate();

                // Clear the form
                numOfTicketsField.setText("1");

                // Update the price
                totalPrice.setText("");
                
                // Refresh the table of shows
                refreshTable();
            }else{
                JOptionPane.showMessageDialog(null, "Sorry, There are not enough seats", "No enough seats", JOptionPane.WARNING_MESSAGE);
            }

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }

        
    }//GEN-LAST:event_actionButtonActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actionButton;
    private static com.raven.datechooser.DateChooser dateChooser;
    private javax.swing.JTextField dateField;
    private static javax.swing.JComboBox<String> dateFilter;
    private javax.swing.JButton decrementButton;
    private javax.swing.JButton incrementButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private static javax.swing.JComboBox<String> moviesComboBox;
    private javax.swing.JTextField numOfTicketsField;
    private static javax.swing.JComboBox<String> orderComboBox;
    private javax.swing.JButton searchButton;
    protected static javax.swing.JTable showsTable;
    private javax.swing.JLabel totalPrice;
    // End of variables declaration//GEN-END:variables
}
