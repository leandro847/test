package HealthTrackerApp;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dinhtran
 */

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

public class AppointmentDataSource {
    
    private ArrayList<Appointment> appointmentDataSource;
    private boolean inserted = false;
    
    public AppointmentDataSource() {
        appointmentDataSource = loadAppointments();
    }
    
    private ArrayList<Appointment> loadAppointments() {
        ArrayList<Appointment> apts = new ArrayList<>();
        
        Doctor Tim = new Doctor("Timothy", "123 Vegas Ave., Militas, CA, 95035");
        Appointment apt1 = new Appointment(Tim, LocalDate.of(2026, 6, 7),LocalTime.of(11, 30));
        
        Doctor Ay = new Doctor("Ay", "4747 Chestnut Dr");
        Appointment apt2 = new Appointment(Ay, LocalDate.of(2026, 9, 7), LocalTime.of(13, 00));
        
        Doctor Jessy = new Doctor("Jessy", "439 Vegas Ave.");
        Appointment apt3 = new Appointment(Jessy, LocalDate.of(2026, 7, 4), LocalTime.of(9, 45));
        
        Doctor Henry = new Doctor("Henry", "50 Sky Dr.");
        Appointment apt4 = new Appointment(Henry, LocalDate.of(2026, 11, 1), LocalTime.of(14, 15));
        
        apts.add(apt1);
        apts.add(apt2);
        apts.add(apt3);
        apts.add(apt4);
        
        apts.sort(Comparator.comparing(Appointment::getDate));
        return apts;

    }
    
    public ArrayList<Appointment> getAppointments(){
        return appointmentDataSource;
    }
    
    public Appointment getUpcomingApt(){
        return appointmentDataSource.get(0);
    }
    
    public ArrayList<Appointment> add(Appointment apt){
        if (appointmentDataSource.isEmpty()){
            appointmentDataSource.add(apt);
        } else {
            for (int n = 0; n < appointmentDataSource.size(); n++){
                if (apt.getDate().isBefore(appointmentDataSource.get(n).getDate())){
                    appointmentDataSource.add(n, apt);
                    inserted = true;
                    break;
                } else
                    inserted = false;
            }
            if (inserted)
                appointmentDataSource.add(apt);
        }
        return appointmentDataSource;
    }
    
            
    public ArrayList<Appointment> delete(int index){
        appointmentDataSource.remove(index);
        return appointmentDataSource;
    }
    
}
