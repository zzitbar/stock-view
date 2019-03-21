package cn.coderme.stockview.service;

import cn.coderme.stockview.entity.LiveRoom;
import com.baomidou.mybatisplus.service.IService;

import java.time.LocalDate;

/**
 * <p>
 * 财经直播间 服务类
 * </p>
 *
 * @author Coderme
 * @since 2018-07-06
 */
public interface LiveRoomService extends IService<LiveRoom> {

    LiveRoom findByRoomId(String roomId);

    LiveRoom findByLiveDate(LocalDate date);
}
