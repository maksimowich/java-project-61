package hexlet.code;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Cli {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RAND = new Random();
    private static final int NUMBER_OF_OPTIONS = 7;

    private static final int EVEN = 2;
    private static final int CALC = 3;
    private static final int GCD = 4;
    private static final int PROGRESSION = 5;
    private static final int PRIME = 6;
    private static final int UPPER_LIMIT = 100;
    private static final int STEP = 3;
    private static final int LOWER_BOUND = 5;
    private static final int NUMBER_OF_OPERATIONS = 3;
    private static final int MULTIPLIER = 4;
    private static final int MAX_VALUE = 1000;

    private static final ArrayList<Integer> AVAILABLE_OPTIONS = new ArrayList<>() {
        { for (int i = 0; i < NUMBER_OF_OPTIONS; i++)  {
                add(i); } }
    };

    public static void run() {
        showMenu();
        String name;
        int choice = SCANNER.nextInt();
        System.out.println();
        if (choice == 0 || !AVAILABLE_OPTIONS.contains(choice)) {
            return;
        }
        name = greet();
        if (choice == 1) {
            return;
        }
        int result = playGame(choice);
        showResult(result, name);
    }


    public static String greet() {
        System.out.println("Welcome to the Brain Games!");
        System.out.println("May I have your name?");
        String name = SCANNER.next();
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
        if (choice == AVAILABLE_OPTIONS.get(EVEN)) {
            System.out.println("Answer 'yes' if number even otherwise answer 'no'.");
        } else if (choice == AVAILABLE_OPTIONS.get(CALC)) {
            System.out.println("What is the result of the expression?");
        } else if (choice == AVAILABLE_OPTIONS.get(GCD)) {
            System.out.println("Find the greatest common divisor of given numbers.");
        } else if (choice == AVAILABLE_OPTIONS.get(PROGRESSION)) {
            System.out.println("What number is missing in the progression?");
        } else if (choice == AVAILABLE_OPTIONS.get(PRIME)) {
            System.out.println("Answer 'yes' if given number is prime. Otherwise answer 'no'.");
        } else {
            System.out.println("No task for this number.");
        }
    }

    public static String[] getQuestionAndAnswer(int choice) {
        if (choice == AVAILABLE_OPTIONS.get(EVEN)) {
            int question = RAND.nextInt(UPPER_LIMIT);
            String answer = (question % 2 == 0 ? "yes" : "no");
            return new String[]{Integer.toString(question), answer};
        } else if (choice == AVAILABLE_OPTIONS.get(CALC)) {
            int answer;
            int a = RAND.nextInt(UPPER_LIMIT);
            int b = RAND.nextInt(UPPER_LIMIT);
            String[] operations = new String[] {"+", "-", "*"};
            int operation = RAND.nextInt(NUMBER_OF_OPERATIONS);
            if (operation == 0) {
                answer = a + b;
            } else if (operation == 1) {
                answer = a - b;
            } else {
                answer = a * b;
            }
            return new String[] {String.format("%d %s %d", a, operations[operation], b), Integer.toString(answer)};
        } else if (choice == AVAILABLE_OPTIONS.get(GCD)) {
            int a = RAND.nextInt(UPPER_LIMIT);
            int b = RAND.nextInt(UPPER_LIMIT);
            int gcd = 1;
            for (int i = 1; i <= a && i <= b; i++) {
                if (a % i == 0 && b % i == 0) {
                    gcd = i;
                }
            }
            return new String[]{String.format("%d %d", a, b), Integer.toString(gcd)};
        } else if (choice == AVAILABLE_OPTIONS.get(PROGRESSION)) {
            int start = RAND.nextInt(UPPER_LIMIT);
            int step = RAND.nextInt(STEP);
            int numberOfElements = LOWER_BOUND + (int) (Math.random() * LOWER_BOUND);
            int index = RAND.nextInt(numberOfElements);
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
        } else if (choice == AVAILABLE_OPTIONS.get(PRIME)) {
            Integer integer = RAND.nextInt(MAX_VALUE);
            BigInteger bigInteger = BigInteger.valueOf(integer);
            boolean probablePrime = bigInteger.isProbablePrime((int) Math.log(integer));
            String answer = (probablePrime) ? "yes" : "no";
            return new String[] {Integer.toString(integer), answer};
        } else {
            return new String[2];
        }
    }

    public static int playGame(int choice) {
        printTask(choice);
        String respond;
        for (int i = 0; i < NUMBER_OF_OPERATIONS; i++) {
            String[] qa = getQuestionAndAnswer(choice);
            System.out.printf("Question: %s\n", qa[0]);
            respond = SCANNER.next();
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
