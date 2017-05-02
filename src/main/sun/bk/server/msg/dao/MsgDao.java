package main.sun.bk.server.msg.dao;

import main.sun.bk.server.msg.model.Msg;
import main.sun.bk.server.msg.model.MsgAll;

import java.util.List;

/**
 * Created by SUN on 2017/5/1.
 */
public interface MsgDao {
    public boolean addMsg(Msg msg);

    public List<MsgAll> getAllMsg();
}
