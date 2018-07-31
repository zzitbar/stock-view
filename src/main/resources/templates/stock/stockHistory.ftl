<script>
    Highcharts.setOptions({
        lang: {
            rangeSelectorZoom: ''
        }
    });
    $.post('${request.contextPath}/stockInfo/history/${stockId}', function (data) {
        if(data.code !== Stock.SUCCESS) {
            Stock.showError("读取股票数据失败");
            return false;
        }
        data = data.data;
        var ohlc = [],
                volume = [],
                dataLength = data.length,
                // set the allowed units for data grouping
                groupingUnits = [[
                    'week',                         // unit name
                    [1]                             // allowed multiples
                ], [
                    'month',
                    [1, 2, 3, 4, 6]
                ]],
                i = 0;
        for (i; i < dataLength; i += 1) {
            ohlc.push([
                data[i].timestamp, // the date
                data[i].open, // open
                data[i].high, // high
                data[i].low, // low
                data[i].close // close
            ]);
            volume.push([
                data[i].timestamp, // the date
                data[i].dealQty // the volume
            ]);
        }
        // create the chart
        var chart = Highcharts.stockChart('container', {
            rangeSelector: {
                selected: 1,
                inputDateFormat: '%Y-%m-%d'
            },
            title: {
                text: '平安银行历史股价'
            },
            xAxis: {
                dateTimeLabelFormats: {
                    millisecond: '%H:%M:%S.%L',
                    second: '%H:%M:%S',
                    minute: '%H:%M',
                    hour: '%H:%M',
                    day: '%m-%d',
                    week: '%m-%d',
                    month: '%y-%m',
                    year: '%Y'
                }
            },
            tooltip: {
                formatter: function() {
                    console.log(data);
                    console.log(this.points[0]);
                    var open = this.points[0].point.open.toFixed(2);
                    var high = this.points[0].point.high.toFixed(2);
                    var low = this.points[0].point.low.toFixed(2);
                    var close = this.points[0].point.close.toFixed(2);
                    var y = (this.points[1].point.y*0.0001).toFixed(2);
                    var tip= '<b>'+ Highcharts.dateFormat('%Y-%m-%d  %A', this.x) +'</b><br/>';
                    tip += '开盘价：<span style="color: #DD2200;">'+open+' </span><br/>';
                    tip += '最高价：<span style="color: #DD2200;">'+high+' </span><br/>';
                    tip += '最低价：<span style="color: #DD2200;">'+low+' </span><br/>';
                    tip += '收盘价：<span style="color: #DD2200;">'+close+' </span><br/>';
                    // tip += '涨跌额：<span style="color: #DD2200;">'+zde+' </span><br/>';
                    return tip;
                }
            },
            // tooltip: {
            //     split: false,
            //     shared: true,
            // },
            yAxis: [{
                labels: {
                    align: 'right',
                    x: -3
                },
                title: {
                    text: '股价'
                },
                height: '65%',
                resize: {
                    enabled: true
                },
                lineWidth: 2
            }, {
                labels: {
                    align: 'right',
                    x: -3
                },
                title: {
                    text: '成交量'
                },
                top: '65%',
                height: '35%',
                offset: 0,
                lineWidth: 2
            }],
            series: [{
                type: 'candlestick',
                name: '平安银行',
                color: 'green',
                lineColor: 'green',
                upColor: 'red',
                upLineColor: 'red',
                tooltip: {
                },
                navigatorOptions: {
                    color: Highcharts.getOptions().colors[0]
                },
                data: ohlc,
                dataGrouping: {
                    units: groupingUnits
                },
                id: 'sz'
            },{
                type: 'column',
                name: '成交量',
                data: volume,
                yAxis: 1,
                dataGrouping: {
                    units: groupingUnits
                }
            }]
        });
    }, "json");
</script>
<div id="container" style="min-width:400px;height:400px">图表加载中...</div>