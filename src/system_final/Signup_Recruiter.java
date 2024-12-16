
package system_final;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Signup_Recruiter extends javax.swing.JFrame {

    public Signup_Recruiter() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Black_bg = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        SignUp_label = new javax.swing.JLabel();
        Cname_label = new javax.swing.JLabel();
        CompanyName = new javax.swing.JTextField();
        Address_label = new javax.swing.JLabel();
        Email_Address_label = new javax.swing.JLabel();
        Email_Address = new javax.swing.JTextField();
        PNumber_label = new javax.swing.JLabel();
        Password_label = new javax.swing.JLabel();
        ReEnter_label = new javax.swing.JLabel();
        Signup_btn = new javax.swing.JButton();
        PNumber = new javax.swing.JTextField();
        Back_btn = new javax.swing.JButton();
        Password = new javax.swing.JPasswordField();
        Re_password = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        address = new javax.swing.JTextField();
        showpassBT = new javax.swing.JCheckBox();
        showpassBT1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Black_bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Black_bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        SignUp_label.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        SignUp_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SignUp_label.setText("SIGN UP");

        Cname_label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Cname_label.setText("Company Name/Store Name");

        Address_label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Address_label.setText("Address");

        Email_Address_label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Email_Address_label.setText("Email Address");

        PNumber_label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        PNumber_label.setText("Mobile Number");

        Password_label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Password_label.setText("Password");

        ReEnter_label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ReEnter_label.setText("Re-enter password");

        Signup_btn.setBackground(new java.awt.Color(0, 0, 0));
        Signup_btn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Signup_btn.setForeground(new java.awt.Color(255, 255, 255));
        Signup_btn.setText("Sign up");
        Signup_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Signup_btnActionPerformed(evt);
            }
        });

        Back_btn.setBackground(new java.awt.Color(0, 0, 0));
        Back_btn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Back_btn.setForeground(new java.awt.Color(255, 255, 255));
        Back_btn.setText("Login");
        Back_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back_btnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("I  already have an Account");

        showpassBT.setText("Show ");
        showpassBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpassBTActionPerformed(evt);
            }
        });

        showpassBT1.setText("Show ");
        showpassBT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpassBT1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(SignUp_label))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Address_label)
                                .addComponent(Cname_label)
                                .addComponent(ReEnter_label)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Email_Address_label)
                                        .addComponent(Email_Address, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(PNumber_label)
                                        .addComponent(PNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(Password_label)
                                .addComponent(Signup_btn)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(Back_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(Password, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Re_password))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(showpassBT)
                                        .addComponent(showpassBT1))))
                            .addComponent(CompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(SignUp_label, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(Cname_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Address_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Email_Address_label)
                    .addComponent(PNumber_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Email_Address, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Password_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showpassBT1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ReEnter_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Re_password, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showpassBT))
                .addGap(18, 18, 18)
                .addComponent(Signup_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Back_btn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back_btnActionPerformed
        Login_Final LoginFrame = new Login_Final();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null);
        this.dispose();

    }//GEN-LAST:event_Back_btnActionPerformed

    private void Signup_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Signup_btnActionPerformed
        String Cname, Address, Email, Pnumber, password, Rpassword;
        String SUrl = "jdbc:MySQL://localhost:3306/job_finder_database";
        String SUser = "root";
        String Spass = "";

        try (Connection con = DriverManager.getConnection(SUrl, SUser, Spass)) {

            // Input validation
            if ("".equals(CompanyName.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Company/Store Name is Required", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if ("".equals(address.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Address is Required", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if ("".equals(Email_Address.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Email Address is Required", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if ("".equals(PNumber.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Phone Number is Required", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!PNumber.getText().matches("\\d{11}")) { // Adjust regex to match your criteria
                JOptionPane.showMessageDialog(new JFrame(), "Invalid Phone Number. Must be 11 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if ("".equals(Password.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Password is Required", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if ("".equals(Re_password.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Re-enter Password is Required", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cname = CompanyName.getText();
            Address = address.getText();
            Email = Email_Address.getText();
            Pnumber = PNumber.getText();
            password = Password.getText();
            Rpassword = Re_password.getText();

            // Check if email already exists
            String emailCheckQuery = "SELECT * FROM user_recruiter WHERE email = ?";
            String phoneCheckQuery = "SELECT * FROM user_recruiter WHERE pnumber = ?";

            try (PreparedStatement emailStmt = con.prepareStatement(emailCheckQuery);
                 PreparedStatement phoneStmt = con.prepareStatement(phoneCheckQuery)) {

                emailStmt.setString(1, Email);
                try (ResultSet rsEmail = emailStmt.executeQuery()) {
                    if (rsEmail.next()) {
                        JOptionPane.showMessageDialog(new JFrame(), "Email is already in use. Please use a different email.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                phoneStmt.setString(1, Pnumber);
                try (ResultSet rsPhone = phoneStmt.executeQuery()) {
                    if (rsPhone.next()) {
                        JOptionPane.showMessageDialog(new JFrame(), "Phone Number is already in use. Please use a different phone number.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            // If email and phone number are both unique, proceed
            if (password.equals(Rpassword)) {
                String insertQuery = "INSERT INTO user_recruiter(company_name, address, email, pnumber, password) VALUES(?, ?, ?, ?, ?)";
                try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
                    insertStmt.setString(1, Cname);
                    insertStmt.setString(2, Address);
                    insertStmt.setString(3, Email);
                    insertStmt.setString(4, Pnumber);
                    insertStmt.setString(5, password);
                    insertStmt.executeUpdate();

                    JOptionPane.showMessageDialog(new JFrame(), "Signup successful! Please log in.", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Navigate to login screen
                    Login_Final LoginFrame = new Login_Final();
                    LoginFrame.setVisible(true);
                    LoginFrame.pack();
                    LoginFrame.setLocationRelativeTo(null);
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (HeadlessException | SQLException e) {
            System.out.println("Error!" + e.getMessage());
       }
    }//GEN-LAST:event_Signup_btnActionPerformed

    private void showpassBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpassBTActionPerformed
        // TODO add your handling code here:
        if (showpassBT.isSelected()) {
            Re_password.setEchoChar((char) 0); // Make the password visible
        } else {
            Re_password.setEchoChar('•'); // Hide the password
        }
    }//GEN-LAST:event_showpassBTActionPerformed

    private void showpassBT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpassBT1ActionPerformed
        // TODO add your handling code here:
        if (showpassBT1.isSelected()) {
            Password.setEchoChar((char) 0); // Make the password visible
        } else {
            Password.setEchoChar('•'); // Hide the password
        }
    }//GEN-LAST:event_showpassBT1ActionPerformed

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
            java.util.logging.Logger.getLogger(Signup_Recruiter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            new Signup_Recruiter().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Address_label;
    private javax.swing.JButton Back_btn;
    private javax.swing.JPanel Black_bg;
    private javax.swing.JLabel Cname_label;
    private javax.swing.JTextField CompanyName;
    private javax.swing.JTextField Email_Address;
    private javax.swing.JLabel Email_Address_label;
    private javax.swing.JLabel Logo;
    private javax.swing.JTextField PNumber;
    private javax.swing.JLabel PNumber_label;
    private javax.swing.JPasswordField Password;
    private javax.swing.JLabel Password_label;
    private javax.swing.JLabel ReEnter_label;
    private javax.swing.JPasswordField Re_password;
    private javax.swing.JLabel SignUp_label;
    private javax.swing.JButton Signup_btn;
    private javax.swing.JTextField address;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox showpassBT;
    private javax.swing.JCheckBox showpassBT1;
    // End of variables declaration//GEN-END:variables


    
}
