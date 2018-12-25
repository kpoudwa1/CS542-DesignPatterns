package genericCheckpointing.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A class for performing file related operations.
 * @author Kedar Poudwal
 */
public class FileProcessor
{
	/**
	 * Enum for opening the file in C style in either
	 * read mode or write mode
	 * @author Kedar Poudwal
	 *
	 */
	public static enum FileLevel
	{
		READ,
		WRITE
	};
	
	private FileLevel mode;
	private BufferedReader bfrReader;
	private PrintWriter pwr;
	
	/**
	 * Creates a FileProcessor instance. It initializes it
	 *  with the input file path in the appropriate mode.
	 * @param filePath A String path for the input file
	 * @throws FileNotFoundException If the input file is not found
	 */
	public FileProcessor(String filePath, FileLevel modeIn) throws FileNotFoundException
	{
		try
		{
			this.mode = modeIn;
			if(mode.equals(FileLevel.READ))
				bfrReader = new BufferedReader(new FileReader(filePath));
			else if(mode.equals(FileLevel.WRITE))
				pwr = new PrintWriter(filePath);
		}
		catch(FileNotFoundException e)
		{
			throw e;
		}
	}
	
	/**
	 * Function reads the file one line
	 *  at a time and returns the line back to the
	 *  caller function for processing
	 * @return A single line read from the file
	 * @throws If an I/O error occurs
	 */
	public String readLine() throws IOException
	{
		String line = null;
		return ((line = bfrReader.readLine()) != null) ?  line.trim() : line;
	}
	
	/**
	 * Function for writing a line to a given file
	 * @param line The line to be written
	 */
	public void writeLine(String line)
	{
			pwr.println(line);
	}
	
	
	/**
	 * Static function for checking if the output file exists
	 *  or not. If the output file is exists and is not empty,
	 *  it throws an exception
	 * @param outputFilePath A String path for the output file
	 * @throws Exception If the output file exists and is not empty
	 */
	public static void checkOutputFile(String outputFilePath) throws Exception
	{
		try
		{
			/*Check if the output file already exists and is empty
			 or not*/
			File outputFile = new File(outputFilePath);
			
			if((!outputFile.isFile()) || (outputFile.isFile() && outputFile.length() == 0))
				return;
			else
				throw new Exception("Output file already exists and is not empty !");
		}
		catch(Exception e)
		{
			throw e;
		}
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
			if(mode.equals(FileLevel.READ) && bfrReader != null)
				bfrReader.close();
			else if(mode.equals(FileLevel.WRITE) && pwr != null)
				pwr.close();
		}
		catch(IOException e)
		{
			throw e;
		}
	}

	@Override
	public String toString()
	{
		return "FileProcessor [mode=" + mode + ", bfrReader=" + bfrReader
				+ ", pwr=" + pwr + "]";
	}
}