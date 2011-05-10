import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

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
private MainFrame parentWindow;

	public ViewMember(MainFrame parentWindow, Member m){
		this.parentWindow = parentWindow;

		parentWindow.setEnabled(false);
		mainFrame = new JFrame("View Member");
		mainFrame.setBounds(275, 200, 450, 310);
		//mainFrame.setFocusableWindowState(false);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				ViewMember.this.parentWindow.setEnabled(true);
				ViewMember.this.parentWindow.requestFocus();
			}
		});
		
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setResizable(false);
		
		
		
		addPanel(m);
		mainFrame.setVisible(true);
		
		mainFrame.validate();
		
		
		
	}
	
	/*
	 * Date lastSignupDate = m.getLastSignupDate();
	   int membershipLength = m.getMembershipLength();
	   long expirationDate = 0;
	 */
	
	@SuppressWarnings("deprecation")
	public void handleExpiration(Date dIn, int iIn, JTextField jtfIn)
	{
		jtfIn.setText((dIn.getMonth()+1) + "/" + dIn.getDate() + "/" + (dIn.getYear()+1900));
							
	}
	
	private void addPanel(Member m){
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		firstNameLabel = new JLabel("First Name: ");
		lastNameLabel = new JLabel("Last Name: ");
		emailLabel = new JLabel("E-Mail: ");
		yearLabel = new JLabel("Current Year: ");
		membershipTypeLabel = new JLabel("Membership Type: ");
		expirationLabel = new JLabel("Expiration Date: ");
		discountsLabel = new JLabel("Discounts Available: ");
		
		discountsTextField = new JTextField();
		discountsTextField.setEditable(false);
		discountsTextField.setText(Integer.toString(m.getAvailableDiscounts()));
		
		IOULabel = new JLabel("IOU Amount: $");
		
		DecimalFormat df = new DecimalFormat("0.00");
		IOUTextField = new JTextField();
		IOUTextField.setEditable(false);
		IOUTextField.setText(df.format(m.getIouAmount()));
		
		firstNameTextField = new JTextField();
		firstNameTextField.setEditable(false);
		firstNameTextField.setText(m.getFirstName());
		
		lastNameTextField = new JTextField();
		lastNameTextField.setEditable(false);
		lastNameTextField.setText(m.getLastName());
		
		emailTextField = new JTextField();
		emailTextField.setEditable(false);
		emailTextField.setText(m.getEmailAddress());
		
		currentYearBox = new JTextField();
		currentYearBox.setEditable(false);
		String currentYear = YearsInSchool.class.getEnumConstants()[m.getYearsInSchool()].getStrVal();
		currentYearBox.setText(currentYear);
		
		membershipTypeBox = new JTextField();
		membershipTypeBox.setEditable(false);
		String membershipType = MembershipTypes.class.getEnumConstants()[m.getMembershipType()].getStrVal();
		membershipTypeBox.setText(membershipType);
		
		expirationTextField = new JTextField();
		expirationTextField.setEditable(false);
				
		handleExpiration(m.getExpirationDate(), 3, expirationTextField);

		workHistoryButton = new JButton("Display Work History");
		
		okButton = new JButton("OK");
		okButton.addActionListener(new ButtonListener());

		createPanel();		
	}
	
	private void createPanel()
	{
		GridBagLayout gbl = new GridBagLayout();
		mainPanel = new JPanel(gbl);	
		mainPanel.setVisible(true);
		mainPanel.validate();
		GridBagConstraints c = new GridBagConstraints();	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.insets = new Insets(2,2,2,2);
		mainPanel.add(firstNameLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 3;
		mainPanel.add(firstNameTextField, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		mainPanel.add(lastNameLabel, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 3;
		mainPanel.add(lastNameTextField, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		mainPanel.add(emailLabel, c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 3;
		mainPanel.add(emailTextField, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		mainPanel.add(yearLabel, c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.ipadx = 100;
		mainPanel.add(currentYearBox, c);
		
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		c.ipadx = 0;
		c.anchor = GridBagConstraints.LINE_END;
		mainPanel.add(membershipTypeLabel, c);
		
		c.gridx = 3;
		c.gridy = 3;
		c.gridwidth = 1;
		c.ipadx = 50;
		c.anchor = GridBagConstraints.LINE_START;
		mainPanel.add(membershipTypeBox, c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.ipadx = 0;
		mainPanel.add(expirationLabel, c);
		
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 1;
		mainPanel.add(expirationTextField, c);
		
		c.gridx = 2;
		c.gridy = 5;
		c.gridwidth = 2;
		mainPanel.add(workHistoryButton, c);
		
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		mainPanel.add(discountsLabel, c);
		
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 1;
		mainPanel.add(discountsTextField, c);
		
		c.gridx = 2;
		c.gridy = 6;
		c.gridwidth = 1;
		mainPanel.add(IOULabel, c);
		
		c.gridx = 3;
		c.gridy = 6;
		c.gridwidth = 1;
		mainPanel.add(IOUTextField, c);
		
		c.gridx = 3;
		c.gridy = 7;
		c.gridwidth = 1;
		mainPanel.add(okButton, c);
		
		mainFrame.add(mainPanel, BorderLayout.NORTH);
		mainFrame.pack();
		mainFrame.setLocation(350, 150);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		mainFrame.validate();
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(okButton)){
				mainFrame.dispose();
				//parentWindow.reenableButtons();
				parentWindow.setEnabled(true);
				parentWindow.requestFocus();
			}else if(e.getSource().equals(workHistoryButton)){
				//TODO: Implement methods for OKButton
				
			}else{
				System.exit(0);
			}
		}
		
		
	}
	
	
}




