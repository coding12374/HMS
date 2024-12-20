package hospital;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

public class HomePage {

    private JFrame frame;

    /**
     * Launch the GUI application.
     */
    public static void launchGUI() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HomePage window = new HomePage();
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
    public HomePage() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Homepage");
        frame.setBounds(100, 100, 778, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBounds(0, 0, 770, 83);
        frame.getContentPane().add(titlePanel);
        titlePanel.setLayout(null);

        JLabel homepageLabel = new JLabel("Hospital Management System");
        homepageLabel.setForeground(new Color(0, 153, 204));
        homepageLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        homepageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        homepageLabel.setBounds(111, 25, 555, 47);
        titlePanel.add(homepageLabel);

        // Logo Image
        JLabel logoImage = new JLabel(loadImage("hospitalLogo.jpg", 80, 80));
        logoImage.setBounds(26, 0, 89, 83);
        titlePanel.add(logoImage);

        // Display Panel
        JPanel displayPanel = new JPanel();
        displayPanel.setBackground(Color.WHITE);
        displayPanel.setBounds(0, 83, 770, 598);
        frame.getContentPane().add(displayPanel);
        displayPanel.setLayout(null);

        // Add Panels for Patients, Reports, Medicines, Earnings, CheckUp, Power
        addFeaturePanel(displayPanel, "patient.png", "Patient", 63, 24, new Patients());
        addFeaturePanel(displayPanel, "report.jpg", "Report", 268, 24, new Reports());
        addFeaturePanel(displayPanel, "medicine.jpg", "Medicines", 63, 218, new Medicines());
        addFeaturePanel(displayPanel, "money.jpg", "Earning", 268, 218, new Earnings());
        addFeaturePanel(displayPanel, "checkup.png", "Check Up", 63, 404, new CheckUp());
        addFeaturePanel(displayPanel, "powerButton.png", "Power Off", 268, 404, new Login());

        // Doctor Image
        JLabel doctorImage = new JLabel(loadImage("doctors.jpg", 460, 543));
        doctorImage.setBounds(420, 24, 353, 516);
        displayPanel.add(doctorImage);
    }

    // Utility Method to Load Images
    private ImageIcon loadImage(String fileName, int width, int height) {
        String path = "src/hospital/" + fileName; // Relative Path
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    // Utility Method to Create Feature Panels
    private void addFeaturePanel(JPanel parentPanel, String imageName, String labelText, int x, int y, JFrame targetFrame) {
        JPanel featurePanel = new JPanel();
        featurePanel.setBounds(x, y, 142, 136);
        featurePanel.setBorder(new LineBorder(new Color(0, 153, 204), 3, true));
        featurePanel.setBackground(Color.WHITE);
        featurePanel.setLayout(null);
        parentPanel.add(featurePanel);

        featurePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                featurePanel.setBackground(Color.decode("#deedee"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                featurePanel.setBackground(Color.WHITE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                targetFrame.setVisible(true);
                frame.dispose();
            }
        });

        JLabel featureImage = new JLabel(loadImage(imageName, 100, 100));
        featureImage.setBounds(24, 11, 92, 85);
        featurePanel.add(featureImage);

        JLabel featureLabel = new JLabel(labelText);
        featureLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        featureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        featureLabel.setBounds(24, 107, 92, 14);
        featurePanel.add(featureLabel);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
