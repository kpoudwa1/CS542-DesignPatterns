package airportSecurityState.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * A class consisting of static methods for implementing the logger
 *  with multiple log levels
 * @author Kedar Poudwal
 */
public class MyLogger
{

	/**
	 * Enum for representing the different log levels
	 * @author Kedar Poudwal
	 */
	public static enum DebugLevel
	{
		CONSTRUCTOR,
		STATE,
		AIRPORT_STATE_METRICS,
		INPUT_READ,
		NO_OUTPUT,
		NONE
	};

	private static DebugLevel debugLevel;
	private static PrintWriter pwr; 

	/**
	 * Function for setting the debug level on the basis of
	 *  the numerical value passed<br><br>
	 * DEBUG_VALUE=4 [Prints to file every time a constructor
	 *  is called]<br>
	 * DEBUG_VALUE=3 [Prints to file the name of the current 
	 *  state]<br>
	 * DEBUG_VALUE=2 [Prints to file the Airport Metrics such as
	 *  the number of travellers, number of prohibited items etc]
	 *  <br>
	 * DEBUG_VALUE=1 [Prints to file the line currently read]<br>
	 * DEBUG_VALUE=0 [Prints the operation ids of the tasks needed
	 *  to be performed for maintaining peace and harmony]
	 * @param levelIn The numerical value for setting the
	 *  debug level
	 */
	public static void setDebugValue(int levelIn)
	{
		switch(levelIn)
		{
			case 4:
				debugLevel = DebugLevel.CONSTRUCTOR;
				break;
			case 3:
				debugLevel = DebugLevel.STATE;
				break;
			case 2:
				debugLevel = DebugLevel.AIRPORT_STATE_METRICS;
				break;
			case 1:
				debugLevel = DebugLevel.INPUT_READ;
				break;
			case 0:
				debugLevel = DebugLevel.NO_OUTPUT;
				break;
			default:
				debugLevel = DebugLevel.NONE;
				break;
		}
	}

	/**
	 * Function for setting the output file to which print
	 *  statements are written.
	 * @param outputFilePath A String path for the output file
	 * @throws FileNotFoundException If the given file is not
	 *  writable file or if the file cannot be created or if
	 *  some other error occurs while opening or creating the file
	 */
	public static void setOutputFile(String outputFilePath) throws FileNotFoundException
	{
		try
		{
			pwr = new PrintWriter(outputFilePath);
		}
		catch(FileNotFoundException e)
		{
			throw e;
		}
	}
	
	/**
	 * Function for closing the PrintWriter object.
	 */
	public static void closeOutput()
	{
		if(pwr != null)
			pwr.close();
	}
	
	/**
	 * Function for setting the debug level on the basis of
	 *  the Enum value passed
	 * @param levelIn The Enum value for setting the
	 *  debug level
	 */
	public static void setDebugValue(DebugLevel levelIn)
	{
		debugLevel = levelIn;
	}

	/**
	 * This method prints the message passed with the appropriate
	 *  debug value to output file
	 * @param message The message to be printed to output file
	 * @param levelIn The debug level for the message
	 */
	public static void writeMessage(String message, DebugLevel levelIn)
	{
		if (levelIn == debugLevel)
			pwr.println(message);
	}

	/**
	 * This method prints the error message passed with the
	 *  appropriate debug value to stderr. The error messages
	 *  are printed to the stderr in case if the output file is
	 *  not writable or cannot be created.
	 * @param message The error message to be printed to stderr
	 * @param levelIn The debug level for the message
	 */
	public static void writeExceptionMessage(String message)
	{
		System.err.println(message);
	}
	
	/**
	 * This method prints the error object (stack trace) passed with
	 *  the appropriate debug value to stderr
	 * @param e The error object to be printed to stderr
	 * @param levelIn The debug level for the message
	 */
	public static void writeExceptionMessage(Exception e)
	{
		e.printStackTrace();
	}
	
	public String toString()
	{
		return "The debug level has been set to the following " + debugLevel;
	}
}