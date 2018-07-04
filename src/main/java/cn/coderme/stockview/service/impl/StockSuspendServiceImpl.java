package cn.coderme.stockview.service.impl;

import cn.coderme.stockview.entity.StockSuspend;
import cn.coderme.stockview.mapper.StockSuspendMapper;
import cn.coderme.stockview.service.StockSuspendService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Coderme
 * @since 2018-06-26
 */
@Service
public class StockSuspendServiceImpl extends ServiceImpl<StockSuspendMapper, StockSuspend> implements StockSuspendService {

    @Override
    public StockSuspend findByStockIdAndDate(Integer stockId, LocalDate suspendDate) {
        StockSuspend ss = new StockSuspend();
        ss.setStockId(stockId);
        ss.setSuspendDate(suspendDate);
        return super.baseMapper.selectOne(ss);
    }
}
