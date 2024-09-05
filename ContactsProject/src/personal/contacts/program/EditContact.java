package personal.contacts.program;

import java.util.Calendar;

/* Class that deals only with editing an existing contact
 * Edits are done in the program's main window by setting the text fields to be editable
 * To directly access the contact info panel found in ContactGUI, that panel has been given no access modifier
 */
public class EditContact 
{
	private static ContactsPanel contactInfo;
	private static Contact currentContact;
	private static int currentContactIndex;
	
	//Stores the original contact and its list index
	public static void setCurrentlyEditing()
	{
		ContactGUI.getContactsJList().setEnabled(false);
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
		Calendar birthday = new Calendar.Builder().setDate(contactInfo.getBirthYear(),
														   contactInfo.getBirthMonthIndex(),
														   contactInfo.getBirthDay()).build();
		Contact newContact = new Contact(contactInfo.getFirstName(), contactInfo.getLastName(),
										 contactInfo.getHomeNum(), contactInfo.getCellNum(),
										 contactInfo.getHomeAddress(), contactInfo.getEmailAddress(),
										 birthday, contactInfo.getNotes());
		
		revertFields();
		ListOfContacts.setAtContactsList(newContact, currentContactIndex);
	}
	
	//Discards and changes made by the user by re-setting the current contact at its corresponding index
	public static void revertContactInfoPanel()
	{
		revertFields();
		contactInfo.setFieldData(currentContact);	
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
		ContactGUI.getContactsJList().setEnabled(true);
		
		contactInfo.revalidate();
		contactInfo.repaint();
	}
}
