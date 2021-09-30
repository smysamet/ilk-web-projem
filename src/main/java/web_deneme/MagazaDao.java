package web_deneme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class MagazaDao {
	private String hataLabel;
	private String kaydedildiLabel;
	
	public String getHataLabel() {
		return hataLabel;
	}
	
	public String getKaydedildiLabel() {
		return kaydedildiLabel;
	}

	public void setKaydedildiLabel(String kaydedildiLabel) {
		this.kaydedildiLabel = kaydedildiLabel;
	}

	public void setHataLabel(String hataLabel) {
		this.hataLabel = hataLabel;
	}

	public void magazayiKaydet(Magaza magaza) throws SQLException {
		this.kaydedildiLabel = "";
		
		if(magaza.getName().isEmpty()) {
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
		Statement stmt = connection.createStatement();
		int a = stmt.executeUpdate(
				"insert into products.stores(store_name)"
						+ "values(" + "'" + magaza.getName() + "')");
		
		if(a!=0) {
			this.kaydedildiLabel = "Maðaza kaydedildi.";
		}else
			this.kaydedildiLabel = "Maðaza kaydedilemedi!";
			
		
		stmt.close();
		connection.close();

		magaza.reset();
	}
}
