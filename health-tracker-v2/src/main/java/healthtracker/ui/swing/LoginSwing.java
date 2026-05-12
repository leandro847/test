package healthtracker.ui.swing;

import healthtracker.auth.AuthService;
import healthtracker.auth.User;
import healthtracker.gui.TabBarsPage;

import javax.swing.*;

public class LoginSwing {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Health Tracker Login (Swing)");
        JTextField username = new JTextField(10);
        JPasswordField password = new JPasswordField(10);
        JLabel result = new JLabel();

        JButton login = new JButton("Login");
        login.addActionListener(e -> {
            User user = AuthService.login(
                    username.getText(),
                    new String(password.getPassword())
            );
            if (user != null) {
                result.setText("Welcome " + user.getUsername());
                // Close login frame and open main dashboard
                frame.dispose();
                TabBarsPage dashboard = new TabBarsPage(user);
                dashboard.setVisible(true);
            } else {
                result.setText("Login Failed");
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("User"));
        panel.add(username);
        panel.add(new JLabel("Pass"));
        panel.add(password);
        panel.add(login);
        panel.add(result);

        frame.add(panel);
        frame.setSize(350, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
