<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script>
    var app;
    $(function () {
        // $.getJSON("http://api.money.126.net/data/feed/1399001,1399300,0000001,1399006,1399005?callback=_ntes_quote_callback", function(data) {
        //     console.log("1", data);
        // });
        $(".nav-list").on("click", function (e) {
            var $this = e.target;
            $($this).parent().siblings().removeClass("active");
            $($this).parent().addClass("active");
            var url = $($this).attr("data-url");
            $.get(url, function (result) {
                $("#page").html(result);
            });
        })
        loaddata();
        app = new Vue({
            el: '#app',
            data: {
                a: "123",
                items: [],
                plateItems: [],
                hangyedata: {list:[]},
                gainiandata: {list:[]}
            },
            methods: {},
            created: function () {
                setInterval("loaddata()", "5000");
            }
        })
    })

    function _ntes_quote_callback(data) {
        console.log("2", data)
    }

    //http://api.money.126.net/data/feed/RANK_SC_PLATE_HANGYE_ALL,RANK_SC_PLATE_GAINIAN_ALL,HSRANK_COUNT_SHA,,HSRANK_COUNT_SZA,HSRANK_COUNT_SH3,
    function loaddata() {
        $.ajax({
            type: "get",
            url: "http://api.money.126.net/data/feed/1399001,1399300,0000001,1399006,1399005,RANK_SC_PLATE_HANGYE_ALL,RANK_SC_PLATE_GAINIAN_ALL,HSRANK_COUNT_SHA,,HSRANK_COUNT_SZA,HSRANK_COUNT_SH3",
            dataType: "jsonp",
            // jsonp: "_ntes_quote_callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(默认为:callback)
            // jsonpCallback:"_ntes_quote_callback",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名
            success: function (data) {
                var items = [];
                data["1399006"].stockdata = {};
                data["1399006"].hasstockdata = false;
                data["1399005"].stockdata = {};
                data["1399005"].hasstockdata = false;
                data["0000001"].stockdata = data["HSRANK_COUNT_SHA"];
                data["0000001"].hasstockdata = true;
                data["1399001"].stockdata = data["HSRANK_COUNT_SZA"];
                data["1399001"].hasstockdata = true;
                data["1399300"].stockdata = data["HSRANK_COUNT_SH3"];
                data["1399300"].hasstockdata = true;
                $.each(data, function (i, val) {
                    // app.$data["stock"+i] = val;
                    if (i=="1399001" || i=="1399300" || i=="0000001" || i=="1399006" || i=="1399005") {
                        items.push(val);
                    }
                    if (i=="RANK_SC_PLATE_HANGYE_ALL") {
                        app.$data.hangyedata = val;
                    } else if (i=="RANK_SC_PLATE_GAINIAN_ALL") {
                        app.$data.gainiandata = val;
                    }
                })
                items.sort(compare);
                app.$data.items = items;
            },
            error: function () {
                alert('请求失败');
            }
        });
    }

    var compare = function (obj1, obj2) {
        var val1 = obj1.symbol;
        var val2 = obj2.symbol;
        if (val1 < val2) {
            return -1;
        } else if (val1 > val2) {
            return 1;
        } else {
            return 0;
        }
    }
</script>
<div class="row">
    <div class="col-xs-4 col-md-3">
        <#include "./live/index.ftl">
    </div>
    <div class="col-xs-8 col-md-9">
        <div class="container-fluid" style="background: #ffffff" id="app">
            <div class="row">
                <div class="col-md-4" v-for="item in items" style="margin: 15px 0">
                    <div class=" stock-panel">
                        <p class="stock-name">{{item.name}} ({{item.code}})</p>
                        <p class="stock-price" v-bind:class="{ 'text-green': (item.updown<0), 'text-red': (item.updown>=0) }">{{item.price}} {{item.arrow}}</p>
                        <#--<div class="row" v-if="item.hasstockdata">-->
                            <#--<div class="col-md-4 text-red text-center">-->
                                <#--涨: {{item.stockdata.up}}-->
                            <#--</div>-->
                            <#--<div class="col-md-4 text-green text-center">-->
                                <#--跌: {{item.stockdata.down}}-->
                            <#--</div>-->
                            <#--<div class="col-md-4 text-muted text-center">-->
                                <#--平: {{item.stockdata.equal}}-->
                            <#--</div>-->
                        <#--</div>-->
                        <div class="progress" v-if="item.hasstockdata">
                            <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" v-bind:title="'涨: '+item.stockdata.up"
                                 aria-valuemin="0" aria-valuemax="100" v-bind:style="{width: ((item.stockdata.up/item.stockdata.total)*100).toFixed(2)+ '%'}">
                                涨: {{item.stockdata.up}}
                            </div>
                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60" v-bind:title="'跌: '+item.stockdata.down"
                                 aria-valuemin="0" aria-valuemax="100" v-bind:style="{width: ((item.stockdata.down/item.stockdata.total)*100).toFixed(2)+ '%'}">
                                跌: {{item.stockdata.down}}
                            </div>
                            <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="60" v-bind:title="'平: '+item.stockdata.equal"
                                 aria-valuemin="0" aria-valuemax="100" v-bind:style="{width: ((item.stockdata.equal/item.stockdata.total)*100).toFixed(2)+ '%'}">
                                平: {{item.stockdata.equal}}
                            </div>
                        </div>
                        <hr class="text-info">
                        <table class="table table-no-bordered stock-percent" v-bind:class="{ 'text-green': (item.updown<0), 'text-red': (item.updown>=0) }">
                            <tr>
                                <td>涨跌额</td>
                                <td class="text-right">{{item.updown}}</td>
                            </tr>
                            <tr>
                                <td>涨跌幅</td>
                                <td class="text-right">{{(item.percent*100).toFixed(2)+'%'}}</td>
                            </tr>
                            <tr>
                                <td>成交额</td>
                                <td class="text-right">{{(item.turnover/100000000).toFixed(2)+'亿'}}</td>
                            </tr>
                            <tr>
                                <td>成交量</td>
                                <td class="text-right">{{(item.turnover/1000000).toFixed(2)+'万手'}}</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <hr>
            <div class="row" v-if="hangyedata.list.length > 0">
                <p>行业板块涨跌</p>
                <div class="col-md-4" v-for="item in hangyedata.list">
                    <div class="well">
                        <p class="stock-name">{{item.NAME}} ({{item.STOCK_COUNT}} 只)</p>
                        <p class="stock-price" v-bind:class="{ 'text-green': (item.PERCENT<0), 'text-red': (item.PERCENT>=0) }">{{((item.PERCENT)*100).toFixed(2)}} %</p>
                        <div class="row">
                            <div class="col-md-4 text-red text-center">
                                涨: {{item.UPNUM}}
                            </div>
                            <div class="col-md-4 text-green text-center">
                                跌: {{item.DOWNNUM}}
                            </div>
                            <div class="col-md-4 text-muted text-center">
                                平: {{item.STOCK_COUNT-item.UPNUM-item.DOWNNUM}}
                            </div>
                        </div>
                    </div>
                </div>
                <p></p>
            </div>
        </div>
    </div>
</div>