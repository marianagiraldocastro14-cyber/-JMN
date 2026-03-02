package model;

public class Student extends  Person{

    private String birthDate;

    public Student() {
    }

    public Student(Long userId, String code, String documentNumber, String firtsName, String lastName, String status) {
        super (userId, code, documentNumber, firtsName, lastName, status);
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
