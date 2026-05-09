package HealthReminder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class DoctorPanel extends JPanel {
    private DoctorManager manager;

    private JTable table;
    private DefaultTableModel model;
    private JTextField idField;
    private JTextField nameField;
    private JTextField specialtyField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField addressField;

    public DoctorPanel(){
        manager = new DoctorManager();
        setLayout( new BorderLayout());

        String[] columns = {"ID", "Name", "Specialty",
        "Phone", "Email", "Address"};

        model = new DefaultTableModel( columns, 0);
        table = new JTable( model);
        JScrollPane scrollPane = new JScrollPane( table);
        add( scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel( new GridLayout( 6, 2));
        idField = new JTextField();
        nameField = new JTextField();
        specialtyField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        addressField = new JTextField();

        formPanel.add( new JLabel("ID"));
        formPanel.add(idField);
        formPanel.add( new JLabel("Name"));
        formPanel.add(nameField);
        formPanel.add( new JLabel("Specialty"));
        formPanel.add(specialtyField);
        formPanel.add( new JLabel("Phone"));
        formPanel.add(phoneField);
        formPanel.add( new JLabel("Email"));
        formPanel.add(emailField);
        formPanel.add( new JLabel("Address"));
        formPanel.add(addressField);
        add( formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");
        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        add( buttonPanel, BorderLayout.SOUTH);

        loadTable();

        table.getSelectionModel().addListSelectionListener( e -> {
            int row = table.getSelectedRow();
            if ( row >= 0){
                idField.setText( model.getValueAt( row, 0).toString());
                nameField.setText( model.getValueAt( row, 1).toString());
                specialtyField.setText( model.getValueAt( row, 2).toString());
                phoneField.setText( model.getValueAt( row, 3).toString());
                emailField.setText( model.getValueAt( row, 4).toString());
                addressField.setText( model.getValueAt( row, 5).toString());
            }
        });
        addBtn.addActionListener( e1 ->{
            Doctor doctor = new Doctor(
                    Integer.parseInt( idField.getText()), nameField.getText(),
                    specialtyField.getText(), phoneField.getText(),
                    emailField.getText(), addressField.getText());
            manager.addDoctor( doctor);
            loadTable();
            clearFields();
        });
        editBtn.addActionListener( e2 -> {
            int row = table.getSelectedRow();
            if ( row >= 0){
                int id = Integer.parseInt( model.getValueAt( row, 0).toString());
                Doctor doctor = manager.getDoctor( id);

                if ( doctor != null){
                    doctor.setName(nameField.getText());
                    doctor.setSpecialty( specialtyField.getText());
                    doctor.setPhone( phoneField.getText());
                    doctor.setEmail( emailField.getText());
                    doctor.setAddress( addressField.getText());
                    loadTable();
                    clearFields();
                }
            }
        });
        deleteBtn.addActionListener( e3 -> {
            int row = table.getSelectedRow();
            if ( row >= 0){
                int id = Integer.parseInt( model.getValueAt( row, 0).toString());
                manager.removeDoctor( id);
                loadTable();
                clearFields();
            }
        });
    }
    private void loadTable(){
        model.setRowCount( 0);
        for ( Doctor d: manager.getDoctorList()){
            model.addRow( new Object[]{
                    d.getDoctorID(), d.getName(), d.getSpecialty(),
                    d.getPhone(), d.getEmail(), d.getAddress()
            });
        }
    }
    private void clearFields(){
        idField.setText("");
        nameField.setText("");
        specialtyField.setText("");
        phoneField.setText("");
        emailField.setText("");
        addressField.setText("");
    }
}
