import mapa_trapezium.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class Testing {
   /* @Test
    public void FullTest() {
        rhombusTest();
        trapeziumTest();
        triangleTest();
        pentagonTest();
        hexTest();
    }

    @Test
    public void rhombusTest(){
        System.out.println("rhombusTest");
        Trapezium checkTrapezium = new Trapezium(new Point(7.0,7.0),new Point(6.0,10.0),new Point(8.0,16.0),new Point(10.0,10.0));// jn diagonal
        Point observer = new Point(4,4);
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(6, 10));
        points.add(new Point(8, 16));
        points.add(new Point(10, 10));
        points.add(new Point(8, 4));
        Point[] pointArr = points.toArray(new Point[0]);
        assert Utils.isPolygonBulging(pointArr);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0,5,5));

        //side
        checkTrapezium = new Trapezium(new Point(7.312252964426879,6.300395256917),new Point(6.545454545454547,11.63636363636364),new Point(8.25,15.25),new Point(9.304347826086957,7.913043478260871));
        observer =new Point(2,2);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0,5,5));
    }

    @Test
    public void trapeziumTest(){
        System.out.println("trapeziumTest");
        Trapezium checkTrapezium = new Trapezium(new Point(3.0,6.0),new Point(7.0,6.0),new Point(8.0,3.0),new Point(2.0,3.0));//up
        Point observer = new Point(5, 12);
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 3));
        points.add(new Point(3, 6));
        points.add(new Point(7, 6));
        points.add(new Point(9, 3));
        Point[] pointArr = points.toArray(new Point[0]);
        assert Utils.isPolygonBulging(pointArr);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0,5,5));
        checkTrapezium = new Trapezium(new Point(7.857142857142857,4.714285714285714),new Point(2.142857142857143,4.714285714285714),new Point(9.0,3.0),new Point(1.0,3.0));//center
        observer =new Point(5,4);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0,5,5));
        //up cross lines
        checkTrapezium = new Trapezium(new Point(7.0,6.0),new Point(3.0,6.0),new Point(1.0,3.0),new Point(9.0,3.0));
        observer =new Point(5,9);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0,5,5));
        //under big base
        checkTrapezium = new Trapezium(new Point(6.0,3.0),new Point(4.0,3.0),new Point(3.0,6.0),new Point(7.0,6.0));
        observer =new Point(5,0);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0,5,5));
        //on diagonal
        checkTrapezium = new Trapezium(new Point(8.66666666666667,3.5),new Point(6.0,3.0),new Point(2.428571428571429,5.142857142857143),new Point(7.0,6.0));
        observer =new Point(11,0);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0,5,5));
        //up corner
        checkTrapezium = new Trapezium(new Point(7.25,5.625),new Point(6.5,6.0),new Point(3.0,6.0),new Point(9.0,3.0));
        observer =new Point(7, 6);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0,5,5));
    }

    @Test
    public void triangleTest(){
        System.out.println("triangleTest");
        Trapezium checkTrapezium = new Trapezium(new Point(5.800000000000001,5.4),new Point(8.2,5.4),new Point(8.5,3.0),new Point(5.5,3.0));//up
        Point observer = new Point(7,15);
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(4, 3));
        points.add(new Point(7, 7));
        points.add(new Point(10, 3));
        Point[] pointArr = points.toArray(new Point[0]);
        assert Utils.isPolygonBulging(pointArr);
        assertTrue("//up",testGenTrap(pointArr,observer,checkTrapezium,0,5,5));

        //under big base
        checkTrapezium = new Trapezium(new Point(7.75,3.0),new Point(6.25,3.0),new Point(5.6875,5.25),new Point(8.3125,5.25));
        observer =new Point(7,0);
        assertTrue("//under big base",testGenTrap(pointArr,observer,checkTrapezium,0,5,5));

        //on diagonal
        checkTrapezium = new Trapezium(new Point(7.999999999999998,4.333333333333333),new Point(7.0,3.0),new Point(5.0,4.333333333333333),new Point(6.333333333333333,6.111111111111111));
        observer =new Point(13,-1);
        assertTrue("//on diagonal",testGenTrap(pointArr,observer,checkTrapezium,0,5,5));

        //somewhere near side diagonal
        checkTrapezium = new Trapezium(new Point(7.176470588235294,4.647058823529412),new Point(8.764705882352942,4.647058823529412),new Point(7.0,3.0),new Point(4.0,3.0));
        observer =new Point(10.75, 6.5);
        assertTrue("//somewhere near side diagonal",testGenTrap(pointArr,observer,checkTrapezium,0,5,5));
    }

    @Test
    public void pentagonTest(){
        System.out.println("pentagonTest");
        Trapezium checkTrapezium = new Trapezium(new Point(9.6,16.399999999999995),new Point(9.6,11.6),new Point(4.0,10.0),new Point(4.0,18.0));//up
        Point observer = new Point(18,14);
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(4, 10));
        points.add(new Point(4, 18));
        points.add(new Point(8, 18));
        points.add(new Point(12, 14));
        points.add(new Point(8, 10));
        Point[] pointArr = points.toArray(new Point[0]);
        assert Utils.isPolygonBulging(pointArr);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0,5,5));

        //on top
        checkTrapezium = new Trapezium(new Point(11.5,14.5),new Point(11.5,13.5),new Point(8.0,10.0),new Point(8.0,18.0));
        observer =new Point(12,14);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0,5,5));

        //somewhere
        checkTrapezium = new Trapezium(new Point(6.0,10.0),new Point(4.0,12.0),new Point(8.0,18.0),new Point(12.0,14.0));
        observer =new Point(0,6);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0,5,5));

    }

    @Test
    public void hexTest(){
        System.out.println("hexTest");
        //check value
        Trapezium [] trapeziums = new Trapezium[4];

        trapeziums[0] = new Trapezium(new Point(1.9961021884099057,8.17037663191509),new Point(3.2435897435897525,8.034188034188032),new Point(4.0,4.0),new Point(1.4835164835164836,4.274725274725275));//0
        trapeziums[1] = new Trapezium(new Point(1.9961021884099057,8.17037663191509),new Point(3.2435897435897525,8.034188034188032),new Point(4.0,4.0),new Point(1.4835164835164836,4.274725274725275));//1
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
            if(!testGenTrap(pointArr,observer,trapeziums[i],i,5,5)){
                fail(String.valueOf("fail on"+i));
            }
        }
    }

    @Test
    public void testPointBelong(){
        Point p1 = new Point(4,4),
                p2 = new Point(8,0),
                p3 = new Point(6,2);
        assertTrue(Utils.isPointBelongToStretch(p1,p2,p3));
    }

    @Test
    public void testCrossPoint(){
        Line line1 = Utils.createLine(new Point(3,6), new Point(8,6));
        Line line2 = Utils.createLine(new Point(7,8), new Point(3,4));
        Point crossPoint, crossPointExpected;
        crossPointExpected = new Point(5,6);
        crossPoint = Utils.getCrossPoint(line1,line2);
        assertTrue(Utils.same(crossPoint,crossPointExpected));
    }

    @Test
    public void testPointInsideArea(){
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 3));
        points.add(new Point(3, 6));
        points.add(new Point(7, 6));
        points.add(new Point(9, 3));
        Point[] pointArr = points.toArray(new Point[0]);
        Point observer = new Point(3,5.5);
        assertTrue(Utils.isPointInside(observer,pointArr));
        observer = new Point(3,7);
        assertFalse(Utils.isPointInside(observer,pointArr));
        observer = new Point(3,6);
        assertFalse(Utils.isPointInside(observer,pointArr));
    }

    @Test
    public void testPointOnBorder(){
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 3));
        points.add(new Point(3, 6));
        points.add(new Point(7, 6));
        points.add(new Point(9, 3));
        Point[] pointArr = points.toArray(new Point[0]);
        Point observer = new Point(3,6);
        assertTrue(Utils.isPointOnBorder(observer,pointArr));
        observer = new Point(3,7);
        assertFalse(Utils.isPointOnBorder(observer,pointArr));
        observer = new Point(3,5);
        assertFalse(Utils.isPointOnBorder(observer,pointArr));
    }

    boolean testGenTrap(Point[] pointsArr, Point observer, Trapezium trapezium ,int typeOfTrapGen, int numbOfNearestPoints, int numbOfFurtherPoints){

        Trapezium trapeziumCalculated = TrapeziumUtils.calculateTrapezium(pointsArr, observer,typeOfTrapGen);

        return  (Utils.same(trapezium.getArea(),trapeziumCalculated.getArea())
                &&(Utils.same(Utils.getDist(observer, trapezium.getNearLine()), Utils.getDist(observer, trapeziumCalculated.getNearLine())))
                &&(Utils.same(Utils.getDist(observer, trapezium.getFurtherLine()), Utils.getDist(observer, trapeziumCalculated.getFurtherLine())))
                );
    }
*/}