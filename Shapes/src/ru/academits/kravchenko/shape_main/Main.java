package ru.academits.kravchenko.shape_main;

import ru.academits.kravchenko.shape.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static Comparator<Shape> areaComparator = new ShapeAreaComparator();
    private static Comparator<Shape> perimeterComparator = new ShapePerimeterComparator();

    private static Shape getMaxAreaShape(Shape[] shapes) {
        Arrays.sort(shapes, areaComparator);

        return shapes[shapes.length - 1];
    }

    private static Shape getSecondPerimeterShape(Shape[] shapes) {
        Arrays.sort(shapes, perimeterComparator);

        return shapes[shapes.length - 2];
    }

    private static void printComparisonResult(Shape shape1, Shape shape2) {
        System.out.println("    ФИГУРЫ");
        System.out.println(shape1.toString());
        System.out.println(shape2.toString());
        System.out.println("Хэш-код 1:" + shape1.hashCode());
        System.out.println("Хэш-код 2:" + shape2.hashCode());

        if (shape1.equals(shape2)) {
            System.out.println("    РАВНЫ");
            System.out.println();
        } else {
            System.out.println("    НЕ РАВНЫ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Shape[] shapes = new Shape[]{new Circle(1.2), new Circle(0.9),
                new Square(1.34), new Square(2),
                new Rectangle(0.95, 2.11), new Rectangle(1.75, 1.6), new Rectangle(2.11, 0.95),
                new Triangle(1.5, 1, 2.5, 3, 3.5, 0), new Triangle(1.1, 2.3, 3.8, 4, 4.1, 1.9)};

        for (Shape shape : shapes) {
            System.out.print(shape.toString());
            System.out.printf(" (ширина: %.2f; ", shape.getWidth());
            System.out.printf("высота: %.2f; ", shape.getHeight());
            System.out.printf("площадь: %.2f; ", shape.getArea());
            System.out.printf("периметр: %.2f)%n", shape.getPerimeter());
        }

        System.out.println();

        for (int i = 0; i < shapes.length - 1; i++) {
            printComparisonResult(shapes[i], shapes[i + 1]);
        }

        System.out.print("Фигура с максимальной площадью это - ");
        System.out.println(getMaxAreaShape(shapes).toString());

        System.out.print("Фигура со вторым по величине периметром это - ");
        System.out.println(getSecondPerimeterShape(shapes).toString());
        System.out.println();
    }
}