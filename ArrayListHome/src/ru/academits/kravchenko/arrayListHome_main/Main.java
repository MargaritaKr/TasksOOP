package ru.academits.kravchenko.arrayListHome_main;

import ru.academits.kravchenko.arrayListHome.ArrayListHome;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        for (String s : ArrayListHome.readFileInList("CSV/CSV.csv")) {
            System.out.println(s);
        }

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 4, 8, 5, 7, 4, 6, 9, 24, 19, 10));
        System.out.println(numbers);

        ArrayListHome.removeEvenNumbers(numbers);
        System.out.println(numbers);

        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5, 4, 4, 11, 1));
        System.out.println(numbers2);

        System.out.println(ArrayListHome.removeDuplicates(numbers2));
    }
}
