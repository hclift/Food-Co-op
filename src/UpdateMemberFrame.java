import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;



public class UpdateMemberFrame{
private JFrame mainFrame;
private JPanel mainPanel;
private JLabel firstNameLabel, lastNameLabel, emailLabel, yearLabel, 
	membershipTypeLabel, expirationLabel,discountsLabel, IOULabel, recieveEmailLabel;

private JTextField firstNameTextField, lastNameTextField, 
	emailTextField, expirationTextField, discountsTextField, IOUTextField;

private JButton addIOUButton, applyDiscountButton, saveButton, cancelButton, addSemesterButton, addYearButton, subtractIOUButton;

private JComboBox currentYearBox, membershipTypeBox;
private JCheckBox recieveEmailCheckBox;
private JCheckBox activeMemberCheckBox;

private Controller controller;
private Member member;
double tempIOU;
int	   tempAvailDiscounts;
GregorianCalendar   tempExpirationDate;


	public UpdateMemberFrame(Controller controller, Member member){
		this.member = member;
		this.controller = controller;
		tempIOU = member.getIouAmount();
		tempAvailDiscounts = (int)tempIOU;
		tempExpirationDate = new GregorianCalendar(member.getExpirationDate().get(Calendar.YEAR), member.getExpirationDate().get(Calendar.MONTH), member.getExpirationDate().get(Calendar.DAY_OF_MONTH));
		mainFrame = new JFrame("Update Member");
		mainFrame.setBounds(475, 300, 550, 410);
		//mainFrame.setFocusableWindowState(false);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//mainFrame.setResizable(false);
		addPanel();
		if(!member.getActive())
			setButtons(false);
		mainFrame.setVisible(true);
		mainFrame.validate();
		
	}
	private void setButtons(boolean visibility)
	{
		addIOUButton.setVisible(visibility);
		applyDiscountButton.setVisible(visibility);
		//addyear button
		//addsemesterbutton
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
		
		discountsTextField = new JTextField(""+member.getAvailableDiscounts());
		discountsTextField.setBounds(130, 180, 80, 25);
		discountsTextField.setEditable(false);
		
		IOULabel = new JLabel("IOU Amount: ");
		IOULabel.setBounds(240, 180, 80, 20);
		
		IOUTextField = new JTextField(""+member.getIouAmount());
		IOUTextField.setBounds(320, 180, 80, 25);
		IOUTextField.setEditable(false);
		
		recieveEmailCheckBox = new JCheckBox();
		recieveEmailCheckBox.setBounds(5, 216, 25, 28);
		recieveEmailCheckBox.setSelected(member.getReceiveEmail());
		
		activeMemberCheckBox = new JCheckBox();
		activeMemberCheckBox.setBounds(178, 216, 25, 28);
		activeMemberCheckBox.setSelected(member.getActive());
		activeMemberCheckBox.addActionListener(new OKCancelButtonListener());
		
		
		recieveEmailLabel = new JLabel("Recieve E-Mails");
		recieveEmailLabel.setBounds(30, 220, 150, 20);
		
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
		expirationTextField.setBounds(100, 140, 70, 25);
		expirationTextField.setEditable(false);
		expirationTextField.setText("12/12/2011");
		
		addIOUButton = new JButton("Add IOU");
		addIOUButton.setBounds(178, 140, 120, 25);
		addIOUButton.addActionListener(new OKCancelButtonListener());
		
		addSemesterButton = new JButton("Add Semester");
		addSemesterButton.setBounds(350, 270, 120, 30);
		addSemesterButton.addActionListener(new OKCancelButtonListener());
		
		addYearButton = new JButton("Add Year");
		addYearButton.setBounds(220, 270, 120, 30);
		addYearButton.addActionListener(new OKCancelButtonListener());
		
		applyDiscountButton = new JButton("Apply Discount");
		applyDiscountButton.setBounds(305, 140, 160, 25);
		applyDiscountButton.addActionListener(new OKCancelButtonListener());
		if(member.getMembershipType() == 0 || member.getIouAmount() < 1)
			applyDiscountButton.setVisible(false);
		
		saveButton = new JButton("SAVE");
		saveButton.setBounds(250, 230, 80, 30);
		saveButton.addActionListener(new OKCancelButtonListener());
		
		subtractIOUButton = new JButton("Subtract IOU");
		subtractIOUButton.setBounds(100, 240, 80, 30);
		subtractIOUButton.addActionListener(new OKCancelButtonListener());
		
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
		mainPanel.add(recieveEmailCheckBox);
		mainPanel.add(recieveEmailLabel);
		mainPanel.add(subtractIOUButton);
		mainPanel.add(saveButton);
		mainPanel.add(cancelButton);
		mainPanel.add(activeMemberCheckBox);
		mainPanel.add(addSemesterButton);
		mainPanel.add(addYearButton);
		mainPanel.setVisible(true);
		mainFrame.add(mainPanel, BorderLayout.CENTER);
		
		
	}
	
