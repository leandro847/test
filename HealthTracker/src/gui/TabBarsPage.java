/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import HealthReminder.Doctor;
import HealthReminder.DoctorManager;
import java.util.List;
import java.util.ArrayList;
import healthtracker.*;
import java.awt.HeadlessException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.*;
import java.util.function.Consumer;
import java.awt.CardLayout;



/**
 *
 * @author Leandropc
 */
public class TabBarsPage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TabBarsPage.class.getName());
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton addTreatmentButton;
    private javax.swing.JPanel appointmentMain;
    private javax.swing.JTextField appointmentSearch;
    private javax.swing.JTable appointmentsTable;
    private javax.swing.JButton delTreatmentButton;
    private javax.swing.JButton deleteAppointmentButton;
    private javax.swing.JButton editAppointmentButton;
    private javax.swing.JButton editTreatmentButton;
    private javax.swing.JButton newDoc;
    private javax.swing.JButton editDoc;
    private javax.swing.JButton deleteDoc;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollBar jScrollBar2;
    private javax.swing.JScrollBar jScrollBar3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane docTab;
    private javax.swing.JTable docTable;
    private javax.swing.JTable jTableTreatments;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JButton newAppointmnetButton;
    private javax.swing.JTextArea upcomingAptTextArea;
    // End of variables declaration                   
    
    private JPanel mainPanel, mail;
    private java.awt.Container originalContentPane;
    private String email = "";

    
    List<Treatment> treatmentTableContents;
    TreatmentTableModel treatTableModel;
 
    AppointmentDataSource appointmentDataSource;
    DoctorManager docList;

    /**
     * Creates new form TabBarsPageFrame
     */
    public TabBarsPage() {
        mainPanel = new JPanel(new CardLayout());
        initComponents();
        setupComponents();
        initTreatments();
        initAppointments();
        initDocs();     
        originalContentPane = getContentPane();
        this.setTitle("Welcome " + email);

    }
    
    public TabBarsPage(String email){
        this();
        this.email = email;
        this.setTitle("Welcome " + email); //janky to call this again. no parameter constructor shouldn't be our default
    }

    
    void refreshUpcomingAptTextArea(){
        if (appointmentDataSource.isEmpty()){
            upcomingAptTextArea.setText("There is no upcoming appointment.");
        } else
            upcomingAptTextArea.setText(appointmentDataSource.getUpcomingApt().toString());
    }
    
    void refreshAptsTable(){
        DefaultTableModel model = (DefaultTableModel) appointmentsTable.getModel();
        model.setRowCount(0);
        for(Appointment a : appointmentDataSource.getAppointments()){
            model.addRow(new Object[]{
                a.getNiceDateString(),
                a.getTime(),
                a.getDoctor().getName(),
                a.getDoctor().getAddress()
            });
        }
    }
    
    void refreshDocsTable(){
        DefaultTableModel model = (DefaultTableModel) docTable.getModel();
        model.setRowCount(0);
        for(Doctor d : docList.getDoctorList()){
            model.addRow(new Object[]{
                d.getStringDocID(),
                d.getName(),
                d.getSpecialty(),
                d.getPhone(),
                d.getEmail(),
                d.getAddress(),
            });
        }
    }
    
    public void refreshPage(){
        this.refreshAptsTable();
        this.refreshUpcomingAptTextArea();
    }
    
    public int getSelectedIndex(){
        return appointmentsTable.getSelectedRow();
    }
    
    public int getSelectedIndexDocs(){
        return docTable.getSelectedRow();
    }
    
    private void initTreatments() {
        setTreatments(); 
        treatTableModel = new TreatmentTableModel(treatmentTableContents);
        jTableTreatments.setModel(treatTableModel);
        //System.out.println("treatment count = " + jTableTreatments.getRowCount());

    }
       
    /*
    Fill the treatments table. Ideally this would be from a database, but that's 
    for a future time.
    */
    private void setTreatments(){
        treatmentTableContents = Treatment.getData();
//        treatmentTableContents = new ArrayList<Treatment>();
//        treatmentTableContents.add(new Medication(0, 0, "Amoxicillin", "Take with food", "antibiotic", "every 8 hours", "12mg", 20));
//        treatmentTableContents.add(new Treatment(0, 0, "Blood Pressure measurement", "", "Monitor blood pressure", "once a day"));
    }

    /*
    Call the EditTreatment dialog box, handle the results
    */
    private void editSelectedTreatment(int row){
        //System.out.println("Whee! Made it to editTreatment!");
        Treatment origTreatment = treatmentTableContents.get(row);
        
        EditTreatment dialog = new EditTreatment(this, true, origTreatment, false);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        if(dialog.isConfirmed()){
            Treatment updatedTreatment = dialog.getTreatment();
            treatTableModel.updateTreatment(row, updatedTreatment);
        } else if (dialog.isDelete()){
            treatTableModel.deleteTreatment(row);
        }
    }
    
    private void showCard(String name) {
        if (name.equals("mail")) {
            setContentPane(new MailPanel(this::showCard));
        } else if (name.equals("main")) {
            setContentPane(originalContentPane); // restore everything
        }
        revalidate();
        repaint();
    }
    
    private void setupComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        docTab = new javax.swing.JTabbedPane();
        appointmentMain = new javax.swing.JPanel();
        editAppointmentButton = new javax.swing.JButton();
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
        addTreatmentButton = new javax.swing.JButton();
        editTreatmentButton = new javax.swing.JButton();
        delTreatmentButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        docTable = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        jScrollBar3 = new javax.swing.JScrollBar();
        newDoc = new javax.swing.JButton();
        editDoc = new javax.swing.JButton();
        deleteDoc = new javax.swing.JButton();
        jPanel5 = new ProfilePanel(this::showCard, new User("a", "asd", "pass"));
        jPanel2 = new javax.swing.JPanel();

        mail = new MailPanel(this::showCard);
        mainPanel.add(mail, "mail");
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 30));
        jPanel1.setVerifyInputWhenFocusTarget(false);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
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

        docTab.setBackground(new java.awt.Color(255, 255, 255));
        docTab.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        docTab.setForeground(new java.awt.Color(0, 102, 255));
        docTab.setPreferredSize(new java.awt.Dimension(800, 470));

        appointmentMain.setBackground(new java.awt.Color(255, 255, 255));
        appointmentMain.setPreferredSize(new java.awt.Dimension(800, 470));

        editAppointmentButton.setForeground(new java.awt.Color(102, 102, 102));
        editAppointmentButton.setText("Edit Appointment");
        editAppointmentButton.setMaximumSize(new java.awt.Dimension(140, 25));
        editAppointmentButton.addActionListener(this::editAppointmentButtonActionPerformed);

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
        newAppointmnetButton.addActionListener(this::newAppointmnetButtonActionPerformed);

        jScrollPane8.setForeground(new java.awt.Color(102, 102, 102));

        appointmentsTable.setForeground(new java.awt.Color(102, 102, 102));
        appointmentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Date", "Time", "Doctor", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        appointmentsTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane8.setViewportView(appointmentsTable);

        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Upcoming Appointment");

        upcomingAptTextArea.setEditable(false);
        upcomingAptTextArea.setColumns(20);
        upcomingAptTextArea.setRows(5);
        jScrollPane9.setViewportView(upcomingAptTextArea);

        javax.swing.GroupLayout appointmentMainLayout = new javax.swing.GroupLayout(appointmentMain);
        appointmentMain.setLayout(appointmentMainLayout);
        appointmentMainLayout.setHorizontalGroup(
            appointmentMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, appointmentMainLayout.createSequentialGroup()
                        .addGroup(appointmentMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(appointmentMainLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(appointmentSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3))
                            .addComponent(jScrollPane8))
                        .addGap(16, 16, 16))
                    .addComponent(jScrollPane9)
                    .addGroup(appointmentMainLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addGap(16, 16, 16)
                .addGroup(appointmentMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newAppointmnetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editAppointmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteAppointmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        docTab.addTab("Appointments", appointmentMain);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jTableTreatments.setForeground(new java.awt.Color(102, 102, 102));
        jTableTreatments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
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

        addTreatmentButton.setForeground(new java.awt.Color(102, 102, 102));
        addTreatmentButton.setText("New Treatment");
        addTreatmentButton.setMaximumSize(new java.awt.Dimension(140, 25));
        addTreatmentButton.addActionListener(this::addTreatmentButtonActionPerformed);

        editTreatmentButton.setForeground(new java.awt.Color(102, 102, 102));
        editTreatmentButton.setText("Edit Treatment");
        editTreatmentButton.setMaximumSize(new java.awt.Dimension(140, 25));
        editTreatmentButton.addActionListener(this::editTreatmentButtonActionPerformed);

        delTreatmentButton.setForeground(new java.awt.Color(102, 102, 102));
        delTreatmentButton.setText("Delete Treatment");
        delTreatmentButton.setMaximumSize(new java.awt.Dimension(140, 25));
        delTreatmentButton.addActionListener(this::delTreatmentButtonActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(addTreatmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(136, 136, 136)
                        .addComponent(editTreatmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                        .addComponent(delTreatmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)))
                .addComponent(jScrollBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                        .addGap(64, 64, 64))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addTreatmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editTreatmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(delTreatmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        docTab.addTab("Treatments", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        docTable.setForeground(new java.awt.Color(102, 102, 102));
        docTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1234", "Jessi", "Dentist", "111-111-1111", "abc@abc.com", "44 Red Dr."},
                {"5666", "Jack", "Ped", "222-222-2222", "adg@adds.com", "356 Hayward"},
                {"333", "Timothy", "Family Doc", "333-333-3333", "tim@daf.com", "123 Vegas Ave., Militas, CA, 95035"},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Speciality", "Phone", "Email", "Address"
            }
        ));
        docTable.setColumnSelectionAllowed(true);
        docTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane6.setViewportView(docTable);
        docTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (docTable.getColumnModel().getColumnCount() > 0) {
            docTable.getColumnModel().getColumn(0).setResizable(false);
        }

        jScrollPane7.setViewportView(jTextPane2);

        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Search");

        newDoc.setForeground(new java.awt.Color(102, 102, 102));
        newDoc.setText("New Doctor");
        newDoc.setMaximumSize(new java.awt.Dimension(140, 25));
        newDoc.addActionListener(this::jButton10ActionPerformed);

        editDoc.setForeground(new java.awt.Color(102, 102, 102));
        editDoc.setText("Edit Doctor");
        editDoc.setPreferredSize(new java.awt.Dimension(140, 25));
        editDoc.addActionListener(this::jButton11ActionPerformed);

        deleteDoc.setForeground(new java.awt.Color(102, 102, 102));
        deleteDoc.setText("Delete Doctor");
        deleteDoc.setPreferredSize(new java.awt.Dimension(140, 25));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addComponent(newDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122)
                .addComponent(editDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addComponent(newDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        docTab.addTab("Doctors", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 787, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        docTab.addTab("Profile", new ProfilePanel(this::showCard, new User("a", "asd", "pass")));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(docTab, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(docTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }
    
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         
    
    private void initAppointments(){
        appointmentDataSource = new AppointmentDataSource();
        refreshAptsTable();
        refreshUpcomingAptTextArea();
    }
    
    private void newAppointmnetButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        AddAppointment add = new AddAppointment(this, appointmentDataSource);
        add.setVisible(true);
    }                                                    
    
    private void deleteAppointmentButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                        

        try {
            int selectedRow = appointmentsTable.getSelectedRow();
            if (selectedRow != -1) {
                int d = JOptionPane.showConfirmDialog(
                    null,
                    "Do you really want to delete this appointment?",
                    "Delete" ,
                    JOptionPane.YES_NO_OPTION);
                if (d == JOptionPane.YES_OPTION){
                    appointmentDataSource.delete(selectedRow);
                    this.refreshPage();
                    this.setVisible(true);
                    JOptionPane.showConfirmDialog(
                        null,
                        "Appointment deleted.",
                        "Delele",
                        JOptionPane.CLOSED_OPTION);
                } else{
                    this.setVisible(true);
                }

            } else {
                JOptionPane.showConfirmDialog(
                    null, "Please select a row.", "Warning", JOptionPane.CLOSED_OPTION);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Error!");
        }

    }                                                       

    private void editAppointmentButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        try{
            int selectedRow = appointmentsTable.getSelectedRow();
            if (selectedRow != -1) {
                EditAppointment edit = new EditAppointment(this, appointmentDataSource, this.getSelectedIndex());
                edit.setDataFields();
                edit.setVisible(true);
            } else
                JOptionPane.showConfirmDialog(
                    null, "Please select a row.", "Warning", JOptionPane.CLOSED_OPTION);
        }catch(HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Error!");
        }    
    }                                                     

    private void jTableTreatmentsMouseClicked(java.awt.event.MouseEvent evt) {                                              
        // TODO add your handling code here:
        if(evt.getClickCount()==2){
            int row = jTableTreatments.getSelectedRow();
            if(row !=-1){
                editSelectedTreatment(row);
            }
        }
    }                                             

    private void delTreatmentButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
        int row = jTableTreatments.getSelectedRow();
        if(row != -1){
            int response = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to delete this treatment?", 
                    "Delete Treatment", JOptionPane.YES_NO_CANCEL_OPTION);
            if(response == JOptionPane.YES_OPTION){
                treatTableModel.deleteTreatment(row);
            }
        }
    }                                                  

    private void editTreatmentButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        // TODO add your handling code here:
        int row = jTableTreatments.getSelectedRow();
        if(row !=-1){
            editSelectedTreatment(row);
        }
    }                                                   

    private void addTreatmentButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
        
        EditTreatment dialog = new EditTreatment(this, true, null, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        if(dialog.isConfirmed()){
            Treatment updatedTreatment = dialog.getTreatment();
            treatTableModel.addTreatment(updatedTreatment);
        } 
    }  

     private void initDocs(){
        docList = new DoctorManager();
        refreshDocsTable();
    }
    
    private void newDocButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        EditDocs add = new EditDocs(this, -1, docList);
        add.setVisible(true);
    }                                                    
    
    public void refreshDocPage(){
        refreshDocsTable();
    }
    
    private void deleteDocButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                        
 
        try {
            int selectedRow = docTable.getSelectedRow();
            if (selectedRow != -1) {
                int d = JOptionPane.showConfirmDialog(
                    null,
                    "Do you really want to delete this doctor?",
                    "Delete" ,
                    JOptionPane.YES_NO_OPTION);
                if (d == JOptionPane.YES_OPTION){
                    docList.removeDoctor(selectedRow);
                    this.refreshDocPage();
                    this.setVisible(true);
                    JOptionPane.showConfirmDialog(
                        null,
                        "Doctor is deleted.",
                        "Delele",
                        JOptionPane.CLOSED_OPTION);
                } else{
                    this.setVisible(true);
                }

            } else {
                JOptionPane.showConfirmDialog(
                    null, "Please select a row.", "Warning", JOptionPane.CLOSED_OPTION);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Error!");
        }

    }                                                       

    private void editDocButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try{
            int selectedRow = appointmentsTable.getSelectedRow();
            if (selectedRow != -1) {
                EditDocs add = new EditDocs(this, this.getSelectedIndexDocs(), docList);
                add.setVisible(true);
            } else
                JOptionPane.showConfirmDialog(
                    null, "Please select a row.", "Warning", JOptionPane.CLOSED_OPTION);
        }catch(HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Error!");
        }    
    }         
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TabBarsPage().setVisible(true));
    }

    // Variables declaration - do not modify                     
    // End of variables declaration                   
}

