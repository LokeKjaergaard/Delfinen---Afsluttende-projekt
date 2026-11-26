package DomainModel;

import Activities.Discipline;
import Membership.Member_type;
import Users.Member;

import javax.swing.plaf.metal.MetalMenuBarUI;
import java.util.ArrayList;

public class Team {
    private int teamNumber;
    private String teamName;
    private Member trainer; //todo: make a proper employee (trainer) class?
    private ArrayList<Member> members;
    private Member_type memberType;
    private ArrayList<Discipline> disciplines;

    public Team(int teamNumber, ArrayList<Member> members, Member trainer, Member_type memberType) {
        setTeamNumber(teamNumber);
        setTeamMembers(members);
        setTeamTrainer(trainer);
        setMemberType(memberType);
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public Member getTeamTrainer() {
        return trainer;
    }

    public ArrayList<Member> getAllTeamMembers() {
        return members;
    }


    public Member_type getTeamMemberType() {
        return memberType;
    }

    public ArrayList<Discipline> getTeamDisciplines() {
        return disciplines;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public void setTeamTrainer(Member trainer) {
        if (trainer == null) {
            throw new IllegalArgumentException(
                    "A Trainer must be assigned!"
            );
        }
        this.trainer = trainer;
    }

    // private: we don't want people to be able to replace the completely change the member list after creation.
    private void setTeamMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public void setDisciplines(ArrayList<Discipline> disciplines) {
        if (disciplines.isEmpty() || disciplines.size() > 4) {
            throw new IllegalArgumentException(
                    "There must be 1 to 4 disciplines!"
            );
        }

        //check for duplicates.
        boolean hasButterfly = false;
        boolean hasCrawl = false;
        boolean hasBackstroke = false;
        boolean hasBreaststroke = false;

        for (Discipline discipline : disciplines) {
            switch (discipline) {
                case BUTTERFLY -> {
                    if (hasButterfly) {
                        throw new IllegalArgumentException(
                                "Team already has one butterfly!"
                        );
                    }
                    hasButterfly = true;
                }
                case CRAWL -> {
                    if (hasCrawl) {
                        throw new IllegalArgumentException(
                                "Team already has one crawl!"
                        );
                    }
                    hasCrawl = true;
                }
                case BACKSTROKE -> {
                    if (hasBackstroke) {
                        throw new IllegalArgumentException(
                                "Team already has one backstroke!"
                        );
                    }
                    hasBackstroke = true;
                }
                case BREASTSTROKE -> {
                    if (hasBreaststroke) {
                        throw new IllegalArgumentException(
                                "Team already has one backstroke!"
                        );
                    }
                    hasBreaststroke = true;
                }
            }
        }
        this.disciplines = disciplines;
    }

    public void setMemberType(Member_type memberType) {
        if (memberType == null) {
            throw new IllegalArgumentException(
                    "The team must be set as either a junior team or a senior team!"
            );
        }

        for (Member member : members) {
            if (member.getMemberType() != memberType) {
                throw new IllegalArgumentException(
                        "Member(s) does not match new member type!"
                );
            }
        }
        this.memberType = memberType;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public boolean addMember(Member member) {
        if (member.getAssignedTeam() == this) {
            return false;
        }
        member.setAssignedTeam(this);
        return members.add(member);
    }

    public boolean removeMember(Member member) {
        if (!members.contains(member)) {
            return false;
        }
        member.setAssignedTeam(this);
        return members.remove(member);
    }
}
