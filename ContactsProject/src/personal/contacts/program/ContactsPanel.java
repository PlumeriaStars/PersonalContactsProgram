package personal.contacts.program;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.Year;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/* Template panel that labels and displays all attributes of a Contact object
 * Birthdays are represented in two forms
 * 		1. As simple text when viewing an existing contact
 * 		2. As a combo box when creating a new contact or editing an existing contact 
 */
public class ContactsPanel extends JPanel
{
	private JTextField firstName, lastName, homeNum, cellNum, homeAddr, emailAddr,
					   monthField, dayField, yearField;
	private JTextArea notes;
	private JComboBox<String> birthMonth;
	private JComboBox<Integer> birthDay, birthYear;
	private String[] months = {"January", "Feburary", "March", "April", "May", "June", "July", 
								"August", "September", "October", "November", "December"};
	private int startingYear;
	
	//Constructor - used when creating an empty contact panel to be edited
	public ContactsPanel(String title) 
	{ 
		createEmptyPanel();
		this.setBorder(new CompoundBorder(new TitledBorder(title), new EmptyBorder(8, 8, 8, 8)));
		setBirthdayComboBoxVisibilty(true);
		setBirthdayFieldVisibilty(false);
	}
	
	//Constructor - used when creating a contact panel to display contact information
	//				reads and displays from the passed Contact object, fields are not editable
	public ContactsPanel(Contact c)
	{
		createEmptyPanel();
		setFieldData(c);
		setTextFieldEditability(false);
		
		setBirthdayComboBoxVisibilty(false);
		setBirthdayFieldVisibilty(true);
		
		this.setVisible(true);
	}
	
	//Getter - gets the user-inputed text in the First Name Field
	public String getFirstName()
	{
		return firstName.getText();
	}
	
	//Getter - gets the user-inputed text in the Last Name Field
	public String getLastName()
	{
		return lastName.getText();
	}
	
	//Getter - gets the user-inputed text in the Home Number Field
	public long getHomeNum()
	{
		return Long.parseLong(homeNum.getText());
	}
	
	//Getter - gets the user-inputed text in the Cell Number Field
	public long getCellNum()
	{
		return Long.parseLong(cellNum.getText());
	}
	
	//Getter - gets the user-inputed text in the Home Address Field
	public String getHomeAddress()
	{
		return homeAddr.getText();
	}
	
	//Getter - gets the user-inputed text in the Email Address Field
	public String getEmailAddress()
	{
		return emailAddr.getText();
	}
	
	//Getter - gets the supplied month text in the Month Field
	public String getBirthMonthField()
	{
		return monthField.getText();
	}
	
	//Getter - gets the index of the currently selected month in the combo box
	//Month array starts at 0 while LocalDate month starts at 1,
	// so once the correct month is found add 1 to the index before returning it
	public int getBirthMonthIndex()
	{		
		int index = 0;
		for(String currMonth: months)
		{
			if(currMonth.equals(birthMonth.getSelectedItem().toString()))
				return index+=1;
		
			index++;
		}
		
		return -1;
	}
	
	//Getter - gets the index of the currently selected day in the combo box
	public int getBirthDay()
	{
		return Integer.valueOf(birthDay.getSelectedItem().toString());
	}
	
	//Getter - gets the index of the currently selected year in the combo box
	public int getBirthYear()
	{
		return Integer.valueOf(birthYear.getSelectedItem().toString());
	}
	
	//Getter - gets the user-inputed text in the Notes Field
	public String getNotes()
	{
		return notes.getText();
	}
	
