/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthtracker;

/**Time
 * -------------------
 * -hour:int
 * -minute:int
 * -period:String
 * -------------------
 * Time()
 * Time(hour:int, minute:int)
 * +setHour(h:int)
 * +getHour():int
 * +setMinute(m:int)
 * +getMinute():int
 * +toString():String
 * 
 
 */
public class Time {
    private int hour;
    private int minute;
    
    Time(){
        hour = 0;
        minute = 0;
    }
    Time(int h, int m){
        setHour(h);
        setMinute(m);
    }
    
    public void setHour(int h){
        if (h < 0 || h > 24) {
            throw new IllegalArgumentException("Please input right hour frame: 0-24");
        } else hour = h;
    }
    
    public int getHour(){
        return hour;
    }
    public void setMinute(int m){
        if (m < 0 || m > 60){
            throw new IllegalArgumentException("Please input right minute frame: 0-60");
        } else minute = m;
    }
    
    public int getMinute(){
        return minute;
    }
    
    @Override
    public String toString(){
        return String.format("%d : %d", getHour(), getMinute());
    }
}
