package HealthTrackerApp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

import java.awt.*;
import javax.swing.*;
import java.util.function.Consumer;

public class ProfilePanel extends javax.swing.JPanel {

    private JTextField tfID, tfName, tfEmail;
    private JLabel title, valID, valName, valEmail;
    private JButton btnMail, btnLogout, btnEdit, btnSave, btnCancel;
    private final User user;
    private final Consumer<String> navigator;
    private GridBagConstraints gbc;

    
    public ProfilePanel(Consumer<String> navigator, User user) {
        this.user = user;
        this.navigator = navigator;
        setupComponents();
    }

    
    private void setupComponents() {
        // clear
        removeAll();
        
        // set style
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240));

        // build ui
        buildTextFields();
        buildButtons();
        buildLayout();
        buildButtonMenu();

        // populate panel
        revalidate();
        repaint();
    }

    
    private void buildButtonMenu() {
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        bottom.setBackground(new Color(240, 240, 240));
        bottom.add(btnMail);
        bottom.add(btnLogout);
        bottom.add(btnEdit);
        bottom.add(btnSave);
        bottom.add(btnCancel);
        add(bottom, gbc);
    }
    
    
    private void buildLayout() {
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 10, 4, 10); // small padding
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;

        // Title spanning both columns
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);
        gbc.gridwidth = 1; // reset

        addRow("ID:",    valID,    tfID,    gbc, 1);
        addRow("Name:",  valName,  tfName,  gbc, 2);
        addRow("Email:", valEmail, tfEmail, gbc, 3);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
    }
    
    
    private void buildTextFields() {
        title = new JLabel("Profile Page");
        title.setForeground(new Color(180, 50, 50));
        title.setFont(new Font("SansSerif", Font.PLAIN, 20));
        
        valID    = new JLabel(String.valueOf(user.userID));
        valName  = new JLabel(user.name);
        valEmail = new JLabel(user.email);

        tfID    = new JTextField(String.valueOf(user.userID), 15);
        tfName  = new JTextField(user.name, 15);
        tfEmail = new JTextField(user.email, 15);

        tfID.setVisible(false);
        tfName.setVisible(false);
        tfEmail.setVisible(false);
    }
    
    
    private void buildButtons() {
        btnMail   = new JButton("Go to Mail");
        btnLogout = new JButton("logout");
        btnEdit   = new JButton("Edit");
        btnSave   = new JButton("Save");
        btnCancel = new JButton("Cancel");
        
        btnSave.setVisible(false);
        btnCancel.setVisible(false);
        
        btnMail.addActionListener(e -> {
            navigator.accept("mail"); // go to mail summary
            cancelChanges(); // in case user is editing when go to mail pressed
        });
        
        btnLogout.addActionListener(e -> {
            navigator.accept("login"); // go to login page
            cancelChanges(); // in case user is editing when logout pressed
        });
        
        btnEdit.addActionListener(e -> {setEditing(true);});
        btnCancel.addActionListener(e -> {cancelChanges();});
        btnSave.addActionListener(e -> {saveChanges();});
    }
    
    
    private void saveChanges() {
        // update user info
        user.userID = Integer.parseInt(tfID.getText());
        user.name = tfName.getText();
        user.email = tfEmail.getText();
        
        // update display info
        valID.setText(tfID.getText());
        valName.setText(tfName.getText());
        valEmail.setText(tfEmail.getText());
        setEditing(false);
    }
    
    
    private void cancelChanges() {
        // set textfields back to old values
        tfID.setText(valID.getText());
        tfName.setText(valName.getText());
        tfEmail.setText(valEmail.getText());
        setEditing(false);
    }
    
    
    private void setEditing(boolean editing) {
        // make values invisible
        valID.setVisible(!editing);
        valName.setVisible(!editing);
        valEmail.setVisible(!editing);

        // make textfields visible
        tfID.setVisible(editing);
        tfName.setVisible(editing);
        tfEmail.setVisible(editing);

        // set edit invisible
        btnEdit.setVisible(!editing);
        // set save and cancel visible
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
        
        gbc.gridx = 0; 
        gbc.gridy = row;
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
