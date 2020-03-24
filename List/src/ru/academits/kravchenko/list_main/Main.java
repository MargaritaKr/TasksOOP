package ru.academits.kravchenko.list_main;

import ru.academits.kravchenko.list.ListItem;
import ru.academits.kravchenko.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Тест класса SinglyLinkedList");
        System.out.println();

        SinglyLinkedList<Integer> numbers = new SinglyLinkedList<>();

        for (int i = 1; i <= 10; i++) {
            numbers.add(numbers.getSize(), i);
        }

        SinglyLinkedList<String> strings = new SinglyLinkedList<>();

        for (int i = 1; i <= 10; i++) {
            strings.add(strings.getSize(), "строка" + i);
        }

        System.out.println("Создаем два списка разных типов - ");
        System.out.println("Числа: " + numbers);
        System.out.println("Строки: " + strings);
        System.out.println();

        System.out.println("Первый элемент \"Чисел\": " + numbers.getFirst());
        System.out.println("Пятый элемент \"Строк\": " + strings.getData(5));
        System.out.println();

        System.out.println("Размер списка \"Строк\": " + strings.getSize());
        strings.addToStart("строка0");
        System.out.println("Добавим в начало еще один элемент: " + strings);
        System.out.println("Теперь его размер: " + strings.getSize());
        System.out.println();

        System.out.println("Заменим элемент - " + strings.setData(10, "последняя строка") + ": " + strings);
        System.out.println();

        System.out.println("Удалим первый элемент - " + strings.deleteFirst() + ": " + strings);
        System.out.println();

        System.out.println("Удалим четверный элемент - " + strings.delete(3) + ": " + strings);
        System.out.println();

        numbers.setData(2, null);
        numbers.setData(3, null);
        numbers.setData(7, null);

        System.out.println("Заменим на null три элемента  \"Чисел\": " + numbers);
        System.out.println();

        numbers.deleteFirstEntrance(null);
        System.out.println("Удалим певое вхождение null: " + numbers);

        numbers.deleteAllEntrances(null);
        System.out.println("И все остальные тоже: " + numbers);

        System.out.println("Удалим певое вхождение null еще раз: " + numbers.deleteFirstEntrance(null));
        System.out.println("И все вхождения числа \"1\": " + numbers.deleteAllEntrances(1));
        System.out.println();

        SinglyLinkedList<String> strings2 = strings.copy();

        System.out.println("Скопируем массив строк: " + strings2);
        System.out.println("Это отдельный массив новых объектов,");

        strings.flip();

        System.out.println("так как после разворота первого массива: " + strings);
        System.out.println("второй остался без изменений: " + strings2);
        System.out.println();
        System.out.println();

        ListItem<Integer> withoutLink = new ListItem<>(4);
        System.out.println(withoutLink);

        System.out.println();
        System.out.println();
    }
}
