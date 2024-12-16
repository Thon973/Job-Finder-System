
package system_final;


import com.myapp.session.UserSession;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.sql.*; 
import java.awt.Color;
import java.util.Random;


public class Login_Final extends javax.swing.JFrame {
    
    private final String dbUrl = "jdbc:mysql://localhost:3306/job_finder_database"; // DB URL
    private final String dbUser = "root"; // DB username
    private final String dbPassword = ""; // DB password
    Color DefaultColor, ClickedColor;
    public Login_Final() {
        initComponents();
        
        ClickedColor = new Color(0,0,0);
        DefaultColor = new Color(255,0,0);
        
        SignUp_label.setForeground(DefaultColor);
    }
    
     private void resetPassword() {
        String email = JOptionPane.showInputDialog(
                null,
                "Enter your registered email:",
                "Reset Password",
                JOptionPane.QUESTION_MESSAGE
        );

        if (email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Email cannot be empty.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        String resetCode = generateResetCode();
        boolean emailExists = false;

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "SELECT email FROM user_recruiter WHERE email = ? " +
                    "UNION " +
                    "SELECT email FROM user_seeker WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                stmt.setString(2, email);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    emailExists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Database error. Please try again later.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (!emailExists) {
            JOptionPane.showMessageDialog(
                    null,
                    "Email not found.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        JOptionPane.showMessageDialog(
                null,
                "A reset code is: " + resetCode,
                "Reset Password",
                JOptionPane.INFORMATION_MESSAGE
        );

        String enteredCode = JOptionPane.showInputDialog(
                null,
                "Enter the reset code:",
                "Reset Password",
                JOptionPane.QUESTION_MESSAGE
        );

        if (enteredCode == null || !enteredCode.equals(resetCode)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Invalid reset code.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        String newPassword = JOptionPane.showInputDialog(
                null,
                "Enter your new password:",
                "Reset Password",
                JOptionPane.QUESTION_MESSAGE
        );

        if (newPassword == null || newPassword.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Password cannot be empty.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String updateRecruiterQuery = "UPDATE user_recruiter SET password = ? WHERE email = ?";
            String updateSeekerQuery = "UPDATE user_seeker SET password = ? WHERE email = ?";

            try (PreparedStatement stmt = conn.prepareStatement(updateRecruiterQuery)) {
                stmt.setString(1, newPassword);
                stmt.setString(2, email);
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = conn.prepareStatement(updateSeekerQuery)) {
                stmt.setString(1, newPassword);
                stmt.setString(2, email);
                stmt.executeUpdate();
            }

            JOptionPane.showMessageDialog(
                    null,
                    "Your password has been successfully reset.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "An error occurred while updating your password. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Generate a random 6-digit reset code
    private String generateResetCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // Generates a 6-digit number
        return String.valueOf(code);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Black_bg = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        Login_label = new javax.swing.JLabel();
        Email_label = new javax.swing.JLabel();
        Email = new javax.swing.JTextField();
        Passwaord_label = new javax.swing.JLabel();
        Forget_pass = new javax.swing.JLabel();
        Login_btn = new javax.swing.JButton();
        IDA_label = new javax.swing.JLabel();
        SignUp_label = new javax.swing.JLabel();
        Password = new javax.swing.JPasswordField();
        showpassBT = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 500));

        Black_bg.setBackground(new java.awt.Color(0, 0, 0));
        Black_bg.setPreferredSize(new java.awt.Dimension(400, 500));

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Job_Finder_Logo-removebg-preview.png"))); // NOI18N

        javax.swing.GroupLayout Black_bgLayout = new javax.swing.GroupLayout(Black_bg);
        Black_bg.setLayout(Black_bgLayout);
        Black_bgLayout.setHorizontalGroup(
            Black_bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Black_bgLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(Logo)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        Black_bgLayout.setVerticalGroup(
            Black_bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Black_bgLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(Logo)
                .addContainerGap(165, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Black_bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Black_bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Login_label.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        Login_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Login_label.setText("LOGIN");

        Email_label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Email_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user.png"))); // NOI18N
        Email_label.setText("  Email");

        Email.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        Passwaord_label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Passwaord_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/padlock (1).png"))); // NOI18N
        Passwaord_label.setText("Password");

        Forget_pass.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Forget_pass.setForeground(new java.awt.Color(0, 0, 255));
        Forget_pass.setText("Forgot Password?");
        Forget_pass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Forget_passMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Forget_passMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Forget_passMouseReleased(evt);
            }
        });

        Login_btn.setBackground(new java.awt.Color(0, 0, 0));
        Login_btn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Login_btn.setForeground(new java.awt.Color(255, 255, 255));
        Login_btn.setText("Login");
        Login_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Login_btnActionPerformed(evt);
            }
        });

        IDA_label.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        IDA_label.setText("I don't have an Account");

