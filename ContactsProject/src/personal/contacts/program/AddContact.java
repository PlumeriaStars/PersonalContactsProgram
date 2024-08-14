package personal.contacts.program;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class AddContact 
{
	private static ArrayList<Contact> contactsList = new ArrayList<Contact>();
	
	public static void GetUserInput(Contact c) throws IOException
    {
    	String notCorrect;
		Scanner kb = new Scanner(System.in);
		System.out.println();
		System.out.println("You will be asked to enter the contact's information");
		System.out.println("If you don't know what to put, type in N/A");
		System.out.println();

		try
		{
			String firstName;
			do
			{
				System.out.print("What is the contact's first name? ");
				firstName = kb.nextLine();
				System.out.println("Is this information correct?(Y/N) :: " + firstName);
				System.out.print("  ");
				notCorrect = kb.next();
				kb.nextLine();
			} while(notCorrect.equals("N") || notCorrect.equals("n"));
			c.setFirstName(firstName);
			System.out.println();
			
			String lastName;
			do
			{
				System.out.print("What is the contact's last name? ");
				lastName = kb.nextLine();
				System.out.println("Is this information correct?(Y/N) :: " + lastName);
				System.out.print("  ");
				notCorrect = kb.next();
				kb.nextLine();
			} while(notCorrect.equals("N") || notCorrect.equals("n"));
			c.setLastName(lastName);
			System.out.println();

			long homeNumber;
			do
			{
				System.out.print("What is the contact's home number? ");
				homeNumber = kb.nextLong();
				kb.nextLine();
				System.out.println("Is this information correct?(Y/N) :: " + homeNumber);
				System.out.print("  ");
				notCorrect = kb.next();
				kb.nextLine();
			} while(notCorrect.equals("N") || notCorrect.equals("n"));
			c.setHomeNumber(homeNumber);
			System.out.println();

			long cellNumber;
			do
			{
				System.out.print("What is the contact's cell number? ");
				cellNumber = kb.nextLong();
				kb.nextLine();
				System.out.println("Is this information correct?(Y/N) :: " + cellNumber);
				System.out.print("  ");
				notCorrect = kb.next();
				kb.nextLine();
			} while(notCorrect.equals("N") || notCorrect.equals("n"));
			c.setCellNumber(cellNumber);
			System.out.println();

			String homeAddress;
			do
			{
				System.out.print("What is the contact's home address? ");
				homeAddress = kb.nextLine();
				System.out.println("Is this information correct?(Y/N) :: " + homeAddress);
				System.out.print("  ");
				notCorrect = kb.next();
				kb.nextLine();
			} while(notCorrect.equals("N") || notCorrect.equals("n"));
			c.setHomeAddress(homeAddress);
			System.out.println();

			String emailAddress;
			do 
			{
				System.out.print("What is the contact's email address? ");
				emailAddress = kb.nextLine();
				System.out.println("Is this information correct?(Y/N) :: " + emailAddress);
				System.out.print("  ");
				notCorrect = kb.next();
				kb.nextLine();
			} while(notCorrect.equals("N") || notCorrect.equals("n"));
			c.setEmailAddress(emailAddress);
			System.out.println();

			//note -- Months start at 0 so January == 0
			//     -- Days start at 1 
			int bMonth;
			int bDay;
			int bYear;
			do 
			{
				System.out.print("What month was the contact born? ");
				bMonth = kb.nextInt();
				kb.nextLine();
				System.out.print("What day was the contact born? ");
				bDay = kb.nextInt();
				kb.nextLine();
				System.out.print("What year was the contact born? ");
				bYear = kb.nextInt();
				kb.nextLine();
				System.out.println("Is this information correct?(Y/N) :: " + bMonth +"/" + bDay + "/" + bYear);
				System.out.print("  ");
				notCorrect = kb.next();		
				kb.nextLine();
			} while(notCorrect.equals("N") || notCorrect.equals("n"));
			Calendar birthday = new Calendar.Builder().setDate(bYear, bMonth-1, bDay).build();
			c.setBirthday(birthday);
			System.out.println();

			String notes;
			do 
			{
				System.out.print("Any extra notes? ");
				notes = kb.nextLine();
				System.out.println("Is this information correct?(Y/N) :: " + notes);
				System.out.print("  ");
				notCorrect = kb.next();			
				kb.nextLine();
			} while(notCorrect.equals("N") || notCorrect.equals("n"));
			c.setNotes(notes);
			System.out.println();
			
		}

		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}

		kb.close();
    }
	
	public static void addToContactsList(Contact c)
	{
		contactsList.add(c);
	}
	
	public static ArrayList<Contact> getContactsList()
	{
		return contactsList;
	}
	
}
