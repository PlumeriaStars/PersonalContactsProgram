package personal.contacts.program;

import java.util.ArrayList;

/* Contains the ArrayList of Contact objects the program will be working with
 * Various methods related to changing or displaying from the ArrayList are here
 */
public class ListOfContacts
{
	private static ArrayList<Contact> contactsList = new ArrayList<Contact>();
	
	public static void addToContactsList(Contact c)
	{
		contactsList.add(c);
	}
	
	public static ArrayList<Contact> getContactsList()
	{
		return contactsList;
	}
	
	public static void viewFirstLastNames()
	{
		for(Contact c: contactsList)
		{
			System.out.println(c.getFirstName() + " " + c.getLastName());
		}
	}
	
	public static void viewLastFirstNames()
	{
		for(Contact c: contactsList)
		{
			System.out.println(c.getLastName() + " " + c.getFirstName());
		}
	}
	
	public static void viewAllContactInformation()
	{
		for(Contact c: contactsList)
		{
			System.out.println(c);
		}
	}

}
