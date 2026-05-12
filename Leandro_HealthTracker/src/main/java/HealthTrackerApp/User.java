package HealthTrackerApp;

public class User {
    public int userID;
    public String name;
    public String email;
    public DoctorManager doctorManager;

    public User(int userID, String name, String email){
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.doctorManager = new DoctorManager();
    }
    public DoctorManager getDoctorManager(){
        return doctorManager;
    }
}
