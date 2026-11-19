package UI;

import DomainModel.Controller;
import Users.ActivityType;
import Users.Member;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private final Controller controller = new Controller();

    public void startUI() {
        while (true) {
            String input = awaitInput(scanner, "Please make your choice.").toLowerCase();
            switch (input) {
                case "create" -> {
                    System.out.println("Creating a new member...");
                    System.out.println("Please provide member info (name, date, activity):");

                    String name = awaitInput(scanner, "Member name?");
                    LocalDate dateOfBirth = LocalDate.parse(awaitInput(scanner, "Date of birth (YYYY-MM-DD)?"));
                    ActivityType activityType = parseActivityType(scanner);

                    boolean result = controller.createMember(name, dateOfBirth, activityType);
                    if (result) {
                        System.out.println("Created the member");
                    } else System.out.println("Creation failed");
                }
                case "exit" -> {
                    return;
                }
                default -> System.out.println("Invalid action!");
            }
        }

    }

    private ActivityType parseActivityType(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("1. Passive");
            System.out.println("2. Exerciser");
            System.out.println("3. Competitive");
            int input = awaitInputInt(scanner, "What kind of activity is this member?");
            switch (input) {
                case 1 -> {
                    return ActivityType.PASSIVE;
                }
                case 2 -> {
                    return ActivityType.EXERCISER;
                }
                case 3 -> {
                    return ActivityType.COMPETITIVE;
                }
                default -> System.out.println("That's not a valid choice. Try again.");
            }
        }
    }

    private static String awaitInput(Scanner scanner, String text) {
        // Await player input
        System.out.println();
        // don't write the message line if an empty text string is provided
        if (!text.isEmpty()) {
            System.out.println(text);
        }
        System.out.print("> ");
        return scanner.nextLine();
    }

    private static int awaitInputInt(Scanner scanner, String text) {
        // Keep asking until the player enters a valid integer
        while (true) {
            try {
                // reuse await input and parse it to an integer. Reprompts if the input isn't an integer.
                return Integer.parseInt(awaitInput(scanner, text).trim());
            } catch (NumberFormatException e) {
                System.out.println("That is not a whole number! Enter a whole number ex: 1, 2, 3 or 4");
            }
        }
    }


}
