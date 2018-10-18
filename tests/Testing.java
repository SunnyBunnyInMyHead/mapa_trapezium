import mapa_trapezium.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class Testing {

    @Test
    public void rhombusTest(){
        System.out.println("rhombusTest");
        Point observer = new Point(4,4);
        Trapezium checkTrapezium = new Trapezium(new Point(7.0,7.0),new Point(6.0,10.0),new Point(7.0,13.0),new Point(10.0,10.0),observer);// on diagonal
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(6, 10));
        points.add(new Point(8, 16));
        points.add(new Point(10, 10));
        points.add(new Point(8, 4));
        Point[] pointArr = points.toArray(new Point[0]);
        assert Utils.isPolygonBulging(pointArr);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0));

        //side
        observer =new Point(2,2);
        checkTrapezium = new Trapezium(new Point(6.25,9.25),new Point(7.0,6.999999999999999),new Point(10.0,10.0),new Point(8.799999999999999,13.6),observer);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0));
    }

    @Test
    public void trapeziumTest(){
        System.out.println("trapeziumTest");
        Point observer = new Point(5, 12);
        Trapezium checkTrapezium = new Trapezium(new Point(3.0,6.0),new Point(7.0,6.0),new Point(8.0,3.0),new Point(2.0,3.0),observer);//up
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 3));
        points.add(new Point(3, 6));
        points.add(new Point(7, 6));
        points.add(new Point(9, 3));
        Point[] pointArr = points.toArray(new Point[0]);
        assert Utils.isPolygonBulging(pointArr);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0));
        observer =new Point(5,4);
        checkTrapezium = new Trapezium(new Point(6.0,3.0),new Point(4.0,3.0),new Point(7.0,6.0),new Point(3.0,6.0),observer);//center
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0));
        //up cross lines
        observer =new Point(5,9);
        checkTrapezium = new Trapezium(new Point(3.0,6.0),new Point(7.0,6.0),new Point(9.0,3.0),new Point(1.0,3.0),observer);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0));
        //under big base
        observer =new Point(5,0);
        checkTrapezium = new Trapezium(new Point(6.0,3.0),new Point(4.0,3.0),new Point(3.0,6.0),new Point(7.0,6.0),observer);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0));
        //on diagonal
        observer =new Point(11,0);
        checkTrapezium = new Trapezium(new Point(7.0,3.0),new Point(4.2727272727272725,3.0),new Point(1.75,4.125),new Point(3.0,6.0),observer);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0));
        //up corner
        observer =new Point(7, 6);
        checkTrapezium = new Trapezium(new Point(7.25,5.625),new Point(7.0,6.0),new Point(1.0,3.0),new Point(9.0,3.0),observer);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0));
    }

    @Test
    public void triangleTest(){
        System.out.println("triangleTest");
        Point observer = new Point(7,15);
        Trapezium checkTrapezium = new Trapezium(new Point(5.800000000000001,5.4),new Point(8.2,5.4),new Point(8.5,3.0),new Point(5.5,3.0),observer);//up
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(4, 3));
        points.add(new Point(7, 7));
        points.add(new Point(10, 3));
        Point[] pointArr = points.toArray(new Point[0]);
        assert Utils.isPolygonBulging(pointArr);
        assertTrue("//up",testGenTrap(pointArr,observer,checkTrapezium,0));

        //under big base
        observer =new Point(7,0);
        checkTrapezium = new Trapezium(new Point(7.9,3.0),new Point(6.1,3.0),new Point(5.5,5.0),new Point(8.5,5.0),observer);
        assertTrue("//under big base",testGenTrap(pointArr,observer,checkTrapezium,0));

        //on diagonal
        observer =new Point(13,-1);
        checkTrapezium = new Trapezium(new Point(10.0,3.0),new Point(7.2727272727272725,3.0),new Point(5.125,4.5),new Point(7.0,7.0),observer);
        assertTrue("//on diagonal",testGenTrap(pointArr,observer,checkTrapezium,0));

        //somewhere near side diagonal
        observer =new Point(10.75, 6.5);
        checkTrapezium = new Trapezium(new Point(7.970588235294118,5.705882352941177),new Point(8.764705882352942,4.647058823529412),new Point(7.0,3.0),new Point(5.5,5.0),observer);
        assertTrue("//somewhere near side diagonal",testGenTrap(pointArr,observer,checkTrapezium,0));
    }

    @Test
    public void pentagonTest(){
        System.out.println("pentagonTest");
        Point observer = new Point(18,14);
        Trapezium checkTrapezium = new Trapezium(new Point(9.600000000000001,16.4),new Point(9.6,11.6),new Point(4.0,10.0),new Point(4.0,18.0),observer);//up
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(4, 10));
        points.add(new Point(4, 18));
        points.add(new Point(8, 18));
        points.add(new Point(12, 14));
        points.add(new Point(8, 10));
        Point[] pointArr = points.toArray(new Point[0]);
        assert Utils.isPolygonBulging(pointArr);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0));

        //on top
        observer =new Point(12,14);
        checkTrapezium = new Trapezium(new Point(11.5,14.5),new Point(12.0,14.0),new Point(4.0,10.0),new Point(8.0,18.0),observer);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0));

        //somewhere
        observer =new Point(0,6);
        checkTrapezium = new Trapezium(new Point(4.0,12.0),new Point(6.0,10.0),new Point(12.0,14.0),new Point(8.0,18.0),observer);
        assertTrue(testGenTrap(pointArr,observer,checkTrapezium,0));

    }

    @Test
    public void hexTest(){
        System.out.println("hexTest");
        //check value
        Trapezium [] trapeziums = new Trapezium[4];
        Point observer = new Point(2.5, 12);
        trapeziums[0] = new Trapezium(new Point(2.0,8.2),new Point(3.2435897435897525,8.034188034188032),new Point(4.0,4.0),new Point(1.4835164835164836,4.274725274725275),observer);//0
        trapeziums[1] = new Trapezium(new Point(2.0,8.2),new Point(3.2435897435897525,8.034188034188032),new Point(4.0,4.0),new Point(1.4835164835164836,4.274725274725275),observer);//1
        trapeziums[2] = new Trapezium(new Point(2.0,8.2),new Point(3.5,8.0),new Point(4.25,5.0),new Point(1.4835164835164836,4.274725274725275),observer);//2
        trapeziums[3] = new Trapezium(new Point(3.2435897435897525,8.034188034188032),new Point(2.009513742071885,8.19873150105708),new Point(1.5,4.25),new Point(4.0,4.0),observer);//3


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
            if(!testGenTrap(pointArr,observer,trapeziums[i],i)){
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
        assertTrue(Utils.isPointOnTop(observer,pointArr));
        observer = new Point(3,7);
        assertFalse(Utils.isPointOnTop(observer,pointArr));
        observer = new Point(3,5);
        assertFalse(Utils.isPointOnTop(observer,pointArr));
    }

    boolean testGenTrap(Point[] pointsArr, Point observer, Trapezium trapezium ,int typeOfTrapGen){

        Trapezium trapeziumCalculated = TrapeziumUtils.calculateTrapezium(pointsArr, observer,typeOfTrapGen);

        return  (Utils.same(trapezium.getArea(),trapeziumCalculated.getArea())
                &&(Utils.same(Utils.getDist(observer, trapezium.getNearLine()), Utils.getDist(observer, trapeziumCalculated.getNearLine())))
                &&(Utils.same(Utils.getDist(observer, trapezium.getFurtherLine()), Utils.getDist(observer, trapeziumCalculated.getFurtherLine())))
                );
    }
}