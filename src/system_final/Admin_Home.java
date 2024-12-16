
package system_final;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class Admin_Home extends javax.swing.JInternalFrame {


    public Admin_Home() {
        initComponents();  // Initialize the GUI components
            this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
            ui.setNorthPane(null);

            //Set up the ComboBox ActionListener
            List.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call displayAllRecords based on the selected item
                displayAllRecords();
            }
        });

        JTableHeader header = Table.getTableHeader();
        Font headerFont = new Font("Arial", Font.BOLD, 16); // Set your desired font, style, and size
        header.setFont(headerFont);
        Table.setRowHeight(30);  // Set the row height to 30 pixels (adjust as needed)
        Table.getTableHeader().setReorderingAllowed(false);
        Table.setDefaultEditor(Object.class, null);

        displayAllRecords();
        }

        private void displayAllRecords() {
        String url = "jdbc:mysql://localhost:3306/job_finder_database";
        String user = "root";
        String password = "";

        String selectedFilter = List.getSelectedItem().toString();  // Get the selected option from the ComboBox

        String applicantsQuery;

        if (selectedFilter.equals("All Records")) {
            applicantsQuery = 
                "SELECT seeker_id AS user_id, CONCAT(first_name, ' ', last_name) AS name, email, password, 'Seeker' AS user_type " +
                "FROM user_seeker " +
                "UNION " +
                "SELECT recruiter_id AS user_id, company_name AS name, email, password, 'Recruiter' AS user_type " +
                "FROM user_recruiter";
        } else if (selectedFilter.equals("Seeker Records")) {
            applicantsQuery = 
                "SELECT seeker_id AS user_id, first_name, last_name, gender, CONCAT(month, ' ', day, ' ',  year) AS birthday, email, address, pnumber, password " +
                "FROM user_seeker";
        } else if (selectedFilter.equals("Recruiter Records")) {
            applicantsQuery = 
                "SELECT recruiter_id AS user_id, company_name, address, email, pnumber, password " +
                "FROM user_recruiter";
        } else {
            applicantsQuery = ""; // Empty query if no valid selection is made (although this should not happen)
        }

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            try (PreparedStatement pst = conn.prepareStatement(applicantsQuery)) {
                try (ResultSet rs = pst.executeQuery()) {
                    DefaultTableModel model;

                    // Check which filter was selected and prepare the table model accordingly
                    if (selectedFilter.equals("All Records")) {
                        model = new DefaultTableModel(
                            new String[]{"User_Id", "Name", "User_Type", "Email", "Password"}, 0);
                    } else if (selectedFilter.equals("Seeker Records")) {
                        model = new DefaultTableModel(
                            new String[]{"Seeker_Id", "First Name", "Last Name", "Gender", "Birthday", "Email", "Address", "Phone Number", "Password"}, 0);
                    } else {
                        model = new DefaultTableModel(
                            new String[]{"Recruiter_Id", "Company Name",  "Address", "Email", "Phone Number", "Password"}, 0);
                    }

                    while (rs.next()) {
                        // Add data to table based on the selected filter
                        if (selectedFilter.equals("All Records")) {
                            model.addRow(new Object[]{
                                rs.getInt("user_id"),  // User ID
                                rs.getString("name"),   // Full name or company name
                                rs.getString("user_type"),  // User type (Seeker or Recruiter)
                                rs.getString("email"),  // Email
                                rs.getString("password")  // Password
                            });
                        } else if (selectedFilter.equals("Seeker Records")) {
                            model.addRow(new Object[]{
                                rs.getInt("user_id"),  // Seeker ID
                                rs.getString("first_name"),  // First Name
                                rs.getString("last_name"),   // Last Name
                                rs.getString("gender"),      // Gender
                                rs.getString("birthday"),    // Year
                                rs.getString("email"),       // Email
                                rs.getString("address"),     // Address
                                rs.getString("pnumber"),     // Phone Number
                                rs.getString("password")     // Password
                            });
                        } else if (selectedFilter.equals("Recruiter Records")) {
                            model.addRow(new Object[]{
                                rs.getInt("user_id"),  // Recruiter ID
                                rs.getString("company_name"),  // Company Name
                                rs.getString("address"),  // Address
                                rs.getString("email"),    // Email
                                rs.getString("pnumber"),  // Phone Number
                                rs.getString("password") // Password
                            });
                        }
                    }

                    Table.setModel(model);
                    configureTableRenderers(); // Center-align table content

                    // Adjust column widths based on the selected filter
                    if (selectedFilter.equals("All Records")) {
                        setColumnWidthsForAllRecords();
                    } else if (selectedFilter.equals("Seeker Records")) {
                        setColumnWidthsForSeekerRecords();
                    } else if (selectedFilter.equals("Recruiter Records")) {
                        setColumnWidthsForRecruiterRecords();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving accounts: " + e.getMessage());
        }
    }
    
    private void setColumnWidthsForAllRecords() {
        Table.setRowHeight(30);
        Table.getColumnModel().getColumn(0).setPreferredWidth(70);  // User_Id
        Table.getColumnModel().getColumn(1).setPreferredWidth(250); // Name
        Table.getColumnModel().getColumn(2).setPreferredWidth(100); // User_Type
        Table.getColumnModel().getColumn(3).setPreferredWidth(250); // Email
        Table.getColumnModel().getColumn(4).setPreferredWidth(180); // Password
    }

    private void setColumnWidthsForSeekerRecords() {
        Table.setRowHeight(30);
        Table.getColumnModel().getColumn(0).setPreferredWidth(100);  // Seeker_Id
        Table.getColumnModel().getColumn(1).setPreferredWidth(150); // First Name
        Table.getColumnModel().getColumn(2).setPreferredWidth(150); // Last Name
        Table.getColumnModel().getColumn(3).setPreferredWidth(100); // Gender
        Table.getColumnModel().getColumn(4).setPreferredWidth(150);  // Birthday
        Table.getColumnModel().getColumn(5).setPreferredWidth(200); // Email
        Table.getColumnModel().getColumn(6).setPreferredWidth(400); // Address
        Table.getColumnModel().getColumn(7).setPreferredWidth(120); // Phone Number
        Table.getColumnModel().getColumn(8).setPreferredWidth(150); // Password
    }

    private void setColumnWidthsForRecruiterRecords() {
        Table.setRowHeight(30);
        Table.getColumnModel().getColumn(0).setPreferredWidth(120);  // Recruiter_Id
        Table.getColumnModel().getColumn(1).setPreferredWidth(200); // Company Name
        Table.getColumnModel().getColumn(2).setPreferredWidth(400); // Address
        Table.getColumnModel().getColumn(3).setPreferredWidth(200); // Email
        Table.getColumnModel().getColumn(4).setPreferredWidth(120); // Phone Number
        Table.getColumnModel().getColumn(5).setPreferredWidth(150); // Password
    }


    private void configureTableRenderers() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < Table.getColumnCount(); i++) {
            Table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        Table.setRowHeight(30);
        // Set preferred widths for each column based on the table's structure
        for (int i = 0; i < Table.getColumnCount(); i++) {
            Table.getColumnModel().getColumn(i).setPreferredWidth(150);  // Adjust width as needed
        }
        Table.getTableHeader().setReorderingAllowed(false);
        JTableHeader header = Table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));

        revalidate(); // Revalidate the panel to ensure it properly resize
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DesktopPane1 = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        List = new javax.swing.JComboBox<>();
        delete_btn = new javax.swing.JButton();
        EDIT = new javax.swing.JButton();
        account_lb = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(780, 520));
        setPreferredSize(new java.awt.Dimension(767, 531));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        Table.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "User_Id", "Name", "User_Type", "Email", "Password"
            }
        ));
        jScrollPane1.setViewportView(Table);

        List.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        List.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Records", "Seeker Records", "Recruiter Records" }));

        delete_btn.setBackground(new java.awt.Color(0, 0, 0));
        delete_btn.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        delete_btn.setForeground(new java.awt.Color(255, 255, 255));
        delete_btn.setText("DELETE");
        delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnActionPerformed(evt);
            }
        });

        EDIT.setBackground(new java.awt.Color(0, 0, 0));
        EDIT.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        EDIT.setForeground(new java.awt.Color(255, 255, 255));
        EDIT.setText("EDIT");
        EDIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EDITActionPerformed(evt);
            }
        });

        account_lb.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        account_lb.setText("ACCOUNTS");

        DesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DesktopPane1.setLayer(List, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DesktopPane1.setLayer(delete_btn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DesktopPane1.setLayer(EDIT, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DesktopPane1.setLayer(account_lb, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout DesktopPane1Layout = new javax.swing.GroupLayout(DesktopPane1);
        DesktopPane1.setLayout(DesktopPane1Layout);
        DesktopPane1Layout.setHorizontalGroup(
            DesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DesktopPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(EDIT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DesktopPane1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(account_lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(List, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        DesktopPane1Layout.setVerticalGroup(
            DesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DesktopPane1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(DesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(List, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(account_lb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(DesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EDIT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(DesktopPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(DesktopPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EDITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EDITActionPerformed
       int selectedRow = Table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a user to edit.");
        return;
    }

    // Get the user ID and user type from the selected row
    int userId = (int) Table.getValueAt(selectedRow, 0); // Assuming user_id is in the first column
    String userType = (String) Table.getValueAt(selectedRow, 2); // Assuming user_type is in the third column

    // Call the appropriate update function based on user type
    if (userType.equals("Seeker")) {
        updateSeekerInformation(userId); // Call the Seeker update method
    } else if (userType.equals("Recruiter")) {
        updateRecruiterInformation(userId); // Call the Recruiter update method
    } else {
        JOptionPane.showMessageDialog(this, "Unknown user type. Cannot edit information.");
    }
    }//GEN-LAST:event_EDITActionPerformed

    private void delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnActionPerformed
         // Get the selected row from the table
        int selectedRow = Table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.");
            return;
        }

        // Get the user ID and user type from the selected row
        int userId = (int) Table.getValueAt(selectedRow, 0); // Assuming user_id is in the first column
        String userType = (String) Table.getValueAt(selectedRow, 2); // Assuming user_type is in the third column

        // Confirm the deletion
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Define the SQL queries
        String url = "jdbc:mysql://localhost:3306/job_finder_database";
        String user = "root";
        String password = "";

        String deleteUserQuery = "";
        String deleteApplicationsQuery = "DELETE FROM applications WHERE seeker_id = ?";  // For Seeker
        String deleteJobPostsQuery = "DELETE FROM job_post WHERE recruiter_id = ?";  // For Recruiter
        String deleteApplicationsForRecruiterQuery = "DELETE FROM applications WHERE job_id IN (SELECT job_id FROM job_post WHERE recruiter_id = ?)";  // For Recruiter

        // Set the delete query for user based on user type
        if (userType.equals("Seeker")) {
            deleteUserQuery = "DELETE FROM user_seeker WHERE seeker_id = ?";
        } else if (userType.equals("Recruiter")) {
            deleteUserQuery = "DELETE FROM user_recruiter WHERE recruiter_id = ?";
        } else {
            JOptionPane.showMessageDialog(this, "Unknown user type. Cannot delete record.");
            return;
        }

        // Execute the deletion
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Start transaction
            conn.setAutoCommit(false);

            try (
                PreparedStatement pstApp = conn.prepareStatement(deleteApplicationsQuery);
                PreparedStatement pstJobPosts = conn.prepareStatement(deleteJobPostsQuery);
                PreparedStatement pstAppForRecruiter = conn.prepareStatement(deleteApplicationsForRecruiterQuery);
                PreparedStatement pstUser = conn.prepareStatement(deleteUserQuery)
            ) {
                // If the user is a Seeker, delete related applications
                if (userType.equals("Seeker")) {
                    pstApp.setInt(1, userId);
                    pstApp.executeUpdate();
                }

                // If the user is a Recruiter, delete related job posts and applications
                if (userType.equals("Recruiter")) {
                    pstJobPosts.setInt(1, userId);
                    pstJobPosts.executeUpdate();

                    pstAppForRecruiter.setInt(1, userId);
                    pstAppForRecruiter.executeUpdate();
                }

                // Delete the user from the appropriate table
                pstUser.setInt(1, userId);
                int rowsAffected = pstUser.executeUpdate();

                if (rowsAffected > 0) {
                    conn.commit();
                    JOptionPane.showMessageDialog(this, "User and related data deleted successfully.");
                    displayAllRecords(); // Refresh the table to reflect changes
                } else {
                    conn.rollback();
                    JOptionPane.showMessageDialog(this, "Failed to delete user.");
                }
            } catch (SQLException e) {
                // Rollback on failure
                conn.rollback();
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting user: " + e.getMessage());
            } finally {
                // Restore default auto-commit behavior
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error: " + e.getMessage());
       }
    }//GEN-LAST:event_delete_btnActionPerformed

   
    
    // Method for updating Seeker information
    private void updateSeekerInformation(int userId) {
        String url = "jdbc:mysql://localhost:3306/job_finder_database";
        String user = "root";
        String password = "";

        String query = "SELECT first_name, last_name, gender, month, day, year, email, address, pnumber, password FROM user_seeker WHERE seeker_id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, userId);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    // Create the editable form components
                    JTextField firstNameField = new JTextField();
                    JTextField lastNameField = new JTextField();

                    // Gender combo box
                    String[] genders = {"Male", "Female", "Other"};
                    JComboBox<String> genderComboBox = new JComboBox<>(genders);
                    genderComboBox.setSelectedItem(rs.getString("gender"));

                    // Month combo box
                    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                    JComboBox<String> monthComboBox = new JComboBox<>(months);
                    monthComboBox.setSelectedItem(rs.getString("month"));

                    JTextField dayField = new JTextField();
                    JTextField yearField = new JTextField();
                    JTextField emailField = new JTextField();
                    JTextArea addressArea = new JTextArea();
                    addressArea.setWrapStyleWord(true);
                    addressArea.setLineWrap(true);
                    JTextField phoneField = new JTextField();
                    JTextField passwordField = new JTextField();

                    // Populate fields with existing data
                    firstNameField.setText(rs.getString("first_name"));
                    lastNameField.setText(rs.getString("last_name"));
                    dayField.setText(rs.getString("day"));
                    yearField.setText(rs.getString("year"));
                    emailField.setText(rs.getString("email"));
                    addressArea.setText(rs.getString("address"));
                    phoneField.setText(rs.getString("pnumber"));
                    passwordField.setText(rs.getString("password"));

                    // Save Changes button
                    JButton saveButton = new JButton("Save Changes");
                    saveButton.addActionListener(e -> {
                        String updatedFirstName = firstNameField.getText();
                        String updatedLastName = lastNameField.getText();
                        String updatedGender = (String) genderComboBox.getSelectedItem();
                        String updatedMonth = (String) monthComboBox.getSelectedItem();
                        String updatedDay = dayField.getText();
                        String updatedYear = yearField.getText();
                        String updatedEmail = emailField.getText();
                        String updatedAddress = addressArea.getText();
                        String updatedPhone = phoneField.getText();
                        String updatedPassword = passwordField.getText();

                        updateSeekerInDatabase(userId, updatedFirstName, updatedLastName, updatedGender, updatedMonth, updatedDay, updatedYear, updatedEmail, updatedAddress, updatedPhone, updatedPassword);
                    });

                    // Display the form
                    JPanel updatePanel = new JPanel();
                    updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.Y_AXIS));
                    updatePanel.add(new JLabel("First Name:"));
                    updatePanel.add(firstNameField);
                    updatePanel.add(new JLabel("Last Name:"));
                    updatePanel.add(lastNameField);
                    updatePanel.add(new JLabel("Gender:"));
                    updatePanel.add(genderComboBox);
                    updatePanel.add(new JLabel("Month:"));
                    updatePanel.add(monthComboBox);
                    updatePanel.add(new JLabel("Day:"));
                    updatePanel.add(dayField);
                    updatePanel.add(new JLabel("Year:"));
                    updatePanel.add(yearField);
                    updatePanel.add(new JLabel("Email:"));
                    updatePanel.add(emailField);
                    updatePanel.add(new JLabel("Address:"));
                    updatePanel.add(new JScrollPane(addressArea));
                    updatePanel.add(new JLabel("Phone Number:"));
                    updatePanel.add(phoneField);
                    updatePanel.add(new JLabel("Password:"));
                    updatePanel.add(passwordField);
                    updatePanel.add(saveButton);

                    JOptionPane.showMessageDialog(null, updatePanel, "Update Seeker Information", JOptionPane.PLAIN_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving user information: " + e.getMessage());
        }
    }


    private void updateRecruiterInformation(int userId) {
        String url = "jdbc:mysql://localhost:3306/job_finder_database";
        String user = "root";
        String password = "";

        String query = "SELECT company_name, address, email, pnumber, password FROM user_recruiter WHERE recruiter_id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, userId);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    // Create the editable form components
                    JTextField companyNameField = new JTextField();
                    JTextField emailField = new JTextField();
                    JTextArea addressArea = new JTextArea();
                    addressArea.setWrapStyleWord(true);
                    addressArea.setLineWrap(true);
                    JTextField phoneField = new JTextField();
                    JTextField passwordField = new JTextField();

                    // Populate fields with existing data
                    companyNameField.setText(rs.getString("company_name"));
                    emailField.setText(rs.getString("email"));
                    addressArea.setText(rs.getString("address"));
                    phoneField.setText(rs.getString("pnumber"));
                    passwordField.setText(rs.getString("password"));

                    // Save Changes button
                    JButton saveButton = new JButton("Save Changes");
                    saveButton.addActionListener(e -> {
                        String updatedCompanyName = companyNameField.getText();
                        String updatedEmail = emailField.getText();
                        String updatedAddress = addressArea.getText();
                        String updatedPhone = phoneField.getText();
                        String updatedPassword = passwordField.getText();

                        updateRecruiterInDatabase(userId, updatedCompanyName, updatedEmail, updatedAddress, updatedPhone, updatedPassword);
                    });

                    // Display the form
                    JPanel updatePanel = new JPanel();
                    updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.Y_AXIS));
                    updatePanel.add(new JLabel("Company Name:"));
                    updatePanel.add(companyNameField);
                    updatePanel.add(new JLabel("Email:"));
                    updatePanel.add(emailField);
                    updatePanel.add(new JLabel("Address:"));
                    updatePanel.add(new JScrollPane(addressArea));
                    updatePanel.add(new JLabel("Phone Number:"));
                    updatePanel.add(phoneField);
                    updatePanel.add(new JLabel("Password:"));
                    updatePanel.add(passwordField);
                    updatePanel.add(saveButton);

                    JOptionPane.showMessageDialog(null, updatePanel, "Update Recruiter Information", JOptionPane.PLAIN_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving user information: " + e.getMessage());
        }
    }

    private void updateSeekerInDatabase(int userId, String firstName, String lastName, String gender, String month, String day, String year, String email, String address, String phone, String password) {
        String url = "jdbc:mysql://localhost:3306/job_finder_database";
        String user = "root";
        String passwordDB = "";

        String updateQuery = "UPDATE user_seeker SET first_name = ?, last_name = ?, gender = ?, month = ?, day = ?, year = ?, email = ?, address = ?, pnumber = ?, password = ? WHERE seeker_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, passwordDB)) {
            try (PreparedStatement pst = conn.prepareStatement(updateQuery)) {
                pst.setString(1, firstName);
                pst.setString(2, lastName);
                pst.setString(3, gender);
                pst.setString(4, month);
                pst.setString(5, day);
                pst.setString(6, year);
                pst.setString(7, email);
                pst.setString(8, address);
                pst.setString(9, phone);
                pst.setString(10, password);
                pst.setInt(11, userId);

                int rowsUpdated = pst.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Seeker information updated successfully!");
                    displayAllRecords(); // Refresh the data in the table
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update Seeker.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating Seeker information: " + e.getMessage());
        }
    }

    // Method to update Recruiter information in the database
    private void updateRecruiterInDatabase(int userId, String companyName, String address, String email, String phone, String password) {
        String url = "jdbc:mysql://localhost:3306/job_finder_database";
        String user = "root";
        String passwordDB = "";

        String updateQuery = "UPDATE user_recruiter SET company_name = ?, address = ?, email = ?, pnumber = ?, password = ? WHERE recruiter_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, passwordDB)) {
            try (PreparedStatement pst = conn.prepareStatement(updateQuery)) {
                pst.setString(1, companyName);
                pst.setString(2, address);
                pst.setString(3, email);
                pst.setString(4, phone);
                pst.setString(5, password);
                pst.setInt(6, userId);

                int rowsUpdated = pst.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Recruiter information updated successfully!");
                    displayAllRecords(); // Refresh the data in the table
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update Recruiter.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating Recruiter information: " + e.getMessage());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DesktopPane1;
    private javax.swing.JButton EDIT;
    private javax.swing.JComboBox<String> List;
    private javax.swing.JTable Table;
    private javax.swing.JLabel account_lb;
    private javax.swing.JButton delete_btn;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
