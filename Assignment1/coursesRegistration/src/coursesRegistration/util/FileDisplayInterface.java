package coursesRegistration.util;

import java.util.List;
import coursesRegistration.beans.Allocation;

public interface FileDisplayInterface 
{
	public void writeToFile(List<Allocation> allocatedList, String filePath);
}