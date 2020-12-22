<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./write.css">
    <script src="js/prefixfree.min.js"></script>
    <title>写游记</title>
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
        <div id="middle">
            <div id="dtitle">
                <input name="title" id="title" type="text" class="pTitle" placeholder="写个标题吧...[可空]..." autocomplete="off" maxlength="50">
            </div>
            <div id="yj_contan">
                <textarea id="dcontent" name="content" rows="5" class="form-control" placeholder="写点儿什么呢？记录下旅游的感受，留住一段时光 ...
😄 😊 😃 😉 😍 😘 😚 😳 😁 😌 😜 😝 😒 😏 😓 😔 😞 😖 😥 😰 😨 😣 ..." style="overflow-x: hidden; word-wrap: break-word; resize: none; height: 400px;width: 50%;" maxlength="8000"></textarea>
            </div>
            <div id="yj_tijiao">
                <button type="submit" class="btn">
                    😊 提 交&nbsp;&nbsp;<i class="icon-chevron-sign-right"></i>
                </button>
            </div>
        </div>
        <div id="right">
            <div id="r-cont">

            </div>
            <div id="r-top">
                <div id="t-tupian">
                <?php
                    include "conn.php";
                    if(!isset($_SESSION['se_user']))
                    {
                      exit();
                    }
                    $username = $_SESSION['se_user'];
                    $sql = "select * from zc_information where username='$username'";
                    $result = mysql_query($sql);
                    $row=mysql_fetch_array($result);
                    echo "<img id='tx' src=".$row["user_img"].">";
                ?>
                </div>

                <div id="r-co">
                  <table style="margin-left: 0px;color:skyblue">
                    <tr style="color:skyblue">
                      <td>
                        <h4>用户：&nbsp;<?php echo $_SESSION['se_user']; ?></h4>
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <h4>收藏数：30</h4>
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <h4>
                          IP：&nbsp;<?php $IP = $_SERVER["REMOTE_ADDR"]; echo "$IP"; ?>
                        </h4>
                      </td>
                    </tr>
                  </table>
                </div>
            </div>
        </div>
        <div id="bottom">

        </div>
        
        <div class="container-wrapper">

            <div class="container">
          
              <div class="circle-container">
                <div class="sky">
                  <div class="sky-night">
                    <div class="stars">
                      <div class="shooting-star"></div>
                      <div class="star-group-1"></div>
                      <div class="star-group-2"></div>
                      <div class="star-group-3"></div>
                      <div class="star-group-4"></div>
                    </div>
          
                  </div>
                  <div class="cloud cloud-1"></div>
                  <div class="cloud cloud-2"></div>
                  <div class="cloud cloud-3"></div>
                  <div class="cloud cloud-4"></div>
                  <div class="cloud cloud-5"></div>
                  <div class="cloud cloud-6"></div>
                  <div class="bird bird-1"></div>
                  <div class="bird bird-2"></div>
                  <div class="bird bird-3"></div>
          
                </div>
          
                <div class="mountains">
                  <div class="mountain-back">
                    <div class="mountain-back-1"></div>
                    <div class="mountain-back-2"></div>
                  </div>
                  <div class="mountain-middle">
                    <div class="mountain-middle-1"></div>
                    <div class="mountain-middle-2"></div>
                  </div>
                  <div class="mountain-top">
                    <div class="mountain-top-1"></div>
                  </div>
                </div>
          
                <div class="sun-container">
                  <div class="sun"></div>
                </div>
          
                <div class="ocean">
                  <div class="boat"></div>
                  <div class="ocean-night">
          
                    <div class="moon"></div>
          
                    <div class="mountains mountains-bottom">
                      <div class="mountain-back">
                        <div class="mountain-back-1"></div>
                        <div class="mountain-back-2"></div>
                      </div>
                      <div class="mountain-middle">
                        <div class="mountain-middle-1"></div>
                        <div class="mountain-middle-2"></div>
                      </div>
                      <div class="mountain-top">
                        <div class="mountain-top-1"></div>
                      </div>
                    </div>
          
                  </div>
                </div>
          
              </div>
          
            </div>
            
          
        </div>
          
          <script src='js/jquery.min.js'></script>
        </script>
          <script src="js/script.js"></script>
    </div>
</body>
</html>