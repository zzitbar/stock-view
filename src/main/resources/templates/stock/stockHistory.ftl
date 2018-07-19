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
        console.log(ohlc);
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
                split: false,
                shared: true,
            },
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