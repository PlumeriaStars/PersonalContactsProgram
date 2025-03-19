package personal.contacts.program;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"firstName", "lastName", "homeNumber", "cellNumber", 
	                "homeAddress", "emailAddress", "birthday", "notes"})
public class Contact implements Comparable<Contact>
{
	private String firstName;
	private String lastName;
	private long homeNumber;
	private long cellNumber;
	private String homeAddress;
	private String emailAddress;
	private LocalDate birthday;
	private String notes;


	//Constructor - Empty
    public Contact()
    {
    	this.firstName = "";		
    	this.lastName = "";
		this.homeNumber = -1;
		this.cellNumber = -1;
		this.homeAddress = "";
		this.emailAddress = "";
		this.birthday = null;
		this.notes = "";
    }
    
    //Constructor - Overloaded
    public Contact(String fName, String lName, long hNum, long cNum, String hAddr, String eAddr, LocalDate bday, String n)
    {
    	this.firstName = fName;		
    	this.lastName = lName;
		this.homeNumber = hNum;
		this.cellNumber = cNum;
		this.homeAddress = hAddr;
		this.emailAddress = eAddr;
		this.birthday = bday;
		this.notes = n;
    }

    //Getter methods
    public String getFirstName()
    {
    	return this.firstName;
    }
    
    public String getLastName()
    {
    	return this.lastName;
    }
    
    public long getHomeNumber()
    {
    	return this.homeNumber;
    }
    
    public long getCellNumber()
    {
    	return this.cellNumber;
    }
    
    public String getHomeAddress()
    {
    	return this.homeAddress;
    }
    
    public String getEmailAddress()
    {
    	return this.emailAddress;
    }
    
    public LocalDate getBirthday()
    {
    	return this.birthday;
    }
    
    public String getNotes()
    {
    	return this.notes;
    }
    
    //Setter methods
    public void setFirstName(String fName)
    {
    	this.firstName = fName;
    }
    
    public void setLastName(String lName)
    {
    	this.lastName = lName;
    }
    
    public void setHomeNumber(long hNum)
    {
    	this.homeNumber = hNum;
    }
    
    public void setCellNumber(long cNum)
    {
    	this.cellNumber = cNum;
    }
    
    public void setHomeAddress(String hAddr)
    {
    	this.homeAddress = hAddr;
    }
    
    public void setEmailAddress(String eAddr)
    {
    	this.emailAddress = eAddr;
    }
    
    public void setBirthday(String bday)
    {
    	this.birthday = LocalDate.parse(bday);
    }
    
    public void setNotes(String n)
    {
    	this.notes = n;
    }

    //Uses the Comparable interface to compare two Contact objects
	@Override
	public int compareTo(Contact c) 
	{
		String thisFullName = this.firstName + " " + this.lastName;
		String otherFullName = c.getFirstName() + " " +c.getLastName();
		
		//Two Contact objects are equal if their content is equal, not necessarily their object addresses
		if( this.firstName.compareToIgnoreCase(c.getFirstName()) == 1  &&
			this.lastName.compareToIgnoreCase(c.getLastName()) == 1	&&
			this.homeNumber == c.getHomeNumber() &&
			this.cellNumber == c.getCellNumber() &&	
			this.homeAddress.compareToIgnoreCase(c.getHomeAddress()) == 1 &&	
			this.emailAddress.compareToIgnoreCase(c.getEmailAddress()) == 1 &&	
			this.birthday.equals(c.getBirthday()) &&
			this.notes.compareToIgnoreCase(c.getNotes()) == 1
		  )
			return 0;
		//If the full name of this contact is alphabetically before the comparing contact
		else if(thisFullName.compareToIgnoreCase(otherFullName) < 0)
		{
			return thisFullName.compareToIgnoreCase(otherFullName);
		}
		//Else, the full name of this contact is alphabetically after the comparing contact
		else
			return thisFullName.compareToIgnoreCase(otherFullName);
	}

	//General toString
	public String toString()
	{
		/* Phone Numbers stored as continuous numbers
		 * Conevert to string and add delimiter: # # # - # # # - # # #  #
		 * Index                                 0 1 2 3 4 5 6 7 8 9 10 11
		 * Only applies to full numbers
		 * If the user left the number fields empty, no need to build a new string using delimiters
		 */
		StringBuilder hNum = null;
		if(String.valueOf(this.homeNumber).length() == 10)
		{
			hNum= new StringBuilder(String.valueOf(this.homeNumber));
			hNum.insert(3, '-');
			hNum.insert(7, '-');
		}
		
		StringBuilder cNum = null;
		if(String.valueOf(this.cellNumber).length() == 10)
		{
			cNum = new StringBuilder(String.valueOf(this.cellNumber));
			cNum.insert(3, '-');
			cNum.insert(7, '-');
		}
		
		String result = "";
		if(hNum != null && cNum != null)
		{
			result = String.format("%1$s %2$s \n" + 
					  "%3$s \n" +
					  "%4$s \n" +
					  "%5$s \n" + 
					  "%6$s \n" +
					  "%7$tD \n" +
					  "%8$s", 
					  this.firstName, this.lastName, hNum.toString(), cNum.toString(),
					  this.homeAddress, this.emailAddress, this.birthday, this.notes
					 );
		}
		else
		{
			result = String.format("%1$s %2$s \n" + 
					  "%3$s \n" +
					  "%4$s \n" +
					  "%5$s \n" + 
					  "%6$s \n" +
					  "%7$tD \n" +
					  "%8$s", 
					  this.firstName, this.lastName, String.valueOf(this.homeNumber), String.valueOf(this.cellNumber),
					  this.homeAddress, this.emailAddress, this.birthday, this.notes
					 );
		}
			
		return result;
	}
}
