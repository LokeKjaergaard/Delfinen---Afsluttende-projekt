package main.java.Users;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

import Users.CompetitionResult;
import Users.Competitive_swimmer;
import Users.ActivityType;
import Activities.Discipline;


public class CompetitionResultTest {

    @Test
    void testAddCompetitionResult() {
    Competitive_swimmer swimmer = new Competitive_swimmer("Test Swimmer", LocalDate.of(2000,1,1), ActivityType.COMPETITIVE,12345);
    CompetitionResult result = new CompetitionResult("EK´s Svømmestævne", LocalDate.of(2026,11,20), Discipline.CRAWL, 2, 30.30);
    swimmer.addCompetitionResult(result);

    assertTrue(swimmer.getCompetitionResults().contains(result));
    }
}
