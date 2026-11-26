package DomainModel;

import Activities.Discipline;
import DataSource.FileHandler;
import Membership.Member_type;
import Users.ActivityType;
import Users.BestTime;
import Users.Competitive_swimmer;
import Users.Member;
import Users.CompetitionResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final FileHandler fileHandler = new FileHandler("temp.csv");
    private final ClubManager clubManager = new ClubManager();
    private boolean changed = false;

    public Controller() {

    }

    public ArrayList<Member> getMemberArrayList() {
        return clubManager.getMemberArrayList();
    }

    public boolean createMember(String name, LocalDate dateOfBirth, ActivityType activityType, List<Discipline> disciplines) {
        boolean result = clubManager.createMember(name, dateOfBirth, activityType, disciplines);
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

    public boolean addCompetitionResult(int uuid, String competitionName, LocalDate date, Discipline discipline, int placement, double time) {
        Member member = clubManager.findMemberByUUID(uuid);
        if (member instanceof Competitive_swimmer swimmer) {
           CompetitionResult result = new CompetitionResult(competitionName, date, discipline, placement, time);
           swimmer.addCompetitionResult(result);
           changed = true;
           return true;
        }
        return false;
    }

    public ArrayList<Team> getTeams() {
        return clubManager.getTeams();
    }

    public boolean createTeam(ArrayList<Member> members, Member trainer, Member_type memberType) {
        return clubManager.createTeam(members, trainer, memberType);
    }
}
