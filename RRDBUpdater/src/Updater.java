import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Updater {
	
	private String db_pwd = "";
	private String db_usr = "root";
	
	
	
//	rawConn = D
	public Updater(){
		
	}
	
	
	public void doExecuteUpdate(String rawDB,String cleanDB) throws SQLException{
		Connection rawConn = DBConnect.connectTo(rawDB, db_usr, db_pwd);
		Connection cleanConn = DBConnect.connectTo(cleanDB, db_usr, db_pwd);
		String select = "SELECT t.id_rr, t.nom, t.role,t.id_demandeur, t.equipe_rm, t.date_debut,t.date_fin,t.competences,t.gcm,t.adresse,t.ville,t.niveau_min,t.niveau_max FROM table_1 t ";
		String insert = "INSERT INTO rr(id_rr, nom_rr, role, demandeur_rr, equipe_rm, date_debut, date_fin, competence_rr, gcm_rr, adresse, ville, niveau_min, niveau_max) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Statement stmt;
		PreparedStatement selectPstmt;
		PreparedStatement insertPstmt;
		ResultSet rawResult;
		
		
		stmt = rawConn.createStatement();
		rawResult = stmt.executeQuery(select);
		insertPstmt = cleanConn.prepareStatement(insert);
		
		
		while(rawResult.next()){
			insertPstmt.setInt(1, rawResult.getInt("id_rr"));
			insertPstmt.setString(2, rawResult.getString("nom"));
			insertPstmt.setString(3, rawResult.getString("role"));
			insertPstmt.setString(4, rawResult.getString("id_demandeur"));
			insertPstmt.setString(5, rawResult.getString("equipe_rm"));
			insertPstmt.setDate(6, rawResult.getDate("date_debut"));
			insertPstmt.setDate(7, rawResult.getDate("date_fin"));
			insertPstmt.setString(8, rawResult.getString("competences"));
			
			String gcm = rawResult.getString("gcm");
			if (gcm.length()<=4){
				gcm = "    ";
			}
			
			insertPstmt.setString(9, gcm.substring(0, 3));
			insertPstmt.setString(10, rawResult.getString("adresse"));
			insertPstmt.setString(11, rawResult.getString("ville"));
			insertPstmt.setString(12, rawResult.getString("niveau_min"));
			insertPstmt.setString(13, rawResult.getString("niveau_max"));
			insertPstmt.executeUpdate();
			System.out.println("-------------ON GOING---------------------");
		}
		
		rawResult.close();
		insertPstmt.close();
		rawConn.close();
		cleanConn.close();
		System.out.println("--------------DONE--------------------");				
	}
	
	public static void main(String [] args){
		
		Updater updater = new Updater();
		try {
			updater.doExecuteUpdate("jdbc:mysql://localhost/rawdb", "jdbc:mysql://localhost/ressources");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	

}
