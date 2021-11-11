import org.w3c.dom.ls.LSOutput;
import java.util.Scanner;
import javax.naming.spi.DirStateFactory.Result;
import java.sql.*;
public class Admin {

    private static Statement statement = Main.getStatement();
    private static boolean adminAccess =false;
    private static int passowordAttempts =3;

    public static void admin_page(University university) {
        Scanner scanner = new Scanner(System.in);
        String password;
        String response;
        String repeat_response = "1";
        String repeat_response2="y";
        while(!adminAccess && passowordAttempts>0){
        System.out.println("Please enter super secret password");
        password = scanner.nextLine();
        if(!password.equals("bnm")){
            System.out.println("Incorrect");
            passowordAttempts--;
        }
        else{
            adminAccess=true;
        }
        }
        if (adminAccess) {
            System.out.println("1. Add courses");
            System.out.println("2. Add Majors");
            System.out.println("3. Add classes");
            System.out.println("4. Display number of Students");
            System.out.println("5. Display number of Professors");
            System.out.println("6. Display number of Courses");
            System.out.println("7. Return to Main Menu");
            
            response = scanner.nextLine();
            if (response.equals("1")) {
                while (repeat_response.equals("1")) {
                    addCourse(university);
                    System.out.println("1) Add another course");
                    System.out.println("2) Return to previous menu");
                    repeat_response = scanner.nextLine();
                }
                admin_page(university);
                return;

            }

            else if (response.equals("2")){
                while(repeat_response.equals("1")){
                    addMajor(university);
                    System.out.println("1) Add another major");
                    System.out.println("2) Return to previous menu");
                    repeat_response = scanner.nextLine();
                }
                admin_page(university);
                return;
                }

            else if(response.equals("3")){
                while(repeat_response.equals("1")){
                    addClass(university);
                }
            }
            else if(response.equals("7")){
                Main.mainMenu(university);
                return;
            }

            System.out.println("Go back to main menu? (y/n)");
            repeat_response = scanner.nextLine();
            if (repeat_response.equals("y")){
                Main.mainMenu(university);
            }
        }
    }

    public static void addCourse(University university){
        String courseID;
        String courseName;
        int counter = 1;
        for (College college: university.getColleges()){
            System.out.println((counter)+") " +college.getCollege_name());
            counter++;
        }
        System.out.println("Select college option");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        int collegeOption = Integer.parseInt(option);
        College selectedCollege = university.getColleges().get(collegeOption-1);
        System.out.println("Enter Course ID");
        courseID = scanner.nextLine();
        System.out.println("Enter Course Name");
        courseName = scanner.nextLine();
        Course course= new Course(courseID,courseName,selectedCollege);
        String sqlStatement =
        "INSERT INTO COURSE VALUES ('"+course.getCourseID()+"','"+course.getCourse_Name()
        +"',"+selectedCollege.getCollegeID()+")";
        try{
            statement.executeUpdate(sqlStatement);
            System.out.println("New Course Succesfully added to "+selectedCollege.getCollege_name());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void addMajor(University university){
        String majorName;
        String option;
        int counter = 1;
        for (College college: university.getColleges()){
            System.out.println((counter)+") " +college.getCollege_name());
            counter++;
        }
        Scanner scanner = new Scanner(System.in);
        option= scanner.nextLine();
        int collegeOption = Integer.parseInt(option);
        College selectedCollege = university.getColleges().get(collegeOption-1);
        System.out.println("Enter major name");
        majorName= scanner.nextLine();
        Major major; 
        String sqlStatement=
        "INSERT INTO MAJOR VALUES(DEFAULT,'"+majorName+"',"+(collegeOption)+")";
        try {
            statement.executeUpdate(sqlStatement);
            sqlStatement=
            "SELECT MAX(MAJOR_ID) FROM MAJOR";
            ResultSet rs = statement.executeQuery(sqlStatement);
            while(rs.next()){
            major= new Major(rs.getInt("Max"),majorName,selectedCollege);
            }
            System.out.println("Major succesfully added to "+ selectedCollege.getCollege_name());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void addClass(University university){
        System.out.println("Select College");
        for(int i = 0; i<university.getColleges().size(); i++){
            System.out.println((i+1)+ ") "+ university.getColleges().get(i).getCollege_name());
        }
        Scanner scanner = new Scanner(System.in);
        String collegeOption = scanner.nextLine();
        College selectedCollege = university.getColleges().get(Integer.parseInt(collegeOption)-1);
        courseList(selectedCollege);
        
        
    }
    
    public static void addSection(Course course){
        Scanner scanner = new Scanner(System.in);
        CourseSection section = course.addNewSection();
           System.out.println("How many days in a week?");
           String numOfDaysStr = scanner.nextLine();
           int numOfDays = Integer.parseInt(numOfDaysStr);
           String day="";
           for(int i = 0 ; i < numOfDays;i++){
               System.out.println("Enter Day "+ i);
               day = scanner.nextLine();
               section.addDay(day);
           }
           System.out.println("Enter Start time");
           String startTime = scanner.nextLine();
           System.out.println("Enter End time");
           String endTime = scanner.nextLine();
           section.setStartTime(startTime);
           section.setEndTime(endTime);
           System.out.println(section);
           String sqlStatement = 
            "INSERT INTO CourseSection VALUES ('"+section.getSectionID()+"','"+section.getCourseID()+"',"+3+",'"+section.getDays().get(0)+"','"+startTime+"','"+endTime+"')";
            try{
                statement.executeUpdate(sqlStatement);
                System.out.println("New Course Section Succesfully added to "+course.getCourseID());
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
           System.out.println("1) Add another section for " + course.getCourseID());
           System.out.println("2) Return to course list");
           System.out.println("3) Return to College list");
           System.out.println("4) Return to admin menu");
           String response = scanner.nextLine();

           switch(response){
               case "1":
                addSection(course);
                break;
               case "2":

           }

    }

    public static void courseList(College college){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Course");
        for(int i=0; i< college.getCourses().size(); i++){
            System.out.println((i+1)+ ") "+ college.getCourses().get(i).getCourseID());
        }
        String courseOption = scanner.nextLine();
        Course selectedCourse = college.getCourses().get(Integer.parseInt(courseOption)-1);
        System.out.println("1) Add a new Course Section");
        for(int i= 0; i< selectedCourse.getCourseSections().size();i++){
            System.out.println((i+2)+") "+ selectedCourse.getCourseSections().get(i));
        }
        String response = scanner.nextLine();
        System.out.println("The response "+ response);
        if(response.equals("1")){
            addSection(selectedCourse);

        }
    }
    public static void main(String[] args) {

            }
        }







