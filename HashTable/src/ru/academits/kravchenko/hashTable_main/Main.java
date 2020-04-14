package ru.academits.kravchenko.hashTable_main;

import ru.academits.kravchenko.hashTable.HashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Тест класса HashTable");
        System.out.println();

        HashTable<String> names = new HashTable<>();

        names.add("Маргарита");
        names.add("Евгений");
        names.add("Артём");
        names.add("Михаил");
        names.add("Маргарита");
        names.add("Наталья");
        names.add("Дмитрий");
        names.add("Егор");
        names.add("Екатерина");
        names.add("Аркадий");

        System.out.println("Создаем коллекцию имен - " + names);
        System.out.println();
        System.out.println("Вот как он выглядет в Хэш-таблице:");
        System.out.println(names.toTable());
        System.out.println();

        HashTable<Integer> numbers = new HashTable<>(5);

        for (int i = 1; i <= 15; i++) {
            numbers.add(i);
        }

        System.out.println("Создаем коллекцию чисел (вместимость таблицы - 5 массивов) - " + numbers);
        System.out.println();
        System.out.println("Вот как он выглядет в Хэш-таблице:");
        System.out.println(numbers.toTable());
        System.out.println();

        System.out.println("Проверим пуста ли коллекция чисел: " + numbers.isEmpty());
        System.out.println("Её размер: " + numbers.size());
        numbers.clear();
        System.out.println("Очистим коллекцию: " + numbers);
        System.out.println("Проверим пуста ли она, еще раз: " + numbers.isEmpty());
        System.out.println("Её размер: " + numbers.size());
        System.out.println();

        System.out.println("Добавим в имена два элемента \"null\": ");

        names.add(null);
        names.add(null);

        System.out.println(names.toTable());
        System.out.println();

        HashTable<String> names2 = new HashTable<>();

        names2.add("Евгений");
        names2.add("Артём");
        names2.add("Михаил");

        System.out.println("Создаем еще одну коллекцию имен - " + Arrays.toString(names2.toArray()));
        System.out.println();

        names.addAll(names2);

        System.out.println("Соединим эти коллекции: " + names);
        System.out.println("Размер итоговой коллекции: " + names.size());
        System.out.println();

        names.removeAll(names2);

        System.out.println("Удалим из коллекции имен добавленные: " + names);
        System.out.println();

        System.out.println("Теперь удалим все имена, кроме списка - " + Arrays.toString(new String[]{"Маргарита", "Наталья"}));

        names.retainAll(Arrays.asList("Маргарита", "Наталья", null));

        System.out.println("Новая коллекция имен: " + names);
    }
}
