package personal.contacts.program;

import java.time.LocalDate;

/* Class that deals only with editing an existing contact
 * Edits are done in the program's main window by setting the text fields to be editable
 * To directly access the contact info panel found in ContactGUI, that panel has been given no access modifier
 * After the contact is edited, the JSON holding all the contacts is updated
 */
public class EditContact 
{
	private static ContactsPanel contactInfo;
	private static Contact currentContact;
	private static int currentContactIndex;
	private static Boolean errorFound = false;
	private static String errorType = "";
	
	//Stores the original contact and its list index
	public static void setCurrentlyEditing()
	{
		ContactGUI.getContactsJList().setEnabled(false);
		ContactGUI.addContact.setEnabled(false);
		ContactGUI.deleteContact.setEnabled(false);
		ContactGUI.sortConfirm.setEnabled(false);
		currentContact = ContactGUI.getCurrentlySelectedContact();
		currentContactIndex = ContactGUI.getCurrentlySelectedContactIndex();
	}
	
	//Sets the fields in the contactsPanel to be editable by the user
	public static void makeContactInfoEditable()
	{
		contactInfo = ContactGUI.contactInfo;
		contactInfo.setBirthdayComboBoxVisibilty(true);
		contactInfo.setBirthdayFieldVisibilty(false);
		contactInfo.setTextFieldEditability(true);
		
		//Used to set the birthday combo box to the contact's birthday rather than default to the first box item
		contactInfo.setBirthdayComboBox();

		ContactGUI.editContact.setVisible(false);
		ContactGUI.editConfirm.setVisible(true);
		ContactGUI.editCancel.setVisible(true);
		
	}
	
	//Creates a "new" contact with the new fields
	//Adds the edited contact to the contact's current index
	public static void saveEditedContact()
	{
		LocalDate birthday = LocalDate.of(contactInfo.getBirthYear(), contactInfo.getBirthMonthIndex(), contactInfo.getBirthDay());

		Contact newContact = new Contact(contactInfo.getFirstName(), contactInfo.getLastName(),
										 contactInfo.getHomeNum(), contactInfo.getCellNum(),
										 contactInfo.getHomeAddress(), contactInfo.getEmailAddress(),
										 birthday, contactInfo.getNotes());
		
		revertFields();
		ListOfContacts.setAtContactsList(newContact, currentContactIndex);
		ContactGUI.displayContactInfo(newContact);
		JsonReadWrite.writeToFile();
	}
	
	//Discards and changes made by the user by re-setting the current contact at its corresponding index
	public static void revertContactInfoPanel()
	{
		revertFields();
		ContactGUI.displayContactInfo(currentContact);
	}
	
	//Returns the fields to no longer be editable
	private static void revertFields()
	{
		contactInfo.setBirthdayComboBoxVisibilty(false);
		contactInfo.setBirthdayFieldVisibilty(true);
		contactInfo.setTextFieldEditability(false);
		ContactGUI.editContact.setVisible(true);
		ContactGUI.editConfirm.setVisible(false);
		ContactGUI.editCancel.setVisible(false);
		ContactGUI.addContact.setEnabled(true);
		ContactGUI.deleteContact.setEnabled(true);
		ContactGUI.sortConfirm.setEnabled(true);
		ContactGUI.getContactsJList().setEnabled(true);
		ContactGUI.clearContactInfoPanel();
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
		
		if(contactInfo.getHomeNum() == 0)
		{ }
		else if(String.valueOf(contactInfo.getHomeNum()).length() != 10)
		{
			errorFound = true;
			errorType = "num";
		}
		
		if(contactInfo.getCellNum() == 0)
		{ }
		else if(String.valueOf(contactInfo.getCellNum()).length() != 10)
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
