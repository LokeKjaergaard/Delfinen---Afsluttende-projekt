package Users;

import Membership.Payments;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PaymentsTest {

    @Disabled("TODO")
    @Test
    void negativAmount(){



    }
    @Disabled("TODO")
    @Test
    void moreThanOwed(){



    }
    @Disabled("TODO")
    @Test
    void notInt(){


    }
    @Disabled("TODO")
    @Test
    void unrealisticAmount(){


    }
    @Disabled("TODO")
    @Test
    void lowerThanOwedAmount(){


    }

    @Test
    void payExactAmount() {
        // Arrange
        Payments payments = new Payments();
        payments.addMissingPayment(2026, 1600);

        // Act
        int surplus = payments.registerPayment(1600);

        // Assert
        assertEquals(0, surplus);
        assertTrue(payments.isPaid());
    }
}




