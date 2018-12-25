package genericCheckpointing.visitor;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A Palindrome visitor which checks if MyAllTypesFirst and 
 *  MyAllTypesSecond contains strings which are palindrome or not.
 * @author Kedar Poudwal
 */
public class PalindromVisitor implements Visitor
{
	@Override
	public void visit(MyAllTypesFirst myFirst)
	{
		try
		{
			//Getting the class
			Class<?> cls = myFirst.getClass();
			
			//Iterating over the fields
			for(Field field : cls.getDeclaredFields())
			{
				String fieldType = field.getType().toString();
				
				if(fieldType.equalsIgnoreCase("class java.lang.String"))
				{
					Method method = cls.getMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
					Object result = method.invoke(myFirst);
					
					String stringValue = result.toString();
					if(stringValue != null && isPalindrome(stringValue))
						System.out.println(stringValue + " is a palindrome");
				}
			}
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
		catch(IllegalAccessException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}

	@Override
	public void visit(MyAllTypesSecond mySecond) 
	{
		try
		{
			//Getting the class
			Class<?> cls = mySecond.getClass();
			
			//Iterating over the fields
			for(Field field : cls.getDeclaredFields())
			{
				String fieldType = field.getType().toString();
				
				if(fieldType.equalsIgnoreCase("class java.lang.String"))
				{
					Method method = cls.getMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
					Object result = method.invoke(mySecond);
					
					String stringValue = result.toString();
					if(stringValue != null && isPalindrome(stringValue))
						System.out.println(stringValue + " is a palindrome");
				}
			}
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
		catch(IllegalAccessException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/**
	 * Private function for checking if the string is
	 *  palindrome or not
	 * @param strValue The string to be checked
	 * @return Returns true if the string is palindrome
	 *  else returns false
	 */
	private boolean isPalindrome(String strValue)
	{
		String reverStr = "";
		
		for(int i = (strValue.length() - 1); i >= 0; i--)
			reverStr = reverStr + strValue.charAt(i);
		
		return strValue.equalsIgnoreCase(reverStr);
	}
}
