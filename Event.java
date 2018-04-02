
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * Creates and manages events for the Calendar 
 * @author Dev Patel 
 */

public class Event implements Comparable<Event> {
	public String title;
	public String inputDate;
	public String startTime; 
	public String endTime;
	public Date userInputStringToDate;

	/**
	 * Constructor for Event Class
	 */
	public Event(String title,String inputDate,String startTime,String endTime)
	{
		this.title=title;
		this.inputDate=inputDate;	
		this.startTime=startTime;
		this.endTime=endTime;		 
		dateObjectConverter();
	}

	/**
	 * Set's an event's title
	 * @param title title of event
	 */
	public void setTitle(String title){
		this.title=title;}
	/**
	 * Set's an event's date
	 * @param inputDate date of event
	 */
	public void setInputDate(String inputDate){
		this.inputDate=inputDate;}
	/**
	 * Set's an event's start time
	 * @param startTime  start time of event
	 */
	public void setStartTime(String startTime){
		this.startTime=startTime;}
	/**
	 * Set's an event's end time
	 * @param endTime  end time of event
	 */
	public void setEndTime(String endTime){
		this.endTime=endTime;}
	/**
	 * Set's an event's date object of the user input date
	 * @param date the date object for the date of the event
	 */
	public void setUserInputStringToDate(String date){
		inputDate=date;dateObjectConverter();}


	/**
	 * Get's an event's title
	 * @return title
	 */
	public String getTitle(){
		return title;}
	/**
	 * Get's an event's input date
	 * @return inputDate 
	 */
	public String getInputDate(){
		return inputDate;}
	/**
	 * Get's an event's start time
	 * @return startTime
	 */
	public String getStartTime(){
		return startTime;}
	/**
	 * Ge'ts an event's for end time
	 * @return endTime
	 */
	public String getEndTime(){
		return endTime;}
	/**
	 * Get's an event's date object of event date value
	 * @return userInputStringToDate
	 */
	public Date getUserInputStringToDate(){
		return userInputStringToDate;}
	
	/**
	 * Returns details about an event
	 * @return s
	 */
	public String toString()
	{
		String s = this.title + " " + this.inputDate + " " + this.startTime;
		if (!endTime.equals(null))
			s += " " + this.endTime;
		return s;
	}

	/**
	 *Converts userInput to date
	 */
	public void dateObjectConverter()
	{
		String concatenated = this.inputDate;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");  
		try {
			userInputStringToDate = dateFormatter.parse(concatenated);
		}
		catch(ParseException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Equals to function
	 */
	public boolean equals(Object other){
		Event that = (Event) other;  
		return this.compareTo(that) == 0; 
	}
	
	/**
	 * Compare to function for sorting the events
	 */
	public int compareTo(Event other){
		//comparing by date  
		int dateCmp = this.userInputStringToDate.compareTo(other.userInputStringToDate);
		if(dateCmp != 0)
			return dateCmp;
		String thisTimeString="",otherTimeString="";
		String thisTime= this.getStartTime();
		String otherTime= other.getStartTime();	
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");   
		try{
			Date thisTimeHHmm= timeFormatter.parse(thisTime);
			Date otherTimeHHmm= timeFormatter.parse(otherTime);
			
			thisTimeString =timeFormatter.format(thisTimeHHmm);
			otherTimeString =timeFormatter.format(otherTimeHHmm);
			
		}catch(ParseException e){e.printStackTrace();}
		
		int timeCmp = thisTimeString.compareTo(otherTimeString);
		if(timeCmp!=0)
			return timeCmp;

		return 0;
	}
}	