import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class Main {






    public static void main(String[] args) {

        Connection c = null;
        Statement stmt = null;
        ArrayList<Dean> deans = new ArrayList<Dean>();
        ArrayList<College> colleges = new ArrayList<College>();
        ArrayList<Major> majors = new ArrayList<Major>();
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Professor> professors= new ArrayList<Professor>();

        try{
            Class.forName("org.postgresql.Driver");
            c= DriverManager.getConnection("jdbc:postgresql://localhost:5432/university", "postgres", "password");
            System.out.println("Opened database successfully");
            stmt= c.createStatement();
            String strStatement=
            "SELECT * FROM DEAN ORDER BY DEAN_ID";
            ResultSet rs = stmt.executeQuery(strStatement);
            while(rs.next()){
                Dean dean = new Dean(rs.getInt("Dean_ID"),rs.getString("dean_name"));
                deans.add(dean);
            }

            strStatement = 
            "SELECT * FROM COLLEGE ORDER BY COLLEGE_ID";
            rs = stmt.executeQuery(strStatement);
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
            rs = stmt.executeQuery(strStatement);
            while(rs.next()){
                College college = College.FindCollege(colleges, rs.getInt("College_ID"));
                if(college==null){
                    college = new College();
                }
                 new Major(rs.getInt("Major_ID"),rs.getString("Major_name"),college);
                
            }

            strStatement =
            "SELECT * FROM STUDENT ORDER BY STUDENT_ID";
            rs = stmt.executeQuery(strStatement);
            while(rs.next()){
                College college = College.FindCollege(colleges, rs.getInt("College_ID"));
                Major major = Major.FindMajor(majors, rs.getInt("Major_ID"));
                if(college==null){
                    college = new College();
                }
                if(major==null){
                    major= new Major();
                }

                new Student(rs.getInt("Student_ID"),rs.getString("Student_name"),college,major);
               
            }

            strStatement =
            "SELECT * FROM PROFESSOR ORDER BY PROFESSOR_ID";
            rs = stmt.executeQuery(strStatement);
            while(rs.next()){
                College college = College.FindCollege(colleges, rs.getInt("College_ID"));
                if(college==null){
                    college = new College();
                }

             new Professor(rs.getInt("Professor_ID"),rs.getString("Professor_Name"),college);
            }

        }
        

        catch(Exception e){
            System.out.println("Something went wrong");
        }
   

        // for(Dean dean: deans){
        //     System.out.println("Dean ID: "+ dean.getDeanID());
        //     System.out.println("Dean name: "+ dean.getDeanName());
        //     System.out.println("");
        // }

        // for(College college: colleges){
        //     college.displayStudents();
            // System.out.println("College ID: "+ college.getCollegeID());
            // System.out.println("College name: "+ college.getCollege_name());
            // System.out.println("Dean name "+college.getDean().getDeanName());
            // System.out.println("");
        

        // for(Major major: majors){
        //     System.out.println("Major ID: "+ major.getMajorID());
        //     System.out.println("Major name: "+ major.getMajor_name());
        //     System.out.println("College name "+major.getCollege().getCollege_name());
        //     System.out.println("");
        // }

        // for(Professor professor: professors){
        //     System.out.println("Professor ID: "+ professor.getProfessorID());
        //     System.out.println("Professor name: "+ professor.getProfessorName());
        //     System.out.println("College name "+professor.getCollege().getCollege_name());
        //     System.out.println("");
        // }

        // for(Student student: students){
        //     System.out.println("Student ID: "+ student.getStudent_ID());
        //     System.out.println("Student name: "+ student.getStudent_Name());
        //     System.out.println("College name "+student.getCollege().getCollege_name());
        //     System.out.println("");
        // }

        




        System.out.println("Welcome");

         University drexel = new University("John Fry");
         drexel.setColleges(colleges);
         mainMenu(drexel);   
        }






    public static void mainMenu (University university){
        String[] args2={};
        /*
        Starting point
        Students chooses either 1 or 2 if they are a current student
        of a new student
         */

        System.out.println("Enter 1 or 2 ");
        System.out.println("1. Current Student ");
        System.out.println("2. New Student");
        System.out.println("3. Administrator");
        System.out.println("4. Display all students");
        System.out.println("5. Display all courses");
        System.out.println("6. Select Major");
        Scanner scanner = new Scanner(System.in);
        String status = scanner.nextLine();
         if (status.equals("1")) {
            System.out.println("Enter student Id");
            String strID= scanner.nextLine();
            int id = Integer.parseInt(strID);
            Student student=Student.findStudent(id, university);



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

                else {
                    System.out.println("You entered a wrong input");
                    mainMenu(university);
                }

            }



    public static Student registerStudent(Student student,University university){
        System.out.println("What college would you like to join");
        System.out.println("Enter 1, 2, 3, 4, or 5");
        System.out.println("1. College of Computing and Informatics");
        System.out.println("2. College of Business");
        System.out.println("3. College of Engineering");
        System.out.println("4. College of Arts and Science");
        System.out.println("5. College of Westphal");

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

            default:
                System.out.println("Incorrect input");

        }

        return student;
    }
  
    public static Student selectMajor(Student student, College college){
        System.out.println("Select major");
        college.displayMajors();
        Scanner scanner = new Scanner(System.in);
        int response = scanner.nextInt();
        try{
        student.setMajor(college.getMajors().get(response+1));
        }
        catch(Exception e){
            System.out.println("There was an error");
        }

        return student;

    }

    public static void currentStudent(Student student, University university){
        System.out.println("Student name: "+ student.getStudent_Name());
        System.out.println("Student college: "+student.getCollege() );
        System.out.println("Student Major: "+ student.getMajor());

        String response ="";

        while(response.equalsIgnoreCase("6")){
            System.out.println("1. Show classes");
            System.out.println("2. Add Course");
            System.out.println("3. Change college");
            System.out.println("4. Change major");
            System.out.println("5. Return to main menu");
            System.out.println("6. Quit");
        }
    }
}