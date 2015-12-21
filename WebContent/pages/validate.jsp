<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*,dao.*" errorPage="../inc/error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>判断用户登录</title>
</head>
<%
	String User = request.getParameter("username");     //得到登录用户名
	String Pwd = request.getParameter("password");       //得到登录密码
	Connection conn = DbDao.getConnection();
	String loginString=DbDao.login(User, Pwd,request.getRemoteAddr());
	out.print(loginString);
	if ((loginString!="用户错误")&&(loginString!="密码错误")){
		session.setAttribute("usertype",loginString);
		session.setAttribute("login",User);  
		//response.sendRedirect("newsSend.jsp");
		response.sendRedirect("newsSend.jsp");
		}
%>
<body>
</body>
</html>