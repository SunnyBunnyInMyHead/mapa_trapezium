package mapa_trapezium;

import java.util.ArrayList;

import static mapa_trapezium.MainClass.show;

public class AdditionalMain {
    public static void main (String[] args){
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1028, 8352));
        points.add(new Point(1058, 8447));
        points.add(new Point(1133, 8507));
        points.add(new Point(1135, 8263));
        Point[] pointArr = points.toArray(new Point[0]);
        //show(pointArr);
        Point observer = new Point(1029, 8373);
        Trapezium trapezium0 = TrapeziumUtils.calculateTrapezium(pointArr, observer, 3);
        show(trapezium0 .getPoints());
        System.out.println(trapezium0.findSektorArea());
        System.out.println(trapezium0.getArea());

        //System.out.println(Utils.getDist(observer,new Point( 1133, 8507)));
       // System.out.println(Utils.getDist(observer,new Point( 1135, 8263)));
        Line nl = Utils.createLine(new Point(1058, 8447),new Point(1028, 8352));
        Line cross1 = Utils.createLine(observer,new Point( 1133, 8507));
        Line cross2 = Utils.createLine(observer,new Point( 1135, 8263));
        Point p1= Utils.getCrossPoint(nl,cross1), p2 = Utils.getCrossPoint(nl,cross2);
        Trapezium trapezium = new Trapezium(p1,p2,new Point(1135,  8263),new Point( 1133, 8507),observer);
        //show(trapezium .getPoints());
        System.out.println(trapezium.findSektorArea());
        System.out.println(trapezium.getArea());
    }
}