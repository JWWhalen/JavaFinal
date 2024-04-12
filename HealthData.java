public class HealthData {
    private int id;
    private int userId;
    private double weight;
    private double height;
    private int steps;
    private int heartRate;
    private String date;

    // constructour, getters, and setters
    public HealthData(int id, int userId, double weight, double height, int steps, int heartRate, String date) {
        this.id = id;
        this.userId = userId;
        this.weight = weight;
        this.height = height;
        this.steps = steps;
        this.heartRate = heartRate;
        this.date = date;
    }

    /**
     * Get the ID of the object.
     *
     * @return         the ID of the object
     */
    public int getId() {
        return id;
    }

    /**
     * get the user ID.
     *
     * @return    the user ID
     */
    public int getUserId() {
        return userId;
    }

   
    public double getWeight() {
        return weight;
    }

    /**
     * Get the height of the object.
     *
     * @return     he height of the object
     */
    public double getHeight() {
        return height;
    }

    
    public int getSteps() {
        return steps;//get the number of steps
    }

    public int getHeartRate() {
        return heartRate;//get the number of heart rate
    }

    public String getDate() {
        return date;//get the date
    }

    public void setId(int id) {
        this.id = id;//set the id
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "HealthData{" +
                "id=" + id +
                ", userId=" + userId +
                ", weight=" + weight +
                ", height=" + height +
                ", steps=" + steps +
                ", heartRate=" + heartRate +
                ", date='" + date + '\'' +
                '}';
    }
}
