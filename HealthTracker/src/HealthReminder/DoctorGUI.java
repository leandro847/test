package HealthReminder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DoctorGUI extends JFrame{

    private DoctorManager manager = new DoctorManager();

    private JTable generalTable, personalTable;
    private DefaultTableModel generalModel, personalModel;

    private JTextField nameField, specialtyField,
    phoneField, emailField, addressField;


    public DoctorGUI() {
        setTitle("Doctor Management");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columns = {"ID", "Name", "Specialty", "Phone",
                "Email", "Address"};

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

    private void loadTable(DefaultTableModel model, List<Doctor> list){
        model.setRowCount(0);
        for ( Doctor d: list){
            model.addRow( new Object[]{
                    d.getDoctorID(), d.getName(),
                    d.getSpecialty(), d.getPhone(),
                    d.getEmail(), d.getAddress()
            });
        }
    }
    public static void main( String[] args){
        SwingUtilities.invokeLater(() -> new DoctorGUI().setVisible(true));
    }
}