
package system_final;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import com.myapp.session.UserSession;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;
public class Recruiter_Query extends javax.swing.JInternalFrame {

    private JPanel seekersPanel;  // Panel to display the seekers who applied
    private JScrollPane scrollPane;  // ScrollPane to make the list scrollable
    
    public Recruiter_Query() {
        initComponents();  // Initialize the GUI components
       String recruiterEmail = UserSession.getEmail();  // Get the logged-in recruiter's email
    if (recruiterEmail == null || recruiterEmail.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No recruiter is logged in.");
        return;
    }

    // Database connection setup
    String url = "jdbc:mysql://localhost:3306/job_finder_database";
    String user = "root";
    String password = "";

    try (Connection conn = DriverManager.getConnection(url, user, password)) {
        // First, get the recruiter ID from the email
        String recruiterIdQuery = "SELECT recruiter_id FROM user_recruiter WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(recruiterIdQuery)) {
            stmt.setString(1, recruiterEmail);
            try (ResultSet rs = stmt.executeQuery()) {
                int recruiterId = -1;
                if (rs.next()) {
                    recruiterId = rs.getInt("recruiter_id");
                }

                // If no recruiter ID found, show an error message
                if (recruiterId == -1) {
                    JOptionPane.showMessageDialog(this, "Recruiter not found.");
                    return;
                }

                // Now fetch the job titles for the recruiter’s job posts
                String query = "SELECT job_title FROM job_post WHERE recruiter_id = ?";
                try (PreparedStatement pst = conn.prepareStatement(query)) {
                    pst.setInt(1, recruiterId);  // Set the recruiter ID parameter
                    try (ResultSet rs2 = pst.executeQuery()) {
                        // Clear the combo box before adding new items
                        JobTitle.removeAllItems();

                        // Loop through the result set and add each job title to the combo box
                        while (rs2.next()) {
                            String jobTitle = rs2.getString("job_title");
                            JobTitle.addItem(jobTitle);  // Add the job title to the combo box
                        }
                    }
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error retrieving job titles: " + e.getMessage());
    }
        JTableHeader header = Table.getTableHeader();
        Font headerFont = new Font("Arial", Font.BOLD, 16); // Set your desired font, style, and size
        header.setFont(headerFont);
        Table.setRowHeight(30);  // Set the row height to 30 pixels (adjust as needed)
        Table.getTableHeader().setReorderingAllowed(false);
        Table.setDefaultEditor(Object.class, null);
         
        displaySeekersWhoApplied();
        Status.addActionListener(e -> displaySeekersWhoApplied());
        // Remove the default title bar
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }

    private void displaySeekersWhoApplied() {
        String selectedJobTitle = (String) JobTitle.getSelectedItem(); // Job title combo box
        String selectedStatus = (String) Status.getSelectedItem(); // Status combo box

        // If no job title is selected, exit the method
        if (selectedJobTitle == null || selectedJobTitle.isEmpty()) {
            return;
        }



        // Show the buttons based on the status
        if ("pending".equalsIgnoreCase(selectedStatus)) {
            Approve.setVisible(true);
            Reject.setVisible(true);
        } else {
            Approve.setVisible(false);
            Reject.setVisible(false);
        }

        String url = "jdbc:mysql://localhost:3306/job_finder_database";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Query to get job ID based on selected job title
            String jobIdQuery = "SELECT job_id FROM job_post WHERE job_title = ?";
            try (PreparedStatement stmt = conn.prepareStatement(jobIdQuery)) {
                stmt.setString(1, selectedJobTitle);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int jobId = rs.getInt("job_id");

                        // Query to get applicants based on job ID and selected status
                        String applicantsQuery =
                            "SELECT user_seeker.seeker_id, user_seeker.first_name, user_seeker.last_name, " +
                            "user_seeker.gender, user_seeker.pnumber, applications.status " +
                            "FROM user_seeker " +
                            "JOIN applications ON user_seeker.seeker_id = applications.seeker_id " +
                            "WHERE applications.job_id = ? AND applications.status = ?";

                        try (PreparedStatement pst = conn.prepareStatement(applicantsQuery)) {
                            pst.setInt(1, jobId);
                            pst.setString(2, selectedStatus);

                            try (ResultSet rs2 = pst.executeQuery()) {
                                DefaultTableModel model = new DefaultTableModel(
                                    new String[]{"Id", "Last Name", "First Name", "Gender", "Phone Number", "Status"}, 0);

                                while (rs2.next()) {
                                    model.addRow(new Object[]{
                                        rs2.getInt("seeker_id"),
                                        rs2.getString("last_name"),
                                        rs2.getString("first_name"),
                                        rs2.getString("gender"),
                                        rs2.getString("pnumber"),
                                        rs2.getString("status")
                                    });
                                }

                                Table.setModel(model);
                                configureTableRenderers(); // Center-align table content
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "No job post found for the selected title.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving applicants: " + e.getMessage());
        }
    }
    
    // Method to configure table renderers
    private void configureTableRenderers() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < Table.getColumnCount(); i++) {
            Table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        Table.setRowHeight(30);
        Table.getTableHeader().setReorderingAllowed(false);
        JTableHeader header = Table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        Approve = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Reject = new javax.swing.JButton();
        JobTitle = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        Status = new javax.swing.JComboBox<>();

        setMinimumSize(new java.awt.Dimension(780, 520));
        setPreferredSize(new java.awt.Dimension(780, 520));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        Table.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "LAST NAME", "FIRST NAME", "GENDER", "CONTACT NUMBER", "JOB STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Table);
        if (Table.getColumnModel().getColumnCount() > 0) {
            Table.getColumnModel().getColumn(2).setPreferredWidth(30);
        }

        Approve.setBackground(new java.awt.Color(0, 0, 0));
        Approve.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        Approve.setForeground(new java.awt.Color(255, 255, 255));
        Approve.setText("APPROVE");
        Approve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApproveActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel1.setText("POST TITLE");

        Reject.setBackground(new java.awt.Color(0, 0, 0));
        Reject.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        Reject.setForeground(new java.awt.Color(255, 255, 255));
        Reject.setText("REJECT");
        Reject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RejectActionPerformed(evt);
            }
        });

