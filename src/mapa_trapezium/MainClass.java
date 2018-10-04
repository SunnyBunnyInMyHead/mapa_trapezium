package mapa_trapezium;

import java.util.ArrayList;

public class MainClass {
	private static final ArrayList<Point> points = new ArrayList<>();

	public static void main(String[] args) {

        points.add(new Point(1, 3));
        points.add(new Point(3, 6));
        points.add(new Point(7, 6));
        points.add(new Point(9, 3));
/*

        points.add(new Point(50.38137821162598, 29.142608642578125));
        points.add(new Point(50.390572301609, 29.234619140625));
        points.add(new Point(50.42951794712287, 29.2510986328125));
        points.add(new Point(50.41333094849548, 29.102096557617188));
*/

//    	points.add(new Point(1, 5));
//		points.add(new Point(1, 7));
//		points.add(new Point(2, 8.2));
//		points.add(new Point(3.5, 8));
//		points.add(new Point(4.2, 7));
//		points.add(new Point(4.5, 6));
//		points.add(new Point(4, 4));
//		points.add(new Point(3, 3.6));
//		points.add(new Point(2, 3.5));

		// started array
		Point[] pointArr = points.toArray(new Point[0]);
		
		System.out.println(Utils.isPolygonBulging(pointArr));

        show(pointArr);

		Point observer = new Point(5,12);//(2.5, 12);
        //Point observer = new Point(50.334942738432986, 29.17144775390625);
		Trapezium trapezium = TrapeziumUtils.calculateTrapezium(pointArr, observer, 4, 4,0);
		show(trapezium.getPoints());
		getTrPoint(trapezium.getPoints());

		//Point observer = new Point(2.5, 12);
		//new Trapezium(new Point(3.2435897435897525,8.034188034188032),new Point(2.004273504273513,8.158119658119656),new Point(1.5,4.25),new Point(4.0,4.0));//0
		//new Trapezium(new Point(3.5,8.0),new Point(2.2142857142857224,7.142857142857148),new Point(2.0,3.5),new Point(4.25,5.0));//1
		//new Trapezium(new Point(3.5,8.0),new Point(1.919152276295133,7.585557299843013),new Point(1.4835164835164836,4.274725274725275),new Point(4.25,5.0));//2
		//new Trapezium(new Point(3.2435897435897525,8.034188034188032),new Point(2.004273504273513,8.158119658119656),new Point(1.5,4.25),new Point(4.0,4.0));//3
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
