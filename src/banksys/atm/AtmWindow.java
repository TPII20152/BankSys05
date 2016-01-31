package banksys.atm;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;

public class AtmWindow {

	private JFrame frame;

	/**
	 * Criando a Aplicação.
	 */
	public AtmWindow() {
		initialize();
	}

	/**
	 * Inicializando o Frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setBackground(UIManager.getColor("Button.darkShadow"));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu creditar = new JMenu("Creditar");
		menuBar.add(creditar);
				
		JMenu debitar = new JMenu("Debitar");
		menuBar.add(debitar);
		
		JMenu transferir = new JMenu("Transferência");
		menuBar.add(transferir);
		
		JMenu saldo = new JMenu("Saldo");
		menuBar.add(saldo);
		
		JMenu juros = new JMenu("Juros");
		menuBar.add(juros);
		
		JMenu bonus = new JMenu("Bonus");
		menuBar.add(bonus);
		
		frame.setVisible(true);
	}

}
