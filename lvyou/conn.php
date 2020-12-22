<meta http-equiv="content-type" content="text/html;charset=utf-8">
<?php
    //数据库的配置
    //连接数据库
    //设置数据库的编码
    //编写sql语句
    $db_host = "localhost";
    $db_name = "ly";
    $db_use = "root";
    $db_pwd = "root";
    
    $link = mysql_connect("$db_host","$db_use","$db_pwd");
    if(!$link){
        echo"连接数据库失败";
        exit();
    }

    $select = mysql_select_db($db_name);
    if(!$select){
        echo "选择数据库 $select 失败";
        exit();
    }

    mysql_query("set names utf8");


?>