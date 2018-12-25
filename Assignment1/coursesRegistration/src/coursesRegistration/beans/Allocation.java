package coursesRegistration.beans;

/**
 * This class contains the results of the allocation process
 * @author Kedar Poudwal
 *
 */
public class Allocation 
{
	private String studentId;
	private String allocation1;
	private String allocation2;
	private String allocation3;
	
	public String getStudentId() 
	{
		return studentId;
	}
	
	public void setStudentId(String studentId) 
	{
		this.studentId = studentId;
	}
	
	public String getAllocation1() 
	{
		return allocation1;
	}
	
	public void setAllocation1(String allocation1) 
	{
		this.allocation1 = allocation1;
	}
	
	public String getAllocation2() 
	{
		return allocation2;
	}
	
	public void setAllocation2(String allocation2) 
	{
		this.allocation2 = allocation2;
	}
	
	public String getAllocation3() 
	{
		return allocation3;
	}
	
	public void setAllocation3(String allocation3) 
	{
		this.allocation3 = allocation3;
	}

	@Override
	public String toString() 
	{
		StringBuffer strBuffer = new StringBuffer();
			strBuffer.append(studentId + ":");
			
		if(allocation1 != null)
			strBuffer.append(allocation1);
		if(allocation2 != null)
			strBuffer.append(", " + allocation2);
		if(allocation3 != null)
			strBuffer.append(", " + allocation3);
		return strBuffer.toString();
	}
}