package Users;

import Activities.Discipline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompetitiveSwimmerTest {

    private Competitive_swimmer swimmer;

    @BeforeEach
    void setUp() {
        // Kører før HVER test, så alle tests starter med en "frisk" svømmer
        swimmer = new Competitive_swimmer("Anna", LocalDate.of(2005, 3, 14), ActivityType.COMPETITIVE, 1, List.of(Discipline.CRAWL));
    }

    @Test
    void addTime_thenGetBestTime_returnsSameTime() {
        // Arrange
        BestTime time = new BestTime(62.5, LocalDate.of(2026, 11, 1));

        // Act
        swimmer.addTime(Discipline.CRAWL, time);

        // Assert
        assertSame(time, swimmer.getBestTime(Discipline.CRAWL));

    }


    @Test
    void getBestTime_noTimeAdded_returnsNull() {
        // Vi tilføjer INGEN tid, og spørger direkte
        BestTime result = swimmer.getBestTime(Discipline.CRAWL);

        // Vi forventer null, fordi der ikke er gemt noget
        assertNull(result);
    }

    @Test
    void addTime_slowerTimeAfterFaster_keepsFastest() {
        // To tider: 62.5 er hurtigst (lavest = bedst i svømning)
        BestTime fast = new BestTime(62.5, LocalDate.of(2026, 11, 1));
        BestTime slow = new BestTime(65.0, LocalDate.of(2026, 11, 8));

        // Først den hurtige, så den langsomme
        swimmer.addTime(Discipline.CRAWL, fast);
        swimmer.addTime(Discipline.CRAWL, slow);

        // Den BEDSTE tid skal stadig være den hurtige
        assertSame(fast, swimmer.getBestTime(Discipline.CRAWL));
    }

    @Test
    void addTime_inOneDiscipline_doesNotAffectOther() {
        BestTime time = new BestTime(62.5, LocalDate.of(2026, 11, 1));

        // Tid gemt i crawl
        swimmer.addTime(Discipline.CRAWL, time);

        // Butterfly skal stadig være tom
        assertNull(swimmer.getBestTime(Discipline.BUTTERFLY));
    }

    @Test
    void createSwimmer_withNoDisciplines_throwsException() {
        List<Discipline> noDisciplines = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () ->
                new Competitive_swimmer("Anna", LocalDate.of(2005, 3, 14),
                        ActivityType.COMPETITIVE, 1, noDisciplines)
        );
    }
}