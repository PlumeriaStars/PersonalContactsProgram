package personal.contacts.program;

import java.io.IOException;

import javax.swing.UnsupportedLookAndFeelException;

public class ContactsProgram 
{

	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		JsonReadWrite.readFromFile();
		ListOfContacts.sortContactsList(new ContactComparators.firstLastNameComparator(), 0);	
		

		@SuppressWarnings("unused")
		ContactGUI programGUI = new ContactGUI();
		
		//Write the current list of contacts to file before closing application
		JsonReadWrite.writeToFile();

	}

}
