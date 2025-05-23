package personal.contacts.program;

import java.util.ArrayList;
import java.util.Comparator;

/* Contains various Comparators to sort Contact objects based on different fields
 * Default sort is ALPHABETICAL ORDER (ASCENDING) - 
 * Can use Collections.reverseOrder(Comparator) to achieve the opposite(descending)
 */
public class ContactComparators 
{
	private static ArrayList<Comparator<Contact>> comparatorList = new ArrayList<Comparator<Contact>>();
	private static int currentComparatorIndex = 0;
	private static int currentComparatorOrder = 0;
	
	//Default constructor
	public ContactComparators() 
	{ 
		addComparatorsToList();
		setCurrentComparator(currentComparatorIndex);
	}
	
	//Adds the comparators found in ContactComparators to a list
	private static void addComparatorsToList() 
	{	
		comparatorList.add(new ContactComparators.firstLastNameComparator());
		comparatorList.add(new ContactComparators.lastFirstNameComparator());
	}
		
	//Sets the index corresponding to which comparator in the comparatorList will be used
	private static void setCurrentComparator(int index)
	{
		switch(index)
		{
			case 0:
				currentComparatorIndex = index;
				break;
			case 1:
				currentComparatorIndex = index;
				break;
			default:
				currentComparatorIndex = 0;
		}
	}
		
	//Returns the current comparator being used based on the value of currentComparatorIndex
	public Comparator<Contact> getCurrentComparator()
	{
		switch(currentComparatorIndex)
		{
			case 0:
				return comparatorList.get(0);
			case 1:
				return comparatorList.get(1);
			default:
				return comparatorList.get(0);
				
		}
	}
	
	//Sets the current order - Descending or Ascending
	public void setCurrentComparatorOrder(int order)
	{
		currentComparatorOrder = order;
	}
	
	//Returns the current order - Descending or Ascending
	public int getCurrentComparatorOrder()
	{
		return currentComparatorOrder;
	}
	
	//Compares two contacts based on their combined "First Last" name string
	public static class firstLastNameComparator implements Comparator<Contact>
	{
		@Override
		public int compare(Contact c1, Contact c2) 
		{
			String c1FullName = c1.getFirstName() + " " + c1.getLastName();
			String c2FullName = c2.getFirstName() + " " + c2.getLastName();
			
			if(c1FullName.compareToIgnoreCase(c2FullName) == 0)
				return 0;
			//If the full name(first last) of this contact is alphabetically before the comparing contact
			else if(c1FullName.compareToIgnoreCase(c2FullName) < 0)
			{
				return c1FullName.compareToIgnoreCase(c2FullName);
			}
			//Else, the full name(first last) of this contact is alphabetically after the comparing contact
			else
				return c1FullName.compareToIgnoreCase(c2FullName);
		}		
	}
	
	//Compares two contacts based on their combined "Last First" name string
	public static class lastFirstNameComparator implements Comparator<Contact>
	{
		@Override
		public int compare(Contact c1, Contact c2) 
		{
			String c1FullName = c1.getLastName() + " " + c1.getFirstName();
			String c2FullName = c2.getLastName() + " " + c2.getFirstName();
			
			if(c1FullName.compareToIgnoreCase(c2FullName) == 0)
				return 0;
			//If the full name(last first) of this contact is alphabetically before the comparing contact
			else if(c1FullName.compareToIgnoreCase(c2FullName) < 0)
			{
				return c1FullName.compareToIgnoreCase(c2FullName);
			}
			//Else, the full name(last first) of this contact is alphabetically after the comparing contact
			else
				return c1FullName.compareToIgnoreCase(c2FullName);
		}		
	}
}
