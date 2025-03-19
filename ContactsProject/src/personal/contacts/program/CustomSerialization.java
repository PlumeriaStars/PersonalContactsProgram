package personal.contacts.program;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.jr.ob.api.ReaderWriterProvider;
import com.fasterxml.jackson.jr.ob.api.ValueReader;
import com.fasterxml.jackson.jr.ob.api.ValueWriter;
import com.fasterxml.jackson.jr.ob.impl.JSONReader;
import com.fasterxml.jackson.jr.ob.impl.JSONWriter;

//Class that holds other classes related to custom serializing/deserializing
//Used specifically for a Contact's birthday(LocalDate)
public class CustomSerialization 
{
	public CustomSerialization() { }
	
	public static class CustomLocalDateSerializer implements ValueWriter 
	{
	    @Override
	    public Class<?> valueType()
	    {
	    	return LocalDate.class;
	    }
	 
	    @Override
	    public void writeValue(JSONWriter jsonWriter, JsonGenerator jsonGenerator, Object o) throws IOException 
	    {
	    	jsonGenerator.writeString(o.toString());
	    }
	}
	
	public static class CustomLocalDateDeserializer extends ValueReader 
	{
	    private final static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	 
	    public CustomLocalDateDeserializer() 
	    {
	        super(LocalDate.class);
	    }
	 
	    @Override
	    public Object read(JSONReader jsonReader, JsonParser jsonParser) throws IOException 
	    {
	        return LocalDate.parse(jsonParser.getText(), dateTimeFormat);
	    }
	}
	
	public static class CustomHandlerProvider extends ReaderWriterProvider
	{
		 @Override
		 public ValueWriter findValueWriter (JSONWriter writeContext, Class<?> type) 
		 {
		    if (type == LocalDate.class) 
		       return new CustomSerialization.CustomLocalDateSerializer();

		    return null;
		 }

		 @Override
		 public ValueReader findValueReader (JSONReader readContext, Class<?> type) 
		 {
		    if (type.equals(LocalDate.class)) 
		       return new CustomSerialization.CustomLocalDateDeserializer();

		    return null;
		}
	}
}
