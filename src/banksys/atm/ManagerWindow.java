package banksys.atm;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

import banksys.control.ManagerWindowController;

public class ManagerWindow {

	private JFrame frame;
	private ManagerWindowController windowController;
	public static JMenuItem comum;
	public static JMenuItem especial;
	public static JMenuItem poupanca;
	public static JMenuItem imposto;
	public static JMenuItem removerConta;

	/**
	 * Criando a aplicação.
	 */
	public ManagerWindow() {
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
		windowController = new ManagerWindowController();
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu criarConta = new JMenu("Criar Conta");
		menuBar.add(criarConta);
		
		comum = new JMenuItem("Comum");
		comum.addActionListener(windowController);
		criarConta.add(comum);
		especial = new JMenuItem("Especial");
		especial.addActionListener(windowController);
		criarConta.add(especial);
		poupanca = new JMenuItem("Poupança");
		poupanca.addActionListener(windowController);
		criarConta.add(poupanca);
		imposto = new JMenuItem("Imposto");
		imposto.addActionListener(windowController);
		criarConta.add(imposto);
		
		
		removerConta = new JMenuItem("Remover Conta");
		removerConta.addActionListener(windowController);
		menuBar.add(removerConta);
		
		
		/*frame.getContentPane().setLayout(new GridLayout(qtdContas, 2));*/
		frame.setVisible(true);
	}

}
