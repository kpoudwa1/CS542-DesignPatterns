package airportSecurityState.airportStates;

import airportSecurityState.util.MyLogger;
import airportSecurityState.util.MyLogger.DebugLevel;

/**
 * A class which represents the Airport. It implements
 *  the AirportContextI interface.
 * @author Kedar Poudwal
 */
public class Airport implements AirportContextI
{
	private AirportStateI currentState;
	private int noOfTravellers;
	private int noOfProhibitedItems;
	private int noOfDays;
	private int avgTrafficPerDay;
	private int avgProhibitedItemsPerDay;
	
	//State references
	private AirportStateI lowRisk;
	private AirportStateI moderateRisk;
	private AirportStateI highRisk;
	
	/**
	 * Creates an Airport instance with currentState set
	 *  to LowRisk state.
	 */
	public Airport()
	{
		MyLogger.writeMessage("Airport()", DebugLevel.CONSTRUCTOR);
		
		//Initialize states
		lowRisk = new LowRisk(this);
		moderateRisk = new ModerateRisk(this);
		highRisk = new HighRisk(this);
		
		//Initialize variables
		noOfTravellers = 0;
		noOfProhibitedItems = 0;
		noOfDays = 0;
		
		currentState = lowRisk;
	}

	public AirportStateI getCurrentState()
	{
		return currentState;
	}

	public void setCurrentState(AirportStateI currentState)
	{
		this.currentState = currentState;
	}

	public int getNoOfTravellers()
	{
		return noOfTravellers;
	}

	public void setNoOfTravellers(int noOfTravellers) 
	{
		this.noOfTravellers = noOfTravellers;
	}

	public int getNoOfProhibitedItems()
	{
		return noOfProhibitedItems;
	}

	public void setNoOfProhibitedItems(int noOfProhibitedItems)
	{
		this.noOfProhibitedItems = noOfProhibitedItems;
	}

	public int getNoOfDays() 
	{
		return noOfDays;
	}

	public void setNoOfDays(int noOfDays) 
	{
		this.noOfDays = noOfDays;
	}

	public int getAvgTrafficPerDay() 
	{
		return avgTrafficPerDay;
	}

	public void setAvgTrafficPerDay(int avgTrafficPerDay) 
	{
		this.avgTrafficPerDay = avgTrafficPerDay;
	}

	public int getAvgProhibitedItemsPerDay() 
	{
		return avgProhibitedItemsPerDay;
	}

	public void setAvgProhibitedItemsPerDay(int avgProhibitedItemsPerDay) 
	{
		this.avgProhibitedItemsPerDay = avgProhibitedItemsPerDay;
	}

	public AirportStateI getLowRisk()
	{
		return lowRisk;
	}

	public void setLowRisk(AirportStateI lowRisk)
	{
		this.lowRisk = lowRisk;
	}

	public AirportStateI getModerateRisk()
	{
		return moderateRisk;
	}

	public void setModerateRisk(AirportStateI moderateRisk)
	{
		this.moderateRisk = moderateRisk;
	}

	public AirportStateI getHighRisk()
	{
		return highRisk;
	}

	public void setHighRisk(AirportStateI highRisk)
	{
		this.highRisk = highRisk;
	}

	/**
	 * Function for checking if a given item is prohibited or not.
	 *  The function maps the string to the enum value. In case
	 *  the item is not prohibited, an IllegalArgumentException
	 *  is thrown which is caught and handled appropriately. 
	 * @param item The item to be checked if it is prohibited or not
	 * @return Returns true if the item is prohibited else returns
	 *  false if the item is not prohibited
	 */
	public boolean checkItemProhibited(String item)
	{
		try
		{
			ProhibitedItems.valueOf(item.toUpperCase());
			return true;
		}
		catch(IllegalArgumentException e)
		{
			return false;
		}
	}
	
	@Override
	public void increaseOrDecreaseSecurity(String line)
	{
		currentState.increaseOrDecreaseSecurity(line);
	}

	@Override
	public String toString()
	{
		return "Airport [noOfTravellers=" + noOfTravellers
				+ ", noOfProhibitedItems=" + noOfProhibitedItems
				+ ", noOfDays=" + noOfDays + ", avgTrafficPerDay="
				+ avgTrafficPerDay + ", avgProhibitedItemsPerDay="
				+ avgProhibitedItemsPerDay + "]";
	}
}