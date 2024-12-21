package hospital;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class CheckUp extends JFrame {

    private JFrame frame;
    private JTextField patientIDTextField;
    private JTextField patientFeeTextField;
    private JTextField medicineQuantityTextField;
    private JComboBox<String> medicineComboBox = new JComboBox<>();

    // Thay đổi DoublyLinkedList thành HashTable
    private HashTable<String, Patient> patientTable = new HashTable<>(100);
    private HashTable<Integer, Medicine> medicineTable = new HashTable<>(100);
    private DoublyLinkedList<BillingInfo> recordList = new DoublyLinkedList<>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CheckUp window = new CheckUp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     */
    public CheckUp() {
        initialize();
        readAllMData();
        readAllPData();
        readAllRData();
        loadMedicine();
    }

    /**
     * Load medicines into comboBox.
     */
    public void loadMedicine() {
        medicineComboBox.removeAllItems();
        for (Medicine medicine : medicineTable.getAllValues()) {
            medicineComboBox.addItem(medicine.getId() + " - " + medicine.getName());
        }
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }

    /**
     * Check if medicine quantity is sufficient.
     */
    public boolean checkForQuantity() {
        int currentMedicineID = Integer.parseInt(medicineComboBox.getSelectedItem().toString().split(" ")[0]);
        Medicine medicine = medicineTable.get(currentMedicineID);
        if (medicine != null) {
            return Integer.parseInt(medicineQuantityTextField.getText()) > medicine.getQuantity();
        }
        return false;
    }

    /**
     * Read medicines data from file.
     */
    public void readAllMData() {
        try {
            File file = new File("src/hospital/mdata.txt"); // Sử dụng đường dẫn tương đối
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] currentData = data.split(";");
                Medicine medicine = new Medicine(
                    Integer.parseInt(currentData[0]), // ID
                    currentData[1],                   // Name
                    Integer.parseInt(currentData[2]), // Selling Price
                    Integer.parseInt(currentData[3]), // Buying Price
                    Integer.parseInt(currentData[4]), // Quantity
                    currentData[5]                    // Description
                );
                medicineTable.insert(medicine.getId(), medicine); // Hoặc medicineList.add(medicine)
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Read patients data from file.
     */
    public void readAllPData() {
        try {
            File file = new File("src/hospital/pdata.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] currentData = data.split(";");
                Patient patient = new Patient(
                        currentData[0],
                        Integer.parseInt(currentData[2]),
                        currentData[1],
                        currentData[3],
                        currentData[4],
                        currentData[5]
                );
                patientTable.insert(patient.getId(), patient);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/**
 * Read billing records from file.
 */
    public void readAllRData() {
        try {
            File file = new File("src/hospital/record.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] currentData = data.split(";");
                BillingInfo record = new BillingInfo();
                record.setPatientID(currentData[0]);
                record.setFee(Integer.parseInt(currentData[1]));
                record.setRecomendations(currentData[2]);
                record.setDate(currentData[3]);

                String[] medicineIDs = currentData[4].split(",");
                String[] quantities = currentData[5].split(",");

                for (int j = 0; j < medicineIDs.length; j++) {
                    if (!medicineIDs[j].isEmpty() && !quantities[j].isEmpty()) {
                        record.addMedicineID(Integer.parseInt(medicineIDs[j].trim()));
                        record.addMedicineQuantity(Integer.parseInt(quantities[j].trim()));
                    }
                }

                recordList.insert(record);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


/**
 * Save all medicines data to file.
 */
public void saveAllMData() {
    try {
        FileWriter fileWriter = new FileWriter("src/hospital/mdata.txt");
        for (Medicine medicine : medicineTable.getAllValues()) {
            fileWriter.write(medicine.getId() + ";" + medicine.getName() + ";" + medicine.getSellingPrice() + ";"
                    + medicine.getBuyingPrice() + ";" + medicine.getQuantity() + ";" + medicine.getDescription() + "\n");
        }
        fileWriter.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

/**
 * Save all billing records to file.
 */
public void saveAllRData() {
    try {
        FileWriter fileWriter = new FileWriter("src/hospital/record.txt");
        for (int i = 0; i < recordList.size(); i++) {
            BillingInfo record = recordList.getAtIndex(i);
            fileWriter.append(record.getPatientID() + ";" + record.getFee() + ";" + record.getRecomendations() + ";"
                    + record.getDate() + ";");
            for (int j = 0; j < record.getMedicineID().size(); j++) {
                fileWriter.append(record.getMedicineID().getAtIndex(j) + ",");
            }
            fileWriter.append("\n");
        }
        fileWriter.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
private void initialize() {
    frame = new JFrame();
    frame.getContentPane().setBackground(Color.WHITE);
    frame.setTitle("Checkup Menu");
    frame.setBounds(100, 100, 1064, 525);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    // Panel Title
    JPanel titlePanel = new JPanel();
    titlePanel.setBackground(Color.WHITE);
    titlePanel.setBounds(0, 0, 1052, 72);
    frame.getContentPane().add(titlePanel);
    titlePanel.setLayout(null);

    JLabel checkUpMenuLabel = new JLabel("Checkup Menu");
    checkUpMenuLabel.setForeground(new Color(0, 153, 204));
    checkUpMenuLabel.setFont(new Font("iCiel Cadena", Font.PLAIN, 24));
    checkUpMenuLabel.setBounds(20, 19, 200, 40);
    titlePanel.add(checkUpMenuLabel);

    JButton homePageButton = new JButton("Homepage");
    homePageButton.setBounds(912, 13, 121, 25);
    homePageButton.setBackground(Color.WHITE);
    homePageButton.setForeground(new Color(51, 153, 153));
    homePageButton.setFont(new Font("Tahoma", Font.BOLD, 14));
    homePageButton.addActionListener(e -> {
        HomePage homePage = new HomePage();
        frame.dispose();
        homePage.setVisible(true);
    });
    titlePanel.add(homePageButton);

    // Patient Info Panel
    JPanel patientInfoPanel = new JPanel();
    patientInfoPanel.setBackground(Color.WHITE);
    patientInfoPanel.setBounds(10, 77, 1026, 396);
    frame.getContentPane().add(patientInfoPanel);
    patientInfoPanel.setLayout(null);

    JLabel patientIDLabel = new JLabel("Patient ID:");
    patientIDLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
    patientIDLabel.setBounds(20, 20, 100, 25);
    patientInfoPanel.add(patientIDLabel);

    patientIDTextField = new JTextField();
    patientIDTextField.setBounds(120, 20, 200, 25);
    patientInfoPanel.add(patientIDTextField);

    JLabel feeLabel = new JLabel("Fee:");
    feeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
    feeLabel.setBounds(20, 60, 100, 25);
    patientInfoPanel.add(feeLabel);

    patientFeeTextField = new JTextField();
    patientFeeTextField.setBounds(120, 60, 200, 25);
    patientInfoPanel.add(patientFeeTextField);

    JLabel recommendationLabel = new JLabel("Recommendations:");
    recommendationLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
    recommendationLabel.setBounds(20, 100, 150, 25);
    patientInfoPanel.add(recommendationLabel);

    JTextArea recommendationTextArea = new JTextArea();
    recommendationTextArea.setBounds(20, 130, 300, 200);
    recommendationTextArea.setLineWrap(true);
    recommendationTextArea.setWrapStyleWord(true);
    JScrollPane recommendationScroll = new JScrollPane(recommendationTextArea);
    recommendationScroll.setBounds(20, 130, 300, 200);
    patientInfoPanel.add(recommendationScroll);

    JLabel medicineLabel = new JLabel("Medicines:");
    medicineLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
    medicineLabel.setBounds(350, 20, 100, 25);
    patientInfoPanel.add(medicineLabel);

    medicineComboBox = new JComboBox<>();
    medicineComboBox.setBounds(450, 20, 250, 25);
    patientInfoPanel.add(medicineComboBox);

    JLabel quantityLabel = new JLabel("Quantity:");
    quantityLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
    quantityLabel.setBounds(350, 60, 100, 25);
    patientInfoPanel.add(quantityLabel);

    medicineQuantityTextField = new JTextField();
    medicineQuantityTextField.setBounds(450, 60, 250, 25);
    patientInfoPanel.add(medicineQuantityTextField);

    JLabel medicineListLabel = new JLabel("Medicines List:");
    medicineListLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
    medicineListLabel.setBounds(350, 100, 150, 25);
    patientInfoPanel.add(medicineListLabel);

    JTextArea medicineListArea = new JTextArea();
    medicineListArea.setEditable(false);
    medicineListArea.setLineWrap(true);
    medicineListArea.setWrapStyleWord(true);
    JScrollPane medicineListScroll = new JScrollPane(medicineListArea);
    medicineListScroll.setBounds(350, 130, 350, 200);
    patientInfoPanel.add(medicineListScroll);

    JButton addMedicineButton = new JButton("Add Medicine");
    addMedicineButton.setBounds(720, 20, 120, 60);
    addMedicineButton.setBackground(Color.WHITE);
    addMedicineButton.setForeground(new Color(0, 153, 153));
    addMedicineButton.setFont(new Font("Tahoma", Font.BOLD, 12));
    addMedicineButton.addActionListener(e -> {
        String selectedMedicine = (String) medicineComboBox.getSelectedItem();
        String quantity = medicineQuantityTextField.getText();
        if (selectedMedicine != null && !quantity.isEmpty()) {
            medicineListArea.append(selectedMedicine + " - Quantity: " + quantity + "\n");
            medicineQuantityTextField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a medicine and enter quantity.");
        }
    });
    patientInfoPanel.add(addMedicineButton);

    JButton saveButton = new JButton("Save");
    saveButton.setBounds(450, 360, 100, 30);
    saveButton.setBackground(Color.WHITE);
    saveButton.setForeground(new Color(0, 153, 153));
    saveButton.setFont(new Font("Tahoma", Font.BOLD, 14));
    saveButton.addActionListener(e -> {
        try {
            // Validate input
            if (patientIDTextField.getText().isEmpty() || 
                patientFeeTextField.getText().isEmpty() || 
                recommendationTextArea.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Parse the Doctor Fee
            String patientID = patientIDTextField.getText();
            int doctorFee = Integer.parseInt(patientFeeTextField.getText());
            String recommendation = recommendationTextArea.getText();
            DateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy");
            String date = dateFormat.format(new Date());

           
         // Parse Medicines List
            StringBuilder medicines = new StringBuilder();
            StringBuilder quantities = new StringBuilder(); // Thêm danh sách số lượng
            String[] medicineEntries = medicineListArea.getText().split("\n");
            int totalMedicineCost = 0; // Total medicine cost
            for (String entry : medicineEntries) {
                if (!entry.isEmpty()) {
                    String[] details = entry.split(" - Quantity: ");
                    int medicineID = Integer.parseInt(details[0].split(" ")[0]); // Medicine ID
                    int quantity = Integer.parseInt(details[1]); // Quantity sold

                    // Get medicine from HashTable
                    Medicine medicine = medicineTable.get(medicineID);
                    if (medicine != null) {
                        // Check if there is enough stock
                        if (medicine.getQuantity() < quantity) {
                            JOptionPane.showMessageDialog(frame, 
                                "Not enough stock for medicine: " + medicine.getName(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        // Deduct quantity from stock
                        medicine.setQuantity(medicine.getQuantity() - quantity);
                        // Add cost to total medicine cost
                        totalMedicineCost += quantity * medicine.getSellingPrice();
                        // Append to medicines list
                        medicines.append(medicineID).append(",");
                        quantities.append(quantity).append(","); // Lưu số lượng
                    }
                }
            }
     
            //calculate total cost
            int totalCost = doctorFee + totalMedicineCost;


            // Lưu vào file record.txt
            try (FileWriter fileWriter = new FileWriter("src/hospital/record.txt", true)) { // Append mode
                fileWriter.write(patientID + ";" + doctorFee + ";" + recommendation + ";" + date + ";" 
                                + medicines + ";" + quantities + "\n");
            }

            // Save updated medicine data to file
            saveAllMData();

            // Display costs to user
            JOptionPane.showMessageDialog(frame, 
                "Checkup saved successfully!\n" +
                "Doctor Fee: " + doctorFee + "\n" +
                "Total Medicine Cost: " + totalMedicineCost + "\n" +
                "Total Cost: " + totalCost,
                "Success", JOptionPane.INFORMATION_MESSAGE);

            // Clear fields after saving
            patientIDTextField.setText("");
            patientFeeTextField.setText("");
            recommendationTextArea.setText("");
            medicineListArea.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numbers for Fee and Quantity.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred while saving the record.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });




    patientInfoPanel.add(saveButton);
}


}
