package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for performing the deserialization of a XML file.
 *  It implements the SerStrategy interface.
 * @author Kedar Poudwal
 */
public class XMLDeserialization implements SerStrategy
{
	private FileProcessor fp;
	
	public XMLDeserialization(FileProcessor fpIn)
	{
		this.fp = fpIn;
	}

	@Override
	public SerializableObject processInput()
	{
		SerializableObject object = null;
		try
		{
			String line = null;
			
			//For marking the start and end of DPSerialization node
			String startTag = "<DPSerialization>";
			String endTag = "</DPSerialization>";
			
			if((line = fp.readLine()) != null)
			{
				List<String> complexTypeNode = new ArrayList<String>();
				if(line.equals(startTag))
				{
					/*For getting the complexType node as 
					 a list of strings*/
					while(!(line = fp.readLine()).equals(endTag))
					{
						complexTypeNode.add(line);
					}
					
					object = deserialize(complexTypeNode);
				}
			}
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		catch(InstantiationException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		catch(IllegalAccessException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		catch(NoSuchMethodException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		catch(InvocationTargetException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		return object;
	}
	
	/**
	 * Private function for deserializing complexType node
	 * @param complexTypeNode Lines read from the file
	 *  containing complexType node
	 * @return Returns an object deserialized from the file
	 */
	private SerializableObject deserialize(List<String> complexTypeNode) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException
	{
		Object serializableObject = null;
		try
		{
			//For deserializing the class name from the file
			String className = complexTypeNode.get(0).split("\\s")[1].replaceAll("\"", "").replace("xsi:type=", "").replace(">", "");
			
			//Creating a class object using reflection
			Class<?> cls = Class.forName(className);
			serializableObject = (SerializableObject) cls.newInstance();
			
			/*For iterating through the list of fields from
			 the file*/
			for(int i = 1; i < complexTypeNode.size() - 1; i++)
			{
				/*For deserializing the data type, variable name
				 and the value*/
				String dataType = complexTypeNode.get(i).split("\\s")[1].substring(0,  complexTypeNode.get(i).split("\\s")[1].indexOf(">")).replace("\"", "").replace("xsi:type=", "").replace("xsd:", "");
				String variableName = complexTypeNode.get(i).split("\\s")[0].replace("<", "");
				String value = complexTypeNode.get(i).substring(complexTypeNode.get(i).indexOf(">") + 1, complexTypeNode.get(i).lastIndexOf("<"));
				
				/*For converting "string" to
				 "class java.lang.String"*/
				if(dataType.equals("string"))
					dataType = "class java.lang." + dataType.substring(0, 1).toUpperCase() + dataType.substring(1);
				
				//For getting the mapping of signature and params
				Class<?> [] signature = new Class[1];
				Object [] params = new Object[1];
				if(dataType.equals(byte.class.toString()))
				{
					signature[0] = byte.class;
					params[0] = new Byte(Byte.parseByte(value));
				}
				else if(dataType.equals(char.class.toString()))
				{
					signature[0] = char.class;
					params[0] = value.charAt(0);
				}
				else if(dataType.equals(short.class.toString()))
				{
					signature[0] = short.class;
					params[0] = new Short(Short.parseShort(value));
				}
				else if(dataType.equals(int.class.toString()))
				{
					signature[0] = int.class;
					params[0] = new Integer(Integer.parseInt(value));
				}
				else if(dataType.equals(long.class.toString()))
				{
					signature[0] = long.class;
					params[0] = new Long(Long.parseLong(value));
				}
				else if(dataType.equals(float.class.toString()))
				{
					signature[0] = float.class;
					params[0] = new Float(Float.parseFloat(value));
				}
				else if(dataType.equals(double.class.toString()))
				{
					signature[0] = double.class;
					params[0] = new Double(Double.parseDouble(value));
				}
				else if(dataType.equals(boolean.class.toString()))
				{
					signature[0] = boolean.class;
					params[0] = new Boolean(Boolean.parseBoolean(value));
				}
				else if(dataType.equals(String.class.toString()))
				{
					signature[0] = String.class;
					params[0] = value;
				}
				
				//Getting the method name
				String methodName = "set" + variableName.substring(0, 1).toUpperCase() + variableName.substring(1);
				
				//Invoking the method for setting the value
				Method method = cls.getMethod(methodName, signature);
				method.invoke(serializableObject, params);
			}
		}
		catch(ClassNotFoundException e)
		{
			throw e;
		}
		catch(InstantiationException e)
		{
			throw e;
		}
		catch(IllegalAccessException e)
		{
			throw e;
		}
		catch(NoSuchMethodException e)
		{
			throw e;
		}
		catch(InvocationTargetException e)
		{
			throw e;
		}
		
		return (SerializableObject) serializableObject;
	}

	@Override
	public void processInput(SerializableObject sObject)
	{
	}
}