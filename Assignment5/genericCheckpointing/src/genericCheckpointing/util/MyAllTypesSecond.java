package genericCheckpointing.util;

/**
 * A class for MyAllTypesSecond.
 * @author Kedar Poudwal
 */
public class MyAllTypesSecond extends SerializableObject
{
	private double myDoubleT;
	private float myFloatT;
	private short myShortT;
	private char myCharT;
	private double myOtherDoubleT;
	
	public MyAllTypesSecond()
	{
		myDoubleT = myOtherDoubleT = 0;
	}
	
	public MyAllTypesSecond(double myDoubleTIn, float myFloatTIn, short myShortTIn, char myCharTIn, double myOtherDoubleTIn)
	{
		this.myDoubleT = myDoubleTIn;
		this.myFloatT = myFloatTIn;
		this.myShortT = myShortTIn;
		this.myCharT = myCharTIn;
		this.myOtherDoubleT = myOtherDoubleTIn;
	}
	
	public double getMyDoubleT()
	{
		return myDoubleT;
	}
	
	public void setMyDoubleT(double myDoubleT)
	{
		this.myDoubleT = myDoubleT;
	}
	
	public float getMyFloatT() 
	{
		return myFloatT;
	}
	
	public void setMyFloatT(float myFloatT)
	{
		this.myFloatT = myFloatT;
	}
	
	public short getMyShortT() 
	{
		return myShortT;
	}
	
	public void setMyShortT(short myShortT) 
	{
		this.myShortT = myShortT;
	}
	
	public char getMyCharT() 
	{
		return myCharT;
	}
	
	public void setMyCharT(char myCharT) 
	{
		this.myCharT = myCharT;
	}
	
	public double getMyOtherDoubleT() 
	{
		return myOtherDoubleT;
	}
	
	public void setMyOtherDoubleT(double myOtherDoubleT) 
	{
		this.myOtherDoubleT = myOtherDoubleT;
	}

	@Override
	public String toString()
	{
		return "MyAllTypesSecond [myDoubleT=" + myDoubleT + ", myFloatT="
				+ myFloatT + ", myShortT=" + myShortT + ", myCharT=" + myCharT
				+ ", myOtherDoubleT=" + myOtherDoubleT + "]";
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		
		if(this.getClass() != obj.getClass())
			return false;
		
		MyAllTypesSecond otherObj = (MyAllTypesSecond) obj;
		
		if(this.myDoubleT != otherObj.myDoubleT && this.myDoubleT >= 10)
			return false;
		
		if(this.myFloatT != otherObj.myFloatT)
			return false;
		
		if(this.myShortT != otherObj.myShortT)
			return false;
		
		if(this.myCharT != otherObj.myCharT)
			return false;
		
		if(this.myOtherDoubleT != otherObj.myOtherDoubleT && this.myOtherDoubleT >= 10)
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() 
	{
		int prime = 523;
		int hash = 1;
		
		hash = prime * hash + Double.valueOf(myDoubleT).hashCode();
		hash = prime * hash + Float.valueOf(myFloatT).hashCode();
		hash = prime * hash + Short.valueOf(myShortT).hashCode();
		hash = prime * hash + new Character(myCharT).hashCode();
		hash = prime * hash + Double.valueOf(myOtherDoubleT).hashCode();
		
		return hash;
	}
}