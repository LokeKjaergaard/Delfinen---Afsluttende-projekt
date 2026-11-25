package main.java.Users;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;

import Users.CompetitionResult;
import Users.Competitive_swimmer;
import Users.ActivityType;
import Activities.Discipline;
import Users.BestTime;


public class CompetitionResultTest {

    @Test
    void testAddCompetitionResult() {
    Competitive_swimmer swimmer = new Competitive_swimmer("Test Swimmer", LocalDate.of(2000,1,1), ActivityType.COMPETITIVE,12345, List.of(Discipline.CRAWL));
    CompetitionResult result = new CompetitionResult("EK´s Svømmestævne", LocalDate.of(2026,11,20), Discipline.CRAWL, 2, 30.30);
    swimmer.addCompetitionResult(result);

    assertTrue(swimmer.getCompetitionResults().contains(result));
    }

    @Test
    void testMultipleCompetitionResultsSameDiscipline() {
        Competitive_swimmer swimmer = new Competitive_swimmer("Test Swimmer", LocalDate.of(2000,1,1), ActivityType.COMPETITIVE,12346, List.of(Discipline.CRAWL));
        CompetitionResult result1 = new CompetitionResult("Danish open", LocalDate.of(2026,11,20), Discipline.CRAWL, 2, 32.45);
        CompetitionResult result2 = new CompetitionResult("Copenhagen Open", LocalDate.of(2026,11,22), Discipline.CRAWL, 1, 30.25);
        swimmer.addCompetitionResult(result1);
        swimmer.addCompetitionResult(result2);

        assertEquals(2, swimmer.getCompetitionResults().size());
        assertTrue(swimmer.getCompetitionResults().contains(result1));
        assertTrue(swimmer.getCompetitionResults().contains(result2));
    }

    @Test
    void testNegativePlacement() {
        assertThrows(IllegalArgumentException.class, () ->
                        new CompetitionResult("Danish Open", LocalDate.of(2026,11,20), Discipline.CRAWL, -1, 32.45));
    }

    @Test
    void testZeroPlacement() {
        assertThrows(IllegalArgumentException.class, () ->
                new CompetitionResult("Danish Open", LocalDate.of(2026,11,20), Discipline.CRAWL, 0, 32.45));
    }

    @Test
    void testCompetitionResultDoesNotChangeBestTime() {
        Competitive_swimmer swimmer = new Competitive_swimmer("Test Swimmer", LocalDate.of(2000,1,1), ActivityType.COMPETITIVE, 12347, List.of(Discipline.CRAWL));
        BestTime trainingTime = new BestTime(32.45, LocalDate.of(2026,11,19));
        swimmer.addTime(Discipline.CRAWL, trainingTime);
        CompetitionResult result = new CompetitionResult("Danish Open", LocalDate.of(2026,11,20), Discipline.CRAWL, 1, 30.25);
        swimmer.addCompetitionResult(result);
        assertEquals(32.45, swimmer.getBestTime(Discipline.CRAWL).getBestTime());

    }
}
