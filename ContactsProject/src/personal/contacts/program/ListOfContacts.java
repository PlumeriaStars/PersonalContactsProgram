package personal.contacts.program;

import java.util.ArrayList;

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

}
