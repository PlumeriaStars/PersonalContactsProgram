package personal.contacts.program;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/* Class that deals only with adding a new contact
 * When adding a contact, a new dialog appears using the panel created by ContactsPanel
 * Also includes two buttons - Add    -> adds the newly created contact to the contacts list
 * 							   Cancel -> closes the pop-up by setting its visibility to false
 * The user must close the pop-up in order to return to the main program window
 * After the contact is successfully added, the JSON holding all the contacts is updated
 */
public class AddContact extends JDialog implements ActionListener
{
	private JButton addButton, cancelButton;
	private static ContactsPanel addNewContact;
	private static Boolean errorFound = false;
	private static String errorType = "";
	
	//Constructor - creates the pop-up
	public AddContact() 
	{ 
		
		this.setTitle("Add New Contact");
		this.setPreferredSize(new Dimension(450, 300));
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		this.setLocationRelativeTo(null);
		
		addNewContact = new ContactsPanel("New Contact");
		this.add(addNewContact);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.lightGray);
		buttonPanel.setPreferredSize(new Dimension(this.getWidth(), 40));
		addButton = new JButton("Add");
		addButton.setFont(this.getFont());
		addButton.setBackground(Color.lightGray);
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(this.getFont());
		cancelButton.setBackground(Color.lightGray);
		addButton.addActionListener(this);
		cancelButton.addActionListener(this);
		buttonPanel.add(addButton);
		buttonPanel.add(cancelButton);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		
		this.pack();
		this.setVisible(true);
			
	}
	
	//Debug - Gets user input to create a contact through Console window
	//		  Takes in a Contact object and changes its attributes
	protected static void GetUserInput(Contact c) throws IOException
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
			
			String bString = bYear + "-" + bMonth + "-" + bDay;
			c.setBirthday(bString);
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

	//Button Actions
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//If the Add button is pressed, create the contact and add it to the contacts list
		if(e.getSource() == addButton)
		{
			//Check if the user triggered any errors when filling out the fields
			checkForErrors();
			
			//If the error flag isn't tripped, add the new contact
			if(!errorFound)
			{
				LocalDate birthday = LocalDate.of(addNewContact.getBirthYear(), addNewContact.getBirthMonthIndex(), addNewContact.getBirthDay());
				
				Contact newContact = new Contact(addNewContact.getFirstName(), addNewContact.getLastName(),
												 addNewContact.getHomeNum(), addNewContact.getCellNum(),
												 addNewContact.getHomeAddress(), addNewContact.getEmailAddress(),
												 birthday, addNewContact.getNotes());

				ContactComparators compTemp = new ContactComparators();
				ListOfContacts.addToContactsList(newContact);
				ListOfContacts.sortContactsList(compTemp.getCurrentComparator(), compTemp.getCurrentComparatorOrder());
				JsonReadWrite.writeToFile();
				this.setVisible(false);
			}
			else
			{	
				//"num" type errors means the home/cell number fields did not contain 10 digits
				if(errorType.equalsIgnoreCase("num"))
					new ErrorDialog.NumberLengthError();
				
				//Once the user closes the pop-up, reset the error flag/type
				revertErrors();
			}
					
		}
		//If the Cancel button is pressed, set the pop-up to no longer be visible
		else if(e.getSource() == cancelButton)
		{
			this.setVisible(false);
		}
		
	}
	
	public static Boolean getErrorFlag()
	{
		return errorFound;
	}
	
	public static String getErrorType()
	{
		return errorType;
	}
	
	//There is a chance contacts don't have a number
	//In that case, no error should be found if the field is empty
	//If the field is not empty and does not have 10 digits, trigger the error flag
	public static void checkForErrors()
	{
			
	   if(addNewContact.getHomeNum() == 0)
	   { }
	   else if(String.valueOf(addNewContact.getHomeNum()).length() != 10)
	   {
	      errorFound = true;
	      errorType = "num";
	   }
			
	   if(addNewContact.getCellNum() == 0)
	   { }
	   else if(String.valueOf(addNewContact.getCellNum()).length() != 10)
	   {
	      errorFound = true;
	      errorType = "num";
	   }
	}
	
	public static void revertErrors() 
	{
		errorFound = false;
		errorType = "";
	}
	
}
