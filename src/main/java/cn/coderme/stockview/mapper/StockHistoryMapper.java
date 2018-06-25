package cn.coderme.stockview.mapper;

import cn.coderme.stockview.entity.StockHistory;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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
}
