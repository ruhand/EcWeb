package dao;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Properties;
import org.apache.commons.logging.LogFactory;

public class DbcpConnectionPool {
	private static BasicDataSource dataSource=null;
	
	public DbcpConnectionPool(){
		
	}
	//��ʼ�����ݿ����ӳ�
	public static void init(){
		if(dataSource!=null)
		{
			try{
				dataSource.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			dataSource=null;
		}
		//ʹ��properties���������ݿ����ӳ���Ϣ
		try{
			Properties p=new Properties();
  
			  p.setProperty("driverClassName", "com.mysql.jdbc.Driver");
	            p.setProperty("url", "jdbc:mysql://localhost:3306/newsdb");
	            p.setProperty("password", "root");
	            p.setProperty("username", "root");
	            p.setProperty("maxActive", "30");
	            p.setProperty("maxIdle", "10");
	            p.setProperty("maxWait", "1000");
//	�����⼸����������Ҳ����ʵ�֣�������û��Ǽӣ����忴�鱾�������һ�η������������������м���           

	            p.setProperty("removeAbandonedTimeout", "120");
	            p.setProperty("testOnBorrow", "true");
	            p.setProperty("logAbandoned", "true"); 
            //��ָ����Ϣ��������Դ
          
            dataSource = (BasicDataSource) BasicDataSourceFactory.createDataSource(p);
		
		}catch(Exception e)
		{
			
			e.printStackTrace();
		}
	}
		public static synchronized Connection getConnection() throws SQLException{
			if(dataSource==null){
				init();
			}
			Connection conn=null;
			if(dataSource!=null){
			
				conn=dataSource.getConnection();
			
			}
			return conn;
		}
/*���������ԣ���һ��main
 * 	public static void main(String[] args){
		Connection conn=null;
		try{
		conn=DbcpConnectionPool.getConnection();
		}catch(Exception e){
			System.out.println("555555");
			e.printStackTrace();
		}
	}*/
}
