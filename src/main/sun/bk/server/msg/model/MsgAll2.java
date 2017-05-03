package main.sun.bk.server.msg.model;

import main.sun.bk.server.msgReply.model.MsgReply;

import java.util.List;

/**
 * Created by SUN on 2017/5/3.
 */
public class MsgAll2 {
    private int msgId;
    private String userName;
    private String msgTime;
    private String content;
    private List<MsgReply> msgReplyList;

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<MsgReply> getMsgReplyList() {
        return msgReplyList;
    }

    public void setMsgReplyList(List<MsgReply> msgReplyList) {
        this.msgReplyList = msgReplyList;
    }
}
