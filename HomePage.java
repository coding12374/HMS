package hospital;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.WHITE);
		titlePanel.setBounds(0, 0, 770, 83);
		frame.getContentPane().add(titlePanel);
		titlePanel.setLayout(null);
		
		JLabel homepageLabel = new JLabel("Hospital Management System");
		homepageLabel.setForeground(new Color(0, 153, 204));
		homepageLabel.setBounds(111, 25, 555, 47);
		titlePanel.add(homepageLabel);
		homepageLabel.setFont(new Font("iCiel Cadena", Font.PLAIN, 40));
		homepageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // Load and scale the logo
        ImageIcon logoIcon = new ImageIcon("src/hospital/hospitalLogo.jpg");
        Image logoImg = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoImage = new JLabel(new ImageIcon(logoImg));
        logoImage.setBounds(26, 0, 89, 83);
        titlePanel.add(logoImage);
        
        JPanel displayPanel = new JPanel();
        displayPanel.setBackground(Color.WHITE);
        displayPanel.setBounds(0, 83, 770, 598);
        frame.getContentPane().add(displayPanel);
        displayPanel.setLayout(null);
        
        JPanel patientPanel = new JPanel();
        patientPanel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		patientPanel.setBackground(java.awt.Color.decode("#deedee"));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		patientPanel.setBackground(java.awt.Color.WHITE);
        	}
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Patients myPatients = new Patients();
        		myPatients.setVisible(true);
        		frame.dispose();
        	}
        });
        patientPanel.setBackground(Color.WHITE);
        patientPanel.setBorder(new LineBorder(new Color(0, 153, 204), 3, true));
        patientPanel.setBounds(63, 24, 142, 136);
        displayPanel.add(patientPanel);
        patientPanel.setLayout(null);
        
        // Load and scale the patient image
        ImageIcon patientIcon = new ImageIcon("src/hospital/patient.png");
        Image patientImg = patientIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel patientImage = new JLabel(new ImageIcon(patientImg));
        patientImage.setBounds(24, 11, 92, 85); // Adjusted dimensions to match scaled image
        patientPanel.add(patientImage);
        
        JLabel patientLabel = new JLabel("Patient");
        patientLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        patientLabel.setHorizontalAlignment(SwingConstants.CENTER);
        patientLabel.setBounds(48, 107, 53, 14);
        patientPanel.add(patientLabel);
        
        JPanel reportPanel = new JPanel();
        reportPanel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		reportPanel.setBackground(java.awt.Color.decode("#deedee"));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		reportPanel.setBackground(java.awt.Color.WHITE);
        	}
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Reports myReports = new Reports();
        		myReports.setVisible(true);
        		frame.dispose();
        	}
        });
        reportPanel.setLayout(null);
        reportPanel.setBorder(new LineBorder(new Color(0, 153, 204), 3, true));
        reportPanel.setBackground(Color.WHITE);
        reportPanel.setBounds(268, 24, 142, 136);
        displayPanel.add(reportPanel);
        
        // Load and display the report image
        ImageIcon reportIcon = new ImageIcon("src/hospital/report.jpg");
        Image reportImg = reportIcon.getImage().getScaledInstance(92, 85, Image.SCALE_SMOOTH); // Scale the image
        JLabel reportImage = new JLabel(new ImageIcon(reportImg));
        reportImage.setBounds(24, 10, 92, 98);
        reportPanel.add(reportImage);
        
        JLabel reportLabel = new JLabel("Report");
        reportLabel.setHorizontalAlignment(SwingConstants.CENTER);
        reportLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        reportLabel.setBounds(50, 107, 46, 18);
        reportPanel.add(reportLabel);
        
        JPanel medicinePanel = new JPanel();
        medicinePanel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		medicinePanel.setBackground(java.awt.Color.decode("#deedee"));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		medicinePanel.setBackground(java.awt.Color.WHITE);
        	}
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Medicines myMedicines = new Medicines();
        		frame.dispose();
        		myMedicines.setVisible(true);
        	}
        });
        medicinePanel.setLayout(null);
        medicinePanel.setBorder(new LineBorder(new Color(0, 153, 204), 3, true));
        medicinePanel.setBackground(Color.WHITE);
        medicinePanel.setBounds(63, 218, 142, 136);
        displayPanel.add(medicinePanel);
        
        // Load and display the medicine image
        ImageIcon medicineIcon = new ImageIcon("src/hospital/medicine.jpg");
        Image medicineImg = medicineIcon.getImage().getScaledInstance(50, 90, Image.SCALE_SMOOTH); // Scale the image
        JLabel medicineImage = new JLabel(new ImageIcon(medicineImg));
        medicineImage.setBounds(24, 11, 92, 96);
        medicinePanel.add(medicineImage);
        
        JLabel medicineLabel = new JLabel("Medicines");
        medicineLabel.setHorizontalAlignment(SwingConstants.CENTER);
        medicineLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        medicineLabel.setBounds(25, 110, 92, 14);
        medicinePanel.add(medicineLabel);
        
        JPanel earningPanel = new JPanel();
        earningPanel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		earningPanel.setBackground(java.awt.Color.decode("#deedee"));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		earningPanel.setBackground(java.awt.Color.WHITE);
        	}
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Earnings myEarnings = new Earnings();
        		myEarnings.setVisible(true);
        		frame.dispose();
        	}
        });
        earningPanel.setLayout(null);
        earningPanel.setBorder(new LineBorder(new Color(0, 153, 204), 3, true));
        earningPanel.setBackground(Color.WHITE);
        earningPanel.setBounds(268, 218, 142, 136);
        displayPanel.add(earningPanel);
        
        // Load and display the earning image
        ImageIcon earningIcon = new ImageIcon("src/hospital/money.jpg");
        Image earningImg = earningIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Scale the image
        JLabel earningImage = new JLabel(new ImageIcon(earningImg));
        earningImage.setBounds(24, 11, 92, 85);
        earningPanel.add(earningImage);
        
        JLabel earningLabel = new JLabel("Earning");
        earningLabel.setHorizontalAlignment(SwingConstants.CENTER);
        earningLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        earningLabel.setBounds(26, 107, 92, 18);
        earningPanel.add(earningLabel);
        
        JPanel checkUpPanel = new JPanel();
        checkUpPanel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		checkUpPanel.setBackground(java.awt.Color.decode("#deedee"));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		checkUpPanel.setBackground(java.awt.Color.WHITE);
        	}
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		CheckUp myCheckUp = new CheckUp();
        		myCheckUp.setVisible(true);
        		frame.dispose();
        	}
        });
        checkUpPanel.setLayout(null);
        checkUpPanel.setBorder(new LineBorder(new Color(0, 153, 204), 3, true));
        checkUpPanel.setBackground(Color.WHITE);
        checkUpPanel.setBounds(63, 404, 142, 136);
        displayPanel.add(checkUpPanel);
        
        // Load and display the check-up image
        ImageIcon checkUpIcon = new ImageIcon("src/hospital/checkup.png");
        Image checkUpImg = checkUpIcon.getImage().getScaledInstance(92, 85, Image.SCALE_SMOOTH); // Scale the image
        JLabel checkUpImage = new JLabel(new ImageIcon(checkUpImg));
        checkUpImage.setBounds(10, 11, 122, 97);
        checkUpPanel.add(checkUpImage);
        
        JLabel checkUpLabel = new JLabel("Check up");
        checkUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        checkUpLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        checkUpLabel.setBounds(24, 107, 92, 18);
        checkUpPanel.add(checkUpLabel);
        
        JPanel powerPanel = new JPanel();
        powerPanel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		powerPanel.setBackground(java.awt.Color.decode("#deedee"));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		powerPanel.setBackground(java.awt.Color.WHITE);
        	}
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Login myLogin = new Login();
        		frame.dispose();
        		myLogin.setVisible(true);
        	}
        });
        powerPanel.setLayout(null);
        powerPanel.setBorder(new LineBorder(new Color(0, 153, 204), 3, true));
        powerPanel.setBackground(Color.WHITE);
        powerPanel.setBounds(268, 404, 142, 136);
        displayPanel.add(powerPanel);
        
        // Load and display the power button image
        ImageIcon powerIcon = new ImageIcon("src/hospital/powerButton.png");
        Image powerImg = powerIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Scale the image
        JLabel powerImage = new JLabel(new ImageIcon(powerImg));
        powerImage.setBounds(35, 11, 73, 85);
        powerPanel.add(powerImage);
        
        JLabel powerLabel = new JLabel("Power Off");
        powerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        powerLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        powerLabel.setBounds(24, 107, 92, 14);
        powerPanel.add(powerLabel);
        
        // Load and display the doctor image
        ImageIcon doctorIcon = new ImageIcon("src/hospital/doctors.jpg");
        Image doctorImg = doctorIcon.getImage().getScaledInstance(460, 543, Image.SCALE_SMOOTH); // Scale to match the bounds
        JLabel doctorImage = new JLabel(new ImageIcon(doctorImg));
        doctorImage.setBounds(420, 24, 353, 516);
        displayPanel.add(doctorImage);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
