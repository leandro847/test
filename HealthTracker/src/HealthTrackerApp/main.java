/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Leandropc
 */
public class main extends Application{

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
        this.name     = name;
        this.username = username;
        this.email    = email;
        this.phone    = phone;
    }
}
