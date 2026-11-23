package Membership;

import Users.Member;

import java.util.ArrayList;

public class Members {
    private ArrayList<Member> members;

    public Members() {
        members = new ArrayList<>();
    }
    // tilføjer et medlem til listen
    public void addMember(Member member){
        members.add(member);
    }

    public ArrayList<Member> getMembers() {
        return members;
    }
}
