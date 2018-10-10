package mapa_trapezium;

import java.util.ArrayList;

import static mapa_trapezium.MainClass.show;

public class AdditionalMain {
    public static void main (String[] args){
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point( 48.10287466329324, 37.83524036407471));
        points.add(new Point(48.10588373465718, 37.84472465515137));
        points.add(new Point(48.11339131599862, 37.85073280334473));
        points.add(new Point( 48.11356323249645, 37.82635688781738));
        Point[] pointArr = points.toArray(new Point[0]);
        show(pointArr);
        Point observer = new Point(48.10298929781082,37.83742904663086);
        Trapezium trapezium0 = TrapeziumUtils.calculateTrapezium(pointArr, observer, 0);
        show(trapezium0 .getPoints());
    }
}