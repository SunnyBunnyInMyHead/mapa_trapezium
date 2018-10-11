package mapa_trapezium;

import java.util.ArrayList;

import static mapa_trapezium.MainClass.show;

public class AdditionalMain {
    public static void main (String[] args){
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 3));
        points.add(new Point(3, 6));
        points.add(new Point(7, 6));
        points.add(new Point(9, 3));
        Point[] pointArr = points.toArray(new Point[0]);
        show(pointArr);
        Point observer = new Point(5, 12);
        Trapezium trapezium0 = TrapeziumUtils.calculateTrapezium(pointArr, observer, 0);
        show(trapezium0 .getPoints());
        System.out.println(trapezium0.findSektorArea());
        System.out.println(trapezium0.getArea());



    }
}