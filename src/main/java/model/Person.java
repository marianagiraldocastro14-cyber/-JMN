package model;

public class Person {

    private Long userId;
    private String code;
    private String documentNumber;
    private String firtsName;
    private String lastName;
    private String status;

   public Person() {
   }

    public Person(Long userId, String code, String documentNumber, String firtsName, String lastName, String status) {
        this.userId = userId;
        this.code = code;
        this.documentNumber = documentNumber;
        this.firtsName = firtsName;
        this.lastName = lastName;
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public String getCode() {
        return code;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getFirtsName() {
        return firtsName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setFirtsName(String firtsName) {
        this.firtsName = firtsName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
