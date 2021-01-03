package Visuals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.Casa;
import Services.ServicioCasa;
import Utils.Reportes;
import Utils.Validate;

public class ReporteLecturaValor extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel jPanel = null;

	private JComboBox jComboBox = null;

	private JButton jButton = null;

	private DefaultComboBoxModel defaultComboBoxModel = null;  //  @jve:decl-index=0:visual-constraint="741,122"

	private JTextField jValorTextField = null;

	/**
	 * This is the default constructor
	 */
	public ReporteLecturaValor() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(333, 154);
		this.setContentPane(getJContentPane());
		this.setTitle("Mostrar Plan Metodologico");
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
			jContentPane.setLayout(null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(11, 15, 294, 63));
			jPanel.setBorder(BorderFactory.createTitledBorder(null, "Seleccione la casa         Introduzca Valor", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel.add(getJComboBox(), null);
			jPanel.add(getJValorTextField(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.setBounds(new Rectangle(8, 26, 136, 25));
			jComboBox.setModel(getDefaultComboBoxModel());
			defaultComboBoxModel.addElement(new String ("<Seleccione>"));
			LinkedList<Casa> list = new LinkedList<Casa>();
			try {
				list = ServicioCasa.getHomes();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Casa casa : list) {
				defaultComboBoxModel.addElement(casa.getNumero());
			}
		}
		return jComboBox;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(101, 86, 85, 19));
			jButton.setText("Mostrar");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int pos = jComboBox.getSelectedIndex();
					LinkedList<Casa> list = new LinkedList<Casa>();
					try {
						list = ServicioCasa.getHomes();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String cod = list.get(pos - 1).getIdentificador();
					float valor =Float.valueOf(jValorTextField.getText());					
					Reportes.getR().CargarLecturasConValor(valor,cod);
					dispose();
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes defaultComboBoxModel	
	 * 	
	 * @return javax.swing.DefaultComboBoxModel	
	 */
	private DefaultComboBoxModel getDefaultComboBoxModel() {
		if (defaultComboBoxModel == null) {
			defaultComboBoxModel = new DefaultComboBoxModel();
		}
		return defaultComboBoxModel;
	}

	/**
	 * This method initializes jValorTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJValorTextField() {
		if (jValorTextField == null) {
			jValorTextField = new JTextField();
			jValorTextField.setBounds(new Rectangle(188, 26, 83, 26));
			Validate.validateDigitAndComma(jValorTextField);
		}
		return jValorTextField;
	}

}  //  @jve:decl-index=0:visual-constraint="219,133"
