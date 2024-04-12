import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

public class HealthMonitoringApp {

    private static UserDao userDao = new UserDao();
    private static DoctorPortalDao doctorPortalDao = new DoctorPortalDao();

    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Health Monitoring System.");
        while (true) {
            System.out.println("-------------------------------");
            System.out.println("1. Register a new user");
            System.out.println("2. Log in");
            System.out.println("3. Exit");
            System.out.println("Choose an option:");
            String response = scanner.nextLine();

            switch (response) {
                case "1":
                    registerUser(scanner);
                    break;
                case "2":
                    testLoginUser(scanner);
                    break;
                case "3":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    public static void registerUser(Scanner scanner) {
        System.out.println("Register: ");
        System.out.println("-------------------------------");
        System.out.println("Please enter first name: ");
        String first_name = scanner.nextLine();
        System.out.println("Please enter last name: ");
        String last_name = scanner.nextLine();
        System.out.println("Please enter email: ");
        String email = scanner.nextLine();
        System.out.println("Please enter password: ");
        String password = scanner.nextLine();
        System.out.println("Are you a doctor? (true/false): ");
        boolean is_doctor = Boolean.parseBoolean(scanner.nextLine());
        User user = new User(first_name, last_name, email, password, is_doctor);
        userDao.createUser(user);
    }

    public static void testLoginUser(Scanner scanner) {
        System.out.println("Log In: ");
        System.out.println("-------------------------------");
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
    
        User user = userDao.getUserByEmail(email);
        if (user != null) {
            boolean loginSuccess = BCrypt.checkpw(password, user.getPassword());
    
            if (loginSuccess) {
                System.out.println("Login Successful");
                if (user.isDoctor()) {
                    doctorLoggedInOptions(scanner, user.getId());
                } else {
                    userLoggedInOptions(scanner, user.getId());
                }
                return;
            }
        }
    
        System.out.println("Incorrect email or password. Please try again.");
    
    }

    public static boolean loginUser(String email, String password) {
        User user = userDao.getUserByEmail(email);
        return user != null && BCrypt.checkpw(password, user.getPassword());
    }

    public static void userLoggedInOptions(Scanner scanner, int userId) {
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Enter Health Data");
            System.out.println("2. View Recommendations");
            System.out.println("3. Setup Medicine Reminder");
            System.out.println("4. Logout");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    collectAndStoreHealthData(scanner, userId);
                    break;
                case "2":
                    displayUserRecommendations(scanner, userId);
                break;
                case "3":
                    addMedicineReminder(scanner, userId);
                break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
    public static void doctorLoggedInOptions(Scanner scanner, int doctorId) {
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. View My Patients");
            System.out.println("2. Access Patient Health Data");
            System.out.println("3. Add/Modify Health Recommendations");
            System.out.println("4. Logout");
            String choice = scanner.nextLine();
    
            switch (choice) {
                case "1":
                    viewMyPatients(scanner, doctorId);
                break;
                case "2":
                    accessPatientHealthData(scanner);
                break;
                case "3":
                    addOrModifyHealthRecommendations(scanner , doctorId);
                break;
                case "4":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
    public static void viewMyPatients(Scanner scanner, int doctorId) {
        List<User> patients = doctorPortalDao.getPatientsByDoctorId(doctorId);
        if (patients.isEmpty()) {
            System.out.println("You currently have no patients assigned.");
            System.out.println("Would you like to assign a patient to yourself? (yes/no)");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                assignPatientToDoctor(scanner, doctorId);
            }
        } else {
            System.out.println("Your patients:");
            for (User patient : patients) {
                System.out.printf("ID: %d, Name: %s %s, Email: %s\n",
                    patient.getId(),
                    patient.getFirstName(),
                    patient.getLastName(),
                    patient.getEmail()); // Ensure you comply with privacy regulations when displaying this information
            }
        }
    }
    
    
    public static void assignPatientToDoctor(Scanner scanner, int doctorId) {
    System.out.println("Enter the ID of the patient you want to assign to yourself:");
    int patientId = Integer.parseInt(scanner.nextLine());
    
    boolean success = doctorPortalDao.assignPatientToDoctor(patientId, doctorId);
    if (success) {
        System.out.println("Patient successfully assigned to you.");
    } else {
        System.out.println("Failed to assign patient to you.");
    }
}

    public static void accessPatientHealthData(Scanner scanner) {
        System.out.println("Enter the patient's ID:");
        int patientId = Integer.parseInt(scanner.nextLine());
        List<HealthData> healthData = doctorPortalDao.getHealthDataByPatientId(patientId);
        if (healthData.isEmpty()) {
            System.out.println("No health data found for the specified patient.");
        } else {
            for (HealthData data : healthData) {
                System.out.println(data.toString());
            }
        }
    }
    public static void addOrModifyHealthRecommendations(Scanner scanner, int doctorId) {
        System.out.println("Enter the patient's ID:");
        int patientId = Integer.parseInt(scanner.nextLine());
    
        // Retrieve existing recommendations, if any
        List<String> existingRecommendations = new HealthDataDao().getRecommendationsByUserId(patientId);
    
        // If there are existing recommendations, display them to the doctor
        if (!existingRecommendations.isEmpty()) {
            System.out.println("Existing recommendations for the patient:");
            for (String recommendation : existingRecommendations) {
                System.out.println("- " + recommendation);
            }
            System.out.println();
        }
    
        // Prompt the doctor to enter new recommendations
        System.out.println("Enter new recommendations (one per line, type 'done' to finish):");
        List<String> newRecommendations = new ArrayList<>();
        String input;
        while (!(input = scanner.nextLine()).equalsIgnoreCase("done")) {
            newRecommendations.add(input);
        }
    
        // Merge existing and new recommendations
        existingRecommendations.addAll(newRecommendations);
    
        // Call DoctorPortalDao to add or modify recommendations
        DoctorPortalDao doctorPortalDao = new DoctorPortalDao();
        boolean success = doctorPortalDao.addOrModifyHealthRecommendations(patientId, existingRecommendations);
        if (success) {
            System.out.println("Health recommendations added or modified successfully!");
        } else {
            System.out.println("Failed to add or modify health recommendations.");
        }
    }
    public static void collectAndStoreHealthData(Scanner scanner, int userId) {
        System.out.println("Enter your weight (in kg): ");
        double weight = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter your height (in cm): ");
        double height = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter your steps for today: ");
        int steps = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter your heart rate: ");
        int heartRate = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter today's date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        HealthData healthData = new HealthData(0, userId, weight, height, steps, heartRate, date);
        boolean success = new HealthDataDao().createHealthData(healthData);
        if (success) {
            System.out.println("Health data stored successfully!");
        
            // Generate recommendations
            RecommendationSystem recommendationSystem = new RecommendationSystem();
            List<String> recommendations = recommendationSystem.generateRecommendations(healthData);
            
            // Store recommendations
            boolean recommendationsSaved = new HealthDataDao().saveRecommendations(recommendations, userId);
            if (recommendationsSaved) {
                System.out.println("Recommendations generated and saved.");
            } else {
                System.out.println("Failed to save recommendations.");
            }
        } else {
            System.out.println("Failed to store health data.");
        }
    }
    // Inside HealthMonitoringApp
public static void displayUserRecommendations(Scanner scanner, int userId) {
    List<String> recommendations = new HealthDataDao().getRecommendationsByUserId(userId);
    if (!recommendations.isEmpty()) {
        System.out.println("Your Recommendations:");
        for (String recommendation : recommendations) {
            System.out.println("- " + recommendation);
        }
    } else {
        System.out.println("No recommendations available. Please enter your health data first.");
    }
}

public static void addMedicineReminder(Scanner scanner, int userId) {
    System.out.println("Enter medicine name:");
    String medicineName = scanner.nextLine();
    System.out.println("Enter dosage (e.g., '2 tablets'):");
    String dosage = scanner.nextLine();
    System.out.println("Enter schedule (e.g., 'every 8 hours'):");
    String schedule = scanner.nextLine();
    System.out.println("Enter start date (YYYY-MM-DD):");
    String startDate = scanner.nextLine();
    System.out.println("Enter end date (YYYY-MM-DD):");
    String endDate = scanner.nextLine();


    MedicineReminder reminder = new MedicineReminder(userId, medicineName, dosage, schedule, startDate, endDate);

    
    MedicineReminderManager reminderManager = new MedicineReminderManager();
    boolean success = reminderManager.addReminder(reminder);

    if (success) {
        System.out.println("Medicine reminder added successfully!");
    } else {
        System.out.println("Failed to add medicine reminder.");
    }
}

}
