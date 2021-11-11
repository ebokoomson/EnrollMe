import java.util.ArrayList;

public class Major {
    private int major_id;
    private String major_name;
    private College college;

    public Major(){

    }
    public Major(int collegeID, String majorName,College college){
        this.setMajorID(collegeID);
        this.setMajor_name(majorName);
        this.setCollege(college);
    }

    public int getMajorID(){
        return major_id;
    }

    public String getMajorName() {
        return major_name;
    }

    public College getCollege(){
        return college;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public void setMajorID(int major_id){
        this.major_id=major_id;
    }

    public void setCollege(College college) {
        this.college = college;
        college.addMajor(this);
    }

    public static Major FindMajor(ArrayList<Major> majors,int id){
        for(Major major : majors){
            if(major.getMajorID()== id){
                return major;
            }
        }

        return null;
    }
}
