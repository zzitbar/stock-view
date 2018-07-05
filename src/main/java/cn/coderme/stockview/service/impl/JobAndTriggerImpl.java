package cn.coderme.stockview.service.impl;

import cn.coderme.stockview.base.PageDataDto;
import cn.coderme.stockview.base.PageReqDto;
import cn.coderme.stockview.entity.JobAndTrigger;
import cn.coderme.stockview.mapper.JobAndTriggerMapper;
import cn.coderme.stockview.service.JobAndTriggerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class JobAndTriggerImpl extends ServiceImpl<JobAndTriggerMapper, JobAndTrigger> implements JobAndTriggerService {

	public PageDataDto<JobAndTrigger> getJobAndTriggerDetails(PageReqDto dto) {
		dto.getPage().setRecords(super.baseMapper.getJobAndTriggerDetails(dto.pageable().getPage()));
		return new PageDataDto<JobAndTrigger>(dto.getPage());
	}

}