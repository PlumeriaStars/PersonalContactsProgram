package personal.contacts.program;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.DefaultListModel;

/* Contains the DefaultListModel of Contact objects the program will be working with
 * Can add to, remove from index, and return the DefaultListModel
 * Sorting method for the DefaultListModel created
 */
public class ListOfContacts
{
	private static DefaultListModel<Contact> contactsList = new DefaultListModel<Contact>();
	
	//Add Contact c to the end of the DefaultListModel
	public static void addToContactsList(Contact c)
	{
		contactsList.addElement(c);
	}
	
	//Return the full DefaultListModel
	public static DefaultListModel<Contact> getContactsList()
	{
		return contactsList;
	}
	
	//Remove from the DefaultListModel at the index specified
	public static void removeFromContactList(int index)
	{
		contactsList.remove(index);
	}
	
	//In order to sort the DefaultListModel, first copy the contents of the model into an ArrayList
	//Then, call the ArrayList's sorting method () using a comparator defined in ContactComparators
	//Clear the DefaultListModel, add the elements of the sorted ArrayList back into the DefaultListModel
	public static void sortContactsList(Comparator<Contact> comparator)
	{
		ArrayList<Contact> cList = Collections.list(contactsList.elements());
		Collections.sort(cList, comparator);;
		contactsList.clear();
		
		for(Contact c: cList)
			contactsList.addElement(c);
	}

}
