import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthToolTipUI;

public  class  College implements Serializable {

    protected University university;
    protected int collegeID;
    protected String college_name;
    protected Dean dean;
    protected ArrayList<Professor> professors = new ArrayList<>();
    protected ArrayList<Student> students = new ArrayList<>();
    protected ArrayList<Course> courses = new ArrayList<>();
    protected ArrayList<Major> majors = new ArrayList<>();

    public College(){

    }


    public College(String college_name, Dean dean) {
        this.college_name = college_name;
        this.dean= dean;
    }
    
    public College(String college_name, Dean dean, University university) {
        this.college_name = college_name;
        this.dean= dean;
        this.university = university;
        university.addCollege(this);
    }
    public College(int collegeID, String college_name, Dean dean) {
        this.collegeID=collegeID;
        this.college_name = college_name;
        this.dean= dean;
    }
   

 

   

    public int getCollegeID(){
        return collegeID;
    }

    public String getCollege_name() {
        return college_name;
    }

    public Dean getDean() {
        return dean;
    }


    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(ArrayList<Professor> professors) {
        this.professors = professors;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addProfessor(Professor professor) {
        professors.add(professor);

    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addMajor(Major major){
        majors.add(major);
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public int numberOfStudents() {
        return students.size();

    }

    public void setCollegeID(int collegeID){
        this.collegeID = collegeID;
    }

    public ArrayList<Major> getMajors(){
        return majors;
    }

    public void displayStudents() {

        for (Student student : students) {
            System.out.println(student.getStudent_Name());
        }

    }


    public void displayCourses() {
        for (Course course : getCourses()) {
            System.out.println(course.getCourse_Name());
        }
    }

    public void displayMajors(){
        for(Major major: this.getMajors()){
            System.out.println(major.getMajorName());
        }
    }

    public static College FindCollege(ArrayList<College> colleges,int id){
        for(College college:colleges){
            if(college.getCollegeID()==id){
                return college;
            }
        }

        return null;

    }

    public String toString(){
        System.out.println(this.getCollege_name());
        System.out.println("Total number of Students: "+ this.getStudents().size());
        System.out.println("Total number of Majors: "+ this.getMajors().size());
        System.out.println("Total number of Courses: "+ this.getCourses().size());
        System.out.println("Total number of Professors: "+ this.getProfessors().size());
        return "";
    }

}
