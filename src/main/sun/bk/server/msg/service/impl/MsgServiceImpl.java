package main.sun.bk.server.msg.service.impl;

import main.sun.bk.server.msg.dao.impl.MsgImpl;
import main.sun.bk.server.msg.model.Msg;
import main.sun.bk.server.msg.service.MsgService;

/**
 * Created by SUN on 2017/5/1.
 */
public class MsgServiceImpl implements MsgService{
    private MsgImpl msgImpl = new MsgImpl();
    @Override
    public void addMsg(Msg msg)
    {
        msgImpl.addMsg(msg);
    }
}
