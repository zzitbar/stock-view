package cn.coderme.stockview.service;

import cn.coderme.stockview.entity.StockRealtime;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 实时数据 服务类
 * </p>
 *
 * @author Coderme
 * @since 2018-06-13
 */
public interface StockRealtimeService extends IService<StockRealtime> {

    StockRealtime findByStockId(Integer stockId);
}
