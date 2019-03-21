package cn.coderme.stockview.service.impl;

import cn.coderme.stockview.base.PageDataDto;
import cn.coderme.stockview.dto.StockInfoDto;
import cn.coderme.stockview.entity.StockHistory;
import cn.coderme.stockview.entity.StockRealtime;
import cn.coderme.stockview.mapper.StockHistoryMapper;
import cn.coderme.stockview.service.StockHistoryService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 历史交易数据 服务实现类
 * </p>
 *
 * @author Coderme
 * @since 2018-06-21
 */
@Service
public class StockHistoryServiceImpl extends ServiceImpl<StockHistoryMapper, StockHistory> implements StockHistoryService {

    @Override
    public void importCsv(String filePath, Integer stockType) {
        super.baseMapper.importCsv(filePath, stockType);
        super.baseMapper.clearStockCode(stockType);
    }

    @Override
    public PageDataDto<StockHistory> history(StockInfoDto dto) {
        dto.getPage().setRecords(super.baseMapper.page(dto.pageable().getPage(), dto));
        return new PageDataDto<StockHistory>(dto.getPage());
    }

    @Override
    public void updateHistroy(StockRealtime stockRealtime) {
        StockHistory stockHistory = super.selectOne(new EntityWrapper<StockHistory>()
                .eq("stockCode", stockRealtime.getStockCode())
                .eq("realDate", stockRealtime.getRealDate()));
        if (null == stockHistory) {
            stockHistory = new StockHistory();
        }
        stockHistory.setStockCode(stockRealtime.getStockCode());
        stockHistory.setRealDate(stockRealtime.getRealDate());
        stockHistory.setClose(stockRealtime.getCurrentPrice());
        stockHistory.setHigh(stockRealtime.getHigh());
        stockHistory.setLow(stockRealtime.getLow());
        stockHistory.setOpen(stockRealtime.getOpen());
        stockHistory.setLastClose(stockRealtime.getLastClose());
        stockHistory.setIncrease(stockRealtime.getCurrentPrice()-stockRealtime.getLastClose());
        stockHistory.setIncreaseRate(stockRealtime.getIncreaseRate());
        stockHistory.setDealQty(stockRealtime.getDealQty());
        stockHistory.setDealMoney(stockRealtime.getDealMoney());
        stockHistory.setUpdateTime(LocalDateTime.now());
        super.baseMapper.updateById(stockHistory);
    }
}
