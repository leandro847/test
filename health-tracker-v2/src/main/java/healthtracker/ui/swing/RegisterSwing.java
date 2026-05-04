package healthtracker.ui.swing;

import healthtracker.auth.AuthService;

import javax.swing.*;

public class RegisterSwing {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Health Tracker Register (Swing)");
        JTextField user = new JTextField(10);
        JPasswordField pass = new JPasswordField(10);
        JLabel res = new JLabel();

        JButton register = new JButton("Register");
        register.addActionListener(e -> {
            if (AuthService.userExists(user.getText()))
                res.setText("User already exists");
            else
                res.setText("Registration denied (JSON is read-only)");
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Username"));
        panel.add(user);
        panel.add(new JLabel("Password"));
        panel.add(pass);
        panel.add(register);
        panel.add(res);

        frame.add(panel);
        frame.setSize(350, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
