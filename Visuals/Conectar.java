package Visuals;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import model.Usuario;
import Services.ServicioUsuario;
import Utils.CurrentUser;
import Utils.Idioma;
import Utils.UserInterfaceSuport;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

public class Conectar extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JTextField jTextField = null;

	private JLabel jLabel1 = null;

	private JPasswordField jPasswordField = null;

	private JButton jButton = null;

	private JRadioButton jRadioButton = null;

	private JRadioButton jRadioButton1 = null;

	private ButtonGroup buttonGroup = null;  //  @jve:decl-index=0:visual-constraint="496,146"

	private JButton jButtonSalir = null;

	/**
	 * This is the default constructor
	 */
	public Conectar() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(288, 195);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/password.png")));
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Conectar");
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowActivated(java.awt.event.WindowEvent e) {    
				jTextField.setText("");
				jPasswordField.setText("");
			}
			public void windowOpened(java.awt.event.WindowEvent e) {
				jTextField.setText("");
				jPasswordField.setText("");
				
			}
		});
		getButtonGroup();
		Idioma.getInstance().setIdioma("espanol");
		UserInterfaceSuport.centerComponent(Conectar.this);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(23, 57, 97, 19));
			jLabel1.setText("Contraseña :");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(23, 19, 97, 19));
			jLabel.setText("Nombre usuario :");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJPasswordField(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJRadioButton(), null);
			jContentPane.add(getJRadioButton1(), null);
			jContentPane.add(getJButtonSalir(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(138, 19, 113, 19));
			jTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
						getJButton().doClick();
					}
				}
			});
		}
		return jTextField;
	}

	/**
	 * This method initializes jPasswordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJPasswordField() {
		if (jPasswordField == null) {
			jPasswordField = new JPasswordField();
			jPasswordField.setBounds(new Rectangle(138, 57, 113, 19));
			jPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
						getJButton().doClick();
					}
				}
			});
		}
		return jPasswordField;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(31, 127, 89, 19));
			jButton.setText("Iniciar");
			jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				// Obtener el rol del usuario que se está autenticando
				String rol = ServicioUsuario.getLoginUsuario(jTextField.getText(), jPasswordField.getPassword());
				if(rol.equals("")){
					JOptionPane.showMessageDialog(returnThis(), "Usuario y/o contraseña inválidos");
					//jTextFieldUsuario.setText("");
					jPasswordField.setText("");
				}else{
					//Creando una instancia de tipo usuario para actualizar la clase CurrentUser
					Usuario usuario = new Usuario(jTextField.getText(),rol);
					CurrentUser.getCurrentUser().setSessionUser(usuario);
					Principal frmPpal = new Principal(returnThis());
					returnThis().setVisible(false);
					frmPpal.setVisible(true);
					frmPpal.idioma();
				}
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton() {
		if (jRadioButton == null) {
			jRadioButton = new JRadioButton();
			jRadioButton.setBounds(new Rectangle(31, 96, 89, 19));
			jRadioButton.setSelected(true);
			jRadioButton.setText("Español");
			jRadioButton.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
					if(jRadioButton.isSelected()){
						Idioma.getInstance().setIdioma("espanol");
						idioma();
					}
				}
			});
		}
		return jRadioButton;
	}

	/**
	 * This method initializes jRadioButton1	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton1() {
		if (jRadioButton1 == null) {
			jRadioButton1 = new JRadioButton();
			jRadioButton1.setBounds(new Rectangle(162, 96, 89, 19));
			jRadioButton1.setText("Inglés");
			jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
					if(jRadioButton1.isSelected()){
						Idioma.getInstance().setIdioma("ingles");
						idioma();
					}
				}
			});
		}
		return jRadioButton1;
	}

	/**
	 * This method initializes buttonGroup	
	 * 	
	 * @return javax.swing.ButtonGroup	
	 */
	private ButtonGroup getButtonGroup() {
		if (buttonGroup == null) {
			buttonGroup = new ButtonGroup();
			buttonGroup.add(getJRadioButton());
			buttonGroup.add(getJRadioButton1());
		}
		return buttonGroup;
	}

	private void idioma(){
		if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
			this.setTitle("Login");
			jLabel.setText("Username:");
			jLabel1.setText("Password:");
			jRadioButton.setText("Spanish");
			jRadioButton1.setText("English");
			jButton.setText("Start");
			jButtonSalir.setText("Exit");
		}
		else{
			this.setTitle("Conectar");
			jLabel.setText("Nombre usuario:");
			jLabel1.setText("Contraseña:");
			jRadioButton.setText("Español");
			jRadioButton1.setText("Inglés");
			jButton.setText("Iniciar");
			jButtonSalir.setText("Salir");
		}
	}
	
	/**
	 * This method initializes jButtonSalir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonSalir() {
		if (jButtonSalir == null) {
			jButtonSalir = new JButton();
			jButtonSalir.setBounds(new Rectangle(162, 127, 89, 19));
			jButtonSalir.setText("Salir");
			jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return jButtonSalir;
	}

	public static void main(String[] args) {
		try {
			NimRODLookAndFeel NimRODLF = new NimRODLookAndFeel();
			NimRODLookAndFeel.setCurrentTheme(new NimRODTheme(new Color(190,190,250), new Color(250,250,250)));
			UIManager.setLookAndFeel(NimRODLF);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	    Conectar autenticar = new Conectar();
		autenticar.setVisible(true);
	}
	
	public Conectar returnThis(){
		return this;
	}

}  //  @jve:decl-index=0:visual-constraint="107,85"
