package cn.coderme.stockview.service;

import cn.coderme.stockview.entity.StockHistory;
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
     * @param stockCode
     * @return
     */
    List<StockHistory> history(String stockCode);
}
