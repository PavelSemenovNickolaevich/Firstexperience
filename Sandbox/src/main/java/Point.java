public class Point {
    public double x1;
    public double y1;
    public double x2;
    public double y2;

    public Point (double x1, double y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public double distance(double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - x1));
    }
}