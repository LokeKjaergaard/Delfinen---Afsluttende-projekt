package UI;

import Activities.Discipline;
import DomainModel.Controller;
import Membership.Member_type;

import Users.ActivityType;
import Users.Member;
import Users.Competitive_swimmer;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

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
                //Shows a list of all members
                case "members" -> {
                    ArrayList<Member> members = controller.getMemberArrayList();
                    if (members.isEmpty()) {
                        System.out.println("There are no members in the club.");
                    } else {
                        for (Member member : members) {
                            System.out.println(formatMember(member));
                        }
                    }

                }
                case "add time" -> {
                    System.out.println("Registering a new time...");
                    for (Member m : controller.getMemberArrayList()) {
                        if (m instanceof Competitive_swimmer) {
                            System.out.println(m.getUUID() + " - " + m.getName());
                        }
                    }
                    int uuid = awaitInputInt(scanner, "Swimmer UUID?");
                    Discipline discipline = parseDiscipline(scanner);
                    double time = awaitInputDouble(scanner, "Time in seconds (e.g. 32.45)?");
                    LocalDate date = LocalDate.parse(awaitInput(scanner, "Date of the time (YYYY-MM-DD)?"));

                    if (controller.addTime(uuid, discipline, time, date)) {
                        System.out.println("Time registered");
                    } else System.out.println("No competitive swimmer with that UUID");
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
public String formatMember(Member member) {
String name = member.getName();
int age = member.getAge();
int uuid = member.getUUID();
Member_type memberType = member.getMemberType();
ActivityType activityType = member.getActivityType();
return uuid + " | " + name + " | " + age + " | " + memberType + " | " + activityType;
}

    private Discipline parseDiscipline(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("1. Crawl");
            System.out.println("2. Butterfly");
            System.out.println("3. Backstroke");
            System.out.println("4. Breaststroke");
            int input = awaitInputInt(scanner, "Which discipline?");
            switch (input) {
                case 1 -> { return Discipline.CRAWL; }
                case 2 -> { return Discipline.BUTTERFLY; }
                case 3 -> { return Discipline.BACKSTROKE; }
                case 4 -> { return Discipline.BREASTSTROKE; }
                default -> System.out.println("That's not a valid choice. Try again.");
            }
        }
    }

    private static double awaitInputDouble(Scanner scanner, String text) {
        while (true) {
            try {
                // replace(",", ".") så både 32,45 og 32.45 virker
                return Double.parseDouble(awaitInput(scanner, text).trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("That is not a number! Enter e.g. 32.45");
            }
        }
    }


}
