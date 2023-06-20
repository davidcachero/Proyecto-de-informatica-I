package vista;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import controller.VisualController;
import models.Catalog;
import models.Intolerance;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

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

	private JButton btnDevolverDinero;
	private JButton btnPagar;
	private JMenuBar menu;
	private JMenu pagos;
	private JMenuItem tarjeta;
	private JMenuItem monedas;
	private JTextField txfProducto;
	private JTextField txfSaldo;
	private JLabel lbluserLoggedName;
	private JLabel tf_timer;
	private JButton btnLogOff;
	private JPanel pProducts;
	private JPanel pIntolerance;

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
		txfEstado.setBackground(SystemColor.window);// 115, 147, 19));
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

		btnLogOff = new JButton("CERRAR SESIÓN");
		btnLogOff.setBackground(new Color(255, 66, 66));
		btnLogOff.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogOff.setVisible(false);
		btnLogOff.setForeground(Color.WHITE);
		btnLogOff.setBounds(398, 367, 289, 35);

		contentPane.add(btnLogOff);

		pProducts = new JPanel();
		pProducts.setBounds(24, 56, 344, 395);
		pProducts.setLayout(new GridLayout(3, 3));
		contentPane.add(pProducts);

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

		pIntolerance = new JPanel();
		pIntolerance.setBounds(104, 25, 262, 20);
		pProducts.setLayout(new GridLayout(1, 3));
		contentPane.add(pIntolerance);

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

	public void setProductList(HashMap<String, Catalog> rawProd) {

		for (Entry<String, Catalog> prod : rawProd.entrySet()) {

			Catalog prodData = prod.getValue();

			JButton newBtn = new JButton();
			newBtn.setName(prodData.getKey());
			newBtn.setBorder(null);
			newBtn.setLayout(new GridLayout(100, 50));
			newBtn.setIcon(new ImageIcon(controller.getImageURL(prodData.getImage(), 130, 110)));
			newBtn.setHorizontalAlignment(SwingConstants.CENTER);
			pProducts.add(newBtn);

			newBtn.addMouseListener(btnProductStyle);

		}

		pProducts.revalidate();
		pProducts.repaint();
		pProducts.setLayout(new GridLayout(3, Math.abs(rawProd.size() / 3)));

		contentPane.revalidate();
		contentPane.repaint();

	}

	public void setSelectedProd(String id) {
		System.out.println("[DEV] SELECCIONANDO PRODUCTO " + id);
		prodSelected = id;

	}

	public void setIntoleranceList(HashMap<String, Intolerance> rawData) {

		for (Entry<String, Intolerance> into : rawData.entrySet()) {

			Intolerance intoData = into.getValue();

			JButton newBtn = new JButton();
			
			newBtn.setFont(new Font("Tahoma", Font.BOLD, 9));
			newBtn.setBackground(SystemColor.textHighlightText);
			newBtn.setHorizontalAlignment(SwingConstants.CENTER);
			newBtn.setText(intoData.getName());
			newBtn.setBorder(null);
			newBtn.setName(Integer.toString(intoData.getId()));
			newBtn.setBounds(28, 25, 78, 20);

			pIntolerance.add(newBtn);

		}

		pIntolerance.revalidate();
		pIntolerance.repaint();
		pIntolerance.setLayout(new GridLayout(1, rawData.size() ));
		
		contentPane.revalidate();
		contentPane.repaint();

	}
	
	public void showIntolerance(String[] ids) {
		 List<String> list = Arrays.asList(ids);
		 
		for (Component btnIntolerance : pIntolerance.getComponents()) {
			btnIntolerance.setVisible(false);
			
			if (btnIntolerance instanceof JButton && list.contains( btnIntolerance.getName())) {
				btnIntolerance.setVisible(true);
			}
		}
	}
	
	public void hideIntolerances() {
		for (Component btnIntolerance : pIntolerance.getComponents()) {
			if (btnIntolerance instanceof JButton) {
				btnIntolerance.setVisible(false);
			}
		}

	}

	public void setUserLoggedName(String name) {
		System.out.println(name != null ? "[DEV] loggeando a " + name : "Usuario Deslogeado");
		if (name == null)
			lbluserLoggedName.setText(null);

		else
			lbluserLoggedName.setText("BIENVENIDO: " + name);

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

		controller.updateTimeOut(startTime);

		timer.scheduleAtFixedRate(new TimerTask() {
			int timeOut = startTime;

			public void run() {


				if (timeOut < 0) {
					timer.cancel();
					setEndTimeout();
					controller.logOffUser(false, timeOut);
				} else {
					controller.updateTimeOut(timeOut);
					setTimeout("Time left: " + timeOut);
					timeOut--;
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
