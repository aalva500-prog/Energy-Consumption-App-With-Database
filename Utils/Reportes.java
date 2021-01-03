package Utils;



import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;

public class Reportes {

	
	
	private java.sql.Connection myConnection = null;
       static  Reportes report;
	
	public Reportes() {
		super();
		try {
			this.myConnection = ConnectionBD.connect.getConnection();
			Class.forName("org.postgresql.Driver");
			 myConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/consumo", "postgres", "postgres");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public static Reportes getR()
	{
		if(report == null)
		{
			report = new Reportes();
		}
		return report;
			
	}
	
	public void Contador(){
		try {
			JasperFillManager.fillReportToFile("reportes/contadoresyLecturas.jasper", null, myConnection);
			JasperViewer.viewReport("reportes/contadoresyLecturas.jrprint", false,false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void CargarLecturasConValor(float valor, String cod){
		try {
			//Class.forName("org.postgresql.Driver");
			//java.sql.Connection myConnection = ConnectionBD.connect.getConnection();
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("valor", valor);
			mypara.put("casa", cod);			
			JasperFillManager.fillReportToFile("reportes/LecturasPorEncimaValor.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/LecturasPorEncimaValor.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
		
	
	
	public void ReporteCasasAlteradas(){
		try {
			JasperFillManager.fillReportToFile("reportes/casasAlteradas.jasper", null, myConnection);
			JasperViewer.viewReport("reportes/casasAlteradas.jrprint", false,false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void MayoresConsumidores(){
		try {
			JasperFillManager.fillReportToFile("reportes/mayoresconsumidores.jasper", null, myConnection);
			JasperViewer.viewReport("reportes/mayoresconsumidores.jrprint", false,false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
