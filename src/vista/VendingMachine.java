package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import controller.VisualController;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VendingMachine extends JFrame implements ActionListener, ItemListener, ChangeListener {

	private JPanel contentPane;
	private JTextField textFieldPrecio;
	private JButton btnCocaCola;
	private JButton btnAgua;
	private JButton btnFanta;
	private JButton btnCafe;
	private JButton btnPepsi;
	private JButton btnNesquik;
	private JButton btnExtraer_6;
	private JButton btnExtraer_7;
	private JButton btnColacao;
	private JButton btnNestea;
	private JButton btnAquarius;
	private JButton btnExtraer_11;
	private JButton btnDevolverDinero;
	private JButton btnPagar;
	private JTextField txtGlucosa;
	private JTextField txtGluten;
	private JTextField txtSulfitos;
	private JTextField txtFrutosSecos;
	private JMenuBar menu;
	private JMenu pagos;
	private JMenuItem tarjeta;
	private JMenuItem monedas;
	private JLabel lblSelection;
	private JLabel lblGlucosa;
	
	VisualController controller;
	private JButton btnPagoMonedas;
	
	/**
	 * Create the frame.
	 */
	public VendingMachine(VisualController controller) {

		// set components
		this.controller = controller;
		
		// metadata
		setFont(new Font("Dialog", Font.PLAIN, 16));
		setTitle("COFFEEBREAK");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 500, 519);
		buildView();
	}
	
	/**
	 * Create the frame.
	 */
	public void buildView() {
		

		// principal panel
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Componentes de la pantalla
		textFieldPrecio = new JTextField();
		textFieldPrecio.setBackground(new Color(115, 147, 19));
		textFieldPrecio.setForeground(new Color(0, 0, 0));
		textFieldPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPrecio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldPrecio.setText("");
		textFieldPrecio.setEditable(false);

		textFieldPrecio.setBounds(317, 66, 146, 81);
		contentPane.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);

		/////Menu
		this.menu= new JMenuBar();
		setJMenuBar(menu);
		
		this.pagos= new JMenu("Metodo pago");	
		this.tarjeta=new JMenuItem("Tarjeta");
		tarjeta.addActionListener(controller);
		tarjeta.setActionCommand("target");
		this.monedas=new JMenuItem("Monedas");
		monedas.addActionListener(controller);
		monedas.setActionCommand("monedas");
		
		this.pagos.add(tarjeta);
		this.pagos.add(monedas);
		this.menu.add(pagos);
		
		
		
		
		
		btnCocaCola = new JButton("CocaCola");
		btnCocaCola.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectProduct("coca");
			}
		});
		btnCocaCola.addActionListener(this);
		btnCocaCola.setBounds(24, 66, 78, 35);
		contentPane.add(btnCocaCola);

		btnAgua = new JButton("Agua");
		btnAgua.setBounds(24, 112, 78, 35);
		btnAgua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectProduct("Agua");
			}
		});
		contentPane.add(btnAgua);

		btnFanta = new JButton("Fanta");
		btnFanta.setBounds(112, 66, 78, 35);
		btnFanta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectProduct("Fanta");
			}
		});
		contentPane.add(btnFanta);

		btnCafe = new JButton("Cafe");
		btnCafe.setBounds(24, 158, 78, 35);
		btnCafe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectProduct("Cafe");
			}
		});
		contentPane.add(btnCafe);

		btnPepsi = new JButton("Pepsi");
		btnPepsi.setBounds(112, 112, 78, 35);
		btnPepsi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectProduct("Pepsi");
			}
		});
		contentPane.add(btnPepsi);

		btnNesquik = new JButton("Nesquik");
		btnNesquik.setBounds(200, 158, 78, 35);
		btnNesquik.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectProduct("Nesquik");
			}
		});
		contentPane.add(btnNesquik);

		btnExtraer_6 = new JButton("");
		btnExtraer_6.setBounds(112, 204, 78, 35);
		contentPane.add(btnExtraer_6);

		btnExtraer_7 = new JButton("");
		btnExtraer_7.setBounds(24, 204, 78, 35);
		contentPane.add(btnExtraer_7);

		btnColacao = new JButton("Colacao");
		btnColacao.setBounds(112, 158, 78, 35);
		btnColacao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectProduct("Colacao");
			}
		});
		contentPane.add(btnColacao);

		btnNestea = new JButton("Nestea");
		btnNestea.setBounds(200, 66, 78, 35);
		btnNestea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectProduct("Nestea");
			}
		});
		contentPane.add(btnNestea);

		btnAquarius = new JButton("Aquarius");
		btnAquarius.setBounds(200, 112, 78, 35);
		btnAquarius.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectProduct("Aquarius");
			}
		});
		contentPane.add(btnAquarius);

		btnExtraer_11 = new JButton("");
		btnExtraer_11.setBounds(200, 204, 78, 35);
		contentPane.add(btnExtraer_11);

		btnDevolverDinero = new JButton("DEVOLVER DINERO");
		btnDevolverDinero.setBackground(new Color(8, 8, 8));
		btnDevolverDinero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.returnCoins();
			}
			
		});
		btnDevolverDinero.setBounds(317, 158, 146, 35);
		contentPane.add(btnDevolverDinero);

		btnPagar = new JButton("PAGAR");
		btnPagar.setBackground(new Color(8, 8, 8));
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPagar.setBounds(317, 204, 146, 35);
		contentPane.add(btnPagar);

		txtGlucosa = new JTextField();
		txtGlucosa.setText("Glucosa");
		txtGlucosa.setBounds(24, 298, 50, 20);
		contentPane.add(txtGlucosa);
		txtGlucosa.setColumns(10);

		txtGluten = new JTextField();
		txtGluten.setText("Gluten");
		txtGluten.setColumns(10);
		txtGluten.setBounds(84, 298, 50, 20);
		contentPane.add(txtGluten);

		txtSulfitos = new JTextField();
		txtSulfitos.setText("Sulfitos");
		txtSulfitos.setColumns(10);
		txtSulfitos.setBounds(140, 298, 50, 20);
		contentPane.add(txtSulfitos);

		txtFrutosSecos = new JTextField();
		txtFrutosSecos.setText("Frutos secos");
		txtFrutosSecos.setColumns(10);
		txtFrutosSecos.setBounds(200, 298, 78, 20);
		contentPane.add(txtFrutosSecos);
		
		
		lblSelection = new JLabel("");
		lblSelection.setBackground(new Color(115, 147, 19));
		lblSelection.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelection.setBounds(24, 26, 251, 35);
		contentPane.add(lblSelection);
		
		JButton btnLogOff = new JButton("LOG OFF");
		btnLogOff.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				controller.actionPerformed(e);
			}
		});
		btnLogOff.setBounds(317, 307, 146, 35);
		contentPane.add(btnLogOff);


	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void selectProduct(String value) {
		controller.takeProduct(value);
	}

	public void setTextFieldPrecio(String newTxt) {
		textFieldPrecio.setText(newTxt);
		
	}
	

	public void setLblTxt(String newTxt) {
		lblSelection.setText(newTxt);
		
	}
}
