package P3;

import java.util.LinkedList;

public class Person {
	private String name;
	private LinkedList<Integer> SocialList = new LinkedList<>();
	public Person(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void LinkedListAdd(int personPos) {
		this.SocialList.add(personPos);
	}
	public boolean hasEdge(int personPos) {
		if (this.SocialList.contains(personPos)) 
			return true;
		return false;
	}
	public int socialNetworkSize() {
		return this.SocialList.size();
	}
	public int getSocialPos(int pos) {
		return this.SocialList.get(pos);
	}
	
}
