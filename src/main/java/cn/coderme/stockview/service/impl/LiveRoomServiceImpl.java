package cn.coderme.stockview.service.impl;

import cn.coderme.stockview.entity.LiveRoom;
import cn.coderme.stockview.mapper.LiveRoomMapper;
import cn.coderme.stockview.service.LiveRoomService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * <p>
 * 财经直播间 服务实现类
 * </p>
 *
 * @author Coderme
 * @since 2018-07-06
 */
@Service
public class LiveRoomServiceImpl extends ServiceImpl<LiveRoomMapper, LiveRoom> implements LiveRoomService {

    @Override
    public LiveRoom findByRoomId(String roomId) {
        return super.baseMapper.findByRoomId(roomId);
    }

    @Override
    public LiveRoom findByLiveDate(LocalDate date) {
        return super.baseMapper.findByLiveDate(date);
    }
}
