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
public class SpecialAccountTest {

    SpecialAccount specialAccount;

    @Before
    public void setUp() throws Exception {
        specialAccount = new SpecialAccount("862673");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testEarnBonus() throws Exception {
        double value = 100;
        specialAccount.credit(value);
        double oldBalance = specialAccount.getBalance();
        specialAccount.earnBonus();
        double newBalance = specialAccount.getBalance();
        assertEquals(value*0.01,newBalance-oldBalance,0);

    }

    @Test
    public void testGetBonus() throws Exception {

    }

    @Test
    public void testCredit() throws Exception {
        double value = 150;
        double oldBalance = specialAccount.getBalance();
        specialAccount.credit(value);
        double newBalance = specialAccount.getBalance();
        assertEquals(value,newBalance-oldBalance,0);
    }

    @Test
    public void testDebit() throws Exception {
        double valueCredit = 150;
        specialAccount.credit(valueCredit);
        double oldBalance = specialAccount.getBalance();
        double valueDebit = 50;
        specialAccount.debit(50);
        double newBalance = specialAccount.getBalance();
        assertEquals(valueDebit,oldBalance-newBalance,0);
    }

    @Test
    public void testGetNumber() throws Exception {
        assertEquals("862673",specialAccount.getNumber());
    }

    @Test
    public void testGetBalance() throws Exception {
        double oldBalance = specialAccount.getBalance();
        double value = 20;
        specialAccount.credit(value);
        double newBalance = specialAccount.getBalance();
        assertEquals(value,newBalance-oldBalance,0);
    }

    @Test(expected = NegativeAmountException.class)
    public void testNegativeDebit() throws Exception {
        specialAccount.credit(100);
        specialAccount.debit(-10);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testInsufficientDebit() throws Exception {
        specialAccount.credit(100);
        specialAccount.debit(101);
    }

    @Test(expected = NegativeAmountException.class)
    public void testNegativeCredit() throws Exception {
        specialAccount.credit(-100);
    }

    @Test(expected = NegativeAmountException.class)
    public void testNegativeBonus() throws Exception {
        specialAccount.credit(-10); //Exception launched here -- warning
        specialAccount.earnBonus();
    }
}