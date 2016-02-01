package banksys.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import banksys.atm.AtmWindow;
import banksys.persistence.AccountFile;

public class AtmWindowController implements ActionListener{
	private BankController bankController;
	private String sNumber;
	
	public AtmWindowController(String sNumber) {
		this.sNumber = sNumber;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			bankController = new BankController(new AccountFile());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(AtmWindow.creditar.equals(e)){
			
		}else
		if(AtmWindow.debitar.equals(e)){
			
		}else
		if(AtmWindow.juros.equals(e)){
			
		}else
		if(AtmWindow.saldo.equals(e)){
			
		}else
		if(AtmWindow.transferir.equals(e)){
			
		}else
		if(AtmWindow.bonus.equals(e)){
			
		}
	}

	
	
}
