package mapa_trapezium;

public class Trapezium {
	private Point nearestMain;
	private Point nearestAdd;
	private final Point furtherMain;
	private final Point furtherAdd;
	private double area = 0.0;
    private boolean valid = false;
    private double minDis;
    private double maxDist;


	public Trapezium(Point nearestMain, Point nearestAdd, Point furtherMain, Point furtherAdd, Point outer) {
		this.nearestMain = nearestMain;
		this.nearestAdd = nearestAdd;
		this.furtherMain = furtherMain;
		this.furtherAdd = furtherAdd;
		validate();
		if (valid) {
			calibrate();
            setDistancePoint(outer);
			area = calcArea(this.nearestMain, this.nearestAdd, this.furtherMain, this.furtherAdd);
		}

	}

	private void validate(){
		valid = nearestMain != null && nearestAdd != null && furtherMain != null && furtherAdd != null
				&& !Utils.same(nearestMain, nearestAdd) && !Utils.same(furtherMain, furtherAdd)
				&&Utils.createLine(nearestAdd, nearestMain)!=null&&Utils.createLine(furtherAdd, furtherMain)!=null;

	}

	private void calibrate() {
		Line nearest = Utils.createLine(nearestAdd, nearestMain);
		Line further = Utils.createLine(furtherAdd, furtherMain);
		if (Utils.isLineParallel(nearest, further)){
		    return;
        }
		double distMain = Utils.getDist(nearestMain, further);
		double distAdd = Utils.getDist(nearestAdd, further);
		Line newNearestLine, sideLine;
		Point newPoint;
		if (distMain < distAdd) {
			newNearestLine = Utils.getParallelLine(further, nearestMain);
			sideLine = Utils.createLine(furtherMain, nearestAdd);
			if (sideLine!=null){
				newPoint = Utils.getCrossPoint(newNearestLine, sideLine);
				nearestAdd = newPoint;
			}

		} else {
			newNearestLine = Utils.getParallelLine(further, nearestAdd);
			sideLine = Utils.createLine(furtherAdd, nearestMain);
			if (sideLine!=null){
				newPoint = Utils.getCrossPoint(newNearestLine, sideLine);
				nearestMain = newPoint;
			}
		}
	}

    public boolean isValid() {
        return valid;
    }

    public double getArea() {
		return area;
	}

	private double calcArea(double sideA, double sideB, double sideC, double sideD) {
		double p = (sideA + sideB + sideC + sideD) / 2;
		return Math.sqrt((p - sideA) * (p - sideB) * (p - sideC) * (p - sideD));

	}

	private double calcArea(Point nearestMain, Point nearestAdd, Point furtherMain, Point furtherAdd) {
		double sideA, sideB, sideC, sideD;
		sideA = Utils.getDist(nearestMain, furtherAdd);
		sideB = Utils.getDist(nearestMain, nearestAdd);
		sideC = Utils.getDist(nearestAdd, furtherMain);
		sideD = Utils.getDist(furtherMain, furtherAdd);
		return calcArea(sideA, sideB, sideC, sideD);
	}

	public Point[] getPoints() {
		return new Point[] { nearestMain, nearestAdd, furtherMain, furtherAdd };
	}

	public Line getNearLine() {
		return Utils.createLine(nearestMain, nearestAdd);
	}

	public Line getFurtherLine() {
		return Utils.createLine(furtherMain, furtherAdd);
	}

	public double findSektorArea(){
        Line line1 = Utils.createLine(nearestAdd, furtherMain);
        Line line2 = Utils.createLine(nearestMain, furtherAdd);
        double angle = Utils.getAngle(line1,line2);
	    return Math.PI*(angle/360)*((maxDist*maxDist)-(minDis*minDis));
    }

    private void setDistancePoint(Point outer){
        minDis = Utils.getDist(outer, nearestMain)> Utils.getDist(outer,nearestAdd)?
                Utils.getDist(outer, nearestMain):Utils.getDist(outer,nearestAdd);
        maxDist = Utils.getMin(Utils.getDist(furtherMain,outer),Utils.getDist(furtherAdd,outer),
                Utils.getDist(outer, Utils.createLine(furtherAdd,furtherMain)));
    }
}
