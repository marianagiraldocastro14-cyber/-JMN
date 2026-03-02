package co.edu.cesde.ga.model;

public class Group_subject {

    Long groupId;
    Long subjectId;
    Long teacherId;

    public Group_subject() {
    }

    public Group_subject(Long groupSubjectId, Long groupId, Long subjectId, Long teacherId) {
        this.groupId = groupId;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}