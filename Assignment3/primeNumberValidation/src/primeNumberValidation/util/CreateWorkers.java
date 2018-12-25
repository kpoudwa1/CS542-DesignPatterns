package primeNumberValidation.util;

import java.io.IOException;
import primeNumberValidation.util.MyLogger.DebugLevel;

/**
 * This class borrows the required number of threads from the
 *  thread pool class and then starts them and also performs
 *   join on them
 * @author Kedar Poudwal
 */
public class CreateWorkers
{
	private FileProcessor fp;
	private Results results;
	private IsPrime isPrime;
	
	public CreateWorkers(FileProcessor fpIn, Results resultsIn, IsPrime isPrimeIn)
	{
		MyLogger.writeMessage("CreateWorkers()", DebugLevel.CONSTRUCTOR);
		this.fp = fpIn;
		this.results = resultsIn;
		this.isPrime = isPrimeIn;
	}
	
	/**
	 * This function borrows the required number of threads from
	 *  the thread pool and then starts them and also performs
	 *   join on them 
	 * @param numOfThreads The number of threads passed as
	 *  command line argument to the program
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void startWorkers(int numOfThreads) throws InterruptedException, IOException
	{
		/*Function call for creating the required number of
		 threads in the thread pool*/ 
		ThreadPool.createThreads(numOfThreads, fp, results, isPrime);
		
		/*Borrowing of the threads, one thread at a time and
		 starting the thread and also performing join on
		 the thread*/
		for(int i = 0; i < numOfThreads; i++)
		{
			Runnable runnable = ThreadPool.borrowThreadObject();
			if(runnable != null)
			{
				((Thread) runnable).start();
				try
				{
					((Thread) runnable).join();
				}
				catch(InterruptedException e)
				{
					throw e;
				}
			}
		}
		
		//Closing the file
		try
		{
			fp.closeFile();
		}
		catch(IOException e)
		{
			throw e;
		}
	}

	@Override
	public String toString()
	{
		return "CreateWorkers [fp=" + fp + ", results=" + results
				+ ", isPrime=" + isPrime + "]";
	}
}