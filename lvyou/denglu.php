<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登陆界面</title>
    <link rel="stylesheet" href="./css/login.css">
</head>
<body>
    <div class="dowebok">        
    <div class="logo"></div>

        <form action="#" method="post">
            <div class="form-item">
                <input name="username" type="text" autocomplete="off" placeholder="用户名">
            </div>
            <div class="form-item">
                <input name="password" type="password" autocomplete="off" placeholder="登录密码">
            </div>
            <div class="form-item">
                <input type="submit"  value="登陆">
                <input type="hidden" name = "a" value="login">
            </div>
        </form>
        <div class="reg-bar">
            <a class="reg" href="">立即注册</a>
            <a class="forget" href="">忘记密码</a>
        </div>
    </div>
    
</body>
</html>
<?php

    include "conn.php";

    if (isset($_POST['username']) && $_POST['a'] == "login") 
    {
        $usename = $_POST['username'];
        $pwd = $_POST['password'];
        $sql = "select * from zc_information where username='$usename'and password='$pwd'";
        $return = mysql_query($sql);
        $recode = mysql_num_rows($return);
        
        if($recode)
        {
            $_SESSION['se_user'] = $usename;
            $_SESSION['se_user2'] = $usename;
            echo "<script>window.alert('登陆成功')</script>";
            echo "<script>location.href='index.php';</script>";
        }else
        {
            echo "<script>window.alert('用户名或者密码错误')</script>";
        }
    }

?>