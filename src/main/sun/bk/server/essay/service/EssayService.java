package main.sun.bk.server.essay.service;

import main.sun.bk.server.essay.model.Essay;

import java.util.List;

/**
 * Created by SUN on 2017/4/24.
 */
public interface EssayService {
    public void addEssay(Essay essay);

    public List<Essay> getAllEssayByRecommend(String recommend);

    public List<Essay> getAllEssayLikeTitle(String title);

    public List<Essay> getAllEssayByType(String type);

    public List<Essay> getAllEssayByZanDesc();

    public List<Essay> getAllEssay();
}
