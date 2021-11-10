import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import org.postgresql.util.PSQLException;
import java.sql.*;

public class Main {

    private static Statement statement= null;

    public static Statement getStatement(){
        return statement;
    }

    public static void creatDBConnection(){
        Connection c = null;
        Statement stmt = null;
        try{
        Class.forName("org.postgresql.Driver");
        c= DriverManager.getConnection("jdbc:postgresql://localhost:5432/university", "postgres", "kobstersquid");
        System.out.println("Opened database successfully");
        stmt= c.createStatement();
        }
        catch(Exception e){
           System.out.println("Error connecting to database"); 
        }
        statement=stmt;
    }

    public static void main(String[] args) throws SQLException {
        creatDBConnection();
        ArrayList<Dean> deans = new ArrayList<Dean>();
        ArrayList<College> colleges = new ArrayList<College>();
        ArrayList<Major> majors = new ArrayList<Major>();
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Professor> professors= new ArrayList<Professor>();
        ArrayList<Course> courses= new ArrayList<Course>();

        try{
            String strStatement=
            "SELECT * FROM DEAN ORDER BY DEAN_ID";
            ResultSet rs = statement.executeQuery(strStatement);
            while(rs.next()){
                Dean dean = new Dean(rs.getInt("Dean_ID"),rs.getString("dean_name"));
                deans.add(dean);
            }

            strStatement = 
            "SELECT * FROM COLLEGE ORDER BY COLLEGE_ID";
            rs = statement.executeQuery(strStatement);
            while(rs.next()){
                Dean dean = Dean.findDean(deans, rs.getInt("Dean_ID"));
                if(dean == null){
                    dean = new Dean();
                 }
                College college = new College(rs.getInt("College_ID"),rs.getString("College_name"),dean);
                colleges.add(college);
            }

            strStatement =
            "SELECT * FROM MAJOR ORDER BY MAJOR_ID";
            rs = statement.executeQuery(strStatement);
            while(rs.next()){
                College college = College.FindCollege(colleges, rs.getInt("College_ID"));
                if(college==null){
                    college = new College();
                }
                 Major major =new Major(rs.getInt("Major_ID"),rs.getString("Major_name"),college);
                 majors.add(major);   
            }

            strStatement =
            "SELECT * FROM STUDENT ORDER BY STUDENT_ID";
            rs = statement.executeQuery(strStatement);
            while(rs.next()){
                College college = College.FindCollege(colleges, rs.getInt("College_ID"));
                Major major = Major.FindMajor(majors, rs.getInt("Major_ID"));
                if(college==null){
                    college = new College();
                }
                if(major==null){
                    major= new Major();
                }
                Student student =new Student(rs.getInt("Student_ID"),rs.getString("Student_name"),college,major);
                students.add(student);  
            }

            strStatement =
            "SELECT * FROM PROFESSOR ORDER BY PROFESSOR_ID";
            rs = statement.executeQuery(strStatement);
            while(rs.next()){
                College college = College.FindCollege(colleges, rs.getInt("College_ID"));
                if(college==null){
                    college = new College();
                }
             Professor prof =new Professor(rs.getInt("Professor_ID"),rs.getString("Professor_Name"),college);
             professors.add(prof);
            }

            strStatement = 
            "SELECT * FROM COURSE ORDER BY COURSE_ID";
            rs = statement.executeQuery(strStatement);
            while(rs.next()){
                College college = College.FindCollege(colleges, rs.getInt("College_ID"));
                Course course = new Course(rs.getString("Course_ID"),rs.getString("Course_name"),college);
                courses.add(course);
            }

            strStatement = 
            "SELECT * FROM COURSESECTION ORDER BY COURSE_ID";
            rs = statement.executeQuery(strStatement);
            while(rs.next()){
                Course course= Course.findCourse(courses, rs.getString("Course_ID"));
                CourseSection section = new CourseSection(rs.getString("CourseSection_ID"),rs.getString("Course_ID"));
                Time startTime = rs.getTime("Start_time");
                Time endTime = rs.getTime("End_time");
                section.setStartTime(startTime.toString());
                section.setEndTime(endTime.toString());
                course.addCourse(section);  
            }

            strStatement =
            "SELECT * FROM Student_CourseSection";
            rs = statement.executeQuery(strStatement);
            while(rs.next()){
                CourseSection section= CourseSection.findSection(courses, rs.getString("CourseSection_ID"));
                Student student = Student.findStudentFrmDB(students,rs.getInt("Student_ID"));
                student.addCourseSection(section);
            }

        }
        

        catch(Exception e){
            System.out.println("Something went wrong");
        }
        System.out.println("Welcome");

         University drexel = new University("John Fry");
         drexel.setColleges(colleges);
         mainMenu(drexel);   
        }

