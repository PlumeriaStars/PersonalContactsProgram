
# Contacts Program

## Original Plan

Contacts Program was a Java program capable of storing the contact information of various people locally on the user's PC. The program would store information in an object called Contact, and the user would be given the ability to create, add, or remove contacts. Users would also have the option to view all saved contacts sorted by either first or last names.
 
 
There were two major limitations with this version of the project. First, the limit on the max number of contacts stored was hard-coded. This was because contact information was stored in individual text files, and having too many contacts saved would lead to an excessive amount of text files that would result in a messy directory. Second, the program had no GUI and was conducted entirely through the Command Prompt. While this isn't necessarily bad, it should be noted that not all users may find it easy to use. 

-----------------------------------------------------------------------------------------------------------------------------------------------------

## Remake Plans

This program is a complete overhaul of one of my old Java projects. The original project was very ambitious and faced many limitations due to my inexperience with the language. Now, with a much a better understanding, I hope to create the program I originally envisioned all that time ago. This remake aims to address the aforementioned limitations in order to make the program more user-friendly and efficient. Through the use of either serialization or JSON formatting, the program will be able to save information on all contacts into a single file in order to store data between different runs of the the program. In addition, the program will have a fully functioning GUI that will allow the user to view, write, or change information. 

#### Contact Object:

 *Contact* 
>
> ~~~
> 	{
>      FirstName: String
>      LastName: String
>      HomeNumber: int
>      CellNumber: int
>      HomeAdress: String
>      EmailAddress: String
>      Birthday: Calendar
>      Notes: String
> 	}
> ~~~
> 

#### [Rough] Task Breakdown

- [ ] Create Contact object class
- [ ] Ability to add more Contacts 
- [ ] Ability to view Contacts 
- [ ] Ability to edit Contacts 
- [ ] Ability to delete Contacts 
- [ ] Write current Contacts into a file
- [ ] Read stored Contacts from a file
- [ ] Set up GUI

>
>
>
*Notes:*
- While the program is running, Contacts will be stored in an ArrayList<>() 
- Some level of user input error handling
- May attempt to make a user file to allow for GUI color customization 