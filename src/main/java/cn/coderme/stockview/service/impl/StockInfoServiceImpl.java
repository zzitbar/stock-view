package cn.coderme.stockview.service.impl;

import cn.coderme.stockview.base.PageDataDto;
import cn.coderme.stockview.base.PageReqDto;
import cn.coderme.stockview.dto.StockInfoDto;
import cn.coderme.stockview.entity.StockInfo;
import cn.coderme.stockview.mapper.StockInfoMapper;
import cn.coderme.stockview.service.StockInfoService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Coderme
 * @since 2018-06-13
 */
@Service
public class StockInfoServiceImpl extends ServiceImpl<StockInfoMapper, StockInfo> implements StockInfoService {

    /**
     * 分页查询
     * @param dto
     * @return
     */
    public PageDataDto<StockInfo> getPage(StockInfoDto dto) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题
        // page.setOptimizeCountSql(false);
        // 不查询总记录数
        // page.setSearchCount(false);
        // 注意！！ 分页 total 是经过插件自动 回写 到传入 page 对象
        dto.getPage().setRecords(super.baseMapper.pageRealTime(dto.pageable().getPage(), dto));
        return new PageDataDto<StockInfo>(dto.getPage());
    }

    /**
     * 根据股票代码和交易市场查询
     * @param stockCode
     * @param market
     * @return
     */
    @Override
    public StockInfo findByStockCodeAndMarket(String stockCode, String market) {
        StockInfo si = new StockInfo();
        si.setStockCode(stockCode);
        si.setMarket(market);
        return super.baseMapper.selectOne(si);
    }
}
