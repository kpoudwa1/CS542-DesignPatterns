package coursesRegistration.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import coursesRegistration.beans.Allocation;

/**
 * This class implements the FileDisplayInterface and
 *  StdoutDisplayInterface and writes the allocation
 *   results to the file and the console 
 * @author Kedar Poudwal
 *
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface
{
	@Override
	public void writeToConsole(List<Allocation> allocatedList)
	{
		System.out.println("The courses have been allocated as follows,");
		for(int i = 0; i < allocatedList.size(); i++)
			System.out.println(allocatedList.get(i));
	}

	@Override
	public void writeToFile(List<Allocation> allocatedList, String filePath) 
	{
		try
		{
			PrintWriter pwr = new PrintWriter(filePath);
			for(int i = 0; i < allocatedList.size(); i++)
				pwr.println(allocatedList.get(i));
			pwr.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error in writing the allocation list to the file");
			System.out.println("MESSAGE: " + e.getMessage());
		}
	}
}