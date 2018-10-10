package mapa_trapezium;

import java.util.ArrayList;
import java.util.List;

public class TrapeziumUtils {
    private static Trapezium getBestTrapezium(Trapezium[] trapeziums) {
        if (trapeziums.length == 0) {
            return null;
        }
        Trapezium[] trap  = deleteNotCalibratedTrapezium(trapeziums);
        Trapezium bestTrapezium = trap[0];
        double bestArea = bestTrapezium.getArea();
        for (int i = 1; i < trap.length; i++) {
            if (trap[i].getArea() > bestArea) {
                bestArea = trap[i].getArea();
                bestTrapezium = trap[i];
            }
        }
        return bestTrapezium;
    }

    private static Trapezium[] deleteNotCalibratedTrapezium(Trapezium[] trapeziums){
        ArrayList<Trapezium> arr = new ArrayList<>();
        for (Trapezium trapezium: trapeziums) {
            if(trapezium.isValid()){
                arr.add(trapezium);
            }
        }
        return arr.toArray(new Trapezium[0]);
    }

    private static List<Trapezium> getTrapeziumsByDifferentPoints(Point[] points, Point observer, int numbNearestPoints,
                                                                  int numbFurtherPoints) {
        Position[] positions = Utils.getDist(observer, points);
        Utils.sortPositionByDist(positions);
        Point maxPos;
        Point minPos;
        Point totalMaxPos = positions[positions.length - 1].getPoint();
        Point totalMinPos = positions[0].getPoint();
        Point secondNearestPoint;
        Point secondFurtherPoint;
        List<Trapezium> list = new ArrayList<>();
        for (int i = 0; i < numbNearestPoints; i++) {
            for (int j = 0; j < numbFurtherPoints; j++) {
                maxPos = positions[positions.length - 1 - j].getPoint();
                minPos = positions[i].getPoint();
                secondNearestPoint = getNearestCrossPoint(points, maxPos, observer, totalMinPos,null);
                if (secondNearestPoint == null) {
                    continue;
                }
                secondFurtherPoint = getFurtherCrossPoint(points, minPos, observer, totalMaxPos,null);
                if (secondFurtherPoint == null) {
                    continue;
                }
                list.add(new Trapezium(minPos, secondNearestPoint, maxPos, secondFurtherPoint));
            }
        }
        return list;
    }

    private static List<Trapezium> getTrapeziumsByNearestPoints(Point[] points, Point observer, int numbNearestPoints) {
        Position[] positions = Utils.getDist(observer, points);
        Utils.sortPositionByDist(positions);
        Point minPos1, minPos2;
        Point totalMaxPos = positions[positions.length - 1].getPoint();
        Point furtherPoint1, furtherPoint2;
        List<Trapezium> list = new ArrayList<>();
        for (int i = 0; i < numbNearestPoints; i++) {
            minPos1 = positions[i].getPoint();
            furtherPoint1 = getFurtherCrossPoint(points, minPos1, observer, totalMaxPos, null);
            if (furtherPoint1 == null) {
                continue;
            }
            for (int j = 0; j < numbNearestPoints; j++) {
                if (i == j) {
                    continue;
                }
                minPos2 = positions[j].getPoint();
                furtherPoint2 = getFurtherCrossPoint(points, minPos2, observer, totalMaxPos,furtherPoint1);
                if (furtherPoint2 == null) {
                    continue;
                }
                list.add(new Trapezium(minPos1, minPos2, furtherPoint2, furtherPoint1));
            }
        }
        return list;
    }

    private static List<Trapezium> getTrapeziumsByFurtherPoints(Point[] points, Point observer, int numbFurtherPoints) {
        Position[] positions = Utils.getDist(observer, points);
        Utils.sortPositionByDist(positions);
        Point maxPos1, maxPos2;
        Point totalMinPos = positions[0].getPoint();
        Point nearestPoint1, nearestPoint2;
        List<Trapezium> list = new ArrayList<>();
        for (int i = 0; i < numbFurtherPoints; i++) {
            maxPos1 = positions[positions.length - 1 - i].getPoint();
            nearestPoint1 = getNearestCrossPoint(points, maxPos1, observer, totalMinPos,null);
            if (nearestPoint1 == null) {
                continue;
            }
            for (int j = 0; j < numbFurtherPoints; j++) {
                if (i == j) {
                    continue;
                }
                maxPos2 = positions[positions.length - 1 - j].getPoint();

                nearestPoint2 = getNearestCrossPoint(points, maxPos2, observer, totalMinPos,nearestPoint1);
                if (nearestPoint2 == null) {
                    continue;
                }
                list.add(new Trapezium(nearestPoint1, nearestPoint2, maxPos2, maxPos1));
            }
        }
        return list;
    }

