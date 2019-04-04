package Lesson_3;

import java.util.Random;
import java.util.Scanner;

public class LessonThreeHomeWork {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(getRandomWord());
        System.out.println(getGuess());
        scanner.close();
    }

    static String getRandomWord() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear",
                "pepper", "pineapple", "pumpkin", "potato"};
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    static String getGuess() {
        System.out.println("Please guess the word");
        return scanner.nextLine();
    }


}
