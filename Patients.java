package hospital;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Patients extends JFrame {

    private JFrame frame;
    private JTextField patientIDTextField;
    private JTextField patientAgeTextField;
    private JTextField patientNameTextField;
    private JTextField patientGenderTextField;
    private JTextField patientAddressTextField;
    private JTextField patientContactTextField;
    private JTextArea allPData;

    // Sử dụng MyLinkedHashMap thay vì LinkedHashMap
    private MyLinkedHashMap<String, Patient> patientList = new MyLinkedHashMap<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Patients window = new Patients();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Patients() {
        initialize();
        readAllData();
        writeAllData();
    }

    public void readAllData() {
        try {
            File pFile = new File("src/hospital/pdata.txt"); // Đường dẫn tương đối
            Scanner scanner = new Scanner(pFile);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] currentData = data.split(";");
                Patient patient = new Patient(
                    currentData[0], // ID
                    Integer.parseInt(currentData[2]), // Age
                    currentData[1], // Name
                    currentData[3], // Gender
                    currentData[4], // Address
                    currentData[5]  // Contact
                );
                patientList.put(patient.getId(), patient); // Thêm vào MyLinkedHashMap
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAllData() {
        try {
            FileWriter fileWriter = new FileWriter("src/hospital/pdata.txt"); // Đường dẫn tương đối
            for (String key : patientList.keySet()) {
                Patient patient = patientList.get(key);
                fileWriter.write(patient.getId() + ";" + patient.getName() + ";" + patient.getAge() + ";"
                        + patient.getGender() + ";" + patient.getAddress() + ";" + patient.getContact() + "\n");
            }
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void writeAllData() {
        allPData.setText("");
        allPData.append("ID\tName\tAge\tGender\tAddress\tContact\n");
        for (String key : patientList.keySet()) {
            Patient patient = patientList.get(key);
            allPData.append(patient.getId() + "\t" + patient.getName() + "\t" + patient.getAge() + "\t"
                    + patient.getGender() + "\t" + patient.getAddress() + "\t" + patient.getContact() + "\n");
        }
    }

    public boolean isExistID() {
        return patientList.containsKey(patientIDTextField.getText());
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBounds(100, 100, 1066, 511);
        frame.setTitle("Patients");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBounds(0, 0, 1052, 91);
        frame.getContentPane().add(titlePanel);
        titlePanel.setLayout(null);
        
        ImageIcon patientIcon = new ImageIcon(getClass().getResource("/hospital/patient.png")); // Sử dụng getResource
        Image patientImg = patientIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel patientImage = new JLabel(new ImageIcon(patientImg));
        patientImage.setBounds(10, 0, 92, 85);
        titlePanel.add(patientImage);


        JLabel patientMenuLabel = new JLabel("Patient Menu");
        patientMenuLabel.setForeground(new Color(0, 153, 204));
        patientMenuLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        patientMenuLabel.setBounds(122, 29, 151, 40);
        titlePanel.add(patientMenuLabel);

        JButton homePageButton = new JButton("Homepage");
        homePageButton.addActionListener(e -> {
            HomePage myHomePage = new HomePage();
            frame.dispose();
            myHomePage.setVisible(true);
        });
        homePageButton.setForeground(new Color(51, 153, 153));
        homePageButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        homePageButton.setBackground(Color.WHITE);
        homePageButton.setBounds(921, 10, 121, 25);
        titlePanel.add(homePageButton);

        JPanel patientInfoPanel = new JPanel();
        patientInfoPanel.setBackground(Color.WHITE);
        patientInfoPanel.setBounds(10, 101, 482, 375);
        frame.getContentPane().add(patientInfoPanel);
        patientInfoPanel.setLayout(null);

        JLabel patientInfoLabel = new JLabel("Patient Information");
        patientInfoLabel.setForeground(new Color(0, 153, 204));
        patientInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        patientInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        patientInfoLabel.setBounds(134, 10, 213, 31);
        patientInfoPanel.add(patientInfoLabel);

        JLabel patientIDLabel = new JLabel("ID:");
        patientIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        patientIDLabel.setBounds(48, 58, 68, 25);
        patientInfoPanel.add(patientIDLabel);

        patientIDTextField = new JTextField();
        patientIDTextField.setBounds(131, 58, 303, 25);
        patientInfoPanel.add(patientIDTextField);

        JLabel patientNameLabel = new JLabel("Name:");
        patientNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        patientNameLabel.setBounds(48, 98, 68, 25);
        patientInfoPanel.add(patientNameLabel);

        patientNameTextField = new JTextField();
        patientNameTextField.setBounds(131, 98, 303, 25);
        patientInfoPanel.add(patientNameTextField);

        JLabel patientAgeLabel = new JLabel("Age:");
        patientAgeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        patientAgeLabel.setBounds(48, 137, 68, 25);
        patientInfoPanel.add(patientAgeLabel);

        patientAgeTextField = new JTextField();
        patientAgeTextField.setBounds(131, 137, 303, 25);
        patientInfoPanel.add(patientAgeTextField);

        JLabel patientGenderLabel = new JLabel("Gender:");
        patientGenderLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        patientGenderLabel.setBounds(48, 176, 68, 25);
        patientInfoPanel.add(patientGenderLabel);

        patientGenderTextField = new JTextField();
        patientGenderTextField.setBounds(131, 176, 303, 25);
        patientInfoPanel.add(patientGenderTextField);

        JLabel patientAddressLabel = new JLabel("Address:");
        patientAddressLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        patientAddressLabel.setBounds(48, 215, 68, 25);
        patientInfoPanel.add(patientAddressLabel);

        patientAddressTextField = new JTextField();
        patientAddressTextField.setBounds(131, 215, 303, 25);
        patientInfoPanel.add(patientAddressTextField);

        JLabel patientContactLabel = new JLabel("Contact:");
        patientContactLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        patientContactLabel.setBounds(48, 254, 68, 25);
        patientInfoPanel.add(patientContactLabel);

        patientContactTextField = new JTextField();
        patientContactTextField.setBounds(131, 254, 303, 25);
        patientInfoPanel.add(patientContactTextField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            if (patientIDTextField.getText().isEmpty() ||
                    patientNameTextField.getText().isEmpty() ||
                    patientAgeTextField.getText().isEmpty() ||
                    patientGenderTextField.getText().isEmpty() ||
                    patientAddressTextField.getText().isEmpty() ||
                    patientContactTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled!");
            } else if (isExistID()) {
                JOptionPane.showMessageDialog(null, "ID already exists!");
            } else {
                try {
                    Patient patient = new Patient(
                        patientIDTextField.getText(),
                        Integer.parseInt(patientAgeTextField.getText()),
                        patientNameTextField.getText(),
                        patientGenderTextField.getText(),
                        patientAddressTextField.getText(),
                        patientContactTextField.getText()
                    );
                    patientList.put(patient.getId(), patient);
                    saveAllData();
                    writeAllData();

                    patientIDTextField.setText(null);
                    patientNameTextField.setText(null);
                    patientAgeTextField.setText(null);
                    patientGenderTextField.setText(null);
                    patientAddressTextField.setText(null);
                    patientContactTextField.setText(null);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Age must be a number!");
                }
            }
        });
        saveButton.setForeground(new Color(51, 153, 153));
        saveButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        saveButton.setBackground(Color.WHITE);
        saveButton.setBounds(80, 315, 100, 30);
        patientInfoPanel.add(saveButton);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            String id = patientIDTextField.getText();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid ID!");
            } else if (patientList.containsKey(id)) {
                Patient patient = patientList.get(id);
                JOptionPane.showMessageDialog(null,
                        "Patient Info:\n" +
                        "ID: " + patient.getId() + "\n" +
                        "Name: " + patient.getName() + "\n" +
                        "Age: " + patient.getAge() + "\n" +
                        "Gender: " + patient.getGender() + "\n" +
                        "Address: " + patient.getAddress() + "\n" +
                        "Contact: " + patient.getContact());
            } else {
                JOptionPane.showMessageDialog(null, "Patient not found!");
            }
        });
        searchButton.setForeground(new Color(51, 153, 153));
        searchButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        searchButton.setBackground(Color.WHITE);
        searchButton.setBounds(200, 315, 100, 30);
        patientInfoPanel.add(searchButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            String id = patientIDTextField.getText();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in ID!");
            } else if (patientList.containsKey(id)) {
                patientList.remove(id);
                saveAllData();
                writeAllData();
                JOptionPane.showMessageDialog(null, "Patient deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Patient not found!");
            }
        });
        deleteButton.setForeground(new Color(51, 153, 153));
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        deleteButton.setBackground(Color.WHITE);
        deleteButton.setBounds(320, 315, 100, 30);
        patientInfoPanel.add(deleteButton);

        allPData = new JTextArea();
        allPData.setEditable(false);
        allPData.setBounds(502, 101, 550, 375);
        frame.getContentPane().add(allPData);
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
