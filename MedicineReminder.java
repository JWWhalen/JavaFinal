
public class MedicineReminder {
    private int userId;
    private String medicineName;
    private String dosage;
    private String schedule;
    private String startDate; // Assuming you have this field based on previous discussions
    private String endDate;   // Assuming you have this field based on previous discussions

    // Constructor
    public MedicineReminder(int userId, String medicineName, String dosage, String schedule, String startDate, String endDate) {
        this.userId = userId;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.schedule = schedule;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    /**
     * gets the midicine name.
     *
     * @return         	the medicine name
     */
    public String getMedicineName() {
        return medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

}
