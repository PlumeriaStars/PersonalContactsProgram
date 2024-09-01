package personal.contacts.program;

import java.io.IOException;
import java.util.Calendar;
import javax.swing.UnsupportedLookAndFeelException;

public class ContactsProgram 
{

	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		Calendar calendar = new Calendar.Builder().setDate(1982, 8-1, 24).build();
		
		Contact c1 = new Contact("John", "Smith", 1234567890, 1472589630, 
								 "123 Street", "jsmith@email.com", 
								 calendar, "notes" 
								);
		
		Contact c3 = new Contact("Anne", "Taylor", 1234567890, 1472589630, 
				 				 "123 Street", "annesm@email.com", 
				 				 calendar, "notes" 
								);
		
		Contact c4 = new Contact("Janet", "Goose", 1234567890, 1472589630, 
				 				 "123 Street", "jsmith@email.com", 
				 				 calendar, "notes" 
								);
								
		ListOfContacts.addToContactsList(c1);
		ListOfContacts.addToContactsList(c3);
		ListOfContacts.addToContactsList(c4);
		
		//Collections.sort(ListOfContacts.getContactsList(), Collections.reverseOrder(new ContactComparators.lastFirstNameComparator()));
		ListOfContacts.sortContactsList(new ContactComparators.firstLastNameComparator());	
		
		//Collections.sort(ListOfContacts.getContactsList(), new ContactComparators.lastFirstNameComparator());

		ContactGUI programGUI = new ContactGUI();

	}

}
