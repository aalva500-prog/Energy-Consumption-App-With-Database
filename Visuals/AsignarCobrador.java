package Visuals;

	import java.awt.Frame;
import java.awt.Rectangle;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Casa;
import model.Cobrador;
import Services.ServicioCasa;
import Services.ServicioCobrador;
import Utils.Idioma;
import Utils.UserInterfaceSuport;

	public class AsignarCobrador extends JDialog {

		private static final long serialVersionUID = 1L;

		private JPanel jContentPane = null;

		private JButton insertjButton = null;

		private JPanel consuptionjPanel = null;

		private JComboBox CobradorjComboBox = null;

		private JLabel CobradorjLabel = null;
		private Casa home = null;

		/**
		 * @param owner
		 */
		public AsignarCobrador(Frame owner) {
			super(owner);
			initialize();
			idioma();
			}
		
		public AsignarCobrador(JDialog dialog,boolean modal,Casa home) {
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
			this.setSize(224, 202);
			this.setTitle("Consumo Diario");
			UserInterfaceSuport.centerComponent(this);
			this.setContentPane(getJContentPane());
			//Combobox Cobrador
			LinkedList<Cobrador> list1 = new LinkedList<Cobrador>();
			try {
				list1 = ServicioCobrador.getCobradores();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			DefaultComboBoxModel boxModel9 = new DefaultComboBoxModel();
			if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
				boxModel9.addElement(new String("<Select>"));
				}else{
					boxModel9.addElement(new String("<Seleccione>"));
				}
			for (int i = 0; i < list1.size(); i++) {
				boxModel9.addElement(list1.get(i));
			}
			
			getCobradorjComboBox().setModel(boxModel9);
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
				jContentPane.add(getInsertjButton(), null);
				jContentPane.add(getConsuptionjPanel(), null);
			}
			return jContentPane;
		}

		/**
		 * This method initializes insertjButton	
		 * 	
		 * @return javax.swing.JButton	
		 */
		private JButton getInsertjButton() {
			if (insertjButton == null) {
				insertjButton = new JButton();
				insertjButton.setBounds(new Rectangle(61, 131, 91, 22));
				insertjButton.setText("Asignar");
				insertjButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						if(!(CobradorjComboBox.getSelectedIndex()==0)){
						try {							
							ServicioCasa.ModificarCasa1(home.getIdentificador(), ((Cobrador)getCobradorjComboBox().getSelectedItem()).getIdentificador());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dispose();
						}else{
						if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
							JOptionPane.showMessageDialog(AsignarCobrador.this, "You must select one Collector", "Error", JOptionPane.ERROR_MESSAGE);
								}else{
									JOptionPane.showMessageDialog(AsignarCobrador.this, "Debe seleccionar un cobrador", "Error", JOptionPane.ERROR_MESSAGE);
								}
						}	
					}
					
				});
				}	
			return insertjButton;
		}

		/**
		 * This method initializes consuptionjPanel	
		 * 	
		 * @return javax.swing.JPanel	
		 */
		private JPanel getConsuptionjPanel() {
			if (consuptionjPanel == null) {
				CobradorjLabel = new JLabel();
				CobradorjLabel.setBounds(new Rectangle(17, 11, 119, 25));
				CobradorjLabel.setText("Cobrador:");
				consuptionjPanel = new JPanel();
				consuptionjPanel.setLayout(null);
				consuptionjPanel.setBounds(new Rectangle(16, 9, 177, 112));
				consuptionjPanel.add(getCobradorjComboBox(), null);
				consuptionjPanel.add(CobradorjLabel, null);
				}
			return consuptionjPanel;
		}

		public void idioma(){
			if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
				insertjButton.setText("Assign");
				CobradorjLabel.setText("Collector:");
				this.setTitle("Assign Collector");
				}
			else{
				insertjButton.setText("Asignar");
				CobradorjLabel.setText("Cobrador:");
				this.setTitle("Asignar Cobrador");
				}
		}

		/**
		 * This method initializes CobradorjComboBox	
		 * 	
		 * @return javax.swing.JComboBox	
		 */
		private JComboBox getCobradorjComboBox() {
			if (CobradorjComboBox == null) {
				CobradorjComboBox = new JComboBox();
				CobradorjComboBox.setBounds(new Rectangle(20, 58, 119, 25));
			}
			return CobradorjComboBox;
		}
	}  //  @jve:decl-index=0:visual-constraint="297,22"



