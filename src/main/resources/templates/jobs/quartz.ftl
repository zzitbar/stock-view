<script>
    $(function () {
        $('#jobsInfoTable').bootstrapTable({
            url: '${request.contextPath}/job/page',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            method: "post",
            toolbar: "#toolbar",
            // pagination: true,
            // sidePagination: "server",
            columns: [{
                field: 'jobDescription',
                title: '任务描述'
            }, {
                field: 'jobName',
                title: '任务名称'
            }, {
                field: 'jobGroup',
                title: '任务所在组',
                visible: false
            }, {
                field: 'jobClassName',
                title: '任务类名',
                visible: false
            }, {
                field: 'triggerName',
                title: '触发器名称',
                visible: false
            }, {
                field: 'triggerGroup',
                title: '触发器组',
                visible: false
            }, {
                field: 'triggerState',
                title: '状态'
            }, {
                field: 'triggerType',
                title: '类型'
            }, {
                field: 'cronExpression',
                title: 'CRON'
            }, {
                field: 'timeZoneId',
                title: '时区',
                visible: false
            }, {
                field: 'prevFireDateTime',
                title: '上一次',
                visible: false
            }, {
                field: 'nextFireDateTime',
                title: '下一次'
            }, {
                field: 'action',
                title: '操作',
                class:"text-nowrap",
                formatter: function (value, row, index) {
                    var html = '<button type="button" class="btn btn-xs btn-primary" onclick="editJob(\'' + row.jobName + '\',\'' + row.jobGroup+'\',\'' + row.cronExpression+'\',\'' + row.jobDescription + '\')"><i class="fa fa-pencil"></i> 修改</button>';
                    html += ' <button type="button" class="btn btn-xs btn-success" onclick="exeJob(\'' + row.jobName + '\',\'' + row.jobGroup + '\')"><i class="fa fa-play"></i> 执行</button>';
                    html += ' <button type="button" class="btn btn-xs btn-danger" onclick="pauseJob(\'' + row.jobName + '\',\'' + row.jobGroup + '\')"><i class="fa fa-pause"></i> 暂停</button>';
                    html += ' <button type="button" class="btn btn-xs btn-info" onclick="resumeJob(\'' + row.jobName + '\',\'' + row.jobGroup + '\')"><i class="fa fa-undo"></i> 恢复</button>';
                    html += ' <button type="button" class="btn btn-xs btn-warning" onclick="deleteJob(\'' + row.jobName + '\',\'' + row.jobGroup + '\')"><i class="fa fa-times"></i> 删除</button>';

                    return html;
                }
            }]
        });
    })

    function search() {
        $('#jobsInfoTable').bootstrapTable("refreshOptions", {});
    }

    function editJob(jobName, groupName, cronExpression, jobDescription) {
        $("#jobClassName").val(jobName);
        $("#jobGroupName").val(groupName);
        $("#cronExpression").val(cronExpression);
        $("#jobDescription").val(jobDescription);
        $('#myModal').modal('show');
    }

    function exeJob(jobName, groupName) {
        var loading = Stock.loading();
        $.post("${request.contextPath}/job/exe", {jobName: jobName, groupName: groupName}, function (result) {
            Stock.endLoading(loading);
            if (result.code == Stock.SUCCESS) {
                Stock.showSuccess("调用成功");
                search();
            } else {
                Stock.showError(result.msg);
            }
        }, "json");
    }

    function pauseJob(jobName, groupName) {
        var loading = Stock.loading();
        $.post("${request.contextPath}/job/pause", {jobName: jobName, groupName: groupName}, function (result) {
            Stock.endLoading(loading);
            if (result.code == Stock.SUCCESS) {
                Stock.showSuccess("操作成功");
                search();
            } else {
                Stock.showError(result.msg);
            }
        }, "json");
    }

    function resumeJob(jobName, groupName) {
        var loading = Stock.loading();
        $.post("${request.contextPath}/job/resume", {jobName: jobName, groupName: groupName}, function (result) {
            Stock.endLoading(loading);
            if (result.code == Stock.SUCCESS) {
                Stock.showSuccess("操作成功");
                search();
            } else {
                Stock.showError(result.msg);
            }
        }, "json");
    }
    function addJob() {
        var loading = Stock.loading();
        $.post("${request.contextPath}/job/add", $("#jobForm").serialize(), function (result) {
            Stock.endLoading(loading);
            if (result.code == Stock.SUCCESS) {
                Stock.showSuccess("操作成功");
                $("#jobClassName").val("");
                $("#jobGroupName").val("");
                $("#cronExpression").val("");
                $("#jobDescription").val("");
                $('#myModal').modal('hide');
                search();
            } else {
                Stock.showError(result.msg);
            }
        }, "json");
    }
</script>
<div id="toolbar">
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">新增</button>
</div>
<table id="jobsInfoTable" data-show-refresh="true"
       data-show-toggle="true"
       data-show-columns="true">
</table>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增定时任务</h4>
            </div>
            <div class="modal-body">
                <form id="jobForm">
                    <div class="form-group">
                        <label for="jobDescription">任务描述</label>
                        <input type="text" class="form-control" id="jobDescription" name="jobDescription" placeholder="任务描述">
                    </div>
                    <div class="form-group">
                        <label for="jobClassName">任务类名</label>
                        <input type="text" class="form-control" id="jobClassName" name="jobClassName" placeholder="任务类名" required>
                    </div>
                    <div class="form-group">
                        <label for="jobGroupName">任务所在组</label>
                        <input type="text" class="form-control" id="jobGroupName" name="jobGroupName" placeholder="任务所在组" required>
                    </div>
                    <div class="form-group">
                        <label for="cronExpression">CRON表达式</label>
                        <input type="text" class="form-control" id="cronExpression" name="cronExpression" placeholder="CRON表达式" required>
                        <p class="help-block"><a href="http://cron.qqe2.com/" target="_blank">在线Cron表达式生成器</a></p>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="addJob()">确定</button>
            </div>
        </div>
    </div>
</div>