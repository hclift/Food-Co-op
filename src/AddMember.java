import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class AddMember{
private JFrame mainFrame;
private JPanel mainPanel;
private JLabel firstNameLabel, lastNameLabel, emailLabel, yearLabel, 
	membershipTypeLabel, expirationLabel,discountsLabel, IOULabel, membershipDurationLabel;

private JTextField firstNameTextField, lastNameTextField, 
	emailTextField, expirationTextField, discountsTextField, IOUTextField;

private JButton cancelButton, okButton;
private JComboBox addSemYearComboBox;

private JComboBox currentYearBox, membershipTypeBox;



	public AddMember(){
		mainFrame = new JFrame("Add Member");
		mainFrame.setBounds(275, 150, 450, 250);
		//mainFrame.setFocusableWindowState(false);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setResizable(false);
		
		
		
		addPanel();
		mainFrame.setVisible(true);
		
		mainFrame.validate();
		
		
		
	}
	
	private void addPanel(){
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		firstNameLabel = new JLabel("First Name: ");
		firstNameLabel.setBounds(5, 5, 80, 20);
		
		lastNameLabel = new JLabel("Last Name: ");
		lastNameLabel.setBounds(5, 35, 80, 20);
		
		emailLabel = new JLabel("E-Mail: ");
		emailLabel.setBounds(5, 63, 80, 20);
		
		yearLabel = new JLabel("Current Year: ");
		yearLabel.setBounds(5, 100, 80, 20);
		
		membershipTypeLabel = new JLabel("Membership Type: ");
		membershipTypeLabel.setBounds(200, 100, 150, 20);
		
		//expirationLabel = new JLabel("Expiration Date: ");
		//expirationLabel.setBounds(5, 140, 120, 20);
		
		discountsLabel = new JLabel("Discounts Available: ");
		discountsLabel.setBounds(5, 180, 150, 20);
		
		discountsTextField = new JTextField();
		discountsTextField.setBounds(130, 180, 80, 25);
		discountsTextField.setEditable(false);
		
		IOULabel = new JLabel("IOU Amount: ");
		IOULabel.setBounds(240, 180, 80, 20);
		
		IOUTextField = new JTextField();
		IOUTextField.setBounds(320, 180, 80, 25);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(80, 5, 350, 25);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(80, 35, 350, 25);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(80, 65, 350, 25);
		
		currentYearBox = new JComboBox();
		currentYearBox.setBounds(85, 100, 100, 25);
		currentYearBox.addItem("Freshman");
		currentYearBox.addItem("Sophmore");
		currentYearBox.addItem("Junior");
		currentYearBox.addItem("Senior");
		
		membershipTypeBox = new JComboBox();
		membershipTypeBox.setBounds(310, 100, 120, 25);
		membershipTypeBox.addItem("Ordinary");
		membershipTypeBox.addItem("Working Member");
		membershipTypeBox.addItem("Core Member");
		membershipTypeBox.addItem("Coordinator");
		
		/*
		expirationTextField = new JTextField();
		expirationTextField.setBounds(100, 140, 70, 25);
		expirationTextField.setEditable(false);
		expirationTextField.setText("12/12/2011");
		*/
		membershipDurationLabel = new JLabel("Member Duration: ");
		membershipDurationLabel.setBounds(205, 140, 120, 20);
		
		addSemYearComboBox = new JComboBox();
		addSemYearComboBox.setBounds(310, 140, 120, 25);
		addSemYearComboBox.addItem("Semester");
		addSemYearComboBox.addItem("Year");
		
		
		okButton = new JButton("OK");
		okButton.setBounds(260, 180, 80, 30);
		okButton.addActionListener(new ButtonListener());
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(350, 180, 80, 30);
		cancelButton.addActionListener(new ButtonListener());
		
		okButton.addActionListener(new ButtonListener());
		
		
		mainPanel.add(firstNameLabel);
		mainPanel.add(firstNameTextField);
		mainPanel.add(lastNameLabel);
		mainPanel.add(lastNameTextField);
		mainPanel.add(emailLabel);
		mainPanel.add(emailTextField);
		mainPanel.add(yearLabel);
		mainPanel.add(currentYearBox);
		mainPanel.add(membershipTypeLabel);
		mainPanel.add(membershipTypeBox);
		//mainPanel.add(expirationLabel);
		//mainPanel.add(expirationTextField);
		mainPanel.add(addSemYearComboBox);
		mainPanel.add(membershipDurationLabel);
		mainPanel.add(cancelButton);
		mainPanel.add(okButton);
		
		
		mainPanel.setVisible(true);
		mainFrame.add(mainPanel, BorderLayout.CENTER);
		
		
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(cancelButton)){
				mainFrame.dispose();
			}else if(e.getSource().equals(okButton)){
				//TODO: Implement methods for OKButton
				
			}else{
				System.exit(0);
			}
		}
		
		
	}
	
	
}




