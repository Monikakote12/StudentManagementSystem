public class Validation {

    public static boolean isValidName(String name) {
        return name != null &&
                !name.trim().isEmpty();
    }

    public static boolean isValidAge(int age) {
        return age >= 1 && age <= 100;
    }

    public static boolean isValidEmail(String email) {
        return email != null &&
                email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static boolean isValidCourse(String course) {
        return course != null &&
                !course.trim().isEmpty();
    }

    public static boolean isValidId(int id) {
        return id > 0;
    }
}