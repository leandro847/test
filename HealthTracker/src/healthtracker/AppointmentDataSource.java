/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthtracker;

/**
 *
 * @author tamnguyen
 */

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import HealthReminder.*;

public class AppointmentDataSource {
    
    private ArrayList<Appointment> appointmentList;
    private boolean inserted = false;
    
    public AppointmentDataSource() {
        appointmentList = loadAppointments();
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
        
        sortApts(apts);
        return apts;

    }
    
    private void sortApts(ArrayList<Appointment> a){
        a.sort(Comparator.comparing(Appointment::getDate));
    }
    
    public ArrayList<Appointment> getAppointments(){
        return appointmentList;
    }
    
    public Appointment getAptAtIndex(int index){
        return appointmentList.get(index);
    }
    
    public Appointment getUpcomingApt(){
        return appointmentList.get(0);
    }
    
    public boolean isEmpty(){
        return appointmentList.isEmpty();
    }
    
    public ArrayList<Appointment> add(Appointment apt){
        appointmentList.add(apt);
        sortApts(appointmentList);
        return appointmentList;
    }
    
    public void update(int index, Appointment a){
        appointmentList.set(index, a);
        sortApts(appointmentList);    
    }
            
    public ArrayList<Appointment> delete(int index){
        appointmentList.remove(index);
        return appointmentList;
    }
    
}
