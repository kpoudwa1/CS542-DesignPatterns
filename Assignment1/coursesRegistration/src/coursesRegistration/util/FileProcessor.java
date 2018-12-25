package coursesRegistration.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * A class for performing file related operations.
 * @author Kedar Poudwal
 * 
 */
public class FileProcessor
{
	/*Added for reading the file containing Student's preferences
	one line at a time*/
	private BufferedReader bfrReader = null;
	
	/**
	 * This function reads the file containing information about
	 * courses and returns the contents of the file as a list
	 *  of Strings
	 * @param filePath Path to the file containing the courses
	 *  information
	 * @return The lines from the file as a List
	 */
	public List<String> readCoursesInfo(String filePath)
	{
		List<String> fileContent = null; 
		try
		{
			fileContent = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
		}
		catch(IOException e)
		{
			System.out.println("Error in reading the course information");
			System.out.println("MESSAGE: " + e.getMessage());
			//e.printStackTrace();
		}
		return fileContent;
	}
	
	/**
	 * This function reads the file containing information about
	 *  student preferences one line at a time and returns the
	 *  line back to the caller function for processing
	 * @param filePath Path to the file containing the courses
	 *  information
	 * @return A single line read from the file
	 */
	public String readStudentsPrefs(String filePath)
	{
		String line = null;
		try
		{
			//Initialized when the function is called for the first time
			if(bfrReader == null)
			{
				bfrReader  = new BufferedReader(new FileReader(filePath));
			}
			
			while((line = bfrReader.readLine()) != null)
				break;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error in reading the student's preferences");
			System.out.println("MESSAGE: " + e.getMessage());
		}
		catch(IOException e)
		{
			System.out.println("Error in reading the student's preferences");
			System.out.println("MESSAGE: " + e.getMessage());
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
				System.out.println("Error in closing the file connection for reading the student's preferences");
				System.out.println("MESSAGE: " + e.getMessage());
			}
		}
		return line;
	}
}