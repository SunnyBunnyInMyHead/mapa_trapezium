package mapa_trapezium;

public class Trapezium {
	private Point nearestMain;
	private Point nearestAdd;
	private final Point furtherMain;
	private final Point furtherAdd;
	private double area = 0.0;

	public Trapezium(Point nearestMain, Point nearestAdd, Point furtherMain, Point furtherAdd) {
		this.nearestMain = nearestMain;
		this.nearestAdd = nearestAdd;
		this.furtherMain = furtherMain;
		this.furtherAdd = furtherAdd;
		if (nearestMain != null && nearestAdd != null && furtherMain != null && furtherAdd != null) {
			calibrate();
			area = calcArea(this.nearestMain, this.nearestAdd, this.furtherMain, this.furtherAdd);
		}

	}

	private void calibrate() {
		Line nearest = Utils.createLine(nearestAdd, nearestMain);
		Line further = Utils.createLine(furtherAdd, furtherMain);
		if ((nearest==null|| further==null)&&Utils.isLineParallel(nearest, further)) {
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

}
