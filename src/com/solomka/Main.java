package com.solomka;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // System.out.println("Your mark means " + toTextMark());
        Integer[] array = {1, 2, 2, 3};
        System.out.println("The percent of unique numbers " + uniqueNumbers(array) + "%");


    }

    private static String toTextMark() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input a number from 0 to 10 and press Enter ");
        int numericalMark = in.nextInt();
        in.close();
        String textMark = switch (numericalMark) {
            case 9, 10 -> "Well";
            case 7, 8 -> "Good";
            case 6 -> "Acceptable";
            case 5 -> "Bad";
            case 0, 1, 2, 3, 4 -> "Very Bad";
            default -> "Invalid data";
        };
        return textMark;
    }

    private static int uniqueNumbers(Integer[] array) {
        Double collect = (Arrays.stream(array)
                .collect(Collectors.teeing(
                        Collectors.counting(),
                        Collectors.collectingAndThen(Collectors.toSet(), Set::size),
                        (number, unique) -> (unique.doubleValue() / number.doubleValue() * 100))));
        return collect.intValue();
    }


}
