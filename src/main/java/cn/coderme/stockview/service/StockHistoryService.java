package cn.coderme.stockview.service;

import cn.coderme.stockview.base.PageDataDto;
import cn.coderme.stockview.dto.StockInfoDto;
import cn.coderme.stockview.entity.StockHistory;
import cn.coderme.stockview.entity.StockRealtime;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 历史交易数据 服务类
 * </p>
 *
 * @author Coderme
 * @since 2018-06-21
 */
public interface StockHistoryService extends IService<StockHistory> {

    void importCsv(String filePath, Integer stockType);

    /**
     * 获取股票历史记录
     * @return
     */
    PageDataDto<StockHistory> history(StockInfoDto dto);

    /**
     * 插入或更新历史交易记录
     * @param stockRealtime
     */
    void updateHistroy(StockRealtime stockRealtime);
}
