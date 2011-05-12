/**
 * @author Chun Hung Tseng
 * @version May-11-2011
 * 
 * WorkSchedule class has constructor that takes in int month,string workHours
 * update information on the GUI
 * 
 * @param int month, string workShift
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.GregorianCalendar;
import java.awt.Color;

public class ScheduleGUI extends JPanel {
    public ScheduleGUI(int month,String shift) {
        super(new GridLayout(1,0));

        String[] columnNames = {"Sunday",
                                "Monday",
                                "Tuesday",
                                "Wednesday",
                                "Thursday",
                                "Friday",
                                "Saturday"};
        int nod,som;
        //Get current year
        GregorianCalendar calendar = new GregorianCalendar();
        int year = calendar.get(GregorianCalendar.YEAR);
        
        //Get first day of month and number of days
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);
        
        
        Object[][] data = new Object[6][7];
        //Draw calendar
        for (int i=1; i<=nod; i++)
		{	int row = new Integer((i+som-2)/7);
        	int column  =  (i+som-2)%7;
        	data[row][column]=i;
		}
        

        JTable scheduleCal = new JTable(data, columnNames)
        {
        	public boolean isCellEditable(int row, int col) {
        		return false;
        	}
        };
        
        //Update work schedule on GUI
        int day=0,workHr=0;
        boolean status=false;
        int startIndex=0;
        for(int i=0;i<shift.length();i++)
        {	if(shift.charAt(i)==' ')
        	{	day = Integer.parseInt(shift.substring(startIndex, i));
        		startIndex = i+1;
        	}
        	else if(shift.charAt(i)=='\n')
	    	{	workHr = Integer.parseInt(shift.substring(startIndex, i));
	    		startIndex = i+1;
	    		status =true;
	    	}
	        if(status==true)
	        {	int row = new Integer((day+som-2)/7);
	        	int column  =  (day+som-2)%7;
	        	String dayHr = day + "    " + workHr +"hr";
	        	data[row][column]=dayHr;
	        	status=false;
	        }
        }
        
        //Calendar GUI Property
        scheduleCal.setShowHorizontalLines(true);
        scheduleCal.setShowVerticalLines(true);
        scheduleCal.setShowGrid(true);
        scheduleCal.setGridColor(Color.black);
        scheduleCal.getTableHeader().setReorderingAllowed(false);
        scheduleCal.setDefaultRenderer(scheduleCal.getColumnClass(0), new CalRenderer());
        scheduleCal.setRowSelectionAllowed(false);
        scheduleCal.setPreferredScrollableViewportSize(new Dimension(500, 100));
        
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(scheduleCal);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    /**
     * @author Chun Hung Tseng
     * Highlight the weekend on the calendar
     */
	static class CalRenderer extends DefaultTableCellRenderer{
	    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
	        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
	        //Weekend
	        if (column == 0 || column == 6){
	             setBackground(Color.pink);
	        }
	        //Weekday
	        else{
	            setBackground(Color.white);
	        }
	        return this; 
	    }
	}

    /**
     * Create the GUI and show it.
     * @author Chun Hung Tseng
     * @version May-11-2011
     * 
     * scheduleGUI function takes in int month,string workHours
     * shows a calendar GUI and calls workSchedule
     * 
     * @param int month,string workHours
     */
    public static void scheduleGUI(int month,String shift) {
    	String monthString;
        switch (month) {
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
        //Create and set up the window.
        JFrame frame = new JFrame(monthString);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set Calendar GUI to the center
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screen.getWidth()-frame.getWidth())/ 2);
        int y = (int) ((screen.getHeight()-frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setSize(x, y);
        frame.setLocationRelativeTo(null);
        
        //Create and set up the content pane.
        ScheduleGUI newContentPane = new ScheduleGUI(month,shift);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
//
//    public static void main(String[] args) {
//        //creating and showing this application's GUI.
//    	int month = 8;
//    	String shift = "14 2\n20 3\n";
//        scheduleGUI(month,shift);
//
//    }
}
