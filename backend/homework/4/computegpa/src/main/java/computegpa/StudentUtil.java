package computegpa;

import LogBack.LogBack;

public class StudentUtil {
    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) throws MissingGradeException {
        if (studentIdList.length != studentsGrades.length) {
            throw new IllegalArgumentException(String.format("studentIdList & studentsGrades are out-of-sync. studentIdList.length: %d StduentsGrade.length %d",
                    studentIdList.length, studentsGrades.length));

        }
        double[] gpaList = new double[studentIdList.length];

        for (int i = 0; i < studentsGrades.length; i++) {
            double gpa = 0.0;

            for (int j = 0; j < studentsGrades[i].length; j++) {
                if (studentsGrades[i][j] == 'A') {
                    gpa += 4.0;
                } else if (studentsGrades[i][j] == 'B') {
                    gpa += 3.0;
                } else if (studentsGrades[i][j] == 'C') {
                    gpa += 2.0;
                } else if (studentsGrades[i][j] == ' ') {
                    throw new MissingGradeException(studentIdList[i]);
                }
            }
            gpaList[i] = gpa / studentsGrades[i].length;
        }

        return gpaList;
    }

    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {
        if (lower < 0 || higher < 0 || lower > higher) {
            return null;
        }

        double[] gpaList = new double[studentIdList.length];
        try {
            gpaList = calculateGPA(studentIdList, studentsGrades);
        } catch (MissingGradeException e) {
            
            String message = "Missing grade for student with ID: ".concat(String.valueOf(e.getStudentId()));
            LogBack.slf4jLogger.debug(message);
            throw new InvalidDataException(e);
        }

        int count = 0;
        for (double gpa : gpaList) {
            if (gpa >= lower && gpa <= higher) {
                count++;
            }
        }

        int[] result = new int[count];
        int index = 0;
        for (int i = 0; i < gpaList.length; i++) {
            if (gpaList[i] >= lower && gpaList[i] <= higher) {
                result[index++] = studentIdList[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] studentIdList = { 1001, 1002,1003};
        char[][] studentsGrades = { { 'A', 'A', 'A', ' ' }, { 'B', 'B', 'B', 'A' }, { 'A', 'A', 'B' } };
        double[] gpaList = null;

        try {
            gpaList = calculateGPA(studentIdList, studentsGrades);
        } catch (MissingGradeException e) {
            String message = "Missing grade for student with ID: ".concat(String.valueOf(e.getStudentId()));
            LogBack.slf4jLogger.debug(message);
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            LogBack.slf4jLogger.debug(e.getMessage());
        }
        if (gpaList != null) {
            for (double gpa : gpaList) {
                String message="GPA: ".concat(String.valueOf(gpa));
                LogBack.slf4jLogger.debug(message);

            }
            int[] id = getStudentsByGPA(3.5, 4.0, studentIdList, studentsGrades);

            for (int val : id) {
                String message="ID: ".concat(String.valueOf(val));
                LogBack.slf4jLogger.debug(message);
            }
        }
    }
}
