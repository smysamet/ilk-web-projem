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
public class MagazaAraController {

	private String aranacakMagaza;
	private String secilenRadioButton;
	private String hataLabel;
	private String hataLabel2;
	private String hataLabel3;
	private List<Magaza> magazaList;

	public MagazaAraController() {

	}

	public String getHataLabel3() {
		return hataLabel3;
	}

	public void setHataLabel3(String hataLabel3) {
		this.hataLabel3 = hataLabel3;
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

	public String getSecilenRadioButton() {
		return secilenRadioButton;
	}

	public void setSecilenRadioButton(String secilenRadioButton) {
		this.secilenRadioButton = secilenRadioButton;
	}

	public String getAranacakMagaza() {
		return aranacakMagaza;
	}

	public void setAranacakMagaza(String aranacakMagaza) {
		this.aranacakMagaza = aranacakMagaza;
	}

	public void magazaAra() throws SQLException {
		
		if(aranacakMagaza.isEmpty()) {
			this.hataLabel = "Boþ olamaz!";
		}else {
			this.hataLabel="";
		}
		
		if(secilenRadioButton.isEmpty()) {
			this.hataLabel2 = "Boþ olamaz!";
		}else {
			this.hataLabel2 = "";
		}
		
		// input ve radio button iþaretli deðilse
		if(aranacakMagaza.isEmpty() || secilenRadioButton.isEmpty())
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

		String sql = "";

		if (secilenRadioButton.equals("2")) {
			sql = "select store_id,store_name from products.stores where store_name like '" + this.aranacakMagaza
					+ "%'";
		} else if (secilenRadioButton.equals("1")) {
			sql = "select store_id,store_name from products.stores where store_id like '" + this.aranacakMagaza
					+ "%'";
		}
		
		ResultSet reSSet = stmt.executeQuery(sql);
		
		this.magazaList = new ArrayList<>(); 
		
		if(!reSSet.next()) {
			this.hataLabel3 = "Böyle bir maðaza bulunamadý.";
		}else {
			this.hataLabel3 = "";
			aranacakMagaza = "";
			reSSet = stmt.executeQuery(sql);
			while (reSSet.next()) {
	        	Magaza temp = new Magaza();
	        	temp.setId(reSSet.getString("store_id"));
	        	temp.setName(reSSet.getString("store_name"));
	        	magazaList.add(temp);
	        }
		}
	}

}
