public class Triangle {
    private final double a;
    private final double b;
    private final double c;
    private final double h;

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getH() {
        return h;
    }

    public Triangle(double a, double b, double c, double h) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.h = h;
    }

    public double getPerimeter(){
        return a + b + c;
    }

    public double getArea(){
        return (a*h)/2;
    }
}
