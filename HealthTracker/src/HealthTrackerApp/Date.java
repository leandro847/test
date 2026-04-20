/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthtracker;

/**Date
 * -------------------------
 * -day:int
 * -month:int
 * -year:int
 * -dayOfMonthLeapYear: array int
 * -dayOfMonthNonLeapYear: array int
 * -------------------------
 * Date()
 * Date(m:int, d:int, y:int)
 * +isLeapYear(y:int):boolean
 * +getDay(): int
 * +setDay(d:int)
 * +getMonth():int
 * +setMonth(m:int)
 * +getYear(): int
 * +setYear(y:int)
 * +nextDay():void
 * +toString():String
 * 
 *
 */
public class Date {
    private int day;
    private int month;
    private int year;
    private int[] dayOfMonthLeapYear = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int[] dayOfMonthNonLeapYear = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    Date(){
        day = 1;
        month = 1;
        year = 1;
    }
    
    Date(int m, int d, int y){
        setYear(y);
        setMonth(m);
        setDay(d);
    }
    
    boolean isLeapYear(int y){
        if (y % 4 != 0 || (y % 100 == 0 && y % 400 != 0)){
            return false;
        } else if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0)
            return true;
        return false;
    }
    
    public void setDay(int d){
        if (isLeapYear(year) == false && d > 0 && d <= dayOfMonthNonLeapYear[month]) {
            day = d;
        }
        else if (isLeapYear(year) == true && d > 0 && d <= dayOfMonthLeapYear[month]){
            day = d;
        } else throw new IllegalArgumentException("Please input correct day in the month.");
    }
    
    public int getDay(){
        return day;
    }
    
    public void setMonth(int m){
        if (m > 0 && m <= 12) {
            month = m;
        } else throw new IllegalArgumentException("Please input correct month of the year.");
    }
    
    public int getMonth(){
        return month;
    }
    
    public void setYear(int y){
        if (y > 0){
            year = y;
        } else throw new IllegalArgumentException("Please input correct year.");
    }
    
    public int getYear(){
        return year;
    }
    
    public void nextDay(){
        if (month == 2){
            if (isLeapYear(year) == false){
                if (day == dayOfMonthNonLeapYear[2]){
                    setDay(1);
                    setMonth(3);
                } else setDay(day + 1);
            } else {
                if (day == dayOfMonthLeapYear[2]){
                    setDay(1);
                    setMonth(3);
                } else setDay(day + 1);
            }
        } else if (month == 12) {
            if (day == dayOfMonthLeapYear[12]){
                setDay(1);
                setMonth(1);
                setYear(year + 1);
            } else setDay(day + 1);

        } else { 
            if (day == dayOfMonthLeapYear[month]){
                setDay(1);
                setMonth(month + 1);
            } else setDay(day + 1);
        }
    }
    
    @Override
    public String toString(){
        return String.format("%d/%d/%d", month, day, year);
    }
  
}
