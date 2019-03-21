package cn.coderme.stockview.dto.live;

import cn.coderme.stockview.base.PageReqDto;

/**
 * Created By Administrator
 * Date:2018/7/9
 * Time:14:23
 */
public class LiveMessageReqDto extends PageReqDto {

    private String roomId;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}