        JobTitle.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        JobTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JobTitleActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel2.setText("STATUS");

        Status.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pending", "approved", "rejected", " " }));
        Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(JobTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Reject, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Approve)))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(JobTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Approve, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reject, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApproveActionPerformed
        // Check if a job title is selected
        String selectedJobTitle = (String) JobTitle.getSelectedItem();
        if (selectedJobTitle == null || selectedJobTitle.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a job title.");
            return;
        }

        // Get the selected row index from the JTable
        int selectedRow = Table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a seeker.");
            return;
        }

        // Get the seeker ID from the selected row
        int seekerId = (int) Table.getValueAt(selectedRow, 0); // Assuming seeker_id is in the first column
        String firstName = (String) Table.getValueAt(selectedRow, 2); // Assuming first_name is in the third column
        String lastName = (String) Table.getValueAt(selectedRow, 1); // Assuming last_name is in the second column
        String fullName = firstName + " " + lastName; // Concatenate first and last name with a space

        // Update the status in the database
        String url = "jdbc:mysql://localhost:3306/job_finder_database"; // Your database URL
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Query to get job ID based on selected job title
            String jobIdQuery = "SELECT job_id FROM job_post WHERE job_title = ? LIMIT 1";
            try (PreparedStatement stmt = conn.prepareStatement(jobIdQuery)) {
                stmt.setString(1, selectedJobTitle);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int jobId = rs.getInt("job_id");

                        // SQL query to update the status
                        String updateQuery = "UPDATE applications SET status = 'approved' WHERE seeker_id = ? AND job_id = ?";
                        try (PreparedStatement pst = conn.prepareStatement(updateQuery)) {
                            pst.setInt(1, seekerId);  // Set the seeker_id parameter
                            pst.setInt(2, jobId);  // Set the job_id parameter
                            int rowsUpdated = pst.executeUpdate();

                            if (rowsUpdated > 0) {
                                JOptionPane.showMessageDialog(this, fullName + " 'Approved'");

                                // Update the JTable to reflect the changes
                                Table.setValueAt("approved", selectedRow, 5);  // Assuming the status column is at index 5
                            } else {
                                JOptionPane.showMessageDialog(this, "Failed to update status.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "No job post found for the selected title.");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating status.");
        }
    }//GEN-LAST:event_ApproveActionPerformed

    private void RejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RejectActionPerformed
        // TODO add your handling code here:
        // Check if a job title is selected
        String selectedJobTitle = (String) JobTitle.getSelectedItem();
        if (selectedJobTitle == null || selectedJobTitle.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a job title.");
            return;
        }

        // Get the selected row index from the JTable
        int selectedRow = Table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a seeker.");
            return;
        }

        // Get the seeker ID from the selected row
        int seekerId = (int) Table.getValueAt(selectedRow, 0); // Assuming seeker_id is in the first column
        String firstName = (String) Table.getValueAt(selectedRow, 2); // Assuming first_name is in the third column
        String lastName = (String) Table.getValueAt(selectedRow, 1); // Assuming last_name is in the second column
        String fullName = firstName + " " + lastName; // Concatenate first and last name with a space

        // Update the status in the database
        String url = "jdbc:mysql://localhost:3306/job_finder_database"; // Your database URL
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Query to get job ID based on selected job title
            String jobIdQuery = "SELECT job_id FROM job_post WHERE job_title = ? LIMIT 1";
            try (PreparedStatement stmt = conn.prepareStatement(jobIdQuery)) {
                stmt.setString(1, selectedJobTitle);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int jobId = rs.getInt("job_id");

                        // SQL query to update the status
                        String updateQuery = "UPDATE applications SET status = 'rejected' WHERE seeker_id = ? AND job_id = ?";
                        try (PreparedStatement pst = conn.prepareStatement(updateQuery)) {
                            pst.setInt(1, seekerId);  // Set the seeker_id parameter
                            pst.setInt(2, jobId);  // Set the job_id parameter
                            int rowsUpdated = pst.executeUpdate();

                            if (rowsUpdated > 0) {
                                JOptionPane.showMessageDialog(this, fullName + " 'Rejected'");

                                // Update the JTable to reflect the changes
                                Table.setValueAt("approved", selectedRow, 5);  // Assuming the status column is at index 5
                            } else {
                                JOptionPane.showMessageDialog(this, "Failed to update status.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "No job post found for the selected title.");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating status.");
        }
    }//GEN-LAST:event_RejectActionPerformed

    private void JobTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JobTitleActionPerformed
        
    }//GEN-LAST:event_JobTitleActionPerformed

    private void StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Approve;
    private javax.swing.JComboBox<String> JobTitle;
    private javax.swing.JButton Reject;
    private javax.swing.JComboBox<String> Status;
    private javax.swing.JTable Table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
