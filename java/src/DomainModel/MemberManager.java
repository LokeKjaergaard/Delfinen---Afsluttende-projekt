package DomainModel;

import Users.ActivityType;
import Users.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class MemberManager {
    private final ArrayList<Member> memberArrayList = new ArrayList<>();
    // the UUID list tracks all used UUIDs, in case we need to track it.
    private final ArrayList<Integer> UUIDArrayList = new ArrayList<>();

    public ArrayList<Member> getMemberArrayList() {
        return memberArrayList;
    }

    /**
     * Creates a new member, adds them to the memberArrayList and then remembers the UUID so it won't be used in the future.
     */
    public boolean createMember(String name, LocalDate dateOfBirth, ActivityType activityType) {
        return memberArrayList.add(new Member(name, dateOfBirth, activityType, generateNextUUID()));
    }

    public boolean deleteMember(Member member) {
        return memberArrayList.remove(member);
    }

    /**
     * Creates a new random integer from 0 to 99999999. If that id already exists then recursively do it again.
     *
     * @return A random integer from 0 to 99999999
     */
    private int generateNextUUID() {
        Random randomUUID = new Random();
        int nextUUID = randomUUID.nextInt(100000000);
        if (UUIDArrayList.contains(nextUUID)) {
            nextUUID = generateNextUUID();
        }
        UUIDArrayList.add(nextUUID);
        return nextUUID;

    }
}
