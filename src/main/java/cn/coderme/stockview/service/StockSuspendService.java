package cn.coderme.stockview.service;

import cn.coderme.stockview.entity.StockSuspend;
import com.baomidou.mybatisplus.service.IService;

import java.time.LocalDate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Coderme
 * @since 2018-06-26
 */
public interface StockSuspendService extends IService<StockSuspend> {

    /**
     * 根据股票id和停牌日期查询停牌记录
     * @param stockId
     * @param suspendDate
     */
    StockSuspend findByStockIdAndDate(Integer stockId, LocalDate suspendDate);
}
