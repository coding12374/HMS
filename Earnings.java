package hospital;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

public class Earnings extends JFrame {

    private JFrame frame;
    private JLabel earningByFeeNo;
    private JLabel earningByMedicinesNo;
    private JLabel netEarningNo;

    private HashTable<Integer, Medicine> medicineTable = new HashTable<>(100);
    private DoublyLinkedList<BillingInfo> recordList = new DoublyLinkedList<>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Earnings window = new Earnings();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     */
    public Earnings() {
        initialize();
        readAllMData();
        readAllRData();
        getAllEarnings();
    }

    /**
     * Read all medicine data from file.
     */
    public void readAllMData() {
        try {
            File file = new File("src/hospital/mdata.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] currentData = data.split(";");
                Medicine medicine = new Medicine(
                    Integer.parseInt(currentData[0]),
                    currentData[1],
                    Integer.parseInt(currentData[2]),
                    Integer.parseInt(currentData[3]),
                    Integer.parseInt(currentData[4]),
                    currentData[5]
                );
                medicineTable.insert(medicine.getId(), medicine); // Thêm vào HashTable
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
 * Calculate the profit from medicines based on ID.
 */
    public int getMedicineProfit(int id, int quantity) {
        Medicine medicine = medicineTable.get(id); 
        if (medicine != null && quantity > 0) {
            int profitPerUnit = medicine.getSellingPrice() - medicine.getBuyingPrice();
            return profitPerUnit * quantity; 
        }
        return 0; 
    }



/**
 * Calculate and display all earnings.
 */
    public void getAllEarnings() {
        int earningsByFee = 0;
        int earningsByMedicines = 0;

        for (int i = 0; i < recordList.size(); i++) {
            BillingInfo record = recordList.getAtIndex(i);
            earningsByFee += record.getFee();

            DoublyLinkedList<Integer> tempMedicineIDs = record.getMedicineID();
            DoublyLinkedList<Integer> tempQuantities = record.getMedicineQuantities();

            for (int j = 0; j < tempMedicineIDs.size(); j++) {
                int medicineID = tempMedicineIDs.getAtIndex(j);
                int quantity = tempQuantities.getAtIndex(j);

                if (medicineTable.get(medicineID) != null) {
                    int profit = (medicineTable.get(medicineID).getSellingPrice() - 
                                  medicineTable.get(medicineID).getBuyingPrice()) * quantity;
                    earningsByMedicines += profit;
                }
            }
        }

        earningByFeeNo.setText(String.valueOf(earningsByFee));
        earningByMedicinesNo.setText(String.valueOf(earningsByMedicines));
        netEarningNo.setText(String.valueOf(earningsByFee + earningsByMedicines));
    }

/**
 * Initialize the GUI.
 */
private void initialize() {
    frame = new JFrame();
    frame.setTitle("Earnings");
    frame.setBounds(100, 100, 625, 352);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JPanel titlePanel = new JPanel();
    titlePanel.setBackground(Color.WHITE);
    titlePanel.setBounds(0, 0, 614, 94);
    frame.getContentPane().add(titlePanel);
    titlePanel.setLayout(null);

    JLabel earningsMenuLabel = new JLabel("Earnings Menu");
    earningsMenuLabel.setForeground(new Color(0, 153, 204));
    earningsMenuLabel.setFont(new Font("iCiel Cadena", Font.PLAIN, 24));
    earningsMenuLabel.setBounds(105, 28, 172, 40);
    titlePanel.add(earningsMenuLabel);

    JButton homePageButton = new JButton("Homepage");
    homePageButton.addActionListener(e -> {
        HomePage myHomePage = new HomePage();
        myHomePage.setVisible(true);
        frame.dispose();
    });
    homePageButton.setForeground(new Color(51, 153, 153));
    homePageButton.setFont(new Font("Tahoma", Font.BOLD, 14));
    homePageButton.setBackground(Color.WHITE);
    homePageButton.setBounds(479, 13, 121, 25);
    titlePanel.add(homePageButton);

    ImageIcon moneyIcon = new ImageIcon("src/hospital/money.jpg");
    Image moneyImg = moneyIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
    JLabel reportImage = new JLabel(new ImageIcon(moneyImg));
    reportImage.setBounds(12, 13, 92, 76);
    titlePanel.add(reportImage);

    JPanel displayPanel = new JPanel();
    displayPanel.setBackground(Color.WHITE);
    displayPanel.setBounds(0, 94, 614, 219);
    frame.getContentPane().add(displayPanel);
    displayPanel.setLayout(null);

    JLabel earningByFeeLabel = new JLabel("Earning by fee:");
    earningByFeeLabel.setForeground(new Color(0, 153, 204));
    earningByFeeLabel.setFont(new Font("iCiel Cadena", Font.PLAIN, 24));
    earningByFeeLabel.setBounds(32, 23, 172, 40);
    displayPanel.add(earningByFeeLabel);

    JLabel earningByMedicinesLabel = new JLabel("Earning by medicines:");
    earningByMedicinesLabel.setForeground(new Color(0, 153, 153));
    earningByMedicinesLabel.setFont(new Font("iCiel Cadena", Font.PLAIN, 24));
    earningByMedicinesLabel.setBounds(32, 94, 241, 40);
    displayPanel.add(earningByMedicinesLabel);

    JLabel netEarningLabel = new JLabel("Net earning:");
    netEarningLabel.setForeground(new Color(204, 51, 0));
    netEarningLabel.setFont(new Font("iCiel Cadena", Font.PLAIN, 24));
    netEarningLabel.setBounds(32, 159, 172, 40);
    displayPanel.add(netEarningLabel);

    earningByFeeNo = new JLabel("00");
    earningByFeeNo.setForeground(Color.BLACK);
    earningByFeeNo.setFont(new Font("iCiel Cadena", Font.PLAIN, 24));
    earningByFeeNo.setBounds(285, 23, 269, 40);
    displayPanel.add(earningByFeeNo);

    earningByMedicinesNo = new JLabel("00");
    earningByMedicinesNo.setForeground(Color.BLACK);
    earningByMedicinesNo.setFont(new Font("iCiel Cadena", Font.PLAIN, 24));
    earningByMedicinesNo.setBounds(285, 94, 269, 40);
    displayPanel.add(earningByMedicinesNo);

    netEarningNo = new JLabel("00");
    netEarningNo.setForeground(Color.BLACK);
    netEarningNo.setFont(new Font("iCiel Cadena", Font.PLAIN, 24));
    netEarningNo.setBounds(285, 159, 269, 40);
    displayPanel.add(netEarningNo);
}

public void setVisible(boolean b) {
    frame.setVisible(b);
}
}
