package com.adobe_interview.design_pattern;


interface Shape {
    public void draw();
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Rectangle Draw()");
    }
}

class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Square Draw()");
    }
}

class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Circle Draw()");
    }
}


class ShapeFactory {

    //here comes the factory pattern
    public Shape getShape(String shapeType) {
        if (shapeType == null)
            return null;
        if (shapeType.equalsIgnoreCase("CIRCLE"))
            return new Circle();

        else if (shapeType.equalsIgnoreCase("RECTANGLE"))
            return new Rectangle();

        else if (shapeType.equalsIgnoreCase("SQUARE"))
            return new Square();


        return null;
    }
}

public class FactoryDpDemo {
    public static void main(String[] arg) {

        ShapeFactory factory = new ShapeFactory();

        Shape shape1 = factory.getShape("circle");
        shape1.draw();

        Shape shape2 = factory.getShape("rectangle");
        shape2.draw();

        Shape shape3 = factory.getShape("square");
        shape3.draw();

    }
}
