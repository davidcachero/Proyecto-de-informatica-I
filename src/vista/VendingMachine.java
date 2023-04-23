package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
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
import java.util.Timer;
import java.util.TimerTask;
import java.awt.GridLayout;

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
	private JButton btnExtraer_6;
	private JButton btnFantaN;
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
	private JLabel lbluserLoggedName;
	private JLabel tf_timer;

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

		// Componentes de la pantalla
		txfEstado = new JTextField();
		txfEstado.setBackground(new Color(115, 147, 19));
		txfEstado.setForeground(new Color(0, 0, 0));
		txfEstado.setHorizontalAlignment(SwingConstants.CENTER);
		txfEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txfEstado.setText("");
		txfEstado.setEditable(false);

		txfEstado.setBounds(403, 32, 284, 47);
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
		txfProducto.setBounds(403, 84, 284, 47);
		contentPane.add(txfProducto);

		txfSaldo = new JTextField();
		txfSaldo.setText("");
		txfSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		txfSaldo.setForeground(Color.BLACK);
		txfSaldo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txfSaldo.setEditable(false);
		txfSaldo.setColumns(10);
		txfSaldo.setBackground(new Color(115, 147, 19));
		txfSaldo.setBounds(403, 137, 284, 47);
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
		btnDevolverDinero.setBounds(403, 204, 284, 35);
		contentPane.add(btnDevolverDinero);

		btnPagar = new JButton("PAGAR");
		btnPagar.setBackground(new Color(8, 8, 8));
		btnPagar.setForeground(Color.white);
		btnPagar.setBounds(403, 250, 284, 35);
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
				controller.logOffUser(true);
			}
		});
		btnLogOff.setBounds(594, 296, 93, 35);
		contentPane.add(btnLogOff);

		JPanel panel = new JPanel();
		panel.setBounds(24, 26, 329, 425);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));

		btnCocaCola = new JButton();
		btnCocaCola.setIcon(new ImageIcon(controller.getImage("product_logo/CC", "jpg", 130, 110)));
		btnCocaCola.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btnCocaCola);

		btnFantal = new JButton();
		btnFantal.setIcon(new ImageIcon(controller.getImage("product_logo/FL", "PNG", 150, 120)));
		btnFantal.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btnFantal);

		btnNestea = new JButton();
		btnNestea.setIcon(new ImageIcon(controller.getImage("product_logo/NT", "JPG", 120, 120)));
		btnNestea.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btnNestea);

		btnAgua = new JButton();
		btnAgua.setIcon(new ImageIcon(controller.getImage("product_logo/SC", "jpg", 120, 120)));
		btnAgua.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btnAgua);

		btnPepsi = new JButton(); // En datos de prueba no existe
		btnPepsi.setIcon(new ImageIcon(controller.getImage("product_logo/PP", "JPG", 120, 120)));
		btnPepsi.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btnPepsi);

		btnAquarius = new JButton();
		btnAquarius.setIcon(new ImageIcon(controller.getImage("product_logo/AQ", "jpg", 120, 120)));
		btnAquarius.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btnAquarius);

		btnCafe = new JButton();
		btnCafe.setIcon(new ImageIcon(controller.getImage("product_logo/NCAFE", "jpg", 120, 120)));
		btnCafe.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btnCafe);

		btnColacao = new JButton();
		panel.add(btnColacao);
		btnColacao.setIcon(new ImageIcon(controller.getImage("product_logo/CCao", "jpg", 120, 120)));
		btnColacao.setHorizontalAlignment(SwingConstants.CENTER);

		btnNesquik = new JButton(); // En datos de prueba no existe
		btnNesquik.setIcon(new ImageIcon(controller.getImage("product_logo/NK", "jpg", 120, 120)));
		btnNesquik.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btnNesquik);

		btnFantaN = new JButton(); // En datos de prueba excede saldo
		btnFantaN.setIcon(new ImageIcon(controller.getImage("product_logo/FN", "jpg", 120, 120)));
		btnFantaN.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btnFantaN);

		btnExtraer_6 = new JButton("");
		panel.add(btnExtraer_6);

		btnExtraer_11 = new JButton("");
		panel.add(btnExtraer_11);

		lbluserLoggedName = new JLabel();
		lbluserLoggedName.setBounds(413, 296, 171, 37);
		contentPane.add(lbluserLoggedName);
		
		tf_timer = new JLabel();
		tf_timer.setBounds(527, 402, 160, 35);
		contentPane.add(tf_timer);

	}
	
	/**
	 *  Set de funcionalities of the frame
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
				controller.takeProduct("cafe");
			}
		});
		btnAquarius.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.takeProduct("acua");
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
				controller.takeProduct("agua");
			}
		});
		btnNestea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.takeProduct("nestea");
			}
		});
		btnFantal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.takeProduct("fantal");
			}
		});
		btnFantaN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.takeProduct("fantan");
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
		System.out.println(name != null? "[DEV] loggeando a " + name : "Usuario Deslogeado");
		if (name == null)
			lbluserLoggedName.setText(null);

		else
			lbluserLoggedName.setText("User logged : " + name);

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
    	tf_timer.setText(null);
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
}
