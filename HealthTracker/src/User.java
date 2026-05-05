package HealthReminder;

public class User {
    private int userID;
    private String name;
    private String email;
    private DoctorManager doctorManager;

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
