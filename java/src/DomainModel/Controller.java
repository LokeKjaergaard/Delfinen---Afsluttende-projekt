package DomainModel;

import DataSource.FileHandler;
import Users.ActivityType;
import Users.Member;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private final FileHandler fileHandler = new FileHandler("temp.csv");
    private final MemberManager memberManager = new MemberManager();
    private boolean changed = false;

    public Controller() {

    }

    public ArrayList<Member> getMemberArrayList() {
        return memberManager.getMemberArrayList();
    }

    public boolean createMember(String name, LocalDate dateOfBirth, ActivityType activityType) {
        boolean result = memberManager.createMember(name, dateOfBirth, activityType);
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
}
