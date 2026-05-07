/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package HealthTrackerApp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Leandropc
 */
public class DoctorPanel extends javax.swing.JPanel {

    private DoctorManager manager = new DoctorManager();

    private JTable generalTable, personalTable;
    private DefaultTableModel generalModel, personalModel;

    private JTextField nameField, specialtyField, phoneField, emailField, addressField;
    /**
     * Creates new form DoctorPanel
     * @param navigator
     */
    public DoctorPanel(Consumer<String> navigator) {
        initComponents();
        setupComponents(navigator);
    }
    
    private void setupComponents(Consumer<String> navigator) {
        setLayout(new BorderLayout());

        String[] columns = {"ID", "Name", "Specialty", "Phone", "Email", "Address"};

        generalModel = new DefaultTableModel(columns, 0);
        personalModel = new DefaultTableModel(columns, 0);

        generalTable = new JTable(generalModel);
        personalTable = new JTable(personalModel);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(generalTable),
                new JScrollPane(personalTable)
        );

        add(splitPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));

        nameField = new JTextField();
        specialtyField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        addressField = new JTextField();

        inputPanel.add(new JLabel("Name"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Specialty"));
        inputPanel.add(specialtyField);
        inputPanel.add(new JLabel("Phone"));
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel("Email"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Address"));
        inputPanel.add(addressField);

        add(inputPanel, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel();

        JButton addBtn = new JButton("Add");
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");

        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);

        add(btnPanel, BorderLayout.SOUTH);

        loadTable(generalModel, manager.getGeneralList());
        loadTable(personalModel, manager.getPersonalList());

        addBtn.addActionListener(a1 -> {
            int row = generalTable.getSelectedRow();
            if (row >= 0) {
                int id = (int) generalModel.getValueAt(row, 0);
                for (Doctor d : manager.getGeneralList()) {
                    if (d.getDoctorID() == id) {
                        manager.addToPersonal(d);
                        break;
                    }
                }
                loadTable(personalModel, manager.getPersonalList());
            }
        });

        editBtn.addActionListener(a2 -> {
            int row = personalTable.getSelectedRow();
            if (row >= 0) {
                int id = (int) personalModel.getValueAt(row, 0);
                for (Doctor d : manager.getPersonalList()) {
                    if (d.getDoctorID() == id) {
                        if (!nameField.getText().isEmpty()) d.setName(nameField.getText());
                        if (!specialtyField.getText().isEmpty()) d.setSpecialty(specialtyField.getText());
                        if (!phoneField.getText().isEmpty()) d.setPhone(phoneField.getText());
                        if (!emailField.getText().isEmpty()) d.setEmail(emailField.getText());
                        if (!addressField.getText().isEmpty()) d.setAddress(addressField.getText());
                        break;
                    }
                }
                loadTable(personalModel, manager.getPersonalList());
            }
        });

        deleteBtn.addActionListener(a3 -> {
            int row = personalTable.getSelectedRow();
            if (row >= 0) {
                int id = (int) personalModel.getValueAt(row, 0);
                manager.removeFromPersonal(id);
                loadTable(personalModel, manager.getPersonalList());
            }
        });
    }
    
    private void loadTable(DefaultTableModel model, List<Doctor> list) {
        model.setRowCount(0);
        for (Doctor d : list) {
            model.addRow(new Object[]{
                    d.getDoctorID(), d.getName(),
                    d.getSpecialty(), d.getPhone(),
                    d.getEmail(), d.getAddress()
            });
        }
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
