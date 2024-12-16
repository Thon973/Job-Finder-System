
package system_final;


import com.myapp.session.UserSession;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;
public class Recruiter_CreatePost extends javax.swing.JInternalFrame {


    
    public Recruiter_CreatePost() {
        initComponents();
 
        // Enable line wrapping
        Job_Description.setLineWrap(true);       // Enable line wrapping
        Job_Description.setWrapStyleWord(true);  // Wrap at word boundaries
        Job_Description.setEditable(true);       // Make it editable
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Job_Title = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Job_Description = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        Location = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Salary = new javax.swing.JTextField();
        Job_Type = new javax.swing.JComboBox<>();
        Post = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(780, 520));
        setPreferredSize(new java.awt.Dimension(780, 520));

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel2.setText("Create a post for job");

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel3.setText("Job TItle:");

        jLabel4.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel4.setText("Job Description:");

        Job_Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Job_Title.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Job_TitleActionPerformed(evt);
            }
        });

        Job_Description.setColumns(20);
        Job_Description.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Job_Description.setRows(5);
        jScrollPane1.setViewportView(Job_Description);

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel5.setText("Location:");

        Location.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Location.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocationActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel6.setText("Salary:");

        jLabel7.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel7.setText("Job Type:");

        Salary.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Salary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalaryActionPerformed(evt);
            }
        });

        Job_Type.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Job_Type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Full-time", "Part-time", "Contract", "Internship" }));
        Job_Type.setToolTipText("");

        Post.setFont(new java.awt.Font("Arial Black", 1, 20)); // NOI18N
        Post.setForeground(new java.awt.Color(0, 0, 204));
        Post.setText("POST");
        Post.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PostMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PostMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                PostMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Post)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(Job_Title))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(Location))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(Salary))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)
                                    .addComponent(Job_Type, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Job_Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Salary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Job_Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Post)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Job_TitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Job_TitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Job_TitleActionPerformed

    private void LocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LocationActionPerformed

    private void SalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SalaryActionPerformed

    private void PostMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PostMousePressed
        // TODO add your handling code here:
        Post.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_PostMousePressed

    private void PostMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PostMouseReleased
        // TODO add your handling code here:
        Post.setForeground(new Color(0,0,204));
    }//GEN-LAST:event_PostMouseReleased

    private void PostMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PostMouseClicked
        // TODO add your handling code here:
    String SUrl = "jdbc:MySQL://localhost:3306/job_finder_database";
    String SUser = "root";
    String Spass = "";

    try {
        // Retrieve the logged-in user's email from UserSession
        String email = UserSession.getEmail();  // Get email of logged-in user from session

        // Get the recruiter_id for the logged-in user
        int recruiterId = getRecruiterIdFromDatabase(email);

        if (recruiterId == 0) {
            JOptionPane.showMessageDialog(new JFrame(), "Login failed. Please check your credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop further execution if recruiter_id is not found
        }

        // Validation checks for empty fields
        if ("".equals(Job_Title.getText())) {
            JOptionPane.showMessageDialog(new JFrame(), "Job Title", "Error", JOptionPane.ERROR_MESSAGE);
        } else if ("".equals(Job_Description.getText())) {
            JOptionPane.showMessageDialog(new JFrame(), "Job Description", "Error", JOptionPane.ERROR_MESSAGE);
        } else if ("".equals(Location.getText())) {
            JOptionPane.showMessageDialog(new JFrame(), "Location", "Error", JOptionPane.ERROR_MESSAGE);
        } else if ("".equals(Salary.getText())) {
            JOptionPane.showMessageDialog(new JFrame(), "Salary", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Salary validation (check if it's a valid number)
            String salary = Salary.getText();
            try {
                Double.parseDouble(salary);  // Check if salary is a valid number
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid number for salary.", "Error", JOptionPane.ERROR_MESSAGE);
                return;  // Stop further execution if salary is invalid
            }

            // Get values from input fields
            String jobtitle = Job_Title.getText();
            String jobdescription = Job_Description.getText();
            String location = Location.getText();
            String jobtype = Job_Type.getSelectedItem().toString();

            // Establish a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, Spass);

            // Prepare the SQL query with placeholders for parameters
            String query = "INSERT INTO job_post (job_title, job_description, location, post_on, salary, job_type, recruiter_id) " +
                           "VALUES (?, ?, ?, NOW(), ?, ?, ?)";

            try (PreparedStatement stmt = con.prepareStatement(query)) {
                // Set the values for the placeholders
                stmt.setString(1, jobtitle);        // Set job title
                stmt.setString(2, jobdescription);  // Set job description
                stmt.setString(3, location);        // Set location
                stmt.setString(4, salary);          // Set salary
                stmt.setString(5, jobtype);         // Set job type
                stmt.setInt(6, recruiterId);        // Set recruiter_id

                // Execute the insert query
                stmt.executeUpdate();

                // Clear the input fields
                Job_Title.setText("");
                Job_Description.setText("");
                Location.setText("");
                Salary.setText("");

                JOptionPane.showMessageDialog(this, "Job posted successfully!");
            }

            con.close();
        }

    } catch (HeadlessException | ClassNotFoundException | SQLException e) {
        System.out.println("Error!" + e.getMessage());
    }

    Recruiter_Home11 createPostFrame = new Recruiter_Home11();
    getDesktopPane().add(createPostFrame);
    createPostFrame.setVisible(true);
    this.dispose(); // Close the current frame if necessary
    }//GEN-LAST:event_PostMouseClicked
    public static int getRecruiterIdFromDatabase(String email) {
        String SUrl = "jdbc:MySQL://localhost:3306/job_finder_database";
        String SUser = "root";
        String Spass = "";

        int recruiterId = 0; // Default value if login fails

        try (Connection con = DriverManager.getConnection(SUrl, SUser, Spass)) {
            String query = "SELECT recruiter_id FROM user_recruiter WHERE email = ?";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setString(1, email); // Use the email from UserSession

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        recruiterId = rs.getInt("recruiter_id"); // Get recruiter_id from the result set
                    } else {
                        System.out.println("Invalid email. Recruiter not found.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching recruiter ID: " + e.getMessage());
        }

        return recruiterId; // Return recruiter_id (0 if not found)
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Job_Description;
    private javax.swing.JTextField Job_Title;
    private javax.swing.JComboBox<String> Job_Type;
    private javax.swing.JTextField Location;
    private javax.swing.JLabel Post;
    private javax.swing.JTextField Salary;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables


  
}
