package ru.academits.kravchenko.lambda;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        if (age < 0) {
            throw new IllegalArgumentException("age must be >= 0");
        }

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String toString(){
        String ageToString;

        int lastDigit = age % 10;
        int twoLastDigits = age % 100;

        if (age < 1) {
            ageToString = "младенец";
        } else if (lastDigit == 1 && twoLastDigits != 11) {
            ageToString = age + " год";
        } else if (lastDigit >= 2 && lastDigit <= 4 && (twoLastDigits < 12 || twoLastDigits > 14)) {
            ageToString = age + " года";
        } else {
            ageToString = age + " лет";
        }

        return name + ", " + ageToString;
    }
}