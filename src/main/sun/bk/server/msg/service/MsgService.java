package main.sun.bk.server.msg.service;

import main.sun.bk.server.msg.model.Msg;
import main.sun.bk.server.msg.model.MsgAll2;

import java.util.List;

/**
 * Created by SUN on 2017/5/1.
 */
public interface MsgService {
    public void addMsg(Msg msg);

    public List<MsgAll2> getAllMsg();

    public void deleteMsgById(int msgId);
}
