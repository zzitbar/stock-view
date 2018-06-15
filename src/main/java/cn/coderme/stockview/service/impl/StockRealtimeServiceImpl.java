package cn.coderme.stockview.service.impl;

import cn.coderme.stockview.entity.StockRealtime;
import cn.coderme.stockview.mapper.StockRealtimeMapper;
import cn.coderme.stockview.service.StockRealtimeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 实时数据 服务实现类
 * </p>
 *
 * @author Coderme
 * @since 2018-06-13
 */
@Service
public class StockRealtimeServiceImpl extends ServiceImpl<StockRealtimeMapper, StockRealtime> implements StockRealtimeService {

    @Override
    public StockRealtime findByStockId(Integer stockId) {
        StockRealtime stockRealtime = new StockRealtime();
        stockRealtime.setStockId(stockId);
        return super.baseMapper.selectOne(stockRealtime);
    }
}
