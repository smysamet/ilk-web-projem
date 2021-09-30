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
public class UrunleriGosterController {
	
	private List<Urun> urunList;  
	
	public UrunleriGosterController(){
		try {
			urunleriCek();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Urun> getUrunList() {
		return urunList;
	}

	public void setUrunList(List<Urun> urunList) {
		this.urunList = urunList;
	}

	public void urunleriCek() throws SQLException {
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
		ResultSet reSSet = stmt.executeQuery("select * from products.product");
		this.urunList = new ArrayList<>();    
		
        while (reSSet.next()) {
        	Urun temp = new Urun();
        	temp.setId(reSSet.getString("product_id"));
        	temp.setIsim(reSSet.getString("product_title"));
        	temp.setFiyat(reSSet.getString("product_price"));
        	temp.setAciklama(reSSet.getString("product_description"));
        	temp.setAdet(reSSet.getString("product_count"));
        	temp.setMagaza_id(reSSet.getString("store_id"));
        	urunList.add(temp);
        }
        	
	}
	
}
