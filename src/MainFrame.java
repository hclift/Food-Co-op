import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 *
 * @author Dave Wroblewski
 * @version 4/4/11
 * This is the GUI class for the main menu of the program
 * this class, all it's members, and all it's variables are completely
 * self explanatory
 * 
 * Code reviewed by Raanan Korinow
 * 
 * 
 * 
 */
public class MainFrame extends JFrame {


	/**
	 * Automatically generated serial ID 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates the panels for the main frame
	 * 2 panels on the frame itself one BoarderLayout.EAST
	 * the other BorderLayout.WEST, the latter will contain 3 additional panels
	 * Top middle and bottom
	 * 
	 * 
	 */
	private JPanel eastPanel;
	private JPanel westPanel;
	private JPanel topWestPanel;
	private JPanel middleWestPanel;
	private JPanel bottomWestPanel;
	private JPanel mainPanel;


	//JLabels
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel storeLabel;
	private JLabel kitchenLabel;
	private JLabel firstNameScrollLabel;
	private JLabel lastNameScrollLabel;
	private JLabel membershipScrollLabel;
	private JLabel emailScrollLabel;


	//JTextFields
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;

	//JTextAreas and JScrollPanes
	private JTextArea generalLookupTextArea;
	private JScrollPane generalLookupScrollPane;

	//JListLookup and JList Model
	private JList generalLookup;
	private DefaultListModel generalLookupModel;
	private JScrollPane generalLookupPane;
	
	private JList store;
	private DefaultListModel storeModel;
	private JScrollPane storePane;
	
	private JList kitchen;
	private DefaultListModel kitchenModel;
	private JScrollPane kitchenPane;

	private JMenu menu;

	//JButtons
	private JButton searchButton;
	private JButton viewMemberButton;
	private JButton updateMemberButton;
	private JButton addMemberButton;
	private JButton signIntoStoreButton;
	private JButton signIntoKitchenButton;
	private JButton viewScheduleButton;
	private JButton signOutOfStoreButton;
	private JButton signOutOfKitchenButton;

	private Controller controller;
	private Model model;

	public MainFrame(Controller c, Model m){
		controller = c;
		model = m;

		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
		setVisible(true);
		setTitle("Main Menu");
		Font buttonFont = new Font("Calibri", Font.BOLD, 12);
		Font f2 = new Font("Calibri", Font.PLAIN, 14);
		eastPanel = new JPanel();
		//eastPanel.setBackground(Color.GREEN);
		westPanel = new JPanel();
		topWestPanel = new JPanel();
		//topWestPanel.setBackground(Color.CYAN);
		middleWestPanel = new JPanel();
		//middleWestPanel.setBackground(Color.YELLOW);
		bottomWestPanel = new JPanel();
		//bottomWestPanel.setBackground(Color.PINK);
		mainPanel = new JPanel();

		mainPanel.setLayout(null);

		eastPanel.setLayout(null);
		eastPanel.setBounds(800, 0, 300, 675);
		westPanel.setLayout(new GridLayout(3, 1));
		westPanel.setBounds(0, 0, 800, 675);
		topWestPanel.setLayout(null);
		middleWestPanel.setLayout(null);
		bottomWestPanel.setLayout(null);


		//==============================================================
		/**
		 * Creates the area where the user enters
		 * a members first and last name and can search
		 * This is the top panel of the westPanel
		 * 
		 * 
		 */
		firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setFont(f2);
		firstNameLabel.setBounds(125, 39, 70, 10);

		lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setFont(f2);
		lastNameLabel.setBounds(125, 99, 70, 10);

		firstNameTextField = new JTextField();
		firstNameTextField.setColumns(5);
		firstNameTextField.setBounds(195, 33, 400, 30);

		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(195, 93, 400, 30);

		searchButton = new JButton("Search");
		searchButton.setFont(buttonFont);
		searchButton.setBounds(515, 146, 80, 25);
		searchButton.setEnabled(false);

		topWestPanel.add(firstNameLabel);
		topWestPanel.add(lastNameLabel);
		topWestPanel.add(firstNameTextField);
		topWestPanel.add(lastNameTextField);
		topWestPanel.add(searchButton);
		//==============================================================
		/**
		 * Creates the main text area where
		 * all the member information is placed
		 * this is the middle panel of the westPanel
		 * 
		 * 
		 */
		firstNameScrollLabel = new JLabel("First Name");
		lastNameScrollLabel = new JLabel("Last Name");
		membershipScrollLabel = new JLabel("Membership");
		emailScrollLabel = new JLabel("Email");

		firstNameScrollLabel.setFont(f2);
		lastNameScrollLabel.setFont(f2);
		membershipScrollLabel.setFont(f2);
		emailScrollLabel.setFont(f2);

		firstNameScrollLabel.setBounds(5, 0, 70, 20);
		lastNameScrollLabel.setBounds(182, 0, 70, 20);
		membershipScrollLabel.setBounds(358, 0, 90, 20);
		emailScrollLabel.setBounds(534, 0, 70, 20);

		generalLookupModel = new DefaultListModel();
		generalLookup = new JList(generalLookupModel);

		generalLookupPane = new JScrollPane(generalLookup);
		//generalLookupPane.setBounds(15, 0, 410, 125);
		
		/**
		 * This listener will wait to see if a member has been looked up.
		 */
		generalLookup.addListSelectionListener(new ListSelectionListener(){
			/**
			 * Every time a member has been selected by the cursor, an event will be triggered that will
			 * enable the use of the View Member, Update Member, Sign Into Kitchen, and Sign into Store
			 * buttons.
			 */
			public void valueChanged(ListSelectionEvent e){
				enableButtons();
			}				
		}
		);
		
		// Add a listener for mouse clicks
		generalLookup.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {          // Double-click
		            showViewMember();
		        }
		    }
		});

		generalLookupTextArea = new JTextArea();
		generalLookupTextArea.setFont(f2);

		middleWestPanel.add(firstNameScrollLabel);
		middleWestPanel.add(lastNameScrollLabel);
		middleWestPanel.add(membershipScrollLabel);
		middleWestPanel.add(emailScrollLabel);
		middleWestPanel.add(generalLookupPane);
		
		generalLookupPane.setBounds(5, 20, 795, 205);

