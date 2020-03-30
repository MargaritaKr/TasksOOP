package ru.academits.kravchenko.arrayList_main;

import ru.academits.kravchenko.arrayList.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Тест класса ArrayList");
        System.out.println();

        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }

        ArrayList<String> strings = new ArrayList<>(10);

        for (int i = 1; i <= 10; i++) {
            strings.add(strings.size(), "строка" + i);
        }

        strings.ensureCapacity(3);
        strings.trimToSize();

        System.out.println("Создаем два списка разных типов - ");
        System.out.println("Числа: " + numbers);
        System.out.println("Строки: " + strings);
        System.out.println();

        System.out.println("Проверим пуст ли список строк: " + strings.isEmpty());
        strings.clear();
        System.out.println("Очистим список строк: " + strings);
        System.out.println("Проверим пуст ли он, еще раз: " + strings.isEmpty());
        System.out.println();

        System.out.println("Первый элемент \"Чисел\": " + numbers.get(0));
        System.out.println("Размер списка \"Чисел\": " + numbers.size());

        numbers.add(0, 5);
        System.out.println("Добавим один элемент: " + numbers);
        System.out.println("Теперь его размер: " + numbers.size());
        System.out.println();

        System.out.println("Индекс вхождения элемента \"5\": " + numbers.indexOf(5));
        System.out.println("Индекс последнего вхождения элемента \"5\": " + numbers.lastIndexOf(5));
        System.out.println();

        ArrayList<Integer> numbers2 = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                numbers2.add(i);
            }
        }

        System.out.println("Создаем список Чётных чисел: " + numbers2);
        System.out.println();

        numbers.removeAll(numbers2);

        System.out.println("Удалим из списка1 все элементы списка чётных чисел: " + numbers);
        System.out.println();

        numbers.addAll(3, numbers2);

        System.out.println("Теперь добавим их обратно с индекса 3: " + numbers);
        System.out.println();

        numbers.retainAll(numbers2);

        System.out.println("Теперь оставим в списке 1 только элементы из списка чётных чисел: " + numbers);
        System.out.println();
    }
}
