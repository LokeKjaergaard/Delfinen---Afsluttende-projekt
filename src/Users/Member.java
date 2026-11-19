package Users;

import java.time.LocalDate;

public class Member {
    private String name;
    private LocalDate dateOfBirth;
    private final int uuid;
    private static int nextId;
    private ActivityType activityType;

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

    //setters

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
