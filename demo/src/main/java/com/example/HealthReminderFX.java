// mvn javafx:run

package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;

import java.util.LinkedHashMap;
import java.util.Map;


public class HealthReminderFX extends Application {
    @Override
    public void start(Stage stage) {
        ProfilePage page = new ProfilePage("Alex Rivera", "alexrivera", "alex@example.com", "555-0192", "Admin", stage);
        stage.setScene(page.getScene());
        stage.setTitle("Profile");
        stage.show();
    }
}

class ProfilePage {

    private final Scene scene; // profile page scene
    private Stage stage; // global window
    private VBox root; // wraper for all objects on screen (navbar, labels, etc)

    private HBox navBar; // wraps buttons
    private VBox userInfo; // wraps labels

    private Map<String, Label> labels = new LinkedHashMap<>();
    private Map<String, Button> buttons = new LinkedHashMap<>();

    // TODO: create an account class and pass it as parameter instead of individual strings
    public ProfilePage(String name, String username, String email, String phone, String role, Stage stage) {
        this.stage = stage;
        buildNavBar();
        buildLabels(name, username, email, phone, role);
        buildUserInfo();
        root = new VBox(navBar, userInfo);
        scene = new Scene(root, 600, 350);
    }


    private void buildLabels(String name, String username, String email, String phone, String role) {
        labels.put("title"    , new Label("Profile Page"));
        labels.put("name"     , new Label("Name:     " + name));
        labels.put("username" , new Label("Username: " + username));
        labels.put("email"    , new Label("Email:    " + email));
        labels.put("phone"    , new Label("Phone:    " + phone));
        labels.put("role"     , new Label("Role:     " + role));
    }


    private void buildUserInfo() {
        userInfo = new VBox(12);
        userInfo.getChildren().addAll(labels.values());
        userInfo.setPadding(new Insets(30));
        userInfo.setAlignment(Pos.TOP_LEFT);
        VBox.setVgrow(userInfo, Priority.ALWAYS);
    }


    private void buildButtons() {
        buttons.put("mail", new Button("Mail Summary"));
        buttons.put("edit", new Button("Edit Profile"));
        buttons.put("logout", new Button("Logout"));

        buttons.get("logout").setOnAction(e -> {
            stage.close();
        });

        // TODO: change so it update account info
        buttons.get("edit").setOnAction(e -> {
            String newUsername = "test";
            labels.get("username").setText("Username: " + newUsername);
        });

        // TODO: change stage to mail scene
        buttons.get("mail").setOnAction(e -> {
            System.out.println("Opening mail summary...");
        });
    }

    /* 
    TODO: create a style class to store all styles so the app looks consistent
        also move baseNavStyle and hoverStyle into the style class
    */
    private void buildNavBar() {
        buildButtons();

        // button style when not hovered
        String baseNavStyle =
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #e0e0e0;" +
            "-fx-font-size: 13px;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 8 18 8 18;";

        // Button style when hovered
        String hoverStyle =
            "-fx-background-color: rgba(255,255,255,0.12);" +
            "-fx-text-fill: #ffffff;" +
            "-fx-font-size: 13px;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 8 18 8 18;" +
            "-fx-background-radius: 4;";

        navBar = new HBox(4);
        navBar.getChildren().addAll(buttons.values());
        navBar.setStyle("-fx-background-color: #2c2c3a;");

        // Set listeners to update buttons when hovered
        for (Button btn : buttons.values()) {
            btn.setStyle(baseNavStyle);
            btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
            btn.setOnMouseExited(e  -> btn.setStyle(baseNavStyle));
        }
    }


    public Scene getScene() {
        return scene;
    }
}
