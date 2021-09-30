package web_deneme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class KullanicilariGosterController {
	
	private List<Kullanici> kullaniciList;
	
	public KullanicilariGosterController() {
		try {
			kullanicilariGoster();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Kullanici> getKullaniciList() {
		return kullaniciList;
	}

	public void setKullaniciList(List<Kullanici> kullaniciList) {
		this.kullaniciList = kullaniciList;
	}

	public void kullanicilariGoster() throws SQLException {
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
