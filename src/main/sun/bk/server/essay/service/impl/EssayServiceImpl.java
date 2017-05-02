package main.sun.bk.server.essay.service.impl;

import main.sun.bk.server.comment.dao.impl.CommentImpl;
import main.sun.bk.server.comment.model.Comment;
import main.sun.bk.server.comment.model.Evaluates;
import main.sun.bk.server.commentReply.dao.impl.CommentReplyImpl;
import main.sun.bk.server.commentReply.model.CommentReply;
import main.sun.bk.server.essay.dao.impl.EssayImpl;
import main.sun.bk.server.essay.model.Essay;
import main.sun.bk.server.essay.model.EssayDetail;
import main.sun.bk.server.essay.service.EssayService;
import main.sun.bk.server.users.dao.impl.UserImpl;
import main.sun.bk.server.users.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/4/24.
 */
public class EssayServiceImpl implements EssayService{
    private CommentImpl commentImpl = new CommentImpl();
    private EssayImpl essayImpl = new EssayImpl();
    private UserImpl userImpl = new UserImpl();
    private CommentReplyImpl commentReplyImpl = new CommentReplyImpl();
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

    @Override
    public Essay getEssayById(int essayId)
    {
        Essay essay = essayImpl.findEssayById(essayId);

        return essay;
    }

    @Override
    public EssayDetail getEssayDetailById(int essayId)
    {
        Essay essay = essayImpl.findEssayById(essayId);
        List<Comment> commentList = commentImpl.findCommentByEssayId(essayId);
        List<Evaluates> evaluatesList = new ArrayList<Evaluates>();
        EssayDetail essayDetail = null;
        try
        {
            if(essay != null && !commentList.isEmpty())
            {
                essayDetail = new EssayDetail();
                essayDetail.setEssay(essay);
                for(Comment comment : commentList)
                {
                    Evaluates evaluates = new Evaluates();
                    evaluates.setCommentId(comment.getCommentId());
                    evaluates.setUserName(comment.getUserName());
                    evaluates.setCommentText(comment.getComment());
                    evaluates.setCommentTime(comment.getCommentTime());

                    User user = userImpl.findUserByUserName(comment.getUserName());
                    evaluates.setUserImg(user.getImg());

                    try
                    {
                        CommentReply commentReply = commentReplyImpl.findCommentReplyById(comment.getCommentId());
                        evaluates.setReplyUser(commentReply.getReplyUser());
                        evaluates.setReplyContent(commentReply.getReplyContent());
                        evaluates.setReplyTime(commentReply.getReplyTime());
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    
                    evaluatesList.add(evaluates);
                }
                essayDetail.setEvaluatesList(evaluatesList);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return essayDetail;
    }
}
