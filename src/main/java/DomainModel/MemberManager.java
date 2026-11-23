package DomainModel;

import Users.ActivityType;
import Users.Competitive_swimmer;
import Users.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class MemberManager {
    private final ArrayList<Member> memberArrayList = new ArrayList<>();
    // the UUID list tracks all used UUIDs, in case we need to track it.
    private final ArrayList<Integer> UUIDArrayList = new ArrayList<>();

    public ArrayList<Member> getCompetitiveSwimmers(){
        ArrayList<Member> competitiveSwimmers = new ArrayList<>();
        for(Member member : memberArrayList){
            if (member instanceof Competitive_swimmer){
                competitiveSwimmers.add(member);
            }
        }
        return competitiveSwimmers;
    }

    public ArrayList<Member> getMemberArrayList() {
        return memberArrayList;
    }

    /**
     * Creates a new member, adds them to the memberArrayList and then remembers the UUID so it won't be used in the future.
     */
    public boolean createMember(String name, LocalDate dateOfBirth, ActivityType activityType) {
        int uuid = generateNextUUID();
        Member member;
        Competitive_swimmer Competitive_swimmer;
        if (activityType == ActivityType.COMPETITIVE) {
            member = new Competitive_swimmer(name, dateOfBirth, activityType, uuid);

        } else {
            member = new Member(name, dateOfBirth, activityType, uuid);
        }
        return memberArrayList.add(member);
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

    public Member findMember(int uuid) {
        for (Member member : memberArrayList) {
            if (member.getUUID() == uuid) {
                return member;
            }
        }
        return null;
    }



}
