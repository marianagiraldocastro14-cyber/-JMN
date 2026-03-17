package co.edu.cesde.ga.model;

public class Student extends Person {

    private Long studentId;
    private String birthDate;

    public Student(){super();}

    public Student(Long studentId, String birthDate, Long userId, String code, String documentNumber, String firstName, String lastName, String status) {
        super(userId, code, documentNumber, firstName, lastName, status);
        this.studentId = studentId;
        this.birthDate = birthDate;
    }
private Long getStudentId(){return studentId;}

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId + '\'' +
                "birthDate='" + getBirthDate() + '\n' +
                "userId=" + getUserId() +
                "code='" + getCode() + '\n' +
                "documentNumber='" + getDocumentNumber() + '\n' +
                "firstName='" + getFirstName() + '\n' +
                "lastName='" + getLastName() + '\n' +
                "status='" + getStatus() + '\n' +
                '}';
    }
}
