package genericCheckpointing.driver;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.FileProcessor.FileLevel;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.SerializableListWrapper;
import genericCheckpointing.visitor.PalindromVisitor;
import genericCheckpointing.visitor.PrimeVisitor;
import genericCheckpointing.visitor.Visitor;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.util.Random;

public class Driver
{
    public static void main(String[] args)
    {
    	if (args.length != 3 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}"))
		{
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
			System.exit(0);
		}
    	
    	try
    	{
    		//Reading the arguments to local variables
        	String mode = args[0];
        	int numOfObjects = Integer.parseInt(args[1]);
        	String checkpointFile = args[2];
    		
    		//Init FileProcessor
        	FileProcessor fp = null;
        	
        	//Init Handler
        	InvocationHandler handler = new StoreRestoreHandler();
        	
        	//Init Proxy
        	ProxyCreator pc = new ProxyCreator();
        	
        	//Create a proxy
        	StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
        								 new Class[] {
        								     StoreI.class, RestoreI.class
        								 }, 
        								 handler
        								 );
        	
        	//Init the lists
        	SerializableListWrapper listOld = new SerializableListWrapper();
    		SerializableListWrapper listNew = new SerializableListWrapper();
        	
    		//Create visitor objects
    		Visitor primeVisitor = new PrimeVisitor();
    		Visitor palindromeVisitor = new PalindromVisitor();
    		
        	//Check the mode for execution
        	if(mode.equalsIgnoreCase("serdeser"))
        	{
        		//Open the file write mode
        		fp = new FileProcessor(checkpointFile, FileLevel.WRITE);

        		//Set the file processor in handler
        		((StoreRestoreHandler) handler).setFp(fp);
        		
        		//Creating the number of objects as specified
        		for(int i = 0; i < numOfObjects; i++)
        		{
        			//For creating object of MyAllTypesFirst
        			MyAllTypesFirst myFirst = null;
        			
        			//Generating a random number
        			int randomInt1 = new Random().nextInt(1000);
        			
        			int myInt;
        			long myLong;
        			String myString;
        			boolean myBool;
        			int myOtherInt;
        			long myOtherLong;
        			
        			//Setting the string
        			myString = "MyAllTypesFirst" + i;

        			//Setting the boolean value
    				if(randomInt1 % 2 == 0)
    					myBool = true;
    				else
    					myBool = false;
        			
    				myLong = myOtherLong = myInt = myOtherInt = randomInt1;
    				
    				//Init the object
        			myFirst = new MyAllTypesFirst(myInt, myLong, myString, myBool, myOtherInt, myOtherLong);
        			
        			//Adding the MyAllTypesFirst object to the list 
        			listOld.add(myFirst);
        			
        			//For creating object of MyAllTypesSecond
        			MyAllTypesSecond  mySecond = null;
        			
        			//Generating a random number
        			String temp = "MyAllTypesSecond";
        			int randomInt2 = new Random().nextInt(temp.length());
        			float randomFloat = new Random().nextFloat();
        			
        			double myDoubleT;
        			float myFloatT;
        			short myShortT;
        			char myCharT;
        			double myOtherDoubleT;
        			
        			myFloatT = randomFloat;
        			myShortT = (short) randomInt2;
        			myCharT = temp.charAt(randomInt2);
        			myDoubleT = myOtherDoubleT = randomFloat + randomInt2;
        			
        			//Init the object
        			mySecond = new MyAllTypesSecond(myDoubleT, myFloatT, myShortT, myCharT, myOtherDoubleT);
        			
        			//Adding the MyAllTypesSecond object to the list 
        			listOld.add(mySecond);
        			
        			((StoreI) cpointRef).writeObj(myFirst, 13,  "XML");
        		    ((StoreI) cpointRef).writeObj(mySecond, 17, "XML");
        		}
        		
        		//Close the file opened in write mode
        		fp.closeFile();
        		
        		//Opening the file for read mode
        		fp = new FileProcessor(checkpointFile, FileLevel.READ);

        		//Set the file processor in handler
        		((StoreRestoreHandler) handler).setFp(fp);
        		
        		//Create a data structure to store the returned ojects
        		for(int i = 0; i < 2 * numOfObjects; i++)
        			listNew.add(((RestoreI) cpointRef).readObj("XML"));
        		
        		//Closing the file
        		fp.closeFile();
        		
        		//Comparison of objects
        		int diffObjects = 0;
        		for(int i = 0; i < 2 * numOfObjects; i++)
        		{
        			if(!listOld.get(i).equals(listNew.get(i)))
        				diffObjects++;
        		}
        		System.out.println(diffObjects + " mismatched objects");
        		
        		//Accept the PrimeVisitor
        		listOld.accept(primeVisitor);
        		System.out.println("Total number of unique primes: " + ((PrimeVisitor) primeVisitor).getUniquePrimeNos());
        		
        		//Accept the PalindromVisitor
        		listOld.accept(palindromeVisitor);
        		
        	}
        	else if(mode.equalsIgnoreCase("deser"))
        	{
        		//Opening the file for read mode
        		fp = new FileProcessor(checkpointFile, FileLevel.READ);
        		
        		//Set the file processor in handler
        		((StoreRestoreHandler) handler).setFp(fp);
        		
        		//Create a data structure to store the returned ojects
        		for(int i = 0; i < numOfObjects; i++)
        			listNew.add(((RestoreI) cpointRef).readObj("XML"));
        		
        		//Closing the file
        		fp.closeFile();
        		
        		//Printing of objects
        		for(int i = 0; i < numOfObjects; i++)
        			System.out.println(listNew.get(i));
        		
        		//Accept the PrimeVisitor
        		listNew.accept(primeVisitor);
        		System.out.println("Total number of unique primes: " + ((PrimeVisitor) primeVisitor).getUniquePrimeNos());
        		
        		//Accept the PalindromVisitor
        		listNew.accept(palindromeVisitor);
        	}
        	else
        	{
        		System.out.println("Invalid mode");
        	}
    	}
    	catch(FileNotFoundException e)
    	{
    		e.printStackTrace();
    	}
    	catch(NumberFormatException e)
    	{
    		e.printStackTrace();
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
}
