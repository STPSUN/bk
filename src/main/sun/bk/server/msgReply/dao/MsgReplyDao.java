package main.sun.bk.server.msgReply.dao;

import main.sun.bk.server.msgReply.model.MsgReply;

import java.util.List;

/**
 * Created by SUN on 2017/5/2.
 */
public interface MsgReplyDao {
    public boolean addMsgReply(MsgReply msgReply);

    public List<MsgReply> getAllMsgReply();

    public List<MsgReply> getMsgReplyById(int msgId);

}
