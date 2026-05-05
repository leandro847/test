/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

import java.awt.*;
import javax.swing.*;
import java.util.function.Consumer;

public class ProfilePanel extends javax.swing.JPanel {

    private JTextField tfName, tfUsername, tfEmail, tfPhone;
    private JLabel valName, valUsername, valEmail, valPhone;
    private JButton btnMail, btnLogout, btnEdit, btnSave, btnCancel;

    public ProfilePanel(Consumer<String> navigator, String name, String username, String email, String phone) {
        initComponents();
        setupComponents(navigator, name, username, email, phone);
    }

    private void setupComponents(Consumer<String> navigator, String name, String username, String email, String phone) {
        removeAll();
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 20, 4, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Title
        JLabel title = new JLabel("Profile Page");
        title.setForeground(new Color(180, 50, 50));
        title.setFont(new Font("SansSerif", Font.PLAIN, 13));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);
        gbc.gridwidth = 1;

        // Labels
        valName     = new JLabel(name);
        valUsername = new JLabel(username);
        valEmail    = new JLabel(email);
        valPhone    = new JLabel(phone);

        // Text fields (hidden initially)
        tfName     = new JTextField(name, 15);
        tfUsername = new JTextField(username, 15);
        tfEmail    = new JTextField(email, 15);
        tfPhone    = new JTextField(phone, 15);

        tfName.setVisible(false);
        tfUsername.setVisible(false);
        tfEmail.setVisible(false);
        tfPhone.setVisible(false);

        addRow("Name:",     valName,     tfName,     gbc, 1);
        addRow("Username:", valUsername, tfUsername, gbc, 2);
        addRow("Email:",    valEmail,    tfEmail,    gbc, 3);
        addRow("Phone:",    valPhone,    tfPhone,    gbc, 4);

        // Buttons
        btnMail = new JButton("Go to Mail");
        btnLogout = new JButton("logout");
        btnEdit = new JButton("Edit");
        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");

        btnSave.setVisible(false);
        btnCancel.setVisible(false);
        
        btnMail.addActionListener(e -> navigator.accept("mail"));
        btnLogout.addActionListener(e -> navigator.accept("login"));

        // Edit clicked — show text fields
        btnEdit.addActionListener(e -> {
            setEditing(true);
        });

        // Cancel clicked — revert
        btnCancel.addActionListener(e -> {
            // reset fields back to current label values
            tfName.setText(valName.getText());
            tfUsername.setText(valUsername.getText());
            tfEmail.setText(valEmail.getText());
            tfPhone.setText(valPhone.getText());
            setEditing(false);
        });

        // Save clicked — update labels
        btnSave.addActionListener(e -> {
            valName.setText(tfName.getText());
            valUsername.setText(tfUsername.getText());
            valEmail.setText(tfEmail.getText());
            valPhone.setText(tfPhone.getText());
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
        valName.setVisible(!editing);
        valUsername.setVisible(!editing);
        valEmail.setVisible(!editing);
        valPhone.setVisible(!editing);

        tfName.setVisible(editing);
        tfUsername.setVisible(editing);
        tfEmail.setVisible(editing);
        tfPhone.setVisible(editing);

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
