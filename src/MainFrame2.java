import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainFrame2 extends JFrame{
	
	private Controller controller;
	
	private JButton searchButton;
	private JButton viewMemberButton;
	private JButton updateMemberButton;
	private JButton addMemberButton;
	private JButton signIntoStoreButton;
	private JButton signIntoKitchenButton;
	private JButton signOutOfStoreButton;
	private JButton signOutOfKitchenButton;
	
	//JLabels
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel storeLabel;
	private JLabel kitchenLabel;
	private JLabel firstNameScrollLabel;
	private JLabel lastNameScrollLabel;
	private JLabel membershipScrollLabel;
	private JLabel emailScrollLabel;
    
    
    private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	
	
	private JList generalLookup;
	private DefaultListModel generalLookupModel;
	private JScrollPane generalLookupPane;
	
	private JList store;
	private DefaultListModel storeModel;
	private JScrollPane storePane;
	
	private JList kitchen;
	private DefaultListModel kitchenModel;
	private JScrollPane kitchenPane;
	
	private static Member CURRENT_MEMBER;
	
	public MainFrame2(Controller c){
		controller = c;
		//setSize(new Dimension(1024,768));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setVisible(true);
		//setTitle("Main Menu");
		
		JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font buttonFont = new Font("Calibri", Font.BOLD, 12);
		Font f2 = new Font("Calibri", Font.PLAIN, 14);
        frame.setVisible(true);
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(1024,768));
	}

    public void addComponentsToPane(Container pane) {       
    	
        
        pane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        firstNameLabel = new JLabel("First Name:");
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 0;
        pane.add(firstNameLabel, gbc);
       
        firstNameTextField = new JTextField();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        firstNameTextField.setEditable(true);
        pane.add(firstNameTextField, gbc);
        
        lastNameLabel = new JLabel("Last Name:");
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 1;
        pane.add(lastNameLabel, gbc);

        lastNameTextField = new JTextField();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        lastNameTextField.setEditable(true);
        pane.add(lastNameTextField, gbc);
        
        

        storeLabel = new JLabel("Store");
        gbc.gridx = 5;
        gbc.gridy = 0;
        //gbc.anchor = GridBagConstraints.PAGE_END;
        pane.add(storeLabel, gbc);
        
        kitchenLabel = new JLabel("Kitchen");
        gbc.gridx = 5;
        gbc.gridy = 6;
        //gbc.anchor = GridBagConstraints.PAGE_END;
        pane.add(kitchenLabel, gbc);
        

        firstNameScrollLabel = new JLabel("First Name");
        //gbc.ipady = 40;     //This component has more breadth compared to other button
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 3;
        pane.add(firstNameScrollLabel, gbc);
        
        lastNameScrollLabel = new JLabel("Last Name");
        //gbc.ipady = 40;     //This component has more breadth compared to other button
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 3;
        pane.add(lastNameScrollLabel, gbc);
        
        searchButton = new JButton("Search");
        //gbc.weightx = 0.5;
        gbc.gridx = 3;
        gbc.gridy = 2;
        //gbc.gridwidth = 1;
        pane.add(searchButton, gbc); 
        
        membershipScrollLabel = new JLabel("Membership");
        //gbc.ipady = 40;     //This component has more breadth compared to other button
        gbc.gridwidth = 1;
        gbc.gridx = 3;
        gbc.gridy = 3;
        pane.add(membershipScrollLabel, gbc);
        
        emailScrollLabel = new JLabel("Email");
        //gbc.ipady = 40;     //This component has more breadth compared to other button
        gbc.gridwidth = 1;
        gbc.gridx = 4;
        gbc.gridy = 3;
        pane.add(emailScrollLabel, gbc);
        
        
        viewMemberButton = new JButton("View Member");
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 6;
        viewMemberButton.setEnabled(false);
        pane.add(viewMemberButton, gbc);
        
        updateMemberButton = new JButton("Update");
        gbc.weightx = 0.5;
        gbc.gridx = 2;
        gbc.gridy = 6;
        updateMemberButton.setEnabled(false);
        pane.add(updateMemberButton, gbc);
        
        addMemberButton = new JButton("Add Member");
        gbc.weightx = 0.5;
        gbc.gridx = 3;
        gbc.gridy = 6;
        pane.add(addMemberButton, gbc);
        
        signIntoStoreButton = new JButton("Sign into Store");
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 7;
        signIntoStoreButton.setEnabled(false);
        pane.add(signIntoStoreButton, gbc);
        
        signIntoKitchenButton = new JButton("Sign into Kitchen");
        gbc.weightx = 0.5;
        gbc.gridx = 2;
        gbc.gridy = 7;
        signIntoKitchenButton.setEnabled(false);
        pane.add(signIntoKitchenButton, gbc);
        
        
        signOutOfStoreButton = new JButton("Sign Out");
        gbc.weightx = 0.5;
        gbc.gridx = 6;
        gbc.gridy = 3;
        signOutOfStoreButton.setEnabled(false);
        pane.add(signOutOfStoreButton, gbc);
        
        
        
        signOutOfKitchenButton = new JButton("Sign Out");
        gbc.weightx = 0.5;
        gbc.gridx = 6;
        gbc.gridy = 10;
        signOutOfKitchenButton.setEnabled(false);
        pane.add(signOutOfKitchenButton, gbc);
        
        
        //general Lookup
        generalLookupModel = new DefaultListModel();
        generalLookup = new JList(generalLookupModel);
        Font displayFont = new Font(Font.MONOSPACED, Font.PLAIN,14);
        generalLookup.setFont(displayFont);
        generalLookupPane = new JScrollPane(generalLookup);
        generalLookup.setSelectedIndex(0);
        generalLookup.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.gridheight = 2;
        pane.add(generalLookupPane, gbc);
        
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
				if (e.getValueIsAdjusting() == false) {
					if (generalLookup.getSelectedIndex() != -1) {
						enableButtons();
					}
				}
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
        
        
      //signinstore Lookup
        
        
        storeModel = new DefaultListModel();
        store = new JList(storeModel);
        storePane = new JScrollPane(store);
        store.setSelectedIndex(0);
        store.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        pane.add(storePane, gbc);
        
        /**
		 * This listener will wait to see if a member has been looked up.
		 */
		store.addListSelectionListener(new ListSelectionListener(){
			/**
			 * 
			 */
			public void valueChanged(ListSelectionEvent e){
				if (e.getValueIsAdjusting() == false) {
					if (store.getSelectedIndex() != -1) {
						signOutOfStoreButton.setEnabled(true);
					}
				}
			}				
		}
		);
        
        
      //signinkitchen Lookup
        
        kitchenModel = new DefaultListModel();
        kitchen = new JList(kitchenModel);
        kitchenPane = new JScrollPane(kitchen);
        kitchen.setSelectedIndex(0);
        kitchen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gbc.gridx = 5;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        pane.add(kitchenPane, gbc);
        
        
        /**
		 * This listener will wait to see if a member has been looked up.
		 */
		kitchen.addListSelectionListener(new ListSelectionListener(){
			/**
			 * 
			 */
			public void valueChanged(ListSelectionEvent e){
				if (e.getValueIsAdjusting() == false) {
					if (kitchen.getSelectedIndex() != -1) {
						signOutOfKitchenButton.setEnabled(true);
					}
				}
			}				
		}
		);
        
        
        addListeners();    
        validate();
        
       //JComboBox jcmbSample = new JComboBox(new String[]{"ComboBox 1", "hi", "hello"});
        //gbc.ipady = 0;
        //gbc.weighty = 1.0;
        //gbc.anchor = GridBagConstraints.PAGE_END;
        //gbc.insets = new Insets(10,0,0,0);  //Padding
        //gbc.gridx = 1;
        //gbc.gridwidth = 1;
        //gbc.gridy = 2;
        //pane.add(jcmbSample, gbc); /**/
    }

    /*public void createAndShowGUI() {

    	JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }*/
    
    /**
	 * Adds the listeners to the buttons
	 */
	private void addListeners(){
		searchButton.addActionListener(new SearchButtonListener());
		viewMemberButton.addActionListener(new ViewMemberListener());
		updateMemberButton.addActionListener(new UpdateMemberListener());
		addMemberButton.addActionListener(new AddMemberListener());
		signIntoStoreButton.addActionListener(new SignIntoStoreListener());
		signIntoKitchenButton.addActionListener(new SignIntoKitchenListener());
		//viewScheduleButton.addActionListener(buttonListener);
		signOutOfStoreButton.addActionListener(new SignOutOfStoreListener());
		signOutOfKitchenButton.addActionListener(new SignOutOfKitchenListener());
		//KeyListener Added
		KeyListener enterListener = new EnterListener();
		firstNameTextField.addKeyListener(enterListener);
		lastNameTextField.addKeyListener(enterListener);
	}
	/** 
	 * 
	 * Enable View Member, Update Member, Sign Into Store, and Sign Into Kitchen Button
	 * if Member selected in generalLookup is currently signed into the store or kitchen
	 * then they can't sign into store/kitchen again (though it is caught if they somehow do)
	 *
	 */

	public void enableButtons()
	{
		viewMemberButton.setEnabled(true);
		updateMemberButton.setEnabled(true);
		
		Member currentMember = controller.getMember(generalLookup.getSelectedIndex());

		if (currentMember.canSignIn() && !controller.isSignedIn(currentMember))
		{
			signIntoStoreButton.setEnabled(true);
			signIntoKitchenButton.setEnabled(true);
		}
		else
		{
			signIntoStoreButton.setEnabled(false);
			signIntoKitchenButton.setEnabled(false);
		}
	}

	/**
	 * 
	 * Disable View Member, Update Member, Sign Into Store, and Sign Into Kitchen Button
	 * 
	 */
	public void disableSelectionButtons(){

		viewMemberButton.setEnabled(false);
		updateMemberButton.setEnabled(false);
		signIntoStoreButton.setEnabled(false);
		signIntoKitchenButton.setEnabled(false);
	}
	
	public void reenableButtons()
	{
		searchButton.setEnabled(true);
		addMemberButton.setEnabled(true);
		//viewScheduleButton.setEnabled(true);
		generalLookup.setEnabled(true);
	}

	public void printStore(ArrayList<Member> members)
	{
		store.clearSelection();
		storeModel.clear();
		//signIntoStoreButton.setEnabled(true);
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
	
	public void showViewMember()
	{
		CURRENT_MEMBER = controller.getMember(generalLookup.getSelectedIndex());
//FIXME UNCOMMENT		
		//new ViewMember(this, CURRENT_MEMBER);
	}
	

	public void printKitchen(ArrayList<Member> members)
	{
		kitchen.clearSelection();
		kitchenModel.clear();
		//signIntoKitchenButton.setEnabled(true);
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
		disableSelectionButtons();
		
		if (searchResult.size() < 1)
		{
			JOptionPane.showMessageDialog(null, "No results found.");
			disableSelectionButtons();
			
		}
		else 
		{
			for(int j = 0; j < searchResult.size(); j++){
				String membershipType = MembershipTypes.class.getEnumConstants()[searchResult.get(j).getMembershipType()].getStrVal();
				String string  = new String((String.format("%-22.21s", searchResult.get(j).getFirstName()) + 
											 String.format("%-21.20s", searchResult.get(j).getLastName()) + " " + 
											 String.format("%-22.21s", membershipType) + 
												searchResult.get(j).getEmailAddress())); 
				generalLookupModel.addElement(string);
			}
		}
	}
	
	// Coordinator
	
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
    
    
	/**
	 * 
	 * @author Dave Wroblewski
	 * @version 4/4/11
	 * 
	 * This is the inner ActionListener class
	 * 
	 * 
	 * 
	
	class ButtonListener implements ActionListener{

		String str;

		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource().equals(searchButton))
				printSearchResult(controller.lookUpMember(firstNameTextField.getText(), lastNameTextField.getText()));

			else if(e.getSource().equals(viewMemberButton)){
				showViewMember();

			}else if(e.getSource().equals(updateMemberButton)){
				CURRENT_MEMBER = controller.getMember(generalLookup.getSelectedIndex());
				//disableButtons();
				new UpdateMemberFrame(MainFrame.this, controller, CURRENT_MEMBER);
			}else if(e.getSource().equals(addMemberButton)){
				new AddMember(controller, MainFrame.this);
			}else if(e.getSource().equals(signIntoStoreButton)){
				int memberIndex = generalLookup.getSelectedIndex();
				ArrayList<Member> members = controller.signIntoStore(memberIndex);
				printStore(members);
				signIntoStoreButton.setEnabled(false);
				signIntoKitchenButton.setEnabled(false);
			}else if(e.getSource().equals(signIntoKitchenButton)){
				int memberIndex = generalLookup.getSelectedIndex();
				ArrayList<Member> members = controller.signIntoKitchen(memberIndex);
				printKitchen(members);
				signIntoStoreButton.setEnabled(false);
				signIntoKitchenButton.setEnabled(false);
				
			}else if(e.getSource().equals(signOutOfStoreButton)){
				printStore(controller.signOutOfStore(store.getSelectedIndex()));
				if (controller.getMember(generalLookup.getSelectedIndex()).canSignIn())
				{
					signIntoStoreButton.setEnabled(true);
					signIntoKitchenButton.setEnabled(true);
				}
				else
				{
					signIntoStoreButton.setEnabled(false);
					signIntoKitchenButton.setEnabled(false);
				}
			}else if(e.getSource().equals(signOutOfKitchenButton)){
				printKitchen(controller.signOutOfKitchen(kitchen.getSelectedIndex()));
				if (controller.getMember(generalLookup.getSelectedIndex()).canSignIn())
				{
					signIntoStoreButton.setEnabled(true);
					signIntoKitchenButton.setEnabled(true);
				}
				else
				{
					signIntoStoreButton.setEnabled(false);
					signIntoKitchenButton.setEnabled(false);
				}
			}
		}		
	}
 */
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
			if ((key == KeyEvent.VK_ENTER)) {
				//System.out.println("First Name: " + firstNameTextField.getText() + "\nLastName: " + lastNameTextField.getText());
				printSearchResult(controller.lookUpMember(firstNameTextField.getText(), lastNameTextField.getText()));
			}
		}

		public void keyReleased(KeyEvent e) {
			/*
			if(firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("")){
				searchButton.setEnabled(false);
			}
			else{
				searchButton.setEnabled(true);
			}
			*/
		}

		public void keyTyped(KeyEvent e) {
		}
	}
	
	
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
	public long reconcileShiftLength(long actualShiftLength)
	{
		long reconciledShiftLength = actualShiftLength;
		boolean flag = false;
		while (!flag)
		{
			String value = JOptionPane.showInputDialog("The member has " 
					+ "been working for " + actualShiftLength + 
					" minutes.  Enter the number of minutes that " +
					"they deserve.");
			try
			{
				reconciledShiftLength = Integer.parseInt(value);
				
				if (reconciledShiftLength < 0)
				{
					JOptionPane.showMessageDialog(null, "Please " +
							"enter a non-negative number of minutes.",
							"Error",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					flag = true;
				}
			}
			catch (NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "Please " +
						"enter a valid number of minutes.",
						"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		return reconciledShiftLength;
	}
	
	public void clearSearchResults()
	{
		generalLookupModel.clear();
		generalLookup.clearSelection();
		viewMemberButton.setEnabled(false);
		updateMemberButton.setEnabled(false);
		signIntoStoreButton.setEnabled(false);
		signIntoKitchenButton.setEnabled(false);
	}

   /* public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }*/
	
	class SearchButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			printSearchResult(controller.lookUpMember(firstNameTextField.getText(), lastNameTextField.getText()));
		}		
	}
	
	class ViewMemberListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			showViewMember();
		}		
	}
	
	class UpdateMemberListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			CURRENT_MEMBER = controller.getMember(generalLookup.getSelectedIndex());
