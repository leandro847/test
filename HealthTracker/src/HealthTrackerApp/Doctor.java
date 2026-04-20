/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthtracker;

/**
 *
 * @author dinhtran
 */
public class Doctor {
    private String name;
    private String address;
    Doctor(String name, String address){
        this.name = name;
        this.address = address;
    }
    
    public String getName(){
        return name;
    }
    
    public String getAddress(){
        return address;
    }
}
