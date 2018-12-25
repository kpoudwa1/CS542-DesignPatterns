package studentCoursesBackup.myTree;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a single node of the Binary Search Tree
 *  (BST).
 * @author Kedar Poudwal
 */
public class Node implements SubjectI, ObserverI, Cloneable
{
	private int bNumber;
	private List<String> courses;
	private Node left, right;
	private List<Node> backups;
	
	//Private constructor used for cloning
	private Node()
	{
		super();
		left = right = null;
	}
	
	//Public constructor used by Tree.java for creating a node
	public Node(int bNumber)
	{
		super();
		this.bNumber = bNumber;
		courses = new ArrayList<String>();
		backups = new ArrayList<Node>();
		left = right = null;
	}

	public int getbNumber()
	{
		return bNumber;
	}
	
	public void setbNumber(int bNumber) 
	{
		this.bNumber = bNumber;
	}
	
	public Node getLeft() 
	{
		return left;
	}
	
	public void setLeft(Node left) 
	{
		this.left = left;
	}
	
	public Node getRight() 
	{
		return right;
	}
	
	public void setRight(Node right) 
	{
		this.right = right;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException 
	{
		Node node = null;
		try
		{
			super.clone();
			node = new Node();
			node.setbNumber(this.bNumber);
			node.courses = new ArrayList<String>(this.courses);
		}
		catch(CloneNotSupportedException e)
		{
			throw e;
		}
		
		return node;
	}
	
	/**
	 * This function is for adding the course to the list of the
	 *  courses for the student
	 * @param course The name of the course to be added to the list
	 */
	public void addCourse(String course)
	{
		//Check if the course is already added or not
		if(!courses.contains(course))
			courses.add(course);
	}
	
	/**
	 * This function is for deleting the course from the list of the
	 *  courses for the student.
	 * @param course The name of the course to be deleted to the list
	 */
	public void deleteCourse(String course)
	{
		courses.remove(course);
	}
	
	@Override
	public void register(Node node) 
	{
		backups.add(node);
	}
	
	@Override
	public void unregister(Node node) 
	{
		backups.remove(node);
	}
	
	@Override
	public void notifyAllObservers(String course, UpdateLevel level)
	{
		//Iterate through the observers and call the update() method
		for(int i = 0; i < backups.size(); i++)
		{
			backups.get(i).update(course, level);
		}
	}

	@Override
	public void update(String course, UpdateLevel level) 
	{
		/*Perform the required operation on the
		 basis of the update level*/
		if(level.equals(UpdateLevel.INSERT))
			addCourse(course);
		else if(level.equals(UpdateLevel.DELETE))
			deleteCourse(course);
	}

	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		
		//Append the B number
		buffer.append(String.format("%03d", bNumber) + ":");
		
		//Append the courses
		for(int i = 0; i < courses.size(); i++)
			buffer.append(courses.get(i) + ",");
		
		if(buffer.charAt(buffer.length() - 1) == ',')
			return buffer.substring(0, buffer.length() - 1);
		else
			return buffer.toString();
	}
}