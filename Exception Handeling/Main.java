package Abdullah;
import java.util.InputMismatchException;

import java.util.Scanner;

public class Main {
    public static void checkAboveThousand(int a, int b) throws CustomsExceptions {
        if (a > 1000 || b > 1000) {
            throw new CustomsExceptions("The number cannot be above 1000");
        }
    }

    public static void checkNegativeNumbers(int a, int b) throws  CustomsExceptions{
        if (a < 0 || b < 0) {
            throw new CustomsExceptions("The numbers cannot be negative.");
        }
    }

    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        
        try {
            do {
                try {
                    System.out.println("Enter first number: ");
                    int num1 = sc.nextInt();

                    System.out.println("Enter second number: ");
                    int num2 = sc.nextInt();
                    
                    checkAboveThousand(num1, num2);
                    
                    // Display menu
                    System.out.println("Select operation:");
                    System.out.println("1. Addition");
                    System.out.println("2. Subtraction");
                    System.out.println("3. Multiplication");
                    System.out.println("4. Division");
                    System.out.print("Enter your choice (1-4): ");
                    int operation = sc.nextInt();

                    Calculator calculator = new Calculator();

                    switch (operation) {
                        case 1:
                            calculator.add(num1, num2);
                            break;
                        case 2:
                            calculator.subs(num1, num2);
                            break;
                        case 3:
                            calculator.multiply(num1, num2);
                            break;
                        case 4:
                            calculator.divide(num1, num2);
                            break;
                        default:
                            System.out.println("Invalid operation choice.");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter integers only.");
                    sc.next(); 
                } catch (ArithmeticException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (CustomsExceptions e) {
                    System.out.println("Following Exception occurred: " + e.getMessage());
                }
               

                System.out.println("Click 1 to perform again, or any other key to exit:");
                choice = sc.nextInt();

            } while (choice == 1); 

        } finally {
            System.out.println("Execution Successful");
            sc.close(); 
        }
    }
}