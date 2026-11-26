package UI;

import Activities.Discipline;
import DomainModel.Controller;
import DomainModel.Team;
import Membership.Member_type;
import Users.ActivityType;
import Users.Member;
import Users.Competitive_swimmer;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import java.util.ArrayList;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private final Controller controller = new Controller();

    public void startUI() {
        while (true) {
            printHelp();
            String input = awaitInput(scanner, "Please make your choice.").toLowerCase();
            switch (input) {
                case "create" -> createMember();
                case "members" -> listMembers();
                case "add time" -> addTime();
                case "fees" -> showAnnualFees();
                case "show teams" -> showTeams();
                case "exit" -> {
                    return;
                }
                default -> System.out.println("Invalid action!");
            }
        }
    }

    // PLEASE: write any new commands in this section!
    private static void printHelp() {
        System.out.println();
        System.out.println("Possible commands:");
        System.out.println("- create - creates a new member to the database.");
        System.out.println("- members - lists all current members");
        System.out.println("- add time - create a new compilation time, for a given discipline, into the database");
        System.out.println("- fees - lists all members and their annual membership fees");
        System.out.println("- show teams - prints all Members of either junior or senior");
        System.out.println("- show teams - prints all Members of a specified team");
        System.out.println("- exit - terminates the program");
    }

    private ActivityType parseActivityType() {
        while (true) {
            System.out.println();
            System.out.println("What kind of activity type is this member?");
            System.out.println("1. Passive");
            System.out.println("2. Exerciser");
            System.out.println("3. Competitive");
            int input = awaitInputInt(scanner, "Type in the corresponding number as seem above:");
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
                case 0 -> throw new CancellationException();

                default -> System.out.println("That's not a valid choice. Try again.");
            }
        }
    }


    /**
     * Parses a LocalDate (date) based on action.
     *
     * @param action 0 = generic, 1 = reprompt if the date is in the future.
     * @return LocalDate
     */
    public LocalDate promptLocalDateFromString(int action, String text) {
        while (true) {
            try {
                LocalDate date = LocalDate.parse(awaitInput(scanner, text + " (YYYY-MM-DD)?"));
                switch (action) {
                    case 0 -> {
                        return date;
                    }
                    case 1 -> {
                        LocalDate currentDate = LocalDate.now();
                        if (date.isAfter(currentDate)) {
                            System.out.println("Date cannot be in the future!");
                            continue; // continue restarts the loop.
                        }
                        return date;
                    }
                    default -> throw new IllegalArgumentException(
                            "Invalid action!"
                    );
                }
            } catch (DateTimeException e) {
                System.out.println("Not a valid date! Try again.");
            } catch (Exception e) {
                System.out.println("Not a valid date!" + e.getMessage());
            }
        }
    }

    private String promptMemberName() {
        while (true) {
            String name = awaitInput(scanner, "Member name?");
            if (name.isEmpty()) {
                continue; // continue restarts the loop
            }
            return name;
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

    private static boolean awaitInputBool(Scanner scanner, String text) {
        while (true) {
            String input = awaitInput(scanner, text + " (type yes/no)").toLowerCase();

            switch (input) {
                case "yes" -> {
                    return true;
                }
                case "no" -> {
                    return false;
                }
                default -> System.out.println("No a valid choice! (Only yes/no is allowed)");
            }
        }
    }

    public String formatMember(Member member) {
        String name = member.getName();
        int age = member.getAge(LocalDate.now());
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
                case 1 -> {
                    return Discipline.CRAWL;
                }
                case 2 -> {
                    return Discipline.BUTTERFLY;
                }
                case 3 -> {
                    return Discipline.BACKSTROKE;
                }
                case 4 -> {
                    return Discipline.BREASTSTROKE;
                }
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

    // command methods:

    private void createMember() {
        System.out.println("Creating a new member...");
        System.out.println("Please provide member info (name, date, activity):");

        try {
            String name = promptMemberName();
            LocalDate dateOfBirth = promptLocalDateFromString(1, "Date of birth");
            ActivityType activityType = parseActivityType();
            // creates the member and adds them to the memberList
            boolean result = controller.createMember(name, dateOfBirth, activityType);
            // result handling, prints a message if it fails or not for some unknown reason.
            if (result) {
                System.out.println("Created the member");
            } else System.out.println("Creation failed");
        } catch (CancellationException e) {
            System.out.println("Cancelled creation");
        } catch (Exception e) {
            System.out.println("an error occurred. " + e.getMessage());
        }
    }

    private void listMembers() {
        ArrayList<Member> members = controller.getMemberArrayList();
        if (members.isEmpty()) {
            System.out.println("There are no members in the club.");
        } else {
            for (Member member : members) {
                System.out.println(formatMember(member));
            }
        }
    }

    private void showAnnualFees() {
        ArrayList<Member> members = controller.getMemberArrayList();
        if (members.isEmpty()) {
            System.out.println("There are no members in the club.");
        } else {
            System.out.println("Annual membership fees");

            for (Member member : members) {
                int fee = member.calculateFee(LocalDate.now());

                System.out.println(member.getUUID() + " | " + member.getName() + " | " + fee + " kr.");
            }
        }
    }

    private void addTime() {
        System.out.println("Registering a new time...");
        for (Member m : controller.getMemberArrayList()) {
            if (m instanceof Competitive_swimmer) {
                System.out.println(m.getUUID() + " - " + m.getName());
            }
        }
        int uuid = awaitInputInt(scanner, "Swimmer UUID?");
        Discipline discipline = parseDiscipline(scanner);
        double time = awaitInputDouble(scanner, "Time in seconds (e.g. 32.45)?");
        LocalDate date = promptLocalDateFromString(1, "Enter the date of the time");

        if (controller.addTime(uuid, discipline, time, date)) {
            System.out.println("Time registered");
        } else System.out.println("No competitive swimmer with that UUID");
    }

    //todo: prase teams instead of members
    private void showTeams() {
        ArrayList<Member> teams = controller.getMemberArrayList();
        System.out.println("Showing teams...");
        if (teams.isEmpty()) {
            System.out.println("There's no teams to display.");
            return;
        }
        System.out.println("no - Senior only");
        System.out.println("yes - Junior only");
        boolean sortByJunior = awaitInputBool(scanner, "Show senior or junior teams?");
        printMembersByMemberType(sortByJunior);
    }

    private void printTeams(boolean isJunior, ArrayList<Team> teamArrayList) {
        //todo
        if (teamArrayList.isEmpty()) {
            System.out.println("There's no teams to display.");
            return;
        }

        Member_type memberType;

        if (isJunior) {
            memberType = Member_type.JUNIOR;
        } else {
            memberType = Member_type.SENIOR;
        }

        for (Team team : teamArrayList) {
            if (team.getTeamMemberType() == memberType) {
                if (team.getTeamName().isEmpty()) {
                    System.out.println(team.getTeamNumber() + " | Trainer: " + team.getTeamTrainer().getName() + " | Team member(s): " + team.getAllTeamMembers().size());
                    continue;
                }
                System.out.println(team.getTeamNumber() + " | Name: " + team.getTeamName() + "| Trainer: " + team.getTeamTrainer().getName() + " | Team member(s): " + team.getAllTeamMembers().size());
            }
        }
    }

    private void printMembersByMemberType(boolean isJunior) {
        ArrayList<Member> members = controller.getMemberArrayList();
        if (members.isEmpty()) {
            System.out.println("There's no member(s) to display.");
            return;
        }
        for (Member member : members) {
            if (member.isJunior() == isJunior) {
                System.out.println(formatMember(member));
            }
        }
    }
}
