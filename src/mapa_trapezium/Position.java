package mapa_trapezium;

class Position {
	private final Point point;
	private final double dist;

	Position(Point point, double dist) {
		this.point = point;
		this.dist = dist;
	}

	Point getPoint() {
		return point;
	}

	double getDist() {
		return dist;
	}

}
