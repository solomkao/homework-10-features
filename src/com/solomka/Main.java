package com.solomka;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            printMenu();
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0 -> {
                    System.out.println("The program finished.");
                    quit = true;
                }
                case 1 -> {
                    System.out.println("Input comma-separated list of numbers and press Enter -> ");
                    String numbers = scanner.nextLine();
                    uniqueNumbers(numbers);
                }
                case 2 -> {
                    System.out.println("Input a number from 0 to 10 and press Enter -> ");
                    int numericalMarc = scanner.nextInt();
                    toTextMark(numericalMarc);
                }
                default -> quit = true;
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("""
                Available actions:
                press
                0 - to quit
                1 - to count the percentage  of unique numbers
                2 - to convert marks""");
    }

    private static void toTextMark(int numericalMark) {
        String textMark = switch (numericalMark) {
            case 9, 10 -> "Well";
            case 7, 8 -> "Good";
            case 6 -> "Acceptable";
            case 5 -> "Bad";
            case 0, 1, 2, 3, 4 -> "Very Bad";
            default -> "Invalid data";
        };
        System.out.println("Your mark means " + textMark);
    }

    private static void uniqueNumbers(String numbers) {
        int[] array = convertToList(numbers);
        if (array == null) {
            return;
        }
        Double collect = Arrays.stream(array)
                .boxed()
                .collect(Collectors.teeing(
                        Collectors.counting(),
                        Collectors.collectingAndThen(Collectors.toSet(), Set::size),
                        (number, unique) -> (unique.doubleValue() / number.doubleValue() * 100)));
        System.out.println("The percent of unique numbers " + collect.intValue() + "%");
    }

    private static int[] convertToList(String numbers) {
        String[] arrayOfNumbers = numbers.split(",");
        int[] array = null;
        try {
            array = Arrays.stream(arrayOfNumbers)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (NumberFormatException e) {
            System.out.println("Invalid data. Try again.");
        }
        return array;
    }
}
