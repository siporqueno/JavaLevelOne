package Lesson_1;

public class MainClass {
    byte byteVal = 127;
    short shortVal = -32000;
    int intVal = -1000000000;
    long longVal = 1000000000L;
    char charVal = 'a';
    boolean boolVal = true;
    float floatVal = 123456.25f;
    double doubleVal = 654321.123456;


    public static void main(String[] args) {
        System.out.println("Hola, que tal?");
        System.out.println(expressionCalc(2, 3, 4, 5));
        System.out.println(isSumWithin(5, 18));
        numberSign(1);
        System.out.println(isNegative(1));
        greetingByName("сосед");
        short yearToCheckForLeap = 1900;
        System.out.println(isLeapYear(yearToCheckForLeap));
        System.out.println(isLeapYearImproved(yearToCheckForLeap));

       /* int x = 25;
        x*=10;
        System.out.println(x);*/
    }

    // Home work, task 3
    private static float expressionCalc(int a, int b, int c, int d) {
        return a * (b + (c / d));
    }

    // Home work, task 4
    private static boolean isSumWithin(int a, int b) {
        if (10 <= a + b && a + b <= 20) return true;
        else return false;
    }

    // Home work, task 5
    private static void numberSign(int number) {
        if (number >= 0) System.out.println("This number is positive.");
        else System.out.println("This number is negative");
    }

    // Home work, task 6
    private static boolean isNegative(int number) {
        if (number < 0) return true;
        else return false;
    }

    // Home work, task 7
    private static void greetingByName(String name) {
        System.out.printf("Привет, %s!\n", name);
    }

    // Home work, task 8
    private static boolean isLeapYear(short year) {
        if (year > 0) {
            if (year % 400 == 0) {
                System.out.println("This is a leap year!");
                return true;
            } else {
                if (year % 100 == 0) {
                    System.out.println("This is not a leap year!");
                    return false;
                } else {
                    if (year % 4 == 0) {
                        System.out.println("This is a leap year!");
                        return true;
                    } else {
                        System.out.println("This is not a leap year!");
                        return false;
                    }
                }
            }
        } else {
            System.out.println("Such year does not exist!");
            return false;
        }
    }

    // Home work, task 8 - solution from Artem Evdokimov
    private static boolean isLeapYearImproved(short year) {
        return (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0);
    }

}
