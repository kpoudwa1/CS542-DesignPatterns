package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.myTree.UpdateLevel;

/*****************************************************************
 * The code for the Binary Search Tree and its operations were
 *  referred from Professor Steven Moore's notes (CS580U)
 *  and from code written for Program 6 for the course.
 *****************************************************************/
/**
 * A class for creating the tree of Nodes.
 * @author Kedar Poudwal
 */
public class TreeBuilder
{
	private Node nodeOrig;
	private Node backupNode1;
	private Node backupNode2;
	
	public TreeBuilder()
	{
		nodeOrig = null;
		backupNode1 = null;
		backupNode2 = null;
	}
	
	/**
	 * This function creates a Binary Search Tree(BST).
	 *  It creates a node and if the BST is empty it sets it
	 *  as the root node. If the BST is not empty, it checks
	 *  whether the node already exists or not. If the node
	 *  does not already exists, it creates a new node and
	 *  inserts it at the appropriate place. If the node
	 *  already exists, it updates the course for the given
	 *  B Number.
	 * @param line The line read from the file
	 * @throws CloneNotSupportedException
	 */
	public void insert(String line) throws CloneNotSupportedException
	{
		try
		{
			//Get the B number and course name
			int bNumber = getBNumber(line);
			String course = getCourse(line);
			
			//If the tree is empty, create the root node
			if(nodeOrig == null)
			{
				//Create the original node
				nodeOrig = new Node(bNumber);
				nodeOrig.addCourse(course);
				
				//Clone the original node
				backupNode1 = (Node) nodeOrig.clone();
				backupNode2 = (Node) nodeOrig.clone();

				//Register the observers
				nodeOrig.register(backupNode1);
				nodeOrig.register(backupNode2);
			}//Recursively iterate the tree for inserting the node
			else
			{
				//Check if the node already exists
				Node nodeExists = searchNode(nodeOrig, bNumber);
				
				/*If the node already exists, update the courses
				 in the original node and notify all the observers*/
				if(nodeExists != null)
				{
					nodeExists.addCourse(course);
					nodeExists.notifyAllObservers(course, UpdateLevel.INSERT);
				}//Create a new node
				else
				{
					//Create the original node
					Node org = new Node(bNumber);
					org.addCourse(course);
					
					//Clone the original node
					Node bkp1 = (Node) org.clone();
					Node bkp2 = (Node) org.clone();
					
					//Register the observers
					org.register(bkp1);
					org.register(bkp2);
					
					//Insert the node in their respective trees
					insertNode(nodeOrig, org);
					insertNode(backupNode1, bkp1);
					insertNode(backupNode2, bkp2);
				}
			}
		}
		catch(CloneNotSupportedException e)
		{
			throw e;
		}
	}
	
	/**
	 * Recursive function for inserting the node in the tree.
	 * @param root The root of the tree
	 * @param newNode The node to be inserted
	 */
	private void insertNode(Node root, Node newNode)
	{
		//Check if the same node is being inserted twice
		if(newNode.getbNumber() == root.getbNumber())
		{
			//Already handled
		}/*Check if the node to be inserted has a value less
		than the current node*/
		else if(newNode.getbNumber() < root.getbNumber())
		{
			/*If the left child of the current node is null then
			insert a new node as a leaf node*/
			if(root.getLeft() == null)
			{
				root.setLeft(newNode);
			}/*If the current node has children, recursively iterate
			the left sub tree*/
			else
			{
				insertNode(root.getLeft(), newNode);
			}
		}/*Check if the node to be inserted has a value greater
		than the current node*/
		else if(newNode.getbNumber() > root.getbNumber())
		{
			/*If the right child of the current node is null
			then insert a new node as a leaf node*/
			if(root.getRight() == null)
			{
				root.setRight(newNode);
			}/*If the current node has children, recursively
			iterate the left sub tree*/
			else
			{
				insertNode(root.getRight(), newNode);
			}
		}
	}
	
	/**
	 * This function takes the line read as input and checks if
	 *  the B number passed exists or not and if it exists,
	 *  it deletes the course passed else does nothing. 
	 * @param line The line read from the file
	 */
	public void delete(String line)
	{
		//Get the B number and course name
		int bNumber = getBNumber(line);
		String course = getCourse(line);
		
		//Check if the tree is not empty
		if(nodeOrig != null)
		{
			//Check if the node already exists
			Node nodeExists = searchNode(nodeOrig, bNumber);
			
			if(nodeExists != null)
			{
				//Delete the course in the original tree
				nodeExists.deleteCourse(course);
				
				//Notify all the observers
				nodeExists.notifyAllObservers(course, UpdateLevel.DELETE);
			}
		}
	}
	
	/**
	 * Internal function used for getting the B number from
	 *  the line read.
	 * @param line The line read from the file
	 * @return The B number from the line
	 */
	private int getBNumber(String line)
	{
		return line != null ?  Integer.parseInt(line.split(":")[0].trim()) : -999;
	}
	
	/**
	 * Internal function used for getting the course name
	 *  from the line read.
	 * @param line The line read from the file
	 * @return The name of the course
	 */
	private String getCourse(String line)
	{
		return line != null ?  line.split(":")[1].trim() : "";
	}
	
	/**
	 * Function for checking if the node already exists or not.
	 * @param root The start node from where search is supposed to
	 *  begin
	 * @param bNumber The B number of the student
	 * @return Returns the reference of the node
	 */
	private Node searchNode(Node root, int bNumber)
	{
		Node node = null;
		//Check if the current node and the B number passed is same
		if(bNumber == root.getbNumber())
		{
			node = root;
		}/*Check if the B number value to be searched has a value less
		than the current node*/
		else if(bNumber < root.getbNumber() && root.getLeft() != null)
		{
			node = searchNode(root.getLeft(), bNumber);
		}/*Check if the B number value to be searched has a value
		 greater than the current node*/
		else if(bNumber > root.getbNumber() && root.getRight() != null)
		{
			node = searchNode(root.getRight(), bNumber);
		}
		
		//Return the reference of the node
		return node;
	}
	
	public Node getNodeOrig() 
	{
		return nodeOrig;
	}
	
	public Node getBackupNode1() 
	{
		return backupNode1;
	}

	public Node getBackupNode2() 
	{
		return backupNode2;
	}
}