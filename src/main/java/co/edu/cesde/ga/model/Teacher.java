package co.edu.cesde.ga.model;

public class Teacher extends Person {

    private Long teacherId;

    public Teacher(Long userId, String code,String documentType, String documentNumber, String firstName, String lastName, String status) {
        super(userId, code, documentType, documentNumber, firstName, lastName, status);
    }

    public Teacher(Long teacherId,Long userId, String code,String documentType, String documentNumber, String firstName, String lastName, String status) {
        super(userId, code, documentType, documentNumber, firstName, lastName, status);
        this.teacherId = teacherId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return  "TeacherId= " + getTeacherId() + '\n' +
                "UserId= " + getUserId() + '\n' +
                "Code= " + getCode() + '\n' +
                "DocumentNumber= " + getDocumentNumber() + '\n' +
                "DocumentType= " + getDocumentType() + '\n' +
                "FirstName= " + getFirstName() + '\n' +
                "LastName= " + getLastName() + '\n' +
                "Status= " + getStatus() + '\n' +
                "-----------------------------";
    }
}
