/**
 * 	@author Alma Campos
 * 	CS3560-01
 * 	Assignment 2
 */


import java.util.ArrayList;
import java.util.List;

public class User extends Subject implements Observer, Admin {

	private List<Subject> following = new ArrayList<Subject>();
	private List<String> newsFeed = new ArrayList<String>();
	private String tweet;
	private long lastUpdateTime = 0; 
	
	public User()
	{
		
	}
	
	public User(String id)
	{
		this.setDisplayName(id);
	}
	
	public void follow(User intendedFollow) {
		following.add(intendedFollow);
    	intendedFollow.attach(this);
	}
	
	public List<Subject> getFollowingList()
	{
		return following;
	}
	
	public Subject getUserFollowingAt(int n) 
	{
		return following.get(n);
	}
	
	public int getNumberOfUsersFollowing()
	{
		return following.size();
	}
	
	public void tweetMessage(String message) {
		tweet = this.getDisplayName() + ": " + message;
		newsFeed.add(tweet);
		AdminControlPanel.getInstance().addTweet(tweet);
		lastUpdateTime = System.currentTimeMillis();
		AdminControlPanel.getInstance().setLastUpdated(this);
		update();
	}
	
	public String getTweet() {
		return tweet;
	}
	
	public List<String> getNewsFeed() {
		return newsFeed;
	}
	
	public String getTweetAt(int i) {
		return newsFeed.get(i);
	}
	
	public void addToNewsFeed(String post) {
		newsFeed.add(post);
	}
	
	public void setLastUpdateTime(long time) {
		lastUpdateTime = time;
	}
	
	public long getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	public int accept(AdminVisitor visitor) {
		visitor.visitUser(this);
		return 1;
		
	}

	@Override
	public void update() {
		for (Observer follower : this.getFollowers())
		{
			((User) follower).addToNewsFeed(this.tweet);
			((User) follower).setLastUpdateTime(this.lastUpdateTime);
		}
	}
	
}
