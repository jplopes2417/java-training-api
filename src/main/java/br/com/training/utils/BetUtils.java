package br.com.training.utils;

import java.util.ArrayList;

public class BetUtils {

    public static String formatNumbers(String numbers){

        StringBuilder sb = new StringBuilder(numbers);
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    public static ArrayList<Integer> transformStringInArray(String numbersString){

        ArrayList<Integer> numbers = new ArrayList<>();
        String[] split = numbersString.split(",");
        for (String s : split) {
            numbers.add(Integer.valueOf(s.trim()));
        }
        return numbers;
    }
}
