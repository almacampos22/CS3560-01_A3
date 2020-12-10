/**
 * 	@author Alma Campos
 * 	CS3560-01
 * 	Assignment 2
 */

import java.util.ArrayList;
import java.util.List;

public class UserGroup extends SystemEntry {
	
	private List<SystemEntry> entries = new ArrayList<>();
	private int userTotal;
	private int groupTotal;
	
	public UserGroup(String id)
	{
		this.setDisplayName(id);
	}
	
	public UserGroup()
	{
		
	}
	
	public UserGroup(String id, List<SystemEntry> entries)
	{
		this.setDisplayName(id);
		setEntries(entries);
	}
	
	
	public List<SystemEntry> getEntries()
	{
		return entries;
	}
	
	public SystemEntry getEntry(int n)
	{
		return entries.get(n);
	}
	
	public void setEntries(List<SystemEntry> entries)
	{
		this.entries = entries;
	}
	
	public void addEntry(SystemEntry entry) 
	{
		entries.add(entry);
	}
	
	public SystemEntry findEntry(SystemEntry searchEntry) {
		
		for (SystemEntry entry : entries ) {
			if (entry == searchEntry) {
				return entry;
			}
		}
		
		return null;
	}
	
    public int findEntryIndex(SystemEntry searchEntry) {
		
		for (int i = 0; i < entries.size(); i++ ) {
			if (entries.get(i) == searchEntry) {
				return i;
			}
		}
		
		return -1;
	}

	public int getSize()
	{
		return entries.size();
	}
	
	public void set(int index, SystemEntry searchEntry) {
		entries.set(index, searchEntry);
	}
	
	public User findUser(String id)
	{
		User found = new User();
		for (SystemEntry entry : entries ) {
			if (entry instanceof UserGroup)
		    {
			    found = ((UserGroup) entry).findUser(id);
			    if (found != null) {
			    	return found;
			    }
            }
		    if(entry.getDisplayName() == id)
		    {
		    	found = (User)entry;
		    	return found;
		    }
		}
		
		return found; 
	}
	
	private int setUserTotal() {
		for (SystemEntry entry : this.getEntries()) {
			if (entry instanceof UserGroup) {
				userTotal = userTotal + ((UserGroup)entry).setUserTotal();
			}
			else {
				userTotal++;
			}
		}
		return userTotal;
	}
	
	public int getUserTotal() {
		int total = setUserTotal();
		userTotal = 0;
		return total;
	}
	
	public int getGroupTotal() {
		int total = setGroupTotal();
		groupTotal = 0;
		return total;
	}

	private int setGroupTotal() {
		for (SystemEntry entry : this.getEntries()) {
			if (entry instanceof UserGroup) {
				groupTotal++;
				groupTotal = groupTotal + ((UserGroup)entry).setGroupTotal();
			}
		}
		return groupTotal;
	}

}
