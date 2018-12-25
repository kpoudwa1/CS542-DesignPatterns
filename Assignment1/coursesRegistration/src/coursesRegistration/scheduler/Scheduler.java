package coursesRegistration.scheduler;

import java.util.ArrayList;
import java.util.List;
import coursesRegistration.beans.Allocation;
import coursesRegistration.beans.Course;
import coursesRegistration.beans.Student;
import coursesRegistration.beans.StudentLevel;
import coursesRegistration.util.CourseHelper;
import coursesRegistration.util.StudentHelper;

/**
 * This class contains the scheduling algorithm for assigning
 *  the courses to the students. The assignment of courses is
 *  done as per the student level, with highest priority given
 *  to third year student and the least given to the first
 *   year student.
 * @author Kedar Poudwal
 *
 */
public class Scheduler 
{
	/*List for maintaining the list of students
	  and the courses allocated to them*/
	private List<Allocation> allocatedList = null;
	
	/**
	 * This function allocates the courses to the students as per
	 *  their preferences
	 * @param studentList List of the students with their preferences
	 *  and their level
	 * @param courses List of the courses with their timings and
	 *  available seats
	 */
	public void allocateCourses(List<Student> studentList, List<Course> courses)
	{
		//Create an empty list
		allocatedList = new ArrayList<Allocation>();
		
		/*Allocate courses to the student on the basis of their level
		with highest priority given to third year student and
		the least given to the first year student*/
		priorityAllocation(studentList, courses, StudentLevel.THIRD_YEAR);
		priorityAllocation(studentList, courses, StudentLevel.SECOND_YEAR);
		priorityAllocation(studentList, courses, StudentLevel.FIRST_YEAR);
	}
	
	/**
	 * This function is a generic function for allocating the courses
	 *  to the students as per the student's level 
	 * @param studentList List of the students with their preferences
	 *  and their level
	 * @param courses List of the courses with their timings and
	 *  available seats
	 * @param studentYear Filter for filtering the students of
	 *  a particular year
	 */
	private void priorityAllocation(List<Student> studentList, List<Course> courses, StudentLevel studentYear)
	{
		//Iterating through the list of the students
		for(int i = 0; i < studentList.size(); i++)
		{
			//Filtering out students of a particular level
			if(StudentHelper.getStudentLevel(studentList.get(i).getStudentId()).equals(studentYear))
			{
				//Flag for deciding whether the student is added to the pending list
				//boolean pending = false;
				
				//Converting the student's preferences to an array 
				String temp = studentList.get(i).getPref1() + ":"
							+ studentList.get(i).getPref2() + ":"
							+ studentList.get(i).getPref3() + ":"
							+ studentList.get(i).getPref4() + ":"
							+ studentList.get(i).getPref5() + ":"
							+ studentList.get(i).getPref6() + ":";
				String [] prefArray = temp.split(":");
				
				//New Allocation object
				Allocation a = new Allocation();
				a.setStudentId(studentList.get(i).getStudentId());
				
				for(int j = 0; j < prefArray.length; j++)
				{
					if(checkAvailbility(a, prefArray[j]))
					{
						/*Allocate the course and update the number of
						occupied seats for the course*/
						if(a.getAllocation1() == null)
						{
							a.setAllocation1(prefArray[j]);
							CourseHelper.allocateSeat(prefArray[j]);
						}
						else if(a.getAllocation2() == null)
						{
							a.setAllocation2(prefArray[j]);
							CourseHelper.allocateSeat(prefArray[j]);
						}
						else
						{
							a.setAllocation3(prefArray[j]);
							CourseHelper.allocateSeat(prefArray[j]);
						}
					}
					
					//Break the loop if 3 courses have already been allocated
					if(a.getAllocation1() != null && a.getAllocation2() != null && a.getAllocation3() != null)
						break;
				}
				
				//Adding the allocation to the list
				allocatedList.add(a);
			}
		}
	}
	
	/**
	 * This is a function internally used by the scheduler to verify
	 *  the constraints and to decide whether the preferred course
	 *   can be allocated to the student or not
	 * @param alloc The allocation details of the student
	 * @param course The course for which the contstraints have
	 *  to be verified
	 * @return Returns true if the course can be allocated and
	 *  false if the course cannot be allocated to the student
	 */
	private boolean checkAvailbility(Allocation alloc, String course)
	{
		boolean allocate = false;
		
		//Check if 3 courses have already been allocated to the student
		if(alloc.getAllocation1() != null && alloc.getAllocation2() != null && alloc.getAllocation3() != null)
		{
			//allocate = true;
			return false;
		}
		
		//Check if there are any clash in timings for classes
		int time1 = -999;
		int time2 = -999;
		int time = -999;
		
		if(alloc.getAllocation1() != null)
			time1 = CourseHelper.getClassTime(alloc.getAllocation1());
		
		if(alloc.getAllocation2() != null)
			time2 = CourseHelper.getClassTime(alloc.getAllocation2());
		
		time = CourseHelper.getClassTime(course);
		
		if(time1 != time && time2 != time)
			allocate = true;
		
		//Check if there are empty seats for the desired course 
		if(allocate)
			return CourseHelper.getSeatAvailability(course);
		
		return allocate;
	}

	public List<Allocation> getAllocatedList()
	{
		return allocatedList;
	}
}