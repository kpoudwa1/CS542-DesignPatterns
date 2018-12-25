package coursesRegistration.util;

import java.util.List;

import coursesRegistration.beans.Allocation;

public interface StdoutDisplayInterface 
{
	public void writeToConsole(List<Allocation> allocatedList);
}