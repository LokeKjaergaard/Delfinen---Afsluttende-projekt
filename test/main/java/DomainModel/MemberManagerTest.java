package DomainModel;

import Activities.Discipline;
import Users.ActivityType;
import Users.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberManagerTest {
    private MemberManager memberManager;

    @BeforeEach
    void setup() {
        memberManager = new MemberManager();
    }

    @Test
    void testCreation() {
        boolean result = memberManager.createMember("John", LocalDate.parse("2020-09-09"), ActivityType.PASSIVE,  List.of(Discipline.CRAWL));
        assertTrue(result);
        assertFalse(memberManager.getMemberArrayList().isEmpty());
        Member member = memberManager.getMemberArrayList().getFirst();
        assertEquals("John", member.getName());
        assertEquals(LocalDate.parse("2020-09-09"), member.getDateOfBirth());
        assertEquals(ActivityType.PASSIVE, member.getActivityType());
        assertNotNull(member.getUUID());
    }

    @Test
    void testDeletion() {
        Member member = new Member("John", LocalDate.parse("2020-09-09"), ActivityType.PASSIVE, 0);
        memberManager.getMemberArrayList().add(member);
        assertFalse(memberManager.getMemberArrayList().isEmpty());

        boolean result = memberManager.deleteMember(member);
        assertTrue(result);
        assertTrue(memberManager.getMemberArrayList().isEmpty());
    }

    @Test
    void testFindMemberUUID() {
        memberManager.createMember("John", LocalDate.parse("2020-09-09"), ActivityType.PASSIVE,  List.of(Discipline.CRAWL));
        memberManager.createMember("Jane", LocalDate.parse("2008-07-01"), ActivityType.COMPETITIVE,  List.of(Discipline.CRAWL));

        int janeUUID = memberManager.getMemberArrayList().get(1).getUUID();

        Member result = memberManager.findMemberByUUID(janeUUID);
        assertNotNull(result);
        assertEquals("Jane", result.getName());
        assertEquals(LocalDate.parse("2008-07-01"), result.getDateOfBirth());
        assertEquals(ActivityType.COMPETITIVE, result.getActivityType());
    }

    @Test
    void testFindMemberByName() {
        memberManager.createMember("John", LocalDate.parse("2020-09-09"), ActivityType.PASSIVE,  List.of(Discipline.CRAWL));
        memberManager.createMember("Jane", LocalDate.parse("2008-07-01"), ActivityType.COMPETITIVE,  List.of(Discipline.CRAWL));

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