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
	
	//The add action calls the EditContact class when a component linked to this action is called
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
					EditContact.saveEditedContact();
				
				if(e.getSource().equals(ContactGUI.editCancel))
					EditContact.revertContactInfoPanel();
			}
		}
			
	}
}
