package cn.coderme.stockview.dto.live;

import java.util.List;

/**
 * 财经直播消息 http://data.live.126.net/live/185201.json
 * Created By coderme
 * Date:2018/7/6
 * Time:17:04
 */
public class LiveMessageDto {

    /**
     * duration : 34331000
     * messages : [{"msg":{"align":"center","content":"【渝三峡A子公司取得的增值税专用发票被证实为虚开 收到深交所关注函】渝三峡A公告，公司收到深交所关注函：公司6月29日披露，子公司渝三峡化工取得的、已认证抵扣的、由福建省传祺能源科技开具的增值税专用发票被证实为虚开，所涉及发票132份，金额1.31亿元。深交所要求公司说明上述事项是否涉及会计差错更正，是否需要对以前年度收入和利润进行调整，是否存在虚增收入的情形。请公司评估本次重大事项可能导致的行政处罚情形以及对公司当期经营业绩的实际影响。","fontType":"font-weight: normal;","fontColor":"color:;"},"icon":"noicon","id":7606495,"time":"2018-07-06 17:01:59","commentator":{"name":"直播员 首席哥","imgUrl":"http://img2.ph.126.net/6sqcH6lKVpM-XuAKGgJxIg==/6598059327181717490.jpg"},"section":"2018-07-06"},{"msg":{"align":"center","content":"金地集团6月份实现签约金额145.7亿元，同比下降21.64%。","fontType":"font-weight: normal;","fontColor":"color:;"},"icon":"noicon","id":7606496,"time":"2018-07-06 17:02:08","commentator":{"name":"直播员 首席哥","imgUrl":"http://img2.ph.126.net/6sqcH6lKVpM-XuAKGgJxIg==/6598059327181717490.jpg"},"section":"2018-07-06"},{"msg":{"align":"center","content":"【华天科技：拟80亿元在南京投建集成电路先进封测产业基地】华天科技公告，为进一步完善公司产业布局，公司拟在南京投资新建集成电路先进封测产业基地项目。项目总投资80亿元，分三期建设，主要进行存储器、MEMS、人工智能等集成电路产品的封装测试。公司与南京浦口经济开发区管理委员会经友好协商， 于7月6日签订了项目投资协议。","fontType":"font-weight: normal;","fontColor":"color:;"},"icon":"noicon","id":7606497,"time":"2018-07-06 17:02:17","commentator":{"name":"直播员 首席哥","imgUrl":"http://img2.ph.126.net/6sqcH6lKVpM-XuAKGgJxIg==/6598059327181717490.jpg"},"section":"2018-07-06"},{"msg":{"align":"center","content":"【哈尔斯：实控人近期增持7144万元】哈尔斯晚间公告，当日公司控股股东、实际控制人吕强增持112万元。2018年1月15日至今，吕强已累计增持754.6万股，约占公司总股本的1.84%，合计增持金额7144万元，占增持计划总额的71.44%。吕强将继续增持公司股票。","fontType":"font-weight: normal;","fontColor":"color:;"},"icon":"noicon","id":7606498,"time":"2018-07-06 17:02:25","commentator":{"name":"直播员 首席哥","imgUrl":"http://img2.ph.126.net/6sqcH6lKVpM-XuAKGgJxIg==/6598059327181717490.jpg"},"section":"2018-07-06"},{"msg":{"align":"center","content":"国务院发布关于开展2018年国务院大督查的通知，在全面督查中央经济工作会议部署和《政府工作报告》提出目标任务的基础上，重点督查六个方面工作。打好三大攻坚战和实施乡村振兴战略。推进创新驱动发展。深化\u201c放管服\u201d改革。持续扩大内需。持续扩大内需。持续扩大内需。","fontType":"font-weight: normal;","fontColor":"color:;"},"icon":"noicon","id":7606499,"time":"2018-07-06 17:02:30","commentator":{"name":"直播员 首席哥","imgUrl":"http://img2.ph.126.net/6sqcH6lKVpM-XuAKGgJxIg==/6598059327181717490.jpg"},"section":"2018-07-06"}]
     */
    private int duration;
    private List<MessagesBean> messages;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<MessagesBean> getMessages() {
        return messages;
    }

    public void setMessages(List<MessagesBean> messages) {
        this.messages = messages;
    }

    public static class MessagesBean {
        /**
         * msg : {"align":"center","content":"【渝三峡A子公司取得的增值税专用发票被证实为虚开 收到深交所关注函】渝三峡A公告，公司收到深交所关注函：公司6月29日披露，子公司渝三峡化工取得的、已认证抵扣的、由福建省传祺能源科技开具的增值税专用发票被证实为虚开，所涉及发票132份，金额1.31亿元。深交所要求公司说明上述事项是否涉及会计差错更正，是否需要对以前年度收入和利润进行调整，是否存在虚增收入的情形。请公司评估本次重大事项可能导致的行政处罚情形以及对公司当期经营业绩的实际影响。","fontType":"font-weight: normal;","fontColor":"color:;"}
         * icon : noicon
         * id : 7606495
         * time : 2018-07-06 17:01:59
         * commentator : {"name":"直播员 首席哥","imgUrl":"http://img2.ph.126.net/6sqcH6lKVpM-XuAKGgJxIg==/6598059327181717490.jpg"}
         * section : 2018-07-06
         */

        private MsgBean msg;
        private String icon;
        private String id;
        private String time;
        private CommentatorBean commentator;
        private String section;

        public MsgBean getMsg() {
            return msg;
        }

        public void setMsg(MsgBean msg) {
            this.msg = msg;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public CommentatorBean getCommentator() {
            return commentator;
        }

        public void setCommentator(CommentatorBean commentator) {
            this.commentator = commentator;
        }

        public String getSection() {
            return section;
        }

        public void setSection(String section) {
            this.section = section;
        }

        public static class MsgBean {
            /**
             * align : center
             * content : 【渝三峡A子公司取得的增值税专用发票被证实为虚开 收到深交所关注函】渝三峡A公告，公司收到深交所关注函：公司6月29日披露，子公司渝三峡化工取得的、已认证抵扣的、由福建省传祺能源科技开具的增值税专用发票被证实为虚开，所涉及发票132份，金额1.31亿元。深交所要求公司说明上述事项是否涉及会计差错更正，是否需要对以前年度收入和利润进行调整，是否存在虚增收入的情形。请公司评估本次重大事项可能导致的行政处罚情形以及对公司当期经营业绩的实际影响。
             * fontType : font-weight: normal;
             * fontColor : color:;
             */

            private String align;
            private String content;
            private String fontType;
            private String fontColor;

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
        }

        public static class CommentatorBean {
            /**
             * name : 直播员 首席哥
             * imgUrl : http://img2.ph.126.net/6sqcH6lKVpM-XuAKGgJxIg==/6598059327181717490.jpg
             */

            private String name;
            private String imgUrl;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }
    }
}