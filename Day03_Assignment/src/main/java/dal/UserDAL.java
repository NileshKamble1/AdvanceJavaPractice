package dal;

import java.sql.SQLException;

import pojo.User;

public interface UserDAL {

	User Validate(String userName, String pwd) throws SQLException;
}
