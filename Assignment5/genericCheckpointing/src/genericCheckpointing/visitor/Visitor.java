package genericCheckpointing.visitor;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

public interface Visitor
{
	public void visit(MyAllTypesFirst myFirst);
	public void visit(MyAllTypesSecond mySecond);
}