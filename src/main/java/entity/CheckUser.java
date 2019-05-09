package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class CheckUser {

	public static boolean checkuser(String phone_number,String password,HttpServletRequest request) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs= null ;
	
		PreparedStatement pstmt	= null ;
		DB_Info db_Info=new DB_Info();
		String driver=db_Info.getDriver();//驱动类
		String UN=db_Info.getUsername();//数据库用户名
		String PW=db_Info.getPassword();//数据库密码
		String sql="select * from manager where phone_number='"+phone_number+"' and password='"+password+"'" ;//查询语句
		String url=db_Info.getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(url,UN,PW);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			rs=pstmt.executeQuery();//查询数据库并生成结果集
			if(rs.next())
			{
				HttpSession session=request.getSession();
				session.setAttribute("username", rs.getString("username"));
				session.setAttribute("phone_number", rs.getString("phone_number"));
				session.setAttribute("password", rs.getString("password"));
				session.setAttribute("lv", rs.getString("lv"));
				session.setAttribute("qr_url", rs.getString("qr_url"));
				session.setAttribute("leader", rs.getString("leader"));
				rs.close();//关闭结果集
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				return true;
			}else {				
				rs.close();//关闭结果集
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				return false;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
			
		
	}
}
