package Lesson_6;

public class MainClassString {
    public static void main(String[] args) {

        String str1 = " Предложение один    Теперь предложение два     Предложение три";
        System.out.println(str1);

        String str2 = str1.replaceAll(" +", " ").trim();
        System.out.println(str2);

        StringBuilder stringBuilder = new StringBuilder(str2);

        int counter = 0;
        for (int i = 1; i < str2.length(); i++) {
            if (str2.charAt(i) >= 'A' && str2.charAt(i) <= 'Я') {
                stringBuilder.insert(i + counter - 1, '.');
                counter++;
            }
        }

        stringBuilder.append('.');

        System.out.println(stringBuilder.toString());

    }
}


// задание привести строку к нормальному ввиду