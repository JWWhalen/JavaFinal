import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PatientHealthDataPanel {
    private JPanel panel;
    private JTextArea healthDataArea;
    private JButton backButton;
    private HealthMonitoringAppGUI mainApp;

    public PatientHealthDataPanel(HealthMonitoringAppGUI app) {
        mainApp = app;
        panel = new JPanel(new BorderLayout());

        healthDataArea = new JTextArea(15, 30);
        healthDataArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(healthDataArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        backButton = new JButton("Back to Patients");
        panel.add(backButton, BorderLayout.SOUTH);
        backButton.addActionListener(e -> mainApp.switchPanel("DoctorPatients"));

        loadHealthData(); // loading health data from the backend
    }

    /**
     * a description of the entire Java function.
     *
     */
    private void loadHealthData() {
        // placeholder: Load health data here
        healthDataArea.setText("Patient's health data...");
    }

    public JPanel getPanel() {
        return panel;
    }
}
