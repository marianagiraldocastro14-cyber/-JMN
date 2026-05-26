package co.edu.cesde.ga.model;

public class Student extends Person {

    private String birthDate;
    private Long studentId;

    public Student(Long userId, String documentType, String documentNumber, String firstName, String lastName, String status, String birthDate) {
        super(userId, null, documentType, documentNumber, firstName, lastName, status);
        this.birthDate = birthDate;
    }

    public Student(Long studentId, String birthDate, Long userId, String code,String documentType, String documentNumber, String firstName, String lastName, String status) {
        super(userId, code, documentType, documentNumber, firstName, lastName, status);
        this.birthDate = birthDate;
        this.studentId = studentId;
    }

    public String getBirthDate() { return birthDate;}

    public void setBirthDate(String birthDate) {this.birthDate = birthDate;}

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return  "StudentId= " + getStudentId() + '\n' +
                "UserId= " + getUserId() + '\n' +
                "DocumentNumber= " + getDocumentNumber() + '\n' +
                "DocumentType= " + getDocumentType() + '\n' +
                "FirstName= " + getFirstName() + '\n' +
                "LastName= " + getLastName() + '\n' +
                "BirthDate= " + getBirthDate() + '\n' +
                "Status= " + getStatus() + '\n' +
                "-----------------------------";
    }
}
