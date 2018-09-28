package mapa_trapezium;

import java.util.ArrayList;
import java.util.Arrays;

public class Utils {
	static Line createLine(Point p1, Point p2) {
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
		dir = getDirectionVectorSign(vec1, vec2);

		for (int i = 1; i < points.length - 1; i++) {
			vec1 = Utils.getVector(points[i], points[i - 1]);// i,i-1
			vec2 = Utils.getVector(points[i], points[i + 1]);// i,i+1
			if (dir != getDirectionVectorSign(vec1, vec2)) {
				return false;
			}
		}

		// last
		vec1 = Utils.getVector(points[points.length - 1], points[points.length - 2]);
		vec2 = Utils.getVector(points[points.length - 1], points[0]);
		return dir == getDirectionVectorSign(vec1, vec2);
	}

	static Line[] generateLines(Point[] points) {
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

	static double getDist(Point point1, Point point2) {
		return Math.sqrt((point2.getX() - point1.getX()) * (point2.getX() - point1.getX())
				+ (point2.getY() - point1.getY()) * (point2.getY() - point1.getY()));
	}

	static Position[] getDist(Point point, Point[] points) {
		Position[] positions = new Position[points.length];
		for (int i = 0; i < points.length; i++) {
			positions[i] = new Position(points[i], getDist(point, points[i]));
		}
		return positions;
	}

	static void sortPositionByDist(Position[] positions) {
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

	static Point getCrossPoint(Line l1, Line l2) {
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

	private static Point getVector(Point p1, Point p2) {
		return new Point(p2.getX() - p1.getX(), p2.getY() - p1.getY());
	}

	private static boolean getDirectionVectorSign(Point p1, Point p2) {
		return (p1.getX() * p2.getY() - p1.getY() * p2.getX()) > 0;
	}

	private static Point getMiddlePoint(Point p1, Point p2) {
		return new Point((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
	}

	private static Point[] getTwoMiddlePoint(Point p1, Point p2) {
		double x = p1.getX() + p2.getX(), y = p1.getY() + p2.getY();
		return new Point[] { new Point(x / 3, y / 3), new Point((x / 3) * 2, (y / 3) * 2) };
	}

	public static Point[] addMiddlePoints(Point[] points) {
		Point[] arrWithAddPoints = new Point[points.length * 2];
		for (int i = 0; i < arrWithAddPoints.length - 2; i += 2) {
			arrWithAddPoints[i] = points[i / 2];
			arrWithAddPoints[i + 1] = getMiddlePoint(points[i / 2], points[(i / 2) + 1]);
		}
		arrWithAddPoints[arrWithAddPoints.length - 2] = points[points.length - 1];
		arrWithAddPoints[arrWithAddPoints.length - 1] = getMiddlePoint(points[points.length - 1], points[0]);

		return arrWithAddPoints;
	}

	public static Point[] addTwoMiddlePoints(Point[] points) {
		ArrayList<Point> list = new ArrayList<>();
		for (int i = 0; i < points.length - 1; i++) {
			list.add(points[i]);
			list.addAll(new ArrayList<>(Arrays.asList(getTwoMiddlePoint(points[i], points[i + 1]))));
		}
		list.add(points[points.length - 1]);
		list.addAll(new ArrayList<>(Arrays.asList(getTwoMiddlePoint(points[points.length - 1], points[0]))));
		return list.toArray(new Point[0]);
	}

	static boolean isLineCrossStretch(Line line, Point p1, Point p2) {
		boolean meaning;

		meaning = (p1.getX() * line.getA() + p1.getY() * line.getB() + line.getC()) > 0;

		return meaning != (p2.getX() * line.getA() + p2.getY() * line.getB() + line.getC()) > 0;
	}

	public static Point[] dellRepeatedNearestPoints(Point[] points) {
		ArrayList<Point> list = new ArrayList<>();
		Point lastPoint = points[points.length - 1];// last point
		for (int i = 0; i < points.length; i++) {
			if (lastPoint.getX() != points[i].getX() && lastPoint.getY() != points[i].getY()) {
				lastPoint = points[i];
				list.add(lastPoint);
			}
		}
		return list.toArray(new Point[0]);
	}

	static boolean isLineParallel(Line l1, Line l2) {
		return l1.getA() * l2.getB() - l1.getB() * l2.getA() == 0;
	}

	static Line getParallelLine(Line line, Point point) {
		return new Line(line.getA(), line.getB(), (line.getA() * point.getX() + line.getB() * point.getY()) * (-1));
	}

}