package hexlet.code;

import javax.swing.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Cli {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random rand = new Random();
    private static final int NUMBER_OF_OPTIONS = 7;
    private static final ArrayList<Integer> availableOptions = new ArrayList<>() {{ for (int i = 0; i < NUMBER_OF_OPTIONS; i ++)  add(i);}};

    public static void run() {
        showMenu();
        String name;
        int choice = scanner.nextInt();
        System.out.println();
        if (choice == 0 || !availableOptions.contains(choice)) return;
        name = greet();
        if (choice == 1) return;
        int result = playGame(choice);
        showResult(result, name);
    }


    public static String greet() {
        System.out.println("Welcome to the Brain Games!");
        System.out.println("May I have your name?");
        String name = scanner.next();
        System.out.printf("Hello, %s!\n", name);
        return name;
    }

    public static void showMenu() {
        System.out.println("Please enter the game number and press Enter.");
        System.out.println("1 - Greet");
        System.out.println("2 - Even");
        System.out.println("3 - Calc");
        System.out.println("4 - GCD");
        System.out.println("5 - Progression");
        System.out.println("6 - Prime");
        System.out.println("0 - Exit");
        System.out.print("Your choice: ");
    }

    public static void printTask(int choice) {
        switch (choice) {
            case  2:
                System.out.println("Answer 'yes' if number even otherwise answer 'no'.");
            case  3:
                System.out.println("What is the result of the expression?");
            case 4:
                System.out.println("Find the greatest common divisor of given numbers.");
            case 5:
                System.out.println("What number is missing in the progression?");
            case 6:
                System.out.println("Answer 'yes' if given number is prime. Otherwise answer 'no'.");
        }
    }

    public static String[] getQuestionAndAnswer(int choice) {
        if (choice == 2) {
            int question = rand.nextInt(100);
            String answer = (question % 2 == 0 ? "yes" : "no");
            return new String[]{Integer.toString(question), answer};
        } else if (choice == 3) {
            int answer;
            int a = rand.nextInt(100);
            int b = rand.nextInt(100);
            String[] operations = new String[] {"+", "-", "*"};
            int operation = rand.nextInt(3);
            if (operation == 0) {
                answer = a + b;
            } else if (operation == 1) {
                answer = a - b;
            } else {
                answer = a * b;
            }
            return new String[] {String.format("%d %s %d", a, operations[operation], b), Integer.toString(answer)};
        } else if (choice == 4) {
            int a = rand.nextInt(100);
            int b = rand.nextInt(100);
            int gcd = 1;
            for (int i = 1; i <= a && i <= b; i++) {
                if (a % i == 0 && b % i == 0) gcd = i;
            }
            return new String[]{String.format("%d %d", a, b), Integer.toString(gcd)};
        } else if (choice == 5){
            int start = rand.nextInt(100);
            int step = rand.nextInt(10);
            int numberOfElements = 4 + (int) ( Math.random() * 10);
            int index = rand.nextInt(numberOfElements);
            int answer = start + index * step;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numberOfElements; i++) {
                if (i != index) {
                    sb.append(start + step * i).append(" ");
                } else {
                    sb.append(".. ");
                }
            }
            sb.deleteCharAt(sb.lastIndexOf(" "));
            return new String[] {sb.toString(), Integer.toString(answer)};
        } else if (choice == 6) {
            Integer integer = rand.nextInt(1000);
            BigInteger bigInteger = BigInteger.valueOf(integer);
            boolean probablePrime = bigInteger.isProbablePrime((int) Math.log(integer));
            String answer = (probablePrime) ? "yes" : "no";
            return new String[] {Integer.toString(integer), answer};
        }
        else {
            return new String[2];
        }
    }

    public static int playGame(int choice) {
        printTask(choice);
        String respond;
        for (int i = 0; i < 3; i++) {
            String[] qa = getQuestionAndAnswer(choice);
            System.out.printf("Question: %s\n", qa[0]);
            respond = scanner.next();
            System.out.printf("Your answer: %s\n", respond);
            if (Objects.equals(respond, qa[1])) {
                System.out.println("Correct!");
            } else {
                System.out.printf("'%s' is wrong answer ;(. Correct answer was '%s'.\n", respond, qa[1]);
                return 0;
            }
        }
        return 1;
    }

    public static void showResult(int result, String name) {
        if (result == 1) {
            System.out.printf("Congratulations, %s!\n", name);
        } else {
            System.out.printf("Let's try again, %s!\n", name);
        }
    }

}
