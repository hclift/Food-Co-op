import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
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
private Controller controller;
private Member t;

private ScheduleGUI sg;


	public ViewMember(MainFrame parentWindow, Member m, Controller controller){
		this.controller = controller;
		this.parentWindow = parentWindow;
		parentWindow.disableButtons();
		
		t = m;
		mainFrame = new JFrame("View Member");
		mainFrame.setBounds(275, 200, 450, 310);
		//mainFrame.setFocusableWindowState(false);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				ViewMember.this.parentWindow.reenableButtons();
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
	
	public void handleExpiration(Date dIn, int iIn, long lIn, JTextField jtfIn){
		SimpleDateFormat formattedExpirationDate  = new SimpleDateFormat("MM/dd/yyyy");
		if (iIn == 0)
		{
			//	183 is 365 / 2
			long milliseconds_in_half_year = 15778463000L;
			lIn = dIn.getTime() + 183 + milliseconds_in_half_year;
		}
		else if (iIn == 1)
		{
			// 365 is one year
			long milliseconds_in_year = 31556926000L;
			lIn = dIn.getTime() + 365 + milliseconds_in_year;
		}
		jtfIn.setText(formattedExpirationDate.format(lIn));
	}
	
	private void addPanel(Member m){
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		firstNameLabel = new JLabel("First Name: ");
		firstNameLabel.setBounds(5, 5, 80, 20);
		
		lastNameLabel = new JLabel("Last Name: ");
		lastNameLabel.setBounds(5, 35, 80, 20);
		
		emailLabel = new JLabel("E-Mail: ");
		emailLabel.setBounds(5, 63, 80, 20);
		
		yearLabel = new JLabel("Current Year: ");
		yearLabel.setBounds(5, 100, 110, 20);
		
		membershipTypeLabel = new JLabel("Membership Type: ");
		membershipTypeLabel.setBounds(200, 100, 150, 20);
		
		expirationLabel = new JLabel("Expiration Date: ");
		expirationLabel.setBounds(5, 140, 130, 20);
		
		discountsLabel = new JLabel("Discounts Available: ");
		discountsLabel.setBounds(5, 180, 150, 20);
		
		discountsTextField = new JTextField();
		discountsTextField.setBounds(130, 180, 80, 25);
		discountsTextField.setEditable(false);
		discountsTextField.setText(Integer.toString(m.getAvailableDiscounts()));
		
		
		IOULabel = new JLabel("IOU Amount: $");
		IOULabel.setBounds(230, 180, 100, 20);
		
		DecimalFormat df = new DecimalFormat("0.00");
		IOUTextField = new JTextField();
		IOUTextField.setBounds(320, 180, 80, 25);
		IOUTextField.setEditable(false);
		IOUTextField.setText(df.format(m.getIouAmount()));
		
		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(80, 5, 350, 25);
		firstNameTextField.setEditable(false);
		firstNameTextField.setText(m.getFirstName());
		
		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(80, 35, 350, 25);
		lastNameTextField.setEditable(false);
		lastNameTextField.setText(m.getLastName());
		
		emailTextField = new JTextField();
		emailTextField.setBounds(80, 65, 350, 25);
		emailTextField.setEditable(false);
		emailTextField.setText(m.getEmailAddress());
		
		
		currentYearBox = new JTextField();
		currentYearBox.setBounds(85, 100, 100, 25);
		currentYearBox.setEditable(false);
		currentYearBox.setText(m.getYearsInSchoolString());
		
		membershipTypeBox = new JTextField();
		membershipTypeBox.setBounds(310, 100, 100, 25);
		membershipTypeBox.setEditable(false);
		membershipTypeBox.setText(m.getMembershipTypeString());
		
		expirationTextField = new JTextField();
		expirationTextField.setBounds(100, 140, 100, 25);
		expirationTextField.setEditable(false);
		
		//	Calculate expiration date
		
		Date lastSignupDate = m.getLastSignupDate();
		int membershipLength = m.getMembershipLength();
		long expirationDate = 0;
		handleExpiration(lastSignupDate, membershipLength,expirationDate,expirationTextField);
		/*SimpleDateFormat formattedExpirationDate  = new SimpleDateFormat("MM/dd/yyyy");
		if (membershipLength == 0)
		{
			//	183 is 365 / 2
			expirationDate = lastSignupDate.getTime() + 183 * 24 * 60 * 60;
		}
		else if (membershipLength == 1)
		{
			// 365 is one year
			expirationDate = lastSignupDate.getTime() + 365 * 24 * 60 * 60;
		}
		expirationTextField.setText(formattedExpirationDate.format(expirationDate));
		*/
		
		
		workHistoryButton = new JButton("Display Work History");
		workHistoryButton.setBounds(230, 140, 180, 25);
		
		workHistoryButton.addActionListener(new ButtonListener());
		
		
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
		mainPanel.add(okButton);
		
		mainPanel.setVisible(true);
		mainFrame.add(mainPanel, BorderLayout.CENTER);
		
		
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(okButton)){
				mainFrame.dispose();
				parentWindow.reenableButtons();

			}else if(e.getSource().equals(workHistoryButton)){
				//controller = new Controller(new Model());
				Calendar cal = Calendar.getInstance();
				int month = cal.get(Calendar.MONTH) + 1;
				int year = cal.get(Calendar.YEAR);
//				System.out.println(month + " " + (month -1) + " " + (month -2));
				String shift = populateCalendar(t,month,year);
				String shift2 = populateCalendar(t,month-1,year);
				String shift3 = populateCalendar(t,month-2,year);
				System.out.println(month-2 + " and string " + shift3);
		    	sg = new ScheduleGUI(month-1, shift, shift2, shift3);
		    	ScheduleGUI.calendarGUI(month, shift, shift2, shift3);
			}else{
				System.exit(0);
			}
		}
		
		
	}
	
	/**
	 * @author Kevin Urrutia, Joe Zammito, Nick Cox
	 * 
	 * @param member
	 * @param month we want to get 
	 * @param year the year desired
	 * 
	 * This gets the shifts that the current member has worked 
	 * 
	 * @return it returns a string that show has the days and hours worked
	 * 
	 */
	String populateCalendar(Member member, int month, int year){
		
//		System.out.println(month);
//		System.out.println(year);
//		System.out.println(member.getId());
//		System.out.println("--input data before");
		String shifts = "";
		//ArrayList containing shift lengths
		ArrayList <ShiftInfo>  shiftsArray = new ArrayList <ShiftInfo> ();
		//Variable to store day member worked
		int day;
		//Get minimum time member worked
		int minWorked;
		shiftsArray = controller.getShifts(member, month, year);
		//for each ShiftInfo object in arraylist
		//System.out.println(shiftsArray.size() + " the size is");
		try{
			for(int i = 0; i < shiftsArray.size(); i++){
				//extract arraylist objects
				//map ShiftInfo day data member to calendar day
				day = shiftsArray.get(i).getShiftDay();
				//System.out.println(day + " day");
				shifts += day + " ";
				minWorked = shiftsArray.get(i).getshiftLength();
				//System.out.println(minWorked + " min worked");

				//display shift length for that day (if more than 1 shift/day, sum the minutes for that day)
				if(minWorked < 60)
				{
					//Display in normal minutes
					shifts += minWorked + "\n";
				}
				else if(minWorked % 60 == 0)
				{
					int hourDisplay = minWorked / 60;
					shifts += hourDisplay + "\n";
					//Display as 1 hour, 2 hours, 3 hours, etc
					/*
					 * Example: Worked 120 minutes:
					 * 120 % 60 = 0
					 * 120 / 60 = 2, so hourDisplay = 2
					 */

					//Display hours now
				}
				else
				{
					
				///
					int minuteDisplay = minWorked % 60;
					int hourDisplay = minWorked / 60;
					shifts += Integer.toString(hourDisplay) + "" + Integer.toString(minuteDisplay) + "\n";
					/*
					 * Example: Worked 90 minutes:
					 * 90 % 60 = 30 (minutes)
					 * 90 / 60 = 1 (hour)
					 */
					//System.out.println(hourDisplay + "hours and " + minuteDisplay + "minutes");

				}
			}
		}
			catch(Exception e){
				//maybe some error
			}
		return shifts;
	}
	
}




