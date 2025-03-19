package personal.contacts.program;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.jr.annotationsupport.JacksonAnnotationExtension;
import com.fasterxml.jackson.jr.ob.*;
import com.fasterxml.jackson.jr.ob.api.ExtensionContext;

public class JsonReadWrite 
{
	private static final String FILE_DIRECTORY = "saved\\";
	private static final String FILE_PATH = "data.json";
	private static File jsonFile = new File(FILE_DIRECTORY + FILE_PATH);
	
	public static void readFromFile()
	{
		List<Contact> tempList = new ArrayList<Contact>();
		try 
		{
			//Check to see if the .json file exists
			if(!jsonFile.exists())
			{
				//If the directory that holds the .json file does not exist, create it
				if(Files.notExists(Paths.get(FILE_DIRECTORY)))
					Files.createDirectories(Paths.get(FILE_DIRECTORY));
				
				//Create the .json file if necessary
				jsonFile.createNewFile();
			}
			//Else if, check to see if the .json has data that can be read from
			else if(jsonFile.length() > 0L)
			{
				tempList = JSON.std.listOfFrom(Contact.class, jsonFile);
				
				for(Contact c: tempList)
					ListOfContacts.addToContactsList(c);
			}
			//Else, .json exists but has no data -- do nothing in this case
				
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void writeToFile()
	{ 
		ArrayList<Contact> cList = Collections.list(ListOfContacts.getContactsList().elements());
		JSON annotationMapper = JSON.builder().register(JacksonAnnotationExtension.std)
									.register(new JacksonJrExtension() 
									{										
									   @Override
									   protected void register(ExtensionContext ctxt) 
									   {
									      ctxt.insertProvider(new CustomSerialization.CustomHandlerProvider());	
									   }
									})
									.build();
		try 
		{
			if(!jsonFile.exists())
				jsonFile.createNewFile();

			annotationMapper.with(JSON.Feature.PRETTY_PRINT_OUTPUT)
							.with(JSON.Feature.PRESERVE_FIELD_ORDERING)
							.with(JSON.Feature.WRITE_DATES_AS_TIMESTAMP)
							.write(cList, jsonFile);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
