package co.edu.cesde.ga;

import model.Student;
import model.Teacher;
import model.Person;

public class Main {

    public Main() {
    }

    public static void main(String[] args) {

        Person person = new Person();

        person.setUserId(1L);
        person.setCode("123456");
        person.setDocumentNumber("1234567890");
        person.setFirtsName("Mariana");
        person.setLastName("Giraldo");
        person.setStatus("ACTIVE");

        System.out.println("User id: " + person.getUserId());
        System.out.println("Code: " + person.getCode());
        System.out.println("Document number: " + person.getDocumentNumber());
        System.out.println("First name: " + person.getFirtsName());
        System.out.println("Last name: " + person.getLastName());
        System.out.println("Status: " + person.getStatus());


        Teacher teacher = new Teacher();

        teacher.setUserId(2L);
        teacher.setCode("123456");
        teacher.setDocumentNumber("0987654321");
        teacher.setFirtsName("Wiliam");
        teacher.setLastName("Castro");
        teacher.setStatus("ACTIVE");

        System.out.println("User id: " + teacher.getUserId());
        System.out.println("Code: " + teacher.getCode());
        System.out.println("Document number: " + teacher.getDocumentNumber());
        System.out.println("First name: " + teacher.getFirtsName());
        System.out.println("Last name: " + teacher.getLastName());
        System.out.println("Status: " + teacher.getStatus());

        Student student = new Student();

        student.setUserId(3L);
        student.setCode("4321");
        student.setDocumentNumber("987654321");
        student.setFirtsName("Stephanie");
        student.setLastName("Arenas");
        student.setStatus("ACTIVE");
        student.setBirthDate("06/04/2005");

        System.out.println("User id: " + student.getUserId());
        System.out.println("Code: " + student.getCode());
        System.out.println("Document number: " + student.getDocumentNumber());
        System.out.println("First name: " + student.getFirtsName());
        System.out.println("Last name: " + student.getLastName());
        System.out.println("Status: " + student.getStatus());
        System.out.println(student.getBirthDate());

    }
}
