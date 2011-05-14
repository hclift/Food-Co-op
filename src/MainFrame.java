import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainFrame extends JFrame{
	
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
	private JFrame frame;
	
	public MainFrame(Controller c){
		controller = c;
		
		// JFrame.setDefaultLookAndFeelDecorated(true);
		
		//Why is this indented???
         frame = new JFrame("Binghamton University Food Co-op | Main Menu");

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				ArrayList<Member> kitchenSignIns = controller.getSignedIntoKitchen();
				ArrayList<Member> storeSignIns = controller.getSignedIntoStore();
				if (kitchenSignIns.size() > 0 || storeSignIns.size() > 0)
				{
					JOptionPane.showConfirmDialog(null,
						"The program cannot be closed until all members are signed out.", "Alert",
					    JOptionPane.DEFAULT_OPTION);
				}
				else
				{
					System.exit(0);
				}
			}
		});
		
		//What's with the funny indentation???

				//Are these unused???
        Font buttonFont = new Font("Calibri", Font.BOLD, 12);
        Font f2 = new Font("Calibri", Font.PLAIN, 14);
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();  
        Dimension dimension = toolkit.getScreenSize();
        frame.setSize(dimension);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Make it fill window to start
        //Set up the content pane.
        //addComponentsToPane(frame.getContentPane());
		    frame.setResizable(true);
		    JPanel panel = new JPanel(); //Use JPanel as our container
		    
		    frame.add(panel); //Add to frame
		    addComponentsToPane(panel); //Lay out widgets and add to frame

		    //Don't use!!  We DON'T want it to be as small as possible
		    //frame.pack();  
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        
        //NOT a good idea for future maintenance!
        //frame.setSize(new Dimension(1024,768));
		restoreSignIns();
	}

	//Why indented so far???
    public void addComponentsToPane(Container pane) {       
    	
        
        pane.setLayout(new GridBagLayout());
        GridBagConstraints gBC = new GridBagConstraints();
        
        //make separate gridBag constraint object for EACH widget
        //otherwise, can become debug/maintenance nightmare
        GridBagConstraints fNLGBC = (GridBagConstraints)gBC.clone();
        GridBagConstraints fNTFGBC = (GridBagConstraints)gBC.clone();
        GridBagConstraints lNLGBC = (GridBagConstraints)gBC.clone();
        GridBagConstraints lNTFGBC = (GridBagConstraints)gBC.clone();
        GridBagConstraints sLGBC = (GridBagConstraints)gBC.clone();
        GridBagConstraints kLGBC = (GridBagConstraints)gBC.clone();
        GridBagConstraints fNSLGBC = (GridBagConstraints)gBC.clone();
        GridBagConstraints lNSLGBC = (GridBagConstraints)gBC.clone();
        GridBagConstraints mSLGBC = (GridBagConstraints)gBC.clone();     
        GridBagConstraints sBGBC = (GridBagConstraints)gBC.clone();
        GridBagConstraints eMSLGBC = (GridBagConstraints)gBC.clone();
        GridBagConstraints vMBGBC = (GridBagConstraints)gBC.clone();
        GridBagConstraints aMBGBC = (GridBagConstraints)gBC.clone();
        GridBagConstraints uMBGBC = (GridBagConstraints)gBC.clone();
        GridBagConstraints sISBGBC = (GridBagConstraints)gBC.clone();     
        GridBagConstraints sIKBGBC = (GridBagConstraints)gBC.clone();   
        GridBagConstraints sOSBGBC = (GridBagConstraints)gBC.clone();   
        GridBagConstraints sOKBGBC = (GridBagConstraints)gBC.clone();  
        GridBagConstraints gLJLGBC = (GridBagConstraints)gBC.clone();   
        GridBagConstraints sJLGBC = (GridBagConstraints)gBC.clone();   
        GridBagConstraints kJLGBC = (GridBagConstraints)gBC.clone();   
        
        //address issue for each widget, not all at once!
        //gBC.fill = GridBagConstraints.HORIZONTAL;

        //I've padded the first row with blank labels
        //to insure that the layout is stable
        JLabel [] blankLabel = new JLabel[7];
        GridBagConstraints [] bLGBC = new GridBagConstraints[8];
        String s = "                    ";
        for (int i = 0; i<blankLabel.length; i++)
        {
          blankLabel[i] = new JLabel(s);
          bLGBC[i] = new GridBagConstraints();
          bLGBC[i].gridx = i;
          bLGBC[i].gridy = 0;
          bLGBC[i].anchor = GridBagConstraints.LINE_END;
          pane.add(blankLabel[i], bLGBC[i]);
        }
        
        
        storeLabel = new JLabel("Store");
        sLGBC.gridx = 8;
        sLGBC.gridy = 0;
        sLGBC.anchor = GridBagConstraints.LINE_START;
        sLGBC.insets = new Insets(15, 20, 0, 0);
        pane.add(storeLabel, sLGBC);        
        
        firstNameLabel = new JLabel("First Name");        
        fNLGBC.gridx = 0;
        fNLGBC.gridy = 1;
        fNLGBC.insets = new Insets(0, 0, 0, 10);       
        fNLGBC.anchor = GridBagConstraints.LINE_END;
        pane.add(firstNameLabel, fNLGBC);
       
        firstNameTextField = new JTextField();   
        fNTFGBC.gridx = 1;
        fNTFGBC.gridy = 1;
        fNTFGBC.gridwidth = 4;
        //fNTFGBC.insets = new Insets(0, 0, 0, 0);
        fNTFGBC.fill = GridBagConstraints.HORIZONTAL;
        fNTFGBC.anchor = GridBagConstraints.LINE_START;
        firstNameTextField.setEditable(true);
        pane.add(firstNameTextField, fNTFGBC);
        
        lastNameLabel = new JLabel("Last Name");
        lNLGBC.gridx = 0;
        lNLGBC.gridy = 3;
        lNLGBC.insets = new Insets(10, 0, 0, 10);     
        lNLGBC.anchor = GridBagConstraints.LINE_END;
        pane.add(lastNameLabel, lNLGBC);

        lastNameTextField = new JTextField();
        lNTFGBC.gridx = 1;
        lNTFGBC.gridy = 3;
        lNTFGBC.gridwidth = 4;
        lNTFGBC.insets = new Insets(10, 0, 0, 0);
        lNTFGBC.fill = GridBagConstraints.HORIZONTAL;
        lNTFGBC.anchor = GridBagConstraints.FIRST_LINE_START;
        lastNameTextField.setEditable(true);
        pane.add(lastNameTextField, lNTFGBC);
        
        
        //if you want the search button to line up properly,
        //you need to pad it with a blank or invisible label
        
        JLabel invisibleLabelSearch = new JLabel("     ");
        GridBagConstraints iLSGBC = new GridBagConstraints();
        iLSGBC.gridx = 1;
        iLSGBC.gridy = 5;
        iLSGBC.insets = new Insets(10, 0, 0, 0);     
        pane.add(invisibleLabelSearch, iLSGBC);
 
        searchButton = new JButton("Search");
        sBGBC.gridx = 4;
        sBGBC.gridy = 5;
        sBGBC.insets = new Insets(10, 0, 0, 0);     
        sBGBC.anchor = GridBagConstraints.FIRST_LINE_END;
        pane.add(searchButton, sBGBC);         


        
        kitchenLabel = new JLabel("Kitchen");
        kLGBC.gridx = 8;
        kLGBC.gridy = 10;
        kLGBC.anchor = GridBagConstraints.LINE_START;
        kLGBC.insets = new Insets(15, 20, 0, 0);
        pane.add(kitchenLabel, kLGBC);
        

        firstNameScrollLabel = new JLabel("First Name");
        fNSLGBC.gridx = 0;
        fNSLGBC.gridy = 7;
        //fNSLGBC.gridwidth = 2;
        fNSLGBC.insets = new Insets(15, 15, 0, 0);
        fNSLGBC.anchor = GridBagConstraints.LINE_START;
        fNSLGBC.fill = GridBagConstraints.HORIZONTAL;
        pane.add(firstNameScrollLabel, fNSLGBC);
        
        lastNameScrollLabel = new JLabel("Last Name");
        //GBC.ipady = 40;     //This component has more breadth compared to other button
        //lNSLGBC.gridwidth = 1;
        lNSLGBC.gridx = 1;
        lNSLGBC.gridy = 7;
        lNSLGBC.insets = new Insets(15, 70, 0, 0);
        //lNSLGBC.anchor = GridBagConstraints.LINE_END;
        lNSLGBC.fill = GridBagConstraints.HORIZONTAL;
        pane.add(lastNameScrollLabel, lNSLGBC);
        

        
        membershipScrollLabel = new JLabel("Membership");
        mSLGBC.gridx = 2;
        mSLGBC.gridy = 7;
        mSLGBC.insets = new Insets(15, 135, 0, 0);
        mSLGBC.anchor = GridBagConstraints.LINE_START;
        pane.add(membershipScrollLabel, mSLGBC);
        
        emailScrollLabel = new JLabel("Email");
        eMSLGBC.gridx = 3;
        eMSLGBC.gridy = 7;
        eMSLGBC.insets = new Insets(15, 105, 0, 0);
        eMSLGBC.anchor = GridBagConstraints.LINE_START;
        pane.add(emailScrollLabel, eMSLGBC);
        
        
        viewMemberButton = new JButton("View Member");
        //vMBGBC.weightx = 0.5;
        vMBGBC.gridx = 0;
        vMBGBC.gridy = 16;
        vMBGBC.insets = new Insets(0, 20, 0, 0);
        vMBGBC.fill = GridBagConstraints.HORIZONTAL;
        viewMemberButton.setEnabled(false);
        pane.add(viewMemberButton, vMBGBC);
        
        updateMemberButton = new JButton("Update Member");
        uMBGBC.gridx = 1;
        uMBGBC.gridy = 16;
        uMBGBC.insets = new Insets(0, 15, 0, 0);
        uMBGBC.fill = GridBagConstraints.HORIZONTAL;
        updateMemberButton.setEnabled(false);
        pane.add(updateMemberButton, uMBGBC);
        
        addMemberButton = new JButton("Add Member");
        aMBGBC.gridx = 2;
        aMBGBC.gridy = 16;
        aMBGBC.insets = new Insets(0, 15, 0, 0);
        aMBGBC.fill = GridBagConstraints.HORIZONTAL;
        pane.add(addMemberButton, aMBGBC);
        
        signIntoStoreButton = new JButton("Sign into Store");
        sISBGBC.gridx = 0;
        sISBGBC.gridy = 18;
        sISBGBC.insets = new Insets(15, 20, 0, 0);
        sISBGBC.fill = GridBagConstraints.HORIZONTAL;
        signIntoStoreButton.setEnabled(false);
        pane.add(signIntoStoreButton, sISBGBC);
        
        signIntoKitchenButton = new JButton("Sign into Kitchen");
        //sIKBGBC.weightx = 0.5;
        sIKBGBC.gridx = 1;
        sIKBGBC.gridy = 18;
        sIKBGBC.insets = new Insets(15, 15, 0, 0);
        sIKBGBC.fill = GridBagConstraints.HORIZONTAL;
        signIntoKitchenButton.setEnabled(false);
        pane.add(signIntoKitchenButton, sIKBGBC);
        
        
        signOutOfStoreButton = new JButton("Sign Out");
        sOSBGBC.gridx = 8;
        sOSBGBC.gridy = 9;
        sOSBGBC.insets = new Insets(15, 15, 0, 15);
        signOutOfStoreButton.setEnabled(false);
        pane.add(signOutOfStoreButton, sOSBGBC);
        
        
        
        signOutOfKitchenButton = new JButton("Sign Out");
        sOKBGBC.gridx = 8;
        sOKBGBC.gridy = 20;
        sOKBGBC.insets = new Insets(15, 15, 15, 15);
        signOutOfKitchenButton.setEnabled(false);
        pane.add(signOutOfKitchenButton, sOKBGBC);
        
        
        //general Lookup
        generalLookupModel = new DefaultListModel();
        generalLookup = new JList(generalLookupModel);
        Font displayFont = new Font(Font.MONOSPACED, Font.PLAIN,14);
        generalLookup.setFont(displayFont);
        generalLookupPane = new JScrollPane(generalLookup);
        generalLookup.setSelectedIndex(0);
        generalLookup.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gLJLGBC.gridx = 0;
        gLJLGBC.gridy = 8;
        gLJLGBC.gridwidth = 7;
        gLJLGBC.gridheight = 7;
        gLJLGBC.weightx = 1.0;
        gLJLGBC.weighty = 1.0;
        gLJLGBC.insets = new Insets(5, 15, 15, 0);
        gLJLGBC.anchor = GridBagConstraints.LINE_END;
        gLJLGBC.fill = GridBagConstraints.BOTH;
        pane.add(generalLookupPane, gLJLGBC);
        
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
        sJLGBC.gridx = 8;
        sJLGBC.gridy = 1;
        sJLGBC.gridheight = 8;
        sJLGBC.weightx = 1.0;
        sJLGBC.weighty = 1.0;
        sJLGBC.insets = new Insets(5, 15, 0, 15);
        sJLGBC.anchor = GridBagConstraints.LINE_END;
        sJLGBC.fill = GridBagConstraints.BOTH;
        pane.add(storePane, sJLGBC);
        
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
        kJLGBC.gridx = 8;
        kJLGBC.gridy = 11;
        kJLGBC.gridheight = 8;
        kJLGBC.weightx = 1.0;
        kJLGBC.weighty = 1.0;
        kJLGBC.insets = new Insets(5, 15, 0, 15);
        kJLGBC.anchor = GridBagConstraints.LINE_END;
        kJLGBC.fill = GridBagConstraints.BOTH;
        pane.add(kitchenPane, kJLGBC);
        
        
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
    }
    
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
		new ViewMember(this, CURRENT_MEMBER, controller);
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
				String string  = new String((String.format("%-24.23s", searchResult.get(j).getFirstName()) + 
											 String.format("%-25.25s", searchResult.get(j).getLastName()) + " " + 
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
			if (value == null)
			{
				return -1;
			}
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
	
	public void restoreSignIns()
	{
		controller.restoreSignIns();
		printStore(controller.getSignedIntoStore());
		printKitchen(controller.getSignedIntoKitchen());
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
			new UpdateMemberFrame(MainFrame.this, controller, CURRENT_MEMBER);
		}		
	}
	
	class AddMemberListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
//FIXME UNCOMMENT			
		new AddMember(controller, MainFrame.this);
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
			store.clearSelection();
			signOutOfStoreButton.setEnabled(false);
			
			if (generalLookup.getSelectedIndex() != -1)
			{
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
	
	class SignOutOfKitchenListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			printKitchen(controller.signOutOfKitchen(kitchen.getSelectedIndex()));
			kitchen.clearSelection();
			signOutOfKitchenButton.setEnabled(false);
			if (generalLookup.getSelectedIndex() != -1)
			{
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
	
	public void deactivateExpiredMembers()
	{
		controller.deactivateExpiredMembers();
	}
	
	/**
	 * Enable the main frame to receive input.
	 */
	public void enable()
	{
		frame.setEnabled(true);
	}
	
	/**
	 * Disable the main frame and prevent it from receiving input.
	 * Called by view member, update member, and add member windows
	 * after launch.
	 */
	public void disable()
	{
		frame.setEnabled(false);
	}
}