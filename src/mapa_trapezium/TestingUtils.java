package mapa_trapezium;

import java.util.ArrayList;

public class TestingUtils {
	
	public static boolean testBulging() {
		ArrayList<Point> points;

		int trueNumbs = 0;
		points = new ArrayList<Point>();
		// checking if Polygon is Bulging
		points.add(new Point(5, 10));
		points.add(new Point(7, 7));
		points.add(new Point(8, 4));
		points.add(new Point(3, 3));
		points.add(new Point(1, 8));

		// points.add(new Point(-5,3));
		System.out.println();

		trueNumbs = Utils.isPolygonBulging(points.toArray(new Point[points.size()])) ? trueNumbs++ : trueNumbs--;
		// must be ++

		points.add(new Point(-5, 3));

		trueNumbs = Utils.isPolygonBulging(points.toArray(new Point[points.size()])) ? trueNumbs++ : trueNumbs--;
		// must be --
		return trueNumbs == 0 ? true : false;
	}
	
	public static boolean testTrapezium1() {
		ArrayList<Point> points = new ArrayList<Point>();
		
		points.add(new Point(1, 5));
		points.add(new Point(1, 7));
		points.add(new Point(2, 8.2));
		points.add(new Point(3.5, 8));
		points.add(new Point(4.2, 7));
		points.add(new Point(4.5, 6));
		points.add(new Point(4, 4));
		points.add(new Point(3, 3.6));
		points.add(new Point(2, 3.5));
		
		Point[] pointArr = points.toArray(new Point[points.size()]);
		//Utils.isPolygonBulging(pointArr);
		
		pointArr = Utils.addMidlePoints(pointArr);
		
		Point observer = new Point(10, 15);
		Trapezium trapezium = Utils.calculateTrapezium(pointArr, observer, 3, 3);
		
		Trapezium checkTrapezium = new Trapezium(new Point(3.50,8.00),new Point(4.3758573388203015, 6.413808870598994),new Point(2.50, 3.55),new Point(1.00, 5.3076923076923075));
	
		if(trapezium.getArea()==checkTrapezium.getArea()){
			return true;
		}
		
		return false;
	}
	
	public static boolean testTrapezium2() {
		ArrayList<Point> points = new ArrayList<Point>();
		
		points.add(new Point(1, 5));
		points.add(new Point(1, 7));
		points.add(new Point(2, 8.2));
		points.add(new Point(3.5, 8));
		points.add(new Point(4.2, 7));
		points.add(new Point(4.5, 6));
		points.add(new Point(4, 4));
		points.add(new Point(3, 3.6));
		points.add(new Point(2, 3.5));
		
		Point[] pointArr = points.toArray(new Point[points.size()]);
		//Utils.isPolygonBulging(pointArr);
				
		Point observer = new Point(10, 15);
		Trapezium trapezium = Utils.calculateTrapezium(pointArr, observer, 3, 3);
		
		Trapezium checkTrapezium = new Trapezium(new Point(3.50,8.00),new Point(4.4913627639155465, 6.028790786948174),new Point(3.0, 3.6),new Point(1.00, 5.3076923076923075));
	
		if(trapezium.getArea()==checkTrapezium.getArea()){
			return true;
		}
		
		return false;
	}

	public static boolean testDellNearest() {
		Point[] points = new Point[]{new Point(5, 10),new Point(7, 7),new Point(7, 7),new Point(8, 4),
				new Point(3, 3),new Point(3, 3),new Point(3, 3),new Point(5, 10),new Point(1, 8),new Point(1, 8),new Point(5, 10)};
		points = Utils.dellRepitedNearestPoints(points);
		if(points.length==6){
			return true;
		}
		return false;
	}

	public static boolean test() {
		int i=0;
		if(TestingUtils.testBulging()) i++;
		if(TestingUtils.testTrapezium1()) i++;
		if(TestingUtils.testTrapezium2()) i++;
		if(TestingUtils.testDellNearest()) i++;
		if(i==4){
			return true;
		}
		return false;
	}
}
