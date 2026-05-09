package gui;

import healthtracker.AuthManager;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;
/**
 *
 * @author  Jincy
 */
public class FXLoginApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setLeft(createLeftPane());
        root.setCenter(createRightForm(primaryStage));

        Scene scene = new Scene(root, 800, 520);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Health Reminder - JavaFX Login");
        primaryStage.show();
    }

    private VBox createLeftPane() {
        VBox pane = new VBox();
        pane.setPrefWidth(360);
        pane.setStyle("-fx-background-color: #0099FF;");
        pane.setAlignment(Pos.CENTER);

        Label title = new Label("HEALTH REMINDER");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Helvetica Neue", 32));
        pane.getChildren().add(title);
        return pane;
    }

    private VBox createRightForm(Stage stage) {
        VBox form = new VBox(16);
        form.setPadding(new Insets(40));
        form.setAlignment(Pos.TOP_LEFT);

        Label loginLabel = new Label("LOGIN");
        loginLabel.setTextFill(Color.web("#0066FF"));
        loginLabel.setFont(Font.font("Helvetica Neue", 26));

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPrefWidth(300);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(300);

        Label statusLabel = new Label();
        statusLabel.setTextFill(Color.web("#B22222"));

        Button signInButton = new Button("Sign in");
        Button signUpButton = new Button("Sign up");
        signInButton.setPrefWidth(120);
        signUpButton.setPrefWidth(120);

        signInButton.setOnAction(event -> {
            try {
                if (AuthManager.authenticate(emailField.getText().trim(), passwordField.getText())) {
                    statusLabel.setText("Login successful! Opening dashboard...");
                    openTabBar(stage, emailField.getText().trim());
                } else {
                    statusLabel.setText("Invalid email or password.");
                }
            } catch (Exception ex) {
                statusLabel.setText("Error reading credentials: " + ex.getMessage());
            }
        });

        signUpButton.setOnAction(event -> {
            try {
                AuthManager.register(emailField.getText().trim(), passwordField.getText());
                statusLabel.setText("Signup complete. Please sign in.");
            } catch (IllegalArgumentException ex) {
                statusLabel.setText(ex.getMessage());
            } catch (Exception ex) {
                statusLabel.setText("Error saving credentials: " + ex.getMessage());
            }
        });

        HBox actionBox = new HBox(10, signInButton, signUpButton);
        actionBox.setAlignment(Pos.CENTER_LEFT);

        Label prompt = new Label("You don't have account?");
        prompt.setTextFill(Color.web("#666666"));

        form.getChildren().addAll(loginLabel, emailLabel, emailField, passwordLabel, passwordField, actionBox, prompt, statusLabel);
        VBox.setVgrow(emailField, Priority.NEVER);
        return form;
    }

    private void openTabBar(Stage stage, String email) {
        stage.close();
        SwingUtilities.invokeLater(() -> new TabBarsPage(email).setVisible(true));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
