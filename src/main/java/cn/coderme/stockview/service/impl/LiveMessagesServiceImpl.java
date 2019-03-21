package cn.coderme.stockview.service.impl;

import cn.coderme.stockview.base.PageDataDto;
import cn.coderme.stockview.dto.live.LiveMessageDto;
import cn.coderme.stockview.dto.live.LiveMessageReqDto;
import cn.coderme.stockview.entity.LiveMessages;
import cn.coderme.stockview.mapper.LiveMessagesMapper;
import cn.coderme.stockview.service.LiveMessagesService;
import cn.coderme.stockview.utils.WebSocketUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 财经直播消息 服务实现类
 * </p>
 *
 * @author Coderme
 * @since 2018-07-06
 */
@Service
public class LiveMessagesServiceImpl extends ServiceImpl<LiveMessagesMapper, LiveMessages> implements LiveMessagesService {

    @Autowired
    private WebSocketUtils webSocketUtils;

    @Override
    public LiveMessages findByMsgId(String msgId) {
        return super.baseMapper.findByMsgId(msgId);
    }

    public void insertMessage(String roomId, LiveMessageDto liveMessageDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<LiveMessages> messagesList = new ArrayList<>();
        for (LiveMessageDto.MessagesBean mb : liveMessageDto.getMessages()) {
            LiveMessages lm = this.findByMsgId(mb.getId());
//            LiveMessages lm = null;
            if (null == lm) {
                lm = new LiveMessages();
                lm.setRoomId(roomId);
                if (null != mb.getMsg()) {
                    LiveMessageDto.MessagesBean.MsgBean msb = mb.getMsg();
                    lm.setAlign(msb.getAlign());
                    lm.setContent(msb.getContent());
                    lm.setFontColor(msb.getFontColor());
                    lm.setFontType(msb.getFontType());
                }
                lm.setIcon(mb.getIcon());
                lm.setMsgId(mb.getId());
                lm.setMsgTime(LocalDateTime.parse(mb.getTime(), formatter));
                if (null != mb.getCommentator()) {
                    LiveMessageDto.MessagesBean.CommentatorBean cb = mb.getCommentator();
                    lm.setCommentator(cb.getName());
                    lm.setCommentatorImgUrl(cb.getImgUrl());
                }
                lm.setSection(mb.getSection());
                lm.setUpdateTime(LocalDateTime.now());
                messagesList.add(lm);
            }
        }
        if (messagesList.size()>0) {
            super.insertOrUpdateAllColumnBatch(messagesList, messagesList.size());
            // websocket群发消息
//            LiveMessageWebSocket.sendAll(JSON.toJSONString(messagesList));
            webSocketUtils.liveMessage(JSON.toJSONString(messagesList));
        }
    }

    @Override
    public PageDataDto<LiveMessages> getPage(LiveMessageReqDto dto) {
        dto.getPage().setRecords(super.baseMapper.getPage(dto.pageable().getPage(), dto));
        return new PageDataDto<LiveMessages>(dto.getPage());
    }
}
