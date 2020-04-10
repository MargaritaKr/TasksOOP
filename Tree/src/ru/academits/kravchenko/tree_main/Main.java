package ru.academits.kravchenko.tree_main;

import ru.academits.kravchenko.tree.Tree;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        System.out.println("Тест класса Tree");
        System.out.println();

        Tree<Integer> numbers = new Tree<>();

        numbers.addList(new Integer[]{8, 3, 10, 12, 11, 17, 2, 14, 6, 1, 4, 5, 7, 6, null});
        numbers.addList(null);

        Tree<String> strings = new Tree<>();

        strings.addList(new String[]{"Россия", "Бельгия", "Германия", "Белоруссия", "Франция", "Финляндия",
                "Россия", "Французская полинезия", "Тунис", null});

        System.out.println("Создаем два списка разных типов - ");

        System.out.println("Числа: " + numbers + ", размер: " + numbers.getSize());
        System.out.println("Строки: " + strings + ", размер: " + strings.getSize());
        System.out.println();

        numbers.add(9);

        System.out.println("Добавим в числа число 9: " + numbers);
        System.out.println();

        System.out.println("Оно встроилось в дерево: ");
        System.out.println(numbers.printTree());
        System.out.println();

        System.out.println("Проверим, содержит ли дерево значение \"null\" : " + numbers.contains(null));
        System.out.println();

        System.out.println("Удалим \"null\" : " + numbers.remove(null));
        System.out.println("Распечатаем новый список: " + numbers);
        System.out.println();

        System.out.println("ВЫПОЛНИМ ОБХОДЫ ДЕРЕВА С ПЕЧАТЬЮ ЭЛЕМЕНТОВ");
        System.out.println();

        System.out.println("Обход в ширину: ");
        strings.wideVisit(s -> {
            if (s != null) {
                System.out.println("Государство - " + s);
            }
        });
        System.out.println();

        System.out.println("Обход в глубину: ");
        strings.inDepthVisit(s -> {
            if (s != null) {
                System.out.println("Государство - " + s);
            }
        });
        System.out.println();

        System.out.println("Обход с рекурсией: ");
        System.out.println("Распечатаем квадраты только честных чисел из дерева: ");

        numbers.recursiveVisit(n -> System.out.println((n % 2 == 0) ? n + " ^ 2 = " + n * n : n + " - нечётное"));
        System.out.println();


        Tree<Integer> numbers2 = new Tree<>(Comparator.naturalOrder());
        System.out.println(numbers2);
        System.out.println(numbers2.comparator());

    }
}
