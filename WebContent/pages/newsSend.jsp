<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import ="java.sql.*,dao.*"
    errorPage="../inc/error.jsp"
     %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="newsBean" class="beans.news" scope="request" />
<% 
//判断用户是否经登录
  if(session.getAttribute("login")==null)
{
	session.setAttribute("errMessage","你没有登录系统，无法浏览此页面！");
	response.sendRedirect("../inc/error.jsp");
}  
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻发布系统-发布新闻</title>
<!-- 下面是引用ckeditor编辑器 -->
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script src="../ckeditor/sample.js" type="text/javascript"></script>
<!-- 验证提交内容的合法性 -->
<script language="javascript">

function CheckForm()
{
	// alert("请输入标题！");
	
	if (document.newsForm.title.value=="")
  {
    alert("请输入标题！");
	document.newsForm.title.focus();
	return false;
  }
  if (document.newsForm.username.value=="")
	  {
	    alert("请输入作者！");
		document.newForm.username.focus();
		return false;
	  }
  return true;  
}
</script>
</head>
<body>
<form  name="newsForm" method="post" onsubmit="return CheckForm();">
	<table align="left" width="740">
	<tr>
	<td align="center">标题</td>
	<td><input name="title" type="text" id="title" size="20" /></td>
	</tr>
	<tr>
	<td align="center">作者</td>
	<!-- 从session中获取登录用户信息 -->
	<td><input name="username" type="text" id="username" size="20" value=<%= (String)session.getAttribute("login") %> readonly="true"></td>
	</tr>
	<tr>
	<td align="center">分类</td>
	<td><select style="width: 405px" name="catalogs">
<!-- 从分类表中取出分类信息 -->
<%
	
	ResultSet rs = null;
	try
	{
		DbDao.getConnection();
		rs = DbDao.getSelect("select catalogs,value from `newsdb`.`catalogs`");
	    while(rs.next())
	    {
%>
<option value="<%=rs.getString("value") %>"><%=rs.getString("catalogs") %></option>
<%
	    }
	    rs.close();
	   DbDao.close();
	}catch(Exception e){}
%>
	</select>
	</td>
	</tr>
	<tr>
	<td align="center">内容</td>
	<td><!-- ckeditor编辑器 -->
	<textarea class="ckeditor"  cols="40" id="editor1" name="content" rows="5"></textarea>
	</td>
	</tr>
	<tr>
	<td colspan="2" align="center">
	<input type="submit" name="btnSubmit" value="提交" class="button" onclick="">
	<input type="reset" name="btnReset" value="重置" class="button">
	</td>
	</tr>
	
	</table>

</form>
<!-- 设置news的各属性 -->
<jsp:setProperty property="*" name="newsBean"/>
<jsp:getProperty property="addNewsMessage" name="newsBean"/>

</body>
</html>