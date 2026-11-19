package Users;

import Membership.Member_type;

import java.time.LocalDate;
import java.time.Period;



public class Member {
    private String name;
    private LocalDate dateOfBirth;
    private final int uuid;
    private static int nextId;
    private ActivityType activityType;
    private Member_type memberType;



    public Member(String name, LocalDate dateOfBirth, ActivityType activityType) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.activityType = activityType;
        setMemberType();
        uuid = nextId;
        nextId++;

    }

    // getters

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getUuid() {
        return uuid;
    }

    public ActivityType getActivityType() {
        return activityType;
    }
    public int getAge(){
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public Member_type getMemberType() {
        return memberType;
    }

    //setters
    public void setMemberType(){
if(getAge() < 18){
    this.memberType = Member_type.JUNIOR;
}
else{
    this.memberType = Member_type.SENIOR;
}
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;

    }


}
