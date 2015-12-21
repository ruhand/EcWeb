<%@ page contentType="text/html; charset=gbk" language="java" import="java.sql.*" %>
<%@ page import="java.sql.*" %>
<jsp:useBean id="news" class="beans.news" scope="request" />
<jsp:useBean id="comment" class="beans.comment" scope="request" /> 

<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD>
<TITLE>详细新闻</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">

<script language="javascript">	
function CheckForm()
{
 
  if (document.myform.content.value=="")
  {
    alert("评论内容 不能为空！");
	document.myform.username.focus();
	return false;
  } 
  return true;  
}

</script>



<%
int id = Integer.parseInt(request.getParameter("id"));

news.setId(id);

news.getSearchByID();


//comment.setNewsID(id);
//String catalogs = news.getCatalogs();
//catalogs = CatalogsConvert.CatConvert(catalogs);
String catalogs="hahahah";
%>




		<table align="center" bgColor=#ffffff 
border=0 cellpadding="0" cellspacing="0" width="780">
			<tbody><tr>
				<td bgcolor="#c31715" width="8"></td>
				<td bgcolor="#ffffff" width="37">&nbsp;
					
				</td>
				<td bgcolor="#ffffff" valign="top" width="690">
					<table border="0" cellpadding="0" cellspacing="0" width="690">
						<tbody><tr>
							<td class="titleLine" height="36">
								<%=catalogs %>
							</td>
						</tr>
						<tr>
							<td height="30"></td>
						</tr>
						<tr>
							<td>
								<table align="center" border="0" cellpadding="0" cellspacing="0" width="527">

									<tbody><tr>
										<td class="black_18" align="center" height="43"><strong><jsp:getProperty name="news" property="title" /></strong></td>
									</tr>
									<tr>
										<td class="gray" align="center" height="30"><font size=2px>
											发布时间：<jsp:getProperty name="news" property="submitTime" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            发布人：<jsp:getProperty name="news" property="username" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           浏览次数：<jsp:getProperty name="news" property="viewCount" />
										</font></td>

									</tr>
									<tr class="black">
										<td class="div1" height="30"><font size=2px><jsp:getProperty name="news" property="content" /></font></td>
									</tr>


								</tbody></table>
							</td>
						</tr>
						<tr><td class="blue" align="center">
						当前评论数:&nbsp;&nbsp;<font color=red><jsp:getProperty property="commentCount" name="comment"/></font>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a href="commentList.jsp?newsid=<%=id %>">查看</a></span>
						</td></tr>
						<tr>
							<td height="200">
<form name="myform" method="post" action="../CommentAdd?newsid=<%=id %>" onsubmit="return CheckForm()">
<table align="center" width="500">
<tr>
<td width="100" class="black">用&nbsp;户&nbsp;名</td>
<td width="400">
  <input name="username" type="text" size="41" width="300" value="匿名用户">
</td>
</tr>


<tr>
<td class="black">评论内容</td>
<td>
  <textarea name="content" cols="40" rows="5" ></textarea>
</td>
</tr>
<tr>
<td colspan="2" align="center"><label>
  <input type="submit" name="submit" value="发送" class="button"/>
 <input  type="reset" name="reset" value="重置" class="button"/>
</label></td>
</tr>
</table>
</form>
</td>
						</tr>
					</tbody></table>
				</td>
				
				<td bgcolor="#ffffff" width="38">&nbsp;
					
				</td>
				<td bgcolor="#c31715" width="7"></td>
			</tr>
		</tbody></table>
		<table align="center" border="0" cellpadding="0" cellspacing="0" width="780">
			<tbody><tr>
				<td bgcolor="#c31715" width="8"></td>
				<td bgcolor="#ffffff" width="28">&nbsp;
				<!--  <iframe name="center" src="commentSend.jsp" scrolling="no" frameborder="0" height="200" width="740"></iframe>	-->
				</td>
				<td bgcolor="#ffffff" valign="top" width="707">
					
				</td>
				<td bgcolor="#ffffff" width="30">&nbsp;
					
				</td>
				<td bgcolor="#c31715" width="7"></td>
			</tr>
		</tbody></table>
		<table align="center" border="0" cellpadding="0" cellspacing="0" width="780">
			<tbody><tr>
				<td>
					<iframe name="bottom" src="../inc/a_003.htm" scrolling="no" frameborder="0" height="58" width="780"></iframe>
				</td>
			</tr>
		</tbody></table>
	</body></html>



