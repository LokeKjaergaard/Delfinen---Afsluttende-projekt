package Users;

import java.util.HashMap;
import java.util.Map;
import Activities.Discipline;

import java.time.LocalDate;



public class Competitive_swimmer extends Member {


    private Map<Discipline, BestTime> disciplineBestime = new HashMap<>();


    public Competitive_swimmer(String name, LocalDate dateOfBirth, ActivityType activityType, int UUID) {
        super(name, dateOfBirth, activityType, UUID);
    }

    public void addTime(Discipline discipline, BestTime time) {
        disciplineBestime.put(discipline, time ); // gem

    }

    public BestTime getBestTime(Discipline discipline) {
        return disciplineBestime.get(discipline); // hent (null hvis ingen)
    }




    }



