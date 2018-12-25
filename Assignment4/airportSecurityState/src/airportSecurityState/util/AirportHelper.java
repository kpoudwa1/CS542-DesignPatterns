package airportSecurityState.util;

import java.io.IOException;

import airportSecurityState.airportStates.Airport;
import airportSecurityState.airportStates.AirportContextI;
import airportSecurityState.util.MyLogger.DebugLevel;

/**
 * Helper class for processing the input file 
 * @author Kedar Poudwal
 */
public class AirportHelper
{
	/**
	 * Static helper function for looping the input file and
	 *  updating the state of security after each line read from
	 *  the input file.
	 * @param fp The FileProcessor object for reading the file
	 * @throws IOException If an I/O error occurs
	 */
	public static void processFile(FileProcessor fp) throws IOException
	{
		try 
		{
			String line = null;
			AirportContextI airport = new Airport();
		
			/*Reading the input file one line at a time and
			 passing it to the process function for processing*/
			while((line = fp.readLine()) != null)
			{
				MyLogger.writeMessage(line, DebugLevel.INPUT_READ);
				airport.increaseOrDecreaseSecurity(line);
				MyLogger.writeMessage(airport.toString(), DebugLevel.AIRPORT_STATE_METRICS);
			}
		}
		catch(IOException e)
		{
			throw e;
		}
	}
}