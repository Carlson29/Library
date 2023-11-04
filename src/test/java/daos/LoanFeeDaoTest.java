package daos;

import business.Loan;
import business.LoanFee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
//@author carlson
class LoanFeeDaoTest {




/*testing insertReturnDate*/
    @Test
    void insertReturnDate() {
        LoanFeeDao feeDao= new LoanFeeDao("library");
        int actual=feeDao.insertReturnDate(1);
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
    //delete return date after inserting it return date
   @AfterEach
    void tearDown_deleteReturnDate() {
        LoanFeeDao feeDao= new LoanFeeDao("library");
        feeDao.deleteReturnDate(1);
    }
//test pay loan fee
    @Test
    void testPayLateFee() {
        LoanFeeDao feeDao= new LoanFeeDao("library");
        int actual=feeDao.payLateFee(1,4.25);
        int expected=1;
        assertEquals(actual, expected);
    }

    //delete LoanFee after inserting
    @AfterEach
    void tearDown_deleteLoanFee() {
        LoanFeeDao feeDao= new LoanFeeDao("library");
        feeDao.deleteLateFee(1);
    }
    //insert returnDate before deleting
    @BeforeEach
    void setUp_insertReturnDate() {
        LoanFeeDao feeDao= new LoanFeeDao("library");
        feeDao.insertReturnDate(1);
    }
    /*deleting return date after inserting*/
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
//insert LoanFee before deleting
    @BeforeEach
    void setUp_insertLoanFee() {
        LoanFeeDao feeDao= new LoanFeeDao("library");
        feeDao.payLateFee(1,4.25);
    }

    /**testing delete LoanFee when LoanFee is available*/
    @Test
    void deleteLateFee() {
        LoanFeeDao feeDao= new LoanFeeDao("library");
        int actual=feeDao.deleteLateFee(1);
        int expected=1;
        assertEquals(actual, expected);
        if(actual==expected){
        LoanFee actual1=feeDao.getLateLoanfee(1);
        LoanFee expected1=null;
            assertEquals(actual1, expected1);
        }
    }

    /**When deleting LoanFee when, LoanFee is not available*/
    @Test
    void deleteLateFee_WhenNotPresent() {
        LoanFeeDao feeDao= new LoanFeeDao("library");
        int actual=feeDao.deleteLateFee(5);
        int expected=0;
        assertEquals(actual, expected);

    }
/*testing method get loan by id when a Loan is available*/
    @Test
    void getLoan() {
        LoanFeeDao feeDao= new LoanFeeDao("library");
        LocalDateTime now = LocalDateTime.now();
        Date dateOfLoan= new Date(2023-1900,9,16);
        Date dueDate= new Date(2023-1900,9,20);
        Date returnDate=new Date(now.getYear()-1900,now.getMonthValue()-1,now.getDayOfMonth());
        Loan expected= new Loan(1,1,1,dateOfLoan,dueDate,returnDate);
        Loan actual= feeDao.getLoan(1);
       assertEquals(expected, actual);
    }

    /*testing method get loan by id when no Loan is available*/
    @Test
    void getLoan_WhenNoLoanIsPresent() {
        LoanFeeDao feeDao= new LoanFeeDao("library");
        Loan expected= null;
        Loan actual= feeDao.getLoan(5);
        assertEquals(expected, actual);
    }
}