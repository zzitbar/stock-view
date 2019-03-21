package cn.coderme.stockview.service;

import cn.coderme.stockview.base.PageDataDto;
import cn.coderme.stockview.dto.live.LiveMessageDto;
import cn.coderme.stockview.dto.live.LiveMessageReqDto;
import cn.coderme.stockview.entity.LiveMessages;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 财经直播消息 服务类
 * </p>
 *
 * @author Coderme
 * @since 2018-07-06
 */
public interface LiveMessagesService extends IService<LiveMessages> {

    LiveMessages findByMsgId(String msgId);

    void insertMessage(String roomId, LiveMessageDto liveMessageDto);

    PageDataDto<LiveMessages> getPage(LiveMessageReqDto dto);
}
