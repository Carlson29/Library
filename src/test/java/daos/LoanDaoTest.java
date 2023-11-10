package daos;

import business.Loan;
import daos.LoanDao;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Tom
 */

public class LoanDaoTest {
    public LoanDaoTest() {

    }
    @Test
    public void testGetCurrentLoan() {
        LoanDao loandao = new LoanDao("library");

        LocalDateTime now = LocalDateTime.now();
        Date dateOfLoan = new Date(2023 - 1900,9,18);
        Date dueDate = new Date(2023 - 1900,10,2);
        Date returnDate = null;

        Loan loan = new Loan(1, 1, 1, dateOfLoan, dueDate, returnDate);
        List<Loan> loans = loandao.getLoansCurrent(loan.getUserId());
        loans.add(loan);
    }

    @Test
    public void testgetLoanFormer(){
        LoanDao loandao = new LoanDao("library");

        LocalDateTime now = LocalDateTime.now();
        Date dateOfLoan = new Date(2023 - 1900,9,18);
        Date dueDate = new Date(2023 - 1900,10,2);
        Date returnDate = new Date(2023 - 1900,9,23);

        Loan loan = new Loan(1, 1, 1, dateOfLoan, dueDate, returnDate);
        List<Loan> loans = loandao.getLoansCurrent(loan.getUserId());
        loans.add(loan);
    }

    @Test
    public void testgetLoanAsAdmin(){
        LoanDao loanDao = new LoanDao("library");
        int userType = 2;

        LocalDateTime now = LocalDateTime.now();
        Date dateOfLoan1 = new Date(2023 - 1900,9,18);
        Date dueDate1 = new Date(2023 - 1900,10,2);
        Date returnDate1 = new Date(2023 - 1900,9,23);
        Date dateOfLoan2 = new Date(2023 - 1900,9,25);
        Date dueDate2 = new Date(2023 - 1900,10,9);
        Date returnDate2 = new Date(2023 - 1900,9,30);
        Date dateOfLoan3 = new Date(2023 - 1900,9,18);
        Date dueDate3 = new Date(2023 - 1900,10,2);
        Date returnDate3 = null;

        Loan loan1 = new Loan(1,1,1,dateOfLoan1,dueDate1,returnDate1);
        Loan loan2 = new Loan(2,2,2,dateOfLoan2,dueDate2,returnDate2);
        Loan loan3 = new Loan(3,3,3,dateOfLoan3,dueDate3,returnDate3);
        List<Loan> loans = loanDao.getLoanAsAdmin(userType);
        loans.add(loan1);
        loans.add(loan2);
        loans.add(loan3);
    }
}
