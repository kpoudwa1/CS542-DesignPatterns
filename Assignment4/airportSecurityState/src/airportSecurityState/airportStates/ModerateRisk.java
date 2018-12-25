package airportSecurityState.airportStates;

import airportSecurityState.util.MyLogger;
import airportSecurityState.util.MyLogger.DebugLevel;

/**
 * A class representing the ModerateRisk condition of the Airport.
 *  It implements the AirportStateI interface.
 * @author Kedar Poudwal
 */
public class ModerateRisk implements AirportStateI
{
	private Airport airport;
	
	/*Constant string representing the operations to be performed
	 in ModerateRisk condition*/
	private static final String OPERATION_ID = "2 3 5 8 9";
	
	/**
	 * Creates a ModerateRisk instance initialized with the reference
	 *  to the Airport instance
	 * @param airport The reference for the
	 *  Airport instance
	 */
	public ModerateRisk(Airport airport)
	{
		MyLogger.writeMessage("ModerateRisk()", DebugLevel.CONSTRUCTOR);
		this.airport = airport;
	}
	
	@Override
	public void increaseOrDecreaseSecurity(String line)
	{
		//Splitting the line read from the file
		String []arr = line.split(";");
						
		//Setting the value for number of days
		int noOfDays = Integer.parseInt(arr[0].split(":")[1].trim());
		airport.setNoOfDays(noOfDays);
						
		//Setting the number of travelers
		int noOfTravellers = airport.getNoOfTravellers();
		airport.setNoOfTravellers(++noOfTravellers);
						
		//Checking if the item is prohibited or not
		if(airport.checkItemProhibited(arr[1].split(":")[1].trim()))
		{
			int noOfProhibitedItems = airport.getNoOfProhibitedItems();
			airport.setNoOfProhibitedItems(++noOfProhibitedItems);
		}
		
		//Calculating the required averages
		int avgTrafficPerDay = calculateAvgTrafficPerDay(airport.getNoOfTravellers(), airport.getNoOfDays());
		int avgProhibitedItemsPerDay = calculateAvgProhibitedItemsPerDay(airport.getNoOfProhibitedItems(), airport.getNoOfDays());
				
		//Set the required averages in the Airport instance
		airport.setAvgTrafficPerDay(avgTrafficPerDay);
		airport.setAvgProhibitedItemsPerDay(avgProhibitedItemsPerDay);

		//Calculating the state
		if((0 <= airport.getAvgTrafficPerDay() && airport.getAvgTrafficPerDay() < 4) ||
				(0 <= airport.getAvgProhibitedItemsPerDay() && airport.getAvgProhibitedItemsPerDay() < 2))
		{
			airport.setCurrentState(airport.getLowRisk());
		}
		if((4 <= airport.getAvgTrafficPerDay() && airport.getAvgTrafficPerDay() < 8) ||
				(2 <= airport.getAvgProhibitedItemsPerDay() && airport.getAvgProhibitedItemsPerDay() < 4))
		{
			airport.setCurrentState(airport.getModerateRisk());
		}
		if((airport.getAvgTrafficPerDay() >= 8) || (airport.getAvgProhibitedItemsPerDay() >= 4))
		{
			airport.setCurrentState(airport.getHighRisk());
		}
		
		//Printing the operation id to the output file
		MyLogger.writeMessage(airport.getCurrentState().toString(), DebugLevel.NO_OUTPUT);
		
		//Printing the state name
		MyLogger.writeMessage(airport.getCurrentState().getClass().getSimpleName(), DebugLevel.STATE);
	}

	private int calculateAvgTrafficPerDay(int noOfTravellers, int noOfDays)
	{
		return (noOfTravellers / noOfDays);
	}
	
	private int calculateAvgProhibitedItemsPerDay(int noOfProhibitedItems, int noOfDays)
	{
		return (noOfProhibitedItems / noOfDays);
	}
	
	@Override
	public String toString()
	{
		return OPERATION_ID;
	}
}