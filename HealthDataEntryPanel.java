import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HealthDataEntryPanel {
    private JPanel panel;
    private JTextField weightField, heightField, stepsField, heartRateField, dateField;
    private JButton submitButton, backButton;
    private HealthMonitoringAppGUI mainApp;

    public HealthDataEntryPanel(HealthMonitoringAppGUI app) {
        mainApp = app;
        panel = new JPanel(new GridLayout(6, 2));

        panel.add(new JLabel("Weight (kg):"));
        weightField = new JTextField(10);
        panel.add(weightField);

        panel.add(new JLabel("Height (cm):"));
        heightField = new JTextField(10);
        panel.add(heightField);

        panel.add(new JLabel("Steps today:"));
        stepsField = new JTextField(10);
        panel.add(stepsField);

        panel.add(new JLabel("Heart Rate (bpm):"));
        heartRateField = new JTextField(10);
        panel.add(heartRateField);

        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField(10);
        panel.add(dateField);

        submitButton = new JButton("Submit");
        panel.add(submitButton);
        submitButton.addActionListener(e -> submitHealthData());

        backButton = new JButton("Back to Menu");
        panel.add(backButton);
        backButton.addActionListener(e -> mainApp.switchPanel("MainMenu"));
    }

    private void submitHealthData() {
        // Logic to process and save health data
        JOptionPane.showMessageDialog(panel, "Health Data Submitted");
    }

    public JPanel getPanel() {
        return panel;
    }
}
