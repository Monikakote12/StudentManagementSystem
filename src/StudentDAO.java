import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    public void addStudent(Student student) {
        if (emailExists(student.getEmail())) {
            System.out.println("Email already exists! Please use a different email.");
            return;
        }

        String sql = "INSERT INTO student (name, age, email, course) VALUES (?, ?, ?, ?)";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getCourse());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Student added successfully!");
            }

        } catch (SQLException e) {

            if (e.getErrorCode() == 1062) {

                System.out.println(
                        "Email already exists! Please use a different email."
                );

            } else {

                System.out.println("Failed to add student");
                System.out.println("Error Code: " + e.getErrorCode());
                System.out.println("Message: " + e.getMessage());
            }
        }
    }

    public void viewAllList() {

        String sql = "SELECT * FROM student";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            System.out.println("\n==============================================================");
            System.out.println("                    STUDENT LIST");
            System.out.println("==============================================================");

            System.out.printf(
                    "%-5s %-20s %-5s %-30s %-15s%n",
                    "ID",
                    "NAME",
                    "AGE",
                    "EMAIL",
                    "COURSE"
            );

            System.out.println("--------------------------------------------------------------");

            boolean found = false;

            while (resultSet.next()) {

                found = true;

                System.out.printf(
                        "%-5d %-20s %-5d %-30s %-15s%n",
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("course")
                );
            }

            System.out.println("==============================================================");

            if (!found) {
                System.out.println("No students found!");
            }

        } catch (SQLException e) {

            System.out.println("Failed to retrieve students");
            e.printStackTrace();
        }
    }

    public void findStudentById(int id) {


        String sql = "SELECT * FROM student WHERE id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    System.out.println("\n====================================");
                    System.out.println("          STUDENT DETAILS");
                    System.out.println("====================================");

                    System.out.println("Student ID : " + resultSet.getInt("id"));
                    System.out.println("Name       : " + resultSet.getString("name"));
                    System.out.println("Age        : " + resultSet.getInt("age"));
                    System.out.println("Email      : " + resultSet.getString("email"));
                    System.out.println("Course     : " + resultSet.getString("course"));

                    System.out.println("====================================");

                } else {
                    System.out.println("Student not found!");
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed to find student");
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {

        if (!studentExists(student.getId())) {
            System.out.println("Student not found!");
            return;
        }

        if (emailExistsForOtherStudent(student.getEmail(), student.getId())) {
            System.out.println("Email already exists for another student! Please use a different email.");
            return;
        }

        String sql = "UPDATE student SET name = ?, age = ?, email = ?, course = ? WHERE id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getCourse());
            statement.setInt(5, student.getId());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Student updated successfully!");
            }

        } catch (SQLException e) {

            System.out.println("Failed to update student");
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {

        String sql = "DELETE FROM student WHERE id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found!");
            }

        } catch (SQLException e) {

            System.out.println("Failed to delete student");
            e.printStackTrace();
        }

    }

    public boolean emailExists(String email) {

        String sql = "SELECT id FROM student WHERE email = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {

                return resultSet.next();
            }

        } catch (SQLException e) {

            System.out.println("Error checking email: " + e.getMessage());
            return false;
        }
    }

    public boolean emailExistsForOtherStudent(String email, int id) {

        String sql = "SELECT id FROM student WHERE email = ? AND id != ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, email);
            statement.setInt(2, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                return resultSet.next();
            }

        } catch (SQLException e) {

            System.out.println("Error checking email: " + e.getMessage());
            return false;
        }
    }

    public boolean studentExists(int id) {

        String sql = "SELECT id FROM student WHERE id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                return resultSet.next();
            }

        } catch (SQLException e) {

            System.out.println("Error checking student: " + e.getMessage());
            return false;
        }
    }
}
