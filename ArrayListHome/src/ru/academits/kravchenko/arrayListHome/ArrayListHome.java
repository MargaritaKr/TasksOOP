package ru.academits.kravchenko.arrayListHome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListHome {
    public static ArrayList<String> readFileInList(String fileName) {
        ArrayList<String> result = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                result.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("the argument must contain correct name and path to the file");
        }

        return result;
    }

    public static void removeEvenNumbers(ArrayList<Integer> numbers) {
        int i = 0;

        while (i < numbers.size()) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            } else {
                i++;
            }
        }
    }

    public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> numbers) {
        ArrayList<Integer> result = new ArrayList<>(numbers.size());

        for (Integer element : numbers) {
            if (!result.contains(element)) {
                result.add(element);
            }
        }

        return result;
    }
}