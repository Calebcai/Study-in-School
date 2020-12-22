<%@LANGUAGE="VBSCRIPT" CODEPAGE="65001"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网络聊天室登陆</title>
</head>
<body>
      <h2>欢迎用户<%=request.Form("username")%>进入聊天室</h2>
      <%
      username = request.Form("username")
      userword=request.Form("userword")
      mywords=""
      mywords = username+":"+userword
      application.Lock()
      application("allwords")=application("allwords")+"<br>"+mywords
      Response.Write(application("allwords"))
      application.Unlock()
      %>
      
      <form action="" method="post">
            用户名：
            <input type="text" name="username" id="username">
            聊天内容：
            <input type="text" name="userword" id="userword">
            <input type="submit" value="提交">
      </form>
</body>
</html>
