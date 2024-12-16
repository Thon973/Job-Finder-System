
package system_final;

import com.myapp.session.UserSession;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class Seeker_Query extends javax.swing.JInternalFrame {


    public Seeker_Query() {
        initComponents();  // Initialize the GUI components
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        displaySeekersWhoApplied();
    }
    private void displaySeekersWhoApplied() {
        String url = "jdbc:mysql://localhost:3306/job_finder_database";
        String user = "root";
        String password = "";

        // Get the logged-in user's email from the session
        String loggedInEmail = UserSession.getEmail();
        if (loggedInEmail == null) {
            JOptionPane.showMessageDialog(this, "No user is logged in.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Query to get seeker_id for the logged-in email
            String seekerIdQuery = "SELECT seeker_id FROM user_seeker WHERE email = ?";
            int seekerId = -1; // Default invalid value

            try (PreparedStatement pst = conn.prepareStatement(seekerIdQuery)) {
                pst.setString(1, loggedInEmail);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        seekerId = rs.getInt("seeker_id");
                    }
                }

                // If no seeker ID was found for the email, show an error message
                if (seekerId == -1) {
                    JOptionPane.showMessageDialog(this, "No seeker found for the logged-in email.");
                    return;
                }

                // Query to get all applications for the logged-in seeker
                String applicantsQuery =
                    "SELECT job_post.job_title, user_recruiter.company_name, " +
                    "applications.application_id, applications.date, applications.status " +
                    "FROM applications " +
                    "JOIN job_post ON applications.job_id = job_post.job_id " +
                    "JOIN user_recruiter ON job_post.recruiter_id = user_recruiter.recruiter_id " +
                    "WHERE applications.seeker_id = ?";

                try (PreparedStatement pst2 = conn.prepareStatement(applicantsQuery)) {
                    pst2.setInt(1, seekerId); // Use the seeker_id for the logged-in user

                    try (ResultSet rs2 = pst2.executeQuery()) {
                        DefaultTableModel model = new DefaultTableModel(
                            new String[]{"Application_Id", "Job_Title", "Company_Name", "Date_Applied", "Status"}, 0) {
                            // Make the table non-editable by overriding this method
                            @Override
                            public boolean isCellEditable(int row, int column) {
                                return false; // Disable cell editing
                            }
                        };

                        // Prepare date format
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                        while (rs2.next()) {
                            model.addRow(new Object[]{
                                rs2.getInt("application_id"),
                                rs2.getString("job_title"),
                                rs2.getString("company_name"),
                                sdf.format(rs2.getDate("date")), // Format the date as a string
                                rs2.getString("status")
                            });
                        }

                        table.setModel(model); // Your table name is "table"
                        configureTableRenderers(); // Center-align table content
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving applications: " + e.getMessage());
        }
    }
    
    private void configureTableRenderers() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        Delete_Btn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(780, 520));
        setPreferredSize(new java.awt.Dimension(780, 520));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        table.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Application_Id", "Job Title", "Recruiter_Name", "Date_Applied", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        Delete_Btn.setBackground(new java.awt.Color(0, 0, 0));
        Delete_Btn.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        Delete_Btn.setForeground(new java.awt.Color(255, 255, 255));
        Delete_Btn.setText("DELETE");
        Delete_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_BtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Delete_Btn)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Delete_Btn)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Delete_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_BtnActionPerformed
        int selectedRow = table.getSelectedRow();  // Get the selected row in the table
    
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        // Get the application_id from the selected row. Assuming it's in the first column.
        // We retrieve the value as Object and convert it to Integer if it's valid.
        Object applicationIdObj = table.getValueAt(selectedRow, 0); // Assuming application_id is in the first column
        int applicationId = -1;  // Default invalid value

        // Check if the application_id is a valid number (Integer).
        if (applicationIdObj instanceof Integer) {
            applicationId = (Integer) applicationIdObj; // Directly cast to Integer if it's already an Integer
        } else if (applicationIdObj instanceof String) {
            try {
                applicationId = Integer.parseInt((String) applicationIdObj);  // Parse string to integer
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid application ID format.");
                return;
            }
        }

        String url = "jdbc:mysql://localhost:3306/job_finder_database";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // SQL query to delete the application from the database
            String deleteQuery = "DELETE FROM applications WHERE application_id = ?";

            try (PreparedStatement pst = conn.prepareStatement(deleteQuery)) {
                pst.setInt(1, applicationId);  // Set the application_id parameter

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    // If the row is deleted successfully, show a message 
                    JOptionPane.showMessageDialog(this, "Application deleted successfully.");

                    // Refresh the table after deletion
                    displaySeekersWhoApplied();  // This will reload the data into the table
                } else {
                    JOptionPane.showMessageDialog(this, "Error deleting application.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting application: " + e.getMessage());
        }
    }//GEN-LAST:event_Delete_BtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete_Btn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
