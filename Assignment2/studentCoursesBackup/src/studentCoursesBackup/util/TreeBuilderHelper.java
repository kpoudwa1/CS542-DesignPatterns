package studentCoursesBackup.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import studentCoursesBackup.myTree.Node;

/**
 * This class contains static methods for 
 *  assisting TreeBuilder.java
 * @author Kedar Poudwal
 */
public class TreeBuilderHelper
{
	/**
	 * Static function for reading the input file
	 * @param filePath The path to the input file
	 * @param fp The File processor object 
	 * @param tree The Tree Builder object 
	 * @throws CloneNotSupportedException
	 */
	public static void readInputFile(String filePath, FileProcessor fp, TreeBuilder tree) throws CloneNotSupportedException, FileNotFoundException, IOException
	{
		try
		{
			String line = null;
			while((line = fp.readLine(filePath)) != null)
			{
				tree.insert(line);
			}
		}
		catch(CloneNotSupportedException e)
		{
			throw e;
		}
		catch(FileNotFoundException e)
		{
			throw e;
		}
		catch(IOException e)
		{
			throw e;
		}
	}
	
	/**
	 * Static function for reading the delete input file
	 * @param filePath The path to the delete input file
	 * @param fp The File processor object 
	 * @param tree The Tree Builder object 
	 */
	public static void readDeleteFile(String filePath, FileProcessor fp, TreeBuilder tree) throws FileNotFoundException, IOException
	{
		try
		{
			String line = null;
			fp = new FileProcessor();
			while((line = fp.readLine(filePath)) != null)
			{
				tree.delete(line);
			}
		}
		catch(FileNotFoundException e)
		{
			throw e;
		}
		catch(IOException e)
		{
			throw e;
		}
	}
	
	/**
	 * Static function for sorting the nodes as per the B number
	 * @param rootNode The root node of the tree
	 * @return Sorted list of the nodes
	 */
	public static List<Node> printNodes(Node rootNode)
	{
		//Creating the list
		List<Node> sortedList = new ArrayList<Node>();
		recursiveSort(rootNode, sortedList);
		
		//Returning the list
		return sortedList;
	}
	
	/**
	 * Recursive static function used internally for sorting the BST
	 * @param node The current node
	 * @param sortedList Sorted list of the nodes
	 */
	private static void recursiveSort(Node node, List<Node> sortedList)
	{
		if(node != null)
		{
			if(node.getLeft() != null)
				recursiveSort(node.getLeft(), sortedList);
			
			sortedList.add(node);
			
			if(node.getRight() != null)
				recursiveSort(node.getRight(), sortedList);
		}
	}
}