        SignUp_label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        SignUp_label.setForeground(new java.awt.Color(255, 0, 0));
        SignUp_label.setText("Sign up");
        SignUp_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SignUp_labelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SignUp_labelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SignUp_labelMouseReleased(evt);
            }
        });

        Password.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        showpassBT.setText("Show ");
        showpassBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpassBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Login_label, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(142, 142, 142))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Email)
                            .addComponent(Passwaord_label)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(IDA_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SignUp_label))
                            .addComponent(Password, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(Login_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Forget_pass)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(showpassBT))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(Email_label)))
                        .addContainerGap(50, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(Login_label, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(Email_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(Passwaord_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Forget_pass)
                    .addComponent(showpassBT))
                .addGap(45, 45, 45)
                .addComponent(Login_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDA_label)
                    .addComponent(SignUp_label))
                .addGap(71, 71, 71))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SignUp_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignUp_labelMouseClicked
       
        // TODO add your handling code here:
    }//GEN-LAST:event_SignUp_labelMouseClicked

    private void Forget_passMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Forget_passMouseClicked
        // TODO add your handling code here:
        resetPassword();
    }//GEN-LAST:event_Forget_passMouseClicked

    private void showpassBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpassBTActionPerformed
        // TODO add your handling code here:
        if (showpassBT.isSelected()) {
            Password.setEchoChar((char) 0); // Make the password visible
        } else {
            Password.setEchoChar('•'); // Hide the password
        }
    }//GEN-LAST:event_showpassBTActionPerformed

    private void Login_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Login_btnActionPerformed
        // TODO add your handling code here:
        String SUrl = "jdbc:mysql://localhost:3306/job_finder_database";
        String SUser = "root";
        String SPass = "";

        String username = Email.getText();
        String password = Password.getText();

        if("".equals(username)){
            JOptionPane.showMessageDialog(new JFrame(), "Email is required", "Error", JOptionPane.ERROR_MESSAGE); 
        } else if("".equals(password)){
            JOptionPane.showMessageDialog(new JFrame(), "Password is required", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try (Connection con = DriverManager.getConnection(SUrl, SUser, SPass)) {
                boolean loginSuccess = checkCredentials(con, username, password, "admin") ||
                                       checkCredentials(con, username, password, "user_recruiter") ||
                                       checkCredentials(con, username, password, "user_seeker");

                if (loginSuccess) {
                    UserSession.setEmail(username); // Store the logged-in emai
                    if (isAdmin(con, username)) {
                        Admin_Dashboard adminDashboard = new Admin_Dashboard();
                        adminDashboard.setVisible(true);
                        adminDashboard.pack();
                        adminDashboard.setLocationRelativeTo(null);
                        this.dispose();
                    } else if (isRecruiter(con, username)) {
                        Recruiter_Dashboard recruiterDashboard = new Recruiter_Dashboard();
                        recruiterDashboard.setVisible(true);
                        recruiterDashboard.pack();
                        recruiterDashboard.setLocationRelativeTo(null);
                        this.dispose();
                        Email.getText();

                    } else if (isSeeker(con, username)) {
                        Seeker_Dashboard seekerDashboard = new Seeker_Dashboard();
                        seekerDashboard.setVisible(true);
                        seekerDashboard.pack();
                        seekerDashboard.setLocationRelativeTo(null);
                        this.dispose();
                        Email.getText();
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Invalid login Email or Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                }
            }
    }//GEN-LAST:event_Login_btnActionPerformed
    

    
    private void SignUp_labelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignUp_labelMousePressed
        // TODO add your handling code here:
        SignUp_label.setForeground(ClickedColor);
    }//GEN-LAST:event_SignUp_labelMousePressed

    private void SignUp_labelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignUp_labelMouseReleased
        // TODO add your handling code here:
        SignUp_label.setForeground(DefaultColor);
        Signup_choose LoginFrame = new Signup_choose();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_SignUp_labelMouseReleased

    private void Forget_passMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Forget_passMousePressed
        // TODO add your handling code here:
        Forget_pass.setForeground(ClickedColor);
    }//GEN-LAST:event_Forget_passMousePressed

    private void Forget_passMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Forget_passMouseReleased
        // TODO add your handling code here:
        Forget_pass.setForeground(new Color(0,0,255));
    }//GEN-LAST:event_Forget_passMouseReleased

    private boolean checkCredentials(Connection con, String email, String password, String table) throws SQLException {
        String query = "SELECT email, password FROM " + table + " WHERE email = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return password.equals(storedPassword);
            }
        }
    return false;
    }

    private boolean isAdmin(Connection con, String email) throws SQLException {
        String query = "SELECT email FROM admin WHERE email = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            return rs.next();
        }
    }

    private boolean isRecruiter(Connection con, String email) throws SQLException {
        String query = "SELECT email FROM user_recruiter WHERE email = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            return rs.next();
        }
    }

    private boolean isSeeker(Connection con, String email) throws SQLException {
        String query = "SELECT email FROM user_seeker WHERE email = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            return rs.next();
        }
    }
    
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login_Final.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Login_Final().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Black_bg;
    private javax.swing.JTextField Email;
    private javax.swing.JLabel Email_label;
    private javax.swing.JLabel Forget_pass;
    private javax.swing.JLabel IDA_label;
    private javax.swing.JButton Login_btn;
    private javax.swing.JLabel Login_label;
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel Passwaord_label;
    private javax.swing.JPasswordField Password;
    private javax.swing.JLabel SignUp_label;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox showpassBT;
    // End of variables declaration//GEN-END:variables
}
