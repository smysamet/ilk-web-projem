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
public class MagazalariGosterController {
	private List<Magaza> magazaList;

	public MagazalariGosterController() throws SQLException {
		magazalariCek();
	}

	public List<Magaza> getMagazaList() {
		return magazaList;
	}

	public void setMagazaList(List<Magaza> magazaList) {
		this.magazaList = magazaList;
	}

	public void magazalariCek() throws SQLException {
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
		ResultSet reSSet = stmt.executeQuery("select * from products.stores");
		this.magazaList = new ArrayList<>();

		while (reSSet.next()) {
			Magaza temp = new Magaza();
			temp.setId(reSSet.getString("store_id"));
			temp.setName(reSSet.getString("store_name"));
			magazaList.add(temp);
		}

	}
}
