import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class ViewMember{
private JFrame mainFrame;
private JPanel mainPanel;
private JLabel firstNameLabel, lastNameLabel, emailLabel, yearLabel, 
	membershipTypeLabel, expirationLabel,discountsLabel, IOULabel, recieveEmailLabel;

private JTextField firstNameTextField, lastNameTextField, 
	emailTextField, expirationTextField, discountsTextField, IOUTextField;

private JButton workHistoryButton, okButton;

private JTextField currentYearBox, membershipTypeBox;
private JCheckBox recieveEmailCheckBox;



	public ViewMember(){
		mainFrame = new JFrame("View Member");
		mainFrame.setBounds(275, 200, 450, 310);
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
		
		expirationLabel = new JLabel("Expiration Date: ");
		expirationLabel.setBounds(5, 140, 120, 20);
		
		discountsLabel = new JLabel("Discounts Available: ");
		discountsLabel.setBounds(5, 180, 150, 20);
		
		discountsTextField = new JTextField();
		discountsTextField.setBounds(130, 180, 80, 25);
		discountsTextField.setEditable(false);
		
		IOULabel = new JLabel("IOU Amount: ");
		IOULabel.setBounds(240, 180, 80, 20);
		
		IOUTextField = new JTextField();
		IOUTextField.setBounds(320, 180, 80, 25);
		IOUTextField.setEditable(false);
		
		recieveEmailCheckBox = new JCheckBox();
		recieveEmailCheckBox.setBounds(5, 216, 25, 28);
		
		recieveEmailLabel = new JLabel("Recieve E-Mails");
		recieveEmailLabel.setBounds(30, 220, 150, 20);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(80, 5, 350, 25);
		firstNameTextField.setEditable(false);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(80, 35, 350, 25);
		lastNameTextField.setEditable(false);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(80, 65, 350, 25);
		emailTextField.setEditable(false);
		
		currentYearBox = new JTextField();
		currentYearBox.setBounds(85, 100, 100, 25);
		currentYearBox.setEditable(false);
		
		membershipTypeBox = new JTextField();
		membershipTypeBox.setBounds(310, 100, 100, 25);
		membershipTypeBox.setEditable(false);
		
		expirationTextField = new JTextField();
		expirationTextField.setBounds(100, 140, 70, 25);
		expirationTextField.setEditable(false);
		expirationTextField.setText("12/12/2011");
		
		workHistoryButton = new JButton("Display Work History");
		workHistoryButton.setBounds(230, 140, 180, 25);
		
		
		okButton = new JButton("OK");
		okButton.setBounds(340, 240, 80, 30);
		
		
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
		mainPanel.add(expirationLabel);
		mainPanel.add(expirationTextField);
		mainPanel.add(workHistoryButton);
		mainPanel.add(discountsLabel);
		mainPanel.add(discountsTextField);
		mainPanel.add(IOULabel);
		mainPanel.add(IOUTextField);
	//	mainPanel.add(recieveEmailCheckBox);
	//	mainPanel.add(recieveEmailLabel);
		mainPanel.add(okButton);
		
		mainPanel.setVisible(true);
		mainFrame.add(mainPanel, BorderLayout.CENTER);
		
		
	}
	
	class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(okButton)){
				mainFrame.dispose();
			}else if(e.getSource().equals(workHistoryButton)){
				//TODO: Implement methods for OKButton
				
			}else{
				System.exit(0);
			}
		}
		
		
	}
	
	
}




