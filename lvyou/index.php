<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>旅游网站</title>
    <link rel="stylesheet" href="./index.css">
    <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.15&key=16bdbbc09c6a657b5e18c364603a0ddd&plugin=AMap.CitySearch&plugin=AMap.CitySearch"></script>
    <script src="./js/text.js"></script>
</head>

<body>
    <div id="content">
        <div id="top-search">
            <ul>
                <li style="width: 65%;">
                    <p style="color: orange; font-weight: bold;font-size: 50px;">一起去旅游网</p>
                </li>
                <li>
                    <table style="margin-left: -480px">
                        <tr>
                            <td style="font-weight: bold;color:tomato;font-size:large">
                                <p>今日天气：</p>
                            </td>
                            <td>
                                <p style="color: skyblue;font-weight:bold" id="weather"></p>
                            </td>
                        </tr>
                    </table>
                </li>
                <li>
                    <table>
                        <tr>
                            <td>
                                <p style="font-weight: bold;color:tomato;font-size:large;margin-left:-100px" id="city">北京市</p>
                            </td>
                        </tr>
                    </table>
                </li>
                <li>
                    <table style="padding-left: 30px">
                        <tr>
                            <td style="color: darkgrey;font-size:large;padding-right:10px">
                                <?php
                                if (!empty($_SESSION['se_user'])) {
                                    echo '欢迎：' . $_SESSION['se_user'];
                                } else {
                                    echo '欢迎，请登陆';
                                }
                                ?>
                            </td>
                            <td>
                                <img src="./img/1.jpg" alt="" width="30px" height="30px">
                            </td>
                            <td style="padding-left: 10px">
                                <form action="denglu.php" method="post">
                                    <a style="color: white" id="lo1" href="./denglu.php">登陆</a>
                                    <input type="hidden" name="login" id="login" value="l" />
                                </form>
                            </td>
                            <td style="padding-left: 20px">
                                <a style="color: white" href="">注册</a>
                            </td>
                        </tr>
                    </table>
                </li>
            </ul>
        </div>
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
            </div>
        </div>
        <div id="map" style="width:100%;height:300px;margin-top:70px">
        </div>
        <div id="wrap">
            <?php
            include "conn.php";
            $page = $_GET['p'];
            if(empty($page)){
                echo '<script> window.location.href="index.php?p=1";    </script>';
            }
            $PageSize = 2;
            //编写sql获取分页数据：SELECT * FROM 表名 LIMIT 起始位置 , 显示条数
            $sql = "SELECT*FROM jd LIMIT " . ($page - 1) * $PageSize . ",$PageSize";
            if (!$sql) {
                echo "取出不成功";
            };
            //把sql语句传送到数据库
            $result = mysql_query($sql);
            //处理我们的数据
            while ($row = mysql_fetch_assoc($result)) { ?>

                <div id="jd">

                    <table>
                        <tr>
                            <a href="#">
                                <th>
                                    <h2><?php echo $row['jd_name'] ?></h2>
                                </th>
                            </a>
                        <tr>
                            <td>
                                <p><?php echo '<h4>景点城市：</h4>' . $row['jd_city'] ?></p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p><?php echo '<h4>景点等级：</h4>' . $row['jd_grade'] ?></p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<?php echo $row['jd_img'] ?>" alt="">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p><?php echo '<h4>景点简介：</h4>' . $row['jd_detail'] ?></p>
                            </td>
                        </tr>
                    </table>
                </div>
            <?php   }  ?>

            <?php
            //释放结果
            mysql_free_result($result);
            //获取数据总数
            $to_sql = "SELECT COUNT(*)FROM jd";
            $to_result = mysql_fetch_array(mysql_query($to_sql));
            $to = $to_result[0];
            //计算页数
            $to_pages = ceil($to / $PageSize);
            /** 3.显示数据+分页条 **/
            $page_banner = "";
            //计算偏移量
            if ($page >= 1) {
                $page_banner .= '<table style="font-weight: bold;text-align: center;padding-top: 50px;">';
                $page_banner .= "<tr>";
                $page_banner .= "<td style='padding-left: 30px;'>";
                $page_banner .= "<a style='color:snow' href='" . $_SERVER['PHP_SELF'] . "?p=1'>首页</a>";
                $page_banner .= "</td>";
                $page_banner .= "<td style='padding-left: 30px;'>";
                $page_banner .= "<a style='color:snow' href='" . $_SERVER['PHP_SELF'] . "?p=" . ($page - 1) . "'>上一页</a>";
                $page_banner .= "</td>";
            }
        
            if ($page < $to_pages) {
                
                $page_banner .= "<td>";
                $page_banner .= "<a style='color:snow;padding-left: 30px;' href='" . $_SERVER['PHP_SELF'] . "?p=" . ($page + 1) . "'>下一页</a>";
                $page_banner .= "</td>";
                $page_banner .= "<td>";
                $page_banner .= "<a style='color:snow;padding-left: 30px;' href='" . $_SERVER['PHP_SELF'] . "?p=" . ($to_pages) . "'>尾页</a>";
                $page_banner .= "</td>";
                
            }
            $page_banner .= "<td style='padding-left: 30px;color:snow'>";
            $page_banner .= "共{$to_pages}页";
            $page_banner .= "</td>";
            $page_banner .= "</tr>";
            $page_banner .= "</table>";
            echo $page_banner;
            ?>
            
        </div>
        <div id="bottom">
            <ul>
                <li>
                    <div id="bottom-left">
                        <span>关于我们</span>
                        <br>
                        <i>一起去旅游是在线旅游内容生产和分发平台，<br>
                            提供及时
                            权威的旅游资讯和丰富的旅游短视频。<br>
                            为旅游者提供个性化旅游查询服务。
                        </i>
                    </div>
                </li>
                <li>
                    <div id="bottom-center">
                        <p style="color: orange; font-weight: bold;font-size: 50px;">一起去旅游网</p>
                    </div>
                </li>
                <li>
                    <div id="bottom-right" style="padding-top: 30px;">
                        <span style="color: grey;font-size: large;font-weight: bold;">关注我们</span>
                        <br>
                        <i>
                            <img style="margin-top: 30px;" src="./img/wx.png" alt="" width="100px" height="100px">
                        </i>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="container">
        <ul class="moon">
            <li class="crater"></li>
            <li class="crater"></li>
            <li class="crater"></li>
        </ul>
        <ul class="mountain-range">
            <li class="mountain"></li>
            <li class="mountain"></li>
            <li class="mountain"></li>
            <li class="mountain"></li>
            <li class="mountain"></li>
            <li class="mountain"></li>
            <li class="mountain"></li>
            <li class="mountain"></li>
            <li class="mountain"></li>
            <li class="mountain"></li>
            <li class="mountain"></li>
        </ul>
        <div class="mountain-range-mask"></div>
        <ul class="forest">
            <li class="hill"></li>
            <li class="hill"></li>
            <li class="hill"></li>
        </ul>
        <ul class="sparkles">
            <li class="sparkle one"></li>
            <li class="sparkle one"></li>
            <li class="sparkle one"></li>
            <li class="sparkle one"></li>
            <li class="sparkle two"></li>
            <li class="sparkle two"></li>
            <li class="sparkle two"></li>
            <li class="sparkle two"></li>
            <li class="sparkle three"></li>
            <li class="sparkle three"></li>
            <li class="sparkle three"></li>
            <li class="sparkle three"></li>
            <li class="sparkle four"></li>
            <li class="sparkle four"></li>
            <li class="sparkle four"></li>
            <li class="sparkle four"></li>
            <li class="sparkle five"></li>
            <li class="sparkle five"></li>
            <li class="sparkle five"></li>
            <li class="sparkle five"></li>
            <li class="sparkle six"></li>
            <li class="sparkle six"></li>
            <li class="sparkle six"></li>
            <li class="sparkle six"></li>
            <li class="sparkle seven"></li>
            <li class="sparkle seven"></li>
            <li class="sparkle seven"></li>
            <li class="sparkle seven"></li>
            <li class="sparkle eight"></li>
            <li class="sparkle eight"></li>
            <li class="sparkle eight"></li>
            <li class="sparkle eight"></li>
        </ul>
        <div class="grass">
            <div class="pokemon">
                <div class="poke" id="bulbasaur">
                    <div class="ear"></div>
                    <div class="ear"></div>
                    <div class="head"></div>
                    <div class="leg"></div>
                    <div class="bulba-body"></div>
                    <div class="bulbs">
                        <div class="bulb"></div>
                    </div>
                </div>
                <div class="poke" id="pikachu">
                    <div class="ear"></div>
                    <div class="ear"></div>
                    <div class="hand"></div>
                    <div class="pika-body"></div>
                    <div class="head"></div>
                    <div class="pika-tail"></div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>