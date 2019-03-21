package cn.coderme.stockview.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p>
 * 财经直播消息
 * </p>
 *
 * @author Coderme
 * @since 2018-07-06
 */
@TableName(value = "live_messages")
public class LiveMessages implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 直播间ID
     */
    @TableField("roomId")
    private String roomId;

    private String align;
    /**
     * 内容
     */
    private String content;
    /**
     * 字体
     */
    @TableField("fontType")
    private String fontType;
    /**
     * 字体颜色
     */
    @TableField("fontColor")
    private String fontColor;
    /**
     * 图标
     */
    private String icon;
    /**
     * 消息ID
     */
    @TableField("msgId")
    private String msgId;
    /**
     * 消息时间
     */
    @TableField("msgTime")
    private LocalDateTime msgTime;
    /**
     * 评论员
     */
    private String commentator;
    /**
     * 评论员头像
     */
    @TableField("commentatorImgUrl")
    private String commentatorImgUrl;
    /**
     * 日期
     */
    private String section;
    /**
     * 修改日期
     */
    @TableField("updateTime")
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private LocalTime messageTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFontType() {
        return fontType;
    }

    public void setFontType(String fontType) {
        this.fontType = fontType;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public LocalDateTime getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(LocalDateTime msgTime) {
        this.msgTime = msgTime;
    }

    public String getCommentator() {
        return commentator;
    }

    public void setCommentator(String commentator) {
        this.commentator = commentator;
    }

    public String getCommentatorImgUrl() {
        return commentatorImgUrl;
    }

    public void setCommentatorImgUrl(String commentatorImgUrl) {
        this.commentatorImgUrl = commentatorImgUrl;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalTime getMessageTime() {
        if (null != this.msgTime) {
            return this.msgTime.toLocalTime();
        } else {
            return messageTime;
        }
    }

    public void setMessageTime(LocalTime messageTime) {
        this.messageTime = messageTime;
    }

    @Override
    public String toString() {
        return "LiveMessages{" +
        ", id=" + id +
        ", roomId=" + roomId +
        ", align=" + align +
        ", content=" + content +
        ", fontType=" + fontType +
        ", fontColor=" + fontColor +
        ", icon=" + icon +
        ", msgId=" + msgId +
        ", msgTime=" + msgTime +
        ", commentator=" + commentator +
        ", commentatorImgUrl=" + commentatorImgUrl +
        ", section=" + section +
        ", updateTime=" + updateTime +
        "}";
    }
}
