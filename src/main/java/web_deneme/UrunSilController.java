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
public class UrunSilController {
	
	private List<Urun> urunList;
	private String silinecekUrunID;
	private String hataLabel;
	
	public UrunSilController() {
		urunleriCek();
	}
	
	public String getHataLabel() {
		return hataLabel;
	}

	public void setHataLabel(String hataLabel) {
		this.hataLabel = hataLabel;
	}

	public List<Urun> getUrunList() {
		return urunList;
	}

	public void setUrunList(List<Urun> urunList) {
		this.urunList = urunList;
	}
	
	public String getSilinecekUrunID() {
		return silinecekUrunID;
	}

	public void setSilinecekUrunID(String silinecekUrunID) {
		this.silinecekUrunID = silinecekUrunID;
	}

	public void urunSil() throws SQLException {
		
		if(silinecekUrunID.isEmpty()) {
			this.hataLabel = "Boþ olamaz!";
			return;
		}else
			this.hataLabel = "";
		
		try {
			Double.parseDouble(silinecekUrunID);
		}catch(NumberFormatException e) {
			this.hataLabel = "Lütfen bir sayý girin!";
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
		
		String query = "DELETE FROM products.product WHERE product_id = " + silinecekUrunID + ";";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        boolean a = preparedStmt.execute();
        if(a) {
        	this.hataLabel = "";
        }else {
        	this.hataLabel = "Böyle bir ürün kayýtlý deðil.";
        }
        connection.close();
        
        // tabloyu update ediyoruz tekrar ürünleri çekerek
        urunleriCek();
	}
	
	public void urunleriCek(){
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
			
			String sql = "select * from products.product";
			
			ResultSet reSSet = stmt.executeQuery(sql);
			
			urunList = new ArrayList<Urun>();
			
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
