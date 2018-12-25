package genericCheckpointing.server;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

public interface StoreI extends StoreRestoreI
{
	public void writeObj(MyAllTypesFirst aRecord, int authID, String wireFormat);
	public void writeObj(MyAllTypesSecond bRecord, int authID, String wireFormat);
}