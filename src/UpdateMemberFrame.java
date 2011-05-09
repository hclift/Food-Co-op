/*
 * UpdateMemberFrame.java
 * This is the GUI class for the Update Member window of the program.
 * Interacts with the controller which calls the model.
 * 
 */

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateMemberFrame {
	// main frame of update member; changes on creation and closing
	private JFrame mainFrame;

	// main panel of update member's main frame; changes on creation (addPanel)
	private JPanel mainPanel;
	private JLabel firstNameLabel, lastNameLabel, emailLabel, yearLabel, 
	membershipTypeLabel, expirationLabel,discountsLabel, IOULabel, activeMemberLabel;

	// text fields in main panel; self-documenting for data it receives
	private JTextField firstNameTextField, lastNameTextField, 
	emailTextField, expirationTextField, discountsTextField, IOUTextField;

	// buttons in main panel;  self-documenting for their purposes
	private JButton addSemesterButton, addYearButton, addIOUButton, subtractIOUButton, 
	applyDiscountButton, saveButton, cancelButton;

	// combo boxes in main panel; self-documenting for data it receives
	private JComboBox currentYearBox, membershipTypeBox;

	// check box in main panel; for whether member wants to receive emails
	//private JCheckBox recieveEmailCheckBox;
	// check box in main panel; for whether member is active
	private JCheckBox activeMemberCheckBox;

	// holds instance of controller and member
	private Controller controller;
	private Member member;


	// holds member's IOU amount; changes when discount applied or IOU added
	double tempIOU;
	int	tempAvailDiscounts;
	Date lastSignupDate;
	
	
	MainFrame parentWindow;
	
	/**
	 * Explicit value constructor for UpdateMemberFrame.
	 * Takes in controller and member as parameters.
	 * @param controller, member
	 **/
	public UpdateMemberFrame(MainFrame parentWindow, Controller controller, Member member)
	{
		this.parentWindow = parentWindow;
		parentWindow.setEnabled(false);
		this.member = member;
		this.controller = controller;
		tempIOU = member.getIouAmount();
		tempAvailDiscounts = member.getAvailableDiscounts();
		mainFrame = new JFrame("Update Member");
		//mainFrame.setFocusableWindowState(false);
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				if (verifyClosing())
				{
					mainFrame.dispose();
					UpdateMemberFrame.this.parentWindow.setEnabled(true);
					UpdateMemberFrame.this.parentWindow.requestFocus();
				}
			}
		});
		
		addPanel(member);
		
		if(!member.getActive())
		{
			setButtons(false);
		}
		
	}
	
	
	/**
	 * Sets the visibility of the buttons.
	 * Takes in a boolean which determines whether buttons are visible.
	 * @param visibility
	 **/
	private void setButtons(boolean enabled)
	{
		if (member.canHaveIou())
		{
			addIOUButton.setEnabled(enabled);
			
			if (tempIOU > 0)
				subtractIOUButton.setEnabled(enabled);
		}
		else
		{
			addIOUButton.setEnabled(false);
			subtractIOUButton.setEnabled(false);
		}
		
		addYearButton.setEnabled(enabled);
		addSemesterButton.setEnabled(enabled);
		
		if (tempAvailDiscounts > 0)
		{
			applyDiscountButton.setEnabled(enabled);
		}
		else
		{
			applyDiscountButton.setEnabled(false);
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public void handleExpiration(Date dIn, int iIn, int membershipLength, JTextField jtfIn)
	{
		int month = dIn.getMonth();
		int year = dIn.getYear();
		int day = dIn.getDate();
		
		Calendar c = Calendar.getInstance();
		c.setTime(dIn);
		
		//FIRST CALCULATE NEW START DATE
		//0 means add semester
		if (iIn == 0) 
		{
			switch(month) 
			{
				case 0: c.add(Calendar.MONTH, 8); break;	//FEB, MAR, APR, MAY, SEP
				case 1: c.add(Calendar.MONTH, 8); break;	//MAR, APR, MAY, SEP, OCT
				case 2: c.add(Calendar.MONTH, 8); break;	//APR, MAY, SEP, OCT, NOV
				case 3: c.add(Calendar.MONTH, 8); break;	//MAY, SEP, OCT, NOV, DEC
				case 4: c.add(Calendar.MONTH, 8); break;	//SEP, OCT, NOV, DEC, JAN
				case 5: c.add(Calendar.MONTH, 7); break;	//SEP, OCT, NOV, DEC, JAN
				case 6: c.add(Calendar.MONTH, 6); break;	//SEP, OCT, NOV, DEC, JAN
				case 7: c.add(Calendar.MONTH, 5); break;	//SEP, OCT, NOV, DEC, JAN
				case 8: c.add(Calendar.MONTH, 5); break;	//OCT, NOV, DEC, JAN, FEB
				case 9: c.add(Calendar.MONTH, 5); break;	//NOV, DEC, JAN, FEB, MAR
				case 10: c.add(Calendar.MONTH, 5); break;	//DEC, JAN, FEB, MAR, APR
				case 11: c.add(Calendar.MONTH, 5); break;	//JAN, FEB, MAR, APR, MAY
			}
		}
		//1 means add year
		else if (iIn == 1)
		{
			c.add(Calendar.MONTH, 12);
		}
				
		Date temp = c.getTime();
		
		dIn.setMonth(temp.getMonth());
		dIn.setDate(temp.getDate());
		dIn.setYear(temp.getYear());
		
		//Update member
		member.setLastSignupDate(dIn);
		
		
		//Now add the membership length before displaying
		if (membershipLength == 0)
		{
			c.add(Calendar.MONTH, 6);
		}
		else
		{
			c.add(Calendar.MONTH, 12);
		}
		
		temp = c.getTime();		
		jtfIn.setText((temp.getMonth()+1) + "/" + temp.getDate() + "/" + (temp.getYear()+1900));
							
	}
	
	/**
	 * Adds the main panel into the main frame of update member.
	 * Creates all labels, text fields, buttons, and boxes in main panel.
	 **/
	private void addPanel(Member m)
	{		
		firstNameLabel = new JLabel("First Name: ");
		
		lastNameLabel = new JLabel("Last Name: ");
		
		emailLabel = new JLabel("E-Mail: ");
		
		yearLabel = new JLabel("Current Year: ");
		
		membershipTypeLabel = new JLabel("Membership Type: ");
		
		expirationLabel = new JLabel("Expiration Date: ");
		
		discountsLabel = new JLabel("Discounts Available: ");
		
		discountsTextField = new JTextField(""+member.getAvailableDiscounts());
		discountsTextField.setEditable(false);
		
		IOULabel = new JLabel("IOU Amount: $");
		
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		IOUTextField = new JTextField(decimalFormat.format(member.getIouAmount()));
		IOUTextField.setEditable(false);
		
		activeMemberCheckBox = new JCheckBox();
		activeMemberCheckBox.setSelected(member.getActive());
		activeMemberCheckBox.addActionListener(new ActiveMemberActionListener());
		
		
		activeMemberLabel = new JLabel("Active Member");
		
		firstNameTextField = new JTextField();
		firstNameTextField.setText(member.getFirstName());
		
		lastNameTextField = new JTextField();
		lastNameTextField.setText(member.getLastName());
		
		emailTextField = new JTextField();
		emailTextField.setText(member.getEmailAddress());
		
		currentYearBox = new JComboBox();
		for(YearsInSchool x: YearsInSchool.values()){
			currentYearBox.addItem(x.getStrVal());
		}
		currentYearBox.setSelectedIndex(member.getYearsInSchool());
		
		membershipTypeBox = new JComboBox();
		for(MembershipTypes y: MembershipTypes.values()){
			membershipTypeBox.addItem(y.getStrVal());
		}
		membershipTypeBox.setSelectedIndex(member.getMembershipType());
		membershipTypeBox.addActionListener(new MembershipTypeActionListener());
		
		expirationTextField = new JTextField();
		expirationTextField.setEditable(false);		
		lastSignupDate = m.getLastSignupDate();
		//int membershipLength = m.getMembershipLength();
		handleExpiration(lastSignupDate, 3, member.getMembershipLength(), expirationTextField);
		addSemesterButton = new JButton("Add Semester");
		addSemesterButton.addActionListener(new AddSemesterActionListener());
		addYearButton = new JButton("Add Year");
		addYearButton.addActionListener(new AddYearActionListener());
		
		addIOUButton = new JButton("Add to IOU Amount");
		addIOUButton.addActionListener(new AddToIOUActionListener());
		
		subtractIOUButton = new JButton("Subtract from IOU Amount");
		subtractIOUButton.addActionListener(new SubtractFromIOUActionListener());
		if (tempIOU == 0.00) {
			subtractIOUButton.setEnabled(false);
		} 
		
		if (!member.canHaveIou())
			addIOUButton.setEnabled(false);
		
		applyDiscountButton = new JButton("Apply Discount");
		applyDiscountButton.addActionListener(new ApplyDiscountActionListener());
		
		if (member.getAvailableDiscounts() == 0)
			applyDiscountButton.setEnabled(false);
		
		saveButton = new JButton("SAVE");
		saveButton.addActionListener(new SaveActionListener());
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelActionListener());
		
		createPanel();
		
		/*mainPanel.add(firstNameLabel);
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
		mainPanel.add(addIOUButton);
		mainPanel.add(applyDiscountButton);
		mainPanel.add(discountsLabel);
		mainPanel.add(discountsTextField);
		mainPanel.add(IOULabel);
		mainPanel.add(IOUTextField);
		//mainPanel.add(recieveEmailCheckBox);
		mainPanel.add(activeMemberLabel);
		mainPanel.add(saveButton);
		mainPanel.add(cancelButton);
		mainPanel.add(activeMemberCheckBox);
		mainPanel.setVisible(true);
		mainFrame.add(mainPanel, BorderLayout.CENTER);*/
	}
	
	/**
	 * Dedicated routine to create and structure the main panel. This method also
	 * appends this panel to the main jframe.
	 * Auther: Ryan Hautau
	 */
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
		c.gridwidth = 1;
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
		c.ipadx = 175;
		mainPanel.add(currentYearBox, c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.ipadx = 0;
		c.anchor = GridBagConstraints.LINE_END;
		mainPanel.add(membershipTypeLabel, c);
		
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.LINE_START;
		mainPanel.add(membershipTypeBox, c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		mainPanel.add(expirationLabel, c);
		
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 1;
		mainPanel.add(expirationTextField, c);
		
		c.gridx = 2;
		c.gridy = 5;
		c.gridwidth = 1;
		mainPanel.add(addSemesterButton, c);
		
		c.gridx = 3;
		c.gridy = 5;
		c.gridwidth = 1;
		mainPanel.add(addYearButton, c);
		
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		mainPanel.add(discountsLabel, c);
		
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 2;
		mainPanel.add(discountsTextField, c);
		
		c.gridx = 3;
		c.gridy = 6;
		c.gridwidth = 1;
		mainPanel.add(applyDiscountButton, c);
		
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		mainPanel.add(IOULabel, c);
		
		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = 1;
		mainPanel.add(IOUTextField, c);
		
		c.gridx = 2;
		c.gridy = 7;
		c.gridwidth = 1;
		mainPanel.add(addIOUButton, c);
		
		c.gridx = 3;
		c.gridy = 7;
		c.gridwidth = 1;
		mainPanel.add(subtractIOUButton, c);
		
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		mainPanel.add(activeMemberLabel, c);
		
		c.gridx = 1;
		c.gridy = 8;
		c.gridwidth = 1;
		mainPanel.add(activeMemberCheckBox, c);
		
		c.gridx = 2;
		c.gridy = 9;
		c.gridwidth = 1;
		mainPanel.add(saveButton, c);
		
		c.gridx = 3;
		c.gridy = 9;
		c.gridwidth = 1;
		mainPanel.add(cancelButton, c);
		
		
		mainFrame.add(mainPanel, BorderLayout.NORTH);
		mainFrame.pack();
		mainFrame.setLocation(350, 150);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		mainFrame.validate();
	
	}
	
	class CurrentYearActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			
		}		
	}
	
	class MembershipTypeActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			
		}		
	}
	
	class AddSemesterActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			handleExpiration(lastSignupDate, 0, member.getMembershipLength(), expirationTextField);
		}
		
	}
	
	class AddYearActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			handleExpiration(lastSignupDate, 1, member.getMembershipLength(), expirationTextField);
		}
	}
	
	class ApplyDiscountActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			--tempAvailDiscounts;
			discountsTextField.setText("" + tempAvailDiscounts);
		
			applyDiscountButton.setEnabled(false);
		}		
	}
	
	class AddToIOUActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			double adjustment = 0;
			boolean accept = false;
			
			while(!accept)
			{
				try
				{
					String value = 	JOptionPane.showInputDialog(null,
								 "Adjustment to be made" , "Add" ,
								 JOptionPane.OK_OPTION);
					if (value == null)
					{
						accept = true;
					}
					else
					{
						adjustment = Double.parseDouble(value);
						accept = true;
					}					 
				}
				catch(Exception exception)
				{
					String str = "You may only enter numbers.";
					JOptionPane.showMessageDialog(null, str, "Error",
							JOptionPane.INFORMATION_MESSAGE);
					
					accept = false;			
				}
			}
			
			if (adjustment > 0)
			{
				tempIOU = controller.addToIou(
						currentYearBox.getSelectedIndex(),
						membershipTypeBox.getSelectedIndex(),
						tempIOU , adjustment);

				DecimalFormat df = new DecimalFormat("0.00");
				IOUTextField.setText(""+df.format(tempIOU));
				
				if (tempIOU > 0.00) {
					subtractIOUButton.setEnabled(true);
				}
				
			}
		}
		
	}
	
	class SubtractFromIOUActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			double adjustment = 0.0;
			boolean accept = false;
			
			while(!accept)
			{
				try
				{
					String value = 	JOptionPane.showInputDialog(null,
								 "Adjustment to be made" , "Subtract" ,
								 JOptionPane.OK_OPTION);
					if (value == null)
					{
						accept = true;
					}
					else
					{
						adjustment = Double.parseDouble(value);
						accept = true;
					}					 
				}
				catch(Exception exception)
				{
					String str = "You may only enter numbers.";
					JOptionPane.showMessageDialog(null, str, "Error",
							JOptionPane.INFORMATION_MESSAGE);
					
					accept = false;	
				}
			}
			
			if (adjustment > 0)
			{
				tempIOU = controller.subtractFromIou(
						currentYearBox.getSelectedIndex(),
						membershipTypeBox.getSelectedIndex(),
						tempIOU , adjustment);

				DecimalFormat df = new DecimalFormat("0.00");
				IOUTextField.setText(""+df.format(tempIOU));

				if(tempIOU <= 0.00)
				{
					subtractIOUButton.setEnabled(false);
				}
			}			
			
		}		
	}
	
	class SaveActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			boolean result = controller.updateMember(member,
					firstNameTextField.getText(),
					lastNameTextField.getText(),
					emailTextField.getText(),
					currentYearBox.getSelectedIndex(),
					membershipTypeBox.getSelectedIndex(),
					tempAvailDiscounts, tempIOU,
					activeMemberCheckBox.isSelected());
			
			if(result)
			{
				parentWindow.clearSearchResults();
				
				mainFrame.dispose();
				
				parentWindow.setEnabled(true);
				parentWindow.requestFocus();
			}
			else
			{
				int choice = JOptionPane.showConfirmDialog(null,
						"Results not saved to the database, would you " +
						"like to quit?", "", JOptionPane.YES_NO_OPTION);
				if(choice == 0)
				{
					
					mainFrame.dispose();
					parentWindow.setEnabled(true);
					parentWindow.requestFocus();
				}
			}
		}		
	}
	
	class CancelActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if (verifyClosing())
			{
				mainFrame.dispose();
				parentWindow.setEnabled(true);
				parentWindow.requestFocus();
			}
		}		
	}
	
	/**
	 * Verify that the user wants to close the window.
	 * @return true if they do, false if they don't
	 */
	private boolean verifyClosing()
	{
		int result = JOptionPane.showConfirmDialog(null,
				"Results will not be saved.  Are you sure " +
				"you want to exit?", "Error",
				JOptionPane.YES_NO_OPTION);
		
		return (result == 0);
	}
	
	class ActiveMemberActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			setButtons(activeMemberCheckBox.isSelected());
		}		
	}

}


