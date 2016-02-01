package banksys.control;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;

import banksys.account.OrdinaryAccount;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;
import banksys.control.exception.BankTransactionException;
import banksys.persistence.AccountFile;

public class BankControllerTest {

	BankController controller;
	AccountFile persistence;
	
	@Before
	public void setUp() throws Exception {
		File file = new File(System.getProperty("user.home") + File.separator + "Bank Sys" + File.separator + "accounts.xml");
		file.delete();
		persistence = new AccountFile();
		controller = new BankController(persistence);
	}

	@After
	public void tearDown() throws Exception {
		File file = new File(System.getProperty("user.home") + File.separator + "Bank Sys" + File.separator + "accounts.xml");
		file.delete();
	}

	@Test (expected = BankTransactionException.class)
	public void testAddNullAccount() throws BankTransactionException {
		controller.addAccount(null);
	}
	
	@Test
	public void testAddAccount() throws BankTransactionException {
		controller.addAccount(new OrdinaryAccount("1111"));
		assertEquals(1, persistence.numberOfAccounts());
	}
	
	@Test (expected = BankTransactionException.class)
	public void testRemoveFromEmpty() throws BankTransactionException {
		controller.removeAccount("1111");
	}
	
	@Test
	public void testRemoveAccount() throws BankTransactionException {
		controller.addAccount(new OrdinaryAccount("1111"));
		controller.addAccount(new OrdinaryAccount("1112"));
		controller.removeAccount("1111");
		assertEquals(1, persistence.numberOfAccounts());
	}
	
	@Test
	public void testShowBalance() throws BankTransactionException {
		controller.addAccount(new OrdinaryAccount("1111"));
		double balance = controller.getBalance("1111");
		assertEquals(0d, balance, 0);
	}
	
	@Test
	public void testDoCredit() throws BankTransactionException {
		controller.addAccount(new OrdinaryAccount("1111"));
		controller.doCredit("1111", 100);
		
		double balance = controller.getBalance("1111");
		assertEquals(100d, balance, 0);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testDoNegCredit() throws BankTransactionException {
		controller.addAccount(new OrdinaryAccount("1111"));
		controller.doCredit("1111", -100);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testDoInsufficientDebit() throws BankTransactionException {
		controller.addAccount(new OrdinaryAccount("1111"));
		controller.doDebit("1111", 100);
		
		double balance = controller.getBalance("1111");
		assertEquals(100d, balance, 0);
	}
	
	@Test
	public void testDoDebit() throws BankTransactionException {
		controller.addAccount(new OrdinaryAccount("1111"));
		controller.doCredit("1111", 100);
		controller.doDebit("1111", 50);
		
		double balance = controller.getBalance("1111");
		assertEquals(50d, balance, 0);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testDoNegDebit() throws BankTransactionException {
		controller.addAccount(new OrdinaryAccount("1111"));
		controller.doCredit("1111", 100);
		controller.doDebit("1111", -100);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testDoBigDebit() throws BankTransactionException {
		controller.addAccount(new OrdinaryAccount("1111"));
		controller.doCredit("1111", 100);
		controller.doDebit("1111", 200);
	}

	@Test
	public void testEarnBonus() throws BankTransactionException {
		controller.addAccount(new SpecialAccount("1111"));
		controller.doCredit("1111", 100);
		controller.doEarnBonus("1111");
		
		double balance = controller.getBalance("1111");
		assertEquals(101d, balance, 0);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testEarnBonusNotSpecialAcc() throws BankTransactionException {
		controller.addAccount(new OrdinaryAccount("1111"));
		controller.doCredit("1111", 100);
		controller.doEarnBonus("1111");
	}
	
	@Test
	public void testEarnInterest() throws BankTransactionException {
		controller.addAccount(new SavingsAccount("1111"));
		controller.doCredit("1111", 1000);
		controller.doEarnInterest("1111");
		
		double balance = controller.getBalance("1111");
		assertEquals(1001d, balance, 0);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testEarnInterestNotSavingsAcc() throws BankTransactionException {
		controller.addAccount(new OrdinaryAccount("1111"));
		controller.doCredit("1111", 100);
		controller.doEarnBonus("1111");
	}
	
	@Test (expected = BankTransactionException.class)
	public void testTransferFromNull() throws BankTransactionException {
		controller.addAccount(new OrdinaryAccount("1111"));
		controller.doTransfer(null, "1111", 100);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testTransferToNull() throws BankTransactionException {
		controller.addAccount(new OrdinaryAccount("1111"));
		controller.doCredit("1111", 100);
		controller.doTransfer("1111", null, 100);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testTransferToSelf() throws BankTransactionException {
		controller.addAccount(new OrdinaryAccount("1111"));
		controller.doCredit("1111", 100);
		controller.doTransfer("1111", "1111", 100);
	}
	
	@Test
	public void testTransfer() throws BankTransactionException {
		controller.addAccount(new OrdinaryAccount("1111"));
		controller.addAccount(new OrdinaryAccount("1112"));
		controller.doCredit("1111", 150);
		controller.doTransfer("1111", "1112", 50);
		
		double balance1 = controller.getBalance("1111");
		double balance2 = controller.getBalance("1112");
		
		assertEquals(100d, balance1, 0);
		assertEquals(50d, balance2, 0);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testTransferInsufficient() throws BankTransactionException {
		controller.addAccount(new OrdinaryAccount("1111"));
		controller.addAccount(new OrdinaryAccount("1112"));
		controller.doCredit("1111", 50);
		controller.doTransfer("1111", "1112", 100);
	}
}