	class OKCancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(cancelButton)){
				
				int result = JOptionPane.showConfirmDialog(null, "Results will not be saved, are you sure you want to exit", "Error", JOptionPane.YES_NO_OPTION);
				if(result == 0)
					mainFrame.dispose();
			}else if(e.getSource().equals(saveButton)){
				boolean result = controller.updateMember(member, firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), currentYearBox.getSelectedIndex(), membershipTypeBox.getSelectedIndex(), tempExpirationDate, tempAvailDiscounts, tempIOU , recieveEmailCheckBox.isSelected(), activeMemberCheckBox.isSelected());
				
				if(result)
					mainFrame.dispose();
				
				int choice = JOptionPane.showConfirmDialog(null, "Results not saved to the database, would you like to quit?", "", JOptionPane.YES_NO_OPTION);
				if(choice == 0)
					mainFrame.dispose();
			}
			else if(e.getSource().equals(applyDiscountButton))
			{
				tempIOU = controller.subtractFromIou(currentYearBox.getSelectedIndex(), membershipTypeBox.getSelectedIndex(), tempIOU, 1);
				tempAvailDiscounts = (int)tempIOU;
				int twoplaces = (int) (tempIOU * 100);
				tempIOU = ((double)twoplaces)/100;
				IOUTextField.setText("" + tempIOU);
				discountsTextField.setText(""+tempAvailDiscounts);
				if(tempIOU < 1 || membershipTypeBox.getSelectedIndex() == 0)
					applyDiscountButton.setVisible(false);
			}
			else if(e.getSource().equals(addIOUButton)){
				double adjustment = 0;
				boolean accept = false;
				
				while(!accept)
				{
					try
					{
					 adjustment = Double.parseDouble(JOptionPane.showInputDialog(null, "Adjustment to be made" , "Add" , JOptionPane.OK_OPTION));
					 accept = true;
					}
					catch(Exception exception)
					{
						String str = "Do not enter characters";
						JOptionPane.showMessageDialog(null, str, "Error", JOptionPane.INFORMATION_MESSAGE);
						
						accept = false;
						
					}
				}
				
				tempIOU = controller.addToIou(currentYearBox.getSelectedIndex(), membershipTypeBox.getSelectedIndex(), tempIOU , adjustment);
				tempAvailDiscounts = (int)tempIOU;
				int twoplaces = (int) (tempIOU * 100);
				tempIOU = ((double)twoplaces)/100;
				IOUTextField.setText(""+tempIOU);
				discountsTextField.setText(""+tempAvailDiscounts);
				if(tempIOU >= 1 && membershipTypeBox.getSelectedIndex() > 0)
					applyDiscountButton.setVisible(true);
			}
			else if(e.getSource().equals(activeMemberCheckBox))
			{
				setButtons(activeMemberCheckBox.isSelected());
			}
			else if(e.getSource().equals(membershipTypeBox))
			{
				if(membershipTypeBox.getSelectedIndex() == 0 || member.getIouAmount() < 1)
					applyDiscountButton.setVisible(false);
				else
					applyDiscountButton.setVisible(true);
			}
			else if(e.getSource().equals(addSemesterButton))
			{
				
				GregorianCalendar currentTime = new GregorianCalendar();
				if(currentTime.get(Calendar.YEAR) >= tempExpirationDate.get(Calendar.YEAR) && 
						currentTime.get(Calendar.MONTH) >= tempExpirationDate.get(Calendar.MONTH) && 
						currentTime.get(Calendar.DAY_OF_MONTH) > tempExpirationDate.get(Calendar.DAY_OF_MONTH))
					tempExpirationDate = currentTime;
			
				tempExpirationDate.set(Calendar.MONTH, tempExpirationDate.get(Calendar.MONTH)+5);
				
				String str = "" + tempExpirationDate.get(Calendar.MONTH) + "    " + tempExpirationDate.get(Calendar.YEAR);
				JOptionPane.showMessageDialog(null, str, "Error", JOptionPane.INFORMATION_MESSAGE);
					
				
			}
			else if(e.getSource().equals(addYearButton))
			{
				GregorianCalendar currentTime = new GregorianCalendar();
				
				if(currentTime.get(Calendar.YEAR) >= tempExpirationDate.get(Calendar.YEAR) && 
						currentTime.get(Calendar.MONTH) >= tempExpirationDate.get(Calendar.MONTH) && 
						currentTime.get(Calendar.DAY_OF_MONTH) > tempExpirationDate.get(Calendar.DAY_OF_MONTH))
					tempExpirationDate = currentTime;
			
				
				tempExpirationDate.set(Calendar.YEAR, tempExpirationDate.get(Calendar.YEAR)+1);
				

				String str = "" + tempExpirationDate.get(Calendar.MONTH) + "    " + tempExpirationDate.get(Calendar.YEAR);
				JOptionPane.showMessageDialog(null, str, "Error", JOptionPane.INFORMATION_MESSAGE);
				
			}
			else if(e.getSource().equals(subtractIOUButton))
			{
				double adjustment = 0;
				boolean accept = false;
				
				while(!accept)
				{
					try
					{
					 adjustment = Double.parseDouble(JOptionPane.showInputDialog(null, "Adjustment to be made" , "Subtract" , JOptionPane.OK_OPTION));
					 accept = true;
					}
					catch(Exception exception)
					{
						String str = "Do not enter characters";
						JOptionPane.showMessageDialog(null, str, "Error", JOptionPane.INFORMATION_MESSAGE);
						
						accept = false;
						
					}
				}
				
				tempIOU = controller.subtractFromIou(currentYearBox.getSelectedIndex(), membershipTypeBox.getSelectedIndex(), tempIOU , adjustment);
				tempAvailDiscounts = (int)tempIOU;
				int twoplaces = (int) (tempIOU * 100);
				tempIOU = ((double)twoplaces)/100;
				IOUTextField.setText(""+tempIOU);
				discountsTextField.setText(""+tempAvailDiscounts);
				if(tempIOU < 1 || membershipTypeBox.getSelectedIndex() == 0)
					applyDiscountButton.setVisible(false);
			}
			else{
			
				System.exit(0);
			}
		}
	}
}


