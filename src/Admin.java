import org.w3c.dom.ls.LSOutput;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.sql.*;

public class Admin {

    private static Statement statement = Main.getStatement();
    private static boolean adminAccess =false;
    private static int passowordAttempts =3;



    public static int admin_page(University university) {

       
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
                System.out.println("here");
                while (repeat_response.equals("1")) {
                    addCourse(university);
                    System.out.println("1) Add another course");
                    System.out.println("2) Return to prevoius menu");
                    repeat_response = scanner.nextLine();
                }
                return admin_page(university);

            }

            else if (response.equals("2")){
                while(repeat_response2.equals("y")){
                    Major major = new Major();
                    System.out.println("Enter Major name");
                    response = scanner.nextLine();
                    major.setMajor_name(response);
                    System.out.println("Enter Major college");
                    System.out.println("1. College of Computing and Informatics");
                    System.out.println("2. College of Business");
                    System.out.println("3. College of Engineering");
                    System.out.println("4. College of Arts and Science");
                    System.out.println("5. College of Westphal");
                        response = scanner.nextLine();
                        switch (response) {
                            case "1":
                                major.setCollege(university.getColleges().get(0));
                                break;
                            case "2":
                                major.setCollege(university.getColleges().get(1));
                                break;

                            case "3":
                                major.setCollege(university.getColleges().get(2));
                                break;

                            case "4":
                                major.setCollege(university.getColleges().get(3));
                                break;

                            case "5":
                                major.setCollege(university.getColleges().get(4));
                                break;

                            default:
                                System.out.println("Incorrect input");

                        }
                        System.out.println("Add another course? (y/n)");
                        repeat_response = scanner.nextLine();
                    }
                }
            else if(response.equals("7")){
              return  Main.mainMenu(university);
            }

            System.out.println("Go back to main menu? (y/n)");
            repeat_response = scanner.nextLine();
            if (repeat_response.equals("y")){
                Main.mainMenu(university);
            }
        }

        return 0;
    }

    public static void addCourse(University university){
        int counter = 1;
        for (College college: university.getColleges()){
            System.out.println((counter)+") " +college.getCollege_name());
            counter++;
        }
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        int collegeOption = Integer.parseInt(response);
        College selectedCollege = university.getColleges().get(collegeOption-1);
        Course course = new Course();
        System.out.println("Enter Course ID");
        response = scanner.nextLine();
        course.setCourseID(response);
        System.out.println("Enter Course Name");
        response = scanner.nextLine();
        course.setCourse_Name(response);
        course.setCollege(selectedCollege);
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

    public static void main(String[] args) {










            }
        }







