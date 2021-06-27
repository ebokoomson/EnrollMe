import java.util.ArrayList;

public class CourseSection {
    private String section_Name;
    private int section_id;
    private ArrayList<Student> students;

    public String getSection_Name() {
        return section_Name;
    }

    public int getSection_id(){
        return section_id;
    }

    public void setSection_id(int section_id){
        this.section_id= section_id;
    }

    
    public void setSection_Name(String section_Name) {
        this.section_Name = section_Name;
    }

}
