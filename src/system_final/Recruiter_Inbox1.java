
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
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
public class Recruiter_Inbox1 extends javax.swing.JInternalFrame {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/job_finder_database";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private Connection conn;
    public Recruiter_Inbox1() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        // Setting font for the message pane
        jTextPane1.setFont(new Font("Arial", Font.PLAIN, 14));
         message_content.setFont(new Font("Arial", Font.PLAIN, 16));
         
        // Load approved applicants when the frame is initialized
        loadApprovedApplicantsToJList();

        // Add a listener to handle selection changes in the list
        seeker_list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedSeeker = seeker_list.getSelectedValue();
                if (selectedSeeker != null) {
                    loadMessagesForSelectedSeeker(selectedSeeker);
                }
            }
        });
    }
    
    private void loadApprovedApplicantsToJList() {
        DefaultListModel<String> model = new DefaultListModel<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String recruiterEmail = UserSession.getEmail();

            if (recruiterEmail == null || recruiterEmail.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No recruiter is logged in.");
                return;
            }

            String recruiterQuery = "SELECT recruiter_id FROM user_recruiter WHERE email = ?";
            try (PreparedStatement recruiterStmt = conn.prepareStatement(recruiterQuery)) {
                recruiterStmt.setString(1, recruiterEmail);
                ResultSet recruiterRs = recruiterStmt.executeQuery();

                if (recruiterRs.next()) {
                    int recruiterId = recruiterRs.getInt("recruiter_id");

                    String applicantQuery = """
                        SELECT CONCAT(user_seeker.first_name, ' ', user_seeker.last_name) AS full_name
                        FROM applications
                        JOIN user_seeker ON applications.seeker_id = user_seeker.seeker_id
                        JOIN job_post ON applications.job_id = job_post.job_id
                        WHERE job_post.recruiter_id = ? AND applications.status = 'approved'
                    """;

                    try (PreparedStatement applicantStmt = conn.prepareStatement(applicantQuery)) {
                        applicantStmt.setInt(1, recruiterId);
                        ResultSet rs = applicantStmt.executeQuery();

                        while (rs.next()) {
                            model.addElement(rs.getString("full_name"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading approved applicants: " + e.getMessage());
        }

        seeker_list.setModel(model);
    }
    
    private void sendMessage() {
        String content = message_content.getText().trim();
        if (content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a message.");
            return;
        }

        String recruiterEmail = UserSession.getEmail();
        String selectedSeeker = seeker_list.getSelectedValue();
        if (recruiterEmail == null || selectedSeeker == null) {
            JOptionPane.showMessageDialog(this, "Error: No recruiter or seeker selected.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            int recruiterId = getUserId(conn, recruiterEmail, "user_recruiter", "recruiter_id", "email");
            int seekerId = getUserId(conn, selectedSeeker, "user_seeker", "seeker_id", "CONCAT(first_name, ' ', last_name)");

            if (recruiterId == -1 || seekerId == -1) {
                JOptionPane.showMessageDialog(this, "Error identifying recruiter or seeker.");
                return;
            }

            String insertMessageQuery = """
                INSERT INTO message (sender_type, sender_id, receiver_type, receiver_id, content)
                VALUES ('recruiter', ?, 'seeker', ?, ?)
            """;

            try (PreparedStatement stmt = conn.prepareStatement(insertMessageQuery)) {
                stmt.setInt(1, recruiterId);
                stmt.setInt(2, seekerId);
                stmt.setString(3, content);
                stmt.executeUpdate();

                message_content.setText("");
                loadMessages(seekerId, recruiterId);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error sending message: " + e.getMessage());
        }
    }
    
    private void loadMessagesForSelectedSeeker(String seekerName) {
        String recruiterEmail = UserSession.getEmail();
        if (recruiterEmail == null || recruiterEmail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No recruiter is logged in.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            int recruiterId = getUserId(conn, recruiterEmail, "user_recruiter", "recruiter_id", "email");
            int seekerId = getUserId(conn, seekerName, "user_seeker", "seeker_id", "CONCAT(first_name, ' ', last_name)");

            if (recruiterId == -1 || seekerId == -1) {
                JOptionPane.showMessageDialog(this, "Error identifying recruiter or seeker.");
                return;
            }

            loadMessages(seekerId, recruiterId);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading messages: " + e.getMessage());
        }
    }

    
    private void loadMessages(int seekerId, int recruiterId) {
        String query = """
            SELECT sender_type, content
            FROM message
            WHERE (sender_id = ? AND receiver_id = ?)
               OR (sender_id = ? AND receiver_id = ?)
            ORDER BY id ASC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, recruiterId);
            stmt.setInt(2, seekerId);
            stmt.setInt(3, seekerId);
            stmt.setInt(4, recruiterId);

            ResultSet rs = stmt.executeQuery();
            StyledDocument doc = jTextPane1.getStyledDocument();
            doc.remove(0, doc.getLength());
            
            SimpleAttributeSet fontAttributes = new SimpleAttributeSet();
            StyleConstants.setFontFamily(fontAttributes, "Arial");
            StyleConstants.setFontSize(fontAttributes, 14);
            
            while (rs.next()) {
                String senderType = rs.getString("sender_type");
                String content = rs.getString("content");
                
                SimpleAttributeSet alignment = new SimpleAttributeSet();
                if ("recruiter".equalsIgnoreCase(senderType)) {
                    jTextPane1.setFont(new Font("Arial", Font.PLAIN, 14));
                    StyleConstants.setAlignment(alignment, StyleConstants.ALIGN_RIGHT);
                    StyleConstants.setForeground(alignment, Color.BLUE);
                } else {
                    StyleConstants.setAlignment(alignment, StyleConstants.ALIGN_LEFT);
                    StyleConstants.setForeground(alignment, Color.BLACK);
                    StyleConstants.setFontFamily(alignment, "Arial"); // Set the font family
                    StyleConstants.setFontSize(alignment, 12);
                }
                
                SimpleAttributeSet combinedAttributes = new SimpleAttributeSet();
                combinedAttributes.addAttributes(fontAttributes);
                combinedAttributes.addAttributes(alignment);

                doc.setParagraphAttributes(doc.getLength(), content.length(), combinedAttributes, false);
                doc.insertString(doc.getLength(), content + "\n", combinedAttributes);
            }
        } catch (SQLException | BadLocationException e) {
            JOptionPane.showMessageDialog(this, "Error loading messages: " + e.getMessage());
        }
    }
    
     private int getUserId(Connection conn, String value, String table, String idColumn, String matchColumn) throws SQLException {
        String query = "SELECT " + idColumn + " FROM " + table + " WHERE " + matchColumn + " = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, value);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(idColumn);
            }
        }
        return -1;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        seeker_list = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        Send_btn = new javax.swing.JButton();
        message_content = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(780, 520));
        setPreferredSize(new java.awt.Dimension(780, 520));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel1.setText("JOB_SEEKER");

        jScrollPane1.setToolTipText("");

        seeker_list.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        seeker_list.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(seeker_list);

        Send_btn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Send_btn.setText("Send");
        Send_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Send_btnActionPerformed(evt);
            }
        });

        message_content.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        message_content.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                message_contentActionPerformed(evt);
            }
        });

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextPane1.setEditable(false);
        jTextPane1.setContentType(""); // NOI18N
        jTextPane1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jScrollPane3.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(message_content, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Send_btn)
                .addContainerGap())
            .addComponent(jScrollPane3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Send_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(message_content, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Send_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Send_btnActionPerformed
        sendMessage();
    }//GEN-LAST:event_Send_btnActionPerformed
    
    public void sendMessage(int senderId, int receiverId, String content, String senderType, String receiverType) {
        String insertMessageQuery = "INSERT INTO message (sender_id, receiver_id, content) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jfdb", "root", "");
             PreparedStatement stmt = conn.prepareStatement(insertMessageQuery)) {

            stmt.setInt(1, senderId);
            stmt.setInt(2, receiverId);
            stmt.setString(3, content);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Message sent successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error sending the message: " + e.getMessage());
        }
    }
    
    private void message_contentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_message_contentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_message_contentActionPerformed
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Send_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextField message_content;
    private javax.swing.JList<String> seeker_list;
    // End of variables declaration//GEN-END:variables
}
