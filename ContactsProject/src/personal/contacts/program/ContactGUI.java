package personal.contacts.program;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ContactGUI extends JFrame
{
	JPanel bg, infoPanel, contactsPanel, optionsPanel;
	
	public ContactGUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		this.setLocationRelativeTo(null);		
		this.setMinimumSize(new Dimension(550, 450));
		
		addBackgroundPanel();
		addForegroundPanel();
		topMenuBar();
		
		this.pack();
		this.setVisible(true);
	}
	
	private void addBackgroundPanel()
	{
		bg = new JPanel();
		bg.setBorder(new EmptyBorder(10, 10, 10, 10));
		bg.setLayout(new BorderLayout());
		bg.setBackground(Color.darkGray);
		this.add(bg);
	}
	
	private void addForegroundPanel()
	{
		JPanel fg = new JPanel();
		fg.setMinimumSize(getMinimumSize());
		fg.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        fg.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;
        
		fg.setBackground(Color.lightGray);
		bg.add(fg);
		
		contactsPanel = new JPanel(new BorderLayout());
		displayContactsJList();
		fg.add(contactsPanel, gbc);
		
		
		gbc.gridx++;
		infoPanel = new JPanel(new GridBagLayout());
		infoPanel.setBorder(new CompoundBorder(new TitledBorder("Detailed Information"), new EmptyBorder(4, 4, 4, 4)));
		fg.add(infoPanel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		optionsPanel = new JPanel(new GridBagLayout());
		optionsPanel.setBorder(new CompoundBorder(new TitledBorder("Options"), new EmptyBorder(4, 4, 4, 4)));
		infoPanel.setBackground(Color.lightGray);
		bottomOptionsBar();
		fg.add(optionsPanel, gbc);
	}
	
	private void displayContactsJList()
	{
		contactsPanel.setBackground(Color.lightGray);
		contactsPanel.setBorder(new CompoundBorder(new TitledBorder("Contacts"), new EmptyBorder(4, 4, 4, 4)));
		JList<Contact> contactsJList = new JList<Contact>(convertToDefaultModel());
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
															clearContactInfoPanel();
															displayContactInfo(contactsJList.getSelectedValue());
													    }
														
													}
												}
										      );
        JScrollPane scrollPane = new JScrollPane(contactsJList);
        contactsPanel.add(scrollPane);
	}

	private void displayContactInfo(Contact c)
	{
		GridBagConstraints infoGBC = new GridBagConstraints();
		infoGBC.gridx = 0;
		infoGBC.gridy = 0;
		infoGBC.weightx = 0;
		infoGBC.weighty = 1;
		infoGBC.anchor = GridBagConstraints.NORTHWEST;
		infoGBC.fill = GridBagConstraints.HORIZONTAL;
		
		infoPanel.add(new JLabel("First Name: "), infoGBC);
		infoGBC.gridx += 2;
		infoPanel.add(new JLabel("Last Name: "), infoGBC);
		
		infoGBC.gridx = 0;
		infoGBC.gridy = 1;
		infoPanel.add(new JLabel("Home Number: "), infoGBC);
		infoGBC.gridx += 2;;
		infoPanel.add(new JLabel("Cell Number: "), infoGBC);
		
		infoGBC.gridx = 0;
		infoGBC.gridy++;
		infoPanel.add(new JLabel("Home Address: "), infoGBC);
		infoGBC.gridy++;
		infoPanel.add(new JLabel("Email Address: "), infoGBC);
		infoGBC.gridy++;
		infoPanel.add(new JLabel("Birthday: "), infoGBC);
		infoGBC.gridy++;
		infoPanel.add(new JLabel("Notes: "), infoGBC);
		
		infoGBC.insets = new Insets(0, 0, 0, 4);
		infoGBC.gridx = 1;
		infoGBC.gridy = 0;
		infoGBC.weightx = 1;
		JTextField firstName = new JTextField(c.getFirstName(), 8);
		firstName.setEditable(false);
		infoPanel.add(firstName, infoGBC);
		
		infoGBC.gridx += 2;
		JTextField lastName = new JTextField(c.getLastName(), 8);
		lastName.setEditable(false);
		infoPanel.add(lastName, infoGBC);
			
		infoGBC.gridx = 1;
		infoGBC.gridy++;
		JTextField homeNum = new JTextField("" + c.getHomeNumber());
		homeNum.setEditable(false);
		infoPanel.add(homeNum, infoGBC);
		
		infoGBC.gridx += 2;
		JTextField cellNum = new JTextField("" + c.getCellNumber());
		cellNum.setEditable(false);
		infoPanel.add(cellNum, infoGBC);
		
		infoGBC.gridx = 1;
		infoGBC.gridwidth = 4;
		infoGBC.gridy++;
		JTextField homAddr = new JTextField("" + c.getHomeAddress(), 15);
		homAddr.setEditable(false);
		infoPanel.add(homAddr, infoGBC);
		
		infoGBC.gridy++;
		JTextField emailAddr = new JTextField("" + c.getEmailAddress(), 15);
		emailAddr.setEditable(false);
		infoPanel.add(emailAddr, infoGBC);
		
		infoGBC.gridy++;
		JTextField birthday = new JTextField("" + c.getBirthday().toString(), 15);
		birthday.setEditable(false);
		infoPanel.add(birthday, infoGBC);
		
		infoGBC.gridy++;
		infoGBC.gridheight = 3;
		JTextField notes = new JTextField("" + c.getNotes(), 15);
		notes.setEditable(false);
		infoPanel.add(notes, infoGBC);
		
        
	}
	
	private void topMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		JMenu mainMenu = new JMenu("Menu");
		mainMenu.setMnemonic(KeyEvent.VK_M);
		menuBar.add(mainMenu);
		
		JMenuItem addContact = new JMenuItem("Add", KeyEvent.VK_1);
		addContact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		addContact.setToolTipText("Add a new contact");
		mainMenu.add(addContact);
		
		JMenuItem editContact = new JMenuItem("Edit", KeyEvent.VK_2);
		editContact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		editContact.setToolTipText("Edit an existing contact");
		mainMenu.add(editContact);
		
		JMenuItem appearance = new JMenuItem("Change Appearance", KeyEvent.VK_3);
		appearance.setToolTipText("Customze the program's look");
		mainMenu.add(appearance);
		
		this.setJMenuBar(menuBar);
	}
	
	private void bottomOptionsBar()
	{
		optionsPanel.setBackground(Color.lightGray);
		GridBagConstraints optionsGBC = new GridBagConstraints();
		optionsGBC.gridx = 0;
		optionsGBC.gridy = 0;
		optionsGBC.weightx = 1;
		optionsGBC.weighty = 1;
		optionsGBC.anchor = GridBagConstraints.WEST;
		
		JButton addC = new JButton("Add Contact");
		optionsPanel.add(addC, optionsGBC);
		
		optionsGBC.gridx++;	
		JButton editC = new JButton("Edit Contact");
		optionsPanel.add(editC, optionsGBC);

		optionsGBC.gridx = 0;
		optionsGBC.gridy = 1;		
		JLabel sortT = new JLabel("Sorting Options");
		optionsPanel.add(sortT, optionsGBC);
		
		optionsGBC.gridx++;
		JLabel sortO = new JLabel("Sorting Combo Box");
		optionsPanel.add(sortO, optionsGBC);
		
		optionsGBC.gridx++;
		JButton sortC = new JButton("Sort");
		optionsPanel.add(sortC, optionsGBC);
		
		
	}
	
	private DefaultListModel<Contact> convertToDefaultModel()
	{
		DefaultListModel<Contact> cList = new DefaultListModel<Contact>();
		for(Contact c : ListOfContacts.getContactsList())
		{
			cList.addElement(c);
		}
		
		return cList;
	}
	
	private void clearContactInfoPanel() 
	{
		
		infoPanel.removeAll();
		infoPanel.revalidate();
		infoPanel.repaint();
		
	}

}
