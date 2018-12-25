package coursesRegistration.beans;

/**
 * This class is a representation of the courses
 * @author Kedar Poudwal
 *
 */
public class Course 
{
	private String courseName;
	private int capacity;
	private int classTiming;
	private int occupiedSeats;
	
	public String getCourseName() 
	{
		return courseName;
	}
	
	public void setCourseName(String courseName) 
	{
		this.courseName = courseName;
	}
	
	public int getCapacity() 
	{
		return capacity;
	}
	
	public void setCapacity(int capacity) 
	{
		this.capacity = capacity;
	}
	
	public int getClassTiming() 
	{
		return classTiming;
	}
	
	public void setClassTiming(int classTiming) 
	{
		this.classTiming = classTiming;
	}
	
	public int getOccupiedSeats() 
	{
		return occupiedSeats;
	}

	public void setOccupiedSeats(int occupiedSeats) 
	{
		this.occupiedSeats = occupiedSeats;
	}

	@Override
	public String toString() 
	{
		return "Course [courseName=" + courseName + ", capacity=" + capacity
				+ ", classTiming=" + classTiming + ", occupiedSeats="
				+ occupiedSeats + "]";
	}
}