import java.util.Scanner;

public class Semester extends Department{
    private int semesterNo;
    private int noOfSubjects;

    public Semester() {
        super();
        this.semesterNo= semesterNo;
        this.noOfSubjects=noOfSubjects;
    }

    public int getSemesterNo() {
        return semesterNo;
    }



    public int getNoOfSubjects() {
        return noOfSubjects;
    }

    public void setSemesterNo(int semesterNo) {
        this.semesterNo = semesterNo;
    }



    public void setNoOfSubjects(int noOfSubjects) {
        this.noOfSubjects = noOfSubjects;
    }

    public void ShowInfo()
    {
        super.ShowInfo();

        super.getDepartmentName();
        System.out.println("Dpertment Name:"+ getDepartmentName());
        System.out.println("Semester No:"+semesterNo);
        System.out.println("No of Subjects:"+noOfSubjects);

    }


    public void inputDetails() {
        super.inputDetails();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Semester No: ");
        this.semesterNo = scanner.nextInt();

        System.out.print("Enter No of Subjects: ");
        this.noOfSubjects = scanner.nextInt();
    }
}