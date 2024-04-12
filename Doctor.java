
public class Doctor extends User{
    private String medicalLicenseNumber;
    private String specialization;

    public Doctor(int id, String firstName, String lastName, String email){
        super(id, firstName, lastName, email, "", true);
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.specialization = specialization;
    }

    // geters and setters
    public String getMedicalLicenseNumber() {
        return medicalLicenseNumber;
    }

    /**
     * Sets the medical license number.
     *
     * @param  medicalLicenseNumber  the medical license number to set
     * @return                       void
     */
    public void setMedicalLicenseNumber(String medicalLicenseNumber) {
        this.medicalLicenseNumber = medicalLicenseNumber;
    }

    /**
     * get the specialization of the object.
     *
     * @return         	the specialization of the object
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * set the specialization of the object.
     *
     * @param  specialization   the specialization to set
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}

