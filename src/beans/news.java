package beans;
import java.sql.ResultSet;
import java.util.*;



import dao.*;



public class news {

	private int id;
	private String username;
	private String title;
	private String content;
	private String attachment;
	private int viewCount;
	private Date submitTime;
	private String catalogs;
	
	private String addNewsMessage;
	private StringBuffer indexTopNews;
	
	private String searchByID;
	
	public news(){
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public String getCatalogs() {
		return catalogs;
	}
	public void setCatalogs(String catalogs) {
		this.catalogs = catalogs;
	}
	
	public String getAddNewsMessage() throws Exception {
		/**
		 * 添加新闻
		 * @return addNewsMessage，是否成功
		 */
		
		{
			if(title==null || username==null) {
			//	this.addNewsMessage = "<script language='javascript'>alert('标题作者都没');</script>";
			//	return this.addNewsMessage;
				return "";
			}
			java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			  java.util.Date now = new java.util.Date();
			
			int result = 0;
			String sql = "insert into `newsdb`.`news` (title,username,content,viewCount,submitTime,catalogs,attachment) values ('" 
				+ title+ "','"
				+ username + "','"
				+ content + "',"
				+ 0 + ",'"
				+ sdf.format(now) + "','"
				+ catalogs + "','"
				+ attachment + "')";

			if(title!=null && username!=null)
			{
				DbDao.getConnection();
				try{
					result = DbDao.runSql(sql);
				}catch(Exception e)
				{
					e.printStackTrace();
				}finally
				{
					DbDao.close();
				}
			
				if(result > 0)
					this.addNewsMessage = "<script language='javascript'>alert('添加新闻成功！');</script>";
				else
					this.addNewsMessage = "<script language='javascript'>alert('添加新闻失败，请重试！');</script>";	
				}
			return this.addNewsMessage;
		}
		
	}
	public void setAddNewsMessage(String addNewsMessage) {
		this.addNewsMessage = addNewsMessage;
	}
	
	public StringBuffer getIndexTopNews() {
		ResultSet rs = null;
		String sql = "select id,title,username,submitTime from `newsdb`.`news` order by submitTime desc limit 15";
		try
		{
			DbDao.getConnection();
			rs = DbDao.getSelect(sql);
			indexTopNews = new StringBuffer("");
			int i=1;
			while(rs.next())
			{
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String username=rs.getString("username");
				Date time = rs.getDate("submitTime");
				//HTML格式化每条记录，插入链接和图片
				indexTopNews.append("<tr>");
				indexTopNews.append("<td align='right' width='72'><img src='../image/x_03.png'></td>");
				indexTopNews.append("<td align='center' width='150'>[" +i+ "]</td>");			
				
				indexTopNews.append("<td align='left' width='590'><a href='./detail.jsp?id=" + id + "'>"+title+ "</a></td>");
				indexTopNews.append("<td align='center' width='150'>[" +time.toString()+ "]</td>");			
				indexTopNews.append("</tr>");	
				indexTopNews.append("</br>");
				i++;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DbDao.close();
		}		
	
		return indexTopNews;
	}
	public void setIndexTopNews(StringBuffer indexTopNews) {
		this.indexTopNews = indexTopNews;
	}
	public String getSearchByID()
	{
		String sql = "SELECT * FROM `newsdb`.`news` where id =" + id;
	
		   try{
				DbDao.getConnection();
				ResultSet rs =DbDao.getSelect(sql); 
				if(rs.next())
				{
					catalogs = rs.getString("catalogs");
		            title = rs.getString("title");
		            username = rs.getString("username");
		            content = rs.getString("content");
					submitTime = rs.getDate("submitTime");
					viewCount = rs.getInt("viewCount");
					
					//浏览次数加1
					viewCount++;
					String SQLforUpdate = "update `dangwei`.`news` set viewCount = " + viewCount + " where id = " + id;
					DbDao.runSql(SQLforUpdate);
					
		        }
				
			    DbDao.close();
			}catch(Exception e)
			{
			}		
		return  searchByID;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
