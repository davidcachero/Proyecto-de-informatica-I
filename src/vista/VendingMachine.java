package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import controller.VisualController;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseMotionAdapter;

/**
 * 
 * @author equipoCoffeeBreak
 * 
 *         Vista completa de la interfaz grafica
 *
 */

public class VendingMachine extends JFrame implements ActionListener, ItemListener, ChangeListener {

	private JPanel contentPane;
	private JTextField txfEstado;
	private JButton btnCocaCola;
	private JButton btnAgua;
	private JButton btnFantal;
	private JButton btnCafe;
	private JButton btnPepsi;
	private JButton btnNesquik;
	private JButton btnFantaN;
	private JButton btnColacao;
	private JButton btnNestea;
	private JButton btnAquarius;
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
	private JLabel lbluserLoggedName;
	private JLabel tf_timer;
	private JButton btnLogOff;
	private JLabel label;
	
	private MouseAdapter btnProductStyle;

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
		setTitle("COFFEE BREAK");
		setIconImage(controller.getImage("Logo_CoffeeBreak", "png", 2000, 2000));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 739, 519);

		buildView();
		setFuncionalities();

	}

	/**
	 * Create the frame.
	 */
	private void buildView() {

		// principal panel
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		///// Menu
		this.menu = new JMenuBar();

		txfEstado = new JTextField();
		txfEstado.setBackground(SystemColor.window);//115, 147, 19));
		txfEstado.setForeground(new Color(0, 0, 0));
		txfEstado.setBorder(new EmptyBorder(15, 15, 15, 15));
		txfEstado.setHorizontalAlignment(SwingConstants.CENTER);
		txfEstado.setFont(new Font("Tahoma", Font.BOLD, 15));
		txfEstado.setText("");
		txfEstado.setEditable(false);
		txfEstado.setColumns(10);
		

		txfSaldo = new JTextField();
		txfSaldo.setText("");
		txfSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		txfSaldo.setForeground(Color.BLACK);
		txfSaldo.setBorder(new EmptyBorder(15, 15, 15, 15));
		txfSaldo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txfSaldo.setEditable(false);
		txfSaldo.setColumns(10);
		txfSaldo.setBackground(SystemColor.window);
		
		JPanel terminalPanel = new JPanel();
		terminalPanel.setBorder(new LineBorder(new Color(106, 108, 255), 2));
		terminalPanel.setBounds(398, 56, 289, 160);
		
		
		// Componentes de la pantalla
		txfProducto = new JTextField();
		txfProducto.setText("");
		txfProducto.setHorizontalAlignment(SwingConstants.CENTER);
		txfProducto.setForeground(Color.BLACK);
		txfProducto.setBorder(new EmptyBorder(15, 15, 15, 15));
		txfProducto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txfProducto.setEditable(false);
		txfProducto.setColumns(10);
		txfProducto.setBackground(SystemColor.window);
		terminalPanel.add(txfProducto);
		terminalPanel.add(txfEstado);
		terminalPanel.add(txfSaldo);
		terminalPanel.setLayout(new BoxLayout(terminalPanel, BoxLayout.Y_AXIS));
		contentPane.add(terminalPanel);
		

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
		btnDevolverDinero.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDevolverDinero.setBackground(new Color(106, 108, 255));
		btnDevolverDinero.setForeground(Color.WHITE);
		btnDevolverDinero.setBounds(398, 227, 289, 60);
		contentPane.add(btnDevolverDinero);

		btnPagar = new JButton("PAGAR");
		btnPagar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPagar.setBackground(new Color(106, 108, 255));
		btnPagar.setForeground(Color.WHITE);
		btnPagar.setBounds(398, 291, 289, 60);
		contentPane.add(btnPagar);

		txtGlucosa = new JTextField();
		txtGlucosa.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtGlucosa.setBackground(SystemColor.textHighlightText);
		txtGlucosa.setEditable(false);
		txtGlucosa.setHorizontalAlignment(SwingConstants.CENTER);
		txtGlucosa.setText("Glucosa");
		txtGlucosa.setBorder(null);
		txtGlucosa.setBounds(158, 25, 78, 20);
		contentPane.add(txtGlucosa);
		txtGlucosa.setColumns(10);

		txtGluten = new JTextField();
		txtGluten.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtGluten.setBackground(SystemColor.textHighlightText);
		txtGluten.setEditable(false);
		txtGluten.setHorizontalAlignment(SwingConstants.CENTER);
		txtGluten.setText("Gluten");
		txtGluten.setBorder(null);
		txtGluten.setColumns(10);
		txtGluten.setBounds(216, 25, 78, 20);
		contentPane.add(txtGluten);

		txtSulfitos = new JTextField();
		txtSulfitos.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtSulfitos.setEditable(false);
		txtSulfitos.setBackground(SystemColor.textHighlightText);
		txtSulfitos.setHorizontalAlignment(SwingConstants.CENTER);
		txtSulfitos.setText("Sulfitos");
		txtSulfitos.setBorder(null);
		txtSulfitos.setColumns(10);
		txtSulfitos.setBounds(96, 25, 78, 20);
		contentPane.add(txtSulfitos);

		txtFrutosSecos = new JTextField();
		txtFrutosSecos.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtFrutosSecos.setBackground(SystemColor.textHighlightText);
		txtFrutosSecos.setEditable(false);
		txtFrutosSecos.setHorizontalAlignment(SwingConstants.CENTER);
		txtFrutosSecos.setText("Frutos secos");
		txtFrutosSecos.setBorder(null);
		txtFrutosSecos.setColumns(10);
		txtFrutosSecos.setBounds(288, 25, 78, 20);
		contentPane.add(txtFrutosSecos);

		btnLogOff = new JButton("CERRAR SESIÓN");
		btnLogOff.setBackground(new Color(255, 66, 66));
		btnLogOff.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogOff.setVisible(false);
		btnLogOff.setForeground(Color.WHITE);
		btnLogOff.setBounds(398, 367, 289, 35);

		contentPane.add(btnLogOff);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		panel.setBounds(24, 56, 342, 395);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));

		btnCocaCola = new JButton();
		btnCocaCola.setName("coca");
		btnCocaCola.setBorder(null);
		btnCocaCola.setIcon(new ImageIcon(controller.getImage("product_logo/CC", "jpg", 130, 110)));
		btnCocaCola.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btnCocaCola);

		btnFantal = new JButton();
		btnFantal.setName("fantal");
		btnFantal.setBorder(null);
		btnFantal.setIcon(new ImageIcon(controller.getImage("product_logo/FL", "PNG", 150, 120)));
		btnFantal.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btnFantal);

		btnNestea = new JButton();
		btnNestea.setName("nestea");
		btnNestea.setBorder(null);
		btnNestea.setIcon(new ImageIcon(controller.getImage("product_logo/NT", "JPG", 120, 120)));
		btnNestea.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btnNestea);

		btnAgua = new JButton();
		btnAgua.setName("agua");
		btnAgua.setBorder(null);
		btnAgua.setIcon(new ImageIcon(controller.getImage("product_logo/SC", "jpg", 120, 120)));
		btnAgua.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btnAgua);

		btnPepsi = new JButton(); // En datos de prueba no existe
		btnPepsi.setName("Pepsi");
		btnPepsi.setBorder(null);
		btnPepsi.setIcon(new ImageIcon(controller.getImage("product_logo/PP", "JPG", 120, 120)));
		btnPepsi.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btnPepsi);

		btnAquarius = new JButton();
		btnAquarius.setName("acua");
		btnAquarius.setBorder(null);
		btnAquarius.setIcon(new ImageIcon(controller.getImage("product_logo/AQ", "jpg", 120, 120)));
		btnAquarius.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btnAquarius);

		btnCafe = new JButton();
		btnCafe.setName("cafe");
		btnCafe.setBorder(null);
		btnCafe.setIcon(new ImageIcon(controller.getImage("product_logo/NCAFE", "jpg", 120, 120)));
		btnCafe.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btnCafe);

		btnColacao = new JButton();
		btnColacao.setName("Colacao");
		btnColacao.setBorder(null);
		btnColacao.setIcon(new ImageIcon(controller.getImage("product_logo/CCao", "jpg", 120, 120)));
		btnColacao.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btnColacao);

		btnNesquik = new JButton(); // En datos de prueba no existe
		btnNesquik.setName("Nesquik");
		btnNesquik.setBorder(null);
		btnNesquik.setIcon(new ImageIcon(controller.getImage("product_logo/NK", "jpg", 120, 120)));
		btnNesquik.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btnNesquik);

		btnFantaN = new JButton(); // En datos de prueba excede saldo
		btnFantaN.setName("fantan");
		btnFantaN.setBorder(null);
		btnFantaN.setIcon(new ImageIcon(controller.getImage("product_logo/FN", "jpg", 120, 120)));
		btnFantaN.setHorizontalAlignment(SwingConstants.CENTER);
		btnFantaN.setBorder(new EmptyBorder(15, 15, 15, 15));
		panel.add(btnFantaN);

		lbluserLoggedName = new JLabel();
		lbluserLoggedName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbluserLoggedName.setBounds(398, 25, 171, 37);
		contentPane.add(lbluserLoggedName);
		
		tf_timer = new JLabel();
		tf_timer.setBounds(579, 0, 124, 35);
		tf_timer.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(tf_timer);
		
		JLabel lblNewLabel = new JLabel("Alergenos");
		lblNewLabel.setBounds(24, 25, 65, 20);
		contentPane.add(lblNewLabel);

	}

	/**
	 * Set de funcionalities of the frame
	 */
	private void setFuncionalities() {

		btnDevolverDinero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.returnCoins();
			}

		});

		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.sellProduct(prodSelected);
			}
		});
		
		
		btnLogOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.logOffUser(true);
			}
		});
		
		
		
		
		btnProductStyle = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nameProd = ((JButton) e.getSource()).getName();
				System.out.println(nameProd);
				controller.takeProduct(nameProd);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton btnSelected = ((JButton) e.getSource());
				btnSelected.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				JButton btnSelected = ((JButton) e.getSource());
				btnSelected.setBorder(null);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				JButton btnSelected = ((JButton) e.getSource());
				btnSelected.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				JButton btnSelected = ((JButton) e.getSource());
				btnSelected.setBorder(null);
			}
		};
		
		btnCocaCola.addMouseListener(btnProductStyle);
		btnNesquik.addMouseListener(btnProductStyle);
		btnColacao.addMouseListener(btnProductStyle);
		btnCafe.addMouseListener(btnProductStyle);
		btnAquarius.addMouseListener(btnProductStyle);
		btnPepsi.addMouseListener(btnProductStyle);
		btnAgua.addMouseListener(btnProductStyle);
		btnNestea.addMouseListener(btnProductStyle);
		btnFantal.addMouseListener(btnProductStyle);
		btnFantaN.addMouseListener(btnProductStyle);

	}


	
	public void setTextFieldStatus(String newTxt) {
		txfEstado.setText(newTxt);

	}

	public void setTextFieldProduct(String newProduct) {
		txfProducto.setText("Producto seleccionado: " + newProduct);

	}

	public void clnTextFieldProduct() {
		setTextFieldStatus("");

	}

	public void setTextFieldBalance(String newBalance) {
		txfSaldo.setText("Saldo: " + newBalance + " euros");

	}

	public void setTextFieldBalance(Float newBalance) {
		txfSaldo.setText("Saldo: " + Float.toString(newBalance) + " euros");

	}

	public void setSelectedProd(String id) {
		System.out.println("[DEV] SELECCIONANDO PRODUCTO " + id);
		prodSelected = id;

	}

	public void setUserLoggedName(String name) {
		System.out.println(name != null ? "[DEV] loggeando a " + name : "Usuario Deslogeado");
		if (name == null)
			lbluserLoggedName.setText(null);

		else
			lbluserLoggedName.setText("BIENVENIDO: " + name);

	}

	public void showIntoleranceFrutSecos() {
		txtFrutosSecos.setVisible(true);

	}

	public void showIntoleranceGlucosa() {
		txtGlucosa.setVisible(true);

	}

	public void showIntoleranceGluten() {
		txtGluten.setVisible(true);

	}

	public void showIntoleranceSulfitos() {
		txtSulfitos.setVisible(true);

	}

	public void hideIntolerance() {
		txtFrutosSecos.setVisible(false);
		txtGlucosa.setVisible(false);
		txtGluten.setVisible(false);
		txtSulfitos.setVisible(false);

	}

	public void showIntolerance() {
		txtFrutosSecos.setVisible(true);
		txtGlucosa.setVisible(true);
		txtGluten.setVisible(true);
		txtSulfitos.setVisible(true);

	}

	// timeout - set number in frame

	public void setTimeout(String numMsg) {
		tf_timer.setText(numMsg);
	}

	public void setEndTimeout() {
		btnLogOff.setVisible(false);
    	tf_timer.setText(null);
	}
	
	public void showLogOffBtn() {
		btnLogOff.setVisible(true);
	}


	public void createTimeOut(int startTime, Timer timer) {

		timer.scheduleAtFixedRate(new TimerTask() {
			int timeOut = startTime;

			public void run() {

				setTimeout("Time left: " + timeOut);
				timeOut--;

				if (timeOut < 0) {
					timer.cancel();
					setEndTimeout();
					controller.logOffUser(false);
				}
			}
		}, 0, 1000);

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
}
