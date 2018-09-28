package mapa_trapezium;

import java.util.ArrayList;

class MainClass {
	private static final ArrayList<Point> points = new ArrayList<>();

	public static void main(String[] args) {
		
		points.add(new Point(1, 5));
		points.add(new Point(1, 7));
		points.add(new Point(2, 8.2));
		points.add(new Point(3.5, 8));
		points.add(new Point(4.2, 7));
		points.add(new Point(4.5, 6));
		points.add(new Point(4, 4));
		points.add(new Point(3, 3.6));
		points.add(new Point(2, 3.5));

		// started array
		Point[] pointArr = points.toArray(new Point[0]);
		
		System.out.println(Utils.isPolygonBulging(pointArr));
		
		 //show(pointArr);
		// edited array
		if(pointArr.length<=5){
			pointArr = Utils.addTwoMiddlePoints(pointArr);
		}else{
			pointArr = Utils.addMiddlePoints(pointArr);
		}
		
		 //show(pointArr);
		
		Point observer = new Point(2.5, 12);

		Trapezium trapezium = TrapeziumUtils.calculateTrapezium(pointArr, observer, 4, 4,0);
		show(trapezium.getPoints());

		System.out.println("near dist: " + Utils.getDist(observer, trapezium.getNearLine()));

		System.out.println("further dist: " + Utils.getDist(observer, trapezium.getFurtherLine()));
		
		//System.out.println(TestingUtils.test());
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
}
