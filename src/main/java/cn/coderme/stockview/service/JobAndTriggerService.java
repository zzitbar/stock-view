package cn.coderme.stockview.service;

import cn.coderme.stockview.base.PageDataDto;
import cn.coderme.stockview.base.PageReqDto;
import cn.coderme.stockview.entity.JobAndTrigger;

public interface JobAndTriggerService {
    PageDataDto<JobAndTrigger> getJobAndTriggerDetails(PageReqDto dto);
}