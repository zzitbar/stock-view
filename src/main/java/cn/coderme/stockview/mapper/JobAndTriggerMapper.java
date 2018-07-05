package cn.coderme.stockview.mapper;

import cn.coderme.stockview.entity.JobAndTrigger;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import java.util.List;

public interface JobAndTriggerMapper extends BaseMapper<JobAndTrigger> {
	List<JobAndTrigger> getJobAndTriggerDetails(Pagination page);
}