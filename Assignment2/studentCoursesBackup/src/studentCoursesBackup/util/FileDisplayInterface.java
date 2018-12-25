package studentCoursesBackup.util;

import java.io.FileNotFoundException;
import java.util.List;
import studentCoursesBackup.myTree.Node;

public interface FileDisplayInterface 
{
	public void writeToFile(List<Node> list, String filePath) throws FileNotFoundException;	
}