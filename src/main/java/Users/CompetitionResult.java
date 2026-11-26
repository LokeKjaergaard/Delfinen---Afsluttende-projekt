package Users;
import Activities.Discipline;
import java.time.LocalDate;

public class CompetitionResult {
    private String competitionName;
    private LocalDate date;
    private Discipline discipline;
    private int placement;
    private double time;

    public CompetitionResult(String competitionName, LocalDate date, Discipline discipline, int placement, double time) {
        if (placement <= 0) {
            throw new IllegalArgumentException("Placement must be a positive number!");
        }

        if (competitionName == null || competitionName.isBlank()) {
            throw new IllegalArgumentException("Competition name cannot be empty!");
        }

        // !Double.isFinite(Time) kontrollere om tallet er uendeligt eller NaN(Not a number)
        if (time <= 0 || !Double.isFinite(time)) {
            throw new IllegalArgumentException("Competition time must be a valid positive number!");
        }

        if (date == null || date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Competition date must be a valid date!");
        }

        if (discipline == null) {
            throw new IllegalArgumentException("Competition discipline must be selected!");
        }

        this.competitionName = competitionName;
        this.date = date;
        this.discipline = discipline;
        this.placement = placement;
        this.time = time;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public LocalDate getDate() {
        return date;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public int getPlacement() {
        return placement;
    }

    public double getTime() {
        return time;
    }

}
