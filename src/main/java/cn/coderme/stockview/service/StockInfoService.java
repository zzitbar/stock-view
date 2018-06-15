package cn.coderme.stockview.service;

import cn.coderme.stockview.base.PageDataDto;
import cn.coderme.stockview.base.PageReqDto;
import cn.coderme.stockview.dto.StockInfoDto;
import cn.coderme.stockview.entity.StockInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Coderme
 * @since 2018-06-13
 */
public interface StockInfoService extends IService<StockInfo> {

    /**
     * 分页查询
     * @param dto
     * @return
     */
    PageDataDto<StockInfo> getPage(StockInfoDto dto);
}
