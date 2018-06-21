package cn.coderme.stockview.mapper;

import cn.coderme.stockview.dto.hchart.CountDto;
import cn.coderme.stockview.entity.StockRealtime;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 实时数据 Mapper 接口
 * </p>
 *
 * @author Coderme
 * @since 2018-06-13
 */
public interface StockRealtimeMapper extends BaseMapper<StockRealtime> {

    List<CountDto> increaseRange();
}
