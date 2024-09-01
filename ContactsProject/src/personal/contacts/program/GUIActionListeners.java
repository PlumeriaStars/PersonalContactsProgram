package personal.contacts.program;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

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
			AddContact addNew = new AddContact();
		}
		
	}
}
