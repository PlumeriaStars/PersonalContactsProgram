package personal.contacts.program;

import java.util.Calendar;

public class ContactsProgram 
{

	public static void main(String[] args) 
	{
		Calendar calendar = new Calendar.Builder().setDate(1982, 8, 24).build();
		
		Contact c1 = new Contact("John", "Smith", 1234567890, 1472589630, 
								 "123 Street", "jsmith@email.com", 
								 calendar, "notes" 
								);
								
		System.out.println(c1);

	}

}
