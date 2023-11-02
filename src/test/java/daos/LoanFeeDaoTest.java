package daos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanFeeDaoTest {

   /* @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }*/

    @Test
    void payLateFee() {
        LoanFeeDao feeDao= new LoanFeeDao("library");
        int actual=feeDao.payLateFee(1);
        int expected=1;
        assertEquals(actual, expected);
    }

    @Test
    void testPayLateFee() {
    }
}