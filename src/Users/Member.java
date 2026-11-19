package Users;

import java.time.LocalDate;
import java.time.Period;


public class Member {
    private String name;
    private LocalDate dateOfBirth;
    private final int uuid;
    private static int nextId;
    private ActivityType activityType;

    private static final int PASSIVE_FEE = 500;
    private static final int JUNIOR_FEE = 1000;
    private static final int SENIOR_FEE = 1600;
    private static final int SENIOR_DISCOUNT_FEE = 1200;

    public Member(String name, LocalDate dateOfBirth, ActivityType activityType) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.activityType = activityType;
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
    public int getAge(LocalDate referenceDate) {
        Period age = Period.between(dateOfBirth, referenceDate);
        return age.getYears();
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
