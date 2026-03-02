package co.edu.cesde.ga.application;

import co.edu.cesde.ga.model.*;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        Person person = new Person();

        person.setUserId(2L);
        person.setCode("120");
        person.setStatus("CANCEL");
        person.setDocumentNumber("3142149293");
        person.setFirstName("Julian");
        person.setLastName("Cardona");


        System.out.println("User ID: " + person.getUserId());
        System.out.println("Code: " + person.getCode());
        System.out.println("Document number: " + person.getDocumentNumber());
        System.out.println("First name: " + person.getFirstName());
        System.out.println("Last name: " + person.getLastName());
        System.out.print("Status: " + person.getStatus());

        Teacher teacher = new Teacher();

        teacher.setUserId(2L);
        teacher.setCode("120");
        teacher.setStatus("CANCEL");
        teacher.setDocumentNumber("3142149293");
        teacher.setFirstName("Juli");
        teacher.setLastName("Cardo");


        System.out.println("User ID: " + teacher.getUserId());
        System.out.println("Code: " + teacher.getCode());
        System.out.println("Document number: " + teacher.getDocumentNumber());
        System.out.println("First name: " + teacher.getFirstName());
        System.out.println("Last name: " + teacher.getLastName());
        System.out.print("Status: " + teacher.getStatus());


        Student student = new Student();

        student.setUserId(2L);
        student.setCode("121");
        student.setStatus("ACTIVE");
        student.setDocumentNumber("314298738");
        student.setFirstName("Marcelo");
        student.setLastName("Cardo");
        student.setBirthDate("2008/01/22");


        System.out.println("User ID: " + student.getUserId());
        System.out.println("Code: " + student.getCode());
        System.out.println("Document number: " + student.getDocumentNumber());
        System.out.println("First name: " + student.getFirstName());
        System.out.println("Last name: " + student.getLastName());
        System.out.print("Status: " + student.getStatus());
        System.out.println("birth date: " + student.getBirthDate());

        Enrollments enrollments = new Enrollments();

        enrollments.setStudentId(3L );
        enrollments.setGroupId("2 ");
        enrollments.setPeriodId("1");
        enrollments.setStatus("ACTIVE");
        enrollments.setEnrolledAt("2026-03-03");


        System.out.println("Student ID :" + enrollments.getStudentId());
        System.out.println("Group ID :" + enrollments.getGroupId());
        System.out.println("Period ID :" + enrollments.getPeriodId());
        System.out.println("Status :" + enrollments.getStatus());
        System.out.println("Enrollments :" + enrollments.getEnrolledAt());

        Programs programs = new Programs();

        programs.setCode("4321");
        programs.setName("Ingenieria del software");

        System.out.println("Code :" + programs.getCode());
        System.out.println("Name :" + programs.getName());

        Users users = new Users();

        users.setUserId(4L);
        users.setUsername("Mariana Giraldo");
        users.setEmail("mgiradlo12345678910");
        users.setPasswordHash("12345");
        users.setStatus("Active");
        users.setCreatedAt("2026-03-03");

        System.out.println("User ID :" + users.getUserId());
        System.out.println("Name :" + users.getUsername());
        System.out.println("Email :" + users.getEmail());
        System.out.println("Password :" + users.getPasswordHash());
        System.out.println("Status :" + users.getStatus());
        System.out.println("Created :" + users.getCreatedAt());

        Roles roles = new Roles();

        roles.setRolesId(5L);
        roles.setName("Student");
        roles.setDescription("Puedes consultar informacion y realizar actividades");

        System.out.println("Roles :" + roles.getRolesId());
        System.out.println("Name :" + roles.getName());
        System.out.println("Descripcion :" + roles.getDescription());

        User_roles user_roles = new User_roles();

        user_roles.setUserId(6L);
        user_roles.setRoleId(7L);

        System.out.println("User ID :" + user_roles.getUserId());
        System.out.println("Rol :" + user_roles.getRoleId());

        Subject subject = new Subject();

        subject.setSubjectId(9L);
        subject.setCode("1010");
        subject.setName("Julian");
        subject.setCredits(123);
        subject.setProgramId(10L);

        System.out.println("Subject :" + subject.getSubjectId());
        System.out.println("Code :" + subject.getCode());
        System.out.println("Name :" + subject.getName());
        System.out.println("Credits :" + subject.getCredits());
        System.out.println("Program :" + subject.getProgramId());

        Period period = new Period();

        period.setPeriodId(1L);
        period.setCode("9876");
        period.setStartDate("2026-01-20");
        period.setEndDate("2026-12-05");

        System.out.println("Period :" + period.getPeriodId());
        System.out.println("Code :" + period.getCode());
        System.out.println("Start Date: " + period.getStartDate());
        System.out.println("End Date :" + period.getEndDate());

        Group group = new Group();

        group.setPeriodId(1L);
        group.setCode("123");
        group.setProgramId(1L);
        group.setShift("Morning");

        System.out.println("\n--- GROUP ---");
        System.out.println("Period :" + group.getPeriodId());
        System.out.println("Code: " + group.getCode());
        System.out.println("Program ID: " + group.getProgramId());
        System.out.println("Shift: " + group.getShift());

        Group_subject group_subject = new Group_subject();

        group_subject.setGroupId(1L);
        group_subject.setSubjectId(1L);
        group_subject.setTeacherId(10L);

        System.out.println("\n--- GROUP SUBJECT ---");
        System.out.println("GroupSubject ID: " +group_subject.getGroupId());
        System.out.println("Group ID: " + group_subject.getSubjectId());
        System.out.println("Subject ID: " + group_subject.getTeacherId());

        Grades grades = new Grades();

        grades.setGroupSubjectId(1L);
        grades.setStudentId(5L);
        grades.setFinalScore(4.8);
        grades.setObservation("Excellent work");

        System.out.println("\n--- GRADE ---");
        System.out.println("GroupSubject ID: " + grades.getGroupSubjectId());
        System.out.println("Student ID: " + grades.getStudentId());
        System.out.println("Final Score: " + grades.getFinalScore());
        System.out.println("Observation: " + grades.getObservation());


    }


}
