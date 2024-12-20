package hospital;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

public class Reports extends JFrame {

    private JFrame frame;
    private JTextField enterIDTextField;
    private HashTable<String, Patient> patientTable = new HashTable<>(100); // Sử dụng key là String (Patient ID)
    private HashTable<Integer, Medicine> medicineTable = new HashTable<>(100); // Sử dụng key là Integer (Medicine ID)
    private DoublyLinkedList<BillingInfo> recordList = new DoublyLinkedList<>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Reports window = new Reports();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Reports() {
        initialize();
        readAllPData();
        readAllMData();
        readAllRData();
    }

    /**
     * Read all patient data from file.
     */
    public void readAllPData() {
        try {
            File pFile = new File("src/hospital/pdata.txt");
            Scanner scanner = new Scanner(pFile);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] currentData = data.split(";");
                Patient patient = new Patient(
                    currentData[0], // Patient ID
                    Integer.parseInt(currentData[2]), // Age
                    currentData[1], // Name
                    currentData[3], // Gender
                    currentData[4], // Address
                    currentData[5] // Contact
                );
                patientTable.insert(currentData[0], patient); // Key: Patient ID
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read all medicine data from file.
     */
    public void readAllMData() {
        try {
            File pFile = new File("src/hospital/mdata.txt");
            Scanner scanner = new Scanner(pFile);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] currentData = data.split(";");
                Medicine medicine = new Medicine(
                    Integer.parseInt(currentData[0]), // Medicine ID
                    Integer.parseInt(currentData[2]), // Selling Price
                    Integer.parseInt(currentData[3]), // Buying Price
                    Integer.parseInt(currentData[4]), // Quantity
                    currentData[1], // Name
                    currentData[5] // Description
                );
                medicineTable.insert(Integer.parseInt(currentData[0]), medicine); // Key: Medicine ID
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read all record data from file.
     */
    public void readAllRData() {
        try {
            File pFile = new File("src/hospital/record.txt");
            Scanner scanner = new Scanner(pFile);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine().trim();

                // Loại bỏ dấu phẩy cuối cùng nếu tồn tại
                if (data.endsWith(",")) {
                    data = data.substring(0, data.length() - 1);
                }

                String[] currentData = data.split(";");
                BillingInfo record = new BillingInfo();
                record.setPatientID(currentData[0]); // Patient ID
                record.setFee(Integer.parseInt(currentData[1])); // Fee
                record.setRecomendations(currentData[2]); // Recommendations
                record.setDate(currentData[3]); // Date

                // Phân tích danh sách Medicine ID
                if (currentData.length > 4) {
                    String[] medicines = currentData[4].split(",");
                    for (String medID : medicines) {
                        if (!medID.isEmpty()) {
                            // Chuyển đổi String sang int trước khi thêm vào danh sách
                            record.addMedicineID(Integer.parseInt(medID.trim()));
                        }
                    }
                }
                // Log thông tin thêm vào recordList
                System.out.println("Added record: " + record);

                recordList.insert(record);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/**
 * Get patient data by ID.
 */
public Patient getPatientData(String id) {
    return patientTable.get(id); // Tìm Patient bằng ID
}

/**
 * Get medicine data by ID.
 */
public Medicine getMedicineData(int id) {
    return medicineTable.get(id); // Tìm Medicine bằng ID
}

/**
 * Initialize the GUI components.
 */
private void initialize() {
    frame = new JFrame();
    frame.getContentPane().setBackground(Color.WHITE);
    frame.setTitle("Reports");
    frame.setBounds(100, 100, 1064, 525);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JPanel panel = new JPanel();
    panel.setBackground(Color.WHITE);
    panel.setBounds(0, 0, 1048, 76);
    frame.getContentPane().add(panel);
    panel.setLayout(null);

    ImageIcon reportIcon = new ImageIcon("src/hospital/report.jpg");
    Image reportImg = reportIcon.getImage().getScaledInstance(95, 70, Image.SCALE_SMOOTH);
    JLabel reportImage = new JLabel(new ImageIcon(reportImg));
    reportImage.setBounds(12, 0, 92, 76);
    panel.add(reportImage);

    JLabel reportMenuLabel = new JLabel("Report Menu");
    reportMenuLabel.setForeground(new Color(0, 153, 204));
    reportMenuLabel.setFont(new Font("iCiel Cadena", Font.PLAIN, 24));
    reportMenuLabel.setBounds(116, 22, 172, 40);
    panel.add(reportMenuLabel);

    JButton homePageButton = new JButton("Homepage");
    homePageButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            HomePage myHomePage = new HomePage();
            myHomePage.setVisible(true);
            frame.dispose();
        }
    });
    homePageButton.setForeground(new Color(51, 153, 153));
    homePageButton.setFont(new Font("Tahoma", Font.BOLD, 14));
    homePageButton.setBackground(Color.WHITE);
    homePageButton.setBounds(912, 13, 121, 25);
    panel.add(homePageButton);

    JTextArea recordTextArea = new JTextArea();
    recordTextArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(recordTextArea);
    scrollPane.setBounds(10, 130, 1024, 310);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    frame.getContentPane().add(scrollPane);

    JButton getRecordButton = new JButton("Get Record");
    getRecordButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (enterIDTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter ID!");
            } else {
                String enteredID = enterIDTextField.getText();
                boolean recordFound = false;
                recordTextArea.setText(""); // Xóa nội dung cũ trước khi hiển thị kết quả mới

                // Duyệt qua tất cả các bản ghi trong recordList
                for (int i = 0; i < recordList.size(); i++) {
                    BillingInfo record = recordList.getAtIndex(i);
                    if (record.getPatientID().equals(enteredID)) {
                        recordFound = true;

                        recordTextArea.append("Patient Info\n");
                        Patient patient = getPatientData(record.getPatientID());
                        if (patient != null) {
                            recordTextArea.append("Patient Name = " + patient.getName() + "\n");
                            recordTextArea.append("Patient Contact = " + patient.getContact() + "\n");
                        } else {
                            recordTextArea.append("No patient information found.\n");
                        }

                        recordTextArea.append("\nAll recommended medicines\n");
                        DoublyLinkedList<Integer> temp = record.getMedicineID();
                        for (int j = 0; j < temp.size(); j++) {
                            Medicine medicine = getMedicineData(temp.getAtIndex(j));
                            if (medicine != null) {
                                recordTextArea.append(medicine.getName() + "\n");
                            } else {
                                recordTextArea.append("Medicine ID " + temp.getAtIndex(j) + " not found.\n");
                            }
                        }

                        recordTextArea.append("\nDate: " + record.getDate());
                        recordTextArea.append("\nRecommendation = " + record.getRecomendations());
                        recordTextArea.append("\n-------------------------------------------------------------\n");
                    }
                }

                // Nếu không tìm thấy bất kỳ bản ghi nào
                if (!recordFound) {
                    JOptionPane.showMessageDialog(null, "No record found for the entered ID!");
                }
            }
        }
    });


    getRecordButton.setFont(new Font("Tahoma", Font.BOLD, 13));
    getRecordButton.setBackground(Color.WHITE);
    getRecordButton.setForeground(new Color(0, 153, 153));
    getRecordButton.setBounds(462, 448, 117, 25);
    frame.getContentPane().add(getRecordButton);

    JLabel enterIDLabel = new JLabel("Enter ID for search:");
    enterIDLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
    enterIDLabel.setBounds(283, 89, 160, 25);
    frame.getContentPane().add(enterIDLabel);

    enterIDTextField = new JTextField();
    enterIDTextField.setColumns(10);
    enterIDTextField.setBounds(453, 90, 276, 25);
    frame.getContentPane().add(enterIDTextField);
}

public void setVisible(boolean b) {
    frame.setVisible(true);
}
}
