import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel {
    private JPanel panel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;
    private HealthMonitoringAppGUI mainApp;

    public LoginPanel(HealthMonitoringAppGUI app) {
        mainApp = app;
        panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("Email:"));
        emailField = new JTextField(20);
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        panel.add(loginButton);
        panel.add(registerButton);

        loginButton.addActionListener(e -> performLogin());
        registerButton.addActionListener(e -> mainApp.switchPanel("Register"));
    }

    private void performLogin() {
        String email = emailField.getText();
        char[] password = passwordField.getPassword();  // Using JPasswordField here.
        if (email == null || password.length == 0) {
            JOptionPane.showMessageDialog(panel, "Email or password cannot be empty.");
            return;
        }
        
        if (UserDao.verifyPassword(email, new String(password))) {
            JOptionPane.showMessageDialog(panel, "Login Successful");
            mainApp.switchPanel("MainMenu");  // Assuming there is a MainMenu panel
        } else {
            JOptionPane.showMessageDialog(panel, "Login Failed. Please try again.");
        }
    }
    

    public JPanel getPanel() {
        return panel;
    }
}
