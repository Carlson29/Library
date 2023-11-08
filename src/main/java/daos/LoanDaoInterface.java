package daos;

import business.Loan;

import java.util.List;
import java.util.Date;
/**
 * @author Tom
 */
public interface LoanDaoInterface {
    public List<Loan> getLoansCurrent(int userId);
    public List<Loan> getLoansFormer(int userId);
    public List<Loan> getLoanAsAdmin(int userType);
}