    public static int mainMenu (University university){
        String[] args2={};
        /*
        Starting point
        Students chooses either 1 or 2 if they are a current student
        of a new student
         */

        System.out.println("Enter number options");
        System.out.println("1. Current Student ");
        System.out.println("2. New Student");
        System.out.println("3. Administrator");
        System.out.println("4. Display all students");
        System.out.println("5. Display all courses");
        System.out.println("6. Select College");
        System.out.println("7. Select Major");
        Scanner scanner = new Scanner(System.in);
        String status = scanner.nextLine();
         if (status.equals("1")) {
            System.out.println("Enter student Id");
            String strID= scanner.nextLine();
            int id = Integer.parseInt(strID);
            Student student=Student.findStudent(id, university);
            currentStudent(student, university);



            } 
            else if (status.equals("2")) {
            System.out.println("Enter Full Name:");
            String full_name = scanner.nextLine();
            Student student = new Student();
            student.setStudent_Name(full_name);
            registerStudent(student, university);
            System.out.println("Go back to main menu (y/n)");
            String response1= scanner.nextLine();
            if(response1.equals("y")|| response1.equals("Y")){
                mainMenu(university);
                    }
                }

            else if(status.equals("3")){
                    Admin.admin_page(university);
                }
            else if(status.equals("4")){
                university.displayStudents();
                System.out.println("Go back to main menu (y/n)");
                String response1= scanner.nextLine();
                if(response1.equals("y")|| response1.equals("Y"))
                {
                    mainMenu(university);
                }
                }
                /*
                If the user chooses 5 the if statement will show all courses within
                the university
                 */
            else if(status.equals("5")){
                university.displayCourses();
                System.out.println("Go back to main menu (y/n)");
                String response1= scanner.nextLine();
                if(response1.equals("y")|| response1.equals("Y")){
                    mainMenu(university);
                    }
                }
            
            else if(status.equals("6")){
                selectCollege(university);
               

            }

             else {
                    System.out.println("You entered a wrong input");
                    mainMenu(university);
                }
                return 0;
            }

    public static College selectCollege(University university) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<College> colleges = university.getColleges();
        for(int i=0; i < colleges.size();i++){
            System.out.println((i+1)+ ") "+ colleges.get(i).getCollege_name());
        }
        String collegeOptionStr = scanner.nextLine();
        College selectedCollege;

