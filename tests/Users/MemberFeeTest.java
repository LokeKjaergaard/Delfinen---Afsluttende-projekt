package Users;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberFeeTest {

    //US 4 Tests
    @Test
    void calculateJuniorFee(){
        Member member = new Member("Test", LocalDate.of(2008,11,20),ActivityType.EXERCISER);
        LocalDate referenceDate = LocalDate.of(2026,11,19);
        int result = member.calculateFee(referenceDate);
        assertEquals(1000,result);
    }

    @Test
    void calculateFeeAt18() {
        Member member = new Member ("Test", LocalDate.of(2008,11,19),ActivityType.EXERCISER);
        LocalDate referenceDate = LocalDate.of(2026,11,19);
        int result = member.calculateFee(referenceDate);
        assertEquals(1600,result);
    }

    @Test
    void CalculateFeeAt60() {
        Member member = new Member("Test", LocalDate.of(1966,11,19),ActivityType.EXERCISER);
        LocalDate referenceDate = LocalDate.of(2026,11,19);
        int result = member.calculateFee(referenceDate);
        assertEquals(1200,result);
    }

    @Test
    void CalculatePassiveFee() {
        Member member = new Member("Test", LocalDate.of(2008,11,19),ActivityType.PASSIVE);
        LocalDate referenceDate = LocalDate.of(2026,11,19);
        int result = member.calculateFee(referenceDate);

        assertEquals(500, result);
    }

    @Test
    void CalculateFeeAt59() {
        Member member = new Member("Test", LocalDate.of(1966,11,20),ActivityType.EXERCISER);
        LocalDate referenceDate = LocalDate.of(2026,11,19);
        int result = member.calculateFee(referenceDate);
        assertEquals(1600,result);
    }

}
