package primeNumberValidation.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import primeNumberValidation.util.CreateWorkers;
import primeNumberValidation.util.FileProcessor;
import primeNumberValidation.util.IsPrime;
import primeNumberValidation.util.MyLogger;
import primeNumberValidation.util.Results;
import primeNumberValidation.util.MyLogger.DebugLevel;

/**
 * @author Kedar Poudwal
 */
public class Driver 
{
	public static void main(String[] args)
	{
		try
		{
			/*
			 * As the build.xml specifies the arguments as argX,
			 *  in case the argument value is not given java takes
			 *  the default value specified in build.xml.
			 *   To avoid that, below condition is used
			 */
			if (args.length != 3 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}"))
			{
				System.err.println("Error: Incorrect number of arguments. Program accepts 3 argumnets.");
				System.exit(0);
			}
			
			//Check if the number of threads is between 1 and 5
			if(!(Integer.parseInt(args[1]) >= 1 && Integer.parseInt(args[1]) <= 5))
			{
				System.err.println("Error: Incorrect value of NUM_THREADS. Program accepts a value between 1 and 5.");
				System.exit(0);
			}
			
			//Check if the debug value is between 0 and 4
			if(!(Integer.parseInt(args[2]) >= 0 && Integer.parseInt(args[2]) <= 4))
			{
				System.err.println("Error: Incorrect value of DEBUG_VALUE. Program accepts a value between 0 and 4.");
				System.exit(0);
			}
			
			//Set the debug level
			MyLogger.setDebugValue(Integer.parseInt(args[2]));
			
			/*Creating instances of required classes to be passed to
			 the CreateWorkers class*/ 
			FileProcessor fp = new FileProcessor(args[0]);
			Results results = new Results();
			IsPrime isPrime = new IsPrime();
			
			CreateWorkers workers = new CreateWorkers(fp, results, isPrime);
			workers.startWorkers(Integer.parseInt(args[1]));
			
			MyLogger.writeMessage(results.toString(), DebugLevel.RESULTS_CONTENT);
			results.writeSumToScreen();
		}
		catch(NumberFormatException e)
		{
			System.err.println("Error: Numerical values should be entered for NUM_THREADS and DEBUG_VALUE");
			e.printStackTrace();
		}
		catch(FileNotFoundException e)
		{
			MyLogger.writeExceptionMessage(e, DebugLevel.NO_OUTPUT);
		}
		catch(IOException e)
		{
			MyLogger.writeExceptionMessage(e, DebugLevel.NO_OUTPUT);
		}
		catch(InterruptedException e)
		{
			MyLogger.writeExceptionMessage(e, DebugLevel.NO_OUTPUT);
		}
		catch(Exception e)
		{
			MyLogger.writeExceptionMessage(e, DebugLevel.NO_OUTPUT);
		}
	}
}