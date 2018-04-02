import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

/**
 * Creates and manages a Calendar
 * @author Dev Patel 
 * Version: 1
 */
public class CalendarClass extends GregorianCalendar{

	static  TreeMap<GregorianCalendar, TreeSet<Event>> eventsMap;
	public static GregorianCalendar calendarN = new GregorianCalendar();
	private static Scanner fileScanner;
	String[] monthNames = {"January","February","March","April","May","June","July","August",
			"September","October","November","December"};
	String[] dayNames = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

	/**
	 * Constructor for the Calendar Class
	 */
	public CalendarClass()
	{
		eventsMap = new TreeMap<>();
	}

	/**
	 * Returns the value of the next Month
	 * @return nextMonth
	 */  
	public String nextMonth(GregorianCalendar calendar) {
		calendar.add(Calendar.MONTH, 1);
		String nextMonth= printMonth(calendar);
		return nextMonth;
	}

	/**
	 * Returns the value of the previous Month
	 * @return previousMonth
	 */  
	public String previousMonth(GregorianCalendar calendar) {
		calendar.add(Calendar.MONTH, -1);
		String previousMonth= printMonth(calendar);
		return previousMonth;
	}	

	/**
	 * Prints the Month for user
	 */      
	public String printMonth(GregorianCalendar calendar){
		// gets values
		int monthIndex= calendar.get(Calendar.MONTH); 
		int yearValue= calendar.get(Calendar.YEAR);

		GregorianCalendar  calMonthStart = new GregorianCalendar(yearValue, monthIndex, 1); 
		int dayIndex = calMonthStart.get(Calendar.DAY_OF_WEEK)-1; 
		String d = dayNames[dayIndex]; 
		int daysInMonth=calendar.getActualMaximum(Calendar.DAY_OF_MONTH); 
		
		System.out.println(monthNames[monthIndex]+"  "+ yearValue);
		System.out.println("Su  Mo  Tu  We  Th  Fr  Sa");   

		for(int j=0; j<dayIndex;j++) 
		{
			System.out.print("    ");  
		}
		for(int i=1; i<=daysInMonth; i++)  
		{	
			String dayS;
			if
			(i<10)
			{dayS="0"+i;}
			else
			{dayS=String.valueOf(i);}
			int monthIndx= calendar.get(Calendar.MONTH)+1;
			String monthS;
			if( monthIndx<10)
			{monthS="0"+monthIndx;}
			else
			{monthS=String.valueOf( monthIndx);}
			
			String yearS= String.valueOf(calendar.get(Calendar.YEAR));
			String dateOfi= monthS+"/"+dayS+"/"+yearS;
			GregorianCalendar key=new GregorianCalendar();
			key= stringToGC(dateOfi);
			
			if(i == calendar.get(Calendar.DAY_OF_MONTH) &&  calendarN.get(Calendar.MONTH)==calendar.get(Calendar.MONTH))
				System.out.print("["+i+"] "); 
			else if(eventsMap.containsKey(key))
				System.out.print("{"+i+"} "); 
			else if(i<10)
				System.out.print(i+"   "); 
			else
				System.out.print(i+"  ");
			if( ((dayIndex+ i)%7==0) || (i==daysInMonth) ){System.out.println("\n");}
		}
		String x="";
		return x;
	} 

	/**
	 * Returns the value of the next day
	 * @return nextDay
	 */  
	public String nextDay(GregorianCalendar calendar) {     	
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		String nextDay= printDay(calendar);
		return nextDay;
	}
	/**
	 * Returns the value of the previous day
	 * @return previousDay
	 */  
	public String previousDay(GregorianCalendar calendar) {    
		calendar.add(Calendar.DAY_OF_WEEK, -1);
		String previousDay= printDay(calendar);
		return previousDay;
	}    

	/**
	 * Prints Today's Date
	 * 
	 */      
	public String printDay(GregorianCalendar calendar){
		String today  = dayNames[calendar.get(Calendar.DAY_OF_WEEK)-1];
		String monthShort  = monthNames[calendar.get(Calendar.MONTH)];
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		int year = calendar.get(Calendar.YEAR);
		System.out.println(today+", "+monthShort+" "+ dayOfMonth+", "+year);
		String calendarString = gcToString(calendar);
		getEventsOnThisDay(calendarString);
		String x="";
		return x;    	
	}

	/**
	 * Converts a String to a GregorianCalendar object
	 * @param string
	 * @return c
	 */
	public GregorianCalendar stringToGC(String string){
		
		Date dummyDate = null;
		GregorianCalendar c = new GregorianCalendar();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		try{
			dummyDate = dateFormatter.parse(string);
			c.setTime(dummyDate);
		}
		catch(ParseException e) {
			System.out.println(e.getMessage());
		}
		return c;
	}

	/**
	 * Converts a GregorianCalendar to a String object
	 * @param calendar
	 * @return x
	 */
	public String gcToString(GregorianCalendar calendar){
		
		String dayS;
		int dayI=calendar.get(Calendar.DAY_OF_MONTH);
		if(dayI<10){dayS="0"+dayI;}
		else{dayS=String.valueOf(dayI);}

		String monthS;
		int monthI=calendar.get(Calendar.MONTH)+1;
		if(monthI<10){monthS="0"+monthI;}
		else{monthS=String.valueOf(monthI);}		
		
		String yearS;
		int yearI=calendar.get(Calendar.YEAR);
		yearS=String.valueOf(yearI);
		
		  String x=monthS+"/"+dayS+"/"+yearS;
		  return x;
	}

