import java.util.Scanner;

public class Student {
    private String name;
    private int rollno;
    private String sysid;


    public Student()
    {
        this.name=name;
        this.rollno=rollno;
        this.sysid=sysid;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollno()
    {
        return rollno;
    }
    public void setRollno(int rollno)
    {
        this.rollno= rollno;
    }
    public String getSysid()
    {
        return sysid;
    }
    public void setSysid(String sysid)
    {
        this.sysid= sysid;
    }

    public void ShowInfo()
    {
        System.out.println("Name: "+ name);
        System.out.println("System ID: "+ sysid);
        System.out.println("Roll No:"+ rollno);
    }

    public void inputDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Name: ");
        this.name = scanner.nextLine();

        System.out.print("Enter Roll No: ");
        this.rollno = scanner.nextInt();

        System.out.print("Enter System ID: ");
        this.sysid = scanner.next();
    }
}