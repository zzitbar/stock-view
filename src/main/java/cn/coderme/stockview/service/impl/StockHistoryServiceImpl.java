package cn.coderme.stockview.service.impl;

import cn.coderme.stockview.entity.StockHistory;
import cn.coderme.stockview.mapper.StockHistoryMapper;
import cn.coderme.stockview.service.StockHistoryService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
    }

    @Override
    public List<StockHistory> history(String stockCode) {
        return super.baseMapper.selectList(new EntityWrapper<StockHistory>().eq("stockCode", stockCode).orderBy("realDate", true));
    }
}
