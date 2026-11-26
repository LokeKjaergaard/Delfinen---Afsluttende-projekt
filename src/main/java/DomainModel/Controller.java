package DomainModel;

import Activities.Discipline;
import DataSource.FileHandler;
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
    private final MemberManager memberManager = new MemberManager();
    private boolean changed = false;

    public Controller() {

    }

    public ArrayList<Member> getMemberArrayList() {
        return memberManager.getMemberArrayList();
    }

    public boolean createMember(String name, LocalDate dateOfBirth, ActivityType activityType, List<Discipline> disciplines) {
        boolean result = memberManager.createMember(name, dateOfBirth, activityType, disciplines);
        if (result) {
            changed = true;
        }
        return result;
    }

    public boolean deleteMember(Member member) {
        boolean result = memberManager.deleteMember(member);
        if (result) {
            changed = true;
        }
        return result;
    }
    public boolean addTime(int uuid, Discipline discipline, double time, LocalDate date) {
        Member member = memberManager.findMemberByUUID(uuid);
        if (member instanceof Competitive_swimmer swimmer) {
            swimmer.addTime(discipline, new BestTime(time, date));
            changed = true;
            return true;
        }
        return false;
    }

    public boolean isCompetitiveSwimmer(int uuid) {
        Member member = memberManager.findMemberByUUID(uuid);
        return member instanceof Competitive_swimmer;
    }

    public boolean addCompetitionResult(int uuid, String competitionName, LocalDate date, Discipline discipline, int placement, double time) {
        Member member = memberManager.findMemberByUUID(uuid);
        if (member instanceof Competitive_swimmer swimmer) {
            if (!swimmer.getDisciplines().contains(discipline)) {
                throw new IllegalArgumentException("The swimmer is not registered for this dislipline!");
            }
           CompetitionResult result = new CompetitionResult(competitionName, date, discipline, placement, time);
           swimmer.addCompetitionResult(result);
           changed = true;
           return true;
        }
        return false;
    }


    public int registerPayment(int uuid, int paymentAmount) {
        Member member = memberManager.findMemberByUUID(uuid);

        if (member == null) {
            throw new IllegalArgumentException("Member does not exist");
        }

        int surplus = member.getPayments().registerPayment(paymentAmount);
        changed = true;
        return surplus;
    }
}
