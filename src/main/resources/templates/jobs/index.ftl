<script>
    $(function () {
        $('#jobsInfoTable').bootstrapTable({
            url: '${request.contextPath}/jobs/page',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            method: "post",
            // pagination: true,
            // sidePagination: "server",
            columns: [{
                field: 'jobInfo',
                title: '任务描述'
            }, {
                field: 'method',
                title: '方法名'
            }, {
                field: 'beanName',
                title: 'Bean名称'
            }, {
                field: 'status',
                title: '状态',
                formatter: function (value, row, index) {
                    if (value == '10') {
                        return '<span class="label label-success">运行中</span>'
                    } else if (value == '20') {
                        return '<span class="label label-primary">已暂停</span>'
                    } else {
                        return value;
                    }
                }
            }, {
                field: 'cron',
                title: 'cron'
            }, {
                field: 'fixedDelay',
                title: '延迟'
            }, {
                field: 'fixedRate',
                title: '频率'
            }, {
                field: 'action',
                title: '操作',
                formatter: function (value, row, index) {
                    var html = '<button type="button" class="btn btn-xs btn-primary" onclick="exeJob(\''+row.method+'\',\''+row.beanName+'\')"><i class="fa fa-play"></i> 执行</button>';
                    if (row.status=='10') {
                        html += ' <button type="button" class="btn btn-xs btn-danger" onclick="pauseJob(\''+row.method+'\',\''+row.beanName+'\')"><i class="fa fa-pause"></i> 暂停</button>';
                    } else if (row.status=='20') {
                        html += ' <button type="button" class="btn btn-xs btn-success" onclick="resumeJob(\''+row.method+'\',\''+row.beanName+'\')"><i class="fa fa-undo"></i> 恢复</button>';
                    }
                    return html;
                }
            }]
        });
    })
    function search() {
        $('#jobsInfoTable').bootstrapTable("refreshOptions",{
        });
    }
    function exeJob(methodName, beanName) {
        var loading = Stock.loading();
        $.post("${request.contextPath}/jobs/exe", {methodName:methodName, beanName:beanName}, function (result) {
            Stock.endLoading(loading);
            if (result.code == Stock.SUCCESS) {
                Stock.showSuccess("调用成功");
            } else {
                Stock.showError(result.msg);
            }
        }, "json");
    }
    function pauseJob(methodName, beanName) {
        var loading = Stock.loading();
        $.post("${request.contextPath}/jobs/pause", {methodName:methodName, beanName:beanName}, function (result) {
            Stock.endLoading(loading);
            if (result.code == Stock.SUCCESS) {
                Stock.showSuccess("操作成功");
                search();
            } else {
                Stock.showError(result.msg);
            }
        }, "json");
    }
    function resumeJob(methodName, beanName) {
        var loading = Stock.loading();
        $.post("${request.contextPath}/jobs/resume", {methodName:methodName, beanName:beanName}, function (result) {
            Stock.endLoading(loading);
            if (result.code == Stock.SUCCESS) {
                Stock.showSuccess("操作成功");
                search();
            } else {
                Stock.showError(result.msg);
            }
        }, "json");
    }
</script>
<table id="jobsInfoTable">
</table>