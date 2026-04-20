/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;

import javafx.geometry.Insets;
import javafx.stage.Stage;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Leandropc
 */
public class ProfilePage {
    private final Scene scene;
    private Stage stage;
    private VBox root;
    private HBox navBar;
    private User account;
    private Label titleLabel = new Label("Profile Page");

    private Map<String, TextField> textFields = new LinkedHashMap<>();
    private Map<String, Button>    buttons    = new LinkedHashMap<>();
    private Map<String, Label>     labels     = new LinkedHashMap<>();

    // makes the textfields invisible so they look like labels
    private static final String DISPLAY_STYLE =
        "-fx-background-color: transparent;" +
        "-fx-border-color: transparent;" +
        "-fx-padding: 4 0 4 0;";

    private static final String EDIT_STYLE = ""; // default JavaFX textfield style


    public ProfilePage(User account, Stage stage) {
        this.stage   = stage;
        this.account = account;
        buildNavBar();
        buildLabels();
        buildLayout();
        toggleEdit(false); // apply style on startup
        scene = new Scene(root, 600, 350);
    }


    private void toggleEdit(boolean editing) {
        // toggle textfields style and editability depending on editing mode
        for (TextField t : textFields.values()) {
            t.setEditable(editing);
            t.setStyle(editing ? EDIT_STYLE : DISPLAY_STYLE);
        }
        
        // toggles button visibility depending on editing mode
        buttons.get("edit")  .setVisible(!editing); buttons.get("edit")  .setManaged(!editing);
        buttons.get("save")  .setVisible( editing); buttons.get("save")  .setManaged( editing);
        buttons.get("cancel").setVisible( editing); buttons.get("cancel").setManaged( editing);
    }


    private void handleSave() {
        // updates info being displayed
        account.name = textFields.get("name").getText().trim();
        account.username = textFields.get("username").getText().trim();
        account.email = textFields.get("email").getText().trim();
        account.phone = textFields.get("phone").getText().trim();
    }


    private void handleCancel() {
        // cancels any changes user made
        textFields.get("name").setText(account.name);
        textFields.get("username").setText(account.username);
        textFields.get("email").setText(account.email);
        textFields.get("phone").setText(account.phone);
    }


    private void buildLabels() {
        labels.put("name",     new Label("Name:"));
        labels.put("username", new Label("Username:"));
        labels.put("email",    new Label("Email:"));
        labels.put("phone",    new Label("Phone:"));

        textFields.put("name",     new TextField(account.name));
        textFields.put("username", new TextField(account.username));
        textFields.put("email",    new TextField(account.email));
        textFields.put("phone",    new TextField(account.phone));
    }


    private void buildLayout() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(12);
        grid.setPadding(new Insets(30, 0, 0, 20));

        ColumnConstraints labelCol = new ColumnConstraints();
        labelCol.setMinWidth(80);
        ColumnConstraints fieldCol = new ColumnConstraints();
        fieldCol.setHgrow(Priority.ALWAYS);

        grid.getColumnConstraints().addAll(labelCol, fieldCol);

        int row = 0;
        for (Label l : labels.values())
            grid.add(l, 0, row++);

        row = 0;
        for (TextField t : textFields.values())
            grid.add(t, 1, row++);

        VBox.setMargin(titleLabel, new Insets(15, 0, 0, 20));
        root = new VBox(navBar, titleLabel, grid);
        root.setFocusTraversable(true);
    }


    private void buildButtons() {
        buttons.put("mail",   new Button("Mail Summary"));
        buttons.put("edit",   new Button("Edit Profile"));
        buttons.put("logout", new Button("Logout"));
        buttons.put("save",   new Button("Save"));
        buttons.put("cancel", new Button("Cancel"));

        buttons.get("edit")  .setOnAction(e -> toggleEdit(true));
        buttons.get("logout").setOnAction(e -> stage.close());

        buttons.get("save")  .setOnAction(e -> {
            handleSave(); 
            toggleEdit(false); 
            root.requestFocus();
        });

        buttons.get("cancel").setOnAction(e -> {
            handleCancel(); 
            toggleEdit(false); 
            root.requestFocus();
        });

        buttons.get("mail").setOnAction(e -> {
            System.out.println("Opening mail summary...");
        });
    }


    private void buildNavBar() {
        buildButtons();

        String baseNavStyle =
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #e0e0e0;" +
            "-fx-font-size: 13px;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 8 18 8 18;";

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