    private static Point getNearestCrossPoint(Point[] points0, Point maxPoint, Point observer, Point totalMin,Point strikePoint) {
        Point [] points = new Point[points0.length+1];
        System.arraycopy(points0,0,points,0,points0.length);
        points[points0.length] = points0[0];
        Line[] lines = Utils.generateLines(points0);
        Line maxDistLine = Utils.createLine(observer, maxPoint);
        double maxDistance = Utils.getDist(maxPoint, observer);
        double totalMinDistance = Utils.getDist(totalMin, observer);
        if (maxDistLine == null) {
            return null;
        }
        //for all points except last one
        Point crossPoint, bestCrossPoint = maxPoint;
        double distance, bestCrossPointDist = maxDistance;
        for (int i = 0; i < points.length-1 ; i++) {
            if ((lines[i] != null)) {
                crossPoint = Utils.getCrossPoint(lines[i], maxDistLine);
                if(!Utils.isPointBelongToStretch(points[i],points[i+1],crossPoint)){//check that point on stretch
                    continue;
                }
                distance = Utils.getDist(observer, crossPoint);
                if (distance < maxDistance && distance > totalMinDistance) {
                    if (distance <= bestCrossPointDist) {// check best
                        if(strikePoint!=null&&Utils.same(crossPoint,strikePoint)){
                            continue;
                        }
                        bestCrossPoint = crossPoint;
                        bestCrossPointDist = distance;
                    }
                }
            }
        }
        if (Utils.same(bestCrossPoint.getY(),maxPoint.getY())&&Utils.same(bestCrossPoint.getX(),maxPoint.getX())) {
            return null;// there are no good points
        }
        return bestCrossPoint;
    }

    private static Point getFurtherCrossPoint(Point[] points0, Point minPoint, Point observer, Point totalMax, Point strikePoint) {
        Point [] points = new Point[points0.length+1];
        System.arraycopy(points0,0,points,0,points0.length);
        points[points0.length] = points0[0];
        Line[] lines = Utils.generateLines(points0);
        Line minDistLine = Utils.createLine(observer, minPoint);
        double minDistance = Utils.getDist(minPoint, observer);
        double totalMaxDistance = Utils.getDist(totalMax, observer);
        if (minDistLine == null) {
            return null;
        }
        Point crossPoint, bestCrossPoint = minPoint;
        double distance, bestCrossPointDist = minDistance;
        //for all points except last one
        for (int i = 0; i < points.length -1; i++) {
            if ((lines[i] != null)) {
                crossPoint = Utils.getCrossPoint(lines[i], minDistLine);
                if(!Utils.isPointBelongToStretch(points[i],points[i+1],crossPoint)){//check that point on stretch
                    continue;
                }
                distance = Utils.getDist(observer, crossPoint);
                if (distance > minDistance && distance < totalMaxDistance) {
                    if (distance >= bestCrossPointDist) {// check best
                        if(strikePoint!=null&&Utils.same(crossPoint,strikePoint)){
                            continue;
                        }
                        bestCrossPoint = crossPoint;
                        bestCrossPointDist = distance;
                    }
                }
            }
        }

        if (Utils.same(bestCrossPoint.getY(),minPoint.getY())&&Utils.same(bestCrossPoint.getX(),minPoint.getX())) {
            return null;// there are no good points
        }
        return bestCrossPoint;

    }

    /**
     *
     * @param points point array
     * @param observer outer point
     //* @param numbNearestPoints numb of nearest points witch will tried in combinations
     //* @param numbFurtherPoints numb of further points witch will tried in combinations
     * @param typeOfTrapeziumGeneration 0 - all types
     *                                  1 - one nearest, one further
     *                                  2 - two nearest
     *                                  3 - two further
     * @return trapezium with max area
     */
    public  static Trapezium  calculateTrapezium(Point[] points, Point observer, int typeOfTrapeziumGeneration) {
        Point[] pointsUpdated = points;
        // edited array
        if(pointsUpdated.length<=5) {
            pointsUpdated = Utils.addMiddlePoints(pointsUpdated);
            pointsUpdated = Utils.addMiddlePoints(pointsUpdated);
        }
            pointsUpdated = Utils.addMiddlePoints(pointsUpdated);

        // create positions(points with distance)
        Position[] positions = Utils.getDist(observer, pointsUpdated);
        Utils.sortPositionByDist(positions);
        // create Trapezium
        List<Trapezium> list = new ArrayList<>();
        switch (typeOfTrapeziumGeneration) {
            case 1:
                list.addAll(getTrapeziumsByDifferentPoints(pointsUpdated, observer, pointsUpdated.length/2, pointsUpdated.length/2));
                break;
            case 2:
                list.addAll(getTrapeziumsByNearestPoints(pointsUpdated, observer, pointsUpdated.length));
                break;
            case 3:
                list.addAll(getTrapeziumsByFurtherPoints(pointsUpdated, observer, pointsUpdated.length));
                break;
            default:
                list.addAll(getTrapeziumsByDifferentPoints(pointsUpdated, observer, pointsUpdated.length/2, pointsUpdated.length/2));
                list.addAll(getTrapeziumsByNearestPoints(pointsUpdated, observer,pointsUpdated.length));
                list.addAll(getTrapeziumsByFurtherPoints(pointsUpdated, observer, pointsUpdated.length));
                break;

        }
        //System.out.println(list.size());
        return getBestTrapezium(list.toArray(new Trapezium[0]));
    }

}