	/**
	 * Gets the events for a particular day 
	 * @return s
	 */
	public String getEventsOnThisDay(String date){ 
		String s = "";

		String title="",startT="",endT="";
		Date dummyDate = null;
		GregorianCalendar c = new GregorianCalendar();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

		try{
			dummyDate = dateFormatter.parse(date);
			c.setTime(dummyDate);
		}
		catch(ParseException e) {
			System.out.println(e.getMessage());
		}
		//  check the events map if that key exists.	
		TreeSet<Event> events = new TreeSet<>();
		
		if (eventsMap.containsKey(c))
		{
			events = eventsMap.get(c);
			if (!events.equals(null))
				for (Event e : events){
					title=e.getTitle();
					startT=e.getStartTime();
					endT=e.getEndTime();		
					System.out.println(title+" "+startT+" - "+endT+"\n");
				}
		}
		else 
			System.out.println("No Events Scheduled");

		return s;
	}

	/**
	 * Prints all events 
	 * @return s
	 */	
	public String printAllEvents(){
		String s = "";
		String nameOfDay="", monthLong="", startT="",endT="",title="";
		int dayOfMonth;
		for(GregorianCalendar key : eventsMap.keySet()){

			for (Event e : eventsMap.get(key)){
				nameOfDay=dayNames[key.get(Calendar.DAY_OF_WEEK)-1];
				monthLong=monthNames[key.get(Calendar.MONTH)];
				dayOfMonth=key.get(Calendar.DAY_OF_MONTH);
				startT = e.getStartTime().toString();
				endT = e.getEndTime().toString(); 
				title=e.getTitle();
				System.out.println(nameOfDay+" "+monthLong+" "+dayOfMonth+" "+startT+" - "+endT+" "+title);
			}
		}
		return s;
	}

	/**
	 * Deletes all events 
	 */	
	public void deleteAll(){
		eventsMap.clear();;
	}

	/**
	 * Deletes selected events 
	 */	
	public void deleteSelected(String dateStringToDelete){ 

		Date dummyDate = null;
		GregorianCalendar key = new GregorianCalendar() ;

		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		try{
			dummyDate= dateFormatter.parse(dateStringToDelete);
			key.setTime(dummyDate);
		}
		catch(ParseException e) {
			System.out.println(e.getMessage());
		}

		eventsMap.remove(key);
	}

	/**
	 * Quits and saves events 
	 * @throws IOException
	 */	
	public  void quit(){ 
		String title="",date="",startT="",endT="";
		try{
			PrintWriter printWriter = new PrintWriter("events.txt");
			for(GregorianCalendar key : eventsMap.keySet()){

				for (Event e : eventsMap.get(key)){
					title=e.getTitle();
					date=e.getInputDate();
					startT=e.getStartTime();
					endT=e.getEndTime();
					printWriter.println(title+" "+date+" "+startT+" "+endT);
				}
			}
			printWriter.close();
			System.exit(0);
		}
		catch(IOException e){System.out.println("IOException");}
	}

	/**
	 * Loads events
	 */		
	public void load(){
	try{
			fileScanner = new Scanner(new File("events.txt"));

		}
		catch(Exception e){
			System.out.println("could not find file");
		}

		if(fileScanner.hasNext() != true)
		{
			System.out.println("This is the first run");	
		}else
		{     
			
			while(fileScanner.hasNext())
			{
				String titleReader = fileScanner.next(); 
				String dateReader = fileScanner.next(); 
				String startTimeReader = fileScanner.next(); 
				String endTimeReader = fileScanner.next(); 
				createEvent(titleReader,dateReader,startTimeReader,endTimeReader);
			}
		}
		fileScanner.close();
		System.out.println("Events Loaded");
	}

	/**
	 * Creates an Event
	 * @param title
	 * @param inputDate
	 * @param startTime
	 * @param endTime
	 */
	public void createEvent(String title,String inputDate,String startTime,String endTime){
		Event newEvent = new Event(title, inputDate, startTime, endTime);  
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(newEvent.getUserInputStringToDate());

		if(!checkEventCollision(newEvent)){

			if(eventsMap.containsKey(c)){
				eventsMap.get(c).add(newEvent);  
			} else{
				TreeSet<Event> events = new TreeSet<>(); 
				events.add(newEvent);
				eventsMap.put(c, events);
			}
		} 
	}

	/**
	 * Checks event Collision. Two events at same time 
	 * @param event
	 * @return collision
	 */
	public  boolean checkEventCollision(Event event){  
		boolean collision=false;
		for(GregorianCalendar key : eventsMap.keySet()){
			
			if(key.getTime().equals(event.userInputStringToDate))
			{
				for(Event e : eventsMap.get(key)){
					if(e.startTime.equals(event.startTime) )
					{
						System.out.println("Collision!!");
						collision=true;
					}
				}
			}
		}
		return collision;
	}
}
