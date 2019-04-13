package Lesson_5;

public class Main {

    public static void main(String[] args) {
        // Home work, lesson 5, task 4
        Employee[] empsArray = new Employee[5];
        empsArray[0] = new Employee("Doctor", "Dre", "Начальник отдела", "dr.dre@example.com", 123412341, 80000, 54);
        empsArray[1] = new Employee("Kanye", "West", "Зам. начальника отдела", "kanye.west@example.com", 112341234, 50000, 41);
        empsArray[2] = new Employee("Wiz", "Khalifa", "Ведущий специалист", "wiz.khalifa@example.com", 411234123, 30000, 31);
        empsArray[3] = new Employee("Chris", "Brown", "Преподаватель хип-хопа", "chris.brown@example.com", 341123412, 350000, 29);
        empsArray[4] = new Employee("Eminem", "Forever", "Старший эксперт", "eminem.forever@example.com", 234112341, 50000, 46);

        // Home work, lesson 5, task 5
        for (Employee employee : empsArray) {
            if (employee.getAge() >= 40) employee.info();
        }
    }

}
