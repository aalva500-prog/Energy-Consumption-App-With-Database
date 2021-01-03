package Services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import model.Cobrador;
import Utils.ConnectionBD;

public class ServicioCobrador {

	public  static LinkedList<Cobrador> getCobradores() throws SQLException, ClassNotFoundException{
		LinkedList<Cobrador> cobradores = new LinkedList<Cobrador>();
		Statement consulta = ConnectionBD.connect.getConnection().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sqlSentenc = "SELECT \"public\".\"Cobrador\".\"identificador\",\"public\".\"Cobrador\".\"carnet\",\"public\".\"Cobrador\".\"nombre\",\"public\".\"Cobrador\".\"apellidos\",\"public\".\"Cobrador\".\"anos\",\"public\".\"Cobrador\".\"municipio\"" +
				"FROM  \"public\".\"Cobrador\" ";
		ResultSet resultado = consulta.executeQuery(sqlSentenc);
		while (resultado.next()) {
			Cobrador h = new Cobrador();
			h.setIdentificador(resultado.getString(1));
			h.setCarnet(resultado.getString(2));
			h.setNombre(resultado.getString(3));
			h.setApellidos(resultado.getString(4));
			h.setAnos(resultado.getInt(5));
			h.setMunicipio(resultado.getString(6));
			cobradores.add(h);
		}
		return cobradores;
	}



public static void insertarCobrador(String ide,String car, String nombre, String apell,int anos,String mun) throws SQLException, ClassNotFoundException{
	String sqlSentenc = "SELECT\"public\".\"InsertarCobrador\" (?,?,?,?,?,?)"; /*Los símbolos ? indican los parámetros que se van a pasar */
	PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
	prepararCons.setString(1, ide); /* estamos dándole valor al primer parametro que se pasa, es decir al primer ? que aparezca. */
	prepararCons.setString(2,car);
	prepararCons.setString(3, nombre);
	prepararCons.setString(4,apell);
	prepararCons.setInt(5,anos);
	prepararCons.setString(6,mun);
	prepararCons.execute();
}


public static void  EliminarCobrador(String ide) throws SQLException, ClassNotFoundException { 	
	String sqlSentenc = "SELECT \"public\".\"EliminarCobrador\"(?)" ; 
	PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
	prepararCons.setString(1,ide); 
	prepararCons.execute();			
}


public static void  ModificarCobrador(String iden,String car,String nom,String apell,int anos,String mun) throws SQLException, ClassNotFoundException { 
String sqlSentenc = "SELECT \"public\".\"ModificarCobrador\"(?,?,?,?,?,?)" ; 
PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
prepararCons.setString(1,iden);
prepararCons.setString(2,car); 
prepararCons.setString(3,nom);
prepararCons.setString(4,apell);
prepararCons.setInt(5,anos);
prepararCons.setString(6,mun);
prepararCons.execute();

}

public static Cobrador getCobrador (String name){
	Cobrador a = new Cobrador();
	String sentence = "SELECT * FROM \"Cobrador\" WHERE \"Cobrador\".\"nombre\" = ?";
	try {
		PreparedStatement stat = ConnectionBD.connect.getConnection().prepareStatement(sentence);
		stat.setString(1, name);
		stat.execute();
		ResultSet result = stat.getResultSet();
		while (result.next()) {
			a.setIdentificador(result.getString(1));
			a.setNombre(result.getString(2));
			a.setApellidos(result.getString(3));
			a.setCarnet(result.getString(4));
			a.setMunicipio(result.getString(5));
			a.setAnos(result.getInt(6));
			}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return a;
}



}