package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojo.User;
import utils.DBUtils;



public class UserDALimpl implements UserDAL{

	private Connection con;
	private PreparedStatement stmt;
	
	public UserDALimpl() throws SQLException
	{
		con=DBUtils.getCon();
		
		stmt=con.prepareStatement("select * from users where email=? and password=?");
		System.out.println("---inside UserDALimpl---");
	}
	
	public User validateUser(String userName, String pwd) throws SQLException{
		System.out.println("---inside validateuser method");
		
		stmt.setString(1, userName);
		stmt.setString(2, pwd);
		
		ResultSet rset = stmt.executeQuery();
		
		User user=null;
		if(rset.next()) {
			user=new User(rset.getInt("userid"),
					rset.getString("Name"),
					rset.getString("city"),
					rset.getString("email"),
					rset.getString("Password"));
		}
		return user;
	}
	
	public void cleanUp() throws SQLException{
		System.out.println("---UserDAL Cleanup");
		if(stmt!=null)
			stmt.close();
	}

	@Override
	public User Validate(String userName, String pwd) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
