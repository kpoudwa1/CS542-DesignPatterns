package primeNumberValidation.util;

import java.io.IOException;
import primeNumberValidation.util.MyLogger.DebugLevel;

/**
 * A class for implementing the worker thread which reads
 *  the line, checks if the number is prime and adds
 *  the number to the results class. 
 * @author Kedar Poudwal
 */
public class WorkerThread implements Runnable
{
	private FileProcessor fp;
	private Results results;
	private IsPrime isPrime;

	public WorkerThread(FileProcessor fpIn, Results resultsIn, IsPrime isPrimeIn)
	{
		MyLogger.writeMessage("WorkerThread()", DebugLevel.CONSTRUCTOR);
		this.fp = fpIn;
		this.results = resultsIn;
		this.isPrime = isPrimeIn;
	}

	/**
	 * The worker thread reads the file one line at a time,
	 *  checks if the given number is prime or not and if the number
	 *  is prime adds it to the list of prime numbers in
	 *  Results class
	 */
	@Override
	public void run()
	{
		MyLogger.writeMessage("run()", DebugLevel.THREAD_RUN);
		try
		{
			String line = null;
			/*Read the file line by line until the end of
			 the file is reached*/
			while((line = fp.readLine()) != null)
			{
				//Check if the number is prime or not
				boolean prime = isPrime.checkNum(Integer.parseInt(line));
				
				//Add the number to the results class
				if(prime)
					results.addPrimeNo(Integer.parseInt(line));
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public String toString()
	{
		return "WorkerThread [fp=" + fp + ", results=" + results + ", isPrime="
				+ isPrime + "]";
	}
}