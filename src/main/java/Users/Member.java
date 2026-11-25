package Users;

import DomainModel.Team;
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
    private Team assignedTeam;

    //Annual membership fees
    private static final int PASSIVE_FEE = 500;
    private static final int JUNIOR_FEE = 1000;
    private static final int SENIOR_FEE = 1600;
    private static final int SENIOR_DISCOUNT_FEE = 1200;


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

    public int getAge(LocalDate referenceDate) {
        return Period.between(dateOfBirth, referenceDate).getYears();
    }

    public Member_type getMemberType() {
        return memberType;
    }

    public Team getAssignedTeam() {
        return assignedTeam;
    }

    // Calculate annual fee
    public int calculateFee(LocalDate referenceDate) {
        if (activityType == ActivityType.PASSIVE) {
            return PASSIVE_FEE;
        }

        int age = getAge(referenceDate);

        if (age < 18) {
            return JUNIOR_FEE;
        }

        if (age >= 60) {
            return SENIOR_DISCOUNT_FEE;
        }

        return SENIOR_FEE;
    }

    //setters
    public void setMemberType() {
        if (getAge(LocalDate.now()) < 18) {
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
        setMemberType();
    }

    public void setActivityType(ActivityType activityType) {
        if (activityType == null){
            throw new IllegalArgumentException (
                    "Activity type must be set!"
            );
        }
        this.activityType = activityType;
    }

    public void setAssignedTeam(Team assignedTeam) {
        this.assignedTeam = assignedTeam;
    }
}