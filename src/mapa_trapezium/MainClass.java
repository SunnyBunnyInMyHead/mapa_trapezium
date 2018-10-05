package mapa_trapezium;

import java.util.ArrayList;

public class MainClass {
	private static final ArrayList<Point> points = new ArrayList<>();

	public static void main(String[] args) {


        points.add(new Point(50.38137821162598, 29.142608642578125));
        points.add(new Point(50.390572301609, 29.234619140625));
        points.add(new Point(50.42951794712287, 29.2510986328125));
        points.add(new Point(50.41333094849548, 29.102096557617188));


		// started array
		Point[] pointArr = points.toArray(new Point[0]);
		
		System.out.println(Utils.isPolygonBulging(pointArr));

        show(pointArr);

        Point observer = new Point(50.334942738432986, 29.17144775390625);
		Trapezium trapezium0 = TrapeziumUtils.calculateTrapezium(pointArr, observer, 5, 5,0);
		show(trapezium0 .getPoints());
		getTrPoint(trapezium0 .getPoints());
	}

	private static void show(Point[] points) {
		System.out.println("x");
		for (Point point : points) {
			System.out.println(point.getX());
		}
		System.out.println("y");
		for (Point point : points) {
			System.out.println(point.getY());
		}
		System.out.println(" ");
	}

	public static void getTrPoint(Point[] points){
		for (Point p:points){
			System.out.print("new Point("+p.getX()+","+p.getY()+"),");
		}
	}
}
