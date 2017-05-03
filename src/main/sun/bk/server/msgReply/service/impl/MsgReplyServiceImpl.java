package main.sun.bk.server.msgReply.service.impl;

import main.sun.bk.server.msgReply.dao.impl.MsgReplyImpl;
import main.sun.bk.server.msgReply.model.MsgReply;
import main.sun.bk.server.msgReply.service.MsgReplyService;

import java.util.List;

/**
 * Created by SUN on 2017/5/2.
 */
public class MsgReplyServiceImpl implements MsgReplyService{
    private MsgReplyImpl msgReplyImpl = new MsgReplyImpl();
    @Override
    public void addMsgReply(MsgReply msgReply)
    {
        msgReplyImpl.addMsgReply(msgReply);
    }

    @Override
    public List<MsgReply> getAllMsgReply() {
        List<MsgReply> msgReplyList = msgReplyImpl.getAllMsgReply();
        if(!msgReplyList.isEmpty())
        {
            return msgReplyList;
        }
        return null;
    }

    @Override
    public List<MsgReply> getMsgReplyById(int msgId)
    {
        List<MsgReply> msgReplyList = msgReplyImpl.getMsgReplyById(msgId);
        if(!msgReplyList.isEmpty())
        {
            return msgReplyList;
        }
        return null;
    }
}
