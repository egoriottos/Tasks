public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(4);
        System.out.println(circle.getArea());
        System.out.println(circle.getPerimeter());
        Rectangle rectangle = new Rectangle(3,7);
        System.out.println(rectangle.getArea());
        System.out.println(rectangle.getPerimeter());
        Triangle triangle = new Triangle(5,3,8,4);
        System.out.println(triangle.getArea());
        System.out.println(triangle.getPerimeter());
        Cube cube = new Cube(12);
        System.out.println(cube.calculateVolume());
        System.out.println(cube.calculateSurfaceArea());
    }
}
