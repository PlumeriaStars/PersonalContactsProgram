package personal.contacts.program;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;

/* Actions listeners, derived from AbstractAction, that are used by the GUI
 * GUI will have buttons and a menu bar, providing two different ways to access an action (ex. "Add") 
 * Shared actions that can be called by different components are stored here
 */
public class GUIActionListeners 
{
	public GUIActionListeners() { }
	
	//The add action calls the AddContact class when a component linked to this action is called
	public static class addContactAction extends AbstractAction
	{
		public addContactAction(String desc)
		{
			putValue(SHORT_DESCRIPTION, desc);
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			new AddContact();
		}		
	}
	
	//The add action calls the DeleteContact class when a component linked to this action is called
	public static class deleteContactAction extends AbstractAction
	{		
		public deleteContactAction(String desc)
		{
			putValue(SHORT_DESCRIPTION, desc);
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{	
			if(ContactGUI.getCurrentlySelectedContact() != null)
				new DeleteContact();
		}			
	}
	
	//The edit action calls the EditContact class when a component linked to this action is called
	//Buttons linked to editing in ContactGUI have no access modifier declared
	// so that they can be accessed here, outside of the class
	public static class editContactAction extends AbstractAction
	{			
		public editContactAction(String desc)
		{
			putValue(SHORT_DESCRIPTION, desc);
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{		
			if(ContactGUI.getCurrentlySelectedContact() != null)
			{
				EditContact.setCurrentlyEditing();
				
				if(e.getSource().equals(ContactGUI.editContact) || e.getSource() instanceof JMenuItem)
					EditContact.makeContactInfoEditable();
				
				if(e.getSource().equals(ContactGUI.editConfirm))
				{
					//Check if the user triggered any errors when typing in the edits
					EditContact.checkForErrors();
					
					//If the error flag isn't tripped, continue saving the edits
					if(!EditContact.getErrorFlag())
					   EditContact.saveEditedContact();
					//Else, an error was found
					else
					{
						//"num" type errors means the home/cell number fields did not contain 10 digits
						if(EditContact.getErrorType().equalsIgnoreCase("num"))
						{
							//Display error pop-up
							new ErrorDialog.NumberLengthError();
							//Once the user closes the pop-up, reset the error flag/type
							EditContact.revertErrors();
						}
					}
				}
				
				if(e.getSource().equals(ContactGUI.editCancel))
					EditContact.revertContactInfoPanel();
			}
		}		
	}
	
	//The sort action will sort the Contacts list based on the sorting style chosen by the user
	//Sorting is done on button press rather than when a sorting style is first chosen in the combo box
	public static class sortContact extends AbstractAction
	{
		private String[] sortingStyles = ContactGUI.getSortingStyles();
		
		public sortContact(String desc)
		{
			putValue(SHORT_DESCRIPTION, desc);
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			//Sorting style is set using a switch statement
			//Uses the index of the selected sorted style to determine how to sort
			if(ContactGUI.getContactsJList() != null)
			{
				if(ContactGUI.getCurrentlySelectedSortingStyle().equals(sortingStyles[0]))
					ContactGUI.setCurrentJListRenderer(0);
				else if(ContactGUI.getCurrentlySelectedSortingStyle().equals(sortingStyles[1]))
					ContactGUI.setCurrentJListRenderer(1);
				else if(ContactGUI.getCurrentlySelectedSortingStyle().equals(sortingStyles[2]))
					ContactGUI.setCurrentJListRenderer(2);
				else if(ContactGUI.getCurrentlySelectedSortingStyle().equals(sortingStyles[3]))
					ContactGUI.setCurrentJListRenderer(3);
			}
		}
	}
}
