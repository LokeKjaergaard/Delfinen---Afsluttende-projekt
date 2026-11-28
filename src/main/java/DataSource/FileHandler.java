package DataSource;

import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import Users.Member;
import Users.Competitive_swimmer;
import Activities.Discipline;

public class FileHandler {
    private final String fileName;
    private final String HEADER = "UUID;name;dateOfBirth;activityType;disciplines";

    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    public void saveMembers(List<Member> members) {

        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println(HEADER);

            for (Member member : members) {
                String line = member.getUUID() + ";" + member.getName() + ";" + member.getDateOfBirth() + ";" + member.getActivityType();
                String disciplines = "";
                if (member instanceof Competitive_swimmer swimmer) {
                    for (Discipline discipline : swimmer.getDisciplines()) {
                        if (!disciplines.isEmpty()) {
                            disciplines += ",";
                        }
                        disciplines += discipline.name();

                    }
                }
                writer.println(line + ";" + disciplines);
            }

        } catch (IOException e) {
            System.out.println("Error saving members: " + e.getMessage());
        }

    }
}
