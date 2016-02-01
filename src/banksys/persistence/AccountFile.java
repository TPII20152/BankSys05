package banksys.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import banksys.account.AbstractAccount;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;

public class AccountFile implements IAccountRepository {

	private File file;
	
	public AccountFile() throws IOException {
		file = new File(System.getProperty("user.home") + File.separator + "Bank Sys" + File.separator + "accounts.xml");
		
		if(!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
			
			XStream xstream = new XStream();
			xstream.toXML(new ArrayList<AbstractAccount>(), new FileOutputStream(file));
		}
	}
	
	@Override
	public void create(AbstractAccount acc) throws AccountCreationException {
		List<AbstractAccount> list = readFile();
		
		if (rapidSearch(acc.getNumber(),list) == null) {
			list.add(acc);
		} else {
			throw new AccountCreationException("OrdinaryAccount alredy exist!", acc.getNumber());
		}
		
		try {
			saveFile(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String number) throws AccountDeletionException {
		List<AbstractAccount> list = readFile();
		
		AbstractAccount acc = rapidSearch(number,list);
		if (acc != null) {
			list.remove(acc);
		} else {
			throw new AccountDeletionException("OrdinaryAccount doesn't exist!", number);
		}
		
		try {
			saveFile(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public AbstractAccount retrieve(String number) throws AccountNotFoundException {
		List<AbstractAccount> lista = readFile();
		
		if (!lista.isEmpty()) {
			for (AbstractAccount conta : lista) {
				if (conta.getNumber().equals(number)) {
					return conta;
				}
			}
		}
		throw new AccountNotFoundException("OrdinaryAccount not found!", number);
	}

	@Override
	public AbstractAccount[] list() {
		List<AbstractAccount> list = readFile();
		
		AbstractAccount[] array = null;
		if (!list.isEmpty()) {
			array = new AbstractAccount[list.size()];
			int i = 0;
			for (AbstractAccount acc : list) {
				array[i++] = acc;
			}
		}
		return array;
	}

	@Override
	public int numberOfAccounts() {
		List<AbstractAccount> list = readFile();
		return list.size();
	}

	@Override
	public void update(AbstractAccount newAcc) throws AccountNotFoundException {
		List<AbstractAccount> list = readFile();
		
		AbstractAccount old = rapidSearch(newAcc.getNumber(), list);
		
		if(old == null)
			throw new AccountNotFoundException("OrdinaryAccount not found!", newAcc.getNumber());
		
		list.remove(old);
		list.add(newAcc);
		
		try {
			saveFile(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private AbstractAccount rapidSearch(String number, List<AbstractAccount> list) {
		for (AbstractAccount account : list) {
			if(account.getNumber().equals(number))
				return account;
		}
		return null;
	}
	
	private List<AbstractAccount> readFile() {
		List<AbstractAccount> list = null;
		XStream xstream = new XStream();
		list = (List<AbstractAccount>) xstream.fromXML(file);

		if(list != null) {
			return list;
		} else {
			return new ArrayList<AbstractAccount>();
		}
	}
	
	private void saveFile(List<AbstractAccount> list) throws FileNotFoundException {
		XStream xstream = new XStream();
		xstream.toXML(list, new FileOutputStream(file));
	}


}
