package mapa_trapezium;

import java.util.ArrayList;

public class MainClass {
	static ArrayList<Point> points = new ArrayList<Point>();

	public static void main(String[] args) {
/*		String s  = "522.85";
		String[] s1 = s.split("\\.");
		System.out.println(s1.length);
		double d = Double.valueOf(s);
		int main,add;
		main = Integer.valueOf(s1[0]);
		add = Integer.valueOf(s1[1]);
		System.out.println(main);
		System.out.println(add);*/
		
		
		/*outer: for (int i = 0; i < 4; i++) {
			System.out.println("i"+i);
			inner: for (int j = 0; j < 4; j++) {
				if (i == j) {
					continue inner;
				}
				System.out.println("j"+j);
			}
		}*/
		
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
		Point[] pointArr = points.toArray(new Point[points.size()]);
		
		System.out.println(Utils.isPolygonBulging(pointArr));
		
		 //show(pointArr);
		// edited array
		if(pointArr.length<=5){
			pointArr = Utils.addTwoMidlePoints(pointArr);
		}else{
			pointArr = Utils.addMidlePoints(pointArr);
		}
		
		 //show(pointArr);
		
		Point observer = new Point(2.5, 12);

		Trapezium trapezium = Utils.calculateTrapezium(pointArr, observer, 4, 4);
		show(trapezium.getPoints());

		System.out.println("near dist: " + Utils.getDist(observer, trapezium.getNearLine()));

		System.out.println("further dist: " + Utils.getDist(observer, trapezium.getFurtherLine()));
		
		//System.out.println(TestingUtils.test());
	}

	public static void show(Point[] points) {
		System.out.println("x");
		for (Point point : points) {
			System.out.println(point.getX());
		}
		System.out.println("y");
		for (Point point : points) {
			System.out.println(point.getY());
		}
		System.out.println("");

	}
}