	//Lays out all the components of the ContactsPanel
	//birthDay combo box is dependent on birthMonth combo box, the selectable days change based on the selected month
 	private void createEmptyPanel() 
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		this.add(new JLabel("First Name: "), gbc);
		gbc.gridx += 2;
		this.add(new JLabel("Last Name: "), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(new JLabel("Home Number: "), gbc);
		gbc.gridx += 2;;
		this.add(new JLabel("Cell Number: "), gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		this.add(new JLabel("Home Address: "), gbc);
		gbc.gridy++;
		this.add(new JLabel("Email Address: "), gbc);
		gbc.gridy++;
		this.add(new JLabel("Birthday: "), gbc);
		gbc.gridy++;
		this.add(new JLabel("Notes: "), gbc);
		
		gbc.insets = new Insets(0, 0, 0, 4);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		
		firstName = new JTextField();
		this.add(firstName, gbc);
		
		gbc.gridx += 2;
		lastName = new JTextField();
		this.add(lastName, gbc);
			
		gbc.gridx = 1;
		gbc.gridy++;
		homeNum = new JTextField();
		this.add(homeNum, gbc);
		
		gbc.gridx += 2;
		cellNum = new JTextField();
		this.add(cellNum, gbc);
		
		gbc.gridx = 1;
		gbc.gridwidth = 4;
		gbc.gridy++;
		homeAddr = new JTextField();
		this.add(homeAddr, gbc);
		
		gbc.gridy++;
		emailAddr = new JTextField();
		this.add(emailAddr, gbc);
		
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridy++;
		monthField = new JTextField(12);
		this.add(monthField, gbc);
		birthMonth = new JComboBox<String>(months);
		birthMonth.addItemListener(new ItemListener() 
									{								
										@Override
										public void itemStateChanged(ItemEvent e) 
										{
											if(e.getStateChange() == ItemEvent.SELECTED)
											{
												int selectedIndex = birthMonth.getSelectedIndex();
												int month = selectedIndex + 1;
												
												if(birthDay.getItemCount() != 0)
													birthDay.removeAllItems();
												
												/* Days Count: Days -> Month(corresponding int value)
												 *  29 -> Feb(2)
												 *  30 -> April(4), June(6), Sept(9), Nov(11)
												 *  31 -> Jan(1), March(3), May(5), July(7), Aug(8), Oct(10), Dec(12)
												 */
												if(month == 2)
													fillDays(29);
												else if(month == 4 || month == 6 || month == 9 || month == 11)
													fillDays(30);
												else
													fillDays(31);
												
												birthDay.setVisible(true);
											}
											
										}
									});
		this.add(birthMonth, gbc);
		
		//Default month is January, so fill the days box up to 31
		gbc.gridx++;
		dayField = new JTextField(2);
		this.add(dayField, gbc);
		birthDay = new JComboBox<Integer>();
		fillDays(31);
		this.add(birthDay, gbc);
		
		gbc.gridx++;
		yearField = new JTextField(4);
		this.add(yearField, gbc);
		birthYear = new JComboBox<Integer>();
		fillYears();
		this.add(birthYear, gbc);
		
		gbc.gridx = 1;
		gbc.gridy++;
		gbc.gridheight = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		notes = new JTextArea();
		notes.setLineWrap(true);
		this.add(notes, gbc);
		
		this.setOpaque(true);
	}
 	
 	//Fills in the birthDay combo box with the specified number of days
 	private void fillDays(int numDays)
 	{
 		for(int i = 0; i < numDays; i++)
 			birthDay.addItem((Integer) i + 1);
 	}
 	
 	//Fills in the birthYear combo box with the years between the starting year and the current PC system year
 	private void fillYears()
 	{
 		startingYear = 1920;
 		int size = Year.now().getValue() - startingYear + 1;
 		
 		for(int i = 0; i < size; i++)
 			birthYear.addItem((Integer) startingYear + i);
 	}
 	
 	//Sets all the field text based on the data from the Contact object passed
 	public void setFieldData(Contact c)
 	{
 		firstName.setText(c.getFirstName());
		lastName.setText(c.getLastName());
		homeNum.setText("" + c.getHomeNumber());
		cellNum.setText("" + c.getCellNumber());
		homeAddr.setText(c.getHomeAddress());
		emailAddr.setText(c.getEmailAddress());
		
		monthField.setText("" + months[c.getBirthday().getMonthValue()-1]);
		dayField.setText("" + c.getBirthday().getDayOfMonth());
		yearField.setText("" + c.getBirthday().getYear());

		notes.setText(c.getNotes());
 	}
 	
 	//Sets the visibility of the birthday text fields depending on the boolean parameter
 	public void setBirthdayFieldVisibilty(Boolean bool)
 	{
 		if(bool)
 		{
 			monthField.setVisible(true);
 			dayField.setVisible(true);
 			yearField.setVisible(true);
 		}
 		else
 		{
 			monthField.setVisible(false);
 			dayField.setVisible(false);
 			yearField.setVisible(false);
 		}
 	}
 	
 	//Sets the visibility of the birthday combo boxes depending on the boolean parameter
 	public void setBirthdayComboBoxVisibilty(Boolean bool)
 	{
 		if(bool)
 		{
 			birthMonth.setVisible(true);
 			birthDay.setVisible(true);
 			birthYear.setVisible(true);
 		}
 		else
 		{
 			birthMonth.setVisible(false);
 			birthDay.setVisible(false);
 			birthYear.setVisible(false);
 		}
 	}
 	
 	//Sets the editability of the all the text fields depending on the boolean parameter
 	public void setTextFieldEditability(Boolean bool)
 	{
 		JTextField[] allTextFields = {firstName, lastName, homeNum, cellNum, homeAddr, emailAddr};
 		for(JTextField tx : allTextFields)
 		{
 			if(tx != null)
 				tx.setEditable(bool);
 		}
 		
 		notes.setEditable(bool);
 	}
 	
 	//Sets the default values of the birthday combo box to pre-existing values
 	//This is used when editing an existing contact
 	public void setBirthdayComboBox()
 	{
 		birthMonth.setSelectedItem(monthField.getText());
 		birthDay.setSelectedIndex(Integer.valueOf(dayField.getText()) - 1);
 		birthYear.setSelectedIndex(Integer.valueOf(yearField.getText()) - startingYear);
 	}

}
