package daos;

import business.Loan;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class LoanFeeDaoTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void payLateFee() {
        LoanFeeDao feeDao= new LoanFeeDao("library");
        int actual=feeDao.payLateFee(1);
        int expected=1;
        assertEquals(actual, expected);
        if(actual==expected){
            LocalDateTime now = LocalDateTime.now();
            Date dateOfLoan= new Date(2023-1900,9,16);
            Date dueDate= new Date(2023-1900,9,20);
            Date returnDate= new Date(now.getYear()-1900,now.getMonthValue()-1,now.getDayOfMonth());
            Loan expected1= new Loan(1,1,1,dateOfLoan,dueDate,returnDate);
            Loan actual1= feeDao.getLoan(1);
            assertEquals(expected1, actual1);
        }
    }

    @Test
    void testPayLateFee() {
        LoanFeeDao feeDao= new LoanFeeDao("library");
        int actual=feeDao.payLateFee(1,4.25);
        int expected=1;
        assertEquals(actual, expected);
    }

    @Test
    void deleteReturnDate() {
        LoanFeeDao feeDao= new LoanFeeDao("library");
        int actual=feeDao.deleteReturnDate(1);
        int expected=1;
        assertEquals(actual, expected);
        if(actual==expected){
            Date dateOfLoan= new Date(2023-1900,9,16);
            Date dueDate= new Date(2023-1900,9,20);
            Date returnDate= null;
            Loan expected1= new Loan(1,1,1,dateOfLoan,dueDate,returnDate);
            Loan actual1= feeDao.getLoan(1);
            assertEquals(expected1, actual1);
        }
    }

    @Test
    void deleteLateFee() {
        LoanFeeDao feeDao= new LoanFeeDao("library");
        int actual=feeDao.deleteLateFee(1);
        int expected=1;
        assertEquals(actual, expected);
        if(actual==expected){

        }
    }

    @Test
    void getLoan() {
        LoanFeeDao feeDao= new LoanFeeDao("library");
        LocalDateTime now = LocalDateTime.now();
        Date dateOfLoan= new Date(2023-1900,9,16);
        Date dueDate= new Date(2023-1900,9,20);
        Date returnDate= new Date(now.getYear()-1900,now.getMonthValue()-1,now.getDayOfMonth());
        Loan expected= new Loan(1,1,1,dateOfLoan,dueDate,returnDate);
        Loan actual= feeDao.getLoan(1);
        assertEquals(expected, actual);
    }
}