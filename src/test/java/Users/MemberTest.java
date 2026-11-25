package Users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    private Member member;

    @BeforeEach
    void setup() {
        member = new Member("John Doe", LocalDate.of(2004, 9, 10), ActivityType.PASSIVE, 1);
    }

    @Test
    void createMember() {
        assertNotNull(member);
        assertEquals("John Doe", member.getName());
        assertEquals(ActivityType.PASSIVE, member.getActivityType());
        assertEquals(LocalDate.of(2004, 9, 10), member.getDateOfBirth());
    }

    @Test
    void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> member.setName(""));
        assertThrows(IllegalArgumentException.class, () -> member.setDateOfBirth(null));
        assertThrows(IllegalArgumentException.class, () -> member.setActivityType(null));
    }

}