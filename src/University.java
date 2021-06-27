import java.io.Serializable;
import java.util.ArrayList;

public class University implements Serializable {
    private String president;
    private ArrayList<College> colleges = new ArrayList<>();


    public University(String president) {

    }

    public University(){

    }


    public ArrayList<College> getColleges() {
        return colleges;
    }

    public String getPresident() {
        return president;
    }



    public void setPresident(String president) {
        this.president = president;
    }


    public void addCollege(College college) {
        colleges.add(college);
    }

    public void  setColleges(ArrayList<College> colleges){
        this.colleges = colleges;
    }

    public void displayColleges() {
        for (College college : colleges) {
            System.out.println(college.getCollege_name());
        }
    }


    public void displayStudents() {
        int studentsNumber = 0;

        for (College college : colleges) {
            System.out.println(college.getCollege_name() + " : " + college.getStudents().size()+ " students");
            studentsNumber = studentsNumber + college.numberOfStudents();
            college.displayStudents();
            System.out.println("");

        }

        System.out.println("There are " + studentsNumber + " students in the university");


    }


    public void displayStudentsAndMajor() {

        for (College college : colleges) {
            college.displayStudents();

        }

    }

    public void displayCourses(){
        for(College college: colleges){
            System.out.println(college.getCollege_name()+ " : "+ college.getCourses().size()+ " courses");
            college.displayCourses();
            System.out.println("");
        }
    }
}
