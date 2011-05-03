/**
 * Add Member
 * 
 * This file creates the pop-up window for adding a member and the various
 * methods it uses.  To add a member, a name (both first and last) and an
 * email address must both entered into the text fields.  Other information
 * such as member's year in school and membership length and type are
 * selected from drop down menus.  After all fields have been entered, press 
 * the 'OK' button to add the member into the database.
 */


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class AddMember
{
		
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JLabel firstNameLabel, lastNameLabel, emailLabel, yearLabel, 
		membershipTypeLabel, discountsLabel, IOULabel, membershipDurationLabel;

	private JTextField firstNameTextField, lastNameTextField, 
		emailTextField, discountsTextField, IOUTextField;

	private JButton cancelButton, okButton;
	private JComboBox addSemYearComboBox;

	private JComboBox currentYearBox, membershipTypeBox;

	/**
	 * Create the window
	 * 
	 * This method creates the window for adding a member
	 */
	public AddMember()
	{
		mainFrame = new JFrame("Add Member");
		mainFrame.setBounds(275, 150, 450, 250);
		//mainFrame.setFocusableWindowState(false);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setResizable(false);	
		
		addPanel();
		mainFrame.setVisible(true);
		
		mainFrame.validate();
	}
	
	/**
	 * Convert Year
	 * 
	 * Converts the string for member's year into an integer,
	 * so that it may be stored in the Member object
	 * 
	 * @param sIn	String to be converted
	 * @return		The integer representation of the year
	 */
	private int convertYear(String sIn)
	{
		
		// -1 represents an error
		int status = -1;
		
		if(sIn.equals("Freshman"))
		{
			status = 0;
		}
		else if(sIn.equals("Sophmore"))
		{
			status = 1;
		}
		else if(sIn.equals("Junior"))
		{
			status = 2;
		}
		else if(sIn.equals("Senior"))
		{
			status = 3;
		}
		
		return status;
	}
	
	/**
	 * Convert Member Type
	 * 
	 * Converts the string for member's type into an integer,
	 * so that it may be stored in the Member object
	 * 
	 * @param sIn	String to be converted
	 * @return		The integer representation of the membership type
	 */
	private int convertMemType(String sIn)
	{
		
		// -1 represents an error
		int status = -1;
		
		if(sIn.equals("Ordinary"))
		{
			status = 0;
		}
		else if(sIn.equals("Working"))
		{
			status = 1;
		}
		else if(sIn.equals("Core"))
		{
			status = 2;
		}
		else if(sIn.equals("Coordinator"))
		{
			status = 3;
		}
		
		return status;
	}
	
	/**
	 * Convert Member Duration
	 * 
	 * Converts the string for member's length into an integer,
	 * so that it may be stored in the Member object
	 * 
	 * @param sIn	String to be converted
	 * @return		The integer representation of the membership length
	 */
	private int convertMemDur(String sIn)
	{
		
		// -1 represents an error
		int status = -1;
		
		if(sIn.equals("Semester"))
		{
			status = 0;
		}
		else if(sIn.equals("Year"))
		{
			status = 1;
		}
		
		return status;
	}
	
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
		membershipTypeBox.addItem("Working");
		membershipTypeBox.addItem("Core");
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
		
		//okButton.addActionListener(new ButtonListener());
		
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
		
		if(firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("")
				|| emailTextField.getText().equals(""))
		{
			okButton.setEnabled(false);
		}
		else
		{
			okButton.setEnabled(true);
		}
		
		KeyListener EnterListener = new EnterListener();
		firstNameTextField.addKeyListener(EnterListener);
		lastNameTextField.addKeyListener(EnterListener);
		emailTextField.addKeyListener(EnterListener);		
	}
	
	class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			if(e.getSource().equals(cancelButton))
			{
				mainFrame.dispose();
			}
			else if(e.getSource().equals(okButton))
			{
				//TODO: Implement methods for OKButton
				String fn;
				String ln;
				String em; 
				String cy = (String)currentYearBox.getSelectedItem();
				String mt = (String)membershipTypeBox.getSelectedItem();
				String sy = (String)addSemYearComboBox.getSelectedItem();
				boolean flag = true;
				//
					fn = firstNameTextField.getText();
					ln = lastNameTextField.getText();
					em = emailTextField.getText();
					while(flag){
					if(fn.isEmpty() || ln.isEmpty() || em.isEmpty())
					{
						fn = firstNameTextField.getText();
						ln = lastNameTextField.getText();
						em = emailTextField.getText();
					}
					else
					{
						flag = false;
					}
				}
				
				DatabaseAbstraction.addMember(fn, ln, em, convertMemDur(sy), convertMemType(mt), convertYear(cy), 1);					
				
				mainFrame.dispose();
			}
			else
			{
				System.exit(0);
			}
		}
	}
	
	class EnterListener implements KeyListener
	{
		boolean TextFieldStatus = false;
		public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();
			
		    if ((key == KeyEvent.VK_ENTER) && !firstNameTextField.getText().equals("") || !lastNameTextField.getText().equals("")
					|| !emailTextField.getText().equals(""))
		    {
		    	//System.out.println("First Name: " + firstNameTextField.getText() + "\nLastName: " + lastNameTextField.getText());
		    	//controller.addMember(first,last,email,1,mt,cy,1);
		    }
		}

		public void keyReleased(KeyEvent e)
		{
			if(firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("")
					|| emailTextField.getText().equals(""))
			{
				okButton.setEnabled(false);
			}
			else
			{
				okButton.setEnabled(true);
			}
		}

		public void keyTyped(KeyEvent e) 
		{
		}
	}
	
}