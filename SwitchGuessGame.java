import java.util.Random;
import java.util.Scanner;

class RandomTool {
    private int number;
    private Random rand = new Random();

    // one value: 0 to limit-1
    RandomTool(int limit) {
        number = rand.nextInt(limit);
    }

    // two values: between min and max
    RandomTool(int min, int max) {
        number = min + rand.nextInt(max - min + 1);
    }

    int getNumber() {
        return number;
    }
}

public class SwitchGuessGame {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RandomTool tool = null;

        System.out.println("---- Guess The Number Game ----");
        System.out.println("1. Enter one number");
        System.out.println("2. Enter two numbers");
        System.out.print("Choose option: ");
        int choice = sc.nextInt();

        int low = 0, high = 0;

        switch (choice) {

            case 1:
                System.out.print("Enter limit value: ");
                int limit = sc.nextInt();
                tool = new RandomTool(limit);
                low = 0;
                high = limit - 1;
                break;

            case 2:
                System.out.print("Enter first number: ");
                int a = sc.nextInt();
                System.out.print("Enter second number: ");
                int b = sc.nextInt();

                if (a > b) {   // swap if needed
                    int temp = a;
                    a = b;
                    b = temp;
                }

                tool = new RandomTool(a, b);
                low = a;
                high = b;
                break;

            default:
                System.out.println("Invalid choice");
                return;
        }

        int secret = tool.getNumber();
        int life = 7;
        boolean win = false;

        System.out.println("\nNumber is generated!");
        System.out.println("Range: " + low + " to " + high);
        System.out.println("You have 7 lifelines.");

        while (life > 0) {
            System.out.print("\nEnter your guess: ");
            int guess = sc.nextInt();

            if (guess == secret) {
                System.out.println("🎉 You won! You guessed the correct number.");
                win = true;
                break;
            } 
            else if (guess < secret) {
                System.out.println("Hint: Go higher.");
            } 
            else {
                System.out.println("Hint: Go lower.");
            }

            life--;
            System.out.println("Lifelines left: " + life);
        }

        if (!win) {
            System.out.println("\nGame Over! No lifelines left.");
            System.out.println("The correct number was: " + secret);
        }

        sc.close();
    }
}