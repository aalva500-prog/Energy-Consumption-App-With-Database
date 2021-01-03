package Visuals;



import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Rol;
import model.Usuario;
import Services.ServicioRol;
import Services.ServicioUsuario;
import Utils.Encriptar;
import Utils.Idioma;
import Utils.UserInterfaceSuport;
import Utils.Validate;



public class Usuarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPaneTableUser = null;
	private JTable jTableUser = null;
	private JTextField jTextFieldUser = null;
	private JTextField jTextFieldNomb = null;
	private JPasswordField jPasswordFieldPass = null;
	private JPasswordField jPasswordFieldConfirm = null;
	private JLabel jLabeluse = null;
	private JLabel jLabelNomb = null;
	private JLabel jLabelRol = null;
	private JLabel jLabelPas = null;
	private JLabel jLabelConfirmar = null;
	private JButton jButtonAgreg = null;
	private JButton jButtonCerrar = null;
	private JComboBox jComboBoxRol = null;	
	private JButton jButtonModificar = null;
	protected DefaultComboBoxModel defaultComboBoxModel = null;
	private JButton jButtonEliminar = null;
	protected String nuevo = "";  //  @jve:decl-index=0:
	private JButton jButtonNuevo = null;
	/**
	 * This is the default constructor
	 */
	public Usuarios() {
		super();
		initialize();
		idioma();
	}

	/**
	 * This method initializes this
	 * @return 
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(544, 321);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/ico_alpha_AutoLogon_32x32.png")));
		this.setContentPane(getJContentPane());
		this.setTitle("Gestion de Usuarios");
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowOpened(java.awt.event.WindowEvent e) {
				jButtonEliminar.setEnabled(false);
				jButtonAgreg.setEnabled(true);
				jButtonModificar.setEnabled(false);
				jButtonCerrar.setEnabled(true);
				jButtonNuevo.setEnabled(false);
				DefaultTableModel defaultTableModel = new DefaultTableModel();
				LinkedList<Usuario> list = new LinkedList<Usuario>();
				try {
					list = ServicioUsuario.getUsuarios();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ArrayList<Object> columnDataUser = new ArrayList<Object>();
				ArrayList<Object> columnDataNombre = new ArrayList<Object>();
				ArrayList<Object> columnDataRol = new ArrayList<Object>();
				for (int i = 0; i < list.size(); i++) {
					columnDataUser.add(list.get(i).getUsername());
					columnDataNombre.add(list.get(i).getNombre());
					columnDataRol.add(list.get(i).getRol());
					}
				defaultTableModel.setRowCount(list.size());
				if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
				defaultTableModel.addColumn("User",columnDataUser.toArray());
				defaultTableModel.addColumn("Name",columnDataNombre.toArray());
				defaultTableModel.addColumn("Rol",columnDataRol.toArray());
				}
				else
				{
					defaultTableModel.addColumn("Usuario",columnDataUser.toArray());
					defaultTableModel.addColumn("Nombre",columnDataNombre.toArray());
					defaultTableModel.addColumn("Rol",columnDataRol.toArray());
				}
					
				getJTableUser().setModel(defaultTableModel);
				
				jTableUser.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						int pos = jTableUser.getSelectedRow();
						try {
							LinkedList<Usuario> users = ServicioUsuario.getUsuarios();
							Usuario u = users.get(pos);
							jTextFieldUser.setText(u.getUsername());
							jTextFieldNomb.setText(u.getNombre());
							jPasswordFieldPass.setText(u.getPassword());
							jPasswordFieldConfirm.setText(u.getPassword());
							jTextFieldUser.setEditable(false);
						    //Combobox Rol
							LinkedList<Rol> list2 = new LinkedList<Rol>();
							try {
								list2 = ServicioRol.getRoles();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							DefaultComboBoxModel boxModel1 = new DefaultComboBoxModel();
							if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
								boxModel1.addElement(new String("<Select>"));
								}else{
									boxModel1.addElement(new String("<Seleccione>"));
								}
							for (int i = 0; i < list2.size(); i++) {
								boxModel1.addElement(list2.get(i));
							}
							
							getJComboBoxRol().setModel(boxModel1);
													
													
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						jButtonEliminar.setEnabled(true);
						jButtonModificar.setEnabled(true);
						jButtonAgreg.setEnabled(false);
						jButtonNuevo.setEnabled(true);				
					}
				});	
                 //Combobox Rol
				LinkedList<Rol> list2 = new LinkedList<Rol>();
				try {
					list2 = ServicioRol.getRoles();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DefaultComboBoxModel boxModel1 = new DefaultComboBoxModel();
				if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
				boxModel1.addElement(new String("<Select>"));
				}else{
					boxModel1.addElement(new String("<Seleccione>"));
				}
				for (int i = 0; i < list2.size(); i++) {
					boxModel1.addElement(list2.get(i));
				}
				
				getJComboBoxRol().setModel(boxModel1);
				
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
			jLabelConfirmar = new JLabel();
			jLabelConfirmar.setBounds(new Rectangle(139, 73, 114, 19));
			jLabelConfirmar.setText("Confirmar:");
			jLabelPas = new JLabel();
			jLabelPas.setBounds(new Rectangle(13, 73, 114, 19));
			jLabelPas.setText("Contraseña:");
			jLabelRol = new JLabel();
			jLabelRol.setBounds(new Rectangle(269, 11, 114, 19));
			jLabelRol.setText("Rol:");
			jLabelNomb = new JLabel();
			jLabelNomb.setBounds(new Rectangle(139, 11, 114, 19));
			jLabelNomb.setText("Nombre:");
			jLabeluse = new JLabel();
			jLabeluse.setBounds(new Rectangle(13, 11, 114, 19));
			jLabeluse.setText("Usuario:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJScrollPaneTableUser(), null);
			jContentPane.add(getJTextFieldUser(), null);
			jContentPane.add(getJTextFieldNomb(), null);
			jContentPane.add(getJPasswordFieldPass(), null);
			jContentPane.add(getJPasswordFieldConfirm(), null);
			jContentPane.add(jLabeluse, null);
			jContentPane.add(jLabelNomb, null);
			jContentPane.add(jLabelRol, null);
			jContentPane.add(jLabelPas, null);
			jContentPane.add(jLabelConfirmar, null);
			jContentPane.add(getJButtonAgreg(), null);
			jContentPane.add(getJComboBoxRol(), null);
			jContentPane.add(getJButtonModificar(), null);
			jContentPane.add(getJButtonEliminar(), null);
			jContentPane.add(getJButtonNuevo(), null);
			jContentPane.add(getJButtonCerrar(), null);
			jContentPane.add(jButtonAgreg, null);
			jContentPane.add(jButtonAgreg, null);
			}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPaneTableUser	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneTableUser() {
		if (jScrollPaneTableUser == null) {
			jScrollPaneTableUser = new JScrollPane();
			jScrollPaneTableUser.setBounds(new Rectangle(13, 139, 506, 103));
			jScrollPaneTableUser.setViewportView(getJTableUser());
		}
		return jScrollPaneTableUser;
	}

	/**
	 * This method initializes jTableUser	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTableUser() {
		if (jTableUser == null) {
			jTableUser = new JTable();
		}
		return jTableUser;
	}

	/**
	 * This method initializes jTextFieldUser	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldUser() {
		if (jTextFieldUser == null) {
			jTextFieldUser = new JTextField();
			jTextFieldUser.setBounds(new Rectangle(14, 37, 114, 19));
			Validate.validateLetter(jTextFieldUser);
		}
		return jTextFieldUser;
	}

	/**
	 * This method initializes jTextFieldNomb	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldNomb() {
		if (jTextFieldNomb == null) {
			jTextFieldNomb = new JTextField();
			jTextFieldNomb.setBounds(new Rectangle(140, 37, 114, 19));
			Validate.validateLetter(jTextFieldNomb);
		}
		return jTextFieldNomb;
	}

	/**
	 * This method initializes jPasswordFieldPass	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJPasswordFieldPass() {
		if (jPasswordFieldPass == null) {
			jPasswordFieldPass = new JPasswordField();
			jPasswordFieldPass.setBounds(new Rectangle(13, 97, 114, 19));
		}
		return jPasswordFieldPass;
	}

	/**
	 * This method initializes jPasswordFieldConfirm	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJPasswordFieldConfirm() {
		if (jPasswordFieldConfirm == null) {
			jPasswordFieldConfirm = new JPasswordField();
			jPasswordFieldConfirm.setBounds(new Rectangle(139, 97, 114, 19));
		}
		return jPasswordFieldConfirm;
	}

	/**
	 * This method initializes jButtonAgreg	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonAgreg() {
		if (jButtonAgreg == null) {
			jButtonAgreg = new JButton();
			jButtonAgreg.setBounds(new Rectangle(13, 253, 91, 21));
			jButtonAgreg.setText("Insertar");
			jButtonAgreg.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(jTextFieldNomb.getText().length() > 0 && jTextFieldUser.getText().length() > 0 && jPasswordFieldConfirm.getPassword().length > 0 
							&& jPasswordFieldPass.getPassword().length > 0 && !(jComboBoxRol.getSelectedIndex()==0)){
						try {
						if( Encriptar.getMd5(new String (getJPasswordFieldPass().getPassword())).equals(Encriptar.getMd5(new String(getJPasswordFieldConfirm().getPassword()))) ){
							ServicioUsuario.insertarUsuario(getJTextFieldUser().getText(), getJPasswordFieldPass().getPassword(), getJTextFieldNomb().getText(), ((Rol)getJComboBoxRol().getSelectedItem()).getRol());

							DefaultTableModel defaultTableModel = new DefaultTableModel();
							LinkedList<Usuario> list = new LinkedList<Usuario>();
							try {
								list = ServicioUsuario.getUsuarios();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
						        e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ArrayList<Object> columnDataUser = new ArrayList<Object>();
							ArrayList<Object> columnDataNombre = new ArrayList<Object>();
							ArrayList<Object> columnDataRol = new ArrayList<Object>();
							for (int i = 0; i < list.size(); i++) {
								columnDataUser.add(list.get(i).getUsername());
								columnDataNombre.add(list.get(i).getNombre());
								columnDataRol.add(list.get(i).getRol());
							}
							defaultTableModel.setRowCount(list.size());
							if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
								defaultTableModel.addColumn("User",columnDataUser.toArray());
								defaultTableModel.addColumn("Name",columnDataNombre.toArray());
								defaultTableModel.addColumn("Rol",columnDataRol.toArray());
								}
								else
								{
									defaultTableModel.addColumn("Usuario",columnDataUser.toArray());
									defaultTableModel.addColumn("Nombre",columnDataNombre.toArray());
									defaultTableModel.addColumn("Rol",columnDataRol.toArray());
								}
									
							getJTableUser().setModel(defaultTableModel);
							if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
							JOptionPane.showMessageDialog(Usuarios.this, "User Inserted", "Information", JOptionPane.INFORMATION_MESSAGE);
							}else
							{
								JOptionPane.showMessageDialog(Usuarios.this, "Usuario insertado", "Informacion", JOptionPane.INFORMATION_MESSAGE);	
							}
							UserInterfaceSuport.clearComponents(jContentPane);
							}
						else
							if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
							JOptionPane.showMessageDialog(returnThis(), "Passwords must be iquals");
							}
							else{
								JOptionPane.showMessageDialog(returnThis(), "Las contraseñas deben ser iguales");
							}
					
					} catch (SQLException e1) {
						if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
						JOptionPane.showMessageDialog(Usuarios.this, "User Already Exist", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else{
							JOptionPane.showMessageDialog(Usuarios.this, "El Usuario ya Existe", "Error", JOptionPane.ERROR_MESSAGE);	
						}
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					jButtonNuevo.setEnabled(false);
					jTextFieldUser.setText("");
					jTextFieldNomb.setText("");
					jPasswordFieldPass.setText("");
					jPasswordFieldConfirm.setText("");
					jButtonAgreg.setEnabled(true);
					jButtonModificar.setEnabled(false);
					jButtonEliminar.setEnabled(false);
					}
					else
						if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
						JOptionPane.showMessageDialog(Usuarios.this, "Empty Fields", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else{
							JOptionPane.showMessageDialog(Usuarios.this, "Campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
						}
					
					}
				
			});
		}
		return jButtonAgreg;
	}
	/**
	 * This method initializes jButtonCerrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonCerrar() {
		if (jButtonCerrar == null) {
			jButtonCerrar = new JButton();
			jButtonCerrar.setText("Cerrar");
			jButtonCerrar.setBounds(new Rectangle(430, 253, 91, 21));
			jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return jButtonCerrar;
	}
	 private Usuarios returnThis(){
		 return this;
	 }

	/**
	 * This method initializes jComboBoxRol	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxRol() {
		if (jComboBoxRol == null) {
			jComboBoxRol = new JComboBox();
			jComboBoxRol.setBounds(new Rectangle(270, 37, 114, 19));
		}
		return jComboBoxRol;
	}

	


	/**
	 * This method initializes jButtonModificar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	/**
	 * This method initializes jButtonModificar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonModificar() {
		if (jButtonModificar == null) {
			jButtonModificar = new JButton();
			jButtonModificar.setBounds(new Rectangle(113, 253, 91, 21));
			jButtonModificar.setText("Modificar");
			jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
					int result = JOptionPane.showConfirmDialog(Usuarios.this, "Are you sure you want modify this User?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					int pos = jTableUser.getSelectedRow();
					try {
						LinkedList<Usuario> users = ServicioUsuario.getUsuarios();
						Usuario u = users.get(pos);	
						if(!(jComboBoxRol.getSelectedItem()).toString().equalsIgnoreCase("<Select>")){
						if( Encriptar.getMd5(new String (getJPasswordFieldPass().getPassword())).equals(Encriptar.getMd5(new String(getJPasswordFieldConfirm().getPassword()))) ){
						ServicioUsuario.ModificarUsuario(u.getUsername(), ((Rol)getJComboBoxRol().getSelectedItem()).getRol(),  getJTextFieldNomb().getText(), getJPasswordFieldPass().getPassword());
						JOptionPane.showMessageDialog(Usuarios.this, "User Modified", "Information", JOptionPane.INFORMATION_MESSAGE);
						jButtonAgreg.setEnabled(true);
						DefaultTableModel defaultTableModel = new DefaultTableModel();
						LinkedList<Usuario> list = new LinkedList<Usuario>();
						try {
							list = ServicioUsuario.getUsuarios();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ArrayList<Object> columnDataUser = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre = new ArrayList<Object>();
						ArrayList<Object> columnDataRol = new ArrayList<Object>();
						for (int i = 0; i < list.size(); i++) {
							columnDataUser.add(list.get(i).getUsername());
							columnDataNombre.add(list.get(i).getNombre());
							columnDataRol.add(list.get(i).getRol());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Usuario",columnDataUser.toArray());
						defaultTableModel.addColumn("Nombre",columnDataNombre.toArray());
						defaultTableModel.addColumn("Rol",columnDataRol.toArray());
						getJTableUser().setModel(defaultTableModel);
						
						UserInterfaceSuport.clearComponents(getJContentPane());
						}else
						{
							JOptionPane.showMessageDialog(Usuarios.this, "Passwords must be iquals", "Information", JOptionPane.INFORMATION_MESSAGE);	
						}
						}else
						{
							JOptionPane.showMessageDialog(Usuarios.this, "You must select a Rol", "Information", JOptionPane.INFORMATION_MESSAGE);	
						}
						} catch (SQLException e1) {
						JOptionPane.showMessageDialog(Usuarios.this, "User Already Exist", "Error", JOptionPane.ERROR_MESSAGE);
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					jButtonAgreg.setEnabled(true);
					jButtonEliminar.setEnabled(false);
					jButtonModificar.setEnabled(false);
					jButtonNuevo.setEnabled(false);
					jTextFieldUser.setEditable(true);
					}
					
					}
				else{
					int result = JOptionPane.showConfirmDialog(Usuarios.this, "¿Está seguro que desea modificar el usuario?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					int pos = jTableUser.getSelectedRow();
					try {
						LinkedList<Usuario> users = ServicioUsuario.getUsuarios();
						Usuario u = users.get(pos);	
						if(!(jComboBoxRol.getSelectedItem()).toString().equalsIgnoreCase("<Seleccione>")){
						if( Encriptar.getMd5(new String (getJPasswordFieldPass().getPassword())).equals(Encriptar.getMd5(new String(getJPasswordFieldConfirm().getPassword()))) ){
						ServicioUsuario.ModificarUsuario(u.getUsername(), ((Rol)getJComboBoxRol().getSelectedItem()).getRol(),  getJTextFieldNomb().getText(), getJPasswordFieldPass().getPassword());
						JOptionPane.showMessageDialog(Usuarios.this, "Usuario Modificado", "Información", JOptionPane.INFORMATION_MESSAGE);
						jButtonAgreg.setEnabled(true);
						DefaultTableModel defaultTableModel = new DefaultTableModel();
						LinkedList<Usuario> list = new LinkedList<Usuario>();
						try {
							list = ServicioUsuario.getUsuarios();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ArrayList<Object> columnDataUser = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre = new ArrayList<Object>();
						ArrayList<Object> columnDataRol = new ArrayList<Object>();
						for (int i = 0; i < list.size(); i++) {
							columnDataUser.add(list.get(i).getUsername());
							columnDataNombre.add(list.get(i).getNombre());
							columnDataRol.add(list.get(i).getRol());
							}
						defaultTableModel.setRowCount(list.size());
							defaultTableModel.addColumn("User",columnDataUser.toArray());
							defaultTableModel.addColumn("Name",columnDataNombre.toArray());
							defaultTableModel.addColumn("Rol",columnDataRol.toArray());
						getJTableUser().setModel(defaultTableModel);
						getJTableUser().setModel(defaultTableModel);
						
						UserInterfaceSuport.clearComponents(getJContentPane());
						}else
						{
							JOptionPane.showMessageDialog(Usuarios.this, "Las contraseñas deben ser iguales", "Informacion", JOptionPane.INFORMATION_MESSAGE);	
						}
						}else
						{
							JOptionPane.showMessageDialog(Usuarios.this, "Debe seleccionar un Rol", "Informacion", JOptionPane.INFORMATION_MESSAGE);	
						}
						} catch (SQLException e1) {
						JOptionPane.showMessageDialog(Usuarios.this, "No se puede insertar dos Usuarios de igual Nombre de Usuario", "Error", JOptionPane.ERROR_MESSAGE);
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					jButtonAgreg.setEnabled(true);
					jButtonEliminar.setEnabled(false);
					jButtonModificar.setEnabled(false);
					jButtonNuevo.setEnabled(false);
					jTextFieldUser.setEditable(true);
					}
				}
				}});
			}
		return jButtonModificar;
	}


	

	

	/**
	 * This method initializes jButtonEliminar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonEliminar() {
		if (jButtonEliminar == null) {
			jButtonEliminar = new JButton();
			jButtonEliminar.setBounds(new Rectangle(216, 253, 91, 21));
			jButtonEliminar.setText("Eliminar");
			jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
					int result = JOptionPane.showConfirmDialog(Usuarios.this, "Are you sure you want delete this User?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					int pos = jTableUser.getSelectedRow();
					try {
						LinkedList<Usuario> users = ServicioUsuario.getUsuarios();
						Usuario u = users.get(pos);
						ServicioUsuario.EliminarUsuario(u.getUsername());
						JOptionPane.showMessageDialog(Usuarios.this, "User Deleted", "Information", JOptionPane.INFORMATION_MESSAGE);
						jButtonAgreg.setEnabled(true);
						DefaultTableModel defaultTableModel = new DefaultTableModel();
						LinkedList<Usuario> list = new LinkedList<Usuario>();
						try {
							list = ServicioUsuario.getUsuarios();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ArrayList<Object> columnDataUser = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre = new ArrayList<Object>();
						ArrayList<Object> columnDataRol = new ArrayList<Object>();
						for (int i = 0; i < list.size(); i++) {
							columnDataUser.add(list.get(i).getUsername());
							columnDataNombre.add(list.get(i).getNombre());
							columnDataRol.add(list.get(i).getRol());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("User",columnDataUser.toArray());
						defaultTableModel.addColumn("Name",columnDataNombre.toArray());
						defaultTableModel.addColumn("Rol",columnDataRol.toArray());
						getJTableUser().setModel(defaultTableModel);
						UserInterfaceSuport.clearComponents(getJContentPane());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					jButtonAgreg.setEnabled(true);
					jButtonEliminar.setEnabled(false);
					jButtonModificar.setEnabled(false);
					jTextFieldNomb.setEditable(true);
					jButtonNuevo.setEnabled(false);
					jTextFieldUser.setEditable(true);
				}
					}else{
						int result = JOptionPane.showConfirmDialog(Usuarios.this, "¿Está seguro que desea eliminar el usuario?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						if(result == JOptionPane.YES_OPTION){
						int pos = jTableUser.getSelectedRow();
						try {
							LinkedList<Usuario> users = ServicioUsuario.getUsuarios();
							Usuario u = users.get(pos);
							ServicioUsuario.EliminarUsuario(u.getUsername());
							JOptionPane.showMessageDialog(Usuarios.this, "Usuario Eliminado", "Información", JOptionPane.INFORMATION_MESSAGE);
							jButtonAgreg.setEnabled(true);
							DefaultTableModel defaultTableModel = new DefaultTableModel();
							LinkedList<Usuario> list = new LinkedList<Usuario>();
							try {
								list = ServicioUsuario.getUsuarios();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ArrayList<Object> columnDataUser = new ArrayList<Object>();
							ArrayList<Object> columnDataNombre = new ArrayList<Object>();
							ArrayList<Object> columnDataRol = new ArrayList<Object>();
							for (int i = 0; i < list.size(); i++) {
								columnDataUser.add(list.get(i).getUsername());
								columnDataNombre.add(list.get(i).getNombre());
								columnDataRol.add(list.get(i).getRol());
								}
							defaultTableModel.setRowCount(list.size());
							if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
								defaultTableModel.addColumn("User",columnDataUser.toArray());
								defaultTableModel.addColumn("Name",columnDataNombre.toArray());
								defaultTableModel.addColumn("Rol",columnDataRol.toArray());
								}
								else
								{
									defaultTableModel.addColumn("Usuario",columnDataUser.toArray());
									defaultTableModel.addColumn("Nombre",columnDataNombre.toArray());
									defaultTableModel.addColumn("Rol",columnDataRol.toArray());
								}
									
							getJTableUser().setModel(defaultTableModel);
							UserInterfaceSuport.clearComponents(getJContentPane());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						jButtonAgreg.setEnabled(true);
						jButtonEliminar.setEnabled(false);
						jButtonModificar.setEnabled(false);
						jTextFieldNomb.setEditable(true);
						jButtonNuevo.setEnabled(false);
						jTextFieldUser.setEditable(true);
						}
					}
				}});
		}
		return jButtonEliminar;
	}

	/**
	 * This method initializes jButtonNuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonNuevo() {
		if (jButtonNuevo == null) {
			jButtonNuevo = new JButton();
			jButtonNuevo.setBounds(new Rectangle(322, 253, 91, 21));
			jButtonNuevo.setText("Nuevo");
			jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jTextFieldUser.setText("");
					jTextFieldNomb.setText("");
					jPasswordFieldPass.setText("");
					jPasswordFieldConfirm.setText("");
					jButtonAgreg.setEnabled(true);
					jButtonModificar.setEnabled(false);
					jButtonEliminar.setEnabled(false);
					jButtonNuevo.setEnabled(false);
					 //Combobox Rol
					LinkedList<Rol> list2 = new LinkedList<Rol>();
					try {
						list2 = ServicioRol.getRoles();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					DefaultComboBoxModel boxModel1 = new DefaultComboBoxModel();
					boxModel1.addElement(new String("<Seleccione>"));
					for (int i = 0; i < list2.size(); i++) {
						boxModel1.addElement(list2.get(i));
					}
					
					getJComboBoxRol().setModel(boxModel1);					
                   	jTextFieldNomb.setEditable(true);
					jComboBoxRol.setEnabled(true);
					jPasswordFieldConfirm.setEditable(true);
					jPasswordFieldPass.setEditable(true);
					jTextFieldUser.setEditable(true);
					}
			});
		}
		return jButtonNuevo;
	}

	public void idioma(){
		if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
			jLabelNomb.setText("Name:");
			jLabeluse.setText("User:");
			jLabelPas.setText("Password:");
			jLabelConfirmar.setText("Verify password:");
			jButtonModificar.setText("Modify");
			jButtonEliminar.setText("Delete");
			jButtonAgreg.setText("Insert");
			jButtonCerrar.setText("Cancel");
			jButtonNuevo.setText("New");
			this.setTitle("User's management");
			}
		else{
			jLabelNomb.setText("Nombre :");
			jLabeluse.setText("Usuario:");
			jLabelPas.setText("Contraseña :");
			jLabelConfirmar.setText("Verificar contraseña:");
			jButtonModificar.setText("Modificar");
			jButtonEliminar.setText("Eliminar");
			jButtonAgreg.setText("Insertar");
			jButtonCerrar.setText("Cerrar");
			jButtonNuevo.setText("Nuevo");
			this.setTitle("Gestion de Usuarios");
			}
	}

	
}  //  @jve:decl-index=0:visual-constraint="228,33"
 //  @jve:decl-index=0:visual-constraint="10,10"

