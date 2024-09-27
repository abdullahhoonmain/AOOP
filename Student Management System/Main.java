import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Subject subject = new Subject();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Input Student Details");
            System.out.println("2. Input Department Details");
            System.out.println("3. Input Semester Details");
            System.out.println("4. Input Subject Details");
            System.out.println("5. Show All Details");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    subject.inputDetails();
                    break;
                case 2:
                    subject.inputDetails();
                    break;
                case 3:
                    subject.inputDetails();
                    break;
                case 4:
                    subject.inputDetails();
                    break;
                case 5:
                    subject.ShowInfo();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();

    }
}