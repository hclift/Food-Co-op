/**
 * The ScheduleGUI class creates a window with a calendar containing the
 * recent work history of a member.
 * 
 * @author Chun Hung Tseng
 * @version May-11-2011
 * 
 * WorkSchedule class has constructor that takes in int month,string workHours
 * update information on the GUI
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.GregorianCalendar;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScheduleGUI extends JFrame 
{
	// added
	static private JButton btnPrev, btnNext;
	private Container container;
	static private ScheduleGUI calendarframe;
	private BorderLayout layout;
	static int currentYear, currentMonth,currentIndex,realIndex;
	static JTable scheduleCal;
	static String[] shiftList;
	static private JLabel monthLabel;
    
	/**
	 * Creates the schedule calendar for a given month (current month)
	 * 
	 * @param month		month to be displayed in the calendar
	 * @param shift1	shift worked by member
	 * @param shift2	shift worked by member
	 * @param shift3	shift worked by member
	 */
	public ScheduleGUI(int month, String shift1, String shift2, String shift3) 
    {
    	shiftList = new String[3];
    	shiftList[0] = shift1;
    	shiftList[1] = shift2;
    	shiftList[2] = shift3;
    	
    	layout = new BorderLayout(5,5);
    	
    	// get content pane and set layout
    	container = getContentPane();
    	container.setLayout(layout);
    	
        String[] columnNames = {"Sunday",
                                "Monday",
                                "Tuesday",
                                "Wednesday",
                                "Thursday",
                                "Friday",
                                "Saturday"};
        
        Object[][] data = new Object[6][7];
        GregorianCalendar calendar = new GregorianCalendar();
        currentYear = calendar.get(GregorianCalendar.YEAR);
        System.err.println(currentYear);
        currentMonth = month;
        scheduleCal = new JTable(data, columnNames)
        {
        	public boolean isCellEditable(int row, int col)
        	{
        		return false;
        	}
        };

        // Calendar GUI Property
        scheduleCal.setShowHorizontalLines(true);
        scheduleCal.setShowVerticalLines(true);
        scheduleCal.setShowGrid(true);
        scheduleCal.setGridColor(Color.black);
        scheduleCal.getTableHeader().setReorderingAllowed(false);
        scheduleCal.setDefaultRenderer(scheduleCal.getColumnClass(0), 
        		new CalRenderer());
        scheduleCal.setRowSelectionAllowed(false);
        scheduleCal.setPreferredScrollableViewportSize(new Dimension(500, 
        		100));
        
        // Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(scheduleCal);
        
        // Create controlsLeft
        btnPrev = new JButton ("<<");
        btnNext = new JButton (">>");
        monthLabel = new JLabel();

        // Register action listeners
        btnPrev.addActionListener(new btnPrev_Action());
        btnNext.addActionListener(new btnNext_Action()); 
        
        // Set border
        container.add(scrollPane,BorderLayout.CENTER);
        container.add(btnPrev,BorderLayout.WEST);
        container.add(btnNext,BorderLayout.EAST);
        container.add(monthLabel,BorderLayout.NORTH);
        currentIndex = 2;
        realIndex = 2;
        refreshCalendar(currentMonth, currentYear, currentIndex);
    }

	// Upon clicking button for previous month
    static class btnPrev_Action implements ActionListener
    {
    	public void actionPerformed (ActionEvent e)
        {
        	// Back one year
            if (currentMonth == 1)
            {
                currentMonth = 12;
                currentYear--;
            }
            // Back one month
            else
            {
            	currentMonth -= 1;
            }
            realIndex--;
            refreshCalendar(currentMonth, currentYear,currentIndex);
        }
    }
    
    // Upon clicking button for next month
    static class btnNext_Action implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
        	// Forward one year
            if (currentMonth == 12)
            {
                currentMonth = 1;
                currentYear++;
            }
            //Forward one month
            else
            {
                currentMonth += 1;
            }
            realIndex++;
            refreshCalendar(currentMonth, currentYear,currentIndex);
        }
    }
    
    /**
     * Updates the schedule with appropriate data by first clearing the 
     * calendar, then loading the date information and finally the work history
     * of the member. 
     * 
     * @param month		month to be shown in calendar
     * @param year		year to be shown up in calendar
     * @param index		index of the month
     */
    public static void refreshCalendar(int month, int year, int index)
    {
    	String monthString;
        switch (month) 
        {
            case 1:  monthString = "January";       break;
            case 2:  monthString = "February";      break;
            case 3:  monthString = "March";         break;
            case 4:  monthString = "April";         break;
            case 5:  monthString = "May";           break;
            case 6:  monthString = "June";          break;
            case 7:  monthString = "July";          break;
            case 8:  monthString = "August";        break;
            case 9:  monthString = "September";     break;
            case 10: monthString = "October";       break;
            case 11: monthString = "November";      break;
            case 12: monthString = "December";      break;
            default: monthString = "Invalid month"; break;
        }
        // update month title
        monthLabel.setText(monthString);

        // Number Of Days, Start Of Month
        int nod, som; 
        
        // Allow buttons
        btnPrev.setEnabled(true);
        btnNext.setEnabled(true);
        
        // Clear table
        for (int i=0; i<6; i++)
        {
            for (int j=0; j<7; j++)
            {
                scheduleCal.setValueAt(null, i, j);
            }
        }
        
        int calMonth;
        
        if(month-1 < 0)
        {	
        	calMonth = 11;
        }
        else
        {	
        	calMonth = month - 1;
        }
        
        // Get first day of month and number of days
        GregorianCalendar cal = new GregorianCalendar(year, calMonth, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);
        
        // Draw calendar
        for (int i=1; i<=nod; i++)
        {
            int row = new Integer((i+som-2)/7);
            int column  =  (i+som-2)%7;
            scheduleCal.setValueAt(i, row, column);
        }

        // Update work schedule on GUI
        int day=0,workHr=0;
        boolean status=false;
        int startIndex=0;
        
        for(int i=0;i<shiftList[realIndex].length();i++)
        {	
        	// Member did not work on given day
        	if(shiftList[realIndex].charAt(i)==' ')
        	{	
        		day = Integer.parseInt(shiftList[realIndex].
        				substring(startIndex, i));
        		startIndex = i+1;
        	}
        	// Member did work on given day
        	else if(shiftList[realIndex].charAt(i)=='\n')
	    	{	
        		workHr = Integer.parseInt(shiftList[realIndex].
        				substring(startIndex, i));
	    		startIndex = i+1;
	    		status = true;
	    	}
        	// Display minutes worked
	        if(status==true)
	        {	
	        	int row = new Integer((day+som-2)/7);
	        	int column  =  (day+som-2)%7;
	        	String dayHr = day + "    " + workHr +"";
	        	scheduleCal.setValueAt(dayHr, row, column);        	
	        	status=false;
	        }
        }
        if(realIndex==0)
        {
        	{btnPrev.setEnabled(false);}
        }
        if(realIndex==2)
        {
        	{btnNext.setEnabled(false);}
        }
        currentIndex=realIndex;
    }
    
    /**
     * @author Chun Hung Tseng
     * Highlight the weekend on the calendar
     */
	static class CalRenderer extends DefaultTableCellRenderer
	{
	    public Component getTableCellRendererComponent (JTable table,
	    		Object value, boolean selected, boolean focused, int row, 
	    		int column)
	    {
	        super.getTableCellRendererComponent(table, value, selected, 
	        		focused, row, column);
	        setBackground(Color.white);
	        return this; 
	    }
	}

    /**
     * Create the GUI and show it.
     * @author Chun Hung Tseng
     * @version May-11-2011
     * 
     * scheduleGUI function takes in int month, string workHours
     * shows a calendar GUI and calls workSchedule
     * 
     * @param month		month to be displayed in the calendar
     * @param shift1	shift worked by member
     * @param shift2	shift worked by member
     * @param shift3	shift worked by member
     */
	
    static void calendarGUI(int month, String shift1, String shift2,
    		String shift3) {

    	// Create and set up the window.
        calendarframe = new ScheduleGUI(month,shift1,shift2,shift3);

        // Set Calendar GUI to the center
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screen.getWidth()-calendarframe.getWidth())/ 2);
        int y = (int) ((screen.getHeight()-calendarframe.getHeight()) / 2);
        calendarframe.setLocation(x, y);
        calendarframe.setSize(x, y);
        calendarframe.setLocationRelativeTo(null);
        calendarframe.setTitle("Work Schedule");

        // Display the window.
        calendarframe.pack();
        calendarframe.setVisible(true);
    }
}
