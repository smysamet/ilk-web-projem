package web_deneme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class KullaniciEkleController {
	
	private String hataLabel;
	private String hataLabel2;
	private String hataLabel3;
	private String hataLabel4;
	private String hataLabel5;
	private String hataLabel6;
	
	public KullaniciEkleController() {
		this.hataLabel6 = "";
	}
	
	public String getHataLabel6() {
		return hataLabel6;
	}

	public void setHataLabel6(String hataLabel6) {
		this.hataLabel6 = hataLabel6;
	}

	public String getHataLabel() {
		return hataLabel;
	}

	public void setHataLabel(String hataLabel) {
		this.hataLabel = hataLabel;
	}

	public String getHataLabel2() {
		return hataLabel2;
	}

	public void setHataLabel2(String hataLabel2) {
		this.hataLabel2 = hataLabel2;
	}

	public String getHataLabel3() {
		return hataLabel3;
	}

	public void setHataLabel3(String hataLabel3) {
		this.hataLabel3 = hataLabel3;
	}

	public String getHataLabel4() {
		return hataLabel4;
	}

	public void setHataLabel4(String hataLabel4) {
		this.hataLabel4 = hataLabel4;
	}

	public String getHataLabel5() {
		return hataLabel5;
	}

	public void setHataLabel5(String hataLabel5) {
		this.hataLabel5 = hataLabel5;
	}

	public void kullaniciyiKaydet(Kullanici kullanici) throws SQLException {
		this.hataLabel6 = "";
		if(kullanici.getName().isEmpty()) {
			this.hataLabel = "Boþ olamaz!";
		}else 
			this.hataLabel = "";
		
		if(kullanici.getSurname().isEmpty()) {
			this.hataLabel2 = "Boþ olamaz!";
		}else 
			this.hataLabel2 = "";
		
		if(kullanici.getMail().isEmpty()) {
			this.hataLabel3 = "Boþ olamaz!";
		}else 
			this.hataLabel3 = "";
		
		if(kullanici.getPassword().isEmpty()) {
			this.hataLabel4 = "Boþ olamaz!";
		}else 
			this.hataLabel4 = "";
		
		if(kullanici.getType().isEmpty()) {
			this.hataLabel5 = "Boþ olamaz!";
		}else 
			this.hataLabel5 = "";
		
		if(kullanici.getName().isEmpty() || kullanici.getSurname().isEmpty() || kullanici.getMail().isEmpty() || kullanici.getPassword().isEmpty() || kullanici.getType().isEmpty())
			return;
		
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
		
		stmt.executeUpdate("insert into products.user_info(name,surname,mail,password,type)values('"+kullanici.getName()+"','"+kullanici.getSurname()+"','"+kullanici.getMail()+"',sha1('"+kullanici.getPassword()+"'),'"+kullanici.getType()+"')");
		stmt.close();
        connection.close();
        
        this.hataLabel6 = "Kullanýcý kaydedildi!";
        kullanici.reset();
	}
}
