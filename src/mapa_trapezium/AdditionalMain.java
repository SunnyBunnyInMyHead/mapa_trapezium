package mapa_trapezium;

import java.util.ArrayList;

import static mapa_trapezium.Utils.getDirectionVectorSign;

public class AdditionalMain {
    public static void main (String[] args){
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 3));
        points.add(new Point(3, 6));
        points.add(new Point(7, 6));
        points.add(new Point(9, 3));
        Point[] pointArr = points.toArray(new Point[0]);
        Point observer = new Point(3,6);
        System.out.println(Utils.isPointOnBorder(observer,pointArr));
    }
}