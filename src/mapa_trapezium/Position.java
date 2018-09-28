package mapa_trapezium;

class Position {
	private final Point point;
	private final double dist;

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
