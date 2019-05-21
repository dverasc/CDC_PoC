// Diego Veras, November 7, 2018, CGS3416
import java.util.Scanner;

public class TestShapes {

public static void main(String[] args) {

   
    System.out.println("Enter the number of shapes: ");
    Scanner numberin = new Scanner(System.in);
    int n =numberin.nextInt();
    
    Shape[] shapeArray = new Shape[n];
    for (int i=0; i<n;i++) {
        System.out.println("Enter the choice (Square, Rectangle or Circle): ");
        int choice = numberin.nextInt();
        //
        Scanner text = new Scanner(System.in);
        System.out.println("Enter the color:");
        String color = text.nextLine();
        //Square = 1
        if (choice == 1) {
            System.out.println("Enter the side length of the square: ");
            double sideLength = numberin.nextDouble();
            Shape theSquare = new Square(sideLength,color);
            shapeArray[i]=theSquare;

        }
        //Rectangle=2
        if (choice == 2) {
            System.out.println("Enter the length of the rectangle:");
            double length = numberin.nextDouble();
            //
            System.out.println("Enter the width of the rectangle:");
            double width = numberin.nextDouble();
            //
            Shape theRectangle = new Rectangle(length,width,color);
            shapeArray[i]=theRectangle;

        }
        //Circle =3
        if (choice == 3) {
            System.out.println("Enter the radius of the circle:");
            double radius = numberin.nextDouble();
            //
            Shape theCircle = new Circle(radius,color);
            shapeArray[i]=theCircle;

        }
    }
    //print the array of objects
    for (int i=0; i <shapeArray.length;i++){
        System.out.println("Shape: " + (i+1));
        shapeArray[i].print();
    }
}

}
abstract class Shape {
     //attributes
     private String color;
     //default constructor
     public Shape() {
        this.setColor("red");
     }
     //accessor for color
     public String getColor() {
         return color;
     }
     //mutator for color
     public void setColor(String color) {
         this.color = color;
     }
     //method for print
     public void print() {
        System.out.println("Color: " + this.getColor());
     }
     //abstract method for area
     public abstract double area();
}
//class Square:
class Square extends  Shape{
    //attributes
    private double sideLength;
    //default constructor
    public Square(){
        this.setSideLength(1.0);
    }
    //accessor for SideLength
    public double getSideLength() {
        return sideLength;
    }
    //mutator for SideLength
    public void setSideLength(Double sideLength) {
        this.sideLength = sideLength;
    }

    //parametrized constructor for Square
    public Square(double sideLength, String color){
        super.setColor(color);
        this.setSideLength(sideLength);
    }
    //overrides for area
    @Override
    public double area(){
        return this.getSideLength() * this.getSideLength();
    }
    //overrides for print
    @Override
    public void print() {
        super.print();
        System.out.println("Side Length: " + this.getSideLength());
        System.out.println("Area : " + this.area());

    }
}
//Class Rectangle:
class Rectangle extends  Shape{
    //attributes
    private double length;
    private double width;

    //accessor for length
    public double getLength() {
        return length;
    }
    //mutator for length
    public void setLength(double length) {
        this.length = length;
    }
    //accessor for width
    public double getWidth() {
        return width;
    }
    //mutator for width
    public void setWidth(double width) {
        this.width = width;
    }

    //default constructor
    public Rectangle(){
        this.setLength(1.0);
        this.setWidth(1.0);
    }
    //parametrized constructor for Rectangle
    public Rectangle(double length, double width,  String color){
        super.setColor(color);
        this.setLength(length);
        this.setWidth(width);
    }
    //override for area
    @Override
    public double area(){
        return this.getLength() * this.getWidth();
    }
    //override for print
    @Override
    public void print() {
        super.print();
        System.out.println("Length: " + this.getLength());
        System.out.println("Width: " + this.getWidth());
        System.out.println("Area : " + this.area());

    }
}
//Class Circle:

class Circle extends  Shape{
    //attributes
    private double radius;
    //accessor for radius
    public double getRadius() {
        return radius;
    }
    //mutator for radius
    public void setRadius(double radius) {
        this.radius = radius;
    }
    //default constructor
    public Circle(){
        this.setRadius(1.0);
    }
    //parametrized constructor for Circle
    public Circle(double radius,  String color){
        super.setColor(color);
        this.setRadius(radius);

    }

    //override for area
    @Override
    public double area(){
        return  Math.PI * this.getRadius() * this.getRadius();
    }
    //override for print
    @Override
    public void print() {
        super.print();
        System.out.println("Radius: " + this.getRadius());
        System.out.println("Area : " + this.area());

    }
}



    
