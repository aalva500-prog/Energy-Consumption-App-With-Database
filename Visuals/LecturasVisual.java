package Visuals;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.Casa;
import model.Lectura;
import Services.ServicioLectura;
import Utils.Idioma;
import Utils.UserInterfaceSuport;
import Utils.Validate;

public class LecturasVisual extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel StartRegistrationjLabel = null;

	private JLabel finalRegistrationjLabel = null;

	private JTextField startRegistrationjTextField = null;

	private JTextField FinalRegistrationjTextField = null;

	private JButton insertjButton = null;

	private JButton ModifyjButton = null;

	private JPanel consuptionjPanel = null;

	private JScrollPane consuptionsjScrollPane = null;

	private JTable consuptionsjTable = null;

	private Casa home=null;

	private JLabel DatejLabel = null;

	private JSpinner DatejSpinner = null;

	private SpinnerDateModel DatespinnerDateModel = null;  //  @jve:decl-index=0:visual-constraint="709,40"

	private JLabel numberHomejLabel = null;

		private JButton closejButton = null;

	private JButton EliminarjButton = null;

	private JButton NuevojButton = null;

	private JLabel DireccionjLabel = null;

	/**
	 * @param owner
	 */
	public LecturasVisual(Frame owner) {
		super(owner);
		initialize();
		idioma();
		}
	
	public LecturasVisual(JDialog dialog,boolean modal,Casa home) {
		super(dialog,modal);
		this.home = home;
		initialize();
		idioma();
		}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(670, 372);
		this.setTitle("Consumo Diario");
		UserInterfaceSuport.centerComponent(this);
		this.setContentPane(getJContentPane());
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		LinkedList<Lectura> list = new LinkedList<Lectura>();		
	    list = ServicioLectura.getCons(home.getIdentificador());		
		ArrayList<Object> columnDataFecha = new ArrayList<Object>();
		ArrayList<Object> columnDataInicial = new ArrayList<Object>();
		ArrayList<Object> columnDataFinal = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			columnDataFecha.add(list.get(i).getFecha());
			columnDataInicial.add(list.get(i).getRegistroInicial());
			columnDataFinal.add(list.get(i).getRegistroFinal());
			}
		defaultTableModel.setRowCount(list.size());
		if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
		defaultTableModel.addColumn("Date",columnDataFecha.toArray());
		defaultTableModel.addColumn("Start Registration",columnDataInicial.toArray());
		defaultTableModel.addColumn("Final Registration",columnDataFinal.toArray());
		}else{
			defaultTableModel.addColumn("Fecha",columnDataFecha.toArray());
			defaultTableModel.addColumn("Registro Inicial",columnDataInicial.toArray());
			defaultTableModel.addColumn("Registro Final",columnDataFinal.toArray());	
		}
		getConsuptionsjTable().setModel(defaultTableModel);
		
		consuptionsjTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int pos = consuptionsjTable.getSelectedRow();
				LinkedList<Lectura> consumos = ServicioLectura.getCons(home.getIdentificador());
				Lectura u = consumos.get(pos);
				startRegistrationjTextField.setText(String.valueOf(u.getRegistroInicial()));
				FinalRegistrationjTextField.setText(String.valueOf(u.getRegistroFinal()));
				EliminarjButton.setEnabled(true);
				ModifyjButton.setEnabled(true);
				insertjButton.setEnabled(false);
				DatejSpinner.setEnabled(false);	
				NuevojButton.setEnabled(true);
				}
		});	
		}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			finalRegistrationjLabel = new JLabel();
			finalRegistrationjLabel.setText("  Registro Final:");
			finalRegistrationjLabel.setBounds(new Rectangle(380, 15, 100, 29));
			StartRegistrationjLabel = new JLabel();
			StartRegistrationjLabel.setText("  Registro inicial:");
			StartRegistrationjLabel.setBounds(new Rectangle(257, 15, 100, 29));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setName("Consumo");
			jContentPane.add(getInsertjButton(), null);
			jContentPane.add(getModifyjButton(), null);
			jContentPane.add(getConsuptionjPanel(), null);
			jContentPane.add(getConsuptionsjScrollPane(), null);
			jContentPane.add(getClosejButton(), null);
			jContentPane.add(getEliminarjButton(), null);
			jContentPane.add(getNuevojButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes startRegistrationjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getStartRegistrationjTextField() {
		if (startRegistrationjTextField == null) {
			startRegistrationjTextField = new JTextField();
			startRegistrationjTextField.setBounds(new Rectangle(257, 65, 100, 29));
			Validate.validateDigitAndComma(startRegistrationjTextField);
		}
		return startRegistrationjTextField;
	}

	/**
	 * This method initializes FinalRegistrationjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFinalRegistrationjTextField() {
		if (FinalRegistrationjTextField == null) {
			FinalRegistrationjTextField = new JTextField();
			FinalRegistrationjTextField.setBounds(new Rectangle(380, 65, 100, 29));
			Validate.validateDigitAndComma(FinalRegistrationjTextField);
		}
		return FinalRegistrationjTextField;
	}

		/**
	 * This method initializes insertjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getInsertjButton() {
		if (insertjButton == null) {
			insertjButton = new JButton();
			insertjButton.setBounds(new Rectangle(36, 302, 91, 22));
			insertjButton.setText("Insertar");
			insertjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(startRegistrationjTextField.getText().length() > 0 && FinalRegistrationjTextField.getText().length() > 0){
						if(Float.valueOf(startRegistrationjTextField.getText()) < Float.valueOf(FinalRegistrationjTextField.getText())){
							try {
							Lectura c = new Lectura();
							SimpleDateFormat dataformato = new SimpleDateFormat("dd/MM/yyyy");
							String fecha = dataformato.format(getDatespinnerDateModel().getDate());
						ServicioLectura.insertarConsumo(fecha, Float.valueOf(startRegistrationjTextField.getText()), Float.valueOf(FinalRegistrationjTextField.getText()),c.getIdentificador() , home.getIdentificador());
						DefaultTableModel defaultTableModel = new DefaultTableModel();					
						LinkedList<Lectura> list = new LinkedList<Lectura>();		
					    list = ServicioLectura.getCons(home.getIdentificador());		
						ArrayList<Object> columnDataFecha = new ArrayList<Object>();
						ArrayList<Object> columnDataInicial = new ArrayList<Object>();
						ArrayList<Object> columnDataFinal = new ArrayList<Object>();
						for (int i = 0; i < list.size(); i++) {
							columnDataFecha.add(list.get(i).getFecha());
							columnDataInicial.add(list.get(i).getRegistroInicial());
							columnDataFinal.add(list.get(i).getRegistroFinal());
							}
						defaultTableModel.setRowCount(list.size());
						if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
							defaultTableModel.addColumn("Date",columnDataFecha.toArray());
							defaultTableModel.addColumn("Start Registration",columnDataInicial.toArray());
							defaultTableModel.addColumn("Final Registration",columnDataFinal.toArray());
							}else{
								defaultTableModel.addColumn("Fecha",columnDataFecha.toArray());
								defaultTableModel.addColumn("Registro Inicial",columnDataInicial.toArray());
								defaultTableModel.addColumn("Registro Final",columnDataFinal.toArray());	
							}
						getConsuptionsjTable().setModel(defaultTableModel);
						if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
							JOptionPane.showMessageDialog(LecturasVisual.this, "Consumption Inserted", "Information", JOptionPane.INFORMATION_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(LecturasVisual.this, "Consumo insertado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						}
							UserInterfaceSuport.clearComponents(jContentPane);
							} catch (SQLException e1) {		
								if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
									JOptionPane.showMessageDialog(LecturasVisual.this, "Consumption Already Exist", "Error", JOptionPane.ERROR_MESSAGE);
										}else{
											JOptionPane.showMessageDialog(LecturasVisual.this, "Ya existe el consumo", "Error", JOptionPane.ERROR_MESSAGE);
										}
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					startRegistrationjTextField.setText("");
					FinalRegistrationjTextField.setText("");
					insertjButton.setEnabled(true);
					EliminarjButton.setEnabled(false);
					ModifyjButton.setEnabled(false);
					DatejSpinner.setEnabled(true);
					NuevojButton.setEnabled(false);
					}
						else
							if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
							JOptionPane.showMessageDialog(LecturasVisual.this, "Final Registration must be bigger than Start Registration", "Error", JOptionPane.ERROR_MESSAGE);
							}
							else{
								JOptionPane.showMessageDialog(LecturasVisual.this, "El Registro Final debe ser mayor que el Inicial", "Error", JOptionPane.ERROR_MESSAGE);
							}
					}
					else
						if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
						JOptionPane.showMessageDialog(LecturasVisual.this, "Empty Fields", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else{
							JOptionPane.showMessageDialog(LecturasVisual.this, "Campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}			
			});
			}	
		return insertjButton;
	}

	/**
	 * This method initializes ModifyjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getModifyjButton() {
		if (ModifyjButton == null) {
			ModifyjButton = new JButton();
			ModifyjButton.setBounds(new Rectangle(157, 302, 102, 22));
			ModifyjButton.setText("Modificar");
			ModifyjButton.setEnabled(false);
		    ModifyjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
					int result = JOptionPane.showConfirmDialog(LecturasVisual.this, "Are you sure you want modify this Consumption?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					int pos = consuptionsjTable.getSelectedRow();					
					if(Integer.valueOf(startRegistrationjTextField.getText()) < Integer.valueOf(FinalRegistrationjTextField.getText())){
					try {
						LinkedList<Lectura> consumos = ServicioLectura.getCons(home.getIdentificador());
						Lectura u = consumos.get(pos);
						ServicioLectura.ModificarConsumo(u.getIdentificador(), Float.valueOf(startRegistrationjTextField.getText()),Float.valueOf(FinalRegistrationjTextField.getText()));
						JOptionPane.showMessageDialog(LecturasVisual.this, "Consumption Modified", "Information", JOptionPane.INFORMATION_MESSAGE);
						insertjButton.setEnabled(true);
						DefaultTableModel defaultTableModel = new DefaultTableModel();
						LinkedList<Lectura> list = new LinkedList<Lectura>();		
					    list = ServicioLectura.getCons(home.getIdentificador());	
					   	ArrayList<Object> columnDataFecha = new ArrayList<Object>();
						ArrayList<Object> columnDataInicial = new ArrayList<Object>();
						ArrayList<Object> columnDataFinal = new ArrayList<Object>();
						for (int i = 0; i < list.size(); i++) {
							columnDataFecha.add(list.get(i).getFecha());
							columnDataInicial.add(list.get(i).getRegistroInicial());
							columnDataFinal.add(list.get(i).getRegistroFinal());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Date",columnDataFecha.toArray());
						defaultTableModel.addColumn("Start Registration",columnDataInicial.toArray());
						defaultTableModel.addColumn("Final Registration",columnDataFinal.toArray());
						getConsuptionsjTable().setModel(defaultTableModel);
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					insertjButton.setEnabled(true);
					EliminarjButton.setEnabled(false);
					ModifyjButton.setEnabled(false);
					startRegistrationjTextField.setText("");
					FinalRegistrationjTextField.setText("");
					DatejSpinner.setEnabled(true);
					NuevojButton.setEnabled(false);
					}
					else
						JOptionPane.showMessageDialog(LecturasVisual.this, "Final Registration must be bigger than Start Registration", "Error", JOptionPane.ERROR_MESSAGE);					
						}
					}else{
						int result = JOptionPane.showConfirmDialog(LecturasVisual.this, "¿Está seguro que desea modificar el Consumo?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						if(result == JOptionPane.YES_OPTION){
						int pos = consuptionsjTable.getSelectedRow();
						if(Float.valueOf(startRegistrationjTextField.getText()) < Float.valueOf(FinalRegistrationjTextField.getText())){
						try {
							LinkedList<Lectura> consumos = ServicioLectura.getCons(home.getIdentificador());
							Lectura u = consumos.get(pos);
							ServicioLectura.ModificarConsumo(u.getIdentificador(), Float.valueOf(startRegistrationjTextField.getText()),Float.valueOf(FinalRegistrationjTextField.getText()));
							JOptionPane.showMessageDialog(LecturasVisual.this, "Consumo Modificado", "Información", JOptionPane.INFORMATION_MESSAGE);
							insertjButton.setEnabled(true);
							DefaultTableModel defaultTableModel = new DefaultTableModel();
							LinkedList<Lectura> list = new LinkedList<Lectura>();		
						    list = ServicioLectura.getCons(home.getIdentificador());		
							ArrayList<Object> columnDataFecha = new ArrayList<Object>();
							ArrayList<Object> columnDataInicial = new ArrayList<Object>();
							ArrayList<Object> columnDataFinal = new ArrayList<Object>();
							for (int i = 0; i < list.size(); i++) {
								columnDataFecha.add(list.get(i).getFecha());
								columnDataInicial.add(list.get(i).getRegistroInicial());
								columnDataFinal.add(list.get(i).getRegistroFinal());
								}
							defaultTableModel.setRowCount(list.size());
							defaultTableModel.addColumn("Fecha",columnDataFecha.toArray());
							defaultTableModel.addColumn("Registro Inicial",columnDataInicial.toArray());
							defaultTableModel.addColumn("Registro Final",columnDataFinal.toArray());
							getConsuptionsjTable().setModel(defaultTableModel);
							} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						insertjButton.setEnabled(true);
						EliminarjButton.setEnabled(false);
						ModifyjButton.setEnabled(false);
						startRegistrationjTextField.setText("");
						FinalRegistrationjTextField.setText("");
						DatejSpinner.setEnabled(true);
						NuevojButton.setEnabled(false);
						}
						else
							JOptionPane.showMessageDialog(LecturasVisual.this, "El Registro Final debe ser mayor que el Inicial", "Error", JOptionPane.ERROR_MESSAGE);					
						}
					}
					}});
			}
		    
		return ModifyjButton;
	}
	/**
	 * This method initializes consuptionjPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getConsuptionjPanel() {
		if (consuptionjPanel == null) {
			DireccionjLabel = new JLabel();
			DireccionjLabel.setBounds(new Rectangle(12, 44, 233, 54));
			DireccionjLabel.setText("Direccion" +":"+  home.getDireccion());	
			numberHomejLabel = new JLabel();
			numberHomejLabel.setText("Casa" +":"+  home.getNumero());
			numberHomejLabel.setBounds(new Rectangle(12, 20, 233, 17));
			DatejLabel = new JLabel();
			DatejLabel.setBounds(new Rectangle(504, 15, 100, 29));
			DatejLabel.setText("Fecha:");
			TitledBorder titledBorder = BorderFactory.createTitledBorder(null, "Datos del consumo", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51));
			consuptionjPanel = new JPanel();
			consuptionjPanel.setLayout(null);
			consuptionjPanel.setBounds(new Rectangle(16, 9, 621, 112));
			consuptionjPanel.setBorder(titledBorder);
			consuptionjPanel.add(StartRegistrationjLabel, null);
			consuptionjPanel.add(getStartRegistrationjTextField(), null);
			consuptionjPanel.add(finalRegistrationjLabel, null);
			consuptionjPanel.add(getFinalRegistrationjTextField(), null);
			consuptionjPanel.add(DatejLabel, null);
			consuptionjPanel.add(getDatejSpinner(), null);
			consuptionjPanel.add(DireccionjLabel, null);
			consuptionjPanel.add(numberHomejLabel, null);
		}
		return consuptionjPanel;
	}

	/**
	 * This method initializes consuptionsjScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getConsuptionsjScrollPane() {
		if (consuptionsjScrollPane == null) {
			consuptionsjScrollPane = new JScrollPane();
			consuptionsjScrollPane.setBounds(new Rectangle(16, 129, 627, 160));
			consuptionsjScrollPane.setViewportView(getConsuptionsjTable());
		}
		return consuptionsjScrollPane;
	}

	/**
	 * This method initializes consuptionsjTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getConsuptionsjTable() {
		if (consuptionsjTable == null) {
			consuptionsjTable = new JTable();		
		}
		return consuptionsjTable;
	}
	/**
	 * This method initializes DatejSpinner	
	 * 	
	 * @return javax.swing.JSpinner	
	 */
	private JSpinner getDatejSpinner() {
		if (DatejSpinner == null) {
			DatejSpinner = new JSpinner(getDatespinnerDateModel() );
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			DatejSpinner.setEditor(new JSpinner.DateEditor(DatejSpinner,dateFormat.toPattern()));
			DatejSpinner.setBounds(new Rectangle(504, 65, 100, 29));
		}
		return DatejSpinner;
	}
	/**
	 * This method initializes DatespinnerDateModel	
	 * 	
	 * @return javax.swing.SpinnerDateModel	
	 */
	private SpinnerDateModel getDatespinnerDateModel() {
		if (DatespinnerDateModel == null) {
			DatespinnerDateModel = new SpinnerDateModel();
		}
		return DatespinnerDateModel;
	}
	
	/**
	 * This method initializes closejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getClosejButton() {
		if (closejButton == null) {
			closejButton = new JButton();
			closejButton.setBounds(new Rectangle(529, 302, 102, 22));
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
	 * This method initializes EliminarjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEliminarjButton() {
		if (EliminarjButton == null) {
			EliminarjButton = new JButton();
			EliminarjButton.setBounds(new Rectangle(281, 302, 102, 22));
			EliminarjButton.setText("Eliminar");
			EliminarjButton.setEnabled(false);
			EliminarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
					int result = JOptionPane.showConfirmDialog(LecturasVisual.this, "Are you sure you want delete this Consumption?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					int pos = consuptionsjTable.getSelectedRow();
					try {
						LinkedList<Lectura> consumos = ServicioLectura.getCons(home.getIdentificador());
						Lectura u = consumos.get(pos);
						ServicioLectura.EliminarConsumo(u.getIdentificador());
						JOptionPane.showMessageDialog(LecturasVisual.this, "Consumption Deleted", "Information", JOptionPane.INFORMATION_MESSAGE);
						insertjButton.setEnabled(true);
						DefaultTableModel defaultTableModel = new DefaultTableModel();
						LinkedList<Lectura> list = new LinkedList<Lectura>();		
					    list = ServicioLectura.getCons(home.getIdentificador());		
						ArrayList<Object> columnDataFecha = new ArrayList<Object>();
						ArrayList<Object> columnDataInicial = new ArrayList<Object>();
						ArrayList<Object> columnDataFinal = new ArrayList<Object>();
						for (int i = 0; i < list.size(); i++) {
							columnDataFecha.add(list.get(i).getFecha());
							columnDataInicial.add(list.get(i).getRegistroInicial());
							columnDataFinal.add(list.get(i).getRegistroFinal());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Date",columnDataFecha.toArray());
						defaultTableModel.addColumn("Start Registration",columnDataInicial.toArray());
						defaultTableModel.addColumn("Final Registration",columnDataFinal.toArray());
						getConsuptionsjTable().setModel(defaultTableModel);
						UserInterfaceSuport.clearComponents(getJContentPane());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					insertjButton.setEnabled(true);
					EliminarjButton.setEnabled(false);
					ModifyjButton.setEnabled(false);
					startRegistrationjTextField.setText("");
					FinalRegistrationjTextField.setText("");
					DatejSpinner.setEnabled(true);
					NuevojButton.setEnabled(false);
					}
					}else{
						int result = JOptionPane.showConfirmDialog(LecturasVisual.this, "¿Está seguro que desea eliminar el consumo?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						if(result == JOptionPane.YES_OPTION){
						int pos = consuptionsjTable.getSelectedRow();
						try {
							LinkedList<Lectura> consumos = ServicioLectura.getCons(home.getIdentificador());
							Lectura u = consumos.get(pos);
							ServicioLectura.EliminarConsumo(u.getIdentificador());
							JOptionPane.showMessageDialog(LecturasVisual.this, "Consumo Eliminado", "Información", JOptionPane.INFORMATION_MESSAGE);
							insertjButton.setEnabled(true);
							DefaultTableModel defaultTableModel = new DefaultTableModel();
							LinkedList<Lectura> list = new LinkedList<Lectura>();		
						    list = ServicioLectura.getCons(home.getIdentificador());		
							ArrayList<Object> columnDataFecha = new ArrayList<Object>();
							ArrayList<Object> columnDataInicial = new ArrayList<Object>();
							ArrayList<Object> columnDataFinal = new ArrayList<Object>();
							for (int i = 0; i < list.size(); i++) {
								columnDataFecha.add(list.get(i).getFecha());
								columnDataInicial.add(list.get(i).getRegistroInicial());
								columnDataFinal.add(list.get(i).getRegistroFinal());
								}
							defaultTableModel.setRowCount(list.size());
							defaultTableModel.addColumn("Fecha",columnDataFecha.toArray());
							defaultTableModel.addColumn("Registro Inicial",columnDataInicial.toArray());
							defaultTableModel.addColumn("Registro Final",columnDataFinal.toArray());
							getConsuptionsjTable().setModel(defaultTableModel);
							UserInterfaceSuport.clearComponents(getJContentPane());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						insertjButton.setEnabled(true);
						EliminarjButton.setEnabled(false);
						ModifyjButton.setEnabled(false);
						startRegistrationjTextField.setText("");
						FinalRegistrationjTextField.setText("");
						DatejSpinner.setEnabled(true);
						NuevojButton.setEnabled(false);
						}
					}
					}});
			
		}
		return EliminarjButton;
	}

	/**
	 * This method initializes NuevojButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNuevojButton() {
		if (NuevojButton == null) {
			NuevojButton = new JButton();
			NuevojButton.setBounds(new Rectangle(406, 302, 102, 22));
			NuevojButton.setText("Nuevo");
			NuevojButton.setEnabled(false);
			NuevojButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					startRegistrationjTextField.setText("");
					FinalRegistrationjTextField.setText("");
					DatejSpinner.setEnabled(true);
					insertjButton.setEnabled(true);
					ModifyjButton.setEnabled(false);
					EliminarjButton.setEnabled(false);	
					NuevojButton.setEnabled(false);
				}
			     	
				});
		}
		return NuevojButton;
	}
	
	public void idioma(){
		if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
			DatejLabel.setText("Date:");
			StartRegistrationjLabel.setText("Start Registration:");
			finalRegistrationjLabel.setText("Final Registration:");
			ModifyjButton.setText("Modify");
			EliminarjButton.setText("Delete");
			insertjButton.setText("Insert");
			closejButton.setText("Cancel");
			NuevojButton.setText("New");
			numberHomejLabel.setText("#" +":"+  home.getNumero());
			DireccionjLabel.setText( home.getDireccion());	
			TitledBorder titledBorder = BorderFactory.createTitledBorder(null, "Consumption Information", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51));
			consuptionjPanel.setBorder(titledBorder);
			this.setTitle("Dayli Consumption");
			}
		else{
			DatejLabel.setText("Fecha:");
			StartRegistrationjLabel.setText("Registro Inicial:");
			finalRegistrationjLabel.setText("Registro Final:");
			ModifyjButton.setText("Modificar");
			EliminarjButton.setText("Eliminar");
			insertjButton.setText("Insertar");
			closejButton.setText("Cerrar");
			NuevojButton.setText("Nuevo");
			numberHomejLabel.setText("#" +":"+  home.getNumero());
			DireccionjLabel.setText(home.getDireccion());	
			TitledBorder titledBorder = BorderFactory.createTitledBorder(null, "Datos del Consumo", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51));
			consuptionjPanel.setBorder(titledBorder);
			this.setTitle("Consumo Diario");
			}
	}
}  //  @jve:decl-index=0:visual-constraint="21,11"
