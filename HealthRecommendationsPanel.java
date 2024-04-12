import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HealthRecommendationsPanel {
    private JPanel panel;
    private JTextArea recommendationsArea;
    private JTextField newRecommendationField;
    private JButton addRecommendationButton, backButton;
    private HealthMonitoringAppGUI mainApp;

    public HealthRecommendationsPanel(HealthMonitoringAppGUI app) {
        mainApp = app;
        panel = new JPanel(new BorderLayout());

        recommendationsArea = new JTextArea(10, 30);
        recommendationsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(recommendationsArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        newRecommendationField = new JTextField();
        panel.add(newRecommendationField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        addRecommendationButton = new JButton("Add Recommendation");
        buttonPanel.add(addRecommendationButton);
        addRecommendationButton.addActionListener(e -> addRecommendation());

        backButton = new JButton("Back to Menu");
        buttonPanel.add(backButton);
        backButton.addActionListener(e -> mainApp.switchPanel("MainMenu"));

        panel.add(buttonPanel, BorderLayout.SOUTH);

        loadRecommendations(); // Load existing recommendations
    }

    /**
     * method to add a recommendation to the recommendations area.
     */
    private void addRecommendation() {
        String recommendation = newRecommendationField.getText();
        if (!recommendation.isEmpty()) {
            recommendationsArea.append(recommendation + "\n");
            newRecommendationField.setText("");
        }
    }

    private void loadRecommendations() {
        
        recommendationsArea.setText("Existing recommendations...\n");
    }

    public JPanel getPanel() {
        return panel;
    }
}
