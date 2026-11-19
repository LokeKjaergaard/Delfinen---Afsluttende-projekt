package Users;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void createMember() {
        Member member = new Member("John Doe", LocalDate.of(2004, 9, 10));
        assertNotNull(member);
        assertEquals("John Doe", member.getName());
        assertEquals(LocalDate.of(2004, 9, 10), member.getDateOfBirth());
    }

}