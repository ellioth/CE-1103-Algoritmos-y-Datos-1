package miniProject;

public class Student extends Person{
    private String carneEstudiante;

    public Student(){
        carneEstudiante = "2014057732";
    }

    public String getCarneEstudiante() {
        return carneEstudiante;
    }

    @Override
    public void displayInfo() {
        //super.displayInfo();
        System.out.println("I'm a student: " + name);
        System.out.println(getAge());
    }

    public void setInfo(String name){
        this.name = name;
    }

    public void setInfo(String name, int age){
        this.name = name;
        setAge(age);
    }

    public int setInfo(String name, int age, boolean ifPrint){
        this.name = name;
        setAge(age);
        this.displayInfo();
        Student.mostrar();
        return 0;
    }

    public static void mostrar(){
        System.out.println("Hola soy estudiante!!");
        //this.carneEstudiante = "0";
    }

    /**
     * Holaaa AAAA!!!
     */
    public static void mostrar1(){
        System.out.println("Holaaaa AAAA!!!");
    }
}
