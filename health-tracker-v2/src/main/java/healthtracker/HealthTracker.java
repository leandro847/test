/*
 * Health Tracker Application
 * Integrated with authentication system
 */
package healthtracker;

/**UML
 * Calendar
 * -------------------
 * + date: Date
 * + time: Time
 * + appointment: AppointmentArrayList
 * + appointment.next(): Appointment
 * + Calendar
 *      + editAppointment
 *      + Delete Appointment
 *      + Add Appointment
 *
 *
 * 
 * @author dinhtran
 */
public class HealthTracker {

    /**
     * @param args the command line arguments
     * 
     * Delegates to Main.main() to launch the application
     */
    public static void main(String[] args) {
        // Launch the integrated authentication and UI system
        Main.main(args);
    }
    
}
