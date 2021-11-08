import java.sql.Time;
import java.util.ArrayList;

public class CourseSection {
    private String section_id;
    private String course_id;
    private ArrayList<Student> students;
    private ArrayList<String> days = new ArrayList<>();
    private String startTime;
    private String endTime;

    public CourseSection(String sectionID,String courseID){
        this.setCourseID(courseID);
        this.setSectionID(sectionID);
    }
    public CourseSection(String sectionID){
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

    public ArrayList<String> getDays(){
        return days;
    }

    public void addDay(String day){
        days.add(day);
    }

    public String getStartTime(){
        return startTime;
    }

    public void setStartTime(String startTime){
        this.startTime= startTime;
    }
    
    public String getEndTime(){
        return endTime;
    }

    public void setEndTime(String endTime){
        this.endTime= endTime;
    }

    public String toString(){
        System.out.println("Course ID "+course_id);
        System.out.println("Section ID "+ section_id);
        System.out.println("Days "+getDays());
        System.out.println("Start time "+ startTime);
        System.out.println("End time "+endTime);

        return "";
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
