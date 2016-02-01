package banksys.atm;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

import banksys.control.ManagerWindowController;

public class ManagerWindow extends JFrame {

	private static final long serialVersionUID = -8484993553270017280L;
	
	private ManagerWindowController windowController;
	public static JMenuItem comum;
	public static JMenuItem especial;
	public static JMenuItem poupanca;
	public static JMenuItem imposto;
	public static JMenuItem removerConta;

	public ManagerWindow() {
		initialize();
	}

	/**
	 * Inicializando o Frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		getContentPane().setBackground(UIManager.getColor("Button.darkShadow"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		windowController = new ManagerWindowController();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
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
		
		
		setVisible(true);
	}
	
	@Override
	public void dispose() {
		MainWindow.SetVisible(true);
		super.dispose();
	}

}
