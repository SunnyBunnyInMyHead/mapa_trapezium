package mapa_trapezium;

import java.util.ArrayList;

import static mapa_trapezium.MainClass.show;

public class AdditionalMain {
    public static void main (String[] args){
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 5));
        points.add(new Point(1, 7));
        points.add(new Point(2, 8.2));
        points.add(new Point(3.5, 8));
        points.add(new Point(4.2, 7));
        points.add(new Point(4.5, 6));
        points.add(new Point(4, 4));
        points.add(new Point(3, 3.6));
        points.add(new Point(2, 3.5));
        Point[] pointArr = points.toArray(new Point[0]);
        show(pointArr);
        Point observer = new Point(2.5, 12);
        step(pointArr,observer,0);
        step(pointArr,observer,1);
        step(pointArr,observer,2);
        step(pointArr,observer,3);

    }

    private static void showO(Point p ){
        System.out.println("observer " +p.getX()+" "+p.getY());
    }

    private static void step(Point[] arr,Point observer,int type){
        Trapezium trapezium0;
        System.out.println("");
        trapezium0 = TrapeziumUtils.calculateTrapezium(arr, observer, type);
        showO(observer);
        show(trapezium0 .getPoints());
        MainClass.getTrPoint(trapezium0 .getPoints());
    }
}