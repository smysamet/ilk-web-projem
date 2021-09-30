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
public class KullaniciAraController {
	
	private String aranacakKullanici;
	private String secilenRadioButton;
	private String hataLabel;
	private String hataLabel2;
	private String hataLabel3;
	private List<Kullanici> kullaniciList;
	
	public void KullaniciAraController() {
		
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

	public String getAranacakKullanici() {
		return aranacakKullanici;
	}

	public void setAranacakKullanici(String aranacakKullanici) {
		this.aranacakKullanici = aranacakKullanici;
	}

	public String getSecilenRadioButton() {
		return secilenRadioButton;
	}

	public void setSecilenRadioButton(String secilenRadioButton) {
		this.secilenRadioButton = secilenRadioButton;
	}

	public List<Kullanici> getKullaniciList() {
		return kullaniciList;
	}

	public void setKullaniciList(List<Kullanici> kullaniciList) {
		this.kullaniciList = kullaniciList;
	}

	public void kullaniciAra() throws SQLException {
		
		if(aranacakKullanici.isEmpty()) {
			this.hataLabel = "Boþ olamaz!";
		}else
			this.hataLabel = "";
		
		
		if(secilenRadioButton.isEmpty()) {
			this.hataLabel2 = "Boþ olamaz!";
		}else
			this.hataLabel2 = "";
		
		if(aranacakKullanici.isEmpty() || secilenRadioButton.isEmpty())
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
            ek = "name like " + "'" + aranacakKullanici + "%'";
        } else if (secilenRadioButton.equals("1")) {
            ek = "id like " + "'" + aranacakKullanici + "%'";
        } else if (secilenRadioButton.equals("3")) {
            ek = "surname like " + "'" + aranacakKullanici + "%'";
        } else if (secilenRadioButton.equals("4"))
        	ek = "mail like "+ "'" + aranacakKullanici +"%'";
		
		
		String sql = 
                "select * from products.user_info where " + ek;
        ResultSet reSSet = stmt.executeQuery(sql);
        
        this.kullaniciList = new ArrayList<>(); 
        
        if(reSSet.next()) {
        	this.hataLabel3 = "";
        	reSSet = stmt.executeQuery(sql);
        	
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
        }else {
        	this.hataLabel3 = "Böyle bir kullanýcý bulunamadý!";
        }
        
        
        

	}
}
