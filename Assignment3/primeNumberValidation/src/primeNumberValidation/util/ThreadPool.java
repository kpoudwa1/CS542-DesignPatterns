package primeNumberValidation.util;

import java.util.ArrayList;
import java.util.List;

/**
 * A class with static methods for implementing the thread pool
 * @author Kedar Poudwal
 */
public class ThreadPool
{
	private static int numThreads = 0;
	private static List<Runnable> listOfThreads;
	
	/**
	 * Function for adding the thread to the Thread pool
	 * @param t The Thread object to be added to the Thread pool
	 */
	public static void addThreadObject(Thread t)
	{
		listOfThreads.add(t);
	}
	
	/**
	 * Function for creating the threads for the thread pool
	 * @param numOfThreads The number of threads to be created
	 * @param fp The FileProcessor object for performing file
	 *  operations
	 * @param results The Results object for storing the prime
	 *  numbers
	 * @param isPrime The IsPrime object for checking if a
	 *  number is prime
	 */
	public static void createThreads(int numOfThreads, FileProcessor fp, Results results, IsPrime isPrime)
	{
		//Instantiate the list
		listOfThreads = new ArrayList<Runnable>();
		numThreads = numOfThreads;
		
		for(int i = 0; i < numOfThreads; i++)
		{
			addThreadObject(new Thread(new WorkerThread(fp, results, isPrime)));
		}
	}
	
	/**
	 * Function for borrowing the thread from the Thread pool
	 * @return Returns the thread from the Thread pool
	 */
	public static Runnable borrowThreadObject()
	{
		if(listOfThreads.size() != 0)
			return listOfThreads.remove(0);
		return null;
	}
	
	/**
	 * Function for adding the returned object back to Thread pool
	 * @param t The returned Thread object
	 */
	public void returnThreadObject(Thread t)
	{
		listOfThreads.add(t);
	}

	/**
	 * Function for getting the number of active threads.
	 *  It is the difference between the total number of
	 *  threads created and the current number of idle threads
	 *  in the thread pool  
	 * @return Returns the number of active threads
	 */
	public int getNumActive()
	{
		return (numThreads - listOfThreads.size());
	}
	
	/**
	 * Function for getting the number of idle threads in the pool.
	 *  It is the count of the number of threads current in the
	 *  thread pool.
	 * @return Returns the number of idle threads
	 */
	public int getNumIdle()
	{
		return listOfThreads.size();
	}
	
	@Override
	public String toString()
	{
		return "The thread pool size is " + numThreads;
	}
}