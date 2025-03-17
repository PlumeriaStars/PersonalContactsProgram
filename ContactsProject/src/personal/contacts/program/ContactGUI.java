package personal.contacts.program;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Collections;

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
	private static JPanel bg, infoPanel, jListPanel, optionsPanel;
	private static JScrollPane scrollPane;
	private static Dimension frameDimension;
	private static JList<Contact> contactsJList;	
	private static JComboBox<String> sortComboBox;
	private static String[] sortingStyles = { "First Name, Ascending", "First Name, Descending",
									   "Last Name, Ascending",  "Last Name, Descending"   };
	static JButton addContact, deleteContact, editContact, editConfirm, editCancel, sortConfirm;
	static ContactsPanel contactInfo;
	
	//Constructor - contains calls to functions that create the various panels
	public ContactGUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		frameDimension = new Dimension(550, 450);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		this.setPreferredSize(frameDimension);
		this.setMinimumSize(frameDimension);
		this.setMaximumSize(frameDimension);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Contacts Program");
		
		addBackgroundPanel();
		addForegroundPanel();
		topMenuBar();
		
		this.pack();
		this.setVisible(true);
		
		//new DeleteContact();
		
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
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;
		fg.setBackground(Color.lightGray);
		bg.add(fg);
		
		jListPanel = new JPanel(new BorderLayout());
		jListPanel.setMinimumSize(new Dimension((int)(frameDimension.getWidth() / 6), (int)(frameDimension.getHeight() * 0.55)));
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
	protected static void displayContactInfo(Contact c)
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
		
		JMenuItem addContact = new JMenuItem("Add New", KeyEvent.VK_1);
		addContact.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_DOWN_MASK));
		addContact.setToolTipText("Add a new contact");
		addContact.addActionListener(new GUIActionListeners.addContactAction("Add a new contact"));
		mainMenu.add(addContact);
		
		JMenuItem deleteContact = new JMenuItem("Delete Contact", KeyEvent.VK_2);
		deleteContact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		deleteContact.setToolTipText("Delete an existing contact");
		deleteContact.addActionListener(new GUIActionListeners.deleteContactAction("Delete an existing Contact"));
		mainMenu.add(deleteContact);
		
		JMenuItem editContact = new JMenuItem("Edit Contact", KeyEvent.VK_3);
		editContact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		editContact.setToolTipText("Edit an existing contact");
		editContact.addActionListener(new GUIActionListeners.editContactAction("Edit and existing Contact"));
		mainMenu.add(editContact);
		
		JMenuItem appearance = new JMenuItem("Change Appearance", KeyEvent.VK_3);
		appearance.setToolTipText("Customze the program's look");
		mainMenu.add(appearance);
		
		this.setJMenuBar(menuBar);
	}
	
	//Lays out the buttons at the bottom of the main panel
	//Button options include Adding a contact, Deleting a contact, Editing the selected contact, and choosing a Sorting method
	//The options panel uses a GridLayout to avoid problems with how non-visible elements interact with the GridBagLayout
	private void bottomOptionsBar()
	{
		GridLayout grid = new GridLayout(2, 0, 8, 4);
		optionsPanel = new JPanel(grid);
		optionsPanel.setBorder(new CompoundBorder(new TitledBorder("Options"), new EmptyBorder(4, 4, 4, 4)));
		optionsPanel.setBackground(Color.lightGray);

		
		addContact = new JButton("Add New");
		addContact.addActionListener(new GUIActionListeners.addContactAction("Add a new Contact"));
		optionsPanel.add(addContact);
		
		deleteContact = new JButton("Delete");
		deleteContact.addActionListener(new GUIActionListeners.deleteContactAction("Delete an existing Contact"));;
		optionsPanel.add(deleteContact);
		
		//In order to have two different buttons occupy the same grid square
		//Create a CardLayout panel that will switch between both buttons
		CardLayout editingCard = new CardLayout();
		JPanel editingPanel = new JPanel(editingCard);
		optionsPanel.add(editingPanel);
		
		editContact = new JButton("Edit Contact");
		editContact.addActionListener(new GUIActionListeners.editContactAction("Edit an existing Contact"));
		editContact.addActionListener(new ActionListener() 
										{
											@Override
											public void actionPerformed(ActionEvent e) 
											{
												if(getCurrentlySelectedContact() != null)
													editingCard.next(editingPanel);
											}
										});
		editingPanel.add(editContact);
		
		editConfirm = new JButton("Save Edits");
		editConfirm.addActionListener(new GUIActionListeners.editContactAction("Edit and existing Contact"));
		editingPanel.add(editConfirm);
		editConfirm.setVisible(false);
		
		editCancel = new JButton("Cancel Edits");
		editCancel.addActionListener(new GUIActionListeners.editContactAction("Edit and existing Contact"));
		optionsPanel.add(editCancel);
		editCancel.setVisible(false);
		
		grid.setColumns(3);
		JLabel sortLabel = new JLabel("Sorting Options", JLabel.CENTER);
		optionsPanel.add(sortLabel);

		sortComboBox = new JComboBox<String>(sortingStyles);
		optionsPanel.add(sortComboBox);

		sortConfirm = new JButton("Sort");
		sortConfirm.addActionListener(new GUIActionListeners.sortContact("Sort Contacts List"));
		optionsPanel.add(sortConfirm);
	}
	
	
	//Clears the infoPanel of the last selected contact's information
	protected static void clearContactInfoPanel() 
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
	
	//Gets the selected sorting style from the combo box
	public static String getCurrentlySelectedSortingStyle()
	{
		return (String)sortComboBox.getSelectedItem();
	}
	
	//Gets an array filled with all the sorting styles
	public static String[] getSortingStyles()
	{
		return sortingStyles;
	}
	
	//Using the index of the selected sorting style
	//First, set the corresponding cell renderer
	//Second, sort the actual JList
	public static void setCurrentJListRenderer(int sorter)
	{
		ContactComparators comparator = new ContactComparators();
		switch(sorter)
		{
			case 0: contactsJList.setCellRenderer(new ContactRenderers.FirstNameRenderer());
					ListOfContacts.sortContactsList(new ContactComparators.firstLastNameComparator(), 0);
					comparator.setCurrentComparatorOrder(0);
				break;
			case 1: contactsJList.setCellRenderer(new ContactRenderers.FirstNameRenderer()); 
					ListOfContacts.sortContactsList(new ContactComparators.firstLastNameComparator(), 1);
					comparator.setCurrentComparatorOrder(1);
				break;
			case 2: contactsJList.setCellRenderer(new ContactRenderers.LastNameRenderer());
					ListOfContacts.sortContactsList(new ContactComparators.lastFirstNameComparator(), 0);
					comparator.setCurrentComparatorOrder(0);
				break;
			case 3: contactsJList.setCellRenderer(new ContactRenderers.LastNameRenderer());
					ListOfContacts.sortContactsList(new ContactComparators.lastFirstNameComparator(), 1);
					comparator.setCurrentComparatorOrder(1);
				break;
			default:contactsJList.setCellRenderer(new ContactRenderers.FirstNameRenderer());
					ListOfContacts.sortContactsList(new ContactComparators.firstLastNameComparator(), 0);
					comparator.setCurrentComparatorOrder(0);
				
		}
	}
}
