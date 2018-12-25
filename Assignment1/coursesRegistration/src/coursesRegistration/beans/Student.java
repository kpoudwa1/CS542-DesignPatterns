package coursesRegistration.beans;


/**
 * This class is an abstract representation of the students
 * @author Kedar Poudwal
 *
 */
public class Student 
{
	private String studentId;
	private String pref1;
	private String pref2;
	private String pref3;
	private String pref4;
	private String pref5;
	private String pref6;
	private StudentLevel studentLevel;
	
	public String getStudentId() 
	{
		return studentId;
	}
	
	public void setStudentId(String studentId) 
	{
		this.studentId = studentId;
	}
	
	public String getPref1() 
	{
		return pref1;
	}
	
	public void setPref1(String pref1) 
	{
		this.pref1 = pref1;
	}
	
	public String getPref2() 
	{
		return pref2;
	}
	
	public void setPref2(String pref2) 
	{
		this.pref2 = pref2;
	}
	
	public String getPref3() 
	{
		return pref3;
	}
	
	public void setPref3(String pref3) 
	{
		this.pref3 = pref3;
	}
	
	public String getPref4() 
	{
		return pref4;
	}
	
	public void setPref4(String pref4) 
	{
		this.pref4 = pref4;
	}
	
	public String getPref5() 
	{
		return pref5;
	}
	
	public void setPref5(String pref5) 
	{
		this.pref5 = pref5;
	}
	
	public String getPref6() 
	{
		return pref6;
	}
	
	public void setPref6(String pref6) 
	{
		this.pref6 = pref6;
	}
	
	public StudentLevel getStudentLevel() 
	{
		return studentLevel;
	}
	
	public void setStudentLevel(StudentLevel studentLevel) 
	{
		this.studentLevel = studentLevel;
	}

	@Override
	public String toString() 
	{
		return "Student [studentId=" + studentId + ", pref1=" + pref1
				+ ", pref2=" + pref2 + ", pref3=" + pref3 + ", pref4=" + pref4
				+ ", pref5=" + pref5 + ", pref6=" + pref6 + ", studentLevel="
				+ studentLevel + "]";
	}
}