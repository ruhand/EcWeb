package beans;


import java.util.Date;
import java.sql.*;
import dao.*;

public class comment {
	private int id;
	private String content;
	private Date submitTime;
	private String username;
	private int newsID;
	
	private StringBuffer commentList;
	
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return this.id;
	}
	public String getContent()
	{
		return this.content;
	}
	public void setContent(String c)
	{
		this.content = c;
	}
	public String getUsername()
	{
		return this.username;
	}
	public void setUsername(String u)
	{
		this.username = u;
	}
	public Date getSubmitTime()
	{
		return this.submitTime;
	}
	public void setSubmitTime(Date d)
	{
		this.submitTime = d;
	}
	public void setNewsID(int i)
	{
		this.newsID = i;
	}
	public int getNewsID()
	{
		return this.newsID;
	}
	
	public int getCommentCount()
	{
		String SQLforcount = "select * from `newsdb`.`comment` where newsID=" + newsID;
		int i = 0;
		try
		{
			DbDao.getConnection();
			ResultSet rs = DbDao.getSelect(SQLforcount);
			while(rs.next())
			{
				i++;
			}
			DbDao.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return i;

	}
	public StringBuffer getCommentList()
	{
		commentList = new StringBuffer("");
		String sql = "select content,username,submitTime from `newsdb`.`comment` where newsID=" + newsID;
		
		
		ResultSet rs = null;
		try
		{
			DbDao.getConnection();
			
			
			
				rs = DbDao.getSelect(sql);
				while(rs.next())
				{
					commentList.append("<tr><td class='blue' width=500>"+ rs.getString("username") + "&nbsp;ÓÚ&nbsp;" + rs.getDate("submitTime") +"&nbsp;ÆÀÂÛ:</td></tr>");
					commentList.append("<tr><td class='black' width=500>" + rs.getString("content") + "</td></tr>");
				}
				
			
				DbDao.close();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return commentList;
	}
	
}
