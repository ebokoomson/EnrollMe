import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
    private String course_id;
    private String course_Name;
    private Professor professor;
    private ArrayList<CourseSection> courseSections = new ArrayList<>();
    private College college;
    public static int numberOfCourses;


    public Course(){

        numberOfCourses++;
    }

    public Course(String courseID, String course_Name, College college){
        this.setCourseID(courseID);
        this.setCourse_Name(course_Name);
        this.setCollege(college);
    }

    public String getCourseID(){
        return course_id;
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

    public void setCourseID(String courseID){
        this.course_id=courseID;
    }

    public void setCourse_Name(String course_Name) {
        this.course_Name = course_Name;
    }

    public void addCourse(CourseSection course_section){
        course_section.setCourseID(this.getCourseID());
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

    public static Course findCourse(ArrayList<Course> courses,String ID ){
        for(Course course: courses){
            if(course.getCourseID().equals(ID)){
                return course;
            }
        }
        return null;
    }

    public void copy(Course course){
        this.setCourse_Name(course.getCourse_Name());
        this.setProfessor(course.getProfessor());
        this.setCourseSections(course.getCourseSections());
        this.setCollege(course.getCollege());
    }




}
