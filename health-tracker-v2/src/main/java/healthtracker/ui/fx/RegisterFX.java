package healthtracker.ui.fx;

import healthtracker.auth.AuthService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterFX extends Application {
    @Override
    public void start(Stage s) {
        TextField user = new TextField();
        PasswordField pass = new PasswordField();
        Label res = new Label();

        Button register = new Button("Register");
        register.setOnAction(e -> {
            if (AuthService.userExists(user.getText()))
                res.setText("User already exists");
            else
                res.setText("Registration denied (JSON is read‑only)");
        });

        VBox box = new VBox(10, user, pass, register, res);
        s.setScene(new Scene(box, 300, 200));
        s.show();
    }
}
