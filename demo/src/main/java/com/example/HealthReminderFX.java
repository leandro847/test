// mvn javafx:run

package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;

import java.util.LinkedHashMap;
import java.util.Map;


public class HealthReminderFX extends Application {
    @Override
    public void start(Stage stage) {
        ProfilePage page = new ProfilePage(new User("Alex Rivera", "alexrivera", "alex@example.com", "555-0192"), stage);
        stage.setScene(page.getScene());
        stage.setTitle("Profile");
        stage.show();
    }
}


class User {
    String name; 
    String username; 
    String email;
    String phone;

    public User(String name, String username, String email, String phone) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }
}


class ProfilePage {

    private final Scene scene; // profile page scene
    private Stage stage; // global window
    private VBox root; // wraper for all objects on screen (navbar, labels, etc)
    private User account;

    private HBox navBar; // wraps buttons

    private Map<String, TextField> textFields = new LinkedHashMap<>();
    private Map<String, Button> buttons = new LinkedHashMap<>();
    private Map<String, Label> labels = new LinkedHashMap<>();


    // TODO: create an account class and pass it as parameter instead of individual strings
    public ProfilePage(User account, Stage stage) {
        this.stage = stage;
        this.account = account;
        buildNavBar();
        buildLabels();
        buildUserInfo();
        scene = new Scene(root, 600, 350);
    }


    private void handleSave() {
        // TODO: validate inputs and update user info
        account.name = textFields.get("name").getText().trim();
        account.username = textFields.get("username").getText().trim();
        account.email = textFields.get("email").getText().trim();
        account.phone = textFields.get("phone").getText().trim();
    }


    private void handleCancel() {
        // Reset fields back to current user data
        textFields.get("name").setText(account.name);
        textFields.get("username").setText(account.username);
        textFields.get("email").setText(account.email);
        textFields.get("phone").setText(account.phone);
    }


    private void buildLabels() {
        labels.put("title"    , new Label("Profile Page"));
        labels.put("name"     , new Label("Name:     "));
        labels.put("username" , new Label("Username: "));
        labels.put("email"    , new Label("Email:    "));
        labels.put("phone"    , new Label("Phone:    "));

        textFields.put("name"     , new TextField(account.name));
        textFields.put("username" , new TextField(account.username));
        textFields.put("email"    , new TextField(account.email));
        textFields.put("phone"    , new TextField(account.phone));
    }


    private void buildUserInfo() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(12);
        grid.setPadding(new Insets(30, 0, 0, 20));

        ColumnConstraints labelCol = new ColumnConstraints();
        labelCol.setMinWidth(80);

        ColumnConstraints fieldCol = new ColumnConstraints();
        fieldCol.setHgrow(Priority.ALWAYS); // resizes with window

        grid.getColumnConstraints().addAll(labelCol, fieldCol);

        String[] keys = {"name", "username", "email", "phone"};
        for (int i = 0; i < keys.length; i++) {
            grid.add(labels.get(keys[i]),     0, i);
            grid.add(textFields.get(keys[i]), 1, i);
        }

        root = new VBox(navBar, labels.get("title"), grid);
        VBox.setMargin(labels.get("title"), new Insets(15, 0, 0, 20)); // margin for title
    }


    private void buildButtons() {
        buttons.put("mail", new Button("Mail Summary"));
        buttons.put("edit", new Button("Edit Profile"));
        buttons.put("logout", new Button("Logout"));
        buttons.put("save", new Button("Save"));
        buttons.put("cancel", new Button("Cancel"));

        buttons.get("save").setOnAction(e -> handleSave());
        buttons.get("cancel").setOnAction(e -> handleCancel());

        buttons.get("logout").setOnAction(e -> {
            stage.close();
        });

        // TODO: change so it update account info
        buttons.get("edit").setOnAction(e -> {
            String newUsername = "test";
            textFields.get("username").setText("Username: " + newUsername);
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
