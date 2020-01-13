package service;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UserDao;
import domain.Student;
import domain.User;

public class UserService {
	public UserService() {
		// TODO Auto-generated constructor stub
	}
	private UserDao  userDao = new UserDao();
	public  int login(String username,String password){
        int count = 0;
        try {
          count = userDao.login(username, password);
     } catch (SQLException e) {
         e.printStackTrace();
     }
       return count;
    }
	public int updatePassword(String username,String password) {
		User user = new User();
		user.setPassword(password);
		user.setUsername(username);
		int count = 0;
		try {
			count = userDao.updatePassword(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public int insert(String username,String password) {
		User user = new User();
		user.setPassword(password);
		user.setUsername(username);
		int count = 0;
		try {
			count = userDao.insert(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public int insert(String sno,String sname,String ssex,String sclass) {
		Student student = new Student(sno, sname, ssex, sclass);
		int count = 0;
		try {
			count = userDao.insert(student);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public int delete(String sno,String sname,String ssex,String sclass) {
		Student student = new Student(sno, sname, ssex, sclass);
		int count = 0;
		try {
			count = userDao.delete(student);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public int update(String sno,String sname,String ssex,String sclass) {
		Student student = new Student(sno, sname, ssex, sclass);
		int count = 0;
		try {
			count = userDao.update(student);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public ResultSet select() {
		ResultSet rset = null;
		try {
			rset = userDao.select();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rset;
	}
	public ResultSet select(String sclass) {
		ResultSet rset = null;
		try {
			rset = userDao.select(sclass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rset;
	}
}
