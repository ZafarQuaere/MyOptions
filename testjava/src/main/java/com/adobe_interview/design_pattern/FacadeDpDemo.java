package com.adobe_interview.design_pattern;

/*
Facade pattern hides the complexities of the system and provides an interface to the client using which the client can access the system.
This type of design pattern comes under structural pattern as this pattern adds an interface to existing system to hide its complexities.
This pattern involves a single class which provides simplified methods required by client and delegates calls to methods of existing system classes.
*/

interface MyShape{
     void draw();
}

class Rectangles implements MyShape{

    @Override
    public void draw() {
        System.out.println("Rectangles draw()");
    }
}

class Squares implements MyShape{

    @Override
    public void draw() {
        System.out.println("Squares draw()");
    }
}

class Circles implements MyShape{

    @Override
    public void draw() {
        System.out.println("Circles draw()");
    }
}

class ShapeMaker{
    private MyShape circle;
    private MyShape square;
    private MyShape rectangle;

     ShapeMaker(){
         circle = new Circles();
         square = new Squares();
         rectangle = new Rectangles();
     }

    void drawCircle() {
         circle.draw();
    }

    void drawRectangle() {
        rectangle.draw();
    }

    void drawSquare() {
        square.draw();
    }
}


public class FacadeDpDemo {
    public static void main(String[] arg){
        ShapeMaker shapeMaker = new ShapeMaker();
        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
