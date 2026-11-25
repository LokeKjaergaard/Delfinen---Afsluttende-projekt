package DomainModel;

import Activities.Discipline;
import DataSource.FileHandler;
import Users.ActivityType;
import Users.BestTime;
import Users.Competitive_swimmer;
import Users.Member;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private final FileHandler fileHandler = new FileHandler("temp.csv");
    private final ClubManager clubManager = new ClubManager();
    private boolean changed = false;

    public Controller() {

    }

    public ArrayList<Member> getMemberArrayList() {
        return clubManager.getMemberArrayList();
    }

    public boolean createMember(String name, LocalDate dateOfBirth, ActivityType activityType) {
        boolean result = clubManager.createMember(name, dateOfBirth, activityType);
        if (result) {
            changed = true;
        }
        return result;
    }

    public boolean deleteMember(Member member) {
        boolean result = clubManager.deleteMember(member);
        if (result) {
            changed = true;
        }
        return result;
    }
    public boolean addTime(int uuid, Discipline discipline, double time, LocalDate date) {
        Member member = clubManager.findMemberByUUID(uuid);
        if (member instanceof Competitive_swimmer swimmer) {
            swimmer.addTime(discipline, new BestTime(time, date));
            changed = true;
            return true;
        }
        return false;
    }
}
