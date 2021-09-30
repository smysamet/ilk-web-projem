package web_deneme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean
public class UrunDao {
	
	private String errorPrice;
	private String errorCount;
	private String errorStoreId;
	private String errorTitle;
	private String errorDesc;
	private String kaydedildiLabel;
	
	private boolean priceErr = true;
	private boolean countErr = true;
	private boolean storeErr = true;
	private boolean titleErr = true;
	private boolean descErr = true;
	
	public String getErrorTitle() {
		return errorTitle;
	}
	

	public String getKaydedildiLabel() {
		return kaydedildiLabel;
	}


	public void setKaydedildiLabel(String kaydedildiLabel) {
		this.kaydedildiLabel = kaydedildiLabel;
	}


	public void setErrorTitle(String errorTitle) {
		this.errorTitle = errorTitle;
	}


	public String getErrorDesc() {
		return errorDesc;
	}


	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}


	public UrunDao() {
		
	}
	
	
	public String getErrorPrice() {
		return errorPrice;
	}


	public void setErrorPrice(String errorPrice) {
		this.errorPrice = errorPrice;
	}


	public String getErrorCount() {
		return errorCount;
	}


	public void setErrorCount(String errorCount) {
		this.errorCount = errorCount;
	}


	public String getErrorStoreId() {
		return errorStoreId;
	}


	public void setErrorStoreId(String errorStoreId) {
		this.errorStoreId = errorStoreId;
	}


	public String getErrorMessage1() {
		return errorPrice;
	}

	public void setErrorMessage1(String errorPrice) {
		this.errorPrice = errorPrice;
	}


	public void urunuKaydet(Urun urun) throws SQLException{
		this.kaydedildiLabel = "";
		
		if(urun.getAciklama().isEmpty()) {
			descErr = true;
			errorDesc = "Boþ olamaz!";
		}else {
			descErr = false;
			errorDesc = "";
		}
		if(urun.getAdet().isEmpty()) {
			countErr = true;
			errorCount = "Boþ olamaz!";
		}else {
			countErr = false;
			errorCount = "";
		}
		
		if(urun.getFiyat().isEmpty()) {
			priceErr = true;
			errorPrice = "Boþ olamaz!";
		}else {
			priceErr = false;
			errorPrice = "";
		}
		
		if(urun.getIsim().isEmpty()) {
			titleErr = true;
			errorTitle = "Boþ olamaz!";
		}else {
			titleErr = false;
			errorTitle = "";
		}
		
		if(urun.getMagaza_id().isEmpty()) {
			storeErr = true;
			errorStoreId = "Boþ olamaz!";
		}else {
			storeErr = false;
			errorStoreId = "";
		}
		
		try{
			Double.parseDouble(urun.getFiyat());
		}catch (NumberFormatException e) {
			errorPrice = "Sayi girilmeli!";
			priceErr = true;	
		}
			
		
		try{
			Double.parseDouble(urun.getAdet());
		}catch (NumberFormatException e) {
			errorCount = "Sayi girilmeli!";
			countErr = true;
		}
		
		try{
			Double.parseDouble(urun.getMagaza_id());
		}catch (NumberFormatException e) {
			errorStoreId= "Sayi girilmeli!";
			storeErr = true;
		}
			
		
		if(!priceErr && !countErr && !storeErr) {
			errorPrice = "";
			errorCount = "";
			errorStoreId = "";
		}
		
		if(storeErr || titleErr || priceErr || countErr || descErr) {
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
		Statement stmt = connection.createStatement();
		int a = stmt.executeUpdate("insert into products.product(product_title,product_price,product_description,product_count,store_id)"
				+ "values("+"'"+urun.getIsim()+"','"+urun.getFiyat()+"','"+urun.getAciklama()+"','"+urun.getAdet()+"','"+urun.getMagaza_id()+"')");
		
		if(a != 0) {
			this.kaydedildiLabel = "Ürün kaydedildi!";
		}else {
			this.kaydedildiLabel = "Ürün kaydedilemedi!";
		}
		
		stmt.close();
        connection.close();
        
        urun.reset();
	}
	
	
}
