package cn.coderme.stockview.mapper;

import cn.coderme.stockview.entity.LiveRoom;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * <p>
 * 财经直播间 Mapper 接口
 * </p>
 *
 * @author Coderme
 * @since 2018-07-06
 */
public interface LiveRoomMapper extends BaseMapper<LiveRoom> {

    LiveRoom findByRoomId(@Param(value = "roomId") String roomId);

    LiveRoom findByLiveDate(@Param(value = "liveDate") LocalDate liveDate);
}
