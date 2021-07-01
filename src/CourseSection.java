import java.util.ArrayList;

public class CourseSection {
    private String section_id;
    private String course_id;
    private ArrayList<Student> students;

    public CourseSection(String sectionID,String courseID){
        this.setCourseID(courseID);
        this.setSectionID(sectionID);
    }

    public String getSectionID() {
        return section_id;
    }

    public String getCourseID(){
        return course_id;
    }

    public void setCourseID(String courseID){
        this.course_id= courseID;
    }

    public void setSectionID(String sectionID) {
        this.section_id= sectionID;
    }

    public static CourseSection findSection(ArrayList<Course> courses, String id){
        for(Course course: courses){
            for(CourseSection section: course.getCourseSections())
            if(section.getSectionID().equals(id)){
                return section;
            }
        }
        return null;
    }

}
