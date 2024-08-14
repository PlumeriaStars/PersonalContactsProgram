package personal.contacts.program;

import java.io.IOException;
import java.util.Calendar;

public class ContactsProgram 
{

	public static void main(String[] args) throws IOException
	{
		Calendar calendar = new Calendar.Builder().setDate(1982, 8-1, 24).build();
		
		Contact c1 = new Contact("John", "Smith", 1234567890, 1472589630, 
								 "123 Street", "jsmith@email.com", 
								 calendar, "notes" 
								);
		Contact c2 = new Contact();
		AddContact.GetUserInput(c2);
								
		AddContact.addToContactsList(c1);
		AddContact.addToContactsList(c2);
		
		for(Contact c: AddContact.getContactsList())
		{
			System.out.println(c);
			System.out.println();
		}

	}

}
