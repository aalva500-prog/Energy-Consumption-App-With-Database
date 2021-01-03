package Visuals;

import java.awt.Frame;
import java.awt.Rectangle;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Casa;
import model.Cobrador;
import Services.ServicioCasa;
import Utils.Idioma;
import Utils.UserInterfaceSuport;
import Utils.Validate;

public class CasasCobrador extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel tablejPanel = null;

	private JScrollPane listOfHousejScrollPane = null;

	private JTable listOfHousejTable = null;

	private JButton closejButton = null;
	private Cobrador cobrador=null;

	private JLabel ConsumojLabel = null;

	private JTextField ConsumojTextField = null;

	private JButton AsignarjButton = null;
	private Casa casa = null;

	/**
	 * @param owner
	 */
	public CasasCobrador(JDialog dialog,boolean modal,Cobrador cobrador) {
		super(dialog,modal);
		this.cobrador = cobrador;
		initialize();
		idioma();
		}

	/**
	 * @param owner
	 */
	public CasasCobrador(Frame owner) {
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
		this.setSize(791, 215);
		this.setTitle("Casas del Cobrador");
		this.setContentPane(getJContentPane());
		UserInterfaceSuport.centerComponent(this);
		AsignarjButton.setEnabled(false);
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		LinkedList<Casa> list = new LinkedList<Casa>();
		list = ServicioCasa.getHomes1(cobrador.getIdentificador());
		ArrayList<Object> columnDataNumero = new ArrayList<Object>();
		ArrayList<Object> columnDataDireccion = new ArrayList<Object>();
		ArrayList<Object> columnDataHab = new ArrayList<Object>();
		ArrayList<Object> columnDataAltered = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			columnDataNumero.add(list.get(i).getNumero());
			columnDataDireccion.add(list.get(i).getDireccion());
			columnDataHab.add(list.get(i).gethabitantes());
			columnDataAltered.add(list.get(i).getConsumoAlterado());
			}
		defaultTableModel.setRowCount(list.size());
		if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
		defaultTableModel.addColumn("Number",columnDataNumero.toArray());
		defaultTableModel.addColumn("Address",columnDataDireccion.toArray());
		defaultTableModel.addColumn("Quantity of Habitants",columnDataHab.toArray());
		defaultTableModel.addColumn("Collector Consumption",columnDataAltered.toArray());
		}else{
			defaultTableModel.addColumn("Numero",columnDataNumero.toArray());
			defaultTableModel.addColumn("Direccion",columnDataDireccion.toArray());
			defaultTableModel.addColumn("Cantidad de Habitantes",columnDataHab.toArray());
			defaultTableModel.addColumn("Consumo del Cobrador",columnDataAltered.toArray());
		}
		getListOfHousejTable().setModel(defaultTableModel);
		
		listOfHousejTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int pos = listOfHousejTable.getSelectedRow();
				LinkedList<Casa> homes = new LinkedList<Casa>();
				homes = ServicioCasa.getHomes1(cobrador.getIdentificador());
				Casa h = homes.get(pos);				
				casa=h;		
				ConsumojTextField.setText(String.valueOf(h.getConsumoAlterado()));
				AsignarjButton.setEnabled(true);
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
			ConsumojLabel = new JLabel();
			ConsumojLabel.setBounds(new Rectangle(581, 13, 154, 27));
			ConsumojLabel.setText("Consumo del Cobrador:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getTablejPanel2(), null);
			jContentPane.add(getClosejButton(), null);
			jContentPane.add(ConsumojLabel, null);
			jContentPane.add(getConsumojTextField(), null);
			jContentPane.add(getAsignarjButton(), null);
		}
		return jContentPane;
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
			tablejPanel.setBounds(new Rectangle(5, 5, 565, 161));
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
			listOfHousejScrollPane.setBounds(new Rectangle(6, 7, 554, 147));
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
	 * This method initializes closejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getClosejButton() {
		if (closejButton == null) {
			closejButton = new JButton();
			closejButton.setBounds(new Rectangle(600, 133, 116, 27));
			closejButton.setText("Cerrar");
			closejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return closejButton;
	}

	public void idioma(){
		if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
			closejButton.setText("Cancel");
			this.setTitle("Collector Homes");
			AsignarjButton.setText("Assign");
			ConsumojLabel.setText("Collector Consumption:");
			}
		else{
			closejButton.setText("Cerrar");
			this.setTitle("Casas del Cobrador");
			AsignarjButton.setText("Asignar");
			ConsumojLabel.setText("Consumo del Cobrador:");
			}
	}

	/**
	 * This method initializes ConsumojTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getConsumojTextField() {
		if (ConsumojTextField == null) {
			ConsumojTextField = new JTextField();
			ConsumojTextField.setBounds(new Rectangle(581, 53, 154, 27));
			Validate.validateDigitAndComma(ConsumojTextField);
		}
		return ConsumojTextField;
	}

	/**
	 * This method initializes AsignarjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAsignarjButton() {
		if (AsignarjButton == null) {
			AsignarjButton = new JButton();
			AsignarjButton.setBounds(new Rectangle(600, 93, 116, 27));
			AsignarjButton.setText("Asignar");
			AsignarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						ServicioCasa.ModificarCasa2(casa.getIdentificador(), Float.valueOf(ConsumojTextField.getText()));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					DefaultTableModel defaultTableModel = new DefaultTableModel();
					LinkedList<Casa> list = new LinkedList<Casa>();
					list = ServicioCasa.getHomes1(cobrador.getIdentificador());
					ArrayList<Object> columnDataNumero = new ArrayList<Object>();
					ArrayList<Object> columnDataDireccion = new ArrayList<Object>();
					ArrayList<Object> columnDataHab = new ArrayList<Object>();
					ArrayList<Object> columnDataAltered = new ArrayList<Object>();
					for (int i = 0; i < list.size(); i++) {
						columnDataNumero.add(list.get(i).getNumero());
						columnDataDireccion.add(list.get(i).getDireccion());
						columnDataHab.add(list.get(i).gethabitantes());
						columnDataAltered.add(list.get(i).getConsumoAlterado());
						}
					defaultTableModel.setRowCount(list.size());
					if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
					defaultTableModel.addColumn("Number",columnDataNumero.toArray());
					defaultTableModel.addColumn("Address",columnDataDireccion.toArray());
					defaultTableModel.addColumn(" Quantity of Habitants",columnDataHab.toArray());
					defaultTableModel.addColumn("Collector Consumption",columnDataAltered.toArray());
					}else{
						defaultTableModel.addColumn("Numero",columnDataNumero.toArray());
						defaultTableModel.addColumn("Direccion",columnDataDireccion.toArray());
						defaultTableModel.addColumn("Cantidad de Habitantes",columnDataHab.toArray());
						defaultTableModel.addColumn("Consumo del Cobrador",columnDataAltered.toArray());
					}
					getListOfHousejTable().setModel(defaultTableModel);
					ConsumojTextField.setText("");
					}	
					});
		}
		return AsignarjButton;
	}
}  //  @jve:decl-index=0:visual-constraint="16,7"
