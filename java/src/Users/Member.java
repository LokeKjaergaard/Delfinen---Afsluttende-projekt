package Users;

import java.time.LocalDate;

public class Member {
    private String name;
    private LocalDate   dateOfBirth;
    private int uuid;
    private static int nextId;

    public Member(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
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

    //setters

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
