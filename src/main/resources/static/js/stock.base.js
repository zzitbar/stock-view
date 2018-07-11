var Stock = {};
$(function () {
    $(".menu-list").on("click", function (e) {
        var $this = e.target;
        $($this).siblings().removeClass("active");
        $($this).addClass("active");
        var url = $($this).attr("data-url");
        $.get(url, function (result) {
            $("#content").html(result);
        });
    })
    $(".nav-list").on("click", function (e) {
        var $this = e.target;
        $($this).parent().siblings().removeClass("active");
        $($this).parent().addClass("active");
        var url = $($this).attr("data-url");
        $.get(url, function (result) {
            $("#page").html(result);
        });
    })
})

Stock.SUCCESS = 200;
Stock.baseoption = {
    chart: {
           // width: 500,
           // height: 400,
        renderTo: "",
        type: "line"
    },
    title: {
        text: ""
        // center
    },
    subtitle: {
        text: ""
    },
    xAxis: {
        title: {
            text: ""
        },
        categories: [],
        labels: {
            rotation: 0,
            step: 1,
            style: { "color": "#666666", "cursor": "default", "fontSize": "13px" }
        }
    },
    yAxis: {
        title: {
            text: ""
        },
        allowDecimals: false,
        minTickInterval: 1,
        min: 0,
        plotLines: [{
            value: 0,
            width: 1,
            color: "#808080"
        }],
        plotBands: []
    },
    tooltip: {
        valueSuffix: ""
    },
    legend: {
        x: 0,
        y: 0,
        floating: false,
        margin: 0,
        itemStyle: {
            color: 'green',
            fontWeight: 'bold'
        }
    },
    plotOptions: {
        line: {
            dataLabels: {
                enabled: true,
                format: '{point.y} '
            },
            enableMouseTracking: true
        },
        column: {
            dataLabels: {
                enabled: true,
                format: '{point.y} '
            },
            pointPadding: 0.2,
            borderWidth: 0,
            enableMouseTracking: true
        },
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                enabled: true,
                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                style: {
                    "fontSize": "13px",
                    "color": 'green'
                }
            },
//                showInLegend: true,
            startAngle: -180,
            endAngle: 180,
            center: ['50%', '50%']
        }
    },
    credits: {
        enabled: false
    },
//        colors: ['#EEAD0E', '#EE00EE', '#FFB90F', '#FF00FF', '#f15c80', '#8085e8', '#8d4653', '#91e8e1', '#7cb5ec', '#e4d354', '#8d4653'],
    series: [],
    lang: {
        noData: "无数据"
    },
    exporting: {
        filename: 'chart-file'
    }
};
//加载中
Stock.loading = function () {
    return layer.load(0, {shade: [0.8, '#393D49']});
};
Stock.endLoading = function(index) {
    layer.close(index);
};

Stock.showSuccess = function(msg) {
    layer.msg(msg);
};
Stock.showError = function(errorMsg) {
    layer.alert(errorMsg, {icon: 2});
};
Stock.confirm = function (msg, confirmFunc) {
    //询问框
    var confirmMsg=msg?msg:"请确认";
    layer.confirm(confirmMsg, {icon: 3, title:'请确认', btn: ['确定','取消']},
        function(index){
            confirmFunc();
            layer.close(index);
        }, function(index){
            layer.close(index);
        });
};
//深复制对象方法
Stock.deepCloneObj = function (obj) {
    var newObj = {};
    if (obj instanceof Array) {
        newObj = [];
    }
    for (var key in obj) {
        var val = obj[key];
        //newObj[key] = typeof val == 'object' ? arguments.callee(val) : val; //arguments.callee 在哪一个函数中运行，它就代表哪个函数, 一般用在匿名函数中。
        newObj[key] = typeof val == 'object' ? Stock.deepCloneObj(val): val;
    }
    return newObj;
};
Stock.renderChart = function(url, params, options) {
    var loading = Stock.loading();
    $.post(url, params, function (result) {
        Stock.endLoading(loading);
        if (result && result.code == Stock.SUCCESS) {
            var data = result.data;
            if (data instanceof Object) {
                data = [];
                data.push(result.data);
            }
            $.each(data, function (i, val) {
                var option = Stock.deepCloneObj(Stock.baseoption);
                $.extend(true, option, options);
                option.chart.renderTo = val.renderTo;
                option.chart.type = val.type;
                option.title.text = val.chartTitle;
                option.yAxis.title.text = val.yTitle;
                option.yAxis.plotBands = [];
                option.xAxis.categories = val.xAxis;
                // option.xAxis.labels.rotation = rotation;
                if ("pie" == val.type) {
                    option.series = [{
                        name: '人数',
                        type: 'pie',
//            radius: ['50%', '75%'],
                        innerSize: '80%',
                        data: val.pieDtos
                    }];
                } else {
                    option.series = val.chartDataDtos;
                }
                new Highcharts.Chart(option);
            })
        } else {
            Stock.showError(result.errorMsg);
        }
    }, "json");
};