package hospital;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.*;

public class Medicines extends JFrame {

    private JFrame frame;
    private JTextField medicineIDTextField;
    private JTextField medicineNameTextField;
    private JTextField medicineSellingPriceTextField;
    private JTextField medicineBuyingPriceTextField;
    private JTextField medicineQuantityTextField;
    private JTextField medicineDescriptionTextField;
    private JTextArea allMData;

    // Sử dụng MyLinkedHashMap thay vì DoublyLinkedList
    private MyLinkedHashMap<Integer, Medicine> medicineList = new MyLinkedHashMap<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Medicines window = new Medicines();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Medicines() {
        initialize();
        readAllData();
        writeAllData();
    }

    public void readAllData() {
        try {
            File mFile = new File("src/hospital/mdata.txt");
            Scanner scanner = new Scanner(mFile);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] currentData = data.split(";");
                Medicine medicine = new Medicine(
                    Integer.parseInt(currentData[0]), // ID
                    currentData[1], // Name
                    Integer.parseInt(currentData[2]), // Selling Price
                    Integer.parseInt(currentData[3]), // Buying Price
                    Integer.parseInt(currentData[4]), // Quantity
                    currentData[5]  // Description
                );
                medicineList.put(medicine.getId(), medicine); // Thêm vào MyLinkedHashMap
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAllData() {
        try {
            FileWriter fileWriter = new FileWriter("src/hospital/mdata.txt");
            for (Integer key : medicineList.keySet()) {
                Medicine medicine = medicineList.get(key);
                fileWriter.write(medicine.getId() + ";" + medicine.getName() + ";" + medicine.getSellingPrice() + ";" +
                        medicine.getBuyingPrice() + ";" + medicine.getQuantity() + ";" + medicine.getDescription() + "\n");
            }
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeAllData() {
        allMData.setText("");
        allMData.append("ID\tName\tSelling Price\tBuying Price\tQuantity\tDescription\n");
        for (Integer key : medicineList.keySet()) {
            Medicine medicine = medicineList.get(key);
            allMData.append(medicine.getId() + "\t" + medicine.getName() + "\t" + medicine.getSellingPrice() + "\t" +
                    medicine.getBuyingPrice() + "\t" + medicine.getQuantity() + "\t" + medicine.getDescription() + "\n");
        }
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1127, 502);
        frame.setTitle("Medicine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBounds(0, 0, 1100, 91);
        frame.getContentPane().add(titlePanel);
        titlePanel.setLayout(null);

        JLabel titleLabel = new JLabel("Medicines Menu");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        titleLabel.setBounds(100, 20, 250, 40);
        titlePanel.add(titleLabel);

        JButton homepageButton = new JButton("Homepage");
        homepageButton.addActionListener(e -> {
            HomePage homePage = new HomePage();
            frame.dispose();
            homePage.setVisible(true);
        });
        homepageButton.setBounds(950, 30, 130, 30);
        titlePanel.add(homepageButton);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBounds(10, 100, 500, 350);
        frame.getContentPane().add(inputPanel);
        inputPanel.setLayout(null);

        JLabel medicineIDLabel = new JLabel("ID:");
        medicineIDLabel.setBounds(20, 20, 100, 30);
        inputPanel.add(medicineIDLabel);

        medicineIDTextField = new JTextField();
        medicineIDTextField.setBounds(130, 20, 300, 30);
        inputPanel.add(medicineIDTextField);

        JLabel medicineNameLabel = new JLabel("Name:");
        medicineNameLabel.setBounds(20, 60, 100, 30);
        inputPanel.add(medicineNameLabel);

        medicineNameTextField = new JTextField();
        medicineNameTextField.setBounds(130, 60, 300, 30);
        inputPanel.add(medicineNameTextField);

        JLabel sellingPriceLabel = new JLabel("Selling Price:");
        sellingPriceLabel.setBounds(20, 100, 100, 30);
        inputPanel.add(sellingPriceLabel);

        medicineSellingPriceTextField = new JTextField();
        medicineSellingPriceTextField.setBounds(130, 100, 300, 30);
        inputPanel.add(medicineSellingPriceTextField);

        JLabel buyingPriceLabel = new JLabel("Buying Price:");
        buyingPriceLabel.setBounds(20, 140, 100, 30);
        inputPanel.add(buyingPriceLabel);

        medicineBuyingPriceTextField = new JTextField();
        medicineBuyingPriceTextField.setBounds(130, 140, 300, 30);
        inputPanel.add(medicineBuyingPriceTextField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(20, 180, 100, 30);
        inputPanel.add(quantityLabel);

        medicineQuantityTextField = new JTextField();
        medicineQuantityTextField.setBounds(130, 180, 300, 30);
        inputPanel.add(medicineQuantityTextField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(20, 220, 100, 30);
        inputPanel.add(descriptionLabel);

        medicineDescriptionTextField = new JTextField();
        medicineDescriptionTextField.setBounds(130, 220, 300, 30);
        inputPanel.add(medicineDescriptionTextField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            if (medicineIDTextField.getText().isEmpty() ||
                medicineNameTextField.getText().isEmpty() ||
                medicineSellingPriceTextField.getText().isEmpty() ||
                medicineBuyingPriceTextField.getText().isEmpty() ||
                medicineQuantityTextField.getText().isEmpty() ||
                medicineDescriptionTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled!");
            } else {
                int medicineID = Integer.parseInt(medicineIDTextField.getText());
                if (medicineList.containsKey(medicineID)) {
                    JOptionPane.showMessageDialog(null, "Medicine ID already exists!");
                } else {
                    try {
                        Medicine medicine = new Medicine(
                            medicineID,
                            medicineNameTextField.getText(),
                            Integer.parseInt(medicineSellingPriceTextField.getText()),
                            Integer.parseInt(medicineBuyingPriceTextField.getText()),
                            Integer.parseInt(medicineQuantityTextField.getText()),
                            medicineDescriptionTextField.getText()
                        );
                        medicineList.put(medicine.getId(), medicine);
                        saveAllData();
                        writeAllData();

                        // Clear input fields after saving
                        medicineIDTextField.setText("");
                        medicineNameTextField.setText("");
                        medicineSellingPriceTextField.setText("");
                        medicineBuyingPriceTextField.setText("");
                        medicineQuantityTextField.setText("");
                        medicineDescriptionTextField.setText("");

                        JOptionPane.showMessageDialog(null, "Medicine added successfully!");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter valid numbers for Selling Price, Buying Price, and Quantity!");
                    }
                }
            }
        });

        saveButton.setBounds(150, 270, 80, 30);
        inputPanel.add(saveButton);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            int id = Integer.parseInt(medicineIDTextField.getText());
            if (medicineList.containsKey(id)) {
                Medicine medicine = medicineList.get(id);
                JOptionPane.showMessageDialog(frame,
                        "Medicine Info:\n" +
                        "ID: " + medicine.getId() + "\n" +
                        "Name: " + medicine.getName() + "\n" +
                        "Selling Price: " + medicine.getSellingPrice() + "\n" +
                        "Buying Price: " + medicine.getBuyingPrice() + "\n" +
                        "Quantity: " + medicine.getQuantity() + "\n" +
                        "Description: " + medicine.getDescription());
            } else {
                JOptionPane.showMessageDialog(frame, "Medicine not found!");
            }
        });
        searchButton.setBounds(250, 270, 80, 30);
        inputPanel.add(searchButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int id = Integer.parseInt(medicineIDTextField.getText());
            if (medicineList.containsKey(id)) {
                medicineList.remove(id);
                saveAllData();
                writeAllData();
                JOptionPane.showMessageDialog(frame, "Medicine deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Medicine not found!");
            }
        });
        deleteButton.setBounds(350, 270, 80, 30);
        inputPanel.add(deleteButton);

        allMData = new JTextArea();
        allMData.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(allMData);
        scrollPane.setBounds(520, 100, 570, 350);
        frame.getContentPane().add(scrollPane);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
