
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
public class Seekerr_Inbox11 extends javax.swing.JInternalFrame {
    
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/job_finder_database";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private Connection conn;
    public Seekerr_Inbox11() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadApprovedCompaniesToJList();
        
        jTextPane1.setFont(new Font("Arial", Font.PLAIN, 14));
        message_content.setFont(new Font("Arial", Font.PLAIN, 16));
         
        // Add a listener to the JList for company selection
        seeker_list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedCompany = seeker_list.getSelectedValue();
                if (selectedCompany != null) {
                    loadMessagesForSelectedCompany(selectedCompany);
                }
            }
        });
    }
    
    private void loadApprovedCompaniesToJList() {
        DefaultListModel<String> model = new DefaultListModel<>();
        String seekerEmail = UserSession.getEmail();

        if (seekerEmail == null || seekerEmail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: No seeker is logged in.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            int seekerId = getSeekerId(conn, seekerEmail);
            if (seekerId == -1) {
                JOptionPane.showMessageDialog(this, "Error: Seeker not found.");
                return;
            }

            String query = "SELECT DISTINCT user_recruiter.company_name " +
                           "FROM applications " +
                           "JOIN job_post ON applications.job_id = job_post.job_id " +
                           "JOIN user_recruiter ON job_post.recruiter_id = user_recruiter.recruiter_id " +
                           "WHERE applications.seeker_id = ? AND applications.status = 'approved'";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, seekerId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    model.addElement(rs.getString("company_name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading approved companies: " + e.getMessage());
        }

        seeker_list.setModel(model);
    }
    
    private int getSeekerId(Connection conn, String email) throws SQLException {
        String query = "SELECT seeker_id FROM user_seeker WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("seeker_id");
            }
        }
        return -1;
    }
       
    private int getRecruiterId(Connection conn, String companyName) throws SQLException {
        String query = "SELECT recruiter_id FROM user_recruiter WHERE company_name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, companyName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("recruiter_id");
            }
        }
        return -1;
    }
    private void loadMessagesForSelectedCompany(String companyName) {
        String seekerEmail = UserSession.getEmail();
        if (seekerEmail == null || seekerEmail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: No seeker is logged in.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            int seekerId = getSeekerId(conn, seekerEmail);
            if (seekerId == -1) {
                JOptionPane.showMessageDialog(this, "Error: Seeker not found.");
                return;
            }

            int recruiterId = getRecruiterId(conn, companyName);
            if (recruiterId == -1) {
                JOptionPane.showMessageDialog(this, "Error: Recruiter not found.");
                return;
            }

            loadMessages(seekerId, recruiterId);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading messages: " + e.getMessage());
        }
    }

    private void loadMessages(int seekerId, int recruiterId) {
        String query = "SELECT sender_type, content FROM message " +
                       "WHERE (sender_id = ? AND receiver_id = ?) " +
                       "   OR (sender_id = ? AND receiver_id = ?) " +
                       "ORDER BY id ASC";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, seekerId);
            stmt.setInt(2, recruiterId);
            stmt.setInt(3, recruiterId);
            stmt.setInt(4, seekerId);

            ResultSet rs = stmt.executeQuery();
            StyledDocument doc = jTextPane1.getStyledDocument();
            doc.remove(0, doc.getLength()); // Clear existing text

            SimpleAttributeSet fontAttributes = new SimpleAttributeSet();
            StyleConstants.setFontFamily(fontAttributes, "Arial");
            StyleConstants.setFontSize(fontAttributes, 14);

            while (rs.next()) {
                String senderType = rs.getString("sender_type");
                String content = rs.getString("content");

                SimpleAttributeSet alignment = new SimpleAttributeSet();
                if ("seeker".equalsIgnoreCase(senderType)) {
                    StyleConstants.setAlignment(alignment, StyleConstants.ALIGN_RIGHT);
                    StyleConstants.setForeground(alignment, Color.BLUE);
                } else {
                    StyleConstants.setAlignment(alignment, StyleConstants.ALIGN_LEFT);
                    StyleConstants.setForeground(alignment, Color.BLACK);
                }

                SimpleAttributeSet combinedAttributes = new SimpleAttributeSet();
                combinedAttributes.addAttributes(fontAttributes);
                combinedAttributes.addAttributes(alignment);

                doc.setParagraphAttributes(doc.getLength(), content.length(), combinedAttributes, false);
                doc.insertString(doc.getLength(), content + "\n", combinedAttributes);
            }

        } catch (SQLException | BadLocationException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading messages: " + e.getMessage());
        }
    }

       
    private void sendMessage() {
        String content = message_content.getText().trim();
        if (content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a message.");
            return;
        }

        String seekerEmail = UserSession.getEmail();
        if (seekerEmail == null || seekerEmail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: No seeker is logged in.");
            return;
        }

        String selectedCompany = seeker_list.getSelectedValue();
        if (selectedCompany == null) {
            JOptionPane.showMessageDialog(this, "Please select a company from the list.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            int seekerId = getSeekerId(conn, seekerEmail);
            int recruiterId = getRecruiterId(conn, selectedCompany);

            String query = "INSERT INTO message (sender_type, sender_id, receiver_type, receiver_id, content) " +
                           "VALUES ('seeker', ?, 'recruiter', ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, seekerId);
                stmt.setInt(2, recruiterId);
                stmt.setString(3, content);
                stmt.executeUpdate();

                message_content.setText("");
                loadMessages(seekerId, recruiterId); // Refresh messages
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error sending the message: " + e.getMessage());
        }
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

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 17)); // NOI18N
        jLabel1.setText("JOB_RECRUITER");

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
        jTextPane1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTextPane1.setToolTipText("");
        jTextPane1.setDoubleBuffered(true);
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
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
