/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthtracker;

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

public class Appointment {
    private Doctor doctor;
    private LocalDate date;
    private Time time;
    Appointment(Doctor doc, LocalDate d, Time t){
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
    
    public void setTime(Time t){
        time = t;
    }
    
    public Doctor getDoctor(){
        return doctor;
    }
    
    public LocalDate getDate(){
        return date;
    }
    
    public Time getTime(){
        return time;
    }
    
    @Override
    public String toString(){
        return String.format("%s\n%s \n%d/%d/%d \n%d:%d",
                getDoctor().getName(), getDoctor().getAddress(),
                getDate(), getDate().getMonth(), getDate().getYear(),
                getTime().getHour(), getTime().getMinute());
    }
}
