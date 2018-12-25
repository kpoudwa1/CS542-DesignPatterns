package primeNumberValidation.util;

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
		THREAD_RUN,
		RESULTS_ENTRY_ADDED,
		RESULTS_CONTENT,
		NO_OUTPUT,
		NONE
	};

	private static DebugLevel debugLevel;

	/**
	 * Function for setting the debug level on the basis of
	 *  the numerical value passed<br><br>
	 * DEBUG_VALUE=4 [Prints to stdout every time a constructor
	 *  is called]<br>
	 * DEBUG_VALUE=3 [Prints to stdout every time a thread's run()
	 *  method is called]<br>
	 * DEBUG_VALUE=2 [Prints to stdout every time an entry is added
	 *  to the Results data structure]<br>
	 * DEBUG_VALUE=1 [Prints to stdout the contents of the data
	 *  structure in the store Results instance]<br>
	 * DEBUG_VALUE=0 [Prints the line "The sume of all the
	 *  prime numbers is: XYZ"]
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
				debugLevel = DebugLevel.THREAD_RUN;
				break;
			case 2:
				debugLevel = DebugLevel.RESULTS_ENTRY_ADDED;
				break;
			case 1:
				debugLevel = DebugLevel.RESULTS_CONTENT;
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
	 *  debug value to stdout
	 * @param message The message to be printed to stdout
	 * @param levelIn The debug level for the message
	 */
	public static void writeMessage(String message, DebugLevel levelIn)
	{
		if (levelIn == debugLevel)
			System.out.println(message);
	}

	/**
	 * This method prints the error message passed with the
	 *  appropriate debug value to stdout
	 * @param message The error message to be printed to stderr
	 * @param levelIn The debug level for the message
	 */
	public static void writeExceptionMessage(String message, DebugLevel levelIn)
	{
		if (levelIn == debugLevel)
			System.err.println(message);
	}
	
	/**
	 * This method prints the error object (stack trace) passed with
	 *  the appropriate debug value to stderr
	 * @param e The error object to be printed to stderr
	 * @param levelIn The debug level for the message
	 */
	public static void writeExceptionMessage(Exception e, DebugLevel levelIn)
	{
		if (levelIn == debugLevel)
			e.printStackTrace();
	}
	
	public String toString()
	{
		return "The debug level has been set to the following " + debugLevel;
	}
}