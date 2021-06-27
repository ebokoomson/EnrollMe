import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {

    private String student_Name;
    private int student_ID;
    private College college;
    private ArrayList<Course> courses = new ArrayList<>();
    private Major major;
    private static int numberOfStudents;

    public Student(){
        numberOfStudents++;
    }

    public Student(int student_id,String student_name){
        this.setStudent_Name(student_name);
        this.setStudent_ID(student_id);
    }

    public Student(int student_id,String student_name, College college, Major major){
        this.setStudent_Name(student_name);
        this.setStudent_ID(student_id);
        this.setMajor(major);
        this.setCollege(college);
    }




    public String getStudent_Name() {
        return student_Name;
    }

    public void setStudent_Name(String student_Name) {
        this.student_Name = student_Name;
    }

    public int getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(int student_ID) {
        this.student_ID = student_ID;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
        college.addStudent(this);

    }

    public Major getMajor(){
        return major;
    }

    public void setMajor(Major major){
        this.major=major;
    }

    public static Student findStudent(int id, University university){

        for(College college: university.getColleges()){
            for(Student student: college.getStudents()){
                if(student.getStudent_ID()==id){
                    return student;
                }
            }
        }

        return null;
    }


}
