package HealthReminder;

public class Doctor {
    private int doctorID;
    private String name;
    private String specialty;
    private String phone;
    private String email;
    private String address;

    public Doctor( int doctorID, String name, String specialty, String phone,
                   String email, String address){
        this.doctorID = doctorID;
        this.name = name;
        this.specialty = specialty;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Doctor ( String name, String address){
        this.name = name;
        this.address = address;
    }

    public int getDoctorID(){
        return doctorID;
    }
    public void setDoctorID(int doctorID){
        this.doctorID = doctorID;
    }
    public String getName(){
        return name;
    }
    public void setName( String name){
        this.name = name;
    }
    public String getSpecialty(){
        return specialty;
    }
    public void setSpecialty( String specialty){
        this.specialty = specialty;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone( String phone){
        this.phone = phone;
    }
    public String getEmail() { return email;}
    public void setEmail( String email){ this.email = email;}
    public String getAddress(){ return address;}
    public void setAddress( String address){ this.address = address;}

}
