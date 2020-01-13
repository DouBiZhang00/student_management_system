package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Student;
import domain.User;
import utils.JDBCUtils;


public class UserDao {
	
	//登陆验证用户名或密码是否正确
    public int login(String username,String password) throws SQLException{
        Connection conn = JDBCUtils.getcon();
        String sql = "select count(*) from users where uname = ? and password = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, username);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        int count = 0;
        while(rs.next()){
            count=rs.getInt(1);
        }
        return count;
    }
    //添加用户
    public int insert(User user) throws SQLException{
        Connection conn = JDBCUtils.getcon();
        String sql = "insert into users(uname,password) values(?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, user.getUsername());
        pst.setString(2, user.getPassword());
        int row = pst.executeUpdate();
        JDBCUtils.close(conn, pst);
        return row;
    }
    //修改密码
    public int updatePassword(User user) throws SQLException {
    	int row = 0;
    	Connection conn = JDBCUtils.getcon();
        String sql = "update users set password=? where uname=? ";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, user.getPassword());
        pst.setString(2, user.getUsername());
        row = pst.executeUpdate();
        JDBCUtils.close(conn, pst);
    	return row;
    }
    //添加学生信息
    public int insert(Student student) throws SQLException{
        Connection conn = JDBCUtils.getcon();
        String sql = "insert into student(sno,sname,ssex,sclass) values(?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, student.getSno());
        pst.setString(2, student.getSname());
        pst.setString(3, student.getSsex());
        pst.setString(4, student.getSclass());
        int row = pst.executeUpdate();
        JDBCUtils.close(conn, pst);
        return row;
    }
    //修改学生信息
    public int update(Student student) throws SQLException{
        Connection conn = JDBCUtils.getcon();
        String sql = "update student set sname=?,ssex=?,sclass=? where sno=? ";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, student.getSname());
        pst.setString(2, student.getSsex());
        pst.setString(3, student.getSclass());
        pst.setString(4, student.getSno());
        int row = pst.executeUpdate();
        JDBCUtils.close(conn, pst);
        return row;
    }
    //删除学生信息
    public int delete(Student student) throws SQLException{
        Connection conn = JDBCUtils.getcon();
        String sql = "delete from student where sno=? and sname=? and ssex=? and sclass=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, student.getSno());
        pst.setString(2, student.getSname());
        pst.setString(3, student.getSsex());
        pst.setString(4, student.getSclass());
        int row = pst.executeUpdate();
        JDBCUtils.close(conn, pst);
        return row;
    }
    //查询所有人
    public ResultSet select() throws SQLException{
        Connection conn = JDBCUtils.getcon();
        String sql = "select * from student";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        return rs;
    }
    //按班级查询
    public ResultSet select(String sclass) throws SQLException{
        Connection conn = JDBCUtils.getcon();
        String sql = "select * from student where sclass=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, sclass);
        ResultSet rs = pst.executeQuery();
        return rs;
    }
}
