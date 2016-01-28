package banksys.control;

import banksys.account.AbstractAccount;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;
import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;
import banksys.control.exception.BankTransactionException;
import banksys.control.exception.IncompatibleAccountException;
import banksys.logging.Logger;
import banksys.persistence.IAccountRepository;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;

public class BankController {

	private IAccountRepository repository;

	public BankController(IAccountRepository repository) {
		this.repository = repository;
	}

	public void addAccount(AbstractAccount account) throws BankTransactionException {
		try {
			this.repository.create(account);
		} catch (AccountCreationException ace) {
			Logger.write("Account number "+account.getNumber()+" not created. ");
			throw new BankTransactionException(ace);
		}
		Logger.write("Account number "+account.getNumber()+ " created.");
	}

	public void removeAccount(String number) throws BankTransactionException {
		try {
			this.repository.delete(number);
		} catch (AccountDeletionException ade) {
			Logger.write("Failed attempt to delete account number "+number+".");
			throw new BankTransactionException(ade);
		}
		Logger.write("Account number "+number+"deleted.");
	}

	public void doCredit(String number, double amount) throws BankTransactionException {
		AbstractAccount account;
		try {
			account = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			Logger.write("Account number "+number+" not found. ");
			throw new BankTransactionException(anfe);
		}
		try {
			account.credit(amount);
		} catch (NegativeAmountException nae) {
			Logger.write("Failed attempt to credit negative value - account number. "+number+"Value: "+amount);
			throw new BankTransactionException(nae);
		}
		Logger.write(amount+" credited - account number "+account.getNumber());
	}

	public void doDebit(String number, double amount) throws BankTransactionException {
		AbstractAccount account;
		try {
			account = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			Logger.write("Account number "+number+" not found.");
			throw new BankTransactionException(anfe);
		}
		try {
			account.debit(amount);
		} catch (InsufficientFundsException ife) {
			Logger.write("Failed attempt to debit (Insufficient Funds). Account number. "+number+" Value: "+amount);
			throw new BankTransactionException(ife);
		} catch (NegativeAmountException nae) {
			Logger.write("Failed attempt to debit negative value - account number. "+number+" Value: "+amount);
			throw new BankTransactionException(nae);
		}
		Logger.write(amount+" debited - account number "+account.getNumber());
	}

	public double getBalance(String number) throws BankTransactionException {
		AbstractAccount account;
		try {
			account = this.repository.retrieve(number);
			Logger.write("Balance consulted - account number "+account.getNumber());
			return account.getBalance();
		} catch (AccountNotFoundException anfe) {
			Logger.write("Account number "+number+" not found. ");
			throw new BankTransactionException(anfe);
		}
	}

	public void doTransfer(String fromNumber, String toNumber, double amount) throws BankTransactionException {
		AbstractAccount fromAccount;
		try {
			fromAccount = this.repository.retrieve(fromNumber);
		} catch (AccountNotFoundException anfe) {
			Logger.write("Account number "+fromNumber+" not found.");
			throw new BankTransactionException(anfe);
		}

		AbstractAccount toAccount;
		try {
			toAccount = this.repository.retrieve(toNumber);
		} catch (AccountNotFoundException anfe) {
			Logger.write("Account number "+toNumber+" not found.");
			throw new BankTransactionException(anfe);
		}

		try {
			fromAccount.debit(amount);
			toAccount.credit(amount);
		} catch (InsufficientFundsException sie) {
			Logger.write("Failed attempt to transfer (Insufficient Funds). Account number. "+fromNumber+" Value: "+amount);
			throw new BankTransactionException(sie);
		} catch (NegativeAmountException nae) {
			Logger.write("Failed attempt to transfer (Negative Value). Account number. "+fromNumber+" Value: "+amount);
			throw new BankTransactionException(nae);
		}
	}

	public void doEarnInterest(String number) throws BankTransactionException, IncompatibleAccountException {
		AbstractAccount auxAccount;
		try {
			auxAccount = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			Logger.write("Account number "+number+" not found.");
			throw new BankTransactionException(anfe);
		}

		if (auxAccount instanceof SavingsAccount) {
			((SavingsAccount) auxAccount).earnInterest();
			Logger.write("Interest credited - Account number: "+auxAccount.getNumber());
		} else {
			Logger.write("Failed attempt to get interest - Account number: "+auxAccount.getNumber() + "is not a SavingsAccount");
			throw new IncompatibleAccountException(number);
		}
	}

	public void doEarnBonus(String number) throws BankTransactionException, IncompatibleAccountException {
		AbstractAccount auxAccount;
		try {
			auxAccount = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			Logger.write("Account number "+number+" not found.");
			throw new BankTransactionException(anfe);
		}

		if (auxAccount instanceof SpecialAccount) {
			((SpecialAccount) auxAccount).earnBonus();
			Logger.write("Bonus credited - Account number: "+auxAccount.getNumber());
		} else {
			Logger.write("Failed attempt to get bonus - Account number: "+auxAccount.getNumber() + "is not a BonusAccount");
			throw new IncompatibleAccountException(number);
		}
	}
}
