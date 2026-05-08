/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package HealthTrackerApp;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.function.Consumer;

/**
 *
 * @author Leandropc
 */
public class TabBarsPagePanel extends javax.swing.JPanel {

    /**
     * Creates new form TabBarsPagePanel
     */
    
    private JButton btnAppointments, btnTreatments, btnDoctors, btnProfile;
    
    private javax.swing.JPanel appointmentMain;
    private javax.swing.JScrollBar appointmentScrollBar1;
    private javax.swing.JTextField appointmentSearch;
    private javax.swing.JTable appointmentsTable;
    private javax.swing.JButton deleteAppointmentButton;
    private javax.swing.JButton editAppointmentButton;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollBar jScrollBar2;
    private javax.swing.JScrollBar jScrollBar3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTableTreatments;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JButton newAppointmnetButton;
    private javax.swing.JTextArea upcomingAptTextArea;

    
    List<Treatment> treatmentTableContents;
    TreatmentTableModel treatTableModel;
 
    AppointmentDataSource appointmentDataSource;

    
    public TabBarsPagePanel(Consumer<String> navigator, JPanel profile, JPanel doctors) {
        initComponents();
        blah(navigator);
        setupComponents(navigator, profile, doctors);
        initTreatments();
        initAppointments();
    }
    
    private void blah(Consumer<String> navigator) {
        
        btnAppointments = new JButton("Appointments");
        btnTreatments = new JButton("Treatments");
        btnDoctors = new JButton("Doctors");
        btnProfile = new JButton("Profile");
        
        btnAppointments.addActionListener(e -> navigator.accept("appointments"));
        btnTreatments.addActionListener(e -> navigator.accept("treatments"));
        btnDoctors.addActionListener(e -> navigator.accept("doctors"));
        btnProfile.addActionListener(e -> navigator.accept("profile"));
    }
    
    private void initTreatments() {
        setTreatments();
        treatTableModel = new TreatmentTableModel(treatmentTableContents);
        jTableTreatments.setModel(treatTableModel);
    }

    private void setTreatments() {
        treatmentTableContents = new ArrayList<Treatment>();
        treatmentTableContents.add(new Medication(0, 0, "Amoxicillin", "Take with food", "antibiotic", "every 8 hours", "12mg", 20));
        treatmentTableContents.add(new Treatment(0, 0, "Blood Pressure measurement", "", "Monitor blood pressure", "once a day"));
    }

