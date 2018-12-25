package genericCheckpointing.util;

import genericCheckpointing.visitor.Visitor;
import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper class which implements a subset of APIs of
 *  the java.util.List.
 * @author Kedar Poudwal
 *
 */
public class SerializableListWrapper
{
	private List<SerializableObject> listofObjects;
	
	public SerializableListWrapper()
	{
		listofObjects = new ArrayList<SerializableObject>();
	}
	
	/**
	 * Function for adding a SerializableObject to the list
	 * @param obj The SerializableObject to be added
	 * @return Returns true if the object was added,
	 *  else returns false
	 */
	public boolean add(SerializableObject obj)
	{
		return listofObjects.add(obj);
	}
	
	/**
	 * Function for getting the SerializableObject from
	 *  the list
	 * @param index The index
	 * @return SerializableObject
	 */
	public SerializableObject get(int index)
	{
		return listofObjects.get(index);
	}
	
	/**
	 * Function for accepting a visitor
	 * @param visitor The visitor to be accepted
	 */
	public void accept(Visitor visitor)
	{
		for(int i = 0; i < listofObjects.size(); i++)
		{
			if(listofObjects.get(i) instanceof MyAllTypesFirst)
				visitor.visit((MyAllTypesFirst) listofObjects.get(i));
			else if(listofObjects.get(i) instanceof MyAllTypesSecond)
				visitor.visit((MyAllTypesSecond) listofObjects.get(i));
		}
	}
}