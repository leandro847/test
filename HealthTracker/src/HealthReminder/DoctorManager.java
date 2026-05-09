package HealthReminder;

import java.util.ArrayList;
import java.util.List;

public class DoctorManager {
    private List<Doctor> doctorList;

    public DoctorManager(){
        doctorList = new ArrayList<>();

        doctorList.add( new Doctor(1,"James Whitfield","Cardiologist","(408)-555-0182","j.whitfield@gmail.com","142 Elm Street, San Jose"));
        doctorList.add( new Doctor(2,"Maria Santos","Pediatrician","(408)-555-0247","m.santos@gmail.com","88 Maple Avenue, San Jose"));
        doctorList.add( new Doctor(3,"Kevin Nguyen","Neurologist","(408)-555-0391","k.nguyen@gmail.com","210 Oak Boulevard, San Jose"));
        doctorList.add( new Doctor(4,"Rachel Thompson","Dermatologist","(408)-555-0164","r.thompson@gmail.com","55 Almaden Avenue, San Jose"));
        doctorList.add( new Doctor(5,"Samuel Okafor","Orthopedic Surgeon","(408)-555-0278","s.okafor@gmail.com","330 Blossom Hill road, San Jose"));
        doctorList.add( new Doctor(6,"Linda Chen","Psychiatrist","(408)-555-0439","l.chen@gmail.com","77 Santa Clara Street, San Jose"));
        doctorList.add( new Doctor(7,"Carlos Rivera","Oncologist","(408)-555-0316","c.rivera@gmail.com","190 Saratoga Avenue, San Jose"));
        doctorList.add( new Doctor(8,"Angela Brooks","Gynecologist","(408)-555-0153","a.brooks@gmail.com","402 Meridian Avenue, San Jose"));
        doctorList.add( new Doctor(9,"Patrick Donovan","Gastroenterologist","(408)-555-0284","p.donovan@gmail.com","560 Curtner Avenue, San Jose"));
        doctorList.add( new Doctor(10,"Priya Patel","Endocrinologist","(408)-555-0367","p.patel@gmail.com","215 Bascom Avenue, San Jose"));
        doctorList.add( new Doctor(11,"Marcus Johnson","Emergency Medicine","(408)-555-0491","m.johnson@gmail.com","88 Tully Road, San Jose"));
        doctorList.add( new Doctor(12,"Susan Yamamoto","Ophthalmologist","(408)-555-0228","s.yamamotogmail.com","310 Camden Avenue, San Jose"));
    }
    public void addDoctor(Doctor doctor){
        doctorList.add(doctor);
    }
    public void removeDoctor(int id){
        doctorList.removeIf(d -> d.getDoctorID() == id);
    }
    public Doctor getDoctor( int id){
        for ( Doctor d: doctorList){
            if ( d.getDoctorID() == id){
                return d;
            }
        }
        return null;
    }
    public List<Doctor> getDoctorList(){
        return doctorList;
    }
}
