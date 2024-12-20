package hospital;

public class BillingInfo {
    private String patientID;
    private DoublyLinkedList<Integer> medicineID;
    private String recomendations, date;
    private int fee;

    public BillingInfo() {
        this.patientID = null;
        this.medicineID = new DoublyLinkedList<>();
        this.recomendations = null;
        this.date = null;
        this.fee = 0;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public DoublyLinkedList<Integer> getMedicineID() {
        return medicineID;
    }

    public void addMedicineID(int medicineID) {
        this.medicineID.insert(medicineID);
    }

    public String getRecomendations() {
        return recomendations;
    }

    public void setRecomendations(String recomendations) {
        this.recomendations = recomendations;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        StringBuilder medicineList = new StringBuilder();
        for (int i = 0; i < medicineID.size(); i++) {
            medicineList.append(medicineID.getAtIndex(i)).append(", ");
        }
        if (medicineList.length() > 0) {
            medicineList.setLength(medicineList.length() - 2); // Remove trailing ", "
        }
        return "BillingInfo [patientID=" + patientID + ", medicineID=" + medicineList + ", recomendations=" 
                + recomendations + ", date=" + date + ", fee=" + fee + "]";
    }
}
