package Lesson_5;

// Home work, lesson 5, task 1
public class Employee {
    private String name;
    private String surname;
    private String position;
    private String email;
    private int phone;
    private int salary;
    private byte age;

    // Home work, lesson 5, task 2
    public Employee(String name, String surname, String position, String email, int phone, int salary, byte age) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    // Home work, lesson 5, task 3
    public void info() {
        System.out.println("Имя: " + name + surname + "\n" + "Должность: " + position + "\n" + "Email: " + email + "\n"
                + "Телефон: " + phone + "\n" + "Зарплата: " + salary + "\n" + "Возраст: " + age);
    }
}
