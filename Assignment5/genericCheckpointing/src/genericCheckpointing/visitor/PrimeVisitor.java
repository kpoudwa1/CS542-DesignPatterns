package genericCheckpointing.visitor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

/**
 * A Prime visitor which checks if MyAllTypesFirst and 
 *  MyAllTypesSecond contains integer and which are prime or not.
 * @author Kedar Poudwal
 */
public class PrimeVisitor implements Visitor
{
	private List<Long> uniquePrimes;

	public PrimeVisitor()
	{
		uniquePrimes = new ArrayList<Long>();
	}
	
	public int getUniquePrimeNos()
	{
		return uniquePrimes.size();
	}

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
				
				if(fieldType.equals("int") || fieldType.equals("long") || fieldType.equals("short"))
				{
					Method method = cls.getMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
					Object result = method.invoke(myFirst);
					
					long numValue = Long.parseLong(result.toString());
					
					if(numValue > 0 && isPrime(numValue))
					{
						if(!uniquePrimes.contains(numValue))
							uniquePrimes.add(numValue);
					}
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
				
				if(fieldType.equals("int") || fieldType.equals("long") || fieldType.equals("short"))
				{
					Method method = cls.getMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
					Object result = method.invoke(mySecond);
					
					long numValue = Long.parseLong(result.toString());
					
					if(numValue > 0 && isPrime(numValue))
					{
						if(!uniquePrimes.contains(numValue))
							uniquePrimes.add(numValue);
					}
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
	 * Private function for checking if a number is prime
	 *  or not. The code is referenced from the following
	 *  URL, 
	 *  https://www.javatpoint.com/prime-number-program-in-java
	 * @param numValue The number to be checked if it is a
	 *  prime or not
	 * @return Returns true if the number is prime else
	 *  returns false
	 */
	private boolean isPrime(long numValue)
	{
		long i, m = 0, flag = 0;
		m = numValue / 2;
		
		if (numValue == 0 || numValue == 1)
			return false;
		else
		{
			for(i = 2; i <= m; i++)
			{
				if(numValue % i == 0)
					return false;
			}
			
			if (flag == 0)
				return true;
		}
		return false;
	}
}