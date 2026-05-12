package healthtracker;
import HealthReminder.*;

public class User {
    public int userID;
    public String name;
    public String email;
    public DoctorManager doctorManager;

    public User(String name, String email, String pass){
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.doctorManager = new DoctorManager();
    }
    public DoctorManager getDoctorManager(){
        return doctorManager;
    }
}
