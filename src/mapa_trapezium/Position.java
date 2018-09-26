package mapa_trapezium;

public class Position {
	private Point point;
	private double dist;

	public Position(Point point, double dist) {
		this.point = point;
		this.dist = dist;
	}

	public Point getPoint() {
		return point;
	}

	public double getDist() {
		return dist;
	}

}
