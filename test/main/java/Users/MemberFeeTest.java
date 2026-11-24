package Users;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberFeeTest {

    @Test
    void testJuniorFee() {
        // Arrange
        LocalDate referenceDate = LocalDate.of(2026, 11, 19);
        Member member = new Member(
                "Junior",
                LocalDate.of(2008, 11, 20),
                ActivityType.EXERCISER,
                1
        );

        // Act
        int fee = member.calculateFee(referenceDate);

        // Assert
        assertEquals(1000, fee);
    }

    @Test
    void testSeniorFee() {
        // Arrange
        LocalDate referenceDate = LocalDate.of(2026, 11, 19);
        Member member = new Member(
                "Senior",
                LocalDate.of(2008, 11, 19),
                ActivityType.EXERCISER,
                2
        );

        // Act
        int fee = member.calculateFee(referenceDate);

        // Assert
        assertEquals(1600, fee);
    }

    @Test
    void testSeniorDiscountFee() {
        // Arrange
        LocalDate referenceDate = LocalDate.of(2026, 11, 19);
        Member member = new Member(
                "Senior60",
                LocalDate.of(1966, 11, 19),
                ActivityType.EXERCISER,
                3
        );

        // Act
        int fee = member.calculateFee(referenceDate);

        // Assert
        assertEquals(1200, fee);
    }

    @Test
    void testPassiveFee() {
        // Arrange
        LocalDate referenceDate = LocalDate.of(2026, 11, 19);
        Member member = new Member(
                "Passive",
                LocalDate.of(2008, 11, 19),
                ActivityType.PASSIVE,
                4
        );

        // Act
        int fee = member.calculateFee(referenceDate);

        // Assert
        assertEquals(500, fee);
    }

    @Test
    void testAge59Boundary() {
        // Arrange
        LocalDate referenceDate = LocalDate.of(2026, 11, 19);
        Member member = new Member(
                "Adult59",
                LocalDate.of(1966, 11, 20),
                ActivityType.EXERCISER,
                5
        );

        // Act
        int fee = member.calculateFee(referenceDate);

        // Assert
        assertEquals(1600, fee);
    }
}

