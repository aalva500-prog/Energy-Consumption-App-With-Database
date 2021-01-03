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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.Casa;
import Services.ServicioCasa;
import Utils.Idioma;
import Utils.UserInterfaceSuport;
import Utils.Validate;

public class CasasVisual extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel DatesOfHousejPanel = null;

	private JLabel NumberjLabel = null;

	private JTextField NumberjTextField = null;

	private JLabel quantityOfInhabitantsjLabel = null;

	private JPanel tablejPanel = null;

	private JScrollPane listOfHousejScrollPane = null;

	private JTable listOfHousejTable = null;

	private JButton ModifyjButton = null;

	private JButton DeletejButton = null;

	private JScrollPane addressScrollPane = null;

	private JTextArea addressTextArea = null;

	private JLabel addressLabel = null;

	private JButton InsertjButton = null;
	
 	private JButton closejButton = null;

	private JTextField habitantesjTextField = null;

	private JButton NuevojButton = null;

	private JButton ConsumosjButton = null;
	
	private Casa selected = null;

	private JButton CobradorjButton = null;

	/**
	 * @param owner
	 */
	public CasasVisual(JDialog dialog,boolean modal) {
		super(dialog,modal);
		initialize();
		idioma();
		}

	/**
	 * @param owner
	 */
	public CasasVisual(Frame owner) {
		super(owner);
		initialize();
		idioma();
		}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(725, 357);
		this.setTitle("Trabajo con una Casa");
		this.setContentPane(getJContentPane());
		DeletejButton.setEnabled(false);
		ModifyjButton.setEnabled(false);
		InsertjButton.setEnabled(true);
		ConsumosjButton.setEnabled(false);
		ConsumosjButton.setEnabled(false);
		CobradorjButton.setEnabled(false);
		NuevojButton.setEnabled(false);
		UserInterfaceSuport.centerComponent(this);
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		LinkedList<Casa> list = new LinkedList<Casa>();
		try {
			list = ServicioCasa.getHomes();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ArrayList<Object> columnDataNumero = new ArrayList<Object>();
		ArrayList<Object> columnDataDireccion = new ArrayList<Object>();
		ArrayList<Object> columnDataHab = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			columnDataNumero.add(list.get(i).getNumero());
			columnDataDireccion.add(list.get(i).getDireccion());
			columnDataHab.add(list.get(i).gethabitantes());
			}
		defaultTableModel.setRowCount(list.size());
		if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
		defaultTableModel.addColumn("Number",columnDataNumero.toArray());
		defaultTableModel.addColumn("Address",columnDataDireccion.toArray());
		defaultTableModel.addColumn(" Quantity of Habitants",columnDataHab.toArray());
		 }else{
			defaultTableModel.addColumn("Numero",columnDataNumero.toArray());
			defaultTableModel.addColumn("Direccion",columnDataDireccion.toArray());
			defaultTableModel.addColumn("Cantidad de Habitantes",columnDataHab.toArray());
			}
		getListOfHousejTable().setModel(defaultTableModel);
		
		listOfHousejTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int pos = listOfHousejTable.getSelectedRow();
				LinkedList<Casa> homes = new LinkedList<Casa>();
				try {
					homes = ServicioCasa.getHomes();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Casa h = homes.get(pos);
				selected=h;
				NumberjTextField.setText(String.valueOf(h.getNumero()));
				habitantesjTextField.setText(String.valueOf(h.gethabitantes()));
				addressTextArea.setText(h.getDireccion());
			    ConsumosjButton.setEnabled(true);
				DeletejButton.setEnabled(true);
				ModifyjButton.setEnabled(true);
				InsertjButton.setEnabled(false);
				ConsumosjButton.setEnabled(true);
				NuevojButton.setEnabled(true);
				NumberjTextField.setEditable(false);
				addressTextArea.setEditable(false);
				CobradorjButton.setEnabled(true);				
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
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getDatesOfHousejPanel(), null);
			jContentPane.add(getTablejPanel2(), null);
			jContentPane.add(getModifyjButton(), null);
			jContentPane.add(getDeletejButton(), null);
			jContentPane.add(getInsertjButton(), null);
			jContentPane.add(getClosejButton(), null);
			jContentPane.add(getNuevojButton(), null);
			jContentPane.add(getConsumosjButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes DatesOfHousejPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDatesOfHousejPanel() {
		if (DatesOfHousejPanel == null) {			
			addressLabel = new JLabel();
			addressLabel.setBounds(new Rectangle(176, 14, 141, 25));
			addressLabel.setText("Direccion:");
			quantityOfInhabitantsjLabel = new JLabel();
			quantityOfInhabitantsjLabel.setBounds(new Rectangle(342, 14, 141, 25));
			quantityOfInhabitantsjLabel.setText(" Habitantes:");
			NumberjLabel = new JLabel();
			NumberjLabel.setBounds(new Rectangle(21, 14, 141, 25));
			NumberjLabel.setText("Número:");
			DatesOfHousejPanel = new JPanel();
			DatesOfHousejPanel.setLayout(null);
			DatesOfHousejPanel.setBounds(new Rectangle(7, 4, 561, 134));
			DatesOfHousejPanel.setBorder(BorderFactory.createTitledBorder(null, "Datos de la casa", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			DatesOfHousejPanel.add(NumberjLabel, null);
			DatesOfHousejPanel.add(getNumberjTextField(), null);
			DatesOfHousejPanel.add(quantityOfInhabitantsjLabel, null);
			DatesOfHousejPanel.add(getAddressScrollPane(), null);
			DatesOfHousejPanel.add(addressLabel, null);
			DatesOfHousejPanel.add(getHabitantesjTextField(), null);
			DatesOfHousejPanel.add(getCobradorjButton(), null);
		}
		return DatesOfHousejPanel;
	}

	/**
	 * This method initializes NumberjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNumberjTextField() {
		if (NumberjTextField == null) {
			NumberjTextField = new JTextField();
			NumberjTextField.setBounds(new Rectangle(21, 53, 141, 25));
			NumberjTextField.setText("");			
			NumberjTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					if(NumberjTextField.getText().length() > 5){
						e.consume();
						getToolkit().beep();
					}
				}
			});
			Validate.validateDigit(NumberjTextField);
			}		
					
		return NumberjTextField;
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
			tablejPanel.setBounds(new Rectangle(7, 145, 561, 161));
			tablejPanel.add(getListOfHousejScrollPane(), null);
		}
		return tablejPanel;
	}

	/**
	 * This method initializes listOfHousejScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getListOfHousejScrollPane() {
		if (listOfHousejScrollPane == null) {
			listOfHousejScrollPane = new JScrollPane();
			listOfHousejScrollPane.setBounds(new Rectangle(6, 6, 550, 147));
			listOfHousejScrollPane.setViewportView(getListOfHousejTable());
		}
		return listOfHousejScrollPane;
	}

	/**
	 * This method initializes listOfHousejTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getListOfHousejTable() {
		if (listOfHousejTable == null) {
			listOfHousejTable = new JTable();
			}
		return listOfHousejTable;
	}

	/**
	 * This method initializes ModifyjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getModifyjButton() {
		if (ModifyjButton == null) {
			ModifyjButton = new JButton();
			ModifyjButton.setBounds(new Rectangle(586, 71, 104, 27));
			ModifyjButton.setText("Modificar");
			ModifyjButton.setEnabled(false);
			ModifyjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
					int result = JOptionPane.showConfirmDialog(CasasVisual.this, "¿Are you sure you want modify this home?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					int pos = listOfHousejTable.getSelectedRow();
					try {
						LinkedList<Casa> casas = ServicioCasa.getHomes();
						Casa cas = casas.get(pos);
						ServicioCasa.ModificarCasa(cas.getIdentificador(), Integer.valueOf(NumberjTextField.getText()), addressTextArea.getText(), Integer.valueOf(habitantesjTextField.getText()),cas.getConsumoAlterado(),cas.getCobrador());
						JOptionPane.showMessageDialog(CasasVisual.this, "Home Modified", "Information", JOptionPane.INFORMATION_MESSAGE);
						InsertjButton.setEnabled(true);
						DefaultTableModel defaultTableModel = new DefaultTableModel();
						LinkedList<Casa> list = new LinkedList<Casa>();
						try {
							list = ServicioCasa.getHomes();
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (ClassNotFoundException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						ArrayList<Object> columnDataNumero = new ArrayList<Object>();
						ArrayList<Object> columnDataDireccion = new ArrayList<Object>();
						ArrayList<Object> columnDataHab = new ArrayList<Object>();
						for (int i = 0; i < list.size(); i++) {
							columnDataNumero.add(list.get(i).getNumero());
							columnDataDireccion.add(list.get(i).getDireccion());
							columnDataHab.add(list.get(i).gethabitantes());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Number",columnDataNumero.toArray());
						defaultTableModel.addColumn("Address",columnDataDireccion.toArray());
						defaultTableModel.addColumn(" Quantity of Habitants",columnDataHab.toArray());
						getListOfHousejTable().setModel(defaultTableModel);
						UserInterfaceSuport.clearComponents(jContentPane);
						} catch (SQLException e1) {
						JOptionPane.showMessageDialog(CasasVisual.this, "You cant'n insert two iquals homes", "Error", JOptionPane.ERROR_MESSAGE);
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					InsertjButton.setEnabled(true);
					DeletejButton.setEnabled(false);
					ModifyjButton.setEnabled(false);
					ConsumosjButton.setEnabled(false);
					NuevojButton.setEnabled(false);
					addressTextArea.setText("");
					NumberjTextField.setText("");
					habitantesjTextField.setText("");
					addressTextArea.setEditable(true);
					NumberjTextField.setEditable(true);
					CobradorjButton.setEnabled(false);
					}
					}else{
						int result = JOptionPane.showConfirmDialog(CasasVisual.this, "¿Está seguro que desea modificar la casa?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						if(result == JOptionPane.YES_OPTION){
						int pos = listOfHousejTable.getSelectedRow();
						try {
							LinkedList<Casa> casas = ServicioCasa.getHomes();
							Casa cas = casas.get(pos);
							ServicioCasa.ModificarCasa(cas.getIdentificador(), Integer.valueOf(NumberjTextField.getText()), addressTextArea.getText(), Integer.valueOf(habitantesjTextField.getText()),cas.getConsumoAlterado(),cas.getCobrador());
							JOptionPane.showMessageDialog(CasasVisual.this, "Casa Modificada", "Información", JOptionPane.INFORMATION_MESSAGE);
							InsertjButton.setEnabled(true);
							DefaultTableModel defaultTableModel = new DefaultTableModel();
							LinkedList<Casa> list = new LinkedList<Casa>();
							try {
								list = ServicioCasa.getHomes();
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (ClassNotFoundException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							ArrayList<Object> columnDataNumero = new ArrayList<Object>();
							ArrayList<Object> columnDataDireccion = new ArrayList<Object>();
							ArrayList<Object> columnDataHab = new ArrayList<Object>();
							for (int i = 0; i < list.size(); i++) {
								columnDataNumero.add(list.get(i).getNumero());
								columnDataDireccion.add(list.get(i).getDireccion());
								columnDataHab.add(list.get(i).gethabitantes());
							}
							defaultTableModel.setRowCount(list.size());
							defaultTableModel.addColumn("Numero",columnDataNumero.toArray());
							defaultTableModel.addColumn("Direccion",columnDataDireccion.toArray());
							defaultTableModel.addColumn("Cantidad de Habitantes",columnDataHab.toArray());
							getListOfHousejTable().setModel(defaultTableModel);
							} catch (SQLException e1) {
							JOptionPane.showMessageDialog(CasasVisual.this, "No se puede insertar dos casas iguales", "Error", JOptionPane.ERROR_MESSAGE);
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						InsertjButton.setEnabled(true);
						DeletejButton.setEnabled(false);
						ModifyjButton.setEnabled(false);
						ConsumosjButton.setEnabled(false);
						NuevojButton.setEnabled(false);
						addressTextArea.setText("");
						NumberjTextField.setText("");
						habitantesjTextField.setText("");
						addressTextArea.setEditable(true);
						CobradorjButton.setEnabled(false);
						NumberjTextField.setEditable(true);
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
			DeletejButton.setBounds(new Rectangle(586, 120, 104, 27));
			DeletejButton.setText(" Eliminar");
			DeletejButton.setEnabled(false);
			DeletejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
					int result = JOptionPane.showConfirmDialog(CasasVisual.this, "¿Are you sure you want delete this home?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					int pos = listOfHousejTable.getSelectedRow();
					try {
						LinkedList<Casa> casas = ServicioCasa.getHomes();
						Casa cas = casas.get(pos);
						ServicioCasa.EliminarCasa(cas.getIdentificador());
						JOptionPane.showMessageDialog(CasasVisual.this, "Home deleted", "Information", JOptionPane.INFORMATION_MESSAGE);
						InsertjButton.setEnabled(true);
						DefaultTableModel defaultTableModel = new DefaultTableModel();
						LinkedList<Casa> list = new LinkedList<Casa>();
						try {
							list = ServicioCasa.getHomes();
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (ClassNotFoundException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						ArrayList<Object> columnDataNumero = new ArrayList<Object>();
						ArrayList<Object> columnDataDireccion = new ArrayList<Object>();
						ArrayList<Object> columnDataHab = new ArrayList<Object>();
						for (int i = 0; i < list.size(); i++) {
							columnDataNumero.add(list.get(i).getNumero());
							columnDataDireccion.add(list.get(i).getDireccion());
							columnDataHab.add(list.get(i).gethabitantes());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Number",columnDataNumero.toArray());
						defaultTableModel.addColumn("Address",columnDataDireccion.toArray());
						defaultTableModel.addColumn(" Quantity of Habitants",columnDataHab.toArray());
						getListOfHousejTable().setModel(defaultTableModel);
						UserInterfaceSuport.clearComponents(getJContentPane());
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(CasasVisual.this, " There are relations with this home", "Error", JOptionPane.ERROR_MESSAGE);
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
					ConsumosjButton.setEnabled(false);
					addressTextArea.setText("");
					NumberjTextField.setText("");
					habitantesjTextField.setText("");
					addressTextArea.setEditable(true);
					NumberjTextField.setEditable(true);
					CobradorjButton.setEnabled(false);
				}
					}else{
						int result = JOptionPane.showConfirmDialog(CasasVisual.this, "¿Está seguro que desea eliminar la casa?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						if(result == JOptionPane.YES_OPTION){
						int pos = listOfHousejTable.getSelectedRow();
						try {
							LinkedList<Casa> casas = ServicioCasa.getHomes();
							Casa cas = casas.get(pos);
							ServicioCasa.EliminarCasa(cas.getIdentificador());
							JOptionPane.showMessageDialog(CasasVisual.this, "Casa Eliminada", "Información", JOptionPane.INFORMATION_MESSAGE);
							InsertjButton.setEnabled(true);
							DefaultTableModel defaultTableModel = new DefaultTableModel();
							LinkedList<Casa> list = new LinkedList<Casa>();
							try {
								list = ServicioCasa.getHomes();
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (ClassNotFoundException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							ArrayList<Object> columnDataNumero = new ArrayList<Object>();
							ArrayList<Object> columnDataDireccion = new ArrayList<Object>();
							ArrayList<Object> columnDataHab = new ArrayList<Object>();
							for (int i = 0; i < list.size(); i++) {
								columnDataNumero.add(list.get(i).getNumero());
								columnDataDireccion.add(list.get(i).getDireccion());
								columnDataHab.add(list.get(i).gethabitantes());
								}
							defaultTableModel.setRowCount(list.size());
							defaultTableModel.addColumn("Numero",columnDataNumero.toArray());
							defaultTableModel.addColumn("Direccion",columnDataDireccion.toArray());
							defaultTableModel.addColumn("Cantidad de Habitantes",columnDataHab.toArray());
							getListOfHousejTable().setModel(defaultTableModel);
							UserInterfaceSuport.clearComponents(getJContentPane());
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(CasasVisual.this, "Existen relaciones con esta Casa", "Error", JOptionPane.ERROR_MESSAGE);
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
						ConsumosjButton.setEnabled(false);
						addressTextArea.setText("");
						NumberjTextField.setText("");
						habitantesjTextField.setText("");
						addressTextArea.setEditable(true);
						NumberjTextField.setEditable(true);
						CobradorjButton.setEnabled(false);
					}
						
					}
					}});
				}
		return DeletejButton;
	}

	/**
	 * This method initializes addressScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getAddressScrollPane() {
		if (addressScrollPane == null) {
			addressScrollPane = new JScrollPane();
			addressScrollPane.setBounds(new Rectangle(175, 53, 146, 54));
			addressScrollPane.setViewportView(getAddressTextArea());
			}
		return addressScrollPane;
	}

	/**
	 * This method initializes addressTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getAddressTextArea() {
		if (addressTextArea == null) {
			addressTextArea = new JTextArea();
			
		}
		return addressTextArea;
	}

	/**
	 * This method initializes InsertjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getInsertjButton() {
		if (InsertjButton == null) {
			InsertjButton = new JButton();
			InsertjButton.setBounds(new Rectangle(586, 22, 104, 27));
			InsertjButton.setText("Insertar");
			InsertjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(NumberjTextField.getText().length() > 0 && habitantesjTextField.getText().length() > 0 && addressTextArea.getText().length() > 0){
						try {
							Casa h = new Casa();
						ServicioCasa.insertarCasa(h.getIdentificador(), Integer.valueOf(getNumberjTextField().getText()), getAddressTextArea().getText(), Integer.valueOf(getHabitantesjTextField().getText()));

						DefaultTableModel defaultTableModel = new DefaultTableModel();
						LinkedList<Casa> list = new LinkedList<Casa>();
						try {
							list = ServicioCasa.getHomes();
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (ClassNotFoundException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						ArrayList<Object> columnDataNumero = new ArrayList<Object>();
						ArrayList<Object> columnDataDireccion = new ArrayList<Object>();
						ArrayList<Object> columnDataHab = new ArrayList<Object>();
						for (int i = 0; i < list.size(); i++) {
							columnDataNumero.add(list.get(i).getNumero());
							columnDataDireccion.add(list.get(i).getDireccion());
							columnDataHab.add(list.get(i).gethabitantes());
						}
						defaultTableModel.setRowCount(list.size());
						if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
						defaultTableModel.addColumn("Number",columnDataNumero.toArray());
						defaultTableModel.addColumn("Address",columnDataDireccion.toArray());
						defaultTableModel.addColumn(" Quantity of Habitants",columnDataHab.toArray());
						}else{
							defaultTableModel.addColumn("Numero",columnDataNumero.toArray());
							defaultTableModel.addColumn("Direccion",columnDataDireccion.toArray());
							defaultTableModel.addColumn("Cantidad de Habitantes",columnDataHab.toArray());
							}
						getListOfHousejTable().setModel(defaultTableModel);
							if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
							JOptionPane.showMessageDialog(CasasVisual.this, "Home Inserted", "Information", JOptionPane.INFORMATION_MESSAGE);
							}
							else{
								JOptionPane.showMessageDialog(CasasVisual.this, "Casa insertada", "Informacion", JOptionPane.INFORMATION_MESSAGE);	
							}
							UserInterfaceSuport.clearComponents(jContentPane);
							
						} catch (SQLException e1) {
							if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
						JOptionPane.showMessageDialog(CasasVisual.this, "You can't insert two iquals Homes", "Error", JOptionPane.ERROR_MESSAGE);
							}else{
								JOptionPane.showMessageDialog(CasasVisual.this, "No se puede insertar dos Casas iguales", "Error", JOptionPane.ERROR_MESSAGE);
							}
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					addressTextArea.setText("");
					NumberjTextField.setText("");
					habitantesjTextField.setText("");
					DeletejButton.setEnabled(false);
					ModifyjButton.setEnabled(false);
					InsertjButton.setEnabled(true);
					ConsumosjButton.setEnabled(false);
					NuevojButton.setEnabled(false);
					addressTextArea.setEditable(true);
					NumberjTextField.setEditable(true);
					CobradorjButton.setEnabled(false);
					}
					else
						if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
						JOptionPane.showMessageDialog(CasasVisual.this, "Empty Fields", "Error", JOptionPane.ERROR_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(CasasVisual.this, "Campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);	
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
			closejButton.setBounds(new Rectangle(586, 267, 104, 27));
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
	private JTextField getHabitantesjTextField() {
		if (habitantesjTextField == null) {
			habitantesjTextField = new JTextField();
			habitantesjTextField.setBounds(new Rectangle(342, 53, 141, 25));
			habitantesjTextField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent e) {
				if(habitantesjTextField.getText().length() > 1){
					e.consume();
					getToolkit().beep();
				}
			}
		});
		Validate.validateDigit(habitantesjTextField);
		}
		return habitantesjTextField;
	}

	/**
	 * This method initializes NuevojButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNuevojButton() {
		if (NuevojButton == null) {
			NuevojButton = new JButton();
			NuevojButton.setBounds(new Rectangle(586, 169, 104, 27));
			NuevojButton.setText("Nuevo");
			NuevojButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					NumberjTextField.setText("");
					habitantesjTextField.setText("");
					addressTextArea.setText("");
					InsertjButton.setEnabled(true);
					ModifyjButton.setEnabled(false);
					DeletejButton.setEnabled(false);	
					ConsumosjButton.setEnabled(false);
					NuevojButton.setEnabled(false);
					NumberjTextField.setEditable(true);
					addressTextArea.setEditable(true);
					CobradorjButton.setEnabled(false);
					}			     	
				});
		}
		return NuevojButton;
	}

	/**
	 * This method initializes ConsumosjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getConsumosjButton() {
		if (ConsumosjButton == null) {
			ConsumosjButton = new JButton();
			ConsumosjButton.setBounds(new Rectangle(586, 218, 104, 27));
			ConsumosjButton.setText("Consumos");
			ConsumosjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					NumberjTextField.setText("");
					habitantesjTextField.setText("");
					addressTextArea.setText("");
					InsertjButton.setEnabled(true);
					ModifyjButton.setEnabled(false);
					DeletejButton.setEnabled(false);	
					CobradorjButton.setEnabled(false);
					NuevojButton.setEnabled(false);
					addressTextArea.setEditable(true);
					NumberjTextField.setEditable(true);
					LecturasVisual c = new LecturasVisual(CasasVisual.this,true,selected);
					c.setVisible(true);
					c.idioma();
				}
			});
			ConsumosjButton.setEnabled(false);
			}
		return ConsumosjButton;
	}
	
	
	public void idioma(){
		if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
			NumberjLabel.setText("Number:");
			quantityOfInhabitantsjLabel.setText("Habitants:");
			addressLabel.setText("Address:");
			ModifyjButton.setText("Modify");
			DeletejButton.setText("Delete");
			InsertjButton.setText("Insert");
			closejButton.setText("Cancel");
			CobradorjButton.setText("Assign Collector");
			NuevojButton.setText("New");
			ConsumosjButton.setText("Consumptions");
       		DatesOfHousejPanel.setBorder(BorderFactory.createTitledBorder(null, "Home Information", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			this.setTitle("Home's management");
			}
		else{
			NumberjLabel.setText("Número:");
			quantityOfInhabitantsjLabel.setText("Habitantes:");
			addressLabel.setText("Dirección:");
			ModifyjButton.setText("Modificar");
			DeletejButton.setText("Eliminar");
			InsertjButton.setText("Insertar");
			CobradorjButton.setText("Asignar Cobrador");
			closejButton.setText("Cerrar");
			NuevojButton.setText("Nuevo");
			ConsumosjButton.setText("Consumos");
			DatesOfHousejPanel.setBorder(BorderFactory.createTitledBorder(null, "Datos de la casa", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			this.setTitle("Trabajo con una Casa");
			
			}
	}

	/**
	 * This method initializes CobradorjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCobradorjButton() {
		if (CobradorjButton == null) {
			CobradorjButton = new JButton();
			CobradorjButton.setBounds(new Rectangle(342, 92, 141, 25));
			CobradorjButton.setText("Asignar Cobrador");
			CobradorjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					NumberjTextField.setText("");
					habitantesjTextField.setText("");
					addressTextArea.setText("");
					InsertjButton.setEnabled(true);
					ModifyjButton.setEnabled(false);
					DeletejButton.setEnabled(false);	
					ConsumosjButton.setEnabled(false);
					NuevojButton.setEnabled(false);
					addressTextArea.setEditable(true);
					NumberjTextField.setEditable(true);
					AsignarCobrador  c = new AsignarCobrador(CasasVisual.this,true,selected);
					c.setVisible(true);
					c.idioma();
				}
			});
			CobradorjButton.setEnabled(false);
		}
		return CobradorjButton;
	}
}  //  @jve:decl-index=0:visual-constraint="16,7"
