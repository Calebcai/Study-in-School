<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./jd_video.css">
    <title>景区热点</title>
</head>

<body>
    <div id="wrap">
        <div id="daohanglan">
            <div id="daohanglan-1">
                <ul>
                    <li id="l1">
                        <a href="index.php">推荐景点</a>
                    </li>
                    <li id="l2">
                        <a href="jd_video.php">旅游热点</a>
                    </li>
                    <li id="l4">
                        <a href="#">游记列表</a>
                    </li>
                    <li id="l4">
                        <a href="#">个人中心</a>
                    </li>
                    <li id="l5">
                        <a href="write.php">写游记</a>
                    </li>
                </ul>
                <p style="font-weight: bold;color: white;text-align: center;"></p>
            </div>
        </div>
        <div id="video-cont">
            <div id="v-left">
                <video controls="controls">
                    <source src="movie.ogg" type="video/ogg">
                    <source src="video\2.mp4" type="video/mp4">
                </video>
            </div>
            <div id="v-right">
                <!-- 循环读出数据库内容 -->
                <?php
                include "conn.php";
                $sql_news = "select * from jd_news";
                $result = mysql_query($sql_news);
                while ($news = mysql_fetch_assoc($result)) { ?>
                    <div id="v-play-1">
                        <a id="v1" href="">
                            <video controls="controls">
                                <source src="movie.ogg" type="video/ogg">
                                <source src="<?php echo $news['hot_videos'] ?>" type="video/mp4">
                            </video>
                        </a>
                        <div id="v-play-2">
                            <table>
                                <tr>
                                    <th style="padding-right: 10px;">
                                        <a href="#" target="_self"><?php echo $news['news_title'] ?></a>
                                    </th>
                                    <td>
                                        <a href=""><?php echo $news['jd_news'] ?></a>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                <?php } ?>
            </div>

        </div>
        <div id="bottom">
            <?php
            include "conn.php";
            $str = '';
            $str2 = '';
            if(!empty($_SESSION['se_user2']))
            {
                $username = $_SESSION['se_user2'];
            }
            if (!empty($username)) {
                echo "<h2 id='wc'>" . "欢迎用户:" . $username . "</h2>";
                $str2 .= '<div id="lyb">';
                $str2 .= '<h1 style="margin-left: -30px">留言板</h1>';
                $str2 .= '<br>';
                $page = $_GET['p'];
                if (empty($page)) {
                    echo '<script> window.location.href="jd_video.php?p=1";    </script>';
                }
                $PageSize = 3;
                //编写sql获取分页数据：SELECT * FROM 表名 LIMIT 起始位置 , 显示条数
                $sql = "SELECT*FROM liuyan LIMIT " . ($page - 1) * $PageSize . ",$PageSize";
                if (!$sql) {
                    echo "取出不成功";
                };
                //把sql语句传送到数据库
                $result = mysql_query($sql);
                while ($row = mysql_fetch_assoc($result)) {
                    $str2 .= '<table>';

                    $str2 .= '<tr>';
                    $str2 .= '<td>';
                    $str2 .= '留言时间:';
                    $str2 .= '</td>';
                    $str2 .= '<td>';
                    $str2 .= $row["ly_time"];
                    $str2 .= '</td>';
                    $str2 .= '</tr>';

                    $str2 .= '<tr>';
                    $str2 .= '<td>';
                    $str2 .= '留言用户:';
                    $str2 .= '</td>';
                    $str2 .= '<td>';
                    $str2 .= $row["username"];
                    $str2 .= '</td>';
                    $str2 .= '</tr>';

                    $str2 .= '<tr>';
                    $str2 .= '<td>';
                    $str2 .= '留言内容:';
                    $str2 .= '</td>';
                    $str2 .= '<td style="width:250px">';
                    $str2 .= $row["ly_contant"];
                    $str2 .= '</td>';
                    $str2 .= '</tr>';
                    $str2 .= '</table>';
                    $str2 .= '<br>';$str2 .= '<br>';
                }
                mysql_free_result($result);
                //获取数据总数
                $to_sql = "SELECT COUNT(*)FROM liuyan";
                $to_result = mysql_fetch_array(mysql_query($to_sql));
                $to = $to_result[0];
                $page_banner = "";
                $to_pages = ceil($to / $PageSize);
                if ($page >= 1) {
                    $page_banner .= "<span class='d'>";
                    $page_banner .= "<a id='ds1' href='" . $_SERVER['PHP_SELF'] . "?p=" . ($page - 1) . "'>上一页</a>";
                }
                if ($page < $to_pages) {
                    $page_banner .= "<a class='ds' style='padding-left:30px'; href='" . $_SERVER['PHP_SELF'] . "?p=" . ($page + 1) . "'>下一页</a>";
                    $page_banner .= "<a class='ds' style=' margin-left: -6px;';>共{$to_pages}页</a>";
                    $page_banner .= "</span>";
                }
                echo $page_banner;
                $str2 .= '<form action="#" method="post">';
                $str2 .= '<textarea name="ly" id="ly" cols="100" rows="11"></textarea>';
                $str2 .= '<span id="tijiao">';
                $str2 .= '<input id="tijiao2" name="tj" type="submit" value="提交留言">';
                $str2 .= '</span>';
                $str2 .= '</form>';
                $str2 .= '</div>';
                echo $str2;
            } else {
                $str .= '<div id="tip">';
                $str .= '<table>';
                $str .= '<tr>';
                $str .= '<th><h2>你需要登陆或注册才能留言</h2></th>';
                $str .= '<td style="padding-left: 30px"><button><a href="denglu.php">登陆</a></button></td>';
                $str .= '<td style="padding-left: 30px"><button>注册</button></td>';
                $str .= '</tr>';
                $str .= '</table>';
                $str .= '</div>';
                echo $str;
            }
            if (isset($_POST['tj']) == "提交留言") {
                if(empty($_POST['ly']))
                {exit();}
                $lycont = $_POST['ly'];
                $time = date('Y-m-d h:i:s', time());
                $sql_ly = "insert into liuyan (username,ly_time,ly_contant)value('$username','$time','$lycont')";
                $result=mysql_query($sql_ly);
            }
            ?>
        </div>
    </div>
    





</body>

</html>