package studentCoursesBackup.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * A class for performing file related operations.
 * @author Kedar Poudwal
 */
public class FileProcessor
{
	//Added for reading the file content one line at a time
	private BufferedReader bfrReader = null;
	
	/**
	 * This function reads the file one line at a time and 
	 * returns the line back to the caller function for processing
	 * @param filePath Path to the file containing the data
	 * @return A single line read from the file
	 */
	public String readLine(String filePath) throws FileNotFoundException, IOException
	{
		String line = null;
		try
		{
			//Initialized when the function is called for the first time
			if(bfrReader == null)
			{
				bfrReader  = new BufferedReader(new FileReader(filePath));
			}
			
			//Read the line
			line = bfrReader.readLine();
		}
		catch(FileNotFoundException e)
		{
			if(bfrReader != null)
				bfrReader.close();
			throw e;
		}
		catch(IOException e)
		{
			if(bfrReader != null)
				bfrReader.close();
			throw e;
		}
		finally
		{
			try
			{
				//Closing the file at the end of line
				if(line == null && bfrReader != null)
					bfrReader.close();
			}
			catch(IOException e)
			{
				throw e;
			}
		}
		return line;
	}
}