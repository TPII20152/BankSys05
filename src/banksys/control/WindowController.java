package banksys.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.omg.CORBA.DynAnyPackage.InvalidValue;

import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;
import banksys.account.TaxAccount;
import banksys.atm.ManagerWindow;
import banksys.persistence.AccountFile;

public class WindowController implements ActionListener{
	private BankController bankController;
		
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			bankController = new BankController(new AccountFile());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(ManagerWindow.removerConta.equals(e.getSource())){
			
		} else {
			
			String sNumber = JOptionPane.showInputDialog(null, "Digite o número da conta.", "BankSys - Criar Conta", JOptionPane
					.INFORMATION_MESSAGE);
			
			if(sNumber != null && !sNumber.isEmpty()){
				
				sNumber = sNumber.trim();
				Integer number;
				AbstractAccount acc;
				
				try{
					number = Integer.parseInt(sNumber);
					
					if(number == 0){
						throw new InvalidValue();
					}
					
					if(ManagerWindow.especial.equals(e.getSource())){
						acc = new SpecialAccount(sNumber);
					}else
					if(ManagerWindow.poupanca.equals(e.getSource())){
						acc = new SavingsAccount(sNumber);
					}else
					if(ManagerWindow.imposto.equals(e.getSource())){
						acc = new TaxAccount(sNumber);
					} else {
						acc = new OrdinaryAccount(sNumber);
					}
					
					bankController.addAccount(acc);
					
				} catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Conta não criada", "BankSys - Criar Conta", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			
			
			
		}
		
	}

	
	
}
