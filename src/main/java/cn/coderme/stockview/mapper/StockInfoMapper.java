package cn.coderme.stockview.mapper;

import cn.coderme.stockview.dto.StockInfoDto;
import cn.coderme.stockview.entity.StockInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Coderme
 * @since 2018-06-13
 */
public interface StockInfoMapper extends BaseMapper<StockInfo> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<StockInfo> page(Pagination page);

    List<StockInfo> pageRealTime(Pagination page, StockInfoDto dto);
}
