package cn.coderme.stockview.mapper;

import cn.coderme.stockview.dto.live.LiveMessageReqDto;
import cn.coderme.stockview.entity.LiveMessages;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 财经直播消息 Mapper 接口
 * </p>
 *
 * @author Coderme
 * @since 2018-07-06
 */
public interface LiveMessagesMapper extends BaseMapper<LiveMessages> {

    //https://www.cnblogs.com/orac/p/6726323.html
    //Mybatis异常There is no getter for property named 'XXX' in 'class java.lang.String'
    LiveMessages findByMsgId(@Param(value = "msgId") String msgId);

    List<LiveMessages> getPage(Page page, LiveMessageReqDto dto);
}
