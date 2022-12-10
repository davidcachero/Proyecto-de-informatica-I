package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VendingMachine extends JFrame implements ActionListener, ItemListener, ChangeListener {

	private JPanel contentPane;
	private JTextField textFieldPrecio;
	private JButton btnCocaCola;
	private final ButtonGroup buttonGroup = new ButtonGroup();
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
	private JButton btnReseteo;
	private JTextField txtGlucosa;
	private JTextField txtGluten;
	private JTextField txtSulfitos;
	private JTextField txtFrutosSecos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VendingMachine frame = new VendingMachine();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VendingMachine() {
		setFont(new Font("Dialog", Font.PLAIN, 16));
		setTitle("COFFEEBREAK");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 500, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldPrecio = new JTextField();/////////
		textFieldPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPrecio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textFieldPrecio.setText("");

		textFieldPrecio.setBounds(317, 66, 146, 81);
		contentPane.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);

		btnCocaCola = new JButton("CocaCola");
		btnCocaCola.addActionListener(this);
		btnCocaCola.setBounds(24, 66, 78, 35);
		contentPane.add(btnCocaCola);

		btnAgua = new JButton("Agua");
		btnAgua.setBounds(24, 112, 78, 35);
		contentPane.add(btnAgua);

		btnFanta = new JButton("Fanta");
		btnFanta.setBounds(112, 66, 78, 35);
		contentPane.add(btnFanta);

		btnCafe = new JButton("Cafe");
		btnCafe.setBounds(24, 158, 78, 35);
		contentPane.add(btnCafe);

		btnPepsi = new JButton("Pepsi");
		btnPepsi.setBounds(112, 112, 78, 35);
		contentPane.add(btnPepsi);

		btnNesquik = new JButton("Nesquik");
		btnNesquik.setBounds(200, 158, 78, 35);
		contentPane.add(btnNesquik);

		btnExtraer_6 = new JButton("");
		btnExtraer_6.setBounds(112, 204, 78, 35);
		contentPane.add(btnExtraer_6);

		btnExtraer_7 = new JButton("");
		btnExtraer_7.setBounds(24, 204, 78, 35);
		contentPane.add(btnExtraer_7);

		btnColacao = new JButton("Colacao");
		btnColacao.setBounds(112, 158, 78, 35);
		contentPane.add(btnColacao);

		btnNestea = new JButton("Nestea");
		btnNestea.setBounds(200, 66, 78, 35);
		contentPane.add(btnNestea);

		btnAquarius = new JButton("Aquarius");
		btnAquarius.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAquarius.setBounds(200, 112, 78, 35);
		contentPane.add(btnAquarius);

		btnExtraer_11 = new JButton("");
		btnExtraer_11.setBounds(200, 204, 78, 35);
		contentPane.add(btnExtraer_11);

		btnDevolverDinero = new JButton("DEVOLVER DINERO");
		btnDevolverDinero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDevolverDinero.setBounds(317, 158, 146, 35);
		contentPane.add(btnDevolverDinero);

		btnReseteo = new JButton("RESETEAR");
		btnReseteo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReseteo.setBounds(317, 204, 146, 35);
		contentPane.add(btnReseteo);

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
