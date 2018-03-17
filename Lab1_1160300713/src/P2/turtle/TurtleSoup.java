/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;
import java.util.ArrayList;

public class TurtleSoup {

	/**
	 * Draw a square.
	 * 
	 * @param turtle
	 *            the turtle context
	 * @param sideLength
	 *            length of each side
	 */
	public static void drawSquare(Turtle turtle, int sideLength) {
		turtle.forward(sideLength);
		turtle.turn(90);
		turtle.forward(sideLength);
		turtle.turn(90);
		turtle.forward(sideLength);
		turtle.turn(90);
		turtle.forward(sideLength);
		turtle.turn(90);
	}

	/**
	 * Determine inside angles of a regular polygon.
	 * 
	 * There is a simple formula for calculating the inside angles of a polygon; you
	 * should derive it and use it here.
	 * 
	 * @param sides
	 *            number of sides, where sides must be > 2
	 * @return angle in degrees, where 0 <= angle < 360
	 */
	public static double calculateRegularPolygonAngle(int sides) {
		return (sides - 2) * 180.0 / sides;
	}

	/**
	 * Determine number of sides given the size of interior angles of a regular
	 * polygon.
	 * 
	 * There is a simple formula for this; you should derive it and use it here.
	 * Make sure you *properly round* the answer before you return it (see
	 * java.lang.Math). HINT: it is easier if you think about the exterior angles.
	 * 
	 * @param angle
	 *            size of interior angles in degrees, where 0 < angle < 180
	 * @return the integer number of sides
	 */
	public static int calculatePolygonSidesFromAngle(double angle) {
		return (int) (360.0 / (180 - angle) + 0.5);
	}

	/**
	 * Given the number of sides, draw a regular polygon.
	 * 
	 * (0,0) is the lower-left corner of the polygon; use only right-hand turns to
	 * draw.
	 * 
	 * @param turtle
	 *            the turtle context
	 * @param sides
	 *            number of sides of the polygon to draw
	 * @param sideLength
	 *            length of each side
	 */
	public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
		double angles = calculateRegularPolygonAngle(sides);
		for (int i = 0; i < sides - 1; i++) {
			turtle.forward(sideLength);
			turtle.turn(180 - angles);
		}
		turtle.forward(sideLength);
	}

	/**
	 * Given the current direction, current location, and a target location,
	 * calculate the heading towards the target point.
	 * 
	 * The return value is the angle input to turn() that would point the turtle in
	 * the direction of the target point (targetX,targetY), given that the turtle is
	 * already at the point (currentX,currentY) and is facing at angle
	 * currentHeading. The angle must be expressed in degrees, where 0 <= angle <
	 * 360.
	 *
	 * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
	 * 
	 * @param currentHeading
	 *            current direction as clockwise from north
	 * @param currentX
	 *            current location x-coordinate
	 * @param currentY
	 *            current location y-coordinate
	 * @param targetX
	 *            target point x-coordinate
	 * @param targetY
	 *            target point y-coordinate
	 * @return adjustment to heading (right turn amount) to get to target point,
	 *         must be 0 <= angle < 360
	 */
	public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY, int targetX,
			int targetY) {
		int x = targetX - currentX, y = targetY - currentY;
		double targetDegree = Math.toDegrees(Math.atan(1.0 * x / y));
		
		if (y < 0) 
			targetDegree += 180;

		double degreeSub = targetDegree - currentHeading;
		
		return degreeSub >= 0 ? degreeSub : (degreeSub + 360);
	}

	/**
	 * Given a sequence of points, calculate the heading adjustments needed to get
	 * from each point to the next.
	 * 
	 * Assumes that the turtle starts at the first point given, facing up (i.e. 0
	 * degrees). For each subsequent point, assumes that the turtle is still facing
	 * in the direction it was facing when it moved to the previous point. You
	 * should use calculateHeadingToPoint() to implement this function.
	 * 
	 * @param xCoords
	 *            list of x-coordinates (must be same length as yCoords)
	 * @param yCoords
	 *            list of y-coordinates (must be same length as xCoords)
	 * @return list of heading adjustments between points, of size 0 if (# of
	 *         points) == 0, otherwise of size (# of points) - 1
	 */
	public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
		ArrayList<Double> degreeList = new ArrayList<>();
		double currentHeading = 0, headingSub = 0;
		for (int i = 0; i < xCoords.size() - 1; i++) {
			headingSub = calculateHeadingToPoint(currentHeading, xCoords.get(i), yCoords.get(i), xCoords.get(i + 1),
					yCoords.get(i + 1));
			currentHeading += headingSub;
			degreeList.add(headingSub);
		}

		return degreeList;
	}

	/**
	 * Draw your personal, custom art.
	 * 
	 * Many interesting images can be drawn using the simple implementation of a
	 * turtle. For this function, draw something interesting; the complexity can be
	 * as little or as much as you want.
	 * 
	 * @param turtle
	 *            the turtle context
	 */
	public static void drawPersonalArt(Turtle turtle) {
		turtle.color(PenColor.RED);
		turtle.turn(calculateHeadingToPoint(0, 0, 0, 1, 1));
		for(int i = 0; i < 180; i++) {
			turtle.turn(1);
			turtle.forward(1);
		}
		turtle.forward(114);
		turtle.turn(90);
		turtle.forward(114);
		for(int i = 0; i < 180; i++) {
			turtle.turn(1);
			turtle.forward(1);
		}
		turtle.turn(180);
		for(int i = 0; i < 60; i++) {
			turtle.turn(359);
			turtle.forward(1);
		}
		turtle.turn(195);
		
		
		turtle.color(PenColor.MAGENTA);//L
		turtle.turn(90);
		turtle.forward(80);
		turtle.turn(270);
		turtle.forward(40);
		turtle.turn(180);
		
		turtle.color(PenColor.ORANGE);//o
		for(int i = 0; i < 120; i++) {
			turtle.turn(3);
			turtle.forward(1);
		}
		
		turtle.color(PenColor.CYAN);//v
		turtle.turn(180);
		for(int i = 0; i < 45; i++) {
			turtle.turn(358);
			turtle.forward(1);
		}
		turtle.turn(180);
		for(int i = 0; i < 30; i++) {
			turtle.turn(358);
			turtle.forward(1);
		}
		turtle.turn(300);
		for(int i = 0; i < 30; i++) {
			turtle.turn(358);
			turtle.forward(1);
		}
		
		turtle.color(PenColor.GRAY);//e
		turtle.turn(180);
		turtle.forward(10);
		turtle.turn(270);
		turtle.forward(40);
		turtle.turn(270);
		for(int i = 0; i < 110; i++) {
			turtle.turn(357);
			turtle.forward(1);
		}
	}

	/**
	 * Main method.
	 * 
	 * This is the method that runs when you run "java TurtleSoup".
	 * 
	 * @param args
	 *            unused
	 */
	public static void main(String args[]) {
		DrawableTurtle turtle = new DrawableTurtle();
//		ArrayList<Integer> x = new ArrayList<>();
//		ArrayList<Integer> y = new ArrayList<>();
//		x.add(0);
//		y.add(0);
//		x.add(1);
//		y.add(1);
//		x.add(2);
//		y.add(0);
		drawPersonalArt(turtle);
//		drawSquare(turtle, 40);
//		drawRegularPolygon(turtle, 8, 50);
//		System.out.println(calculateHeadingToPoint(60, 0, 0, 1, 1));
//		System.out.println(calculateHeadings(x, y));
		// draw the window

		turtle.draw();
	}

}
