package P3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FriendshipGraphTest {

	
	FriendshipGraph FG = new FriendshipGraph();
	Person A = new Person("A");
	Person B = new Person("B");
	Person C = new Person("C");
	Person D = new Person("D");
	Person E = new Person("E");
	Person F = new Person("F");
	
	FriendshipGraph graph = new FriendshipGraph();
	Person rachel = new Person("Rachel");
	Person ross = new Person("Ross");
	Person ben = new Person("Ben");
	Person kramer = new Person("Kramer");

	@Before
	public void Initialize() {

		FG.addVertex(A);
		FG.addVertex(B);
		FG.addVertex(C);
		FG.addVertex(D);
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
		
		
		
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);

	}

	/**
	 * Tests that assertions are enabled.
	 */
	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false;
	}

	/**
	 * Tests the case given by pdf.
	 */
	@Test
	public void Test1() {
		assertEquals(1, graph.getDistance(rachel, ross));
		assertEquals(2, graph.getDistance(rachel, ben));
		assertEquals(0, graph.getDistance(rachel, rachel));
		assertEquals(-1, graph.getDistance(rachel, kramer));
	}

	/**
	 * Tests a case contains 6 persons.
	 */
	@Test
	public void Test2() {
		
		assertFalse(FG.addEdge(B, E));//E doesn't exist in the graph.
		assertEquals(-1, FG.getDistance(A, E));//E doesn't exist in the graph,so the distance is -1.
		assertTrue(FG.addVertex(E));
		assertFalse(FG.addVertex(E));//E already exists in the graph.
		assertTrue(FG.addEdge(B, E));
		assertTrue(FG.addEdge(E, B));
		assertFalse(FG.addEdge(B, E));//this edge already exists.
		assertFalse(FG.addEdge(A, A));//you can't addEdge with yourself.
		
		assertEquals(0, FG.getDistance(A, A));
		assertEquals(1, FG.getDistance(A, B));
		assertEquals(2, FG.getDistance(A, E));
		assertEquals(1, FG.getDistance(B, D));
		
	}

}
