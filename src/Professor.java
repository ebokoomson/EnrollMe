import java.util.ArrayList;

public class Professor {
    private int professor_id;
    private String professor_Name;
    private College college;
    private ArrayList<CourseSection> courseSections = new ArrayList<>();


    public Professor(int professor_id,String professor_Name,College college){
        this.setProfessorID(professor_id);
        this.setProfessorName(professor_Name);
        this.setCollege(college);
    }
    
    public int getProfessorID(){
        return professor_id;
    }

    public String getProfessorName(){
        return professor_Name;
    }

    public College getCollege(){
        return college;
    }
    public ArrayList<CourseSection> getCourseSections(){
        return courseSections;
    }

    public void setProfessorID(int professorID){
        this.professor_id = professorID;
    }

    public void setProfessorName(String profName){
        this.professor_Name= profName;
    }

    public void setCollege(College college){
        this.college = college;
        college.addProfessor(this);
    }

    public static Professor FindProfessor(ArrayList<Professor> professors, int id){
        for(Professor prof: professors){
            if(prof.getProfessorID()==id){
                return prof;
            }
        }
        return null;
    }





}
