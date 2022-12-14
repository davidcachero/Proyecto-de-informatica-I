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
import java.awt.GridLayout;

public class VendingMachine extends JFrame implements ActionListener, ItemListener, ChangeListener {

	private JPanel contentPane;
	private JTextField txfEstado;
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
	private JTextField txfProducto;
	private JTextField txfSaldo;

	VisualController controller;
	String prodSelected;

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
		setBounds(300, 100, 739, 519);
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
		txfEstado = new JTextField();
		txfEstado.setBackground(new Color(115, 147, 19));
		txfEstado.setForeground(new Color(0, 0, 0));
		txfEstado.setHorizontalAlignment(SwingConstants.CENTER);
		txfEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txfEstado.setText("");
		txfEstado.setEditable(false);

		txfEstado.setBounds(433, 32, 254, 47);
		contentPane.add(txfEstado);
		txfEstado.setColumns(10);

		txfProducto = new JTextField();
		txfProducto.setText("");
		txfProducto.setHorizontalAlignment(SwingConstants.CENTER);
		txfProducto.setForeground(Color.BLACK);
		txfProducto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txfProducto.setEditable(false);
		txfProducto.setColumns(10);
		txfProducto.setBackground(new Color(115, 147, 19));
		txfProducto.setBounds(433, 84, 254, 47);
		contentPane.add(txfProducto);

		txfSaldo = new JTextField();
		txfSaldo.setText("");
		txfSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		txfSaldo.setForeground(Color.BLACK);
		txfSaldo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txfSaldo.setEditable(false);
		txfSaldo.setColumns(10);
		txfSaldo.setBackground(new Color(115, 147, 19));
		txfSaldo.setBounds(433, 137, 254, 47);
		contentPane.add(txfSaldo);
		
		///// Menu
		this.menu = new JMenuBar();
		setJMenuBar(menu);

		this.pagos = new JMenu("Metodo pago");
		this.tarjeta = new JMenuItem("Tarjeta");
		tarjeta.addActionListener(controller);
		tarjeta.setActionCommand("target");
		this.monedas = new JMenuItem("Monedas");
		monedas.addActionListener(controller);
		monedas.setActionCommand("monedas");

		this.pagos.add(tarjeta);
		this.pagos.add(monedas);
		this.menu.add(pagos);

		btnDevolverDinero = new JButton("DEVOLVER DINERO");
		btnDevolverDinero.setBackground(new Color(8, 8, 8));
		btnDevolverDinero.setForeground(Color.white);
		btnDevolverDinero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.returnCoins();
			}

		});
		btnDevolverDinero.setBounds(516, 204, 171, 35);
		contentPane.add(btnDevolverDinero);

		btnPagar = new JButton("PAGAR");
		btnPagar.setBackground(new Color(8, 8, 8));
		btnPagar.setForeground(Color.white);
		btnPagar.setBounds(516, 250, 171, 35);
		contentPane.add(btnPagar);

		txtGlucosa = new JTextField();
		txtGlucosa.setEditable(false);
		txtGlucosa.setHorizontalAlignment(SwingConstants.CENTER);
		txtGlucosa.setText("Glucosa");
		txtGlucosa.setBounds(516, 350, 50, 20);
		contentPane.add(txtGlucosa);
		txtGlucosa.setColumns(10);

		txtGluten = new JTextField();
		txtGluten.setEditable(false);
		txtGluten.setHorizontalAlignment(SwingConstants.CENTER);
		txtGluten.setText("Gluten");
		txtGluten.setColumns(10);
		txtGluten.setBounds(576, 350, 50, 20);
		contentPane.add(txtGluten);

		txtSulfitos = new JTextField();
		txtSulfitos.setEditable(false);
		txtSulfitos.setHorizontalAlignment(SwingConstants.CENTER);
		txtSulfitos.setText("Sulfitos");
		txtSulfitos.setColumns(10);
		txtSulfitos.setBounds(637, 350, 50, 20);
		contentPane.add(txtSulfitos);

		txtFrutosSecos = new JTextField();
		txtFrutosSecos.setEditable(false);
		txtFrutosSecos.setHorizontalAlignment(SwingConstants.CENTER);
		txtFrutosSecos.setText("Frutos secos");
		txtFrutosSecos.setColumns(10);
		txtFrutosSecos.setBounds(424, 350, 78, 20);
		contentPane.add(txtFrutosSecos);

		JButton btnLogOff = new JButton("LOG OFF");
		btnLogOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.actionPerformed(e);
			}
		});
		btnLogOff.setBounds(516, 296, 171, 35);
		contentPane.add(btnLogOff);

		JPanel panel = new JPanel();
		panel.setBounds(24, 26, 329, 425);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));

		btnCocaCola = new JButton("CocaCola");
		panel.add(btnCocaCola);

		btnFanta = new JButton("Fanta");
		panel.add(btnFanta);

		btnNestea = new JButton("Nestea");
		panel.add(btnNestea);

		btnAgua = new JButton("Agua");
		panel.add(btnAgua);

		btnPepsi = new JButton("Pepsi");
		panel.add(btnPepsi);

		btnAquarius = new JButton("Aquarius");
		panel.add(btnAquarius);

		btnCafe = new JButton("Cafe");
		panel.add(btnCafe);

		btnColacao = new JButton("Colacao");
		panel.add(btnColacao);

		btnNesquik = new JButton("Nesquik");
		panel.add(btnNesquik);

		btnExtraer_7 = new JButton("");
		panel.add(btnExtraer_7);

		btnExtraer_6 = new JButton("");
		panel.add(btnExtraer_6);

		btnExtraer_11 = new JButton("");
		panel.add(btnExtraer_11);
		
	
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.sellProduct(prodSelected);
			}
		});
		
		btnNesquik.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.takeProduct("Nesquik");
			}
		});
		btnColacao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.takeProduct("Colacao");
			}
		});
		btnCafe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.takeProduct("Cafe");
			}
		});
		btnAquarius.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.takeProduct("Aquarius");
			}
		});
		btnPepsi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.takeProduct("Pepsi");
			}
		});
		btnAgua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.takeProduct("Agua");
			}
		});
		btnNestea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.takeProduct("Nestea");
			}
		});
		btnFanta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.takeProduct("Fanta");
			}
		});
		btnCocaCola.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.takeProduct("coca");
			}
		});
		btnCocaCola.addActionListener(this);

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

	public void setTextFieldStatus(String newTxt) {
		txfEstado.setText(newTxt);

	}

	public void setTextFieldProduct(String newProduct) {
		prodSelected = newProduct;
		txfProducto.setText("Producto seleccionado: " + newProduct);

	}

	public void clnTextFieldProduct() {
		setTextFieldStatus("");
		
	}
	
	public void setTextFieldBalance(String newBalance) {
		txfSaldo.setText("Saldo: " + newBalance + " ctms");

	}
	public void setTextFieldBalance(int newBalance) {
		txfSaldo.setText("Saldo: " + Integer.toString(newBalance) + " ctms");

	}

	public void setSelectedProd(int id) {
		prodSelected = Integer.toString(id);
		
	}
	
}
