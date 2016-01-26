package banksys.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;

public class AccountVectorTest {

	AccountVector accounts;
	
	@Before
	public void setUp() throws Exception {
		accounts = new AccountVector(); 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test (expected = AccountNotFoundException.class)
	public void testRetrieveEmpty() throws AccountNotFoundException {
		accounts.retrieve("11111");
	}
	
	@Test
	public void testCreate() throws AccountNotFoundException, AccountCreationException {
		accounts.create(new OrdinaryAccount("11111"));
		
		AbstractAccount acc = accounts.retrieve("11111");
		assertNotNull(acc);
	}
	
	@Test (expected = AccountCreationException.class)
	public void testCreateDuplicated() throws AccountCreationException {
		accounts.create(new OrdinaryAccount("0001"));
		accounts.create(new OrdinaryAccount("0001"));
	}

	@Test (expected = AccountDeletionException.class)
	public void testDeleteNonexistentAccount() throws AccountDeletionException {
		accounts.delete("9999");
	}
	
	@Test (expected = AccountNotFoundException.class)
	public void testDelete() throws AccountCreationException ,AccountDeletionException, AccountNotFoundException {
		accounts.create(new OrdinaryAccount("0004"));
		accounts.create(new OrdinaryAccount("0005"));
		accounts.delete("0005");
		
		accounts.retrieve("0005");
	}

	@Test
	public void testNumberOfAccounts() throws AccountCreationException {
		accounts.create(new OrdinaryAccount("0007"));
		accounts.create(new OrdinaryAccount("0008"));
		accounts.create(new OrdinaryAccount("0009"));
		
		assertEquals(3, accounts.numberOfAccounts());
	}
	
	@Test
	public void testNumberOfAccountsWithDelete() throws AccountCreationException, AccountDeletionException {
		accounts.create(new OrdinaryAccount("0001"));
		accounts.create(new OrdinaryAccount("0002"));
		accounts.create(new OrdinaryAccount("0003"));
		accounts.delete("0002");
		accounts.create(new OrdinaryAccount("0004"));
		
		assertEquals(3, accounts.numberOfAccounts());
	}
	
	@Test
	public void testList() throws AccountCreationException {
		AbstractAccount[] list;
		
		accounts.create(new OrdinaryAccount("0001"));
		accounts.create(new OrdinaryAccount("0002"));
		accounts.create(new OrdinaryAccount("0003"));
		
		list = accounts.list();
		
		assertEquals(3, list.length);
	}
	
	@Test
	public void testListEmpty() {
		AbstractAccount[] list;
		list = accounts.list();
		
		assertNull(list);
	}

}