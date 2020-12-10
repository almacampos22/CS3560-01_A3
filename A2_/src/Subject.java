/**
 * 	@author Alma Campos
 * 	CS3560-01
 * 	Assignment 2
 */


import java.util.ArrayList;
import java.util.List;

public abstract class Subject extends SystemEntry {

	private List<Observer> followers = new ArrayList<Observer>();
	
	public void attach(Observer follower) {
		followers.add(follower);
	}
	
	public List<Observer> getFollowers()
	{
		return followers;
	}
	

	
	
}
