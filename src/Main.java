import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO studentDAO = new StudentDAO();


        while (true) {

            System.out.println("\n====================================");
            System.out.println("     STUDENT MANAGEMENT SYSTEM");
            System.out.println("====================================");

            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.println("====================================");

            System.out.print("\nEnter your choice: ");

            int choice;

            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {

                case 1:

                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();

                    if (!Validation.isValidName(name)) {
                        System.out.println("Invalid name!");
                        break;
                    }

                    System.out.print("Enter age: ");

                    int age;

                    try {
                        age = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid age! Please enter a number.");
                        sc.nextLine();
                        break;
                    }

                    if (!Validation.isValidAge(age)) {
                        System.out.println("Invalid age! Age must be between 1 and 100.");
                        break;
                    }

                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    if (!Validation.isValidEmail(email)) {
                        System.out.println("Invalid email!");
                        break;
                    }

                    System.out.print("Enter course: ");
                    String course = sc.nextLine();

                    if (!Validation.isValidCourse(course)) {
                        System.out.println("Invalid course!");
                        break;
                    }

                    Student student = new Student(
                            name,
                            age,
                            email,
                            course
                    );

                    studentDAO.addStudent(student);

                    break;

                case 2:

                    studentDAO.viewAllList();

                    break;

                case 3:
                    System.out.print("Enter student ID: ");

                    int searchId;

                    try {
                        searchId = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid ID! Please enter a number.");
                        sc.nextLine();
                        break;
                    }

                    if (!Validation.isValidId(searchId)) {
                        System.out.println("Invalid ID! ID must be greater than 0.");
                        break;
                    }

                    studentDAO.findStudentById(searchId);
                    break;

                case 4:
                    System.out.print("Enter student ID to update: ");

                    int updateId;

                    try {
                        updateId = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid ID! Please enter a number.");
                        sc.nextLine();
                        break;
                    }

                    if (!Validation.isValidId(updateId)) {
                        System.out.println("Invalid ID! ID must be greater than 0.");
                        break;
                    }

                    if (!studentDAO.studentExists(updateId)) {
                        System.out.println("Student not found!");
                        break;
                    }

                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();

                    if (!Validation.isValidName(newName)) {
                        System.out.println("Invalid name!");
                        break;
                    }

                    System.out.print("Enter new age: ");

                    int newAge;

                    try {
                        newAge = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid age! Please enter a number.");
                        sc.nextLine();
                        break;
                    }

                    if (!Validation.isValidAge(newAge)) {
                        System.out.println("Invalid age! Age must be between 1 and 100.");
                        break;
                    }

                    System.out.print("Enter new email: ");
                    String newEmail = sc.nextLine();

                    if (!Validation.isValidEmail(newEmail)) {
                        System.out.println("Invalid email!");
                        break;
                    }

                    System.out.print("Enter new course: ");
                    String newCourse = sc.nextLine();

                    if (!Validation.isValidCourse(newCourse)) {
                        System.out.println("Invalid course!");
                        break;
                    }

                    Student updatedStudent = new Student(
                            updateId,
                            newName,
                            newAge,
                            newEmail,
                            newCourse
                    );

                    studentDAO.updateStudent(updatedStudent);
                    break;

                case 5:
                    System.out.print("Enter student ID to delete: ");

                    int deleteId;

                    try {
                        deleteId = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid ID! Please enter a number.");
                        sc.nextLine();
                        break;
                    }

                    if (!Validation.isValidId(deleteId)) {
                        System.out.println("Invalid ID! ID must be greater than 0.");
                        break;
                    }

                    System.out.print("Are you sure you want to delete this student? (yes/no): ");
                    String confirm = sc.nextLine();

                    if (!confirm.equalsIgnoreCase("yes")) {
                        System.out.println("Delete operation cancelled.");
                        break;
                    }

                    studentDAO.deleteStudent(deleteId);
                    break;

                case 6:

                    System.out.println("\nThank you for using Student Management System!");
                    sc.close();
                    return;

                default:

                    System.out.println("Invalid choice! Please try again.");
            }
        }


    }
}