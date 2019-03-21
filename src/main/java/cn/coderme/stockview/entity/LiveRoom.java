package cn.coderme.stockview.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 财经直播间
 * </p>
 *
 * @author Coderme
 * @since 2018-07-06
 */
@TableName(value = "live_room")
public class LiveRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 直播间ID
     */
    @TableField("roomId")
    private String roomId;
    /**
     * 直播日期
     */
    @TableField("liveDate")
    private LocalDate liveDate;
    /**
     * 直播消息URL
     */
    @TableField("liveMsgUrl")
    private String liveMsgUrl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public LocalDate getLiveDate() {
        return liveDate;
    }

    public void setLiveDate(LocalDate liveDate) {
        this.liveDate = liveDate;
    }

    public String getLiveMsgUrl() {
        return liveMsgUrl;
    }

    public void setLiveMsgUrl(String liveMsgUrl) {
        this.liveMsgUrl = liveMsgUrl;
    }

    @Override
    public String toString() {
        return "LiveRoom{" +
        ", id=" + id +
        ", title=" + title +
        ", roomId=" + roomId +
        ", liveDate=" + liveDate +
        ", liveMsgUrl=" + liveMsgUrl +
        "}";
    }
}
