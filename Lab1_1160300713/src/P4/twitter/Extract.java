/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

	/**
	 * Get the time period spanned by tweets.
	 * 
	 * @param tweets
	 *            list of tweets with distinct ids, not modified by this method.
	 * @return a minimum-length time interval that contains the timestamp of every
	 *         tweet in the list.
	 */
	public static Timespan getTimespan(List<Tweet> tweets) {
		int i = 0;
		Instant max = tweets.get(0).getTimestamp(), min = tweets.get(0).getTimestamp();

		for (; i < tweets.size(); i++) {
			if (max.isBefore(tweets.get(i).getTimestamp()))
				max = tweets.get(i).getTimestamp();
			if (min.isAfter(tweets.get(i).getTimestamp()))
				min = tweets.get(i).getTimestamp();
		}
		Timespan ts = new Timespan(min, max);
		return ts;
	}

	/**
	 * Get usernames mentioned in a list of tweets.
	 * 
	 * @param tweets
	 *            list of tweets with distinct ids, not modified by this method.
	 * @return the set of usernames who are mentioned in the text of the tweets. A
	 *         username-mention is "@" followed by a Twitter username (as defined by
	 *         Tweet.getAuthor()'s spec). The username-mention cannot be immediately
	 *         preceded or followed by any character valid in a Twitter username.
	 *         For this reason, an email address like bitdiddle@mit.edu does NOT
	 *         contain a mention of the username mit. Twitter usernames are
	 *         case-insensitive, and the returned set may include a username at most
	 *         once.
	 */
	public static Set<String> getMentionedUsers(List<Tweet> tweets) {
		int size = 140, i = 0, j = 0;
		String[] strRaw = new String[size];
		String name = null;
		Set<String> tweetsSet = new HashSet<>();

		for (i = 0; i < tweets.size(); i++) {
			strRaw = tweets.get(i).getText().split("[^0-9a-zA-Z-_@]");
			for (j = 0; j < strRaw.length; j++) {
				if (strRaw[j].startsWith("@") && strRaw[j].length() > 1) {
					name = strRaw[j].substring(1);
					if (notExist(name, tweetsSet)) {
						tweetsSet.add(name);
					}
				}
			}
		}
		return tweetsSet;
	}

//	public static boolean isName(String name) {
//
//		for (int k = 0; k < name.length(); k++) {
//			if (!((65 <= (int) name.charAt(k) && (int) name.charAt(k) <= 90)
//					|| (97 <= (int) name.charAt(k) && (int) name.charAt(k) <= 122)
//					|| (48 <= (int) name.charAt(k) && (int) name.charAt(k) <= 57) || (name.charAt(k) == '-')
//					|| (name.charAt(k) == '_'))) {
//				return false;
//			}
//		}
//		return true;
//
//	}

	public static boolean notExist(String name, Set<String> nameSet) {
		for (String curName : nameSet) {
			if (name.equalsIgnoreCase(curName))
				return false;
		}
		return true;
	}

}
