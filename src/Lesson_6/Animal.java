package Lesson_6;

// Home work of Lesson 6, tasks 1-4.
public abstract class Animal {

    protected String name;
    protected int runningLimit;
    protected float jumpingLimit;
    protected int swimmingLimit;

    public Animal(String name) {
        this.name = name;
    }

    void info() {
        System.out.print("Я " + this.getClass().getSimpleName() + " по имени " + name + ". ");
    }

    abstract void run(int distance);

    abstract void jump(float height);

    abstract void swim(int distance);
}

class Cat extends AnimalUnpredictable {
    protected String color;

    public Cat(String name, String color) {
        super(name);
        this.color = color;
        runningLimit = 200;
        jumpingLimit = 2f;
        swimmingLimit = 0;
    }

    @Override
    void info() {
        super.info();
        System.out.println("Мой цвет " + color + ".");
    }

    @Override
    void run(int distance) {
        System.out.printf("%s пробежал %d м.\n", name, distance > runningLimit ? runningLimit : distance);
    }

    @Override
    void jump(float height) {
        System.out.printf("%s подпрыгнул на %f м.\n", name, height > jumpingLimit ? jumpingLimit : height);
    }

    @Override
    void swim(int distance) {
        System.out.println("Вы что-то перепутали, кОты это не кИты!!!");
    }
}

class Dog extends AnimalUnpredictable {
    protected int weight;

    public Dog(String name, int weight) {
        super(name);
        this.weight = weight;
        runningLimit = 500;
        jumpingLimit = 0.5f;
        swimmingLimit = 10;
    }

    @Override
    void info() {
        super.info();
        System.out.println("Мой вес " + weight + " кг.");
    }

    @Override
    void run(int distance) {
        System.out.printf("%s пробежал %d м.\n", name, distance > runningLimit ? runningLimit : distance);
    }

    @Override
    void jump(float height) {
        System.out.printf("%s подпрыгнул на %.1f м.\n", name, height > jumpingLimit ? jumpingLimit : height);
    }

    @Override
    void swim(int distance) {
        System.out.printf("%s проплыл %d м.\n", name, distance > swimmingLimit ? swimmingLimit : distance);
    }
}

class Main {

    public static void main(String[] args) {
        Cat catOne = new Cat("Luntik", "Red");
        catOne.info();
        catOne.run(300);
        catOne.jump(1);
        catOne.swim(20);

        Dog dogOne = new Dog("Muhtar", 40);
        dogOne.info();
        dogOne.run(350);
        dogOne.jump(0.4f);
        dogOne.swim(20);
    }
}


