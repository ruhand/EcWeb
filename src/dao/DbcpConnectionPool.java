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
	//初始化数据库连接池
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
		//使用properties对象定义数据库连接池信息
		try{
			Properties p=new Properties();
  
			  p.setProperty("driverClassName", "com.mysql.jdbc.Driver");
	            p.setProperty("url", "jdbc:mysql://localhost:3306/newsdb");
	            p.setProperty("password", "root");
	            p.setProperty("username", "root");
	            p.setProperty("maxActive", "30");
	            p.setProperty("maxIdle", "10");
	            p.setProperty("maxWait", "1000");
//	下面这几个参数不加也可以实现，不过最好还是加，具体看书本该章最后一段分析，其他深入请自行检索           

	            p.setProperty("removeAbandonedTimeout", "120");
	            p.setProperty("testOnBorrow", "true");
	            p.setProperty("logAbandoned", "true"); 
            //以指定信息创建数据源
          
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
/*方便做测试，加一个main
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
