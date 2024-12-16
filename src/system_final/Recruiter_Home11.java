
package system_final;

import com.myapp.session.UserSession;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.Color;

public class Recruiter_Home11 extends javax.swing.JInternalFrame {
    
    private JPanel dynamicPanel; // Panel to hold dynamic component
    
    Color DefaultColor, ClickedColor;
    public Recruiter_Home11() {
        initComponents();
        dynamicPanel = new JPanel();
        dynamicPanel.setLayout(new BoxLayout(dynamicPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical stacking
        jScrollPane1.setViewportView(dynamicPanel); // Add the dynamic panel to the scroll pane
        fetchDataAndDisplay();
        
        ClickedColor = new Color(0,0,0);
        DefaultColor = new Color(255,0,0);
        }
    
    private void fetchDataAndDisplay() {
        // Database connection setup
        String url = "jdbc:mysql://localhost:3306/job_finder_database";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Get logged-in recruiter's email
            String recruiterEmail = UserSession.getEmail();

            // Fetch the current recruiter's ID
            String idQuery = "SELECT recruiter_id FROM user_recruiter WHERE email = ?";
            PreparedStatement idStmt = conn.prepareStatement(idQuery);
            idStmt.setString(1, recruiterEmail);
            ResultSet idRs = idStmt.executeQuery();

            int recruiterId = -1;
            if (idRs.next()) {
                recruiterId = idRs.getInt("recruiter_id");
            } else {
                JOptionPane.showMessageDialog(null, "Recruiter ID not found for the logged-in user!");
                return;
            }

            // Modify query to fetch only job posts created by the logged-in recruiter
            String query = "SELECT job_post.job_id, job_post.job_title, job_post.job_description, " +
                           "job_post.location, job_post.salary, job_post.job_type, job_post.post_on, " +
                           "user_recruiter.company_name " +
                           "FROM job_post " +
                           "INNER JOIN user_recruiter ON job_post.recruiter_id = user_recruiter.recruiter_id " +
                           "WHERE job_post.recruiter_id = ? " +
                           "ORDER BY job_post.post_on DESC";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, recruiterId);
            ResultSet rs = stmt.executeQuery();

            // Define the font with Arial, plain, size 18 for labels
            Font labelFont = new Font("Arial", Font.PLAIN, 18);

            // Clear existing content in dynamicPanel before adding new components
            dynamicPanel.removeAll();

            while (rs.next()) {
                // Create new components for each record dynamically
                int jobID = rs.getInt("job_post.job_id");

                String jobTitle = rs.getString("job_post.job_title");
                 JLabel jobTitleLabel = new JLabel("<html><b>Job Title: </b>" + jobTitle + "</html>");
                jobTitleLabel.setFont(labelFont);
                jobTitleLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                jobTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                String jobDescription = rs.getString("job_post.job_description");
                JEditorPane jobDescriptionEditorPane = new JEditorPane();
                jobDescriptionEditorPane.setContentType("text/html");
                jobDescriptionEditorPane.setText("<html><body style='font-family: Arial; font-size: 14px; text-align: left;'>"
                                    + "<b>Description: </b>" + jobDescription + "</body></html>");
                jobDescriptionEditorPane.setEditable(false);
                jobDescriptionEditorPane.setOpaque(false);
                jobDescriptionEditorPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                jobDescriptionEditorPane.setPreferredSize(new Dimension(200, 100));
                jobDescriptionEditorPane.setAlignmentX(Component.LEFT_ALIGNMENT);

                String jobLocation = rs.getString("job_post.location");
                JLabel jobLocationLabel = new JLabel("<html><b>Location: </b>" + jobLocation + "</html>");
                jobLocationLabel.setFont(labelFont);
                jobLocationLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                jobLocationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                String jobSalary = rs.getString("job_post.salary");
                JLabel jobSalaryLabel = new JLabel("<html><b>Salary: </b>" + jobSalary + "</html>");
                jobSalaryLabel.setFont(labelFont);
                jobSalaryLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                jobSalaryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                String jobType = rs.getString("job_post.job_type");
                JLabel jobTypeLabel = new JLabel("<html><b>Job Type: </b>" + jobType + "</html>");
                jobTypeLabel.setFont(labelFont);
                jobTypeLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                jobTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                String jobPostDate = rs.getString("job_post.post_on");
                JLabel jobPostLabel = new JLabel("<html><body style='font-family: Arial; font-size: 12px; text-align: left;'>"
                                    + "<b>Job Posted: </b>" + jobPostDate + "</body></html>");
                jobPostLabel.setFont(labelFont);
                jobPostLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
                jobPostLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                // Get the user name who posted the job
                String userName = rs.getString("company_name");

                // Create a panel to hold the components for this record
                JPanel recordPanel = new JPanel();
                recordPanel.setLayout(new BoxLayout(recordPanel, BoxLayout.Y_AXIS)); // Stack components vertically

                recordPanel.setLayout(new BoxLayout(recordPanel, BoxLayout.Y_AXIS));
                recordPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                recordPanel.add(jobTitleLabel);
                recordPanel.add(jobDescriptionEditorPane);
                recordPanel.add(jobLocationLabel);
                recordPanel.add(jobSalaryLabel);
                recordPanel.add(jobTypeLabel);
                recordPanel.add(jobPostLabel);


                // Add an Edit button to allow updating the job post
                JButton editButton = new JButton("    EDIT    ");
                editButton.setFont(new Font("Arial", Font.BOLD, 14));
                editButton.setBorder(BorderFactory.createLineBorder(recordPanel.getBackground(), 3)); // White border
                editButton.setBackground(Color.BLACK);
                editButton.setForeground(Color.WHITE);
                editButton.addActionListener(e -> {
                    // Open an edit form with the current job details
                    openEditJobForm(jobID, jobTitle, jobDescription, jobLocation, jobSalary, jobType);
                });

                JButton deleteButton = new JButton(" DELETE ");
                deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
                deleteButton.setBorder(BorderFactory.createLineBorder(recordPanel.getBackground(), 3)); // White border
                deleteButton.setBackground(Color.BLACK);
                deleteButton.setForeground(Color.WHITE);
                deleteButton.addActionListener(e -> {
                    int confirmation = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure you want to delete this job post?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirmation == JOptionPane.YES_OPTION) {
                        deleteJobPost(jobID);
                    }
                });


                Dimension buttonSize = new Dimension(200, 30); // Width: 100px, Height: 40px
                editButton.setPreferredSize(buttonSize);
                deleteButton.setPreferredSize(buttonSize);
                // Add the Edit button to the panel
                recordPanel.add(deleteButton);
                recordPanel.add(editButton);

                // Add a border around the record panel with a title to identify the group
                recordPanel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.BLACK, 2),
                    userName.toUpperCase(),  // Display the recruiter's name
                    TitledBorder.LEFT,
                    TitledBorder.TOP,
                    new Font("Arial", Font.BOLD, 18),
                    Color.BLACK
                ));

                // Add the record panel to the dynamic panel
                dynamicPanel.add(recordPanel);
            }

            // Refresh the UI after adding all components
            SwingUtilities.invokeLater(() -> {
                dynamicPanel.revalidate();
                dynamicPanel.repaint();

                jScrollPane1.getVerticalScrollBar().setValue(0);
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Remove the North Pane (title bar) from the internal frame
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }
    
    private void deleteJobPost(int jobId) {
        String url = "jdbc:mysql://localhost:3306/job_finder_database";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Step 1: Delete the related records in the 'applications' table that reference this job
            String deleteApplicationsQuery = "DELETE FROM applications WHERE job_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteApplicationsQuery)) {
                stmt.setInt(1, jobId);
                int rowsDeletedFromApplications = stmt.executeUpdate();
                if (rowsDeletedFromApplications > 0) {
                    System.out.println(rowsDeletedFromApplications + " application(s) deleted.");
                }
            }

            // Step 2: Now delete the job post
            String deleteJobQuery = "DELETE FROM job_post WHERE job_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteJobQuery)) {
                stmt.setInt(1, jobId);

                int rowsDeleted = stmt.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(null, "Job post deleted successfully!");
                    fetchDataAndDisplay(); // Refresh the job list after deletion
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete the job post.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void openEditJobForm(int jobId, String jobTitle, String jobDescription, String location, String salary, String jobType) {
        // Create a new form or dialog for editing job details
        JTextField titleField = new JTextField(jobTitle);
        JTextArea descriptionArea = new JTextArea(jobDescription);
        descriptionArea.setWrapStyleWord(true); // Enable word wrapping
        descriptionArea.setLineWrap(true); // Enable line wrapping       
        JTextField locationField = new JTextField(location);
        JTextField salaryField = new JTextField(salary);
        JComboBox<String> jobTypeComboBox = new JComboBox<>(new String[]{"Full-time", "Part-time", "Contract", "Internship"});
        jobTypeComboBox.setSelectedItem(jobType);

        // A button to save the updated job post
        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            String newTitle = titleField.getText();
            String newDescription = descriptionArea.getText();
            String newLocation = locationField.getText();
            String newSalary = salaryField.getText();
            String newJobType = (String) jobTypeComboBox.getSelectedItem();

            updateJobPost(jobId, newTitle, newDescription, newLocation, newSalary, newJobType);
        });

        // Display the form in a dialog or a new window
        JPanel editPanel = new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
        editPanel.add(new JLabel("Job Title:"));
        editPanel.add(titleField);
        editPanel.add(new JLabel("Job Description:"));
        editPanel.add(new JScrollPane(descriptionArea));
        editPanel.add(new JLabel("Location:"));
        editPanel.add(locationField);
        editPanel.add(new JLabel("Salary:"));
        editPanel.add(salaryField);
        editPanel.add(new JLabel("Job Type:"));
        editPanel.add(jobTypeComboBox);
        editPanel.add(saveButton);

        JOptionPane.showMessageDialog(null, editPanel, "Edit Job Post", JOptionPane.PLAIN_MESSAGE);
    }
    
    private void updateJobPost(int jobId, String newTitle, String newDescription, String newLocation, String newSalary, String newJobType) {
        String url = "jdbc:mysql://localhost:3306/job_finder_database";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String updateQuery = "UPDATE job_post SET job_title = ?, job_description = ?, location = ?, salary = ?, job_type = ? WHERE job_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                stmt.setString(1, newTitle);
                stmt.setString(2, newDescription);
                stmt.setString(3, newLocation);
                stmt.setString(4, newSalary);
                stmt.setString(5, newJobType);
                stmt.setInt(6, jobId);

                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Job post updated successfully!");
                    fetchDataAndDisplay(); // Refresh the job list after update
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cjp = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(780, 520));
        setPreferredSize(new java.awt.Dimension(780, 520));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 766, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel2);

        cjp.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        cjp.setText("Create a Job Post");
        cjp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cjpMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cjpMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cjpMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(295, Short.MAX_VALUE)
                .addComponent(cjp)
                .addGap(295, 295, 295))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cjp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cjpMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cjpMousePressed
        // TODO add your handling code here:
        cjp.setForeground(DefaultColor);
    }//GEN-LAST:event_cjpMousePressed

    private void cjpMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cjpMouseReleased
        // TODO add your handling code here:
        cjp.setForeground(ClickedColor);
    }//GEN-LAST:event_cjpMouseReleased

    private void cjpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cjpMouseClicked
        Recruiter_CreatePost createPostFrame = new Recruiter_CreatePost();
        getDesktopPane().add(createPostFrame);
        createPostFrame.setVisible(true);
        this.dispose(); // Close the current frame if necessary
    }//GEN-LAST:event_cjpMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cjp;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
