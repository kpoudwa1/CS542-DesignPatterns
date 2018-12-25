package primeNumberValidation.util;

import primeNumberValidation.util.MyLogger.DebugLevel;

/**
 * A class for checking whether a given number is
 *  prime (odd) or not
 * @author Kedar Poudwal
 */
public class IsPrime
{
	public IsPrime()
	{
		MyLogger.writeMessage("IsPrime()", DebugLevel.CONSTRUCTOR);
	}
	
	/**
	 * Function for checking whether a given number is
	 *  prime (odd) or not
	 * @param number The number to be checked
	 * @return Returns true if the number is prime else
	 *  returns false if the number is not prime
	 */
	public boolean checkNum(int number)
	{
		return !(number % 2 == 0);
	}
}