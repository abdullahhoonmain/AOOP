import java.util.Scanner;

public class Department extends Student{

    private String departmentName;
    private String program;

    public Department()
    {
        super();

        this.departmentName= departmentName;
        this.program= program;
    }

    public void setDepartmentName(String DepartmentName)
    {
        this.departmentName=departmentName;
    }
    public String getDepartmentName()
    {
        return departmentName;
    }
    public void setProgram(String Program)
    {
        this.program= program;
    }
    public String getProgram()
    {
        return program;
    }
    @Override
    public void ShowInfo()
    {
        super.ShowInfo();

        System.out.println("Department Name:"+ departmentName);
        System.out.println("Programe Name:"+ program);
    }

    public void inputDetails() {
        super.inputDetails();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Department Name: ");
        this.departmentName = scanner.nextLine();

        System.out.print("Enter Program Name: ");
        this.program = scanner.nextLine();
    }
}