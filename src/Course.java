import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {

    private String course_Name;
    private Professor professor;
    private ArrayList<CourseSection> courseSections;
    private College college;
    public static int numberOfCourses;


    public Course(){

        numberOfCourses++;
    }


    public String getCourse_Name() {
        return course_Name;
    }

    public Professor getProfessor(){
        return professor;
    }

    public ArrayList<CourseSection> getCourseSections(){
        return courseSections;
    }

    public College getCollege(){
        return college;
    }

    public void setCourse_Name(String course_Name) {
        this.course_Name = course_Name;
    }

    public void addCourse(CourseSection course_section){
        courseSections.add(course_section);
    }

    public void setCollege(College college){
        this.college= college;
        college.addCourse(this);
    }

    public void setProfessor(Professor prof){
        this.professor = prof;
    }

    public void setCourseSections(ArrayList<CourseSection> courseSections){
        this.courseSections = courseSections;
    }

    public void copy(Course course){
        this.setCourse_Name(course.getCourse_Name());
        this.setProfessor(course.getProfessor());
        this.setCourseSections(course.getCourseSections());
        this.setCollege(course.getCollege());
    }




}
