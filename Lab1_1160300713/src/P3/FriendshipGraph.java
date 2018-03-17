package P3;

import java.io.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import java.util.ArrayList;
import java.util.HashMap;

public class FriendshipGraph {
	AdjGraph adjGraph = new AdjGraph();

	/* 增加一个人物节点 */

	public boolean addVertex(Person Person) {
		if (getPosition(Person) != -1) {
			System.out.printf("The name \'%s\' already exists\n", Person.getName());
			return false;
		}

		this.adjGraph.VertexCount++;
		this.adjGraph.VertexList.add(Person);
		return true;
	}

	/* 增加一条人物关系边 */
	public boolean addEdge(Person Person1, Person Person2) {
		int Person1Pos = getPosition(Person1), Person2Pos = getPosition(Person2);
		if (Person1 == Person2) {
			System.out.printf("You can't addEdge with yourself\n");
			return false;
		}
		if ((Person1Pos == -1) || (Person2Pos == -1)) {
			System.out.printf("The Person doesn't exist\n");
			return false;
		}
		if (Person1.hasEdge(Person2Pos) && Person2.hasEdge(Person1Pos)) {
			System.out.printf("You have add this edge\n");
			return false;
		}

		this.adjGraph.EdgeCount++;
		Person1.LinkedListAdd(Person2Pos);
		return true;
	}

	/* 获得两个人之间的距离 */
	public int getDistance(Person Person1, Person Person2) {
		return BFS(adjGraph, Person1, Person2);
	}

	private int BFS(AdjGraph AG, Person Person1, Person Person2) {
		Map<Integer, Integer> routeMap = new HashMap<>();
		boolean visited[] = new boolean[AG.VertexCount];
		Queue<Integer> queue = new LinkedList<Integer>();
		int start = getPosition(Person1);
		int end = getPosition(Person2);
		if (start == end)
			return 0;
		else if ((start == -1) || (end == -1)) {
			System.out.printf("The Person doesn't exist\n");
			return -1;
		}

		for(int i = 0; i < visited.length; i++) 
			visited[i] = false;
		
		visited[start] = true;
		queue.add(start);
		while (!queue.isEmpty()) {
			int curValue = queue.remove();
			for (int next = 0; next < AG.VertexList.get(curValue).socialNetworkSize(); next++) {
				int curvisit = AG.VertexList.get(curValue).getSocialPos(next);
				if (!visited[curvisit]) {
					visited[curvisit] = true;
					routeMap.put(curvisit, curValue);
					queue.add(curvisit);
				}
			}
		}
		for (int i = 0, distence = 1; i < routeMap.keySet().size(); i++, distence++) {
			if (!routeMap.containsKey(end))
				return -1;

			if (routeMap.get(end) == start)
				return distence;

			end = routeMap.get(end);
		}
		return -1;
	}

	private int getPosition(Person Person) {
		for (int i = 0; i < this.adjGraph.VertexList.size(); i++)
			if (this.adjGraph.VertexList.get(i) == Person)
				return i;
		return -1;
	}

	private class AdjGraph {
		int VertexCount = 0, EdgeCount = 0;
		ArrayList<Person> VertexList = new ArrayList<>();
	}

	public static void main(String[] args) throws IOException {
		FriendshipGraph FG = new FriendshipGraph();
		Person A = new Person("A");
		Person B = new Person("B");
		Person C = new Person("C");
		Person D = new Person("D");
		Person E = new Person("E");
		Person F = new Person("F");
		FG.addVertex(A);
		FG.addVertex(B);
		FG.addVertex(C);
		FG.addVertex(D);
		FG.addVertex(E);
		FG.addVertex(F);
		FG.addEdge(F, B);
		FG.addEdge(B, F);
		FG.addEdge(A, F);
		FG.addEdge(F, A);
		FG.addEdge(C, F);
		FG.addEdge(F, C);
		FG.addEdge(D, F);
		FG.addEdge(F, D);
		FG.addEdge(B, A);
		FG.addEdge(A, B);
		FG.addEdge(B, C);
		FG.addEdge(C, B);
		FG.addEdge(D, B);
		FG.addEdge(B, D);
		FG.addEdge(A, C);
		FG.addEdge(C, A);
		FG.addEdge(A, D);
		FG.addEdge(D, A);
		FG.addEdge(C, D);
		FG.addEdge(D, C);
		FG.addEdge(B, E);
		FG.addEdge(E, B);

		FG.getDistance(A, E);
	}
}
