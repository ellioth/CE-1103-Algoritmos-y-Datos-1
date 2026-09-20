class Animal {
    public String name;

    public Animal(String name) {
        this.name = name;
    }

    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
}

class Lion extends Animal {
    // Attempting to inherit from Animal
    public void makeSound() {
        System.out.println("Roar!");
    }
}

public class ZooSimulation {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        myAnimal.name = "Leo";

        Lion myLion = new Lion("Simba");
        myLion.makeSound();
    }
}

