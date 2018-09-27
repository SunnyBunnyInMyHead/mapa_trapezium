package mapa_trapezium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
	public static Line createLine(Point p1, Point p2) {
		double a, b, c;
		a = p1.getY() - p2.getY();
		b = p2.getX() - p1.getX();
		c = p1.getX() * p2.getY() - p2.getX() * p1.getY();
		if (a == 0 && b == 0) {
			return null;
		}
		return new Line(a, b, c);
	}

	public static double getAngle(Line l1, Line l2) {
		if (l1 == null || l2 == null) {
			return 1000;
		}
		double res = (l1.getA() * l2.getA() + l1.getB() * l2.getB())
				/ (Math.sqrt(l1.getA() * l1.getA() + l1.getB() * l1.getB())
						* Math.sqrt(l2.getA() * l2.getA() + l2.getB() * l2.getB()));

		return Math.acos(res) * 180 / Math.PI;
	}

	public static int getAngleDirection(Line l1, Line l2) {
		if (l1 == null || l2 == null) {
			return 1000;
		}
		// System.out.println(l1.getA() * l2.getA() + l1.getB() * l2.getB());
		if (l1.getA() * l2.getA() + l1.getB() * l2.getB() > 0) {
			return 1; // sharp
		} else if (l1.getA() * l2.getA() + l1.getB() * l2.getB() < 0) {
			return -1;
		} else {
			return 0;
		}

	}

	public static boolean isPolygonBulging(Point[] points) {
		if (points.length < 2) {
			return false;
		}
		boolean dir;
		Point vec1, vec2;
		// first
		vec1 = Utils.getVector(points[0], points[points.length - 1]);
		vec2 = Utils.getVector(points[0], points[1]);
		dir = getDirectionVect(vec1, vec2);

		for (int i = 1; i < points.length - 1; i++) {
			vec1 = Utils.getVector(points[i], points[i - 1]);// i,i-1
			vec2 = Utils.getVector(points[i], points[i + 1]);// i,i+1
			if (dir != getDirectionVect(vec1, vec2)) {
				return false;
			}
		}

		// last
		vec1 = Utils.getVector(points[points.length - 1], points[points.length - 2]);
		vec2 = Utils.getVector(points[points.length - 1], points[0]);
		if (dir != getDirectionVect(vec1, vec2)) {
			return false;
		}
		return true;
	}

	public static Line[] generateLines(Point[] points) {
		Line[] lines = new Line[points.length];
		for (int i = 0; i < points.length - 1; i++) {
			lines[i] = createLine(points[i], points[i + 1]);
		}
		lines[points.length - 1] = createLine(points[points.length - 1], points[0]);
		return lines;

	}

	public static double getDist(Point point, Line line) {
		return Math.abs(line.getA() * point.getX() + line.getB() * point.getY() + line.getC())
				/ Math.sqrt(line.getA() * line.getA() + line.getB() * line.getB());
	}

	public static double getDist(Point point1, Point point2) {
		return Math.sqrt((point2.getX() - point1.getX()) * (point2.getX() - point1.getX())
				+ (point2.getY() - point1.getY()) * (point2.getY() - point1.getY()));
	}

	public static Position[] getDist(Point point, Point[] points) {
		Position[] positions = new Position[points.length];
		for (int i = 0; i < points.length; i++) {
			positions[i] = new Position(points[i], getDist(point, points[i]));
		}
		return positions;
	}

	public static void sortPositionByDist(Position[] positions) {
		Position position;
		for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions.length - 1; j++) {
				if (positions[j].getDist() > positions[j + 1].getDist()) {
					position = positions[j];
					positions[j] = positions[j + 1];
					positions[j + 1] = position;
				}
			}
		}
	}

	public static Point getCrossPoint(Line l1, Line l2) {
		double a1, a2, b1, b2, c1, c2, x, y;
		a1 = l1.getA();
		b1 = l1.getB();
		c1 = l1.getC();

		a2 = l2.getA();
		b2 = l2.getB();
		c2 = l2.getC();

		y = (((a2 / a1) * c1) - c2) / (b2 - ((a2 / a1) * b1));
		x = (((b1 * y) + c1) / a1) * (-1);

		return new Point(x, y);
	}

	public static Point getVector(Point p1, Point p2) {
		return new Point(p2.getX() - p1.getX(), p2.getY() - p1.getY());
	}

	public static boolean getDirectionVect(Point p1, Point p2) {
		return (p1.getX() * p2.getY() - p1.getY() * p2.getX()) > 0 ? true : false;
	}

	public static Trapezium getBestTrapezium(Trapezium[] trapeziums) {
		if (trapeziums.length == 0) {
			return null;
		}
		Trapezium bestTrapezium = trapeziums[0];
		double bestArea = bestTrapezium.getArea();
		if (trapeziums.length == 1) {
			return bestTrapezium;
		}
		for (int i = 1; i < trapeziums.length; i++) {
			// System.out.println("curr:"+trapeziums[i].getArea()+"
			// best:"+bestArea);
			if (trapeziums[i].getArea() > bestArea) {
				bestArea = trapeziums[i].getArea();
				bestTrapezium = trapeziums[i];
			}
		}
		return bestTrapezium;
	}

	public static Point getMidlePoint(Point p1, Point p2) {
		return new Point((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
	}

	public static Point[] getTwoMidlePoint(Point p1, Point p2) {
		double x = p1.getX() + p2.getX(), y = p1.getY() + p2.getY();
		return new Point[] { new Point(x / 3, y / 3), new Point((2 / 3) * x, (2 / 3) * y) };
	}

	public static Point[] addMidlePoints(Point[] points) {
		Point[] arrWithAddPoints = new Point[points.length * 2];
		for (int i = 0; i < arrWithAddPoints.length - 2; i += 2) {
			arrWithAddPoints[i] = points[i / 2];
			arrWithAddPoints[i + 1] = getMidlePoint(points[i / 2], points[(i / 2) + 1]);
		}
		arrWithAddPoints[arrWithAddPoints.length - 2] = points[points.length - 1];
		arrWithAddPoints[arrWithAddPoints.length - 1] = getMidlePoint(points[points.length - 1], points[0]);

		return arrWithAddPoints;
	}

	public static Point[] addTwoMidlePoints(Point[] points) {
		ArrayList<Point> list = new ArrayList<Point>();
		for (int i = 0; i < points.length - 1; i++) {
			list.add(points[i]);
			list.addAll(new ArrayList<>(Arrays.asList(getTwoMidlePoint(points[i], points[i + 1]))));
		}
		list.add(points[points.length - 1]);
		list.addAll(new ArrayList<>(Arrays.asList(getTwoMidlePoint(points[points.length - 1], points[0]))));
		return list.toArray(new Point[list.size()]);
	}

	public static boolean isLineCrossLength(Line line, Point p1, Point p2) {
		boolean meaning;

		meaning = (p1.getX() * line.getA() + p1.getY() * line.getB() + line.getC()) > 0;

		if (meaning != (p2.getX() * line.getA() + p2.getY() * line.getB() + line.getC()) > 0) {
			return true;
		}
		return false;
	}

	public static Point getNearestCrossPoint(Point[] points, Point maxPoint, Point observer, Point totalMin) {

		Line[] lines = generateLines(points);
		Line maxDistLine = createLine(observer, maxPoint);
		double maxDistance = getDist(maxPoint, observer);
		double totalMinDistance = getDist(totalMin, observer);
		if (maxDistLine == null) {
			return null;
		}

		Point crossPoint, bestCrossPoint = maxPoint;
		double distance, bestCrossPointDist = maxDistance;
		for (int i = 0; i < lines.length - 1; i++) {
			if (isLineCrossLength(maxDistLine, points[i], points[i + 1])) {
				crossPoint = getCrossPoint(lines[i], maxDistLine);
				distance = getDist(observer, crossPoint);
				if (distance < maxDistance && distance > totalMinDistance) {
					if (distance < bestCrossPointDist) {// check best
						bestCrossPoint = crossPoint;
						bestCrossPointDist = distance;
					}
				}
			}
		}

		if (isLineCrossLength(maxDistLine, points[lines.length - 1], points[0])) {
			crossPoint = getCrossPoint(lines[lines.length - 1], maxDistLine);
			distance = getDist(observer, crossPoint);
			if (distance < maxDistance && distance > totalMinDistance) {
				if (distance < bestCrossPointDist) {// check best
					bestCrossPoint = crossPoint;
					bestCrossPointDist = distance;
				}
			}
		}

		if (bestCrossPoint.equals(maxPoint)) {
			return null;// there are no good points
		}
		return bestCrossPoint;
	}

	public static Point getFurtherCrossPoint(Point[] points, Point minPoint, Point observer, Point totalMax) {

		Line[] lines = generateLines(points);
		Line minDistLine = createLine(observer, minPoint);
		double minDistance = getDist(minPoint, observer);
		double totalMaxDistance = getDist(totalMax, observer);
		if (minDistLine == null) {
			return null;
		}
		Point crossPoint, bestCrossPoint = minPoint;
		double distance, bestCrossPointDist = minDistance;
		for (int i = 0; i < lines.length - 1; i++) {
			if (isLineCrossLength(minDistLine, points[i], points[i + 1])) {
				crossPoint = getCrossPoint(lines[i], minDistLine);
				distance = getDist(observer, crossPoint);
				if (distance > minDistance && distance < totalMaxDistance) {
					if (distance > bestCrossPointDist) {// check best
						bestCrossPoint = crossPoint;
						bestCrossPointDist = distance;
					}
				}
			}
		}

		if (isLineCrossLength(minDistLine, points[lines.length - 1], points[0])) {
			crossPoint = getCrossPoint(lines[lines.length - 1], minDistLine);
			distance = getDist(observer, crossPoint);
			if (distance > minDistance && distance < totalMaxDistance) {
				if (distance > bestCrossPointDist) {// check best
					bestCrossPoint = crossPoint;
					bestCrossPointDist = distance;
				}
			}
		}

		if (bestCrossPoint.equals(minPoint)) {
			return null;// there are no good points
		}
		return bestCrossPoint;

	}

	public static Trapezium calculateTrapezium(Point[] points, Point observer, int numbNearestPoints,
			int numbFurtherPoints) {
		// create positions(points with distance)
		Position[] positions = Utils.getDist(observer, points);
		Utils.sortPositionByDist(positions);
		/*
		 * for (Position position : positions) { System.out.println(
		 * position.getPoint().getX() + " " + position.getPoint().getY() +
		 * " dist:" + position.getDist()); }
		 */

		// create Trapezium
		List<Trapezium> list = new ArrayList<Trapezium>();
		list.addAll(getTrapeziumsByDifferentPoints(points,observer,numbNearestPoints,numbFurtherPoints));
		list.addAll(getTrapeziumsByNearestPoints(points, observer, (int)(numbNearestPoints*1.8)));
		list.addAll(getTrapeziumsByFurtherPoints(points, observer, (int)(numbNearestPoints*1.8)));
		System.out.println(list.size());
		Trapezium trapezium = Utils.getBestTrapezium(list.toArray(new Trapezium[list.size()]));
		// System.out.println(tr.getArea());

		return trapezium;
	}

	public static Point[] dellRepitedNearestPoints(Point[] points) {
		ArrayList<Point> list = new ArrayList<>();
		Point lastPoint = points[0];
		for (int i = 1; i < points.length; i++) {
			if (lastPoint.getX() != points[i].getX() && lastPoint.getY() != points[i].getY()) {
				lastPoint = points[i];
				list.add(lastPoint);
			}
		}

		if (points[0].getX() != points[points.length - 1].getX()
				&& points[0].getY() != points[points.length - 1].getY()) {
			list.remove(list.size() - 1);// dell last
		}
		return list.toArray(new Point[list.size()]);
	}

	public static boolean isLineParallel(Line l1, Line l2) {
		return (l1.getA() * l2.getB() - l1.getB() * l2.getA() == 0) ? true : false;
	}

	public static Line getParallelLine(Line line, Point point) {
		return new Line(line.getA(), line.getB(), (line.getA() * point.getX() + line.getB() * point.getY()) * (-1));
	}

	private static List<Trapezium> getTrapeziumsByDifferentPoints(Point[] points, Point observer, int numbNearestPoints,
			int numbFurtherPoints) {
		Position[] positions = Utils.getDist(observer, points);
		Utils.sortPositionByDist(positions);
		Point maxPos;
		Point minPos;
		Point totalMaxPos = positions[positions.length - 1].getPoint();
		Point totalMinPos = positions[0].getPoint();
		Point secondNearestPoint;
		Point secondFurtherPoint;
		List<Trapezium> list = new ArrayList<Trapezium>();
		for (int i = 0; i < numbNearestPoints; i++) {
			for (int j = 0; j < numbFurtherPoints; j++) {
				maxPos = positions[positions.length - 1 - j].getPoint();
				minPos = positions[i].getPoint();
				secondNearestPoint = Utils.getNearestCrossPoint(points, maxPos, observer, totalMinPos);
				if (secondNearestPoint == null) {
					continue;
				}
				secondFurtherPoint = Utils.getFurtherCrossPoint(points, minPos, observer, totalMaxPos);
				if (secondFurtherPoint == null) {
					continue;
				}
				list.add(new Trapezium(minPos, secondNearestPoint, maxPos, secondFurtherPoint));
			}
		}
		return list;
	}

	private static List<Trapezium> getTrapeziumsByNearestPoints(Point[] points, Point observer, int numbNearestPoints) {
		Position[] positions = Utils.getDist(observer, points);
		Utils.sortPositionByDist(positions);
		Point minPos1, minPos2;
		Point totalMaxPos = positions[positions.length - 1].getPoint();
		Point furtherPoint1, furtherPoint2;
		List<Trapezium> list = new ArrayList<Trapezium>();
		outer: for (int i = 0; i < numbNearestPoints; i++) {
			minPos1 = positions[i].getPoint();
			furtherPoint1 = Utils.getFurtherCrossPoint(points, minPos1, observer, totalMaxPos);
			if (furtherPoint1 == null) {
				continue outer;
			}
			inner: for (int j = 0; j < numbNearestPoints; j++) {
				if (i == j) {
					continue inner;
				}
				minPos2 = positions[j].getPoint();
				furtherPoint2 = Utils.getFurtherCrossPoint(points, minPos2, observer, totalMaxPos);
				if (furtherPoint2 == null) {
					continue inner;
				}
				list.add(new Trapezium(minPos1, minPos2, furtherPoint2, furtherPoint1));
			}
		}
		return list;
	}

	private static List<Trapezium> getTrapeziumsByFurtherPoints(Point[] points, Point observer, int numbFurtherPoints) {
		Position[] positions = Utils.getDist(observer, points);
		Utils.sortPositionByDist(positions);
		Point maxPos1, maxPos2;
		Point totalMinPos = positions[0].getPoint();
		Point nearestPoint1, nearestPoint2;
		List<Trapezium> list = new ArrayList<Trapezium>();
		outer: for (int i = 0; i < numbFurtherPoints; i++) {
			maxPos1 = positions[positions.length - 1 - i].getPoint();
			nearestPoint1 = Utils.getNearestCrossPoint(points, maxPos1, observer, totalMinPos);
			if (nearestPoint1 == null) {
				continue outer;
			}
			inner: for (int j = 0; j < numbFurtherPoints; j++) {
				if (i == j) {
					continue inner;
				}
				maxPos2 = positions[positions.length - 1 - j].getPoint();
				
				nearestPoint2 = Utils.getNearestCrossPoint(points, maxPos2, observer, totalMinPos);
				if (nearestPoint2 == null) {
					continue inner;
				}
				list.add(new Trapezium(nearestPoint1, nearestPoint2, maxPos2, maxPos1));
			}
		}
		return list;
	}

}