    private void editSelectedTreatment(int row) {
        System.out.println("Whee! Made it to editTreatment!");
        Treatment origTreatment = treatmentTableContents.get(row);
 
        // getWindowAncestor returns the parent window; cast to Frame for the dialog owner
        java.awt.Frame parentFrame = (java.awt.Frame) SwingUtilities.getWindowAncestor(this);
        EditTreatment dialog = new EditTreatment(parentFrame, true, origTreatment, false);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            Treatment updatedTreatment = dialog.getTreatment();
            treatTableModel.updateTreatment(row, updatedTreatment);
        } else if (dialog.isDelete()) {
            treatTableModel.deleteTreatment(row);
        }
    }

    private void setupComponents(Consumer<String> navigator, javax.swing.JPanel profile, javax.swing.JPanel doctors) {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        appointmentMain = new javax.swing.JPanel();
        editAppointmentButton = new javax.swing.JButton();
        appointmentScrollBar1 = new javax.swing.JScrollBar();
        appointmentSearch = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        deleteAppointmentButton = new javax.swing.JButton();
        newAppointmnetButton = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        appointmentsTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        upcomingAptTextArea = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableTreatments = new javax.swing.JTable();
        jScrollBar2 = new javax.swing.JScrollBar();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton7 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        jScrollBar3 = new javax.swing.JScrollBar();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
 
        jPanel1.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 30));
        jPanel1.setVerifyInputWhenFocusTarget(false);
 
        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 16));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("HEALTH REMINDER");
 
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
 
        jTabbedPane4.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jTabbedPane4.setForeground(new java.awt.Color(0, 102, 255));
        jTabbedPane4.setPreferredSize(new java.awt.Dimension(800, 470));
 
        appointmentMain.setBackground(new java.awt.Color(255, 255, 255));
        appointmentMain.setPreferredSize(new java.awt.Dimension(800, 470));
 
        editAppointmentButton.setForeground(new java.awt.Color(102, 102, 102));
        editAppointmentButton.setText("Edit Appointment");
        editAppointmentButton.setMaximumSize(new java.awt.Dimension(140, 25));
        editAppointmentButton.addActionListener(e -> navigator.accept("edit appointment"));
 
        appointmentSearch.setForeground(new java.awt.Color(102, 102, 102));
 
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Search");
 
        deleteAppointmentButton.setForeground(new java.awt.Color(102, 102, 102));
        deleteAppointmentButton.setText("Delete Appointment");
        deleteAppointmentButton.addActionListener(this::deleteAppointmentButtonActionPerformed);
 
        newAppointmnetButton.setForeground(new java.awt.Color(102, 102, 102));
        newAppointmnetButton.setText("New Appointment");
        newAppointmnetButton.setMaximumSize(new java.awt.Dimension(140, 25));
        newAppointmnetButton.setPreferredSize(new java.awt.Dimension(140, 25));
        newAppointmnetButton.addActionListener(e -> navigator.accept("add appointment"));
 
        jScrollPane8.setForeground(new java.awt.Color(102, 102, 102));
 
        appointmentsTable.setForeground(new java.awt.Color(102, 102, 102));
        appointmentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {
                {null, null, null, null}
            },
            new String[] {
                "Date", "Time", "Doctor", "Address"
            }
        ) {
            boolean[] canEdit = new boolean[]{false, false, false, false};
 
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        appointmentsTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane8.setViewportView(appointmentsTable);
 
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Upcoming Appointment");
 
        upcomingAptTextArea.setColumns(20);
        upcomingAptTextArea.setRows(5);
        jScrollPane9.setViewportView(upcomingAptTextArea);
 
        javax.swing.GroupLayout appointmentMainLayout = new javax.swing.GroupLayout(appointmentMain);
        appointmentMain.setLayout(appointmentMainLayout);
        appointmentMainLayout.setHorizontalGroup(
            appointmentMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appointmentMainLayout.createSequentialGroup()
                .addGroup(appointmentMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(appointmentMainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(appointmentMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(appointmentMainLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(newAppointmnetButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(130, 130, 130)
                                .addComponent(editAppointmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(130, 130, 130)
                                .addComponent(deleteAppointmentButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, appointmentMainLayout.createSequentialGroup()
                                .addGroup(appointmentMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(appointmentMainLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(appointmentSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel3))
                                    .addComponent(jScrollPane8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(appointmentScrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(appointmentMainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane9)))
                .addContainerGap())
            .addGroup(appointmentMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        appointmentMainLayout.setVerticalGroup(
            appointmentMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appointmentMainLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(appointmentMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appointmentSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(appointmentMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(appointmentMainLayout.createSequentialGroup()
                        .addGap(0, 19, Short.MAX_VALUE)
                        .addComponent(appointmentScrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                .addGap(16, 16, 16)
                .addGroup(appointmentMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newAppointmnetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editAppointmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteAppointmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );
 
        jTabbedPane4.addTab("Appointments", appointmentMain);
 
        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
 
        jTableTreatments.setForeground(new java.awt.Color(102, 102, 102));
        jTableTreatments.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{}
        ));
        jTableTreatments.setColumnSelectionAllowed(true);
        jTableTreatments.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTableTreatments.setShowGrid(true);
        jTableTreatments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTreatmentsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableTreatments);
        jTableTreatments.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
 
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Search");
 
        jScrollPane4.setViewportView(jTextPane1);
 
        jButton7.setForeground(new java.awt.Color(102, 102, 102));
        jButton7.setText("New Treatment");
        jButton7.setMaximumSize(new java.awt.Dimension(140, 25));
 
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(436, 436, 436)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(0, 52, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
                .addGap(64, 64, 64))
        );
 
        jTabbedPane4.addTab("Treatments", jPanel3);
 
        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
 
        jTable3.setForeground(new java.awt.Color(102, 102, 102));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{
                {"1234", "Jessi", "Dentist", "111-111-1111", "abc@abc.com", "44 Red Dr."},
                {"5666", "Jack", "Ped", "222-222-2222", "adg@adds.com", "356 Hayward"},
                {"333", "Timothy", "Family Doc", "333-333-3333", "tim@daf.com", "123 Vegas Ave., Militas, CA, 95035"},
                {null, null, null, null, null, null}
            },
            new String[]{"ID", "Name", "Speciality", "Phone", "Email", "Address"}
        ));
        jTable3.setColumnSelectionAllowed(true);
        jTable3.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane6.setViewportView(jTable3);
        jTable3.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setResizable(false);
        }
 
        jScrollPane7.setViewportView(jTextPane2);
 
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Search");
 
        jButton10.setForeground(new java.awt.Color(102, 102, 102));
        jButton10.setText("New Doctor");
        jButton10.setMaximumSize(new java.awt.Dimension(140, 25));
        jButton10.addActionListener(this::jButton10ActionPerformed);
 
        jButton11.setForeground(new java.awt.Color(102, 102, 102));
        jButton11.setText("Edit Doctor");
        jButton11.setPreferredSize(new java.awt.Dimension(140, 25));
        jButton11.addActionListener(this::jButton11ActionPerformed);
 
        jButton12.setForeground(new java.awt.Color(102, 102, 102));
        jButton12.setText("Delete Doctor");
        jButton12.setPreferredSize(new java.awt.Dimension(140, 25));
 
        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
 
        jTabbedPane4.addTab("Doctors", doctors);
        jTabbedPane4.addTab("Profile", profile);
 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            )
        );     
    }

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
 
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
 
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
 
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
 
    private void jTableTreatmentsMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            int row = jTableTreatments.getSelectedRow();
            if (row != -1) {
                editSelectedTreatment(row);
            }
        }
    }

    private void initAppointments() {
        appointmentDataSource = new AppointmentDataSource();
        refreshAptsTable();
        refreshUpcomingAptTextArea();
    }
 
    //private void newAppointmnetButtonActionPerformed(java.awt.event.ActionEvent evt) {
    //    AddAppointment add = new AddAppointment(
    //        (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
    //        appointmentDataSource
    //    );
    //    add.setVisible(true);
    //}
 
    void refreshUpcomingAptTextArea() {
        upcomingAptTextArea.setText(appointmentDataSource.getUpcomingApt().toString());
    }
 
    void refreshAptsTable() {
        DefaultTableModel model = (DefaultTableModel) appointmentsTable.getModel();
        model.setRowCount(0);
        for (Appointment a : appointmentDataSource.getAppointments()) {
            model.addRow(new Object[]{
                a.getNiceDateString(),
                a.getTime(),
                a.getDoctor().getName(),
                a.getDoctor().getAddress()
            });
        }
    }
 
    public void refreshPage() {
        this.refreshAptsTable();
        this.refreshUpcomingAptTextArea();
    }
 
    private void deleteAppointmentButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int selectedRow = appointmentsTable.getSelectedRow();
            if (selectedRow != -1) {
                int d = JOptionPane.showConfirmDialog(
                    this,
                    "Do you really want to delete this appointment?",
                    "Delete",
                    JOptionPane.YES_NO_OPTION);
                if (d == JOptionPane.YES_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) appointmentsTable.getModel();
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(this, "Appointment deleted.");
                }
                // If NO, just do nothing — no need to recreate the whole page
            } else {
                JOptionPane.showConfirmDialog(
                    this, "Please select a row.", "Warning", JOptionPane.CLOSED_OPTION);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error!");
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
