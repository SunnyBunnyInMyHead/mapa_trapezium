import mapa_trapezium.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.fail;

public class Testing {
    @Test
    public void FullTest() {
        //rhombusTest();
        //circleTest();
        //trapeziumTest();
        //triangleTest();
        //pentagonTest();
        hexTest();
    }

    @Test
    public void rhombusTest(){

    }

    @Test
    public void circleTest(){

    }

    @Test
    public void trapeziumTest(){

    }

    @Test
    public void triangleTest(){

    }

    @Test
    public void pentagonTest(){

    }

    @Test
    public void hexTest(){
        System.out.println("hexTest");
        //check value
        Trapezium [] trapeziums = new Trapezium[4];

        trapeziums[0] = new Trapezium(new Point(3.2435897435897525,8.034188034188032),new Point(2.004273504273513,8.158119658119656),new Point(1.5,4.25),new Point(4.0,4.0));//0
        trapeziums[1] = new Trapezium(new Point(3.5,8.0),new Point(2.2142857142857224,7.142857142857148),new Point(2.0,3.5),new Point(4.25,5.0));//1
        trapeziums[2] = new Trapezium(new Point(3.5,8.0),new Point(1.919152276295133,7.585557299843013),new Point(1.4835164835164836,4.274725274725275),new Point(4.25,5.0));//2
        trapeziums[3] = new Trapezium(new Point(3.2435897435897525,8.034188034188032),new Point(2.004273504273513,8.158119658119656),new Point(1.5,4.25),new Point(4.0,4.0));//3

        Point observer = new Point(2.5, 12);
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
        assert Utils.isPolygonBulging(pointArr);
        for (int i = 0;i<4;i++){// 4, because there are only 4 type of generation
            if(!testGenTrap(pointArr,observer,trapeziums[i],i,4,4)){
                fail(String.valueOf("fail on"+i));
            }
        }
    }

    boolean testGenTrap(Point[] pointsArr, Point observer, Trapezium trapezium ,int typeOfTrapGen, int numbOfNearestPoints, int numbOfFurtherPoints){

        Trapezium trapeziumCalculated = TrapeziumUtils.calculateTrapezium(pointsArr, observer, numbOfNearestPoints, numbOfFurtherPoints,typeOfTrapGen);

        return  (same(trapezium.getArea(),trapeziumCalculated.getArea())
                &&(same(Utils.getDist(observer, trapezium.getNearLine()), Utils.getDist(observer, trapeziumCalculated.getNearLine())))
                &&(same(Utils.getDist(observer, trapezium.getFurtherLine()), Utils.getDist(observer, trapeziumCalculated.getFurtherLine())))
                );
    }

    private boolean same(double numb1, double numb2){
        return numb1-numb2<0.00001;
    }
}