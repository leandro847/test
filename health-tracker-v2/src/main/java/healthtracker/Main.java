package healthtracker;

import healthtracker.ui.fx.LoginFX;
import healthtracker.ui.swing.LoginSwing;
import javafx.application.Application;

public class Main {

    /*
     * Change this flag to switch UI framework to Swing/JAVAFX.
     */
    private static final boolean USE_JAVAFX = true;

    public static void main(String[] args) {
        if (USE_JAVAFX) {
            Application.launch(LoginFX.class, args);
        } else {
            LoginSwing.main(args);
        }
    }
}
