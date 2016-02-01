package banksys.atm;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

import banksys.control.AtmWindowController;

public class AtmWindow {

	private JFrame frame;
	private String sNumber;
	public static JMenuItem creditar;		
	public static JMenuItem debitar;
	public static JMenuItem transferir;
	public static JMenuItem saldo;
	public static JMenuItem juros;
	public static JMenuItem bonus;
	
	private AtmWindowController atmWindowController;

	/**
	 * Criando a Aplicação.
	 */
	public AtmWindow(String sNumber) {
		this.sNumber = sNumber;
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
		
		atmWindowController = new AtmWindowController(sNumber);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		creditar = new JMenuItem("Creditar");
		creditar.addActionListener(atmWindowController);
		menuBar.add(creditar);
				
		debitar = new JMenuItem("Debitar");
		debitar.addActionListener(atmWindowController);
		menuBar.add(debitar);
		
		transferir = new JMenuItem("Transferência");
		transferir.addActionListener(atmWindowController);
		menuBar.add(transferir);
		
		saldo = new JMenuItem("Saldo");
		saldo.addActionListener(atmWindowController);
		menuBar.add(saldo);
		
		juros = new JMenuItem("Juros");
		juros.addActionListener(atmWindowController);
		menuBar.add(juros);
		
		bonus = new JMenuItem("Bonus");
		bonus.addActionListener(atmWindowController);
		menuBar.add(bonus);
		
		frame.setVisible(true);
	}

}
