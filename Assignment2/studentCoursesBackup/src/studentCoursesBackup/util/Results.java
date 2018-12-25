package studentCoursesBackup.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import studentCoursesBackup.myTree.Node;

/**
 * This class implements the FileDisplayInterface and
 *  StdoutDisplayInterface and writes the BST to the
 *  file and the console 
 * @author Kedar Poudwal
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface
{

	@Override
	public void writeToConsole(List<Node> list) 
	{
		for(int i = 0; i < list.size(); i++)
			System.out.println(list.get(i));
		//recursiveWriteToConsole(node);
	}

	@Override
	public void writeToFile(List<Node> list, String filePath) throws FileNotFoundException
	{
		PrintWriter pwr = null;
		try
		{
			pwr = new PrintWriter(filePath);
			//recursiveWriteToFile(node, pwr);
			
			for(int i = 0; i < list.size(); i++)
				pwr.println(list.get(i));
		}
		catch(FileNotFoundException e)
		{
			throw e;
		}
		finally
		{
			if(pwr != null)
				pwr.close();
		}
	}
}