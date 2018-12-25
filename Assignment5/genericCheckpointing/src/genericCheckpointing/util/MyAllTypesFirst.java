package genericCheckpointing.util;

/**
 * A class for MyAllTypesFirst.
 * @author Kedar Poudwal
 */
public class MyAllTypesFirst extends SerializableObject
{
	private int myInt;
	private long myLong;
	private String myString;
	private boolean myBool;
	private int myOtherInt;
	private long myOtherLong;
	
	public MyAllTypesFirst()
	{
		myLong = myOtherLong = myInt = myOtherInt = 0;
	}
	
	public MyAllTypesFirst(int myIntIn, long myLongIn, String myStringIn, boolean myBoolIn, int myOtherIntIn, long myOtherLongIn)
	{
		this.myInt = myIntIn;
		this.myLong = myLongIn;
		this.myString = myStringIn;
		this.myBool = myBoolIn;
		this.myOtherInt = myOtherIntIn;
		this.myOtherLong = myOtherLongIn;
	}
	
	public int getMyInt() 
	{
		return myInt;
	}
	
	public void setMyInt(int myInt) 
	{
		this.myInt = myInt;
	}
	
	public long getMyLong() 
	{
		return myLong;
	}
	
	public void setMyLong(long myLong)
	{
		this.myLong = myLong;
	}
	
	public String getMyString() 
	{
		return myString;
	}
	
	public void setMyString(String myString) 
	{
		this.myString = myString;
	}
	
	public boolean isMyBool() 
	{
		return myBool;
	}
	
	public void setMyBool(boolean myBool)
	{
		this.myBool = myBool;
	}
	
	public int getMyOtherInt() 
	{
		return myOtherInt;
	}
	
	public void setMyOtherInt(int myOtherInt) 
	{
		this.myOtherInt = myOtherInt;
	}
	
	public long getMyOtherLong() 
	{
		return myOtherLong;
	}
	
	public void setMyOtherLong(long myOtherLong) 
	{
		this.myOtherLong = myOtherLong;
	}

	@Override
	public String toString()
	{
		return "MyAllTypesFirst [myInt=" + myInt + ", myLong=" + myLong
				+ ", myString=" + myString + ", myBool=" + myBool
				+ ", myOtherInt=" + myOtherInt + ", myOtherLong=" + myOtherLong
				+ "]";
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		
		if(this.getClass() != obj.getClass())
			return false;
		
		MyAllTypesFirst otherObj = (MyAllTypesFirst) obj;
		
		if(this.myInt != otherObj.myInt && this.myInt >= 10)
			return false;
		
		if(this.myLong != otherObj.myLong && this.myLong >= 10)
			return false;
		
		if(!this.myString.equals(otherObj.myString))
			return false;
		
		if(this.myBool != otherObj.myBool)
			return false;
		
		if(this.myOtherInt != otherObj.myOtherInt && this.myOtherInt >= 10)
			return false;
		
		if(this.myOtherLong != otherObj.myOtherLong && this.myOtherLong >= 10)
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() 
	{
		int prime = 541;
		int hash = 1;
		
		hash = prime * hash + Integer.valueOf(myInt).hashCode();
		hash = prime * hash + Long.valueOf(myLong).hashCode();
		hash = prime * hash + ((myString == null) ? 0 : myString.hashCode());
		hash = prime * hash + Boolean.valueOf(myBool).hashCode();
		hash = prime * hash + Integer.valueOf(myOtherInt).hashCode();
		hash = prime * hash + (int) Long.valueOf(myOtherLong).hashCode();
		
		return hash;
	}
}