package Lesson_6;

import java.util.Random;

import static java.lang.Math.abs;

// Home work of Lesson 6, task 5.
public abstract class AnimalUnpredictable {

    protected String name;
    Random random = new Random();

    protected int averageRunningLimit;
    protected float averageJumpingLimit;
    protected int averageSwimmingLimit;

    protected int dispersionOfRunningLimit;
    protected float dispersionOfJumpingLimit;
    protected int dispersionOfSwimmingLimit;

    protected int runningLimit;
    protected float jumpingLimit;
    protected int swimmingLimit;

    public AnimalUnpredictable(String name) {
        this.name = name;
        runningLimit = averageRunningLimit - dispersionOfRunningLimit + random.nextInt(1 + abs(2 * dispersionOfRunningLimit));

    }

    void info() {
        System.out.print("Я " + this.getClass().getSimpleName() + " по имени " + name + ". ");
    }

    abstract void run(int distance);

    abstract void jump(float height);

    abstract void swim(int distance);
}

class CatUnpredictable extends AnimalUnpredictable {
    protected String color;

    public CatUnpredictable(String name, String color) {
        super(name);
        this.color = color;
        averageRunningLimit = 200;
        averageJumpingLimit = 2f;
        averageSwimmingLimit = 0;

        dispersionOfRunningLimit = 50;
        dispersionOfJumpingLimit = 0.5f;
        dispersionOfSwimmingLimit = 0;

        runningLimit = averageRunningLimit - dispersionOfRunningLimit + random.nextInt(1 + abs(2 * dispersionOfRunningLimit));

        jumpingLimit = averageJumpingLimit - dispersionOfJumpingLimit + 2 * dispersionOfJumpingLimit * random.nextFloat();
    }

    @Override
    void info() {
        super.info();
        System.out.println("Мой цвет " + color + ".");
//        System.out.println(runningLimit);
//        System.out.println(jumpingLimit);
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
        System.out.println("Вы что-то перепутали, кОты это не кИты!!!");
    }
}

class DogUnpredictable extends AnimalUnpredictable {
    protected int weight;

    public DogUnpredictable(String name, int weight) {
        super(name);
        this.weight = weight;

        averageRunningLimit = 500;
        averageJumpingLimit = 0.5f;
        averageSwimmingLimit = 10;

        dispersionOfRunningLimit = 100;
        dispersionOfJumpingLimit = 0.2f;
        dispersionOfSwimmingLimit = 3;

        runningLimit = averageRunningLimit - dispersionOfRunningLimit + random.nextInt(1 + abs(2 * dispersionOfRunningLimit));
        jumpingLimit = averageJumpingLimit - dispersionOfJumpingLimit + 2 * dispersionOfJumpingLimit * random.nextFloat();
        swimmingLimit = averageSwimmingLimit - dispersionOfSwimmingLimit + random.nextInt(1 + abs(2 * dispersionOfSwimmingLimit));

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

class MainUnpredictable {

    public static void main(String[] args) {
        CatUnpredictable catOne = new CatUnpredictable("Luntik", "Red");
        catOne.info();
        catOne.run(220);
        catOne.jump(2.2f);
        catOne.swim(20);

        CatUnpredictable catTwo = new CatUnpredictable("Barsik", "Yellow");
        catTwo.info();
        catTwo.run(220);
        catTwo.jump(2.2f);
        catTwo.swim(20);

        DogUnpredictable dogOne = new DogUnpredictable("Muhtar", 40);
        dogOne.info();
        dogOne.run(600);
        dogOne.jump(0.6f);
        dogOne.swim(12);

        DogUnpredictable dogTwo = new DogUnpredictable("Laika", 10);
        dogTwo.info();
        dogTwo.run(600);
        dogTwo.jump(0.6f);
        dogTwo.swim(12);
    }
}


