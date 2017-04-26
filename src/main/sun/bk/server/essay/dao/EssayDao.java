package main.sun.bk.server.essay.dao;

import main.sun.bk.server.essay.model.Essay;

import java.util.List;

/**
 * Created by SUN on 2017/4/24.
 */
public interface EssayDao {
    public boolean addEssay(Essay essay);

    public List<Essay> findEssayByRecommend(String recommend);

    public List<Essay> findAllEssayByLikeTitle(String title);

    public List<Essay> findEssayByType(String type);

    public List<Essay> findAllEssayByZanDesc();

    public List<Essay> findAllEssay();
}
