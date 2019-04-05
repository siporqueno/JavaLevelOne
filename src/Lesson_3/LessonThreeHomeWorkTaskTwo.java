package Lesson_3;

import java.util.Random;
import java.util.Scanner;

public class LessonThreeHomeWorkTaskTwo {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        playWordGuessGame();
        scanner.close();
    }

    private static void playWordGuessGame() {
        boolean correctGuess = false;
        String word = getRandomWord();
        do {
            String guess = getGuess();
            StringBuilder promptOfLetters = new StringBuilder("###############");
            int lengthOverlapGuessWord = guess.length() < word.length() ? guess.length() : word.length();
            int numberOfRightLetters = 0;
            for (int i = 0; i < lengthOverlapGuessWord; i++) {
                if (guess.charAt(i) == word.charAt(i)) {
                    promptOfLetters.setCharAt(i, word.charAt(i));
                    numberOfRightLetters++;
                }
            }

            if (word.length() == guess.length() && word.length() == numberOfRightLetters) {
                System.out.println("Вы отгадали слово!");
                correctGuess = true;
            } else {
                System.out.println("Вы отгадали следующие буквы: " + promptOfLetters + ". Продолжайте в том же духе!");
            }
        }
        while (!correctGuess);
    }

    private static String getRandomWord() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear",
                "pepper", "pineapple", "pumpkin", "potato"};
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    private static String getGuess() {
        System.out.println("Угадайте слово. Пишите маленькими буквами английского алфавита.");
        return scanner.nextLine().trim();
    }


}