        while(true){
            try{
                int index = Integer.parseInt(collegeOptionStr);
                selectedCollege = colleges.get(index-1);
                break;
            }
            catch(NumberFormatException e){
                System.out.println("Please enter a number");
                collegeOptionStr = scanner.nextLine();
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("Please enter a number between 1 and "+colleges.size());
                collegeOptionStr = scanner.nextLine();
            }

        }
        System.out.println(selectedCollege);
        System.out.println("1) Select student");
        System.out.println("2) Select Major");
        System.out.println("3) Select Course");
        System.out.println("4) Select Professor");
        System.out.println("5) Return to college list");
        String response = scanner.nextLine();
        while(true){
            try{
                int numberOption=Integer.parseInt(response);
                if(numberOption>5 || numberOption<0){
                    throw new Exception();
                }
                break;
            }
            catch(Exception e){
                System.out.println("Please enter a number between 1 and 4 inclusize");
                System.out.println("1) Select student");
                System.out.println("2) Select Major");
                System.out.println("3) Select Course");
                System.out.println("4) Select Professor");
                System.out.println("5) Return to college list");
                response = scanner.nextLine();
            }
        }
        switch(response){
            case "5":
                selectCollege(university);
                break;

        }
        return selectedCollege;
    }

    public static Student registerStudent(Student student,University university){
        System.out.println("What college would you like to join");
        System.out.println("Enter college number option");
        for(int i=0; i< university.getColleges().size(); i++){
            System.out.println(i+1+") "+university.getColleges().get(i).getCollege_name());
        }

        Scanner scanner = new Scanner(System.in);
        String response= scanner.nextLine();
        ArrayList<College> colleges = new ArrayList<>(5);
        colleges.addAll(university.getColleges());
        switch(response) {
            case "1":
                student.setCollege(colleges.get(0));
                break;

            case "2":
                student.setCollege(colleges.get(1));
                break;
            case "3":
                student.setCollege(colleges.get(2));
                break;
            case "4":
                student.setCollege(colleges.get(3));
                break;

            case "5":
                student.setCollege(colleges.get(4));
                break;

            case "6":
                student.setCollege(colleges.get(5));
                break;

            default:
                System.out.println("Incorrect input");

        }

        return selectMajor(student, student.getCollege());
    }
  
    public static Student selectMajor(Student student, College college){
        System.out.println("Select major");
        college.displayMajors();
        Scanner scanner = new Scanner(System.in);
        int response = scanner.nextInt();
        try{
        student.setMajor(college.getMajors().get(response-1));
        String sqlStatement= 
        "INSERT INTO STUDENT VALUES (DEFAULT,'"+student.getStudent_Name()
        +"',"+student.getCollege().getCollegeID()+","+student.getMajor().getMajorID()+")";
        statement.executeUpdate(sqlStatement);
        sqlStatement= 
        "SELECT MAX(STUDENT_ID) FROM STUDENT";
       ResultSet rs= statement.executeQuery(sqlStatement);
       while(rs.next()){
       student.setStudent_ID(rs.getInt("max"));
       }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return student;
    }

    public static void currentStudent(Student student, University university){
        System.out.println("Student name: "+ student.getStudent_Name());
        System.out.println("Student college: "+student.getCollege().getCollege_name() );
        System.out.println("Student Major: "+ student.getMajor().getMajor_name());
        Scanner scanner = new Scanner(System.in);
        String response ="";

        while(!response.equalsIgnoreCase("6")){
            System.out.println("1. Show classes");
            System.out.println("2. Add Course");
            System.out.println("3. Change college");
            System.out.println("4. Change major");
            System.out.println("5. Return to main menu");
            System.out.println("6. Quit");
            response=scanner.nextLine();

            switch(response){
                case "1":
                    student.displayCourses();
                    break;
                case "2":
                    addCourseMenu(student,student.getCollege(),university);
                    break;
                case "3":
                    break;        
            }
        }
    }

    public static void addCourseMenu(Student student, College college, University university){

        System.out.println("Select course. Enter number option");
        for(int i = 0; i < college.getCourses().size();i++){
            System.out.println((i+1)+") "+college.getCourses().get(i).getCourseID()+": "+college.getCourses().get(i).getCourse_Name());
        }
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        int option = Integer.parseInt(response)-1;
        Course selectedCourse =college.getCourses().get(option);
        System.out.println("Select section. Enter number option");
        for(int i =0; i< selectedCourse.getCourseSections().size(); i++){
            System.out.println((i+1)+") "+selectedCourse.getCourseSections().get(i).getSectionID());
        }
        response = scanner.nextLine();
        option = Integer.parseInt(response)-1;
        CourseSection courseSection = selectedCourse.getCourseSections().get(option);
        student.addCourseSection(courseSection);
        String sqlStatement =
        "INSERT INTO STUDENT_COURSESECTION VALUES ("+student.getStudent_ID()+",'"+courseSection.getSectionID()+"')";
        try {
          statement.executeUpdate(sqlStatement);
          System.out.println("Student enrolled in "+courseSection.getSectionID());
        }
        catch(PSQLException pe){
            System.out.println("Student is already enrolled in the class");
        }
         catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("1) Add course");
        System.out.println("2) Previous menu");
        System.out.println("3) Main menu");
        response = scanner.nextLine();
        switch(response){
            case "1":
                addCourseMenu(student, college,university);
                break;
            case "2":
                currentStudent(student, university);
                break;
            case "3":
                mainMenu(university);
                break;
            default:
                System.out.println("Invalid response");
                break;     
        }
    }
}
