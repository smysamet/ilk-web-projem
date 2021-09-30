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
public class UrunAraController {
	
	private String aranacakUrun;
	private String secilenRadioButton;
	private String hataLabel;
	private String hataLabel2;
	private String hataLabel3;
	
	private List<Urun> urunList;
	
	
	public UrunAraController() {

	}
	
	public String getHataLabel3() {
		return hataLabel3;
	}

	public void setHataLabel3(String hataLabel3) {
		this.hataLabel3 = hataLabel3;
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

	public String getSecilenRadioButton() {
		return secilenRadioButton;
	}

	public void setSecilenRadioButton(String secilenRadioButton) {
		this.secilenRadioButton = secilenRadioButton;
	}

	public String getAranacakUrun() {
		return aranacakUrun;
	}

	public void setAranacakUrun(String aranacakUrun) {
		this.aranacakUrun = aranacakUrun;
	}


	public List<Urun> getUrunList() {
		return urunList;
	}

	public void setUrunList(List<Urun> urunList) {
		this.urunList = urunList;
	}

	public void urunAra() throws SQLException {
		
		if(aranacakUrun.isEmpty()) {
			this.hataLabel = "Boþ olamaz!";
		}else
			this.hataLabel = "";
		
		
		if(secilenRadioButton.isEmpty()) {
			this.hataLabel2 = "Boþ olamaz!";
		}else
			this.hataLabel2 = "";
		
		
		if(secilenRadioButton.isEmpty() || aranacakUrun.isEmpty())
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
		
		String ek = "";
		
		if (secilenRadioButton.equals("2")) {
            ek = "product_title like " + "'" + aranacakUrun + "%'";
        } else if (secilenRadioButton.equals("1")) {
            ek = "product_id like " + "'" + aranacakUrun + "%'";
        } else if (secilenRadioButton.equals("3")) {
            ek = "product_description like " + "'" + aranacakUrun + "%'";
        }
		
		String sql = 
                "select * from products.product where " + ek;
        ResultSet reSSet = stmt.executeQuery(sql);
        
        this.urunList = new ArrayList<>(); 
        
        if(!reSSet.next()) {
        	this.hataLabel3 = "Böyle bir ürün bulunamadý!";
        	return;
        }else {
        	reSSet = stmt.executeQuery(sql);
        	this.hataLabel3 = "";
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
}
