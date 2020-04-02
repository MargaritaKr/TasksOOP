package ru.academits.kravchenko.lambda_main;

import ru.academits.kravchenko.lambda.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingInt;

public class Main {
    public static void main(String[] args) {

        // Задача 1:
        ArrayList<Person> persons = new ArrayList<>();

        persons.add(new Person("Маргарита", 36));
        persons.add(new Person("Евгений", 38));
        persons.add(new Person("Артём", 9));
        persons.add(new Person("Михаил", 6));
        persons.add(new Person("Екатерина", 2));
        persons.add(new Person("Наталья", 43));
        persons.add(new Person("Евгений", 45));
        persons.add(new Person("Алина", 21));
        persons.add(new Person("Артём", 12));

        System.out.println("А) получить список уникальных имен: ");

        persons.stream()
                .map(Person::getName)
                .distinct()
                .forEach(System.out::println);

        System.out.println();

        System.out.println("Б) вывести список уникальных имен в формате: ");

        String allNamesString = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", ""));

        System.out.println(allNamesString);
        System.out.println();

        System.out.println("В) получить список людей младше 18, посчитать для них средний возраст: ");

        persons.stream()
                .filter(person -> person.getAge() < 18)
                .forEach(System.out::println);

        double average = persons.stream()
                .filter(person -> person.getAge() < 18)
                .mapToInt(Person::getAge)
                .average()
                .getAsDouble();

        System.out.println("Средний возраст: " + average);
        System.out.println();

        System.out.println("Г) при помощи группировки получить Map, " +
                "в котором ключи – имена, а значения – средний возраст: ");

        Map<String, Double> personsMap = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, averagingInt(Person::getAge)));

        personsMap.forEach((name, person) -> System.out.println(name + ", средний возраст -  " + person));

        System.out.println();

        System.out.println("Д) получить людей, возраст которых от 20 до 45, " +
                "вывести в консоль их имена в порядке убывания возраста");

        List<Person> persons2 = persons.stream()
                .filter(person -> (person.getAge() >= 20 && person.getAge() <= 45))
                .collect(Collectors.toList());

        persons2.stream()
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .map(Person::getName)
                .forEach(System.out::println);
    }
}
