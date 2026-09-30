public class Student {
    private int id;
    private String name;
    private int age;
    private String email;
    private String course;

    //constructor for getting all name, age, email, course
    public Student(String name, int age, String email, String course){
        this.name = name;
        this.age = age;
        this.email = email;
        this.course = course;
    }

    //constructor for getting with id
    public Student(int id, String name, int age, String email, String course){
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.course = course;
    }

    //getter for all
    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public String getEmail(){
        return email;
    }

    public String getCourse(){
        return course;
    }

    //setter for all


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString(){
        return "ID: " + id + ",Name: " + name + ",Age: " + age + ",Email: " + email + ",Course: " + course;
    }

}
