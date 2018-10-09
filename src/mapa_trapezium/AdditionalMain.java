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
        System.out.println(isPointInside(observer,pointArr));


    }

    private static boolean isPointInside(Point point, Point[] arr){
        if (arr.length < 2) {
            return false;
        }
        Point vec1, vec2;

        for (int i = 0; i < arr.length - 1; i++){
            vec1 = Utils.getVector(arr[i], point);
            vec2 = Utils.getVector(arr[i], arr[i + 1]);
            if(!getDirectionVectorSign(vec1,vec2)){
                return false;
            }
        }

        vec1 = Utils.getVector(arr[arr.length - 1], point);
        vec2 = Utils.getVector(arr[arr.length - 1], arr[0]);
        if(!getDirectionVectorSign(vec1,vec2)){
            return false;
        }

        return true;
    }
}
