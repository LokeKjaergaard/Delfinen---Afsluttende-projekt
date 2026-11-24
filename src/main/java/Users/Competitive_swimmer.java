package Users;

import Activities.Discipline;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Competitive_swimmer extends Member {


    private Map<Discipline, BestTime> disciplineBestime = new HashMap<>();
    private ArrayList<CompetitionResult> competitionResults = new ArrayList<>();


    public Competitive_swimmer(String name, LocalDate dateOfBirth, ActivityType activityType, int UUID) {
        super(name, dateOfBirth, activityType, UUID);
    }

    public BestTime getBestTime(Discipline discipline) {
        return disciplineBestime.get(discipline); // hent (null hvis ingen)
    }

    public void addCompetitionResult(CompetitionResult result) {
        competitionResults.add(result);
    }

    public ArrayList<CompetitionResult> getCompetitionResults() {
        return competitionResults;
    }

    public void addTime(Discipline discipline, BestTime newTime) {
        BestTime current = disciplineBestime.get(discipline); // den tid der er gemt nu (eller null)

        // Gem kun hvis der ikke er en tid endnu, ELLER hvis den nye er hurtigere
        if (current == null || newTime.getBestTime() < current.getBestTime()) {
            disciplineBestime.put(discipline, newTime);
        }
    }
}



