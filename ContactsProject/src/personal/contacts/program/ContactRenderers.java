package personal.contacts.program;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/* Contains different List Cell Renderers that can be used by the JList holding Contact objects 
 * Currently supports renders based on the contact's name
 * Renderers based off of other Contact attributes can be added here
 */
public class ContactRenderers 
{
	public ContactRenderers() { }
	
	//Display Contact names in JList as "First Last" 
	public static class FirstNameRenderer extends DefaultListCellRenderer
	{
		 public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
		 {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (value instanceof Contact) 
			{
				Contact c = (Contact)value;
				setText(c.getFirstName() + " " + c.getLastName());
			}
			
			return this;
		}
	}
	
	//Display Contact names in JList as "Last, First" 
	public static class LastNameRenderer extends DefaultListCellRenderer
	{
		 public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
		 {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (value instanceof Contact) 
			{
				Contact c = (Contact)value;
				setText(c.getLastName() + ", " + c.getFirstName());
			}
			
			return this;
		}
	}
}
