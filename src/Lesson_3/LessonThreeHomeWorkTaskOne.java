package Lesson_3;

import java.util.Random;
import java.util.Scanner;

public class LessonThreeHomeWorkTaskOne {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        playGame();
        scanner.close();
    }

    private static void playGame() {
        int responseOfPlayer = 2;
        do {
            playGameOnce();

            do {
                System.out.println("Повторить игру еще раз? 1-да/0-нет");
                responseOfPlayer = scanner.nextInt();

            } while (responseOfPlayer != 0 && responseOfPlayer != 1);

            /* Это неудачная пока попытка защиты от того, что игрок ввел абракадабру вместо 0 или 1
            do {
                System.out.println("Повторить игру еще раз? 1-да/0-нет");
                if (scanner.hasNextInt()) responseOfPlayer = scanner.nextInt(); else {
                    System.out.println("Введите 1-да/0-нет");

                }
            } while (responseOfPlayer != 0 && responseOfPlayer != 1);*/

        } while (responseOfPlayer == 1);
    }

    private static void playGameOnce() {
        Random random = new Random();
        int numberToGuess = random.nextInt(10);
        System.out.println("Отгадайте с трех попыток целое число, которое не меньше 0 и не больше 9");
        int flag = 0;
        for (int i = 0; i < 3; i++) {
            System.out.println("Введите число: ");
            int guess = scanner.nextInt();
            if (guess == numberToGuess) {
                System.out.println("Вы угадали!!!");
                flag = 1;
                break;
            }
            if (guess > numberToGuess) {
                System.out.println("Загаданное число меньше");
            } else {
                System.out.println("Загаданное число больше");
            }
        }
        if (flag == 0) System.out.println("К сожалению, удача оказалась не на вашей стороне(");
    }

}
