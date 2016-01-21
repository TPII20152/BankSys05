package banksys.account;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by thiagoisaias on 1/20/16.
 */
public class OrdinaryAccountTest {

    OrdinaryAccount oAccount;


    @Before
    public void setUp() throws Exception {
        oAccount = new OrdinaryAccount("192837");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetNumber() throws Exception {
        assertEquals("192837",oAccount.getNumber());
    }

    @Test
    public void testGetBalance() throws Exception {
        double oldBalance = oAccount.getBalance();
        double value = 50;
        oAccount.credit(value);
        double newBalance = oAccount.getBalance();
        assertEquals(value,newBalance-oldBalance,0);
    }

    @Test
    public void testCredit() throws Exception {
        double value = 200;
        double oldBalance = oAccount.getBalance();
        oAccount.credit(value);
        double newBalance = oAccount.getBalance();
        assertEquals(value,newBalance-oldBalance,0);
    }

    @Test
    public void testDebit() throws Exception {
        double valueCredit = 200;
        oAccount.credit(valueCredit);
        double oldBalance = oAccount.getBalance();
        double valueDebit = 50;
        oAccount.debit(50);
        double newBalance = oAccount.getBalance();
        assertEquals(valueDebit,oldBalance-newBalance,0);
    }

}