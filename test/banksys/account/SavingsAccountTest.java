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
public class SavingsAccountTest {

    SavingsAccount sAccount;

    @Before
    public void setUp() throws Exception {
        sAccount = new SavingsAccount("918543");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testEarnInterest() throws Exception {
        double value = 100;
        sAccount.credit(value);
        double oldBalance = sAccount.getBalance();
        sAccount.earnInterest();
        double newBalance = sAccount.getBalance();
        assertEquals(newBalance,oldBalance*1.001,0);
    }

    @Test
    public void testCredit() throws Exception {
        double value = 100;
        double oldBalance = sAccount.getBalance();
        sAccount.credit(value);
        double newBalance = sAccount.getBalance();
        assertEquals(value,newBalance-oldBalance,0);
    }

    @Test
    public void testDebit() throws Exception {
        double valueCredit = 100;
        sAccount.credit(valueCredit);
        double oldBalance = sAccount.getBalance();
        double valueDebit = 50;
        sAccount.debit(50);
        double newBalance = sAccount.getBalance();
        assertEquals(valueDebit,oldBalance-newBalance,0);
    }

    @Test
    public void testGetNumber() throws Exception {
        assertEquals("918543",sAccount.getNumber());
    }

    @Test
    public void testGetBalance() throws Exception {
        double oldBalance = sAccount.getBalance();
        double value = 10;
        sAccount.credit(value);
        double newBalance = sAccount.getBalance();
        assertEquals(value,newBalance-oldBalance,0);
    }

    @Test(expected = NegativeAmountException.class)
    public void testNegativeDebit() throws Exception {
        sAccount.credit(100);
        sAccount.debit(-10);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testInsufficientDebit() throws Exception {
        sAccount.credit(100);
        sAccount.debit(101);
    }

    @Test(expected = NegativeAmountException.class)
    public void testNegativeCredit() throws Exception {
        sAccount.credit(-100);
    }

    @Test(expected = NegativeAmountException.class)
    public void testNegativeInterest() throws Exception {
    }

}