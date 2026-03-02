package model;

public class Teacher extends Person {

    public Teacher() {
    }

    public Teacher(Long userId, String code, String documentNumber, String firtsName, String lastName, String status) {
        super(userId, code, documentNumber, firtsName, lastName, status);
    }
}
