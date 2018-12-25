package studentCoursesBackup.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.util.FileDisplayInterface;
import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.util.Results;
import studentCoursesBackup.util.StdoutDisplayInterface;
import studentCoursesBackup.util.TreeBuilder;
import studentCoursesBackup.util.TreeBuilderHelper;

/**
 * @author Kedar Poudwal
 */
public class Driver 
{
	public static void main(String[] args)
	{

		/*
		 * As the build.xml specifies the arguments as argX,
		 *  in case the argument value is not given java takes
		 *  the default value specified in build.xml.
		 *   To avoid that, below condition is used
		 */
		if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
				|| args[3].equals("${arg3}") || args[4].equals("${arg4}"))
		{

			System.err.println("Error: Incorrect number of arguments. Program accepts 5 argumnets.");
			System.exit(0);
		}
		
		try
		{
			//Create the File Processor object
			FileProcessor fp = new FileProcessor();
			
			//Create the Tree Builder object
			TreeBuilder tree = new TreeBuilder();
			
			/*Reading the input file line by line
			and creating a Binary Search Tree (BST)*/
			TreeBuilderHelper.readInputFile(args[0], fp, tree);
			
			/*Reading the delete input file line by line
			and deleting the courses from the BST*/
			TreeBuilderHelper.readDeleteFile(args[1], fp, tree);
			
			//Getting the root nodes of all the trees
			Node root = tree.getNodeOrig();
			Node bkp1 = tree.getBackupNode1();
			Node bkp2 = tree.getBackupNode2();
			
			//Create the sorted lists
			List<Node> originalList = TreeBuilderHelper.printNodes(root);
			List<Node> bkp1List = TreeBuilderHelper.printNodes(bkp1);
			List<Node> bkp2List = TreeBuilderHelper.printNodes(bkp2);
			
			//Print the results
			StdoutDisplayInterface results = new Results();
			
			//Print to console
			/*System.out.println("The original tree");
			System.out.println("=====================");
			results.writeToConsole(originalList);
			System.out.println("The backup 1 tree");
			System.out.println("=====================");
			results.writeToConsole(bkp1List);
			System.out.println("The backup 2 tree");
			System.out.println("=====================");
			results.writeToConsole(bkp2List);*/
			
			//Print to file
			((FileDisplayInterface) results).writeToFile(originalList, args[2]);
			((FileDisplayInterface) results).writeToFile(bkp1List, args[3]);
			((FileDisplayInterface) results).writeToFile(bkp2List, args[4]);
			
			System.out.println("Processing completed !");
		}
		catch(CloneNotSupportedException e)
		{
			System.out.println("Error in Driver: ");
			e.printStackTrace();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error in Driver: ");
			e.printStackTrace();
		}
		catch(IOException e)
		{
			System.out.println("Error in Driver: ");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("Error in Driver: ");
			e.printStackTrace();
		}
	}
}