/**
 * 	@author Alma Campos
 * 	CS3560-01
 * 	Assignment 2
 */


import java.util.ArrayList;

public class AdminControlPanel implements Admin {

	private UserGroup root = new UserGroup("Root");
	private ArrayList<String> systemTweets = new ArrayList<>();
	private int userTotal = 0;
	private int groupTotal = 0;
	private User lastUpdated;
	
	private static AdminControlPanel pointer;
	
	public static AdminControlPanel getInstance()
	{
		if (pointer == null)
		{
			pointer = new AdminControlPanel();
		}
		
		return pointer;
	}
	
	private AdminControlPanel()
	{
		
	}
	
	public UserGroup getRoot()
	{
		return root;
	}
	
	public void addSystemEntry(SystemEntry entry)
	{
		root.addEntry(entry);
	}
	
	public User findUser(String id)
	{
		User found = new User();
		for (SystemEntry entry : root.getEntries() ) {
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
	
	public void addTweet(String tweet) {
		systemTweets.add(tweet);
	}
	
	public ArrayList<String> getSystemTweets() {
		return systemTweets;
	}
	
	public int getSystemTweetsSize() {
		return systemTweets.size();
	}

	@Override
	public int accept(AdminVisitor visitor) {
		return visitor.visitAdminControlPanel(this);
		
	}

	public int getGroupTotal() {
		int total = setGroupTotal();
		groupTotal = 0;
		return total;
	}

	private int setGroupTotal() {
		for (SystemEntry entry : getRoot().getEntries()) {
			if (entry instanceof UserGroup) {
				groupTotal++;
				groupTotal = groupTotal + ((UserGroup)entry).getGroupTotal();
			}
		}
		return groupTotal;
	}

	private int setUserTotal() {
		for (SystemEntry entry : getRoot().getEntries()) {
			if (entry instanceof UserGroup) {
				userTotal = userTotal + ((UserGroup)entry).getUserTotal();
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

	public User getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(User lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
}
