package coursesRegistration.util;

import java.util.ArrayList;
import java.util.List;
import coursesRegistration.beans.Student;
import coursesRegistration.beans.StudentLevel;

/**
 * This class is a helper class containing static helper methods
 *  for processing student preferences of the courses
 * @author Kedar Poudwal
 */
public class StudentHelper 
{
	//List for maintain the list of students
	private static List<Student> studentList = new ArrayList<Student>();
	
	/**
	 * Static helper function for converting the single line read
	 *  from the file to a student object and the adding the
	 *  object in the list
	 * @param line A single line read from the file containing the
	 *  student information 
	 */
	public static void createList(String line)
	{
		String []temp = line.split(";");
		temp = temp[0].split(" ");
		
		Student s = new Student();
		s.setStudentId(temp[0].trim());
		s.setPref1(temp[1].replace(",", "").trim());
		s.setPref2(temp[2].replace(",", "").trim());
		s.setPref3(temp[3].replace(",", "").trim());
		s.setPref4(temp[4].replace(",", "").trim());
		s.setPref5(temp[5].replace(",", "").trim());
		s.setPref6(temp[6].replace(",", "").trim());
		s.setStudentLevel(StudentLevel.valueOf(line.split(";")[1].replace("STUDENT_LEVEL:", "").trim()));
		
		studentList.add(s);
	}

	/**
	 * Static helper function for getting the student level for
	 *  a particular student from the list of students 
	 * @param studentId Student id for which student level
	 *  has be searched
	 * @return The level of the student 
	 */
	public static StudentLevel getStudentLevel(String studentId)
	{
		for(int i = 0; i < studentList.size(); i++)
			if(studentList.get(i).getStudentId().equalsIgnoreCase(studentId))
					return studentList.get(i).getStudentLevel();
		return null;
	}
	
	public static List<Student> getStudentList() 
	{
		return studentList;
	}
}