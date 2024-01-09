import java.util.Scanner;

public class StudentUtil {

    static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) {
        int size = studentIdList.length;
        double[] ans = new double[size];
        for (int i = 0; i < size; i++) {
            double sum = 0;
            int c = 0;
            for (char x : studentsGrades[i]) {
                if (x == 'A') {
                    sum+= 4;
                } else if (x == 'B') {
                    sum += 3;
                } else {
                    sum += 2;
                }
                c++;
            }
            sum =sum/c; // Calculate average GPA
            ans[i] =sum;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] studentIdList ={ 1000, 1001 };
        char[][] arr ={ { 'A', 'A', 'A', 'B' }, { 'A', 'B', 'B' } };
        double[] ans =calculateGPA(studentIdList, arr);
        //logging the gpas of respective stu
        for (int i = 0; i < ans.length; i++) {
            LogBack.slf4jLogger.debug("Student ID: " + studentIdList[i] + ", GPA: " + ans[i]);
        }
    }
}
