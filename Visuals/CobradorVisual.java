package Visuals;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.Cobrador;
import Services.ServicioCobrador;
import Utils.Idioma;
import Utils.UserInterfaceSuport;
import Utils.Validate;

public class CobradorVisual extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel CobradorjPanel = null;

	private JLabel CarnetjLabel = null;

	private JTextField CarnetjTextField = null;

	private JLabel NombrejLabel = null;
	
	private JTextField nombrejTextField = null;
	
	private JLabel municipioLabel = null;
	
	private JLabel apellidosjLabel = null;

	private JPanel tablejPanel = null;

	private JScrollPane CobradoresjScrollPane = null;

	private JTable CobradroresjTable = null;

	private JButton ModifyjButton = null;

	private JButton DeletejButton = null;

    private JButton InsertjButton = null;
	
 	private JButton closejButton = null;

	private JButton NuevojButton = null;

	private JTextField MunicipiojTextField = null;

	private JTextField ApellidosjTextField = null;

	private JLabel AnosjLabel = null;

	private JTextField AnosjTextField = null;
    private Cobrador selected = null;

	private JButton CasasjButton = null;
	/**
	 * @param owner
	 * @throws SQLException 
	 */
	public CobradorVisual(Frame owner, boolean modal ) {
		super(owner,modal);
		initialize();
		idioma();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 * @throws SQLException 
	 */
	private void initialize() {
		this.setSize(818, 403);
		this.setTitle("Cobradores");
		this.setContentPane(getJContentPane());
		DeletejButton.setEnabled(false);
		ModifyjButton.setEnabled(false);
		InsertjButton.setEnabled(true);
		NuevojButton.setEnabled(false);
		CasasjButton.setEnabled(false);
		UserInterfaceSuport.centerComponent(this);
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		LinkedList<Cobrador> list = new LinkedList<Cobrador>();
		try {
			list = ServicioCobrador.getCobradores();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
	        e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<Object> columnDataCarnet = new ArrayList<Object>();
		ArrayList<Object> columnDataNombre = new ArrayList<Object>();
		ArrayList<Object> columnDataApellidos = new ArrayList<Object>();
		ArrayList<Object> columnDataMunicipio = new ArrayList<Object>();
		ArrayList<Object> columnDataAnos = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			columnDataCarnet.add(list.get(i).getCarnet());
			columnDataNombre.add(list.get(i).getNombre());
			columnDataApellidos.add(list.get(i).getApellidos());
			columnDataMunicipio.add(list.get(i).getMunicipio());
			columnDataAnos.add(list.get(i).getAnos());
		}
		defaultTableModel.setRowCount(list.size());
		if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
		defaultTableModel.addColumn("Identity Number",columnDataCarnet.toArray());
		defaultTableModel.addColumn("Name",columnDataNombre.toArray());
		defaultTableModel.addColumn("Last Name",columnDataApellidos.toArray());
		defaultTableModel.addColumn("Municipality",columnDataMunicipio.toArray());
		defaultTableModel.addColumn("Experience Years",columnDataAnos.toArray());
		}else{
			defaultTableModel.addColumn("Carnet de Identidad",columnDataCarnet.toArray());
			defaultTableModel.addColumn("Nombre",columnDataNombre.toArray());
			defaultTableModel.addColumn("Apellidos",columnDataApellidos.toArray());
			defaultTableModel.addColumn("Municipio",columnDataMunicipio.toArray());
			defaultTableModel.addColumn("Años de Experiencia",columnDataAnos.toArray());
		}
		getCobradoresjTable().setModel(defaultTableModel);
		
		CobradroresjTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int pos = CobradroresjTable.getSelectedRow();
				try {
					LinkedList<Cobrador> cobradores = ServicioCobrador.getCobradores();
				   Cobrador c = cobradores.get(pos);
				   selected = c;
				    nombrejTextField.setText(c.getNombre());
					CarnetjTextField.setText(c.getCarnet());
					MunicipiojTextField.setText(c.getMunicipio());
				    AnosjTextField.setText(String.valueOf(c.getAnos()));
				    ApellidosjTextField.setText(c.getApellidos());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				CarnetjTextField.setEditable(false);
				DeletejButton.setEnabled(true);
				ModifyjButton.setEnabled(true);
				InsertjButton.setEnabled(false);
				NuevojButton.setEnabled(true);
				CasasjButton.setEnabled(true);
			}
		});
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 * @throws SQLException 
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getCobradorjPanel(), null);
			jContentPane.add(getTablejPanel2(), null);
			jContentPane.add(getModifyjButton(), null);
			jContentPane.add(getDeletejButton(), null);
			jContentPane.add(getInsertjButton(), null);
			jContentPane.add(getClosejButton(), null);
			jContentPane.add(getNuevojButton(), null);
			jContentPane.add(getCasasjButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes DatesOfHousejPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getCobradorjPanel() {
		if (CobradorjPanel == null) {			
			AnosjLabel = new JLabel();
			AnosjLabel.setBounds(new Rectangle(618, 31, 120, 25));
			AnosjLabel.setText("Años de Experiencia:");
			apellidosjLabel = new JLabel();
			apellidosjLabel.setBounds(new Rectangle(306, 31, 120, 25));
			apellidosjLabel.setText("Apellidos:");
			NombrejLabel = new JLabel();
			NombrejLabel.setBounds(new Rectangle(161, 31, 120, 25));
			NombrejLabel.setText("Nombre:");
			CarnetjLabel = new JLabel();
			CarnetjLabel.setBounds(new Rectangle(19, 31, 120, 25));
			CarnetjLabel.setText("Carnet de Identidad:");
			municipioLabel = new JLabel();
			municipioLabel.setBounds(new Rectangle(458, 31, 120, 25));
			municipioLabel.setText("Municipio:");
			CobradorjPanel = new JPanel();
			CobradorjPanel.setLayout(null);
			CobradorjPanel.setBounds(new Rectangle(10, 4, 786, 134));
			CobradorjPanel.setBorder(BorderFactory.createTitledBorder(null, "Datos del Cobrador", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			CobradorjPanel.add(municipioLabel, null);
			CobradorjPanel.add(getNombrejTextField(), null);
			CobradorjPanel.add(CarnetjLabel, null);
			CobradorjPanel.add(apellidosjLabel, null);
			CobradorjPanel.add(getCarnetjTextField(), null);
			CobradorjPanel.add(NombrejLabel, null);
			CobradorjPanel.add(getMunicipiojTextField(), null);
			CobradorjPanel.add(getApellidosjTextField(), null);
			CobradorjPanel.add(AnosjLabel, null);
			CobradorjPanel.add(getAnosjTextField(), null);
			CobradorjPanel.add(getNombrejTextField(), null);
		}
		return CobradorjPanel;
	}

	/**
	 * This method initializes NumberjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNombrejTextField() {
		if (nombrejTextField == null) {
			nombrejTextField = new JTextField();
			nombrejTextField.setBounds(new Rectangle(161, 81, 120, 25));
			nombrejTextField.setText("");			
			nombrejTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					if(nombrejTextField.getText().length() > 20){
						e.consume();
						getToolkit().beep();
					}
				}
			});
			Validate.validateLetter(nombrejTextField);
			}		
					
		return nombrejTextField;
	}

	
	/**
	 * This method initializes tablejPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTablejPanel2() {
		if (tablejPanel == null) {
			tablejPanel = new JPanel();
			tablejPanel.setLayout(null);
			tablejPanel.setBounds(new Rectangle(7, 145, 790, 161));
			tablejPanel.add(getCobradoresjScrollPane(), null);
		}
		return tablejPanel;
	}

	/**
	 * This method initializes listOfHousejScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getCobradoresjScrollPane() {
		if (CobradoresjScrollPane == null) {
			CobradoresjScrollPane = new JScrollPane();
			CobradoresjScrollPane.setBounds(new Rectangle(6, 6, 775, 147));
			CobradoresjScrollPane.setViewportView(getCobradoresjTable());
			CobradoresjScrollPane.setViewportView(getCobradoresjTable());
		}
		return CobradoresjScrollPane;
	}

	/**
	 * This method initializes listOfHousejTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getCobradoresjTable() {
		if (CobradroresjTable == null) {
			CobradroresjTable = new JTable();
			}
		return CobradroresjTable;
	}

	/**
	 * This method initializes ModifyjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getModifyjButton() {
		if (ModifyjButton == null) {
			ModifyjButton = new JButton();
			ModifyjButton.setBounds(new Rectangle(141, 321, 116, 27));
			ModifyjButton.setText("Modificar");
			ModifyjButton.setEnabled(false);
			ModifyjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
					int result = JOptionPane.showConfirmDialog(CobradorVisual.this, "¿Are you sure you want modify this Collector ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					int pos = CobradroresjTable.getSelectedRow();
					try {
						LinkedList<Cobrador> cobradores = ServicioCobrador.getCobradores();
						Cobrador c = cobradores.get(pos);
						ServicioCobrador.ModificarCobrador(c.getIdentificador(), CarnetjTextField.getText(), nombrejTextField.getText(), ApellidosjTextField.getText(),Integer.valueOf( AnosjTextField.getText()),MunicipiojTextField.getText());
						JOptionPane.showMessageDialog(CobradorVisual.this, "Collector Modified", "Information", JOptionPane.INFORMATION_MESSAGE);
						InsertjButton.setEnabled(true);
						DefaultTableModel defaultTableModel = new DefaultTableModel();
						LinkedList<Cobrador> list = new LinkedList<Cobrador>();
						try {
							list = ServicioCobrador.getCobradores();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
					        e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ArrayList<Object> columnDataCarnet = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre = new ArrayList<Object>();
						ArrayList<Object> columnDataApellidos = new ArrayList<Object>();
						ArrayList<Object> columnDataMunicipio = new ArrayList<Object>();
						ArrayList<Object> columnDataAnos = new ArrayList<Object>();
						for (int i = 0; i < list.size(); i++) {
							columnDataCarnet.add(list.get(i).getCarnet());
							columnDataNombre.add(list.get(i).getNombre());
							columnDataApellidos.add(list.get(i).getApellidos());
							columnDataMunicipio.add(list.get(i).getMunicipio());
							columnDataAnos.add(list.get(i).getAnos());
						}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Identity Number",columnDataCarnet.toArray());
						defaultTableModel.addColumn("Name",columnDataNombre.toArray());
						defaultTableModel.addColumn("Last Name",columnDataApellidos.toArray());
						defaultTableModel.addColumn("Municipality",columnDataMunicipio.toArray());
						defaultTableModel.addColumn("Experience Years",columnDataAnos.toArray());
						getCobradoresjTable().setModel(defaultTableModel);
						UserInterfaceSuport.clearComponents(jContentPane);
						} catch (SQLException e1) {
						JOptionPane.showMessageDialog(CobradorVisual.this, "Collector Already Exist", "Error", JOptionPane.ERROR_MESSAGE);
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					InsertjButton.setEnabled(true);
					DeletejButton.setEnabled(false);
					ModifyjButton.setEnabled(false);
					NuevojButton.setEnabled(false);
					nombrejTextField.setText("");
					CarnetjTextField.setText("");
					ApellidosjTextField.setText("");
					AnosjTextField.setText("");
					MunicipiojTextField.setText("");
					CarnetjTextField.setEditable(true);
					CasasjButton.setEnabled(false);
					}
					}else{
						int result = JOptionPane.showConfirmDialog(CobradorVisual.this, "¿Está seguro que desea modificar el Cobrador?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						if(result == JOptionPane.YES_OPTION){
						int pos = CobradroresjTable.getSelectedRow();
						try {
							LinkedList<Cobrador> cobradores = ServicioCobrador.getCobradores();
							Cobrador c = cobradores.get(pos);
							ServicioCobrador.ModificarCobrador(c.getIdentificador(), CarnetjTextField.getText(), nombrejTextField.getText(), ApellidosjTextField.getText(),Integer.valueOf( AnosjTextField.getText()),MunicipiojTextField.getText());
							JOptionPane.showMessageDialog(CobradorVisual.this, "Cobrador Modificado", "Información", JOptionPane.INFORMATION_MESSAGE);
							InsertjButton.setEnabled(true);
							DefaultTableModel defaultTableModel = new DefaultTableModel();
							LinkedList<Cobrador> list = new LinkedList<Cobrador>();
							try {
								list = ServicioCobrador.getCobradores();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
						        e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ArrayList<Object> columnDataCarnet = new ArrayList<Object>();
							ArrayList<Object> columnDataNombre = new ArrayList<Object>();
							ArrayList<Object> columnDataApellidos = new ArrayList<Object>();
							ArrayList<Object> columnDataMunicipio = new ArrayList<Object>();
							ArrayList<Object> columnDataAnos = new ArrayList<Object>();
							for (int i = 0; i < list.size(); i++) {
								columnDataCarnet.add(list.get(i).getCarnet());
								columnDataNombre.add(list.get(i).getNombre());
								columnDataApellidos.add(list.get(i).getApellidos());
								columnDataMunicipio.add(list.get(i).getMunicipio());
								columnDataAnos.add(list.get(i).getAnos());
							}
							defaultTableModel.setRowCount(list.size());
							defaultTableModel.addColumn("Carnet de Identidad",columnDataCarnet.toArray());
							defaultTableModel.addColumn("Nombre",columnDataNombre.toArray());
							defaultTableModel.addColumn("Apellidos",columnDataApellidos.toArray());
							defaultTableModel.addColumn("Municipio",columnDataMunicipio.toArray());
							defaultTableModel.addColumn("Años de Experiencia",columnDataAnos.toArray());
							getCobradoresjTable().setModel(defaultTableModel);
							UserInterfaceSuport.clearComponents(jContentPane);
							} catch (SQLException e1) {
							JOptionPane.showMessageDialog(CobradorVisual.this, "El cobrador ya existe", "Error", JOptionPane.ERROR_MESSAGE);
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						InsertjButton.setEnabled(true);
						DeletejButton.setEnabled(false);
						ModifyjButton.setEnabled(false);
						NuevojButton.setEnabled(false);
						nombrejTextField.setText("");
						CarnetjTextField.setText("");
						ApellidosjTextField.setText("");
						AnosjTextField.setText("");
						MunicipiojTextField.setText("");
						CarnetjTextField.setEditable(true);
						CasasjButton.setEnabled(false);
						}
					}
						}});
			}
		return ModifyjButton;
	}

	/**
	 * This method initializes DeletejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDeletejButton() {
		if (DeletejButton == null) {
			DeletejButton = new JButton();
			DeletejButton.setBounds(new Rectangle(274, 321, 116, 27));
			DeletejButton.setText(" Eliminar");
			DeletejButton.setEnabled(false);
			DeletejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
					int result = JOptionPane.showConfirmDialog(CobradorVisual.this, "¿Are you sure you want delete this Collector?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					int pos = CobradroresjTable.getSelectedRow();
					try {
						LinkedList<Cobrador> cobradores = ServicioCobrador.getCobradores();
						Cobrador c = cobradores.get(pos);
						ServicioCobrador.EliminarCobrador(c.getIdentificador());
						JOptionPane.showMessageDialog(CobradorVisual.this, "Collector deleted", "Information", JOptionPane.INFORMATION_MESSAGE);
						InsertjButton.setEnabled(true);
						DefaultTableModel defaultTableModel = new DefaultTableModel();
						LinkedList<Cobrador> list = new LinkedList<Cobrador>();
						try {
							list = ServicioCobrador.getCobradores();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
					        e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ArrayList<Object> columnDataCarnet = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre = new ArrayList<Object>();
						ArrayList<Object> columnDataApellidos = new ArrayList<Object>();
						ArrayList<Object> columnDataMunicipio = new ArrayList<Object>();
						ArrayList<Object> columnDataAnos = new ArrayList<Object>();
						for (int i = 0; i < list.size(); i++) {
							columnDataCarnet.add(list.get(i).getCarnet());
							columnDataNombre.add(list.get(i).getNombre());
							columnDataApellidos.add(list.get(i).getApellidos());
							columnDataMunicipio.add(list.get(i).getMunicipio());
							columnDataAnos.add(list.get(i).getAnos());
						}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Identity Number",columnDataCarnet.toArray());
						defaultTableModel.addColumn("Name",columnDataNombre.toArray());
						defaultTableModel.addColumn("Last Name",columnDataApellidos.toArray());
						defaultTableModel.addColumn("Municipality",columnDataMunicipio.toArray());
						defaultTableModel.addColumn("Experience Years",columnDataAnos.toArray());
						getCobradoresjTable().setModel(defaultTableModel);
						UserInterfaceSuport.clearComponents(getJContentPane());
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(CobradorVisual.this, " There are relations with this Collector", "Error", JOptionPane.ERROR_MESSAGE);
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					DeletejButton.setEnabled(false);
					ModifyjButton.setEnabled(false);
					InsertjButton.setEnabled(true);
					NuevojButton.setEnabled(false);
					nombrejTextField.setText("");
					CarnetjTextField.setText("");
					ApellidosjTextField.setText("");
					AnosjTextField.setText("");
					MunicipiojTextField.setText("");
					CarnetjTextField.setEditable(true);
					CasasjButton.setEnabled(false);
					}
					}else{
						int result = JOptionPane.showConfirmDialog(CobradorVisual.this, "¿Está seguro que desea eliminar la casa?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						if(result == JOptionPane.YES_OPTION){
						int pos = CobradroresjTable.getSelectedRow();
						try {
							LinkedList<Cobrador> cobradores = ServicioCobrador.getCobradores();
							Cobrador c = cobradores.get(pos);
							ServicioCobrador.EliminarCobrador(c.getIdentificador());
							JOptionPane.showMessageDialog(CobradorVisual.this, "Cobrador Eliminado", "Información", JOptionPane.INFORMATION_MESSAGE);
							InsertjButton.setEnabled(true);
							DefaultTableModel defaultTableModel = new DefaultTableModel();
							LinkedList<Cobrador> list = new LinkedList<Cobrador>();
							try {
								list = ServicioCobrador.getCobradores();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
						        e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ArrayList<Object> columnDataCarnet = new ArrayList<Object>();
							ArrayList<Object> columnDataNombre = new ArrayList<Object>();
							ArrayList<Object> columnDataApellidos = new ArrayList<Object>();
							ArrayList<Object> columnDataMunicipio = new ArrayList<Object>();
							ArrayList<Object> columnDataAnos = new ArrayList<Object>();
							for (int i = 0; i < list.size(); i++) {
								columnDataCarnet.add(list.get(i).getCarnet());
								columnDataNombre.add(list.get(i).getNombre());
								columnDataApellidos.add(list.get(i).getApellidos());
								columnDataMunicipio.add(list.get(i).getMunicipio());
								columnDataAnos.add(list.get(i).getAnos());
							}
							defaultTableModel.setRowCount(list.size());
							defaultTableModel.addColumn("Carnet de Identidad",columnDataCarnet.toArray());
							defaultTableModel.addColumn("Nombre",columnDataNombre.toArray());
							defaultTableModel.addColumn("Apellidos",columnDataApellidos.toArray());
							defaultTableModel.addColumn("Municipio",columnDataMunicipio.toArray());
							defaultTableModel.addColumn("Años de Experiencia",columnDataAnos.toArray());
							getCobradoresjTable().setModel(defaultTableModel);
							UserInterfaceSuport.clearComponents(getJContentPane());
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(CobradorVisual.this, "Existen relaciones con este Cobrador", "Error", JOptionPane.ERROR_MESSAGE);
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						DeletejButton.setEnabled(false);
						ModifyjButton.setEnabled(false);
						InsertjButton.setEnabled(true);
						NuevojButton.setEnabled(false);
						nombrejTextField.setText("");
						CarnetjTextField.setText("");
						ApellidosjTextField.setText("");
						AnosjTextField.setText("");
						MunicipiojTextField.setText("");
						CarnetjTextField.setEditable(true);
						CasasjButton.setEnabled(false);
						}
						
					}
					}});
				}
		return DeletejButton;
	}
/**
	 * This method initializes InsertjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getInsertjButton() {
		if (InsertjButton == null) {
			InsertjButton = new JButton();
			InsertjButton.setBounds(new Rectangle(14, 321, 116, 27));
			InsertjButton.setText("Insertar");
			InsertjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(nombrejTextField.getText().length() > 0 && CarnetjTextField.getText().length() > 0 && ApellidosjTextField.getText().length() >0 
							&& AnosjTextField.getText().length() >0 && MunicipiojTextField.getText().length()>0){
						if(CarnetjTextField.getText().length() > 10){
					try {
							Cobrador h = new Cobrador();
						ServicioCobrador.insertarCobrador(h.getIdentificador(), CarnetjTextField.getText(), nombrejTextField.getText(), ApellidosjTextField.getText(), Integer.valueOf(AnosjTextField.getText()),MunicipiojTextField.getText());

						DefaultTableModel defaultTableModel = new DefaultTableModel();
						LinkedList<Cobrador> list = new LinkedList<Cobrador>();
						try {
							list = ServicioCobrador.getCobradores();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
					        e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ArrayList<Object> columnDataCarnet = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre = new ArrayList<Object>();
						ArrayList<Object> columnDataApellidos = new ArrayList<Object>();
						ArrayList<Object> columnDataMunicipio = new ArrayList<Object>();
						ArrayList<Object> columnDataAnos = new ArrayList<Object>();
						for (int i = 0; i < list.size(); i++) {
							columnDataCarnet.add(list.get(i).getCarnet());
							columnDataNombre.add(list.get(i).getNombre());
							columnDataApellidos.add(list.get(i).getApellidos());
							columnDataMunicipio.add(list.get(i).getMunicipio());
							columnDataAnos.add(list.get(i).getAnos());
						}
						defaultTableModel.setRowCount(list.size());
						if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
						defaultTableModel.addColumn("Identity Number",columnDataCarnet.toArray());
						defaultTableModel.addColumn("Name",columnDataNombre.toArray());
						defaultTableModel.addColumn("Last Name",columnDataApellidos.toArray());
						defaultTableModel.addColumn("Municipality",columnDataMunicipio.toArray());
						defaultTableModel.addColumn("Experience Years",columnDataAnos.toArray());
						}else{
							defaultTableModel.addColumn("Carnet de Identidad",columnDataCarnet.toArray());
							defaultTableModel.addColumn("Nombre",columnDataNombre.toArray());
							defaultTableModel.addColumn("Apellidos",columnDataApellidos.toArray());
							defaultTableModel.addColumn("Municipio",columnDataMunicipio.toArray());
							defaultTableModel.addColumn("Años de Experiencia",columnDataAnos.toArray());
						}
						getCobradoresjTable().setModel(defaultTableModel);
							if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
							JOptionPane.showMessageDialog(CobradorVisual.this, "Collector Inserted", "Information", JOptionPane.INFORMATION_MESSAGE);
							}
							else{
								JOptionPane.showMessageDialog(CobradorVisual.this, "Cobrador insertado", "Informacion", JOptionPane.INFORMATION_MESSAGE);	
							}
							UserInterfaceSuport.clearComponents(jContentPane);						
							} catch (SQLException e1) {
							if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
						JOptionPane.showMessageDialog(CobradorVisual.this, "Collector Already Exist", "Error", JOptionPane.ERROR_MESSAGE);
							}else{
								JOptionPane.showMessageDialog(CobradorVisual.this, "El Cobrador ya Existe", "Error", JOptionPane.ERROR_MESSAGE);
							}
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ApellidosjTextField.setText("");
					AnosjTextField.setText("");
					MunicipiojTextField.setText("");
					nombrejTextField.setText("");
					CarnetjTextField.setText("");
					DeletejButton.setEnabled(false);
					ModifyjButton.setEnabled(false);
					InsertjButton.setEnabled(true);
					NuevojButton.setEnabled(false);
					CarnetjTextField.setEditable(true);
					CasasjButton.setEnabled(false);
					}else{
						if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
							JOptionPane.showMessageDialog(CobradorVisual.this, "Collector Already Exist", "Error", JOptionPane.ERROR_MESSAGE);
								}else{
									JOptionPane.showMessageDialog(CobradorVisual.this, "El Carnet de Identidad no debe tener menos de 11 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
								}
					}	
					}
					else
						if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
						JOptionPane.showMessageDialog(CobradorVisual.this, "Empty Fields", "Error", JOptionPane.ERROR_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(CobradorVisual.this, "Campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);	
						}
					
					}	
				
			});
			
		}	
		return InsertjButton;
	}
	
	

	/**
	 * This method initializes closejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getClosejButton() {
		if (closejButton == null) {
			closejButton = new JButton();
			closejButton.setBounds(new Rectangle(668, 321, 116, 27));
			closejButton.setText(" Cerrar");
			closejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return closejButton;
	}

	/**
	 * This method initializes habitantesjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCarnetjTextField() {
		if (CarnetjTextField == null) {
			CarnetjTextField = new JTextField();
			CarnetjTextField.setBounds(new Rectangle(19, 81, 120, 25));
			CarnetjTextField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent e) {
				if(CarnetjTextField.getText().length() > 10){
					e.consume();
					getToolkit().beep();
				}
			}
		});
		Validate.validateDigit(CarnetjTextField);
		}
		return CarnetjTextField;
	}

	/**
	 * This method initializes NuevojButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNuevojButton() {
		if (NuevojButton == null) {
			NuevojButton = new JButton();
			NuevojButton.setBounds(new Rectangle(404, 321, 116, 27));
			NuevojButton.setText("Nuevo");
			NuevojButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					nombrejTextField.setText("");
					CarnetjTextField.setText("");
					ApellidosjTextField.setText("");
					AnosjTextField.setText("");
					MunicipiojTextField.setText("");
					InsertjButton.setEnabled(true);
					ModifyjButton.setEnabled(false);
					DeletejButton.setEnabled(false);	
					NuevojButton.setEnabled(false);
					CarnetjTextField.setEditable(true);
					CasasjButton.setEnabled(false);
					}			     	
				});
		}
		return NuevojButton;
	}

	public void idioma(){
		if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
			municipioLabel.setText("Municipality:");
			CarnetjLabel.setText("Identity Number:");
			apellidosjLabel.setText("Last Name:");
			NombrejLabel.setText("Name:");
			AnosjLabel.setText("Experience Years");
			ModifyjButton.setText("Modify");
			DeletejButton.setText("Delete");
			InsertjButton.setText("Insert");
			closejButton.setText("Cancel");
			CasasjButton.setText("Homes");
			NuevojButton.setText("New");
			CobradorjPanel.setBorder(BorderFactory.createTitledBorder(null, "Collector Information", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			this.setTitle("Collector's management");
			}
		else{
			municipioLabel.setText("Municipio:");
			CarnetjLabel.setText("Carnet de Identidad:");
			apellidosjLabel.setText("Apellidos:");
			NombrejLabel.setText("Nombre:");
			AnosjLabel.setText("Años de Experiencia");
			ModifyjButton.setText("Modificar");
			DeletejButton.setText("Eliminar");
			CasasjButton.setText("Casas");
			InsertjButton.setText("Insertar");
			closejButton.setText("Cerrar");
			NuevojButton.setText("Nuevo");
			CobradorjPanel.setBorder(BorderFactory.createTitledBorder(null, "Datos del Cobrador", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			this.setTitle("Gestión de Cobradores");
			
			}
	}

	/**
	 * This method initializes MunicipiojTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getMunicipiojTextField() {
		if (MunicipiojTextField == null) {
			MunicipiojTextField = new JTextField();
			MunicipiojTextField.setBounds(new Rectangle(458, 81, 120, 25));
			MunicipiojTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					if(MunicipiojTextField.getText().length() > 20){
						e.consume();
						getToolkit().beep();
					}
				}
			});
			}
		return MunicipiojTextField;
	}

	/**
	 * This method initializes ApellidosjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getApellidosjTextField() {
		if (ApellidosjTextField == null) {
			ApellidosjTextField = new JTextField();
			ApellidosjTextField.setBounds(new Rectangle(306, 81, 120, 25));
			ApellidosjTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					if(ApellidosjTextField.getText().length() > 20){
						e.consume();
						getToolkit().beep();
					}
				}
			});
			Validate.validateLetter(ApellidosjTextField);
		}
		return ApellidosjTextField;
	}

	/**
	 * This method initializes AnosjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getAnosjTextField() {
		if (AnosjTextField == null) {
			AnosjTextField = new JTextField();
			AnosjTextField.setBounds(new Rectangle(618, 81, 120, 25));
			AnosjTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					if(AnosjTextField.getText().length() > 1){
						e.consume();
						getToolkit().beep();
					}
				}
			});
			Validate.validateDigit(AnosjTextField);
		}
		return AnosjTextField;
	}

	/**
	 * This method initializes CasasjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCasasjButton() {
		if (CasasjButton == null) {
			CasasjButton = new JButton();
			CasasjButton.setBounds(new Rectangle(536, 321, 116, 27));
			CasasjButton.setText("Casas");
			CasasjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					InsertjButton.setEnabled(true);
					ModifyjButton.setEnabled(false);
					DeletejButton.setEnabled(false);	
					NuevojButton.setEnabled(false);
					CasasCobrador c = new CasasCobrador(CobradorVisual.this,true,selected);
					c.setVisible(true);
					c.idioma();
				}
			});
		}
		return CasasjButton;
	}
		
}  //  @jve:decl-index=0:visual-constraint="16,7"
