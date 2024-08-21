public class Circle {
    private final int radius;
    private static final double PI = 3.14;

    public int getRadius() {
        return radius;
    }

    public Circle(int radius) {
        this.radius = radius;
    }

    public double getPerimeter(){
        return radius * 2 * PI;
    }

    public double getArea(){
        int d = radius * radius;
        return (PI *d *d)/4;
    }
}
