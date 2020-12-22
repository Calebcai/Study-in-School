window.onload = function () {
    var map = new AMap.Map("map", {
        resizeEnable: true,
        center: [116.397428, 39.90923],
        zoom: 13
    });
    //获取用户所在城市信息
    function showCityInfo() {
        //实例化城市查询类
        var citysearch = new AMap.CitySearch();
        //自动获取用户IP，返回当前城市
        citysearch.getLocalCity(function (status, result) {
            if (status === 'complete' && result.info === 'OK') {
                if (result && result.city && result.bounds) {
                    var cityinfo = result.city;
                    var citybounds = result.bounds;
                    document.getElementById('city').innerHTML = '您当前所在城市：' + cityinfo;
                    //地图显示当前城市
                    var c = cityinfo;
                    map.setBounds(citybounds);

                    AMap.plugin('AMap.Weather', function() {
                        var weather = new AMap.Weather();
                        //查询实时天气信息, 查询的城市到行政级别的城市，如朝阳区、杭州市
                        weather.getLive(result.info, function(err, data) {
                            var c = result.info;
                            if (!err) {
                                var str = [];
                                str.push('<h4 >实时天气' + '</h4><hr>');
                                str.push('<p>城市/区：' + data.city + '</p>');
                                str.push('<p>天气：' + data.weather + '</p>');
                                str.push('<p>温度：' + data.temperature + '℃</p>');
                                str.push('<p>风向：' + data.windDirection + '</p>');
                                str.push('<p>风力：' + data.windPower + ' 级</p>');
                                str.push('<p>空气湿度：' + data.humidity + '</p>');
                                str.push('<p>发布时间：' + data.reportTime + '</p>');
                                var marker = new AMap.Marker({map: map, position: map.getCenter()});
                                var infoWin = new AMap.InfoWindow({
                                    content: '<div class="info" style="position:inherit;margin-bottom:0;">'+str.join('')+'</div><div class="sharp"></div>',
                                    isCustom:true,
                                    offset: new AMap.Pixel(0, -37)
                                });
                                infoWin.open(map, marker.getPosition());
                                marker.on('mouseover', function() {
                                    infoWin.open(map, marker.getPosition());
                                });
                            }
                        });
                        //未来4天天气预报
                        weather.getForecast(c, function(err, data) {
                            if (err) {return;}
                            var str = [];
                            for (var i = 0,dayWeather; i < 1; i++) {//data.forecasts.length
                                dayWeather = data.forecasts[i];
                                str.push(dayWeather.date+' <span class="weather">'+dayWeather.dayWeather+'</span> '+ dayWeather.nightTemp + '~' + dayWeather.dayTemp + '℃');
                            }
                            document.getElementById('weather').innerHTML = str.join('<br>');
                        });
                    });
                }
            } else {
                document.getElementById('city').innerHTML = result.info;
            }
        });
    }
    showCityInfo();
    /******************************************************************************************* */
}
