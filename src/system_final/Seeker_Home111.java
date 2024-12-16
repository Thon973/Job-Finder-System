
package system_final;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import com.myapp.session.UserSession;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class Seeker_Home111 extends javax.swing.JInternalFrame {
    
    private JPanel dynamicPanel; // Panel to hold dynamic compone
    
    public Seeker_Home111() {
        initComponents();
        dynamicPanel = new JPanel();
        dynamicPanel.setLayout(new BoxLayout(dynamicPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical stacking
        jScrollPane1.setViewportView(dynamicPanel); // Add the dynamic panel to the scroll pane
        fetchDataAndDisplay("");
        // Add an ActionListener to the search field
        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = Search.getText();
                fetchDataAndDisplay(searchText);  // Call the function to filter jobs based on the search text
            }
        });
         addSearchFocusListeners();
        }
        
    private void addSearchFocusListeners() {
        Search.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Show text cursor when focused on the search bar
                setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Hide the cursor when the search bar is not focused
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }
        
    private void fetchDataAndDisplay(String searchText) {
        // Database connection setup
        String url = "jdbc:mysql://localhost:3306/job_finder_database";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Get the email of the logged-in seeker
            String seekerEmail = UserSession.getEmail();

            // Fetch the seeker ID
            String idQuery = "SELECT seeker_id FROM user_seeker WHERE email = ?";
            PreparedStatement idStmt = conn.prepareStatement(idQuery);
            idStmt.setString(1, seekerEmail);
            ResultSet idRs = idStmt.executeQuery();

            int seekerId = -1;
            if (idRs.next()) {
                seekerId = idRs.getInt("seeker_id");
            }

            // Prepare the search query for job title, description, location, and company name
            String query = "SELECT job_post.job_id, job_post.job_title, job_post.job_description, " +
                           "job_post.location, job_post.salary, job_post.job_type, job_post.post_on, " +
                           "user_recruiter.company_name " +
                           "FROM job_post " +
                           "INNER JOIN user_recruiter ON job_post.recruiter_id = user_recruiter.recruiter_id " +
                           "WHERE job_post.job_id NOT IN (SELECT job_id FROM applications WHERE seeker_id = ? AND status = 'pending') " +
                           "AND (job_post.job_title LIKE ? OR job_post.job_description LIKE ? OR job_post.location LIKE ? " +
                           "OR user_recruiter.company_name LIKE ?) " +  // Add company name to the search condition
                           "ORDER BY job_post.post_on DESC LIMIT 50";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, seekerId); // Use the seeker ID to filter out pending applications
            stmt.setString(2, "%" + searchText + "%"); // Search in job title
            stmt.setString(3, "%" + searchText + "%"); // Search in job description
            stmt.setString(4, "%" + searchText + "%"); // Search in job location
            stmt.setString(5, "%" + searchText + "%"); // Search in company name
            ResultSet rs = stmt.executeQuery();

            // Define font for labels
            Font labelFont = new Font("Arial", Font.PLAIN, 18);

            // Clear the dynamic panel before adding new components
            dynamicPanel.removeAll();

            while (rs.next()) {
                final int jobID = rs.getInt("job_post.job_id");
                String jobTitle = rs.getString("job_post.job_title");
                String jobDescription = rs.getString("job_post.job_description");
                String jobLocation = rs.getString("job_post.location");
                String jobSalary = rs.getString("job_post.salary");
                String jobType = rs.getString("job_post.job_type");
                String jobPostDate = rs.getString("job_post.post_on");
                String companyName = rs.getString("company_name");

                // Job Title Label
                JLabel jobTitleLabel = new JLabel("<html><b>Job Title: </b>" + jobTitle + "</html>");
                jobTitleLabel.setFont(labelFont);
                jobTitleLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                jobTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                // Job Description Editor Pane
                JEditorPane jobDescriptionEditorPane = new JEditorPane();
                jobDescriptionEditorPane.setContentType("text/html");
                jobDescriptionEditorPane.setText("<html><body style='font-family: Arial; font-size: 14px; text-align: left;'>"
                                        + "<b>Description: </b>" + jobDescription + "</body></html>");
                jobDescriptionEditorPane.setEditable(false);
                jobDescriptionEditorPane.setOpaque(false);
                jobDescriptionEditorPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                jobDescriptionEditorPane.setPreferredSize(new Dimension(200, 100));
                jobDescriptionEditorPane.setAlignmentX(Component.LEFT_ALIGNMENT);

                // Job Location Label
                JLabel jobLocationLabel = new JLabel("<html><b>Location: </b>" + jobLocation + "</html>");
                jobLocationLabel.setFont(labelFont);
                jobLocationLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                jobLocationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                // Job Salary Label
                JLabel jobSalaryLabel = new JLabel("<html><b>Salary: </b>" + jobSalary + "</html>");
                jobSalaryLabel.setFont(labelFont);
                jobSalaryLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                jobSalaryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                // Job Type Label
                JLabel jobTypeLabel = new JLabel("<html><b>Job Type: </b>" + jobType + "</html>");
                jobTypeLabel.setFont(labelFont);
                jobTypeLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                jobTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                // Job Post Date Label
                JLabel jobPostLabel = new JLabel("<html><body style='font-family: Arial; font-size: 12px; text-align: left;'>"
                                        + "<b>Job Posted: </b>" + jobPostDate + "</body></html>");
                jobPostLabel.setFont(labelFont);
                jobPostLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
                jobPostLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                // Apply Button
                JButton applyButton = new JButton();
                if (hasAlreadyApplied(seekerId, jobID)) {
                    applyButton.setText("Pending");
                    applyButton.setEnabled(false);
                } else {
                    applyButton.setText("Apply");
                    applyButton.setFont(new Font("Arial", Font.BOLD, 14));
                    applyButton.setBackground(Color.BLACK);
                    applyButton.setForeground(Color.WHITE);
                    final int finalSeekerId = seekerId; // Make seekerId final for lambda
                    applyButton.addActionListener(e -> applyForJob(finalSeekerId, jobID, applyButton));
                }

                Dimension buttonSize = new Dimension(100, 35); // Width: 100px, Height: 40px
                applyButton.setPreferredSize(buttonSize);

                // Record Panel
                JPanel recordPanel = new JPanel();
                recordPanel.setLayout(new BoxLayout(recordPanel, BoxLayout.Y_AXIS));
                recordPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                recordPanel.add(jobTitleLabel);
                recordPanel.add(jobDescriptionEditorPane);
                recordPanel.add(jobLocationLabel);
                recordPanel.add(jobSalaryLabel);
                recordPanel.add(jobTypeLabel);
                recordPanel.add(jobPostLabel);

                // Button Panel
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
                buttonPanel.add(applyButton);
                buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

                // Add the button panel to the record panel
                recordPanel.add(buttonPanel);

                // Add a border to the record panel with the company name as the title
                recordPanel.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.BLACK, 5),
                        companyName.toUpperCase(),
                        TitledBorder.LEFT,
                        TitledBorder.TOP,
                        new Font("Arial", Font.BOLD, 18),
                        Color.BLACK
                ));

                // Add the record panel to the dynamic panel
                dynamicPanel.add(recordPanel);
                dynamicPanel.add(Box.createVerticalStrut(10)); // Add spacing between records
                System.out.println("Company Name: " + companyName);
            }

            SwingUtilities.invokeLater(() -> {
                dynamicPanel.revalidate();
                dynamicPanel.repaint();

                jScrollPane1.getVerticalScrollBar().setValue(0);
            });

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching job postings. Please try again later.", "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Remove the North Pane (title bar) from the internal frame
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }


    
    private boolean hasAlreadyApplied(int seekerId, int jobId) {
        // Check if the seeker has already applied for the job
        String url = "jdbc:mysql://localhost:3306/job_finder_database";
        String user = "root";
        String password = "";

        String query = "SELECT COUNT(*) FROM applications WHERE seeker_id = ? AND job_id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, seekerId);
            stmt.setInt(2, jobId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;  // If count > 0, the seeker has already applied
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // No application found
    }
    
    private void applyForJob(int seekerId, int jobId, JButton applyButton) {
        // Insert application into the database
        String url = "jdbc:mysql://localhost:3306/job_finder_database";
        String user = "root";
        String password = "";

        String insertQuery = "INSERT INTO applications (job_id, seeker_id, status) VALUES (?, ?, 'pending')";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            stmt.setInt(1, jobId);  // Set the job ID
            stmt.setInt(2, seekerId);  // Set the seeker ID

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                applyButton.setText("Pending");
                applyButton.setEnabled(false); // Disable the button after applying
                JOptionPane.showMessageDialog(null, "You have successfully applied for the job!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to apply for the job. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while applying for the job.");
        }

    }
    
    
    private int getCurrentSeekerId() {
        // Get the current logged-in seeker's ID
        String email = UserSession.getEmail();
        if (email == null) {
            return -1;
        }

        String url = "jdbc:mysql://localhost:3306/job_finder_database";
        String user = "root";
        String password = "";
        String query = "SELECT seeker_id FROM user_seeker WHERE email = ?";
        int seekerId = -1;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                seekerId = rs.getInt("seeker_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seekerId;
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Search = new javax.swing.JTextField();
        searchIcon = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
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
            .addGap(0, 492, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel2);

        Search.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        Search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                SearchFocusLost(evt);
            }
        });
        Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchMouseClicked(evt);
            }
        });

        searchIcon.setBackground(new java.awt.Color(255, 255, 255));
        searchIcon.setForeground(new java.awt.Color(255, 255, 255));
        searchIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\WINDOWS 10\\Downloads\\search.png")); // NOI18N
        searchIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchIconMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(searchIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(Search)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchIconMouseClicked
 
    }//GEN-LAST:event_searchIconMouseClicked

    private void SearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SearchFocusGained
        // TODO add your handling code here:
        if(Search.getText().equals("Search")){
            Search.setText("");
            Search.setForeground(new Color (153,153,153));
        }
    }//GEN-LAST:event_SearchFocusGained

    private void SearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SearchFocusLost
        // TODO add your handling code here:
        if(Search.getText().equals("")){
            Search.setText("Search");
            Search.setForeground(new Color (153,153,153));
        }
    }//GEN-LAST:event_SearchFocusLost

    private void SearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Search;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel searchIcon;
    // End of variables declaration//GEN-END:variables

}
