package Services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import model.Casa;
import Utils.ConnectionBD;

public class ServicioCasa {
		public  static LinkedList<Casa> getHomes() throws SQLException, ClassNotFoundException{
			LinkedList<Casa> homes = new LinkedList<Casa>();
			Statement consulta = ConnectionBD.connect.getConnection().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sqlSentenc = "SELECT \"public\".\"casa\".\"identificador\",\"public\".\"casa\".\"numero\",\"public\".\"casa\".\"direccion\",\"public\".\"casa\".\"habitantes\"" +
					"FROM  \"public\".\"casa\"";
			ResultSet resultado = consulta.executeQuery(sqlSentenc);
			while (resultado.next()) {
				Casa h = new Casa();
				h.setIdentificador(resultado.getString(1));
				h.setNumero(resultado.getInt(2));
				h.setDireccion(resultado.getString(3));
				h.setHabitantes(resultado.getInt(4));
				homes.add(h);
			}
			return homes;
		}
	
		public static LinkedList<Casa> getHomes1 (String cob){
			LinkedList<Casa> casas = new LinkedList<Casa>();
			String sentence = " SELECT \"public\".\"casa\".\"identificador\",\"public\".\"casa\".\"numero\",\"public\".\"casa\".\"direccion\",\"public\".\"casa\".\"habitantes\",\"public\".\"casa\".\"consumoAlterado\"" +
			"FROM  \"public\".\"casa\" WHERE \"public\".\"casa\".\"cobrador\" = ?" ;
			try {
				PreparedStatement stat = ConnectionBD.connect.getConnection().prepareStatement(sentence);
				stat.setString(1, cob);
				stat.execute();
				ResultSet result = stat.getResultSet();
				while (result.next()) {
					Casa a = new Casa();
					a.setIdentificador(result.getString(1));
					a.setNumero(result.getInt(2));	
					a.setDireccion(result.getString(3));
					a.setHabitantes(result.getInt(4));
					a.setConsumoAlterado(result.getFloat(5));
					casas.add(a);
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return casas;
		}
		
		public  static LinkedList<Casa> getHomesCobradores() throws SQLException, ClassNotFoundException{
			LinkedList<Casa> homes = new LinkedList<Casa>();
			Statement consulta = ConnectionBD.connect.getConnection().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sqlSentenc = "SELECT \"public\".\"casa\".\"identificador\",\"public\".\"casa\".\"numero\",\"public\".\"casa\".\"direccion\",\"public\".\"casa\".\"habitantes\",\"public\".\"casa\".\"consumoAlterado\"" +
					"FROM  \"public\".\"casa\"WHERE \"public\".\"casa\".\"cobrador\" =/= NULL ";
			ResultSet resultado = consulta.executeQuery(sqlSentenc);
			while (resultado.next()) {
				Casa h = new Casa();
				h.setIdentificador(resultado.getString(1));
				h.setNumero(resultado.getInt(2));
				h.setDireccion(resultado.getString(3));
				h.setHabitantes(resultado.getInt(4));
				h.setConsumoAlterado(resultado.getFloat(5));
				homes.add(h);
			}
			return homes;
		}
		
		public static void insertarCasa(String ide,int numero, String direcc, int hab) throws SQLException, ClassNotFoundException{
			String sqlSentenc = "SELECT\"public\".\"InsertarCasa\" (?,?,?,?)"; /*Los símbolos ? indican los parámetros que se van a pasar */
			PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
			prepararCons.setString(1, ide); /* estamos dándole valor al primer parametro que se pasa, es decir al primer ? que aparezca. */
			prepararCons.setInt(2,numero);
			prepararCons.setString(3, direcc);
			prepararCons.setInt(4,hab);
			prepararCons.execute();
		}
		
		
		public static void  EliminarCasa(String ide) throws SQLException, ClassNotFoundException { 			
			String sqlSentenc = "SELECT \"public\".\"EliminarCasa\"(?)" ; 
			PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
			prepararCons.setString(1,ide); 
			prepararCons.execute();			
		}
		

	public static void  ModificarCasa(String iden,int num,String dir,int hab,float cons,String cob) throws SQLException, ClassNotFoundException { 		
		String sqlSentenc = "SELECT \"public\".\"ModificarCasa\"(?,?,?,?,?,?)" ; 
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setString(1,iden);
		prepararCons.setInt(2,num); 
		prepararCons.setString(3,dir);
		prepararCons.setInt(4,hab);
		prepararCons.setFloat(5,cons);
		prepararCons.setString(6,cob);
		prepararCons.execute();
		
	}
	

	public static void  ModificarCasa1(String iden,String cob) throws SQLException, ClassNotFoundException { 		
		String sqlSentenc = "SELECT \"public\".\"PonerCobrador\"(?,?)" ; 
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setString(1,iden);
		prepararCons.setString(2,cob);
		prepararCons.execute();
		
	}
	
	public static void  ModificarCasa2(String iden,float cons) throws SQLException, ClassNotFoundException { 		
		String sqlSentenc = "SELECT \"public\".\"PonerConsumodelCobrador\"(?,?)" ; 
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setString(1,iden);
		prepararCons.setFloat(2,cons);
		prepararCons.execute();
		
	}



}