//FIXME UNCOMMENT
			//new UpdateMemberFrame(MainFrame2.this, controller, CURRENT_MEMBER);
		}		
	}
	
	class AddMemberListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
//FIXME UNCOMMENT			
		//new AddMember(controller, MainFrame2.this);
		}		
	}
	
	class SignIntoStoreListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			int memberIndex = generalLookup.getSelectedIndex();
			ArrayList<Member> members = controller.signIntoStore(memberIndex);
			printStore(members);
			signIntoStoreButton.setEnabled(false);
			signIntoKitchenButton.setEnabled(false);
		}		
	}
	
	class SignIntoKitchenListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			int memberIndex = generalLookup.getSelectedIndex();
			ArrayList<Member> members = controller.signIntoKitchen(memberIndex);
			printKitchen(members);
			signIntoStoreButton.setEnabled(false);
			signIntoKitchenButton.setEnabled(false);
		}		
	}
	
	
	class SignOutOfStoreListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			printStore(controller.signOutOfStore(store.getSelectedIndex()));
			if (controller.getMember(generalLookup.getSelectedIndex()).canSignIn())
			{
				signIntoStoreButton.setEnabled(true);
				signIntoKitchenButton.setEnabled(true);
			}
			else
			{
				signIntoStoreButton.setEnabled(false);
				signIntoKitchenButton.setEnabled(false);
			}
		}		
	}
	
	class SignOutOfKitchenListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			printKitchen(controller.signOutOfKitchen(kitchen.getSelectedIndex()));
			if (controller.getMember(generalLookup.getSelectedIndex()).canSignIn())
			{
				signIntoStoreButton.setEnabled(true);
				signIntoKitchenButton.setEnabled(true);
			}
			else
			{
				signIntoStoreButton.setEnabled(false);
				signIntoKitchenButton.setEnabled(false);
			}
		}		
	}
    
}