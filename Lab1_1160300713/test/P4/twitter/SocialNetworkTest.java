/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    private static final Instant d1 = Instant.parse("2016-02-17T11:41:07Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T09:00:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T13:00:00Z");
    private static final Instant d5 = Instant.parse("2016-02-17T12:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "politico", "Is tech dividing America? POLITICO\u2019s @nancyscola interviews MIT researcher David Autor https://t.co/5bOrOCQ0EF https://t.co/Nyx6ZDoLpl", d1);
    private static final Tweet tweet2 = new Tweet(2, "Kiwigirl58", "RT @politico: Is tech dividing America? POLITICO\u2019s @nancyscola interviews MIT researcher David Autor https://t.co/5bOrOCQ0EF https://t.co/N\u2026", d2);
    private static final Tweet tweet3 = new Tweet(3, "itskac", "Need to interview a #musician, a #freelancer, a #smallbiz owner (or someone who wants to start one) &amp; a #tech\u2026 https://t.co/K6Yzmslj0Y", d3);
    private static final Tweet tweet4 = new Tweet(4, "bbitdiddle", "RT @kalynkahler: \"That was shiny, sparkling redemption.\" - \nMirai was eating In and Out with and watching the last Ol\u2026", d4);
    private static final Tweet tweet5 = new Tweet(5, "goldwaterkid65", "RT @kalynkahler: Team USA @nancyscola skater  is the first American woman to land a triple axel at the Winter Olympics. #PyeongC\u2026", d5);
    
    
    private static final Tweet tweet6 = new Tweet(6, "testSmarter1", "@testSmarter2 hh.", d1);
    private static final Tweet tweet7 = new Tweet(7, "testSmarter2", "RT @testSmarter3: hh.", d2);
    private static final Tweet tweet8 = new Tweet(8, "testSmarter3", "hh.", d3);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraph() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2,tweet3,tweet4,tweet5));
        
        Map<String, Set<String>> standard = new HashMap<String, Set<String>>();
        Set<String> set1 = new HashSet<String>();
        Set<String> set2 = new HashSet<String>();
        Set<String> set3 = new HashSet<String>();
        Set<String> set4 = new HashSet<String>();
        Set<String> set5 = new HashSet<String>();
        set1.add("nancyscola");
        set2.add("politico");
        set2.add("nancyscola");
        set4.add("kalynkahler");
        set5.add("kalynkahler");
        set5.add("nancyscola");
        standard.put("politico",set1);
        standard.put("Kiwigirl58", set2);
        standard.put("itskac", set3);
        standard.put("bbitdiddle", set4);
        standard.put("goldwaterkid65", set5);
        assertEquals(followsGraph, standard);
    }
    
    
    
    
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2,tweet3,tweet4,tweet5));
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertEquals(Arrays.asList("nancyscola","kalynkahler","politico"), influencers);
    }

    @Test
    public void testGetSmarter() {
        Map<String, Set<String>> followsGraph = SocialNetwork.getSmarter(Arrays.asList(tweet6,tweet7,tweet8));
        
        Map<String, Set<String>> standard = new HashMap<String, Set<String>>();
        Set<String> set1 = new HashSet<String>();
        Set<String> set2 = new HashSet<String>();
        Set<String> set3 = new HashSet<String>();

        set1.add("testSmarter2");
        set1.add("testSmarter3");
        set2.add("testSmarter3");
        standard.put("testSmarter1",set1);
        standard.put("testSmarter2", set2);
        standard.put("testSmarter3", set3);

        assertEquals(followsGraph, standard);
    }
    
    
    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}