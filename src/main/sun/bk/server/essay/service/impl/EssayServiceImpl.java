package main.sun.bk.server.essay.service.impl;

import main.sun.bk.server.essay.dao.impl.EssayImpl;
import main.sun.bk.server.essay.model.Essay;
import main.sun.bk.server.essay.service.EssayService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/4/24.
 */
public class EssayServiceImpl implements EssayService{

    private EssayImpl essayImpl = new EssayImpl();
    public void addEssay(Essay essay)
    {
        essayImpl.addEssay(essay);
    }

    public List<Essay> getAllEssayByRecommend(String recommend)
    {
        List<Essay> essayList = essayImpl.findEssayByRecommend(recommend);

        return essayList;
    }

    public List<Essay> getAllEssayLikeTitle(String title)
    {
        List<Essay> essayList = new ArrayList<Essay>();
        essayList = essayImpl.findAllEssayByLikeTitle(title);

        return essayList;
    }

    public List<Essay> getAllEssayByType(String type)
    {
        List<Essay> essayList = essayImpl.findEssayByType(type);

        return essayList;
    }

    public List<Essay> getAllEssayByZanDesc()
    {
        List<Essay> essayList = essayImpl.findAllEssayByZanDesc();

        return essayList;
    }

    public List<Essay> getAllEssay()
    {
        List<Essay> essayList = essayImpl.findAllEssay();

        return essayList;
    }

}
