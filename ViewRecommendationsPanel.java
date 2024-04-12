import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ViewRecommendationsPanel {
    private JPanel panel;
    private JTextArea recommendationsArea;
    private JButton backButton;
    private HealthMonitoringAppGUI mainApp;

    public ViewRecommendationsPanel(HealthMonitoringAppGUI app) {
        mainApp = app;
        panel = new JPanel(new BorderLayout());

        recommendationsArea = new JTextArea(10, 30);
        recommendationsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(recommendationsArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        backButton = new JButton("Back to Menu");
        panel.add(backButton, BorderLayout.SOUTH);
        backButton.addActionListener(e -> mainApp.switchPanel("MainMenu"));

        loadRecommendations(); // Load recommendations from the backend
    }

    private void loadRecommendations() {
        // Placeholder: Load recommendations from backend and display them
        recommendationsArea.setText("Here are your recommendations...");
    }

    public JPanel getPanel() {
        return panel;
    }
}
