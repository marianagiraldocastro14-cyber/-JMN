package co.edu.cesde.ga.model;

public class Grades {

    private Long groupSubjectId;
    private Long studentId;
    private Double finalScore;
    private String observation;

    public Grades() {
    }

    public Grades(Double finalScore, String observation) {
        this.finalScore = finalScore;
        this.observation = observation;
    }

    public Grades(Long groupSubjectId, Long studentId, Double finalScore, String observation) {
        this.groupSubjectId = groupSubjectId;
        this.studentId = studentId;
        this.finalScore = finalScore;
        this.observation = observation;
    }

    public Long getGroupSubjectId() {
        return groupSubjectId;
    }

    public void setGroupSubjectId(Long groupSubjectId) {
        this.groupSubjectId = groupSubjectId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public String toString() {
        return  "GroupSubjectId= " + getGroupSubjectId() + '\n' +
                "StudentId= " + getStudentId() + '\n' +
                "FinalScore= " + getFinalScore() + '\n' +
                "Observation= " + getObservation() + '\n' +
                "-----------------------------";
    }
}
