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
import banksys.control.exception.BankTransactionException;
import banksys.persistence.AccountFile;

public class ManagerWindowController implements ActionListener{
	private BankController bankController;
		
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			bankController = new BankController(new AccountFile());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(ManagerWindow.removerConta.equals(e.getSource())){
			String sNumber = JOptionPane.showInputDialog(null, "Digite o número da conta.", "BankSys - Remover Conta", JOptionPane
					.INFORMATION_MESSAGE);
			
			if(sNumber != null && !sNumber.isEmpty()){
				
				sNumber = sNumber.trim();
				Integer number;
				
				try{
					number = Integer.parseInt(sNumber);
					
					if(number == 0){
						throw new InvalidValue();
					}
					
					bankController.removeAccount(sNumber);
					JOptionPane.showMessageDialog(null, "Conta removida com sucesso!", "BankSys - Remover Conta",JOptionPane.INFORMATION_MESSAGE);
				} catch(BankTransactionException ex){
					JOptionPane.showMessageDialog(null, ex.getMessage(), "BankSys - Remover Conta", JOptionPane.INFORMATION_MESSAGE);
				} catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Número Inválido", "BankSys - Remover Conta", JOptionPane.INFORMATION_MESSAGE);
				}
			}
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
					JOptionPane.showMessageDialog(null, "Conta criada com sucesso!", "BankSys - Criar Conta",JOptionPane.INFORMATION_MESSAGE);
				} catch(BankTransactionException ex){
					JOptionPane.showMessageDialog(null, ex.getMessage(), "BankSys - Criar Conta", JOptionPane.INFORMATION_MESSAGE);
				} catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Conta não criada", "BankSys - Criar Conta", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			
			
			
		}
		
	}

	
	
}
