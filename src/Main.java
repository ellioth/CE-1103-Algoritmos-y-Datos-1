import ListExamples.SimpleLinkedList.SimpleLinkedList;
import miniProject.Person;
import miniProject.Student;
import miniProject.Teacher;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public String aaaa;

    public Main(String aaaa){
        this.aaaa = aaaa;
    }
    public String queNombre(String ninguno){
        ninguno = "saltar";
        return ninguno + "5";
    }

    public void getPerson(Person person){
        if (person instanceof Student) {
            System.out.println(((Student) person).getCarneEstudiante());
        }
        person.displayInfo();
    }

    public void getStudent(Student person){
        person.displayInfo();
        System.out.println(((Student) person).getCarneEstudiante());
    }

    public void getTeacher(Teacher person){
        person.displayInfo();
    }


    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!\n");
        Teacher teacher = new Teacher("Ellioth", 30);
        Student estudiante = new Student();
        Student.mostrar1();
        Student.mostrar();
        Person p1 = new Person("Persona", 23);
        Main aa1 = new Main("aaaa");
        aa1.getPerson(teacher);
        aa1.getPerson(estudiante);
        aa1.getPerson(p1);
        for (String arg : args){
            System.out.println(arg);
        }
        SimpleLinkedList<Integer> listSimple = new SimpleLinkedList<>();
        SimpleLinkedList<Double> listSimpleDouble = new SimpleLinkedList<>();
        SimpleLinkedList<String> listSimpleString = new SimpleLinkedList<>();
        Integer a = Integer.valueOf("1234");
        listSimple.insertDataAtBeginning(a);
        listSimpleString.insertDataSorted("hola");
    }
}