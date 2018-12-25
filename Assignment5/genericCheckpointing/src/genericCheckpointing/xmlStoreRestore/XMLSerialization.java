package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A class for performing the serialization of a Java object
 *  to a XML file. It implements the SerStrategy interface.
 * @author Kedar Poudwal
 */
public class XMLSerialization implements SerStrategy
{
	private FileProcessor fp;

	//XML template tags
	private String dpStartTag = "<DPSerialization>";
	private String dpEndTag = "\n</DPSerialization>";
	private String complexStartTag = "\n\t<complexType xsi:type=\"CLASSNAME\">";
	private String complexEndTag = "\n\t</complexType>";
	
	public XMLSerialization(FileProcessor fpIn)
	{
		this.fp = fpIn;
	}
	
	@Override
	public void processInput(SerializableObject sObject)
	{
		try
		{
			//Getting the class
			Class<?> cls = sObject.getClass();
			
			//Replacing the class name in the complexType tag
			complexStartTag = complexStartTag.replaceAll("CLASSNAME", sObject.getClass().toString().replaceAll("class ", ""));
			
			String dpNode = new String();
			dpNode = dpNode.concat(dpStartTag);
			dpNode = dpNode.concat(complexStartTag);
			
			//Iterating over the fields
			for(Field field : cls.getDeclaredFields())
			{
				//Field tag template
				String fieldTag = "\n\t\t<FIELDNAME xsi:type=\"xsd:FIELDTYPE\">FIELDVALUE</FIELDNAME>";
				
				//Getting the values for field name and data type
				String fieldName = field.getName();
				String fieldType = field.getType().toString();
				
				//Getting the value for the variable
				Method method = null;
				Object result = null;
				
				//Handling of getter for boolean
				if(fieldType.equals(boolean.class.toString()))
					method = cls.getMethod("is" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
				else
					method = cls.getMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
				
				/*Invoking the method for getting the value
				   of the field*/
				result = method.invoke(sObject);
				
				//Handling of data type for String
				if(fieldType.equalsIgnoreCase("class java.lang.String"))
					fieldType = fieldType.substring(fieldType.lastIndexOf(".") + 1).toLowerCase();
				
				//Creating the field tag
				fieldTag = fieldTag.replaceAll("FIELDNAME", fieldName).replaceAll("FIELDTYPE", fieldType);
				
				/*For handling of values less than 10
				 for int, long and double*/
				if(fieldType.equals(int.class.toString()))
				{
					int intValue = Integer.parseInt(result.toString());
					
					if(intValue < 10)
						fieldTag = fieldTag.replaceAll("FIELDVALUE", "0");
					else
						fieldTag = fieldTag.replaceAll("FIELDVALUE", result.toString());
				}
				else if(fieldType.equals(double.class.toString()))
				{
					double doubleValue = Double.parseDouble(result.toString());
					
					if(doubleValue < 10)
						fieldTag = fieldTag.replaceAll("FIELDVALUE", "0");
					else
						fieldTag = fieldTag.replaceAll("FIELDVALUE", result.toString());
				}
				else if(fieldType.equals(long.class.toString()))
				{
					long longValue = Long.parseLong(result.toString());
					
					if(longValue < 10)
						fieldTag = fieldTag.replaceAll("FIELDVALUE", "0");
					else
						fieldTag = fieldTag.replaceAll("FIELDVALUE", result.toString());
				}
				else
					fieldTag = fieldTag.replaceAll("FIELDVALUE", result.toString());
				
				//Appending the field
				dpNode = dpNode.concat(fieldTag);
			}
			//Appending the closing tags
			dpNode = dpNode.concat(complexEndTag);
			dpNode = dpNode.concat(dpEndTag);
			
			//Write the node to file
			fp.writeLine(dpNode);
		}
		catch(NoSuchMethodException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		catch(IllegalAccessException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		catch(InvocationTargetException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}

	@Override
	public SerializableObject processInput()
	{
		return null;
	}
}