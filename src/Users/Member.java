package Users;

import java.time.LocalDate;

public class Member {
    private String name;
    private LocalDate dateOfBirth;
    private final int UUID;
    private ActivityType activityType;

    public Member(String name, LocalDate dateOfBirth, ActivityType activityType, int UUID) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.activityType = activityType;
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

    //setters

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
