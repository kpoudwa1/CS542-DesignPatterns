package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * A handler class.
 * @author Kedar Poudwal
 */
public class StoreRestoreHandler implements InvocationHandler
{
	private FileProcessor fp;
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable 
	{
		String wireFormat = (String) args[args.length - 1];
		
		if(method.getName().equalsIgnoreCase("writeObj") && wireFormat.equalsIgnoreCase("XML"))
		{
			serializeData((SerializableObject) args[0], new XMLSerialization(fp));
		}
		else if(method.getName().equalsIgnoreCase("readObj") && wireFormat.equalsIgnoreCase("XML"))
		{
			return deserializeData(new XMLDeserialization(fp));
		}
		
		return null;
	}
	
	public void serializeData(SerializableObject sObject, SerStrategy sStrategy)
	{
        sStrategy.processInput(sObject);
	}
	
	public SerializableObject deserializeData(SerStrategy sStrategy)
	{
        return sStrategy.processInput();
	}

	public FileProcessor getFp()
	{
		return fp;
	}

	public void setFp(FileProcessor fp)
	{
		this.fp = fp;
	}
}