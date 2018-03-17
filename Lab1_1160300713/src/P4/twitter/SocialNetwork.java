/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even
 * exist as a key in the map; this is true even if A is followed by other people
 * in the network. Twitter usernames are not case sensitive, so "ernie" is the
 * same as "ERNie". A username should appear at most once as a key in the map or
 * in any given map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

	/**
	 * Guess who might follow whom, from evidence found in tweets.
	 * 
	 * @param tweets
	 *            a list of tweets providing the evidence, not modified by this
	 *            method.
	 * @return a social network (as defined above) in which Ernie follows Bert if
	 *         and only if there is evidence for it in the given list of tweets. One
	 *         kind of evidence that Ernie follows Bert is if Ernie
	 * @-mentions Bert in a tweet. This must be implemented. Other kinds of evidence
	 *            may be used at the implementor's discretion. All the Twitter
	 *            usernames in the returned social network must be either authors
	 *            or @-mentions in the list of tweets.
	 */
	public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
		Map<String, Set<String>> followMap = new HashMap<>();
		String[] text = null;
		String name = null;
		for (Tweet oneuser : tweets) {
			text = oneuser.getText().split("[^0-9a-zA-Z-_@]");
			for (String word : text) {
				if (word.startsWith("@") && word.length() > 1) {
					name = word.substring(1);
					if (followMap.containsKey(oneuser.getAuthor())) {
						if (Extract.notExist(name, followMap.get(oneuser.getAuthor()))) {
							followMap.get(oneuser.getAuthor()).add(name);
						}
					} else {
						Set<String> followedSet = new HashSet<>();
						followedSet.add(name);
						followMap.put(oneuser.getAuthor(), followedSet);
					}
				}
			}
			if (!followMap.containsKey(oneuser.getAuthor())) {
				Set<String> followedSet = new HashSet<>();
				followMap.put(oneuser.getAuthor(), followedSet);
			}
		}
		return followMap;

	}

	/**
	 * Find the people in a social network who have the greatest influence, in the
	 * sense that they have the most followers.
	 * 
	 * @param followsGraph
	 *            a social network (as defined above)
	 * @return a list of all distinct Twitter usernames in followsGraph, in
	 *         descending order of follower count.
	 */
	public static List<String> influencers(Map<String, Set<String>> followsGraph) {
		List<String> influenceList = new ArrayList<>();
		Map<String, Integer> influenceMap = new HashMap<>();
		// int count = 0;
		int max = 0;
		String theName = null;
		for (String follower : followsGraph.keySet())
			for (String name : followsGraph.get(follower)) {
				if (influenceMap.containsKey(name)) {
					Integer i = influenceMap.get(name);
					i++;
					influenceMap.put(name, i);
				} else {
					influenceMap.put(name, 1);
				}
			}

		while (!influenceMap.keySet().isEmpty()) {
			for (String name : influenceMap.keySet()) {
				if (influenceMap.get(name) > max) {
					max = influenceMap.get(name);
					theName = name;
				}
			}
			max = 0;
			// if (count < 10) {
			// System.out.println(theName+'\t'+influenceMap.get(theName));
			// count++;
			// }
			influenceList.add(theName);
			influenceMap.remove(theName);
		}

		return influenceList;
	}
	public static Map<String, Set<String>> getSmarter(List<Tweet> tweets) {
		Map<String, Set<String>> followMap = guessFollowsGraph(tweets);
		Map<String, Set<String>> RTMap = new HashMap<>();
		String text = "";
		String name = "";
		Set<String> followers = new HashSet<>();
		for(Tweet oneuser : tweets) {
			text = oneuser.getText();
			if (text.startsWith("RT @")) {
				name = text.substring(4,text.indexOf(':'));
				if (RTMap.containsKey(oneuser.getAuthor())) {
					RTMap.get(oneuser.getAuthor()).add(name);
				}else {
					Set<String> RTed = new HashSet<>();
					RTed.add(name);
					RTMap.put(oneuser.getAuthor(), RTed);
				}
			}
		}
		for(String username : followMap.keySet()) {
			followers = followMap.get(username);
			for(String ifRT : followers) {
				if(RTMap.containsKey(ifRT)) {
					followMap.get(username).addAll(RTMap.get(ifRT));
				}
			}
		}

		return followMap;

	}

}
