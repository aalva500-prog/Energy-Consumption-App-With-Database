package Services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import model.Lectura;
import Utils.ConnectionBD;

public class ServicioLectura {

	public  static LinkedList<Lectura> getLecturas() throws SQLException, ClassNotFoundException{
		LinkedList<Lectura> lecturas = new LinkedList<Lectura>();
		Statement consulta = ConnectionBD.connect.getConnection().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sqlSentenc = " SELECT \"public\".\"Lectura\".\"identificador\",\"public\".\"Lectura\".\"fecha\",\"public\".\"Lectura\".\"consumoInicial\",\"public\".\"Lectura\".\"consumoFinal\"" +
		"FROM  \"public\".\"Lectura\"" ;
		ResultSet resultado = consulta.executeQuery(sqlSentenc);
		while (resultado.next()) {
			Lectura a = new Lectura();
			a.setIdentificador(resultado.getString(1));
			a.setFecha(resultado.getString(2));	
			a.setRegistroInicial(resultado.getFloat(3));
			a.setRegistroFinal(resultado.getFloat(4));
			lecturas.add(a);
		}
		return lecturas;
	}
	
	public static LinkedList<Lectura> getCons (String cas){
		LinkedList<Lectura> consumos = new LinkedList<Lectura>();
		String sentence = " SELECT \"public\".\"Lectura\".\"identificador\",\"public\".\"Lectura\".\"fecha\",\"public\".\"Lectura\".\"consumoInicial\",\"public\".\"Lectura\".\"consumoFinal\"" +
		"FROM  \"public\".\"Lectura\" WHERE \"public\".\"Lectura\".\"casa\" = ?" ;
		try {
			PreparedStatement stat = ConnectionBD.connect.getConnection().prepareStatement(sentence);
			stat.setString(1, cas);
			stat.execute();
			ResultSet result = stat.getResultSet();
			while (result.next()) {
				Lectura a = new Lectura();
				a.setIdentificador(result.getString(1));
				a.setFecha(result.getString(2));	
				a.setRegistroInicial(result.getFloat(3));
				a.setRegistroFinal(result.getFloat(4));
				consumos.add(a);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return consumos;
	}

	
	public static void insertarConsumo(String fech,float consI, float consF, String ide, String cas) throws SQLException, ClassNotFoundException{
		String sqlSentenc = "SELECT\"public\".\"InsertarLectura\" (?,?,?,?,?)"; /*Los símbolos ? indican los parámetros que se van a pasar */
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setString(1, fech); /* estamos dándole valor al primer parametro que se pasa, es decir al primer ? que aparezca. */
		prepararCons.setFloat(2,consI);
		prepararCons.setFloat(3, consF);
		prepararCons.setString(4,ide);
		prepararCons.setString(5,cas);
		prepararCons.execute();
	}
	
	
	public static void  EliminarConsumo(String ide) throws SQLException, ClassNotFoundException { 
		
		String sqlSentenc = "SELECT \"public\".\"EliminarLectura\"(?)" ; 
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setString(1,ide); 
		prepararCons.execute();			
	}
	

public static void  ModificarConsumo(String ide,float I,float f) throws SQLException, ClassNotFoundException { 
	
	String sqlSentenc = "SELECT \"public\".\"ModificarLectura\"(?,?,?)" ; 
	PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
	prepararCons.setString(1,ide);
	prepararCons.setFloat(2,I); 
	prepararCons.setFloat(3,f);
	prepararCons.execute();
	
}

}



