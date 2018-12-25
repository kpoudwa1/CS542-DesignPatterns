package airportSecurityState.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import airportSecurityState.util.FileProcessor;
import airportSecurityState.util.MyLogger;
import airportSecurityState.util.AirportHelper;

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
				System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
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
			
			/*Check if the output file exists or not and initialize
			 the output file for logger*/
			FileProcessor.checkOutputFile(args[1]);
			MyLogger.setOutputFile(args[1]);
			
			//Create FileProcessor object
			FileProcessor fp = new FileProcessor(args[0]);
			
			/*Calling the AirportHelper to read the file line
			 by line and updating the state of the of the Airport
			 Security*/  
			AirportHelper.processFile(fp);
			
			//Closing the input file 
			fp.closeFile();
			
			//Closing the output file
			MyLogger.closeOutput();
		}
		catch(NumberFormatException e)
		{
			MyLogger.writeExceptionMessage(e);
		}
		catch(FileNotFoundException e)
		{
			MyLogger.writeExceptionMessage(e);
		}
		catch(IOException e)
		{
			MyLogger.writeExceptionMessage(e);
		}
		catch(Exception e)
		{
			MyLogger.writeExceptionMessage(e);
		}
	}
}