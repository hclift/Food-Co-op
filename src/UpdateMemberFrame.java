/*
 * UpdateMemberFrame.java
 * This is the GUI class for the Update Member window of the program.
 * Interacts with the controller which calls the model.
 * 
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class UpdateMemberFrame{
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
	private JButton addIOUButton, applyDiscountButton, saveButton, 
	cancelButton;
	
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
	
	/**
	 * Explicit value constructor for UpdateMemberFrame.
	 * Takes in controller and member as parameters.
	 * @param controller, member
	 **/
	public UpdateMemberFrame(Controller controller, Member member)
	{
		this.member = member;
		this.controller = controller;
		tempIOU = member.getIouAmount();
		tempAvailDiscounts = member.getAvailableDiscounts();
		mainFrame = new JFrame("Update Member");
		mainFrame.setBounds(375, 200, 520, 310);
		//mainFrame.setFocusableWindowState(false);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//mainFrame.setResizable(false);
		addPanel();
		if(!member.getActive())
		{
			setButtons(false);
		}
		mainFrame.setVisible(true);
		mainFrame.validate();
	}
	
	
	/**
	 * Sets the visibility of the buttons.
	 * Takes in a boolean which determines whether buttons are visible.
	 * @param visibility
	 **/
	private void setButtons(boolean visibility)
	{
		addIOUButton.setVisible(visibility);
		applyDiscountButton.setVisible(visibility);
	}
	
	
	/**
	 * Adds the main panel into the main frame of update member.
	 * Creates all labels, text fields, buttons, and boxes in main panel.
	 **/
	private void addPanel()
	{
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
		
		discountsTextField = new JTextField(""+member.getAvailableDiscounts());
		discountsTextField.setBounds(130, 180, 80, 25);
		discountsTextField.setEditable(false);
		
		IOULabel = new JLabel("IOU Amount: ");
		IOULabel.setBounds(240, 180, 80, 20);
		
		IOUTextField = new JTextField(""+member.getIouAmount());
		IOUTextField.setBounds(320, 180, 80, 25);
		IOUTextField.setEditable(false);
		
		activeMemberCheckBox = new JCheckBox();
		activeMemberCheckBox.setBounds(100, 216, 25, 28);
		activeMemberCheckBox.setSelected(member.getActive());
		activeMemberCheckBox.addActionListener(new OKCancelButtonListener());
		
		
		activeMemberLabel = new JLabel("Active Member");
		activeMemberLabel.setBounds(10, 220, 150, 20);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(80, 5, 350, 25);
		firstNameTextField.setText(member.getFirstName());
		
		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(80, 35, 350, 25);
		lastNameTextField.setText(member.getLastName());
		
		emailTextField = new JTextField();
		emailTextField.setBounds(80, 65, 350, 25);
		emailTextField.setText(member.getEmailAddress());
		
		currentYearBox = new JComboBox();
		currentYearBox.setBounds(85, 100, 100, 25);
		currentYearBox.addItem("Freshman 1");
		currentYearBox.addItem("Freshman 2");
		currentYearBox.addItem("Sophmore 1");
		currentYearBox.addItem("Sophmore 2");
		currentYearBox.addItem("Junior 1");
		currentYearBox.addItem("Junior 2");
		currentYearBox.addItem("Senior 1");
		currentYearBox.addItem("Senior 2");
		currentYearBox.setSelectedIndex(member.getYearsInSchool());
		
		membershipTypeBox = new JComboBox();
		membershipTypeBox.setBounds(310, 100, 100, 25);
		membershipTypeBox.addItem("Ordinary");
		membershipTypeBox.addItem("Working");
		membershipTypeBox.addItem("Core");
		membershipTypeBox.addItem("Coordinator");
		membershipTypeBox.setSelectedIndex(member.getMembershipType());
		membershipTypeBox.addActionListener(new OKCancelButtonListener());
		
		expirationTextField = new JTextField();
		expirationTextField.setBounds(100, 140, 100, 25);
		expirationTextField.setEditable(false);
		expirationTextField.setText("12/12/2011");
		
		addIOUButton = new JButton("Add to IOU Amount");
		addIOUButton.setBounds(205, 140, 160, 25);
		addIOUButton.addActionListener(new OKCancelButtonListener());
		
		applyDiscountButton = new JButton("Apply Discount");
		applyDiscountButton.setBounds(370, 140, 120, 25);
		applyDiscountButton.addActionListener(new OKCancelButtonListener());
		
		if (member.getAvailableDiscounts() > 0)
			applyDiscountButton.setEnabled(true);
		
		saveButton = new JButton("SAVE");
		saveButton.setBounds(250, 230, 80, 30);
		saveButton.addActionListener(new OKCancelButtonListener());
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(340, 230, 80, 30);
		cancelButton.addActionListener(new OKCancelButtonListener());
		
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
		mainFrame.add(mainPanel, BorderLayout.CENTER);
	}
	
	
	/**
	 * 
	 * This ActionListener is done improperly and will need to be redone.
	 * 
	 */
	class OKCancelButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource().equals(cancelButton))
			{
				int result = JOptionPane.showConfirmDialog(null,
						"Results will not be saved, are you sure " +
						"you want to exit?", "Error",
						JOptionPane.YES_NO_OPTION);
				if(result == 0)
					mainFrame.dispose();

			}
			else if(e.getSource().equals(saveButton))
			{
				boolean result = controller.updateMember(member,
						firstNameTextField.getText(),
						lastNameTextField.getText(),
						emailTextField.getText(),
						currentYearBox.getSelectedIndex(),
						membershipTypeBox.getSelectedIndex(), null,
						tempAvailDiscounts, tempIOU,
						activeMemberCheckBox.isSelected());
				
				if(result)
				{
					mainFrame.dispose();
				}
				
				int choice = JOptionPane.showConfirmDialog(null,
						"Results not saved to the database, would you " +
						"like to quit?", "", JOptionPane.YES_NO_OPTION);
				if(choice == 0)
				{
					mainFrame.dispose();
				}
			}
			else if(e.getSource().equals(applyDiscountButton))
			{
				--tempAvailDiscounts;
				discountsTextField.setText("" + tempAvailDiscounts);
			
				applyDiscountButton.setEnabled(false);
			}
			else if(e.getSource().equals(addIOUButton))
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

					int twoplaces = (int) (tempIOU * 100);
					tempIOU = ((double)twoplaces)/100;
					IOUTextField.setText(""+tempIOU);
					//discountsTextField.setText(""+tempAvailDiscounts);
					if(tempIOU > 0)
					{
						applyDiscountButton.setEnabled(true);
					}
				}
				
			}
			else if(e.getSource().equals(activeMemberCheckBox))
			{
				setButtons(activeMemberCheckBox.isSelected());
			}
			else if(e.getSource().equals(membershipTypeBox))
			{
				if(membershipTypeBox.getSelectedIndex() == 0 || 
						member.getIouAmount() < 1)
				{
					applyDiscountButton.setVisible(false);
				}
				else
				{
					applyDiscountButton.setVisible(true);
				}
			}
			else
			{
				System.exit(0);
			}
		}
	}
}



