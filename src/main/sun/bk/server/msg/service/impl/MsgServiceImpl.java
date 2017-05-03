package main.sun.bk.server.msg.service.impl;

import main.sun.bk.server.msg.dao.impl.MsgImpl;
import main.sun.bk.server.msg.model.Msg;
import main.sun.bk.server.msg.model.MsgAll2;
import main.sun.bk.server.msg.service.MsgService;
import main.sun.bk.server.msgReply.dao.impl.MsgReplyImpl;
import main.sun.bk.server.msgReply.model.MsgReply;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/5/1.
 */
public class MsgServiceImpl implements MsgService{
    private MsgImpl msgImpl = new MsgImpl();
    private MsgReplyImpl msgReplyImpl = new MsgReplyImpl();
    @Override
    public void addMsg(Msg msg)
    {
        msgImpl.addMsg(msg);
    }

    @Override
    public List<MsgAll2> getAllMsg()
    {
        List<MsgAll2> msgAllList = new ArrayList<MsgAll2>();
        List<Msg> msgList = msgImpl.getAllMsg();
        if(!msgList.isEmpty())
        {
            for(Msg msg : msgList)
            {
                MsgAll2 msgAll2 = new MsgAll2();
                msgAll2.setMsgId(msg.getMsgId());
                msgAll2.setContent(msg.getContent());
                msgAll2.setUserName(msg.getUserName());
                msgAll2.setMsgTime(msg.getMsgTime());
                List<MsgReply> msgReplyList = msgReplyImpl.getMsgReplyById(msg.getMsgId());
                if(!msgReplyList.isEmpty())
                {
                    msgAll2.setMsgReplyList(msgReplyList);
                }

                msgAllList.add(msgAll2);
            }
            return msgAllList;
        }
        return null;
    }

    @Override
    public void deleteMsgById(int msgId)
    {
        msgImpl.deleteMsgById(msgId);
    }
}