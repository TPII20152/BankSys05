package banksys.account;

import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by thiagoisaias on 1/20/16.
 */
public class TaxAccountTest {

    TaxAccount tAccount;

    @Before
    public void setUp() throws Exception {
        tAccount = new TaxAccount("123321");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testDebit() throws Exception {
        double valueCredit = 120;
        tAccount.credit(valueCredit);
        double oldBalance = tAccount.getBalance();
        double valueDebit = 50;
        tAccount.debit(50);
        double newBalance = tAccount.getBalance();
        assertEquals(valueDebit*1.001,oldBalance-newBalance,0);
    }

    @Test
    public void testCredit() throws Exception {
        double value = 300;
        double oldBalance = tAccount.getBalance();
        tAccount.credit(value);
        double newBalance = tAccount.getBalance();
        assertEquals(value,newBalance-oldBalance,0);
    }

    @Test
    public void testGetNumber() throws Exception {
        assertEquals("123321",tAccount.getNumber());

    }

    @Test
    public void testGetBalance() throws Exception {
        double oldBalance = tAccount.getBalance();
        double value = 30;
        tAccount.credit(value);
        double newBalance = tAccount.getBalance();
        assertEquals(value,newBalance-oldBalance,0);
    }

    @Test(expected = NegativeAmountException.class)
    public void testNegativeDebit() throws Exception {
        tAccount.credit(100);
        tAccount.debit(-10);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testInsufficientDebit() throws Exception {
        tAccount.credit(100);
        tAccount.debit(101);
    }

    @Test(expected = NegativeAmountException.class)
    public void testNegativeCredit() throws Exception {
        tAccount.credit(-100);
    }
}