package dao;

import java.sql.*;

import javax.sql.*;

import dao.DbcpConnectionPool;

public class DbDao {
	private static Connection conn=null;
	private static Statement stmt=null;
	
	public DbDao(){
		super();
	}
	
	public static Connection getConnection() throws Exception {
		//System.out.println("aaaaa");
		//conn=DBPoolHelper.getConnection();
		conn=DbcpConnectionPool.getConnection();
		//System.out.println("bbbb");
		return conn;
		
	}
	
	public static String login(String User,String Pwd,String lastIP) throws SQLException {
		
		String sql="select password,usertype,audit from users where username='"+User+"'";  
		//����Statementʵ������ִ��SQL���
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		 if (!rs.next())   //�ж����ݿ��Ƿ���ڴ��û�
		 {
			 rs.close();
			 return "�û�����";
		 }else if (Pwd.equals(rs.getString("password"))&&rs.getString("audit").equals("1"))   //�ж��û����������Ƿ���ȷ
			  {
				  String usertype = rs.getString("usertype");
				  			  
				  java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				  java.util.Date now = new java.util.Date();
				  
				  String mysql = "update `newsdb`.`users` set lastIP='" + lastIP + "', status='1',lastLogin='" + sdf.format(now) 
				  + "' where username='"+User + "'";
				
				  stmt.executeUpdate(mysql);
				 //���û����͡��û�������Ϊsession
   
			      //��ת��main.jspҳ��
			      //response.sendRedirect("main.jsp");
				  rs.close();
			     return usertype;
			  }  else
			  {
				  rs.close();
			   return "�������";
			  }
		 	
		 
	}
	   
	public static ResultSet getSelect(String sql) throws SQLException{
		  
		//����Statementʵ������ִ��SQL���
		//System.out.println("aaaa");
		stmt = conn.createStatement();
		//System.out.println("bbbbbb");
		ResultSet rs = stmt.executeQuery(sql);
		//System.out.println("ccccc");
		return rs;
	}
	
	  public static int runSql(String sql) throws SQLException {
			
		    stmt = conn.createStatement();
		//    System.out.println("1111111111'");
		    int result=stmt.executeUpdate(sql);
		//    System.out.println("2222222222");
		    stmt.close();
		    return result;
		  }
	  
	public static void close(){
		try{
			conn.close();
		}catch(SQLException sqlexception)
		{
			sqlexception.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
