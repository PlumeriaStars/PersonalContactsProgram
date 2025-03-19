package personal.contacts.program;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/* Class that directly deals with deleting a contact
 * When removing a contact, a pop-up appears asking the user to confirm the action
 * If the user confirms the action, the selected contact is removed from the list
 * After the contact is successfully deleted, the JSON holding all the contacts is updated
 */
public class DeleteContact extends JDialog implements ActionListener 
{
	private JLabel message, warning, contactName;
	private JButton cancelButton, deleteButton;
	private Contact toBeDeleted;
	private int toBeDeletedIndex;
	private Font defaultTextFont = new Font("SansSerif", Font.PLAIN, 12);
	private Font smallTextFont = new Font("SansSerif", Font.BOLD, 10);
	
	//Constructor - creates the pop-up
	public DeleteContact()
	{
		//Get the currently selected contact and its respective index
		toBeDeleted = ContactGUI.getCurrentlySelectedContact();
		toBeDeletedIndex = ContactGUI.getCurrentlySelectedContactIndex();
		
		Dimension dialogDimension = new Dimension(300, 175);
		
		this.setTitle("Delete Contact");
		this.setPreferredSize(dialogDimension);
		this.setMinimumSize(dialogDimension);
		this.setMaximumSize(dialogDimension);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		createDeletionPanel();
		
		this.pack();
		this.setVisible(true);
	}
	
	//Creates the main panel used in the deletion dialog box
	//Asks the user to confirm if the selected contact is to be deleted
	private void createDeletionPanel()
	{
		JPanel deletionPanel = new JPanel(new GridLayout(4, 1, 0, 0));
		deletionPanel.setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 2), new EtchedBorder(EtchedBorder.LOWERED)));
		
		message = new JLabel("Are you sure you wish to delete this contact?", JLabel.CENTER);
		message.setFont(defaultTextFont);
		deletionPanel.add(message);
		
		contactName = new JLabel(toBeDeleted.getFirstName() + " " + toBeDeleted.getLastName(), JLabel.CENTER);
		contactName.setFont(defaultTextFont);
		deletionPanel.add(contactName);
		
		warning = new JLabel("Contacts that are deleted cannot be recovered.", JLabel.CENTER);
		warning.setFont(smallTextFont);
		warning.setForeground(Color.RED);
		deletionPanel.add(warning);
		
		JPanel buttonPanel = new JPanel();
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(defaultTextFont);
		cancelButton.setFocusable(false);
		cancelButton.addActionListener(this);
		buttonPanel.add(cancelButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.setFocusable(false);
		deleteButton.setFont(defaultTextFont);
		deleteButton.addActionListener(this);
		buttonPanel.add(deleteButton);
		
		deletionPanel.add(buttonPanel);
		
		this.add(deletionPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//If the user selects to delete the contact, remove the contact from the JList
		if(e.getSource() == deleteButton)
		{
			ListOfContacts.removeFromContactList(toBeDeletedIndex);
			JsonReadWrite.writeToFile();
			dispose();
		}
		//Else, dispose of this window
		else if(e.getSource() == cancelButton)
		{
			dispose();
		}
	}

}
