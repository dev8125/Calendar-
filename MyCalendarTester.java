import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Tests if all ascpects of the Calendar work 
 * @author Dev Patel 
 */
public class MyCalendarTester {

	/**
	 * Main function to test the Calendar Program
	 */
	public static void main(String[] args){

		// Creating a calendar object myCalendar		
		CalendarClass myCalendar = new CalendarClass();
		// Creating a scanner object inputScanner to read user input
		Scanner inputScanner = new Scanner(System.in);
		// Declaring object of type String for userInput
		String userInput; 
		// Creating a GregorianCalendar object calendar 
		GregorianCalendar calendar = new GregorianCalendar(); 

		// Printing the Calendar month view
		myCalendar.printMonth(calendar);

		// Creating a do-While loop to provide the user with the required navigation options
		do{
			System.out.println("Select one of the following options: ");
			System.out.println("[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");  
			userInput = inputScanner.nextLine().toUpperCase();

			switch(userInput){
			// Load
			case "L":  	System.out.println(" ");
						myCalendar.load();
						break;
			//View: Day or Month		
			case "V":  System.out.println("[D]ay view or [M]view ? ");
						String viewInput = inputScanner.nextLine();
						if(viewInput.equals("D")|| viewInput.equals("d")){
							String dayNavigation;
								myCalendar.printDay(calendar);  
								do{   
								System.out.println("[P]revious or [N]ext or [M]ain menu ?");
								 dayNavigation = inputScanner.nextLine();
								if(dayNavigation.equals("P")||dayNavigation.equals("p")){myCalendar.previousDay(calendar);}
								else if(dayNavigation.equals("N")||dayNavigation.equals("n")){myCalendar.nextDay(calendar);}
							} while(! (dayNavigation.equals("M") ||  dayNavigation.equals("m")));

						}else if(viewInput.equals("M") ||viewInput.equals("m")){
							myCalendar.printMonth(calendar); 
							String monNavigation;
							do{ 
							System.out.println("[P]revious or [N]ext or [M]ain menu ?");
							 monNavigation = inputScanner.nextLine();
							if(monNavigation.equals("P")||monNavigation.equals("p")){ myCalendar.previousMonth(calendar); }
							else if(monNavigation.equals("N")||monNavigation.equals("n")){myCalendar.nextMonth(calendar);}
							} while(! (monNavigation.equals("M") ||  monNavigation.equals("m")));
						}
						break;		
			// Create			
			case "C":  	
					   System.out.println("\nTitle: ");
					   String titleInput = inputScanner.nextLine();
					   System.out.println("\nDate: ");
					   String dateInput = inputScanner.nextLine();
					   System.out.println("\nStart Time (24:00 format):");
					   String startTInput = inputScanner.nextLine();
					   System.out.println("\nEnd Time (24:00 format): ");
					   String endTInput = inputScanner.nextLine();
					   if((endTInput.length()==0 || endTInput==null)){endTInput="23:59";}
						myCalendar.createEvent(titleInput, dateInput, startTInput, endTInput);
						break;
			// Go To			
			case "G":  System.out.println("\nPlease enter date (MM/DD/YYYY): ");
					   String goToInput = inputScanner.nextLine();
					   System.out.print("\n");
					   myCalendar.printDay(myCalendar.stringToGC(goToInput));
					   break;
			// Event List		   
			case "E":  System.out.println(" ");
					   myCalendar.printAllEvents();
					   System.out.println(" ");
					   break;	
			// Delete		   
			case "D":  System.out.println("[S]elected or [A]ll ? ");
					   String deleteInput = inputScanner.nextLine();
					   if(deleteInput.equals("s") || deleteInput.equals("S") ){
						    System.out.println("\nPlease enter date (MM/DD/YYYY):");
					   		String dateDInput = inputScanner.nextLine();
							myCalendar.deleteSelected(dateDInput); 
					   }else if(deleteInput.equals("A")||deleteInput.equals("a"))
						    myCalendar.deleteAll();
					   break;
			}

		}
		// Quit
		while(!userInput.equals("Q"));
		myCalendar.quit();
	}
}