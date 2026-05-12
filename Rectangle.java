// File Name: Rectangle.java
// Student Starter Code with TODOs

public class Rectangle {

    // TODO 1: Declare two private double attributes: width and height.
    private double width;
    private double height;

    // TODO 2: Create a constructor that accepts width and height as parameters.
    public Rectangle(double width, double height){
        this.width = width;
        this.height = height;
    }

    // TODO 3: Create a method named calculateArea() that returns width * height.
    public double calculateArea(){
        return width * height;
    }

    // TODO 4: Create a method named calculatePerimeter() that returns 2 * (width + height).
    public double calculatePerimeter(){
        return 2 * (width + height);
    }

    public static void main(String[] args) {

        // TODO 5: Create a Rectangle object with sample width and height values.
        Rectangle rectangle = new Rectangle(  8.0,  5.0);

        // TODO 6: Print the rectangle's width, height, area, and perimeter.
        System.out.println("Rectangle Width:" + rectangle.width);
        System.out.println("Rectangle Height:" + rectangle.height);

        System.out.println("Area:" + rectangle.calculateArea() );
        System.out.println("Perimeter:" + rectangle.calculatePerimeter());

    }
}