//==============================================================

		/**
		 * Creates the bottom panel of buttons
		 * the bottom most panel on westPanel
		 * 
		 */
		viewMemberButton = new JButton("View Member");
		viewMemberButton.setBounds(65, 30, 150, 40);
		viewMemberButton.setFont(buttonFont);
		viewMemberButton.setEnabled(false);

		updateMemberButton = new JButton("Update Member");
		updateMemberButton.setBounds(325, 30, 150, 40);
		updateMemberButton.setFont(buttonFont);
		updateMemberButton.setEnabled(false);

		addMemberButton = new JButton("Add Member");
		addMemberButton.setBounds(565, 30, 150, 40);
		addMemberButton.setFont(buttonFont);

		signIntoStoreButton = new JButton("Sign Into Store");
		signIntoStoreButton.setBounds(65, 100, 150, 40);
		signIntoStoreButton.setFont(buttonFont);
		signIntoStoreButton.setEnabled(false);

		signIntoKitchenButton = new JButton("Sign Into Kitchen");
		signIntoKitchenButton.setBounds(325, 100, 150, 40);
		signIntoKitchenButton.setFont(buttonFont);
		signIntoKitchenButton.setEnabled(false);

		viewScheduleButton = new JButton("View Schedule");
		viewScheduleButton.setBounds(565, 100, 150, 40);
		viewScheduleButton.setFont(buttonFont);

		bottomWestPanel.add(viewMemberButton);
		bottomWestPanel.add(updateMemberButton);
		bottomWestPanel.add(addMemberButton);
		bottomWestPanel.add(signIntoStoreButton);
		bottomWestPanel.add(signIntoKitchenButton);
		bottomWestPanel.add(viewScheduleButton);

		//==============================================================
		/**
		 * Creates the view on the east panel
		 * where the user can see who is logged
		 * into the store and kitchen, and can also
		 * log them out
		 * 
		 * 
		 */
		//Un-comment these 2 lines to add text to JTextAreas for testing
		storeLabel = new JLabel("Store:");
		storeLabel.setBounds(15, 15, 40, 10);
		storeLabel.setFont(f2);

		kitchenLabel = new JLabel("Kitchen:");
		kitchenLabel.setBounds(15, 320, 80, 10);
		kitchenLabel.setFont(f2);

		signOutOfStoreButton = new JButton("Sign Out");
		signOutOfStoreButton.setFont(buttonFont);
		signOutOfStoreButton.setBounds(135, 290, 80, 25);
		signOutOfStoreButton.setEnabled(false);
		
		storeModel = new DefaultListModel();
		store = new JList(storeModel);

		storePane = new JScrollPane(store);
		storePane.setBounds(15, 30, 200, 250);
		
		/**
		 * This listener will wait to see if a member has been looked up.
		 */
		store.addListSelectionListener(new ListSelectionListener(){
			/**
			 * 
			 */
			public void valueChanged(ListSelectionEvent e){
				signOutOfStoreButton.setEnabled(true);
			}				
		}
		);

		signOutOfKitchenButton = new JButton("Sign Out");
		signOutOfKitchenButton.setFont(buttonFont);
		signOutOfKitchenButton.setBounds(135, 600, 80, 25);
		signOutOfKitchenButton.setEnabled(false);
		
		kitchenModel = new DefaultListModel();
		kitchen = new JList(kitchenModel);
		
		kitchenPane = new JScrollPane(kitchen);
		kitchenPane.setBounds(15, 340, 200, 250);
		
		/**
		 * This listener will wait to see if a member has been looked up.
		 */
		kitchen.addListSelectionListener(new ListSelectionListener(){
			/**
			 * 
			 */
			public void valueChanged(ListSelectionEvent e){
				signOutOfKitchenButton.setEnabled(true);
			}				
		}
		);

		eastPanel.add(storeLabel);
		eastPanel.add(kitchenLabel);
		eastPanel.add(storePane);
		eastPanel.add(kitchenPane);
		eastPanel.add(signOutOfStoreButton);
		eastPanel.add(signOutOfKitchenButton);

		//==============================================================
		//Sets the panels visible and adds them to the frame
		topWestPanel.setVisible(true);
		middleWestPanel.setVisible(true);
		bottomWestPanel.setVisible(true);
		westPanel.setVisible(true);
		eastPanel.setVisible(true);
		westPanel.add(topWestPanel);
		westPanel.add(middleWestPanel);
		westPanel.add(bottomWestPanel);

		add(mainPanel);
		mainPanel.add(westPanel);
		mainPanel.add(eastPanel);
		addListeners();
		validate();
	}

	/**
	 * Adds the listeners to the buttons
	 * 
	 * 
	 */
	private void addListeners(){



		ActionListener buttonListener = new ButtonListener();
		searchButton.addActionListener(buttonListener);
		viewMemberButton.addActionListener(buttonListener);
		updateMemberButton.addActionListener(buttonListener);
		addMemberButton.addActionListener(buttonListener);
		signIntoStoreButton.addActionListener(buttonListener);
		signIntoKitchenButton.addActionListener(buttonListener);
		viewScheduleButton.addActionListener(buttonListener);
		signOutOfStoreButton.addActionListener(buttonListener);
		signOutOfKitchenButton.addActionListener(buttonListener);
		//KeyListener Added
		KeyListener enterListener = new EnterListener();
		firstNameTextField.addKeyListener(enterListener);
		lastNameTextField.addKeyListener(enterListener);
	}
	/** 
	 * 
	 * Enable View Member, Update Member, Sign Into Store, and Sign Into Kitchen Button
	 * 
	 */

	private void enableButtons(){
		viewMemberButton.setEnabled(true);
		updateMemberButton.setEnabled(true);
		signIntoStoreButton.setEnabled(true);
		signIntoKitchenButton.setEnabled(true);

	}

	/**
	 * 
	 * Disable View Member, Update Member, Sign Into Store, and Sign Into Kitchen Button
	 * 
	 */
	private void disableButtons(){

		viewMemberButton.setEnabled(false);
		updateMemberButton.setEnabled(false);
		signIntoStoreButton.setEnabled(false);
		signIntoKitchenButton.setEnabled(false);

	}

	public void printStore(ArrayList<Member> members)
	{
		store.clearSelection();
		storeModel.clear();
		if (members.size() < 1)
		{
			signOutOfStoreButton.setEnabled(false);
		}
		else
		{
			for (int i = 0; i < members.size(); i++)
			{
				String string = new String(members.get(i).getFirstName() +
					" " + members.get(i).getLastName());
				storeModel.addElement(string);
			}
		}
	}

	public void printKitchen(ArrayList<Member> members)
	{
		kitchen.clearSelection();
		kitchenModel.clear();
		if (members.size() < 1)
		{
			signOutOfKitchenButton.setEnabled(false);
		}
		else
		{
			for (int i = 0; i < members.size(); i++)
			{
				String string = new String(members.get(i).getFirstName() +
					" " + members.get(i).getLastName());
				kitchenModel.addElement(string);
			}
		}

	}

	public void printSearchResult(ArrayList<Member> searchResult)
	{
		//This will clear the previous Search Result automatically to prevent an event from happening.
		generalLookup.clearSelection();
		generalLookupModel.clear();
		
		if (searchResult.size() < 1){
			disableButtons();
		}
		else
		{
			for(int j = 0; j < searchResult.size(); j++){
				String string  = new String((searchResult.get(j).getFirstName()+ "     "+ searchResult.get(j).getLastName()+ "    "
												+ searchResult.get(j).getMembershipTypeString() + "    "
												+ searchResult.get(j).getEmailAddress()+ "    ")); 
				generalLookupModel.addElement(string);
			}
		}
	}
	/**
	 * 
	 * @param errorMessage a string to be displayed in the error message pop-up
	 * 
	 * displays a given string as an error message
	 */


	public void displayException(String errorMessage)
	{
		JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void showViewMember()
	{
		Member m = controller.getMember(generalLookup.getSelectedIndex());
		new ViewMember(m);
	}
	
	/**
	 * 
	 * @author Dave Wroblewski
	 * @version 4/4/11
	 * 
	 * This is the inner ActionListener class
	 * 
	 * 
	 * 
	 */
	class ButtonListener implements ActionListener{

		String str;

		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource().equals(searchButton))
				printSearchResult(controller.lookUpMember(firstNameTextField.getText(), lastNameTextField.getText()));

			else if(e.getSource().equals(viewMemberButton)){
				showViewMember();

			}else if(e.getSource().equals(updateMemberButton)){
				Member m = controller.getMember(generalLookup.getSelectedIndex());
				new UpdateMemberFrame(controller, m);

			}else if(e.getSource().equals(addMemberButton)){
				new AddMember(controller);

			}else if(e.getSource().equals(signIntoStoreButton)){
				int memberIndex = generalLookup.getSelectedIndex();
				ArrayList<Member> members = controller.signIntoStore(memberIndex);
				printStore(members);
				
			}else if(e.getSource().equals(signIntoKitchenButton)){
				int memberIndex = generalLookup.getSelectedIndex();
				ArrayList<Member> members = controller.signIntoKitchen(memberIndex);
				printKitchen(members);
				
			}else if(e.getSource().equals(viewScheduleButton)){
				str = "View schedule method to go here.";
				JOptionPane.showMessageDialog(null, str, "Error", JOptionPane.INFORMATION_MESSAGE);

			}else if(e.getSource().equals(signOutOfStoreButton)){
				ArrayList<Member> members = controller.signOutOfStore(store.getSelectedIndex());
				printStore(members);

			}else if(e.getSource().equals(signOutOfKitchenButton)){
				ArrayList<Member> members = controller.signOutOfKitchen(kitchen.getSelectedIndex());
				printKitchen(members);

			}else{
				System.exit(0);
			}	
		}		
	}

	/**
	 * 
	 * @author Chun Hung Tseng
	 * @version 4/7/11
	 * 
	 * This is the inner KeyListener class
	 * 
	 * 
	 * 
	 */
	class EnterListener implements KeyListener{
		boolean TextFieldStatus = false;
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if ((key == KeyEvent.VK_ENTER) && !(firstNameTextField.getText().equals("") || lastNameTextField.getText().equals(""))) {
				//System.out.println("First Name: " + firstNameTextField.getText() + "\nLastName: " + lastNameTextField.getText());
				printSearchResult(controller.lookUpMember(firstNameTextField.getText(), lastNameTextField.getText()));
			}
		}

		public void keyReleased(KeyEvent e) {
			if(firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("")){
				searchButton.setEnabled(false);
			}
			else{
				searchButton.setEnabled(true);
			}
		}

		public void keyTyped(KeyEvent e) {
		}
	}

	/**
	 *	TODO: Write method to allow the user to reconcile the shift length,
	 * and then	return the resulting length. 
	 *@param shiftLengthInput the actual shift length worked
	 *@return the reconciled shift length
	 **/

	/**
	 * @author Ashley Chin
	 * @version 4/28/11
	 * 
	 * When member has worked for less than 45 minutes or greater than 2 hours,
	 * reconcile shift length window pops up and prompts user to input a new
	 * shift length based on judgement.
	 * 
	 * @param actual shift length
	 * @return reonciled shift length
	 */
	public int reconcileShiftLength(int actualShiftLength)
	{
		int reconciledShiftLength = 0;
		boolean flag = false;
		while (!flag)
		{
			String value = JOptionPane.showInputDialog("Member has been working for " + actualShiftLength + "minutes. Enter the adjusted shift length: ");
			try
			{
				reconciledShiftLength = Integer.parseInt(value);
				if (reconciledShiftLength < 0)
				{
					value = "Please enter a non-negative value for time.";
				}
				else
				{
					flag = true;
				}
			}
			catch (NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "Please enter a valid number.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		return reconciledShiftLength;		
	}
}
