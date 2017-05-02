package main.sun.bk.server.msgReply.service;

import main.sun.bk.server.msgReply.model.MsgReply;

import java.util.List;

/**
 * Created by SUN on 2017/5/2.
 */
public interface MsgReplyService {
    public void addMsgReply(MsgReply msgReply);

    public List<MsgReply> getAllMsgReply();
}
