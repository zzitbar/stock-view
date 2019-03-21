package cn.coderme.stockview.mapper;

import cn.coderme.stockview.dto.StockInfoDto;
import cn.coderme.stockview.entity.StockHistory;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 历史交易数据 Mapper 接口
 * </p>
 *
 * @author Coderme
 * @since 2018-06-21
 */
public interface StockHistoryMapper extends BaseMapper<StockHistory> {

    void importCsv(@Param("filePath") String filePath, @Param("stockType") Integer stockType);

    void clearStockCode(@Param("stockType") Integer stockType);

    List<StockHistory> page(Pagination page, StockInfoDto dto);
}
