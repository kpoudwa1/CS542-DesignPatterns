package primeNumberValidation.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import primeNumberValidation.util.MyLogger.DebugLevel;

/**
 * A class for performing file related operations.
 * @author Kedar Poudwal
 */
public class FileProcessor
{
	private BufferedReader bfrReader = null;
	
	public FileProcessor(String filePath) throws FileNotFoundException, IOException
	{
		MyLogger.writeMessage("FileProcessor()", DebugLevel.CONSTRUCTOR);
		try
		{
			bfrReader  = new BufferedReader(new FileReader(filePath));
		}
		catch(FileNotFoundException e)
		{
			throw e;
		}
	}
	
	/**
	 * This thread safe function reads the file one line
	 *  at a time and returns the line back to the
	 *  caller function for processing
	 * @return A single line read from the file
	 */
	public synchronized String readLine() throws IOException
	{
		String line = null;
		return ((line = bfrReader.readLine()) != null) ?  line.trim() : line;
	}
	
	/**
	 * This function is for closing the file
	 * @throws IOException
	 */
	public void closeFile() throws IOException
	{
		try
		{
			//Closing the file
			if(bfrReader != null)
				bfrReader.close();
		}
		catch(IOException e)
		{
			throw e;
		}
	}

	@Override
	public String toString()
	{
		return "FileProcessor [bfrReader=" + bfrReader + "]";
	}
}