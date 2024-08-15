package personal.contacts.program;

import java.util.Comparator;

/* Contains various Comparators to sort Contact objects based on different fields
 * Default sort is ALPHABETICAL ORDER (DESCENDING) - 
 * Can use Collections.reverseOrder(Comparator) to achieve the opposite(ascending)
 */
public class ContactComparators 
{
	//Default constructor
	public ContactComparators() { }
	
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
