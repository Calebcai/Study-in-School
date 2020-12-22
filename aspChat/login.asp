<%@LANGUAGE="VBSCRIPT" CODEPAGE="65001"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网络聊天室登陆</title>
</head>
	<form method="POST" action="chat.asp">
        <table align="center">
            <tr>
                <th><h2>登陆界面</h2></th>
            </tr>
            <tr>
                <td>
                    用户名：
                </td>
                <td>
                    <input type="text" name="username" id="username">
                </td>
            </tr>
            <tr>
                <td>
                    密码：
                </td>
                <td>
                    <input type="password" name="password">
                </td>
                <td>&nbsp</td>
                <td>
                    <input type="submit"  value="登陆">
                </td>
            </tr>
        </table>
    </form>
<body>
</body>
</html>
