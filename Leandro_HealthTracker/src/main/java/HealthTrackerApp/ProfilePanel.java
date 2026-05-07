package HealthTrackerApp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

import java.awt.*;
import javax.swing.*;
import java.util.function.Consumer;

public class ProfilePanel extends javax.swing.JPanel {

    private JTextField tfID, tfName, tfUsername, tfEmail, tfPhone;
    private JLabel valID, valName, valUsername, valEmail, valPhone;
    private JButton btnMail, btnLogout, btnEdit, btnSave, btnCancel;
    private User user;

    public ProfilePanel(Consumer<String> navigator, User user) {
        this.user = user;
        initComponents();
        setupComponents(navigator);
    }

    private void setupComponents(Consumer<String> navigator) {
        removeAll();
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 20, 4, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Title
        JLabel title = new JLabel("Profile Page");
        title.setForeground(new Color(180, 50, 50));
        title.setFont(new Font("SansSerif", Font.PLAIN, 20));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);
        gbc.gridwidth = 1;

        // Labels
        valID       = new JLabel(String.valueOf(user.userID));
        valName     = new JLabel(user.name);
        valEmail    = new JLabel(user.email);

        // Text fields (hidden initially)
        tfID     = new JTextField(String.valueOf(user.userID), 15);
        tfName     = new JTextField(user.name, 15);
        tfEmail    = new JTextField(user.email, 15);

        tfID.setVisible(false);
        tfName.setVisible(false);
        //tfUsername.setVisible(false);
        tfEmail.setVisible(false);
        //tfPhone.setVisible(false);

        addRow("ID:",     valID,     tfID,     gbc, 1);
        addRow("Name:",     valName,     tfName,     gbc, 2);
        //addRow("Username:", valUsername, tfUsername, gbc, 2);
        addRow("Email:",    valEmail,    tfEmail,    gbc, 3);
        //addRow("Phone:",    valPhone,    tfPhone,    gbc, 4);

        // Buttons
        btnMail = new JButton("Go to Mail");
        btnLogout = new JButton("logout");
        btnEdit = new JButton("Edit");
        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");

        btnSave.setVisible(false);
        btnCancel.setVisible(false);
        
        btnMail.addActionListener(e -> {
            navigator.accept("mail");
            tfID.setText(valID.getText());
            tfName.setText(valName.getText());
            //tfUsername.setText(valUsername.getText());
            tfEmail.setText(valEmail.getText());
            //tfPhone.setText(valPhone.getText());
            setEditing(false);
        });
        
        btnLogout.addActionListener(e -> {
            navigator.accept("login");
            // reset fields back to current label values
            tfID.setText(valID.getText());
            tfName.setText(valName.getText());
            //tfUsername.setText(valUsername.getText());
            tfEmail.setText(valEmail.getText());
            //tfPhone.setText(valPhone.getText());
            setEditing(false);
        });

        
        // Edit clicked — show text fields
        btnEdit.addActionListener(e -> {
            setEditing(true);
        });

        // Cancel clicked — revert
        btnCancel.addActionListener(e -> {
            // reset fields back to current label values
            tfID.setText(valID.getText());
            tfName.setText(valName.getText());
            //tfUsername.setText(valUsername.getText());
            tfEmail.setText(valEmail.getText());
            //tfPhone.setText(valPhone.getText());
            setEditing(false);
        });

        // Save clicked — update labels
        btnSave.addActionListener(e -> {
            valID.setText(tfID.getText());
            valName.setText(tfName.getText());
            //valUsername.setText(tfUsername.getText());
            valEmail.setText(tfEmail.getText());
            //valPhone.setText(tfPhone.getText());
            setEditing(false);
        });

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        bottom.setBackground(new Color(240, 240, 240));
        bottom.add(btnMail);
        bottom.add(btnLogout);
        bottom.add(btnEdit);
        bottom.add(btnSave);
        bottom.add(btnCancel);
        add(bottom, gbc);

        revalidate();
        repaint();
    }

    private void setEditing(boolean editing) {
        valID.setVisible(!editing);
        valName.setVisible(!editing);
        //valUsername.setVisible(!editing);
        valEmail.setVisible(!editing);
        //valPhone.setVisible(!editing);

        tfID.setVisible(editing);
        tfName.setVisible(editing);
        //tfUsername.setVisible(editing);
        tfEmail.setVisible(editing);
        //tfPhone.setVisible(editing);

        btnEdit.setVisible(!editing);
        btnSave.setVisible(editing);
        btnCancel.setVisible(editing);

        revalidate();
        repaint();
    }

    private void addRow(String labelText, JLabel val, JTextField tf, GridBagConstraints gbc, int row) {
        JLabel key = new JLabel(labelText);
        key.setForeground(new Color(180, 50, 50));
        key.setFont(new Font("SansSerif", Font.PLAIN, 13));
        val.setFont(new Font("SansSerif", Font.PLAIN, 13));

        gbc.gridx = 0; gbc.gridy = row;
        add(key, gbc);
        gbc.gridx = 1;
        add(val, gbc);
        add(tf, gbc);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
