
package system_final;
import java.awt.Color;
import javax.swing.JOptionPane;

public class Seeker_Dashboard extends javax.swing.JFrame {

    
    Color DefaultColor, ClickedColor;
    public Seeker_Dashboard() {
        initComponents();
        DefaultColor = new Color(255,255,255);
        ClickedColor = new Color(204,204,204);
        
        Seeker_Home111 internalFrame = new Seeker_Home111();
        internalFrame.setVisible(true);
        jDesktopPane1.add(internalFrame);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Log_out = new javax.swing.JLabel();
        JF_label = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Profile = new javax.swing.JPanel();
        Profile_lb = new javax.swing.JLabel();
        Home = new javax.swing.JPanel();
        Home_lb = new javax.swing.JLabel();
        Inbox = new javax.swing.JPanel();
        Inbox_lb = new javax.swing.JLabel();
        Query = new javax.swing.JPanel();
        Query_lb = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 70));

        Log_out.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logout.png"))); // NOI18N
        Log_out.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Log_outMouseClicked(evt);
            }
        });

        JF_label.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        JF_label.setForeground(new java.awt.Color(255, 255, 255));
        JF_label.setText("JOB FINDER");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(JF_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Log_out, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JF_label)
                    .addComponent(Log_out))
                .addGap(13, 13, 13))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        Profile.setBackground(new java.awt.Color(255, 255, 255));
        Profile.setForeground(new java.awt.Color(255, 255, 255));
        Profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProfileMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ProfileMousePressed(evt);
            }
        });

        Profile_lb.setBackground(new java.awt.Color(255, 255, 255));
        Profile_lb.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        Profile_lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/account (1).png"))); // NOI18N
        Profile_lb.setText("    PROFILE");
        Profile_lb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Profile_lbMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout ProfileLayout = new javax.swing.GroupLayout(Profile);
        Profile.setLayout(ProfileLayout);
        ProfileLayout.setHorizontalGroup(
            ProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProfileLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Profile_lb)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        ProfileLayout.setVerticalGroup(
            ProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProfileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Profile_lb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Home.setBackground(new java.awt.Color(204, 204, 204));
        Home.setForeground(new java.awt.Color(255, 255, 255));
        Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HomeMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                HomeMousePressed(evt);
            }
        });

        Home_lb.setBackground(new java.awt.Color(255, 255, 255));
        Home_lb.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        Home_lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home__1_-removebg-preview (1).png"))); // NOI18N
        Home_lb.setText("    HOME");
        Home_lb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Home_lbMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout HomeLayout = new javax.swing.GroupLayout(Home);
        Home.setLayout(HomeLayout);
        HomeLayout.setHorizontalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Home_lb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        HomeLayout.setVerticalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Home_lb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Inbox.setBackground(new java.awt.Color(255, 255, 255));
        Inbox.setForeground(new java.awt.Color(255, 255, 255));
        Inbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InboxMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                InboxMousePressed(evt);
            }
        });

        Inbox_lb.setBackground(new java.awt.Color(255, 255, 255));
        Inbox_lb.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        Inbox_lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/mail-inbox-app (1).png"))); // NOI18N
        Inbox_lb.setText("    INBOX");
        Inbox_lb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inbox_lbMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout InboxLayout = new javax.swing.GroupLayout(Inbox);
        Inbox.setLayout(InboxLayout);
        InboxLayout.setHorizontalGroup(
            InboxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InboxLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Inbox_lb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        InboxLayout.setVerticalGroup(
            InboxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InboxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Inbox_lb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Query.setBackground(new java.awt.Color(255, 255, 255));
        Query.setForeground(new java.awt.Color(255, 255, 255));
        Query.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QueryMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                QueryMousePressed(evt);
            }
        });

        Query_lb.setBackground(new java.awt.Color(255, 255, 255));
        Query_lb.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        Query_lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-list (1).png"))); // NOI18N
        Query_lb.setText("    QUERY");

        javax.swing.GroupLayout QueryLayout = new javax.swing.GroupLayout(Query);
        Query.setLayout(QueryLayout);
        QueryLayout.setHorizontalGroup(
            QueryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QueryLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Query_lb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        QueryLayout.setVerticalGroup(
            QueryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QueryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Query_lb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Profile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Inbox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Query, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(Home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Inbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Query, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Profile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jDesktopPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDesktopPane1)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ProfileMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileMousePressed
        // TODO add your handling code here:
        Profile.setBackground(ClickedColor);
        Home.setBackground(DefaultColor);
        Inbox.setBackground(DefaultColor);
        Query.setBackground(DefaultColor);

    }//GEN-LAST:event_ProfileMousePressed

    private void HomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMousePressed
        // TODO add your handling code here:
        Profile.setBackground(DefaultColor);
        Home.setBackground(ClickedColor);
        Inbox.setBackground(DefaultColor);
        Query.setBackground(DefaultColor);

    }//GEN-LAST:event_HomeMousePressed

    private void InboxMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InboxMousePressed
        // TODO add your handling code here:
        Profile.setBackground(DefaultColor);
        Home.setBackground(DefaultColor);
        Inbox.setBackground(ClickedColor);
        Query.setBackground(DefaultColor);

    }//GEN-LAST:event_InboxMousePressed

    private void QueryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QueryMousePressed
        // TODO add your handling code here:
        Profile.setBackground(DefaultColor);
        Home.setBackground(DefaultColor);
        Inbox.setBackground(DefaultColor);
        Query.setBackground(ClickedColor);

    }//GEN-LAST:event_QueryMousePressed

    private void ProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileMouseClicked
        // TODO add your handling code here
        Seeker_Profile seeker_profile = new Seeker_Profile();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(seeker_profile);
        seeker_profile.setVisible(true);  // Make the Seeker_Profile visible
        jDesktopPane1.repaint();  // Repaint the jDesktopPane to show the added componen
        
        Profile.setBackground(ClickedColor);
        Home.setBackground(DefaultColor);
        Inbox.setBackground(DefaultColor);
        Query.setBackground(DefaultColor);

    }//GEN-LAST:event_ProfileMouseClicked

    private void HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMouseClicked
        // TODO add your handling code here:
        Seeker_Home111 seeker_home = new Seeker_Home111();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(seeker_home);
        seeker_home.setVisible(true);  // Make the Seeker_Profile visible
        jDesktopPane1.repaint();
        
        Profile.setBackground(DefaultColor);
        Home.setBackground(ClickedColor);
        Inbox.setBackground(DefaultColor);
        Query.setBackground(DefaultColor);

    }//GEN-LAST:event_HomeMouseClicked

    private void InboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InboxMouseClicked
        // TODO add your handling code here:
        Seekerr_Inbox11 seeker_inbox = new Seekerr_Inbox11();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(seeker_inbox);
        seeker_inbox.setVisible(true);  // Make the Seeker_Profile visible
        jDesktopPane1.repaint();
        
    }//GEN-LAST:event_InboxMouseClicked

    private void QueryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QueryMouseClicked
        // TODO add your handling code here:
        Seeker_Query seeker_query = new Seeker_Query();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(seeker_query);
        seeker_query.setVisible(true);  // Make the Seeker_Profile visible
        jDesktopPane1.repaint();
        
        Profile.setBackground(DefaultColor);
        Home.setBackground(DefaultColor);
        Inbox.setBackground(DefaultColor);
        Query.setBackground(ClickedColor);

    }//GEN-LAST:event_QueryMouseClicked

    private void Inbox_lbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inbox_lbMouseClicked
        // TODO add your handling code here:
        Seekerr_Inbox11 seeker_inbox = new Seekerr_Inbox11();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(seeker_inbox);
        seeker_inbox.setVisible(true);  // Make the Seeker_Profile visible
        jDesktopPane1.repaint();
        
        Profile.setBackground(DefaultColor);
        Home.setBackground(DefaultColor);
        Inbox.setBackground(ClickedColor);
        Query.setBackground(DefaultColor);
 
    }//GEN-LAST:event_Inbox_lbMouseClicked

    private void Log_outMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Log_outMouseClicked
        // TODO add your handling code here:
        // Display a confirmation dialog
        int response = JOptionPane.showConfirmDialog(
            this,
            "Do you want to log out?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        // Check the user's choice
        if (response == JOptionPane.YES_OPTION) {
            // Dispose of the current form
            this.dispose();

            Login_Final LoginFrame = new Login_Final();
            LoginFrame.setVisible(true);
            LoginFrame.pack();
            LoginFrame.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_Log_outMouseClicked

    private void Home_lbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home_lbMouseClicked
        // TODO add your handling code here:
        Profile.setBackground(DefaultColor);
        Home.setBackground(ClickedColor);
        Inbox.setBackground(DefaultColor);
        Query.setBackground(DefaultColor);

        
        Seeker_Home111 seeker_home = new Seeker_Home111();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(seeker_home);
        seeker_home.setVisible(true);  // Make the Seeker_Profile visible
        jDesktopPane1.repaint();
        
    }//GEN-LAST:event_Home_lbMouseClicked

    private void Profile_lbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Profile_lbMouseClicked
        // TODO add your handling code here:
        Profile.setBackground(ClickedColor);
        Home.setBackground(DefaultColor);
        Inbox.setBackground(DefaultColor);
        Query.setBackground(DefaultColor);

        Seeker_Profile seeker_profile = new Seeker_Profile();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(seeker_profile);
        seeker_profile.setVisible(true);  // Make the Seeker_Profile visible
        jDesktopPane1.repaint();  // Repaint the jDesktopPane to show the added componen
    }//GEN-LAST:event_Profile_lbMouseClicked

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Seeker_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Seeker_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Seeker_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Seeker_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Seeker_Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Home;
    private javax.swing.JLabel Home_lb;
    private javax.swing.JPanel Inbox;
    private javax.swing.JLabel Inbox_lb;
    private javax.swing.JLabel JF_label;
    private javax.swing.JLabel Log_out;
    private javax.swing.JPanel Profile;
    private javax.swing.JLabel Profile_lb;
    private javax.swing.JPanel Query;
    private javax.swing.JLabel Query_lb;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
