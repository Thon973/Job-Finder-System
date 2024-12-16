
package system_final;

/**
 *
 * @author WINDOWS 10
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.sql.*;
import com.myapp.session.UserSession;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
public class Admin_Profile extends javax.swing.JInternalFrame {

    /**
     * Creates new form Seeker_Profile
     */
    
    public static String SUrl = "jdbc:mysql://localhost:3306/job_finder_database";
    public static String SUser = "root";
    public static String Spass = "";
    
    public Admin_Profile() {
        
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        loadRecruiterData();

    }
    
    private void loadRecruiterData() {
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(SUrl, SUser, Spass);

            // Query to fetch recruiter data
            String query = "SELECT name, email, password, profile_pic FROM admin WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            // Get the email from UserSession
            String recruiterEmail = UserSession.getEmail();

            // Check if the session email is available
            if (recruiterEmail == null || recruiterEmail.isEmpty()) {
                JOptionPane.showMessageDialog(new JFrame(), "No User logged in.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Set the email parameter
            stmt.setString(1, recruiterEmail);

            ResultSet rs = stmt.executeQuery();

            // Populate the fields with data from the result set
            if (rs.next()) {
                name.setText(rs.getString("name"));
                email.setText(rs.getString("email"));
                password.setText(rs.getString("password"));
                
                 byte[] imageBytes = rs.getBytes("profile_pic");
            if (imageBytes != null) {
                // Convert bytes to an ImageIcon
                ImageIcon imageIcon = new ImageIcon(imageBytes);
                Image scaledImage = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Adjust dimensions
                imageIcon = new ImageIcon(scaledImage);
                jLabel1.setIcon(imageIcon); // Assuming jLabel1 is for displaying the profile picture
            } else {
                // Set a default image if no profile picture exists
                jLabel1.setIcon(null);
            }
                
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "No record found for the logged-in user.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Close the connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(), "An error occurred while loading data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Add_Pic = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        Button = new javax.swing.JButton();
        email = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        password = new javax.swing.JTextField();
        passwordlb = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(780, 520));
        setPreferredSize(new java.awt.Dimension(780, 520));

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));

        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 5));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );

        Add_Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/9055425_bxs_image_add_icon.png"))); // NOI18N
        Add_Pic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Add_PicMouseClicked(evt);
            }
        });

        emailLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        emailLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        emailLabel.setText("EMAIL");
        emailLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Button.setBackground(new java.awt.Color(0, 0, 0));
        Button.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        Button.setForeground(new java.awt.Color(255, 255, 255));
        Button.setText("EDIT");
        Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonMouseClicked(evt);
            }
        });
        Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonActionPerformed(evt);
            }
        });

        email.setEditable(false);
        email.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        email.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        email.setText("jTextField1");
        email.setBorder(null);
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        name.setEditable(false);
        name.setFont(new java.awt.Font("Arial Black", 0, 20)); // NOI18N
        name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        name.setText("jTextField1");
        name.setBorder(null);

        password.setEditable(false);
        password.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        password.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        password.setText("jTextField1");
        password.setBorder(null);
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });

        passwordlb.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        passwordlb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordlb.setText("PASSWORD");
        passwordlb.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(passwordlb, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Add_Pic))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Button, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(email)
                                .addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                                .addComponent(name)))))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordlb, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Add_Pic)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addComponent(Button, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Add_PicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Add_PicMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                // Load the image
                BufferedImage originalImage = ImageIO.read(selectedFile);

                // Resize the image to a maximum dimension (e.g., 200x200)
                int targetWidth = 200;
                int targetHeight = 200;

                Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
                BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = resizedImage.createGraphics();
                g2d.drawImage(scaledImage, 0, 0, null);
                g2d.dispose();

                // Convert the resized image to a byte array
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(resizedImage, "jpg", baos); // Use "jpg" or appropriate format
                byte[] imageBytes = baos.toByteArray();
                baos.close();

                // Get the logged-in user's email from the session
                String userEmail = UserSession.getEmail();
                if (userEmail == null || userEmail.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Error: User email not found in session.");
                    return;
                }

                // Store the resized image bytes into the database
                Connection conn = DriverManager.getConnection(SUrl, SUser, Spass);
                String query = "UPDATE `admin` SET `profile_pic`= ? WHERE email = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setBytes(1, imageBytes);
                pstmt.setString(2, userEmail); // Use the logged-in user's email
                int rowsUpdated = pstmt.executeUpdate();

                if (rowsUpdated > 0) {
                    // Display the resized image in a JLabel
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image scaledDisplayImage = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(scaledDisplayImage);
                    jLabel1.setIcon(imageIcon); // Assuming you have a JLabel named jLabel1
                    JOptionPane.showMessageDialog(this, "Profile picture updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Failed to update profile picture.");
                }

                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_Add_PicMouseClicked

    private boolean isEditing = false; // To track whether the fields are in edit mode
    
    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonActionPerformed

    private void ButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here
        if (!isEditing) {
            // Switch to editing mode
            Button.setText("SAVE");
            enableEditing(true); // Enable text fields for editing
            isEditing = true;
        } else {
            // Validate input before proceeding

            if (name.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(new JFrame(), "Company name field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (email.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(new JFrame(), "Email field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (password.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(new JFrame(), "Phone number field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // cnameblish a connection to the database and update details
            try (Connection conn = DriverManager.getConnection(SUrl, SUser, Spass)) {
                // Query to update user details
                String query = "UPDATE admin SET name = ?, email = ?, password = ? WHERE email = ?";

                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    // Set parameters from the editable fields
                    stmt.setString(1, name.getText());
                    stmt.setString(2, email.getText());
                    stmt.setString(3, password.getText());
                    stmt.setString(4, UserSession.getEmail()); // Update based on logged-in user's email

                    // Execute the update
                    int rowsUpdated = stmt.executeUpdate();

                    // Check if the update was successful
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(new JFrame(), "Details updated successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
                        if (!UserSession.getEmail().equals(email.getText())) {
                            UserSession.setEmail(email.getText());
                        }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Failed to update details.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException e) {
                // Log the exception and show a user-friendly message
                e.printStackTrace();
                JOptionPane.showMessageDialog(new JFrame(), "An error occurred while updating details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
           
            Button.setText("EDIT");
            enableEditing(false); // Disable text fields after saving
            isEditing = false;
        }
    }//GEN-LAST:event_ButtonMouseClicked
    private void enableEditing(boolean enable) {
        name.setEditable(enable);
        email.setEditable(enable);
        password.setEditable(enable);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Add_Pic;
    private javax.swing.JButton Button;
    private javax.swing.JTextField email;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField name;
    private javax.swing.JTextField password;
    private javax.swing.JLabel passwordlb;
    // End of variables declaration//GEN-END:variables
}
