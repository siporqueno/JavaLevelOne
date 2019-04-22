package Lesson_7;

// Home work of Lesson 7, tasks 1-6.
public class Cat {
    private int appetite;
    private String name;
    private boolean satiety;

    public Cat(int appetite, String name) {
        this.appetite = appetite;
        this.name = name;
        satiety = false;
    }

    public int getAppetite() {
        return appetite;
    }

    public void setAppetite(int appetite) {
        if (appetite > 0) this.appetite = appetite;
        else System.out.println("Это какая-то ошибка. У меня всегда хороший, положительный аппетит!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void eat(Plate p) {
        if (appetite <= p.getFood()) {
            p.decreaseFood(appetite);
            satiety = true;
        } else System.out.println("Я " + name + ". Мне мало еды в тарелке!");
    }

    public void satietyInfo() {
        System.out.printf("Я %s. Я %s.\n", name, satiety == true ? "сыт )" : "голоден (");
    }
}

class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public void addFood(int f) {
        food += f;
    }

    public void decreaseFood(int n) {
        food -= n;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void info() {
        System.out.println(food);
    }
}


class MainClass {
    public static void main(String[] args) {

        /*Cat cat = new Cat(50, "Vaska");
        Plate plate = new Plate(100);
        plate.info();
        cat.satietyInfo();
        cat.eat(plate);
        plate.info();
        cat.satietyInfo();
        cat.setAppetite(-10);*/

        Cat[] catArr = {
                new Cat(30, "Barsik"),
                new Cat(40, "Murka"),
                new Cat(50, "Vaska")
        };

        Plate plateTwo = new Plate(50);
        plateTwo.info();
        plateTwo.addFood(40);
        plateTwo.info();

        for (Cat gato : catArr) {
            gato.eat(plateTwo);
            gato.satietyInfo();
        }

        plateTwo.info();
    }
}