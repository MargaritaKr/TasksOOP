package ru.academits.kravchenko.shapes_main;

import ru.academits.kravchenko.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private final static double EPSILON = 1.0e-10;
    private static Comparator<? super Shapes.Shape> areaComparator = new ShapeAreaComparator();
    private static Comparator<? super Shapes.Shape> perimeterComparator = new ShapePerimeterComparator();

    private static Shapes.Shape getMaxAreaShape(Shapes.Shape[] shapes) {
        Arrays.sort(shapes, areaComparator);

        return shapes[shapes.length - 1];
    }

    private static Shapes.Shape getSecondPerimeterShape(Shapes.Shape[] shapes) {
        Arrays.sort(shapes, perimeterComparator);

        return shapes[shapes.length - 2];
    }

    private static void printCompare(Shapes.Shape firstShape, Shapes.Shape secondShape) {
        System.out.println("    ФИГУРЫ");
        System.out.println(firstShape.toString());
        System.out.println(secondShape.toString());

        if (firstShape.equals(secondShape)) {
            System.out.println("    РАВНЫ");
            System.out.println();
        } else {
            System.out.println("    НЕ РАВНЫ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Shapes.Shape[] shapes = new Shapes.Shape[10];

        shapes[0] = new Circle(1.2);
        shapes[1] = new Circle(0.9);

        shapes[2] = new Square(1.34);
        shapes[3] = new Square(2);

        shapes[4] = new Rectangle(0.95, 2.11);
        shapes[5] = new Rectangle(2.11, 0.95);
        shapes[6] = new Rectangle(1.75, 1.6);

        shapes[7] = new Triangle(1.5, 1, 2.5, 3, 3.5, 0);
        shapes[8] = new Triangle(3.4, 0, 4.4, 3, 5.4, 1);
        shapes[9] = new Triangle(1.1, 2.3, 3.8, 4, 4.1, 1.9);

        for (ru.academits.kravchenko.shapes.Shapes.Shape shape : shapes) {
            System.out.print(shape.toString());
            System.out.printf(" (Площадь фигуры: %.2f, ", shape.getArea());
            System.out.printf("Периметр фигуры: %.2f)%n", shape.getPerimeter());
        }

        System.out.println();

        for (int i = 0; i < shapes.length - 1; i++) {
            printCompare(shapes[i], shapes[i + 1]);
        }

        System.out.print("Фигура с максимальной площадью это - ");
        System.out.println(getMaxAreaShape(shapes).toString());

        System.out.print("Фигура со вторым по величине периметром это - ");
        System.out.println(getSecondPerimeterShape(shapes).toString());
        System.out.println();
    }

    static class ShapeAreaComparator implements Comparator<Shapes.Shape> {
        @Override
        public int compare(Shapes.Shape firstShape, Shapes.Shape secondShape) {
            return Double.compare(firstShape.getArea() - secondShape.getArea(), EPSILON);
        }
    }

    static class ShapePerimeterComparator implements Comparator<Shapes.Shape> {
        @Override
        public int compare(Shapes.Shape firstShape, Shapes.Shape secondShape) {
            return Double.compare(firstShape.getPerimeter() - secondShape.getPerimeter(), EPSILON);
        }
    }
}

