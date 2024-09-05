package personal.contacts.program;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/* Contains code regarding the main GUI window
 * The main window is split into 3 panels
 * 		1. jListPanel -> displays the JList of existing contacts
 * 		2. infoPanel	 -> displays the various fields of the currently selected contact
 * 		3. optionsPanel	 -> contains buttons that perform various actions
 * A menu bar is also included
 */
public class ContactGUI extends JFrame
{
	private JPanel bg, infoPanel, jListPanel, optionsPanel;
	private static JList<Contact> contactsJList;
	private JScrollPane scrollPane;
	static JButton editContact, editConfirm, editCancel;
	static ContactsPanel contactInfo;
	
	//Constructor - contains calls to functions that create the various panels
	public ContactGUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		this.setLocationRelativeTo(null);		
		this.setMinimumSize(new Dimension(550, 450));
		this.setResizable(false);
		this.setTitle("Contacts Program");
		
		addBackgroundPanel();
		addForegroundPanel();
		topMenuBar();
		
		this.pack();
		this.setVisible(true);
		
	}

	//Creates the background panel which covers the entire frame
	private void addBackgroundPanel()
	{
		bg = new JPanel();
		bg.setBorder(new EmptyBorder(10, 10, 10, 10));
		bg.setLayout(new BorderLayout());
		bg.setBackground(Color.darkGray);
		this.add(bg);
	}
	
	//Instantiates the three foreground panels
	//Calls helper methods that fill these foreground panels
	private void addForegroundPanel()
	{
		JPanel fg = new JPanel();
		fg.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        fg.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;
		fg.setBackground(Color.lightGray);
		bg.add(fg);
		
		jListPanel = new JPanel(new BorderLayout());
		jListPanel.setBorder(new CompoundBorder(new TitledBorder("Contacts"), new EmptyBorder(4, 4, 4, 4)));
		displayContactsJList();
		fg.add(jListPanel, gbc);
		
		
		gbc.gridx++;
		gbc.weightx = 1;
		infoPanel = new JPanel(new BorderLayout());
		infoPanel.setBorder(new CompoundBorder(new TitledBorder("Detailed Information"), new EmptyBorder(4, 4, 4, 4)));
		fg.add(infoPanel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.weightx = 0;
        gbc.weighty = 0.05;
		optionsPanel = new JPanel(new GridBagLayout());
		optionsPanel.setBorder(new CompoundBorder(new TitledBorder("Options"), new EmptyBorder(4, 4, 4, 4)));
		
		infoPanel.setBackground(Color.lightGray);
		bottomOptionsBar();
		fg.add(optionsPanel, gbc);
	}
	
	//Creates and displays the JList found in the contactsPanel
	private void displayContactsJList()
	{
		jListPanel.setBackground(Color.lightGray);
		
		contactsJList = new JList<Contact>(ListOfContacts.getContactsList());
		contactsJList.setCellRenderer(new ContactRenderers.FirstNameRenderer());
		contactsJList.setVisibleRowCount(10);
		contactsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contactsJList.addListSelectionListener(
												new ListSelectionListener()
												{
													@Override
													public void valueChanged(ListSelectionEvent e) 
													{
														if (e.getValueIsAdjusting() == false) 
														{
															//Clear the infoPanel of the previously selected contact's information
															clearContactInfoPanel();
															//Display the contact information of the currently selected contact
															if(contactsJList.getSelectedValue() != null)
																displayContactInfo(contactsJList.getSelectedValue());
													    }
														
													}
												}
										      );
        scrollPane = new JScrollPane(contactsJList);
        jListPanel.add(scrollPane);
	}

	//Uses the ContactsPanel template to display the information of Contact c
	private void displayContactInfo(Contact c)
	{
		contactInfo = new ContactsPanel(c);
		infoPanel.add(contactInfo);       
	}
	
	//Creates the menu bar
	//Adds keyboard shortcuts to the main actions, adding and editing
	//		CTRL + N -> Add new Contact
	// 		CTRL + E -> Edit (selected) contact
	private void topMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		JMenu mainMenu = new JMenu("Menu");
		mainMenu.setMnemonic(KeyEvent.VK_M);
		menuBar.add(mainMenu);
		
		JMenuItem addContact = new JMenuItem("Add", KeyEvent.VK_1);
		addContact.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_DOWN_MASK));
		addContact.setToolTipText("Add a new contact");
		addContact.addActionListener(new GUIActionListeners.addContactAction("Add a new contact"));
		mainMenu.add(addContact);
		
		JMenuItem editContact = new JMenuItem("Edit", KeyEvent.VK_2);
		editContact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		editContact.setToolTipText("Edit an existing contact");
		editContact.addActionListener(new GUIActionListeners.editContactAction("Edit and existing Contact", contactsJList.getSelectedValue(), contactsJList.getSelectedIndex()));
		mainMenu.add(editContact);
		
		JMenuItem appearance = new JMenuItem("Change Appearance", KeyEvent.VK_3);
		appearance.setToolTipText("Customze the program's look");
		mainMenu.add(appearance);
		
		this.setJMenuBar(menuBar);
	}
	
	//Lays out the buttons at the bottom of the main panel
	//Button options include Adding a contact, Editing the selected contact, and choosing a Sorting method
	private void bottomOptionsBar()
	{
		optionsPanel.setBackground(Color.lightGray);
		GridBagConstraints optionsGBC = new GridBagConstraints();
		optionsGBC.gridx = 0;
		optionsGBC.gridy = 0;
		optionsGBC.weightx = 1;
		optionsGBC.weighty = 1;
		optionsGBC.anchor = GridBagConstraints.WEST;
		
		JButton addContact = new JButton("Add Contact");
		addContact.addActionListener(new GUIActionListeners.addContactAction("Add a new Contact"));
		optionsPanel.add(addContact, optionsGBC);
		
		optionsGBC.gridx++;	
		editContact = new JButton("Edit Contact");
		editContact.addActionListener(new GUIActionListeners.editContactAction("Edit and existing Contact", contactsJList.getSelectedValue(), contactsJList.getSelectedIndex()));
		optionsPanel.add(editContact, optionsGBC);
		
		editConfirm = new JButton("Save Edits");
		editConfirm.addActionListener(new GUIActionListeners.editContactAction("Edit and existing Contact", contactsJList.getSelectedValue(), contactsJList.getSelectedIndex()));
		optionsPanel.add(editConfirm, optionsGBC);
		editConfirm.setVisible(false);
		
		optionsGBC.gridx++;	
		editCancel = new JButton("Cancel Edits");
		editCancel.addActionListener(new GUIActionListeners.editContactAction("Edit and existing Contact", contactsJList.getSelectedValue(), contactsJList.getSelectedIndex()));
		optionsPanel.add(editCancel, optionsGBC);
		editCancel.setVisible(false);

		optionsGBC.gridx = 0;
		optionsGBC.gridy = 1;		
		JLabel sortLabel = new JLabel("Sorting Options");
		optionsPanel.add(sortLabel, optionsGBC);
		
		optionsGBC.gridx++;
		JLabel sortComboBox = new JLabel("Sorting Combo Box");
		optionsPanel.add(sortComboBox, optionsGBC);
		
		optionsGBC.gridx++;
		JButton sortConfirm = new JButton("Sort");
		optionsPanel.add(sortConfirm, optionsGBC);
		
		
	}
	
	//Clears the infoPanel of the last selected contact's information
	private void clearContactInfoPanel() 
	{
		
		infoPanel.removeAll();
		infoPanel.revalidate();
		infoPanel.repaint();
		
	}
	
	//Gets the index of the contact currently selected in the JList
	public static Contact getCurrentlySelectedContact()
	{
		return contactsJList.getSelectedValue();
	}
	
	//Gets the Contact object currently selected in the JList
	public static int getCurrentlySelectedContactIndex()
	{
		return contactsJList.getSelectedIndex();
	}

	//Gets the contacts JList
	public static JList<Contact> getContactsJList()
	{
		return contactsJList;
	}
}
