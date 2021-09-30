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
public class KullaniciSilController {
	
	private List<Kullanici> kullaniciList;
	private String silinecekKullaniciID;
	private String hataLabel;
	private String hataLabel2;
	
	public KullaniciSilController() {
		try {
			kullanicilariCek();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public List<Kullanici> getKullaniciList() {
		return kullaniciList;
	}

	public void setKullaniciList(List<Kullanici> kullaniciList) {
		this.kullaniciList = kullaniciList;
	}

	public String getSilinecekKullaniciID() {
		return silinecekKullaniciID;
	}

	public void setSilinecekKullaniciID(String silinecekKullaniciID) {
		this.silinecekKullaniciID = silinecekKullaniciID;
	}
	
	public void kullaniciSil() throws SQLException{
		
		try {
			Double.parseDouble(silinecekKullaniciID);
		}catch (NumberFormatException e) {
			this.hataLabel2 = "Lütfen bir sayý girin";
			return;
		}
		
		
		if(silinecekKullaniciID.isEmpty()) {
			this.hataLabel = "Boþ olamaz!";
			return;
		}else
			this.hataLabel = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		final String LINK = "jdbc:mysql://localhost:3306/products?useUnicode=true&useJDBCCompliantTimezoneShift=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
		final String USER_NAME = "root";
		final String PASSWORD = "admin";
		Connection connection = DriverManager.getConnection(LINK, USER_NAME, PASSWORD);
		
		String query = "DELETE FROM products.user_info WHERE id = " + silinecekKullaniciID + ";";
		
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        if(preparedStmt.execute()) {
        	this.hataLabel2="";
        	this.silinecekKullaniciID ="";
        }else
        	this.hataLabel2="Bu ID'ye sahip bir kullanýcý yok!";
        
        connection.close();
        kullanicilariCek();
	}
	
	public void kullanicilariCek() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		final String LINK = "jdbc:mysql://localhost:3306/products?useUnicode=true&useJDBCCompliantTimezoneShift=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
		final String USER_NAME = "root";
		final String PASSWORD = "admin";
		Connection connection = DriverManager.getConnection(LINK, USER_NAME, PASSWORD);
		Statement stmt = connection.createStatement();
		
		ResultSet reSSet = stmt.executeQuery("select * from products.user_info");
		
		kullaniciList = new ArrayList<Kullanici>();
		
		while (reSSet.next()) {
			
			Kullanici temp = new Kullanici();
			
			temp.setId(reSSet.getString("id"));
			temp.setName(reSSet.getString("name"));
			temp.setSurname(reSSet.getString("surname"));
			temp.setMail(reSSet.getString("mail"));
			temp.setPassword(reSSet.getString("password"));
			temp.setType(reSSet.getString("type"));
			
			kullaniciList.add(temp);

        }
	}
	
}
