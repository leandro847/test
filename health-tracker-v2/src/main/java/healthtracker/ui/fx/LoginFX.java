package healthtracker.ui.fx;

import healthtracker.auth.AuthService;
import healthtracker.auth.User;
import healthtracker.gui.TabBarsPage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginFX extends Application {

    @Override
    public void start(Stage stage) {
        TextField username = new TextField();
        PasswordField password = new PasswordField();
        Label result = new Label();

        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> {
            User user = AuthService.login(username.getText(), password.getText());
            if (user != null) {
                result.setText("Welcome " + user.getUsername());
                // Close JavaFX stage and open Swing dashboard
                stage.close();
                TabBarsPage dashboard = new TabBarsPage(user);
                dashboard.setVisible(true);
            } else {
                result.setText("Login failed");
            }
        });

        GridPane grid = new GridPane();
        grid.addRow(0, new Label("Username"), username);
        grid.addRow(1, new Label("Password"), password);
        grid.addRow(2, loginBtn, result);

        stage.setScene(new Scene(grid, 300, 200));
        stage.setTitle("Health Tracker Login (JavaFX)");
        stage.show();
    }
}
