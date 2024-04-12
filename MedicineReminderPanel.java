import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MedicineReminderPanel {
    private JPanel panel;
    private JTextField medicineNameField, dosageField, scheduleField, startDateField, endDateField;
    private JButton setReminderButton, backButton;
    private HealthMonitoringAppGUI mainApp;

    public MedicineReminderPanel(HealthMonitoringAppGUI app) {
        mainApp = app;
        panel = new JPanel(new GridLayout(6, 2));

        panel.add(new JLabel("Medicine Name:"));
        medicineNameField = new JTextField(20);
        panel.add(medicineNameField);

        panel.add(new JLabel("Dosage:"));
        dosageField = new JTextField(20);
        panel.add(dosageField);

        panel.add(new JLabel("Schedule (e.g., 'every 8 hours'):"));
        scheduleField = new JTextField(20);
        panel.add(scheduleField);

        panel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        startDateField = new JTextField(10);
        panel.add(startDateField);

        panel.add(new JLabel("End Date (YYYY-MM-DD):"));
        endDateField = new JTextField(10);
        panel.add(endDateField);

        setReminderButton = new JButton("Set Reminder");
        panel.add(setReminderButton);
        setReminderButton.addActionListener(e -> setMedicineReminder());

        backButton = new JButton("Back to Menu");
        panel.add(backButton);
        backButton.addActionListener(e -> mainApp.switchPanel("MainMenu"));
    }

    private void setMedicineReminder() {
        // Logic to process and save medicine reminder
        JOptionPane.showMessageDialog(panel, "Medicine Reminder Set");
    }

    public JPanel getPanel() {
        return panel;
    }
}
