package DomainModel;

import Activities.Discipline;
import Users.ActivityType;
import Users.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClubManagerTest {
    private ClubManager clubManager;

    @BeforeEach
    void setup() {
        clubManager = new ClubManager();
    }

    @Test
    void testCreation() {
        boolean result = clubManager.createMember("John", LocalDate.parse("2020-09-09"), ActivityType.PASSIVE);
        assertTrue(result);
        assertFalse(clubManager.getMemberArrayList().isEmpty());
        Member member = clubManager.getMemberArrayList().getFirst();
        assertEquals("John", member.getName());
        assertEquals(LocalDate.parse("2020-09-09"), member.getDateOfBirth());
        assertEquals(ActivityType.PASSIVE, member.getActivityType());
        assertNotNull(member.getUUID());
    }

    @Test
    void testDeletion() {
        Member member = new Member("John", LocalDate.parse("2020-09-09"), ActivityType.PASSIVE, 0);
        clubManager.getMemberArrayList().add(member);
        assertFalse(clubManager.getMemberArrayList().isEmpty());

        boolean result = clubManager.deleteMember(member);
        assertTrue(result);
        assertTrue(clubManager.getMemberArrayList().isEmpty());
    }

    @Test
    void testFindMemberUUID() {
        clubManager.createMember("John", LocalDate.parse("2020-09-09"), ActivityType.PASSIVE,  List.of(Discipline.CRAWL));
        clubManager.createMember("Jane", LocalDate.parse("2008-07-01"), ActivityType.COMPETITIVE,  List.of(Discipline.CRAWL));

        int janeUUID = clubManager.getMemberArrayList().get(1).getUUID();

        Member result = clubManager.findMemberByUUID(janeUUID);
        assertNotNull(result);
        assertEquals("Jane", result.getName());
        assertEquals(LocalDate.parse("2008-07-01"), result.getDateOfBirth());
        assertEquals(ActivityType.COMPETITIVE, result.getActivityType());
    }

    @Test
    void testFindMemberByName() {
        memberManager.createMember("John", LocalDate.parse("2020-09-09"), ActivityType.PASSIVE);
        memberManager.createMember("Jane", LocalDate.parse("2008-07-01"), ActivityType.COMPETITIVE);

        Member result = memberManager.findMemberByName("John");

        assertNotNull(result);
        assertEquals("John", result.getName());
        assertEquals(LocalDate.parse("2020-09-09"), result.getDateOfBirth());
        assertEquals(ActivityType.PASSIVE, result.getActivityType());

    }

    @Test
    void testGetMembers() {
        assertNotNull(memberManager.getMemberArrayList());
    }

}