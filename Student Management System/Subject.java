import java.util.Scanner;

public class Subject extends Semester {
    private String subjectName;
    private int courseId;
    private int creditHours;

    public Subject() {
        super();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public void ShowInfo()
    {

        super.ShowInfo();
        System.out.println("Subject Name:"+ subjectName);
        System.out.println("Course Id:"+ courseId);
        System.out.println("Credit Hours:"+ creditHours);

    }

    public void inputDetails() {
        super.inputDetails();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Subject Name: ");
        this.subjectName = scanner.nextLine();

        System.out.print("Enter Course Id: ");
        this.courseId = scanner.nextInt();

        System.out.print("Enter Credit Hours: ");
        this.creditHours = scanner.nextInt();
    }
}