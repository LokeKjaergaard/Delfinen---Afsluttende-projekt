package Users;

import Membership.Member_type;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;


public class Member {
    private String name;
    private LocalDate dateOfBirth;
    private final int UUID;
    private ActivityType activityType;
    private Member_type memberType;


    public Member(String name, LocalDate dateOfBirth, ActivityType activityType, int UUID) {
        setName(name);
        setDateOfBirth(dateOfBirth);
        this.activityType = activityType;
        setMemberType();
        this.UUID = UUID;
    }

    // getters

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getUUID() {
        return UUID;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public Member_type getMemberType() {
        return memberType;
    }

    //setters
    public void setMemberType() {
        if (getAge() < 18) {
            this.memberType = Member_type.JUNIOR;
        } else {
            this.memberType = Member_type.SENIOR;
        }
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException (
                    "Name cannot be empty!"
            );
        }
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException (
                    "Date of birth cannot be empty!"
            );
        }
        LocalDate currentDate = LocalDate.now();
        if (dateOfBirth.isAfter(currentDate)){
            throw new DateTimeException(
                    "Date cannot be in the future!"
            );
        }
        this.dateOfBirth = dateOfBirth;
    }

    public void setActivityType(ActivityType activityType) {
        if (activityType == null){
            throw new IllegalArgumentException (
                    "Activity type must be set!"
            );
        }
        this.activityType = activityType;
    }
}
