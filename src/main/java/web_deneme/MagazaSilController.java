package web_deneme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class MagazaSilController {
	
	private List<Magaza> magazaList;
	private String silinecekMagazaID;
	private String hataLabel;
	private String hataLabel2;
	
	public MagazaSilController() {
		magazalariCek();
	}
	
	public String getHataLabel2() {
		return hataLabel2;
	}

	public void setHataLabel2(String hataLabel2) {
		this.hataLabel2 = hataLabel2;
	}

	public String getHataLabel() {
		return hataLabel;
	}

	public void setHataLabel(String hataLabel) {
		this.hataLabel = hataLabel;
	}

	public List<Magaza> getMagazaList() {
		return magazaList;
	}

	public void setMagazaList(List<Magaza> magazaList) {
		this.magazaList = magazaList;
	}

	public String getSilinecekMagazaID() {
		return silinecekMagazaID;
	}

	public void setSilinecekMagazaID(String silinecekMagazaID) {
		this.silinecekMagazaID = silinecekMagazaID;
	}
	
	public void magazaSil() throws SQLException {
		if(silinecekMagazaID.isEmpty()) {
			this.hataLabel = "Boþ olamaz!";
			return;
		}else
			this.hataLabel = "";
		
		
		try {
			Double.parseDouble(silinecekMagazaID);
		}catch(NumberFormatException e) {
			this.hataLabel2 = "Lütfen sayý girin!";
			return;
		}
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		final String LINK = "jdbc:mysql://localhost:3306/products?useUnicode=true&useJDBCCompliantTimezoneShift=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
		final String USER_NAME = "root";
		final String PASSWORD = "admin";
		Connection connection = DriverManager.getConnection(LINK, USER_NAME, PASSWORD);
		
		String query = "DELETE FROM products.stores WHERE store_id= " + silinecekMagazaID + ";";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        boolean a = preparedStmt.execute();
        connection.close();
        
        if(a) {
        	this.hataLabel2 = "";
        }else
        	this.hataLabel2 = "Bu ID'ye sahip bir maðaza bulunamadý!";
        
        magazalariCek();
	}
	
	
	public void magazalariCek(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		final String LINK = "jdbc:mysql://localhost:3306/products?useUnicode=true&useJDBCCompliantTimezoneShift=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
		final String USER_NAME = "root";
		final String PASSWORD = "admin";
		try {
			Connection connection = DriverManager.getConnection(LINK, USER_NAME, PASSWORD);
			Statement stmt = connection.createStatement();
			
			String sql = "select * from products.stores";
			
			ResultSet reSSet = stmt.executeQuery(sql);
			
			magazaList = new ArrayList<Magaza>();
			
			while (reSSet.next()) {
	        	Magaza temp = new Magaza();
	        	temp.setId(reSSet.getString("store_id"));
	        	temp.setName(reSSet.getString("store_name"));
	        	magazaList.add(temp);

	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
