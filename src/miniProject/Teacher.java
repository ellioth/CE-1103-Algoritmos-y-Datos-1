package miniProject;

public class Teacher extends Person{

    public Teacher(String name, int edad){
        this.name = name;
        setAge(edad);
    }

    @Override
    public void displayInfo() {
        //super.displayInfo();
        System.out.println("I'm a teacher: " + name);
        System.out.println(getAge());
    }
}
