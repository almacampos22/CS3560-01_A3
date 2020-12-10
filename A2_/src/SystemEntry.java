/**
 * 	@author Alma Campos
 * 	CS3560-01
 * 	Assignment 2
 */


public class SystemEntry {
	
	private String displayName;
	private long creationTime;
	
	public SystemEntry()
	{
		creationTime = System.currentTimeMillis();
	}

	public SystemEntry(String id)
	{
		displayName = id;
		creationTime = System.currentTimeMillis();
	}
	
	public void setDisplayName(String id)
	{
	    displayName = id;	
	}
	public String getDisplayName() 
	{
		return displayName;
	}
	
	public String toString()
	{
		return getDisplayName();
	}
	
	public long getCreationTime() {
		return creationTime;
	}
	
}
