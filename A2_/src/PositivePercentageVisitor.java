/**
 * 	@author Alma Campos
 * 	CS3560-01
 * 	Assignment 2
 */


import java.util.ArrayList;
import java.util.Arrays;

public class PositivePercentageVisitor implements AdminVisitor {
	
	@Override
	public int visitAdminControlPanel(AdminControlPanel instance) {
		int numOfTweets = 0;
		int positiveTweets = 0;
		boolean containsPositiveWord = false;
		ArrayList<String> positiveWords = new ArrayList<>(Arrays.asList("good", "nice", "beautiful", "delightful", "happy",
															"thankful", "delighted", "excited"));
		
		for (String tweet : instance.getSystemTweets()) {
			numOfTweets++;
			int i = 0;
			while((i < positiveWords.size()) && (containsPositiveWord == false)) {
				if (tweet.contains(positiveWords.get(i))) {
					containsPositiveWord = true;
					positiveTweets++;
				}
				i++;
			}
			containsPositiveWord = false;
		}
		
		Double numOfPositiveTweets = Double.valueOf(positiveTweets);
		Double totalTweets = Double.valueOf(numOfTweets);
		double percentage = (numOfPositiveTweets/totalTweets) * 100;
		int positivePercent = (int) Math.round(percentage);
		return positivePercent;
	}

	@Override
	public void visitUser(User user) {
		// does nothing
		
	}

	@Override
	public void visitGroup(UserGroup group) {
		// does nothing
		
	}
}
