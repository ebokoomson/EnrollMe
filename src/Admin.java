import org.w3c.dom.ls.LSOutput;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Admin {


    public static void admin_page(University university) {

       
        Scanner scanner = new Scanner(System.in);

        String password;
        String response;
        String repeat_response = "y";
        String repeat_response2="y";


        System.out.println("Please enter super secret password");
        password = scanner.nextLine();

        if (password.equals("bnm")) {


                System.out.println("1. Add courses");
                System.out.println("2. Add Majors");
                System.out.println("3. Display number of Students");
                System.out.println("4. Display number of Professors");
                System.out.println("5. Display number of Courses");

                response = scanner.nextLine();


                if (response.equals("1")) {


                    while (repeat_response.equals("y")) {
                    Course course= new Course();
                    System.out.println("Enter Course name");
                    response = scanner.nextLine();
                    course.setCourse_Name(response);
                    System.out.println("Enter course college");
                    System.out.println("1. College of Computing and Informatics");
                    System.out.println("2. College of Business");
                    System.out.println("3. College of Engineering");
                    System.out.println("4. College of Arts and Science");
                    System.out.println("5. College of Westphal");
                    response = scanner.nextLine();
                    System.out.println(response);
                    switch (response) {
                        case "1":
                            System.out.println("Its here");
                            course.setCollege(university.getColleges().get(0));
                            break;


                        case "2":
                            course.setCollege(university.getColleges().get(1));
                            break;

                        case "3":
                            course.setCollege(university.getColleges().get(2));
                            break;
                        case "4":
                            course.setCollege(university.getColleges().get(3));
                            break;


                        case "5":
                            course.setCollege(university.getColleges().get(4));
                            break;


                        default:
                            System.out.println("Incorrect input");

                    }



                    System.out.println("Add another course? (y/n)");
                    repeat_response = scanner.nextLine();


                }

            }

                else if (response.equals("2")){

                    while(repeat_response2.equals("y")){
                        Major major = new Major();
                        //Course course= new Course();
                        System.out.println("Enter Major name");
                        response = scanner.nextLine();
                        major.setMajor_name(response);
                        //course.setCourse_Name(;
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

            System.out.println("Go back to main menu? (y/n)");
            repeat_response = scanner.nextLine();
            if (repeat_response.equals("y")){
                Main.mainMenu(university);
            }
        }
    }

    public static void main(String[] args) {










            }
        }







