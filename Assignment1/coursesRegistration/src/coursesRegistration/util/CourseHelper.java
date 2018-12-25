package coursesRegistration.util;

import java.util.ArrayList;
import java.util.List;
import coursesRegistration.beans.Course;

/**
 * This class is a helper class containing static helper methods
 *  for performing courses related operations
 * @author Kedar Poudwal
 */
public class CourseHelper
{
	//List for maintain the list of courses
	private static List<Course> courseList = null;
	
	/**
	 * Static helper function for converting the lines read from
	 *  the file to a list of courses
	 * @param lines The lines read from the file containing the
	 *  courses information 
	 * @return A list of Course objects
	 */
	public static List<Course> createList(List<String> lines)
	{
		//Init an empty list
		courseList = new ArrayList<Course>();
		
		for(int i = 0; i < lines.size(); i++)
		{
			//Create a temporary course object
			Course c = new Course();
			c.setCourseName(lines.get(i).substring(0, 1));
			String []arr = lines.get(i).split(";");
			c.setCapacity(Integer.parseInt(arr[0].substring(arr[0].lastIndexOf(":") + 1).trim()));
			c.setClassTiming(Integer.parseInt(arr[1].replace("CLASS_TIMING:", "").trim()));
			c.setOccupiedSeats(0);
			
			//Add the course object to the list
			courseList.add(c);
		}
		return courseList;
	}
	
	/**
	 * Static helper function for getting the class time for
	 *  the specified course
	 * @param courseName The name of the course for which class time
	 *  has to be determined
	 * @return The time of the class
	 */
	public static int getClassTime(String courseName)
	{
		for(int i = 0; i < courseList.size(); i++)
			if(courseList.get(i).getCourseName().equalsIgnoreCase(courseName))
				return courseList.get(i).getClassTiming();
		
		return -999;
	}
	
	/**
	 * Static helper function for checking if seats are available
	 *  for the desired course
	 * @param courseName The name of the course for which seat
	 *  availability has to be determined
	 * @return Returns true if seats are available else returns
	 *  false
	 */
	public static boolean getSeatAvailability(String courseName)
	{
		for(int i = 0; i < courseList.size(); i++)
			if(courseList.get(i).getCourseName().equalsIgnoreCase(courseName))
				if(courseList.get(i).getCapacity() == courseList.get(i).getOccupiedSeats())
					return false;
		
		return true;
	}
	
	/**
	 * Static helper function for updating the number of occupied
	 *  seats for the desired course
	 * @param courseName The name of the course for which the number
	 *  of seats has to be updated
	 */
	public static void allocateSeat(String courseName)
	{
		int seats = -999;
		int index = -999;
		for(int i = 0; i < courseList.size(); i++)
		{
			if(courseList.get(i).getCourseName().equalsIgnoreCase(courseName))
			{
				index = i;
				seats = courseList.get(i).getOccupiedSeats();
			}
		}
		
		//Update the seats for the course
		if(index != -999)
			courseList.get(index).setOccupiedSeats(++seats);
	}
}
