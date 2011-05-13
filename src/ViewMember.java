import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	membershipTypeLabel, expirationLabel,discountsLabel, IOULabel, activeLabel;

private JTextField firstNameTextField, lastNameTextField, 
	emailTextField, expirationTextField, discountsTextField, IOUTextField, activeTextField;

private JButton workHistoryButton, okButton;

private JTextField currentYearBox, membershipTypeBox;
private JCheckBox recieveEmailCheckBox;
private MainFrame parentWindow;
private Controller controller;
private Member t;

private ScheduleGUI sg;

	public ViewMember(MainFrame parentWindow, Member m, Controller controller)
	{
		this.controller = controller;
		this.parentWindow = parentWindow;

		System.err.println(parentWindow.isLightweight());
		parentWindow.disable();

		t = m;
		mainFrame = new JFrame("View Member");

		//mainFrame.setFocusableWindowState(false);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				ViewMember.this.parentWindow.enable();
				
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
		
		if (m.isActive())
		{
			activeLabel = new JLabel("This member is active.");
			activeLabel.setForeground(Color.green.darker().darker());
		}
		else
		{
			activeLabel = new JLabel("This member is inactive.");
			activeLabel.setForeground(Color.red.darker());
		}
		
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
				
		Date expirationDate = m.getExpirationDate();
		expirationTextField.setText((expirationDate.getMonth()+1) + "/" + expirationDate.getDate() + "/" + (expirationDate.getYear()+1900));

		workHistoryButton = new JButton("Display Work History");

		workHistoryButton.setBounds(230, 140, 180, 25);
		
		workHistoryButton.addActionListener(new ButtonListener());
		
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
		
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 2;
		mainPanel.add(activeLabel, c);
		
//		c.gridx = 1;
//		c.gridy = 7;
//		c.gridwidth = 1;
//		mainPanel.add(activeTextField, c);
		
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
		mainFrame.setLocationRelativeTo(parentWindow);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		mainFrame.validate();
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(okButton)){
				parentWindow.enable();
				mainFrame.dispose();
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
				minWorked = shiftsArray.get(i).getMinWorked();
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




