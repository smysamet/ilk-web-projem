package web_deneme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class UserDao {
	
	private String userName;
	private String password;
	private String errMessage;
	private String hataLabel;
	private String hataLabel2;
	
	private String nextPage;
	
	private boolean isAdmin = false;
	
	public UserDao() {
		
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

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getNextPage() throws SQLException {
		if (loginCheck()) {
			return nextPage;	
		}
		return "";
	}

	
	
	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean loginCheck() throws SQLException {
		
		if(this.password.isEmpty()) {
			this.hataLabel2 = "Boþ olamaz!";
		}else
			this.hataLabel2 = "";
		
		if(this.userName.isEmpty()) {
			this.hataLabel = "Boþ olamaz!";
		}else
			this.hataLabel = "";
		
		
		
		if(this.userName.isEmpty() || this.password.isEmpty())
			return false;
		
		
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
		ResultSet reSSet = stmt.executeQuery("SELECT * FROM products.user_info WHERE mail = '"+userName+"'");
		
		// database'den þifreyi alýyoruz
		
		String passwordFromDB = "";
        if(reSSet.next()){
            passwordFromDB = reSSet.getString("password");
            if(reSSet.getString("type").equals("admin")){
                isAdmin = true;
            }
        }
        
        // kullanýcýn girdiði þifreyi sha1 ile hasleyip databaseden alýyoruz
        reSSet = stmt.executeQuery("select sha1('"+password+"')");
        String pswCheck ="";
        if(reSSet.next()){
            pswCheck = reSSet.getString("sha1('"+password+"')");
        }
        
        // hashli þifre kontrolü
        
        if(passwordFromDB.equals(pswCheck)) {
        	
        	if(isAdmin) {
        		this.nextPage = "AdminMainPage.xhtml";
        	}else {
        		this.nextPage = "PersonelMainPage.xhtml";
        	}
        	
        	return true;
        }else {
        	this.errMessage = "Kullanýcý adý / þifre hatalý.";
        	return false;
        }
	}
	
}
