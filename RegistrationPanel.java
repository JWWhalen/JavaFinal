import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegistrationPanel {
    private JPanel panel;
    private JTextField firstNameField, lastNameField, emailField;
    private JPasswordField passwordField;
    private JCheckBox isDoctorCheckbox;
    private JButton registerButton, backButton;
    private HealthMonitoringAppGUI mainApp;

    public RegistrationPanel(HealthMonitoringAppGUI app) {
        mainApp = app;
        panel = new JPanel(new GridLayout(7, 2));

        panel.add(new JLabel("First Name:"));
        firstNameField = new JTextField(20);
        panel.add(firstNameField);

        panel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField(20);
        panel.add(lastNameField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField(20);
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        panel.add(passwordField);

        panel.add(new JLabel("Are you a doctor?"));
        isDoctorCheckbox = new JCheckBox();
        panel.add(isDoctorCheckbox);

        registerButton = new JButton("Register");
        backButton = new JButton("Back to Login");
        panel.add(registerButton);
        panel.add(backButton);

        registerButton.addActionListener(e -> performRegistration());
        backButton.addActionListener(e -> mainApp.switchPanel("Login"));
    }

    /**
     * a description of the entire Java function.
     *
     * @param  paramName	description of parameter
     * @return         	description of return value
     */
    private void performRegistration() {
        // extract data from fields
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        boolean isDoctor = isDoctorCheckbox.isSelected();

        // Check if all fields are filled
        if (UserDao.createUser(new User(firstName, lastName, email, password, isDoctor))) {
            JOptionPane.showMessageDialog(panel, "Registration Successful");
            mainApp.switchPanel("Login");
        } else {
            JOptionPane.showMessageDialog(panel, "Registration Failed. Please try again.");
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
