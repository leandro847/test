package HealthTrackerApp;
/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**Appointment
 * --------------------
 * - doctor: Doctor
 * - date: Date
 * - time: Time
 * --------------------
 * Appointment(doctor:Doctor, date:Date, time:Time)
 * +setDoctor(doc:Doctor)
 * +setDate(d:Date)
 * +setTime(t:Time)
 * +getDoctor():Doctor
 * +getDate():Date
 * +getTime():Time
 * +toString():String
 * 
 *
 * @author Tam
 */

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private Doctor doctor;
    private LocalDate date;
    private LocalTime time;
    public Appointment(Doctor doc, LocalDate d, LocalTime t){
        doctor = doc;
        date = d;
        time = t;
    }
    
    public void setDoctor(Doctor doc){
        doctor = doc;
    }
    
    public void setDate(LocalDate d){
        date = d;
    }
    
    public void setTime(LocalTime t){
        time = t;
    }
    
    public Doctor getDoctor(){
        return doctor;
    }
    
    public LocalDate getDate(){
        return date;
    }
    
    public String getNiceDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return date.format(formatter);
    }
    
    public LocalTime getTime(){
        return time;
    }
    
    @Override
    public String toString(){
        return String.format("Doctor: %s\nAddress: %s\nDate: %d/%d/%d\nTime: %d:%d",
                getDoctor().getName(),
                getDoctor().getAddress(),
                getDate().getMonthValue(), getDate().getDayOfMonth(), getDate().getYear(),
                getTime().getHour(), getTime().getMinute());
    }
}
