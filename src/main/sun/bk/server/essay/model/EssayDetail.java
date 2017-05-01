package main.sun.bk.server.essay.model;

import main.sun.bk.server.comment.model.Evaluates;

import java.util.List;

/**
 * Created by SUN on 2017/5/1.
 */
public class EssayDetail {
    private Essay essay;
    private List<Evaluates> evaluatesList;

    public Essay getEssay() {
        return essay;
    }

    public void setEssay(Essay essay) {
        this.essay = essay;
    }

    public List<Evaluates> getEvaluatesList() {
        return evaluatesList;
    }

    public void setEvaluatesList(List<Evaluates> evaluatesList) {
        this.evaluatesList = evaluatesList;
    }
}
