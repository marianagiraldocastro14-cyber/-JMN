package co.edu.cesde.ga.model;

public class Subject {

    private Long subjectId;
    private String code;
    private String name;
    private Integer credits;
    private Long programId;

    public Subject() {
    }

    public Subject(String code, String name, Integer credits, Long programId) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.programId = programId;
    }

    public Subject(Long subjectId, String code, String name, Integer credits, Long programId) {
        this.subjectId = subjectId;
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.programId = programId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    @Override
    public String toString() {
        return  "SubjectId= " + getSubjectId() + '\n' +
                "Code= " + getCode() + '\n' +
                "Name= " + getName() + '\n' +
                "Credits= " + getCredits() + '\n' +
                "ProgramId= " + getProgramId() + '\n' +
                "-----------------------------";
    }
}
