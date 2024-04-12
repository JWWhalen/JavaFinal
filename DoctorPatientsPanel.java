import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DoctorPatientsPanel {
    private JPanel panel;
    private JList<String> patientList;
    private JButton viewHealthDataButton, backButton;
    private HealthMonitoringAppGUI mainApp;

    public DoctorPatientsPanel(HealthMonitoringAppGUI app) {
        mainApp = app;
        panel = new JPanel(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        patientList = new JList<>(listModel);
        panel.add(new JScrollPane(patientList), BorderLayout.CENTER);

        viewHealthDataButton = new JButton("View Selected Patient's Health Data");
        panel.add(viewHealthDataButton, BorderLayout.SOUTH);
        viewHealthDataButton.addActionListener(e -> viewPatientHealthData());

        backButton = new JButton("Back to Menu");
        panel.add(backButton, BorderLayout.NORTH);
        backButton.addActionListener(e -> mainApp.switchPanel("MainMenu"));

        loadPatients(listModel);  // Load patient list from the backend
    }

    /**
     * a description of the entire Java function.
     *
     * @param  listModel	description of parameter
     */
    private void loadPatients(DefaultListModel<String> listModel) {
        // Example patients, replace with real data retrieval
        listModel.addElement("Patient 1 - ID: 123");
        listModel.addElement("Patient 2 - ID: 456");
    }

    /**
     * view the health data of the selected patient.
     *
     * @param  paramName	description of parameter
     * @return         	description of return value
     */
    private void viewPatientHealthData() {
        String selectedPatient = patientList.getSelectedValue();
        if (selectedPatient != null) {
            mainApp.switchPanel("PatientHealthData");  // Assuming there is a Panel for Patient Health Data
        } else {
            JOptionPane.showMessageDialog(panel, "Please select a patient from the list.");
        }
    }

    /**
     * retrieves the JPanel object.
     *
     * @return         	the JPanel object
     */
    public JPanel getPanel() {
        return panel;
    }
}
