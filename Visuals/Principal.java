package Visuals;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Utils.CurrentUser;
import Utils.Idioma;
import Utils.Reportes;



public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JMenuBar mainMenuBar = null;

	private JMenu FileMenu = null;

	private JMenu ReportsjMenu = null;

	private JMenuItem biggerConnsumersjMenuItem = null;

	private JMenuItem jMenuItem = null;

	private JMenuItem jMenuItem1 = null;
	private Conectar owner = null;

	private JMenuItem jMenuItemAutenticar = null;

	private JMenu GestionjMenu1 = null;

	private JMenuItem UsuariosjMenuItem = null;

	private JMenuItem VecindariojMenuItem = null;
	
	private JLabel jLabel;

	private JMenuItem CasasjMenuItem2 = null;

	private JMenuItem ContadorporCasajMenuItem2 = null;
	private JMenuItem jLecturasPorValor = null;

	

	

	/**
	 * This is the default constructor
	 */
	public Principal(Conectar parent) {
		super();
		this.owner = parent;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(408, 362);		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/HH00236_.png")));
		this.setEnabled(true);
		this.setJMenuBar(getMainMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Principal");
		this.addWindowListener(new java.awt.event.WindowAdapter() {   
			public void windowClosed(java.awt.event.WindowEvent e) {    
				getOwner().dispose();
			}
			public void windowOpened(java.awt.event.WindowEvent e) {
				if(CurrentUser.getCurrentUser().getSessionUser().getRol().equals("Administrador")){
					getGestionjMenu1().setVisible(true);
				}
				if(CurrentUser.getCurrentUser().getSessionUser().getRol().equals("Invitado")){
					getGestionjMenu1().setVisible(false);
				}
			}
		});
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2,((screenSize.height - getHeight()) / 2));
	}
	
		/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jLabel = new JLabel();
			jLabel.setIcon(new ImageIcon(getClass().getResource("/Img/gl-20-02.jpg")));
			jLabel.setName("jLabel");
			jLabel.setText("");
			jContentPane.setLayout(new CardLayout());
			jContentPane.add(jLabel, jLabel.getName());
			}
		return jContentPane;
	}

	/**
	 * This method initializes mainMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getMainMenuBar() {
		if (mainMenuBar == null) {
			mainMenuBar = new JMenuBar();
			mainMenuBar.add(getFileMenu());
			mainMenuBar.add(getGestionjMenu1());
			mainMenuBar.add(getReportsjMenu());			
			}
		return mainMenuBar;
	}

	/**
	 * This method initializes FileMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFileMenu() {
		if (FileMenu == null) {
			FileMenu = new JMenu();
			FileMenu.setText("Archivo");
			FileMenu.add(getJMenuItemAutenticar());
			FileMenu.add(getJMenuItem1());
		}
		return FileMenu;
	}

	
	/**
	 * This method initializes ReportsjMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getReportsjMenu() {
		if (ReportsjMenu == null) {
			ReportsjMenu = new JMenu();
			ReportsjMenu.setText(" Reportes");
			ReportsjMenu.add(getJMenuItem());
			ReportsjMenu.add(getContadorporCasajMenuItem2());
			ReportsjMenu.add(getLecturasPorValor());
			ReportsjMenu.add(getBiggerConnsumersjMenuItem());
			}
		return ReportsjMenu;
	}

	/**
	 * This method initializes biggerConnsumersjMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getBiggerConnsumersjMenuItem() {
		if (biggerConnsumersjMenuItem == null) {
			biggerConnsumersjMenuItem = new JMenuItem();
			biggerConnsumersjMenuItem.setText("Mayores  Consumidores");
			biggerConnsumersjMenuItem.setIcon(new ImageIcon(getClass().getResource("/Img/lens_in.png")));
			biggerConnsumersjMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Reportes.getR().MayoresConsumidores();
		     }
			});
		}
		return biggerConnsumersjMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setText("Casas con  el consumo alterado");
			jMenuItem.setIcon(new ImageIcon(getClass().getResource("/Img/lens_in.png")));
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Reportes.getR().ReporteCasasAlteradas();
				}
			});
		}
		return jMenuItem;
	}
	
	/**
	 * This method initializes jMenuItem1	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("Salir");
			jMenuItem1.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_Delete_16x16.png")));
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return jMenuItem1;
	}

	public void idioma(){
		if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
			FileMenu.setText("File");
			ReportsjMenu.setText("Reports");
			jMenuItem.setText("Hauses whit Altered Consuption");
			jMenuItem1.setText("Exit");
			VecindariojMenuItem.setText("Collectors");
			biggerConnsumersjMenuItem.setText("Bigger Consumers");
			CasasjMenuItem2.setText("Homes");
			jMenuItemAutenticar.setText("Change User");
			UsuariosjMenuItem.setText("Users");
			GestionjMenu1.setText("Management");
			jLecturasPorValor.setText("Home's Readings");
			 ContadorporCasajMenuItem2.setText("Readings for Collectors");
			this.setTitle("Main");
			}
		else{
			jLecturasPorValor.setText("Lecturas de un hogar");
			FileMenu.setText("Archivo");
			jMenuItem.setText("Casas con el consumo alterado");
			ReportsjMenu.setText("Reportes");
			CasasjMenuItem2.setText("Casas");
			jMenuItem1.setText("Salir");
			VecindariojMenuItem.setText("Cobradores");
			biggerConnsumersjMenuItem.setText("Mayores Consumidores");
			jMenuItemAutenticar.setText("Cambiar Usuario");
			UsuariosjMenuItem.setText("Usuarios");
			GestionjMenu1.setText("Gestión");
			 ContadorporCasajMenuItem2.setText("Contador y sus Lecturas");
			this.setTitle("Principal");
			}
	}

	public Principal returnThis(){
		return this;
	}

	public Conectar getOwner() {
		return owner;
	}

	/**
	 * This method initializes jMenuItemAutenticar	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemAutenticar() {
		if (jMenuItemAutenticar == null) {
			jMenuItemAutenticar = new JMenuItem();
			jMenuItemAutenticar.setText("Cambiar Usuario");
			jMenuItemAutenticar.setIcon(new ImageIcon(getClass().getResource("/Img/edit_user.png")));
			jMenuItemAutenticar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setVisible(false);
					Conectar r = new Conectar();
					r.setVisible(true);
				}
			});
		}
		return jMenuItemAutenticar;
	}

	/**
	 * This method initializes GestionjMenu1	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getGestionjMenu1() {
		if (GestionjMenu1 == null) {
			GestionjMenu1 = new JMenu();
			GestionjMenu1.setText("Gestion");
			GestionjMenu1.add(getUsuariosjMenuItem());
			GestionjMenu1.add(getCasasjMenuItem2());
			GestionjMenu1.add(getVecindariojMenuItem());
		}
		return GestionjMenu1;
	}

	/**
	 * This method initializes UsuariosjMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getUsuariosjMenuItem() {
		if (UsuariosjMenuItem == null) {
			UsuariosjMenuItem = new JMenuItem();
			UsuariosjMenuItem.setText("Usuarios");
			UsuariosjMenuItem.setIcon(new ImageIcon(getClass().getResource("/Img/add_user.png")));
			UsuariosjMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Usuarios f = new Usuarios();
					f.setVisible(true);
				}
			});
		}
		return UsuariosjMenuItem;
	}

	/**
	 * This method initializes VecindariojMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getVecindariojMenuItem() {
		if (VecindariojMenuItem == null) {
			VecindariojMenuItem = new JMenuItem();
			VecindariojMenuItem.setText("Cobradores");
			VecindariojMenuItem.setIcon(new ImageIcon(getClass().getResource("/Img/AOL Instant Messenger-fall.png")));
			VecindariojMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new CobradorVisual(Principal.this,true).setVisible(true);
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return VecindariojMenuItem;
	}

	/**
	 * This method initializes CasasjMenuItem2	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCasasjMenuItem2() {
		if (CasasjMenuItem2 == null) {
			CasasjMenuItem2 = new JMenuItem();
		    CasasjMenuItem2.setText("Casas");
			CasasjMenuItem2.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_HomePage_32x32.png")));
			CasasjMenuItem2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new CasasVisual(Principal.this).setVisible(true);
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return CasasjMenuItem2;
	}

	/**
	 * This method initializes ContadorporCasajMenuItem2	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getContadorporCasajMenuItem2() {
		if (ContadorporCasajMenuItem2 == null) {
			ContadorporCasajMenuItem2 = new JMenuItem();
			 ContadorporCasajMenuItem2.setText("Contador y sus Lecturas");
				ContadorporCasajMenuItem2.setIcon(new ImageIcon(getClass().getResource("/Img/lens_in.png")));
				ContadorporCasajMenuItem2.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						Reportes.getR().Contador();
						System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					}
				});
		}
		return ContadorporCasajMenuItem2;
	}

	private JMenuItem getLecturasPorValor() {
		if (jLecturasPorValor == null) {
			jLecturasPorValor = new JMenuItem();
			jLecturasPorValor.setText("Lecturas de un hogar");
			jLecturasPorValor.setIcon(new ImageIcon(getClass().getResource("/Img/lens_in.png")));
			jLecturasPorValor.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ReporteLecturaValor a = new ReporteLecturaValor();
					a.setVisible(true);
				}
			});
		}
		return jLecturasPorValor;
	}

	
}  //  @jve:decl-index=0:visual-constraint="10,10"
