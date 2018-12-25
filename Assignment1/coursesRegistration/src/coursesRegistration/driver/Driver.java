package coursesRegistration.driver;
import java.util.List;

import coursesRegistration.beans.Allocation;
import coursesRegistration.beans.Course;
import coursesRegistration.scheduler.Scheduler;
import coursesRegistration.util.CourseHelper;
import coursesRegistration.util.FileDisplayInterface;
import coursesRegistration.util.FileProcessor;
import coursesRegistration.util.Results;
import coursesRegistration.util.StdoutDisplayInterface;
import coursesRegistration.util.StudentHelper;

/**
 * @author Kedar Poudwal
 *
 */
public class Driver 
{
	public static void main(String[] args) 
	{
		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if (args.length != 2 || args[0].equals("${arg0}") || args[1].equals("${arg1}"))
		{

			System.err.println("Error: Incorrect number of arguments. Program accepts 2 arguments.");
			System.exit(0);
		}
		
		System.out.println("Hello World! Lets get started with the assignment");

		//Read the courses and create an array list of courses
		FileProcessor fp = new FileProcessor();
		
		//Read the content of the course file
		List<String> lines = fp.readCoursesInfo(args[1]);
		
		//Exit program if there was an error in reading the file
		if(lines == null)
		{
			System.exit(1);
		}
		
		//List of courses
		List<Course> courses = CourseHelper.createList(lines);
		
		/*Reading the student's preferences line by line
		and creating a list*/
		String line = null;
		while((line = fp.readStudentsPrefs(args[0])) != null)
		{
			StudentHelper.createList(line);
		}
		
		//Exit program if there was an error in reading the file
		if(StudentHelper.getStudentList().size() == 0)
		{
			System.exit(1);
		}
		
		//Allocate courses
		Scheduler s = new Scheduler();
		s.allocateCourses(StudentHelper.getStudentList(), courses);
		List<Allocation> allocations = s.getAllocatedList();
		
		//Print the results
		String path = System.getProperty("user.dir") + System.getProperty("file.separator") + "registration_results.txt";
		System.out.println("The output file location is " + path);
		StdoutDisplayInterface results = new Results();
		results.writeToConsole(allocations);
		((FileDisplayInterface) results).writeToFile(allocations, path);
	}
}
