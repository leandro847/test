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

    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;

    public DoctorPanel(){
        manager = new DoctorManager();
        setBackground( Color.WHITE);
        setLayout( new BorderLayout());

        JLabel title = new JLabel("Doctor Management");
        title.setFont( new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add( title, BorderLayout.NORTH);

        String[] columns = {"ID", "Name", "Specialty",
        "Phone", "Email", "Address"};

        model = new DefaultTableModel( columns, 0);
        table = new JTable( model);
        table.setRowHeight( 25);
        JScrollPane scrollPane = new JScrollPane( table);

        JPanel formPanel = new JPanel();
        formPanel.setBackground( Color.WHITE);
        formPanel.setLayout( new GridLayout(6, 2, 10, 10));
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground( Color.WHITE);
        addBtn = new JButton("Add");
        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");
        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground( Color.WHITE);
        rightPanel.setLayout( new BorderLayout());
        rightPanel.add( formPanel, BorderLayout.CENTER);
        rightPanel.add( buttonPanel, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground( Color.WHITE);
        centerPanel.setLayout( new GridLayout(1, 2, 10, 10));
        centerPanel.add( scrollPane);
        centerPanel.add( rightPanel);
        add( centerPanel, BorderLayout.CENTER);

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
            try {
                int id = Integer.parseInt( idField.getText());
                Doctor doctor = new Doctor( id, nameField.getText(), specialtyField.getText(),
                        phoneField.getText(),emailField.getText(), addressField.getText());
                manager.addDoctor( doctor);
                loadTable();
                clearFields();
                JOptionPane.showMessageDialog( this, "Doctor added successfully");
            } catch ( Exception ex){
                JOptionPane.showMessageDialog( this, "Invalid input");
            }
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
                    JOptionPane.showMessageDialog( this, "Doctor updated successfully");
                }
            }
        });
        deleteBtn.addActionListener( e3 -> {
            int row = table.getSelectedRow();
            if ( row >= 0){
                int confirm = JOptionPane.showConfirmDialog( this, "Delete this doctor?", "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);
                if ( confirm == JOptionPane.YES_NO_OPTION){
                    int id = Integer.parseInt( model.getValueAt( row, 0).toString());
                    manager.removeDoctor( id);
                    loadTable();
                    clearFields();
                    JOptionPane.showMessageDialog( this,"Doctor deleted succesfully");
                }
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
