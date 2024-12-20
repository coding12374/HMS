package hospital;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JFrame frame;
	private JPasswordField passwordField;
	private String userPass =  "admin";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	    frame = new JFrame();
	    frame.setTitle("Login");
	    frame.getContentPane().setBackground(new Color(255, 255, 255));
	    frame.getContentPane().setLayout(null);
	    
	    JLabel titleLabel = new JLabel("Hospital Management System");
	    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    titleLabel.setFont(new Font("iCiel Cadena", Font.PLAIN, 30));
	    titleLabel.setBounds(50, 180, 421, 29);
	    frame.getContentPane().add(titleLabel);
	    
	    // Resize and add logo image
	    ImageIcon logoIcon = new ImageIcon("src/hospital/doctor.png");
	    Image logoImg = logoIcon.getImage().getScaledInstance(125, 200, Image.SCALE_SMOOTH);
	    JLabel logoImage = new JLabel(new ImageIcon(logoImg));
	    logoImage.setBounds(178, 5, 160, 173); // Adjust bounds to match the resized image
	    frame.getContentPane().add(logoImage);
	    
	    // Resize and add password image
	    ImageIcon passwordIcon = new ImageIcon("src/hospital/key.png");
	    Image passwordImg = passwordIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	    JLabel passwordImage = new JLabel(new ImageIcon(passwordImg));
	    passwordImage.setBounds(110, 241, 30, 20); 
	    frame.getContentPane().add(passwordImage);
	    
	    passwordField = new JPasswordField();
	    passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    passwordField.setBounds(144, 241, 242, 20);
	    frame.getContentPane().add(passwordField);
	    
	    JLabel loginLabel = new JLabel("Login");
	    loginLabel.setFont(new Font("iCiel Cadena", Font.PLAIN, 24));
	    loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    loginLabel.setBounds(211, 208, 96, 29);
	    frame.getContentPane().add(loginLabel);
	    
	    JButton loginButton = new JButton("Login");
	    loginButton.setForeground(new Color(51, 153, 153));
	    loginButton.setFont(new Font("Tahoma", Font.BOLD, 16));
	    loginButton.addActionListener(new ActionListener() {
	    	@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
	    		if(userPass.equals(passwordField.getText())){
	    			HomePage myHomePage = new HomePage();
	    			frame.dispose();
	    			myHomePage.setVisible(true);
	    		} else {
	    			JOptionPane.showMessageDialog(null, "Login failed");
	    		}
	    	}
	    });
	    loginButton.setBackground(Color.WHITE);
	    loginButton.setBounds(174, 274, 80, 23);
	    frame.getContentPane().add(loginButton);
	    
	    JButton cancelButton = new JButton("Cancel");
	    cancelButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frame.dispose();
	    	}
	    });
	    cancelButton.setForeground(new Color(51, 153, 153));
	    cancelButton.setFont(new Font("Tahoma", Font.BOLD, 16));
	    cancelButton.setBackground(Color.WHITE);
	    cancelButton.setBounds(268, 274, 96, 23);
	    frame.getContentPane().add(cancelButton);
	    frame.setBounds(100, 100, 539, 353);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

}
