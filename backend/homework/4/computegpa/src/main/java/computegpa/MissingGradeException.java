package computegpa;

public class MissingGradeException extends Exception {
     int studentId = 0;

    public MissingGradeException(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

}
