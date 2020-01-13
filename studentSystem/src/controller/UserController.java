package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import service.UserService;

public class UserController {
	public static void main(String[] args) {
		UserController controller = new UserController();
		ResultSet rset = controller.select();
		try {
			while(rset.next()) {
				System.out.println(rset.getString(1) + " " + rset.getString(2)+ " " + rset.getString(3)+ " " + rset.getString(4) );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private  UserService  userService = new UserService();
	public int login(String username,String password){
        return  userService.login(username, password);
    }
	public int updatePassword(String username,String password) {
		return userService.updatePassword(username, password);
	}
	public int insert(String username,String password) {
		return userService.insert(username, password);
	}
	public int insert(String sno,String sname,String ssex,String sclass) {
		return userService.insert(sno, sname, ssex, sclass);
	}
	public int delete(String sno,String sname,String ssex,String sclass) {
		return userService.delete(sno, sname, ssex, sclass);
	}
	public int update(String sno,String sname,String ssex,String sclass) {
		return userService.update(sno, sname, ssex, sclass);
	}
	public ResultSet select() {
		return userService.select();
	}
	public ResultSet select(String sclass) {
		return userService.select(sclass);
	}
}
