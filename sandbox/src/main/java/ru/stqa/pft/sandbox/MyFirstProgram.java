package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String [] args) {
        hello("world");
        hello("user");
        hello("Irina");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle (4,6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

        Point p1 = new Point (6,7);
        Point p2 = new Point (8,9);
        System.out.println("Расстояние между точкой с координатами "+ p1.x + " и " + p1.y + " и второй точкой с координатами " + p2.x + "  и " + p2.y + " = " + p1.distance(p2));
    }

    public static void hello (String somebody) {
        System.out.println("Hello," + somebody + "!");
    }


}