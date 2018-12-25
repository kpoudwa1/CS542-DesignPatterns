package primeNumberValidation.util;

import java.util.ArrayList;
import java.util.List;
import primeNumberValidation.util.MyLogger.DebugLevel;

/**
 * This class implements the StdoutDisplayInterface
 *  and writes the sum of the Prime numbers to the
 *  console 
 * @author Kedar Poudwal
 */
public class Results implements StdoutDisplayInterface
{
	private List<Integer> primeNoList;
	
	public Results()
	{
		MyLogger.writeMessage("Results()", DebugLevel.CONSTRUCTOR);
		primeNoList = new ArrayList<Integer>();
	}
	
	/**
	 * A thread safe method for adding the prime number read from
	 *  the file to the list of prime numbers
	 * @param primeNo The prime number to be added to the list
	 */
	public synchronized void addPrimeNo(int primeNo)
	{
		MyLogger.writeMessage("addPrimeNo()", DebugLevel.RESULTS_ENTRY_ADDED);
		primeNoList.add(primeNo);
	}

	/**
	 * Internal function used for calculating the sum
	 *  of the prime numbers
	 * @return Returns the sum of the prime numbers
	 */
	private long calculateSum()
	{
		long sum = 0;
		
		for(int i = 0; i < primeNoList.size(); i++)
			sum += primeNoList.get(i);
		
		return sum;
	}
	
	@Override
	public void writeSumToScreen()
	{
		MyLogger.writeMessage("The sum of all the prime numbers is: " + calculateSum(), DebugLevel.NO_OUTPUT);
	}

	@Override
	public String toString()
	{
		return "Results: " + primeNoList.toString();
	}
}