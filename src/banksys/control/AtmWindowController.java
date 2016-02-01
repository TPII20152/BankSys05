package banksys.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.omg.CORBA.DynAnyPackage.InvalidValue;

import banksys.atm.AtmWindow;
import banksys.control.exception.BankTransactionException;
import banksys.persistence.AccountFile;

public class AtmWindowController implements ActionListener{
	private BankController bankController;
	private String accNumber;
	
	public AtmWindowController(String sNumber) {
		this.accNumber = sNumber;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			bankController = new BankController(new AccountFile());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(AtmWindow.creditar.equals(e.getSource())){
			String sValue = JOptionPane.showInputDialog(null, "Digite o valor a ser creditado.", "BankSys - Crédito", JOptionPane.INFORMATION_MESSAGE);

			if(sValue != null && !sValue.isEmpty()) {	// CANCEL OPTION

				sValue = sValue.trim();
				Double value;
				try {
					value = Double.parseDouble(sValue);
					
					if(value == 0d) throw new InvalidValue();
					
					bankController.doCredit(accNumber, value);
					JOptionPane.showMessageDialog(null, "Crédito realizado com sucesso.", "BankSys - Crédito", JOptionPane.INFORMATION_MESSAGE);
				} catch (BankTransactionException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "BankSys - Crédito", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Crédito não realizado.", "BankSys - Crédito", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else
		if(AtmWindow.debitar.equals(e.getSource())){
			String sValue = JOptionPane.showInputDialog(null, "Digite o valor a ser debitado.", "BankSys - Débito", JOptionPane.INFORMATION_MESSAGE);

			if(sValue != null && !sValue.isEmpty()) {	// CANCEL OPTION

				sValue = sValue.trim();
				Double value;
				try {
					value = Double.parseDouble(sValue);
					
					if(value == 0d) throw new InvalidValue();
					
					bankController.doDebit(accNumber, value);
					JOptionPane.showMessageDialog(null, "Débito realizado com sucesso.", "BankSys - Débito", JOptionPane.INFORMATION_MESSAGE);
				} catch (BankTransactionException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "BankSys - Débito", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Débito não realizado.", "BankSys - Débito", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else
		if(AtmWindow.juros.equals(e.getSource())){
			try {
				bankController.doEarnInterest(accNumber);
				JOptionPane.showMessageDialog(null, "Acréscimo realizado com sucesso.", "BankSys - Render Juros", JOptionPane.INFORMATION_MESSAGE);
			} catch (BankTransactionException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "BankSys - Render Juros", JOptionPane.ERROR_MESSAGE);
			}
		}else
		if(AtmWindow.saldo.equals(e.getSource())){
			try {
				double balance = bankController.getBalance(accNumber);
				
				String msg = "Conta: " + accNumber + "\n"
						+ "Saldo: " + balance;
				
				JOptionPane.showMessageDialog(null, msg, "BankSys - Visualizar Saldo", JOptionPane.INFORMATION_MESSAGE);
			} catch (BankTransactionException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "BankSys - Visualizar Saldo", JOptionPane.ERROR_MESSAGE);
			}
		}else
		if(AtmWindow.transferir.equals(e.getSource())){
			
			
			String sNumber = JOptionPane.showInputDialog(null, "Digite o número da conta de destino.", "BankSys - Transferência", JOptionPane
					.INFORMATION_MESSAGE);
			
			if(sNumber != null && !sNumber.isEmpty()){
				sNumber = sNumber.trim();
				Integer number;
				
				try{
					number = Integer.parseInt(sNumber);
					
					if(number == 0){
						throw new InvalidValue();
					}

					String sValue = JOptionPane.showInputDialog(null, "Digite o valor a ser transferido.", "BankSys - Transferência", JOptionPane.INFORMATION_MESSAGE);

					if(sValue != null && !sValue.isEmpty()) {	// CANCEL OPTION

						sValue = sValue.trim();
						Double value;
						try {
							value = Double.parseDouble(sValue);
							
							if(value == 0d) throw new InvalidValue();
							
							bankController.doTransfer(accNumber, sNumber, value);
							JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso.", "BankSys - Transferência", JOptionPane.INFORMATION_MESSAGE);
						} catch (BankTransactionException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "BankSys - Transferência", JOptionPane.ERROR_MESSAGE);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Transferência não realizada.", "BankSys - Transferência", JOptionPane.ERROR_MESSAGE);
						}
					}					
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Número Inválido", "BankSys - Transferência", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		}else
		if(AtmWindow.bonus.equals(e.getSource())){
			try {
				bankController.doEarnBonus(accNumber);
				JOptionPane.showMessageDialog(null, "Acréscimo realizado com sucesso.", "BankSys - Render Bonus", JOptionPane.INFORMATION_MESSAGE);
			} catch (BankTransactionException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "BankSys - Render Bonus", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
