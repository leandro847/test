/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.awt.CardLayout;
import java.awt.*;
import javax.swing.*;

public class MainFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger =
        java.util.logging.Logger.getLogger(MainFrame.class.getName());

    private JPanel mainPanel;

    public MainFrame() {
        initComponents();
        setupComponents();
    }

    private void setupComponents() {
        getContentPane().removeAll(); // add this line first
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Profile");
        setSize(600, 400);
        setLayout(new BorderLayout());
        
        mainPanel = new JPanel(new CardLayout());
        mainPanel.add(new ProfilePanel(this::showCard, "Alex Rivera", "alexrivera", "alex@example.com", "555-0192"), "profile");
        mainPanel.add(new LoginPanel(), "login");
        mainPanel.add(new MailPanel(this::showCard), "mail"); // changed
        
        add(mainPanel, BorderLayout.CENTER);

        pack();
        setSize(1000, 600);
    }

    private void showCard(String name) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, name);
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
