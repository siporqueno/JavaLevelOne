package Lesson_2;

import java.util.Arrays;

import static Utilities.Utils.printArr;

public class LessonTwoHomeWork {
    public static void main(String[] args) {

        int[] arrTestDriveZero = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        int[] arrTestDriveOne = {1, 3, 7, 2, 5, 11, 4, 6, -2};
        int[] arrTestDriveTwo = {2, 2, 2, 1, 2, 2, 10, 1};
        int[] arrTestDriveThree = {1, 1, 1};
        Integer[] arrTestDriveFour = {1, 2, 3, 4, 5};
        int[][] arrTestDriveFive = {
                {1, 2, 3},
                {4, 5, 6}
        };

        System.out.println("Home work of lesson 2, task 1");
        System.out.println(Arrays.toString(invertBinaryArray(arrTestDriveZero)));

        System.out.println("Home work of lesson 2, task 2");
        System.out.println(Arrays.toString(createArray()));

        System.out.println("Home work of lesson 2, task 3");
        System.out.println(Arrays.toString(multiplyArray()));

        System.out.println("Home work of lesson 2, task 4");
        printArr(makeSquareArrayWithOnesAtDiagonal(10));
        printArr(arrTestDriveFive);

        System.out.println("Home work of lesson 2, task 5");
        getMinMaxOfArray(arrTestDriveOne);

        System.out.println("Home work of lesson 2, task 6");
        System.out.println(checkBalance(arrTestDriveTwo));

        System.out.println("Home work of lesson 2, task 7");
        System.out.println(Arrays.toString(moveArrayElements(arrTestDriveFour, 3)));

    }

    // Home work of lesson 2, task 1
    static int[] invertBinaryArray(int[] inputArray) {
        int[] outputArray = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            outputArray[i] = inputArray[i] == 0 ? 1 : 0;
        }
        return outputArray;
    }

    // Home work of lesson 2, task 2
    static int[] createArray() {
        int[] outputArray = new int[8];
        for (int i = 0; i < outputArray.length; i++) {
            outputArray[i] = 3 * i;
        }
        return outputArray;
    }

    // Home work of lesson 2, task 3
    static int[] multiplyArray() {
        int[] inputArray = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int[] outputArray = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            outputArray[i] = inputArray[i] < 6 ? 2 * inputArray[i] : inputArray[i];
        }
        return outputArray;
    }

    // Home work of lesson 2, task 5
    static int[][] makeSquareArrayWithOnesAtDiagonal(int sizeOfArray) {
        int[][] outputArray = new int[sizeOfArray][sizeOfArray];
        for (int i = 0; i < sizeOfArray; i++) {
            outputArray[i][i] = 1;
        }
        return outputArray;
    }

    // Home work of lesson 2, task 5
    static int[] getMinMaxOfArray(int[] array) {
        int[] valuesMinMaxOfInputArray = {array[0], array[0]};
        for (int i = 1; i < array.length; i++) {
            if (array[i] < valuesMinMaxOfInputArray[0]) {
                valuesMinMaxOfInputArray[0] = array[i];
            }
            if (valuesMinMaxOfInputArray[1] < array[i]) {
                valuesMinMaxOfInputArray[1] = array[i];
            }
        }
        System.out.println("Min and Max: " + Arrays.toString(valuesMinMaxOfInputArray));
        return valuesMinMaxOfInputArray;
    }

    // Home work of lesson 2, task 6
    static boolean checkBalance(int[] inputArray) {
        boolean existsPlace = false;
        // Let's find first the sum of elements of inputArray
        int sumOfInputArray = 0;

        for (int i : inputArray) sumOfInputArray += i;


        // Let's now check if there is a place where 2*leftSum == sumOfInputArray
        for (int j = 0; j < inputArray.length; j++) {
            // Firstly, let's find leftSum. It is the sum of elements of inputArray from 0 to j.
            int leftSum = 0;
            for (int k = 0; k <= j; k++) {
                leftSum += inputArray[k];
            }

            if (leftSum * 2 == sumOfInputArray) {
                existsPlace = true;
                System.out.println("Such place exists! Left border point: " + j);
                break;
            }
        }

        return existsPlace;
    }

    // Home work of lesson 2, task 7
    static Object[] moveArrayElements(Object[] inputArray, int n) {
        int arrayLength = inputArray.length;
        int move = n >= 0 ? n % arrayLength : (n % arrayLength) + arrayLength;
        for (int i = 0; i < move; i++) inputArray = moveOneElementRight(inputArray);

        return inputArray;
    }

    static Object[] moveOneElementRight(Object[] arrayToMove) {
        Object lastElementContent = arrayToMove[arrayToMove.length - 1];
        for (int i = arrayToMove.length - 1; i > 0; i--) {
            arrayToMove[i] = arrayToMove[i - 1];
        }
        arrayToMove[0] = lastElementContent;
        return arrayToMove;
    }
}
